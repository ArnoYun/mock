package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping
public class MockController {

    @GetMapping(value = "get")
    public List<Map<String, String>> list(){
        return new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{put("text", "Hello World"); }});
        }};

    }
    @PostMapping(value = "login")
    public List<Map<String, String>> authorization (@RequestBody Map<String, String> credentials){
        String name = credentials.get("username");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        return new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{put("time", time);put("login", name);}});
        }};
    }

}
