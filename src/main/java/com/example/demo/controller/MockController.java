package com.example.demo.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.demo.Sql.*;

@RestController
@RequestMapping
public class MockController {

    @GetMapping(value = "get_login/{login}")
    public List<Map<String, String>> list(String get, @PathVariable String login)  {

        try {
            ArrayList<String> get_result = select(login);
            System.out.println(get_result);
            String get_login = get_result.get(0);
            String get_time = get_result.get(1);
            return new ArrayList<Map<String, String>>() {{
                add(new HashMap<String, String>() {{
                    put("login", get_login);
                    put("time", get_time);
                }});
            }};
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


        @PostMapping(value = "login")
        public List<Map<String, String>> authorization (@RequestBody Map<String, String> credentials) {
            String login = credentials.get("login");
            String password = credentials.get("password");
            String name = credentials.get("name");
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String time = format.format(date);
            try {
                if(insert(login, password, name) == 1){
                    insert_time(login, time);
                    return new ArrayList<Map<String, String>>() {{
                        add(new HashMap<String, String>() {{
                            put("time", time);
                            put("login", login);
                        }});
                    }};
                }
                else {
                    throw new Exception() ;
                }
            }
            catch (Exception e){
                throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED);
            }
        }

}
