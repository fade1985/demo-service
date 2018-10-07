package com.example.demo.service.impl;

import com.example.demo.domain.Result;
import com.example.demo.entity.Model;
import com.example.demo.repository.DemoRepository;
import com.example.demo.service.DemoService;
import com.example.demo.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Override public List<String> checkUpdates(){
        List<Model> models = demoRepository.findAll();

        return models.stream().filter(m -> updated(m)).map(m -> m.getWebpage()).collect(Collectors.toList());
    }

    @Override
    public Result insertModel(final String webpage){
        boolean exists = demoRepository.findByWebpage(webpage) != null;
        getInfo(webpage);
        if (!exists) {
            WebDriver driver = new HtmlUnitDriver();
            driver.get(webpage);
            WebElement element = ((HtmlUnitDriver) driver).findElementByClassName("model-name");
            List<Integer> values = getInfo(webpage);
            Model model = Model.builder()
                .name(element.getText())
                .pictures(values.get(0))
                .videos(values.get(1))
                .webpage(webpage)
                .build();
            demoRepository.save(model);
            return Result.builder().message(Constants.OPERATION_INSERT_OK).build();
        } else {
            return Result.builder().message(Constants.MODEL_EXISTS).build();
        }
    }

    @Override
    public String refresh() {
        List<Model> models = demoRepository.findAll();
        models.forEach(m -> {
            List<Integer> values = getInfo(m.getWebpage());
            if (values.get(0) > m.getPictures() || values.get(1) > m.getVideos()) {
                m.setPictures(values.get(0) );
                m.setVideos(values.get(1));
                demoRepository.save(m);
            }
        });

        return "Updated!!";
    }

    private List<Integer> getInfo(final String webpage){
        WebDriver driver = new HtmlUnitDriver();
        driver.get(webpage);
        List<WebElement> elements = ((HtmlUnitDriver) driver).findElementsByClassName("model-navbar-items-total");
        return Arrays.asList(Integer.valueOf(elements.get(0).getText().replace("(", "").replace(")", "")),
            Integer.valueOf(elements.get(1).getText().replace("(", "").replace(")", "")));
    }

    private boolean updated(final Model model){
        List<Integer> actualValues = getInfo(model.getWebpage());

        return actualValues.get(0) > model.getPictures() || actualValues.get(1) > model.getVideos();
    }
}
