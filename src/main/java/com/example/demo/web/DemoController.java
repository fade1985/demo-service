package com.example.demo.web;

import com.example.demo.domain.NewModel;
import com.example.demo.domain.Result;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("/model")
    public ResponseEntity<Result> insertModel(@RequestBody NewModel model) {
        return ResponseEntity.ok().body(demoService.insertModel(model.getWebpage()));
    }

    @GetMapping("/updates")
    public ResponseEntity<List<String>> test(){
        return ResponseEntity.ok().body(demoService.checkUpdates());
    }

    @GetMapping("/refresh")
    public ResponseEntity<String> refresh(){
        return ResponseEntity.ok().body(demoService.refresh());
    }
}
