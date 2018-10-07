package com.example.demo.service;

import com.example.demo.domain.Result;

import java.util.List;

public interface DemoService {

    List<String> checkUpdates();

    Result insertModel(final String webpage);

    String refresh();
}
