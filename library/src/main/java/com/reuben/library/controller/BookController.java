package com.reuben.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired

    @GetMapping("/hello")
    public String sayHello(){
        return "hello world";
    }

    @Autowired
    @GetMapping("/bookList")
    public Map<String, Object> bookList(){
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("三国演义", "123");
         return  map;
    }

    @GetMapping("/saynum")
    public String sayNumber(@RequestParam(value = "id")int id){
        return "数字"+id;
    }

    @GetMapping("/factorial")
    public String factorial(@RequestParam(value = "num")int num){
        int temp = 1;
        for(int i = 1; i <= num; i++){
            temp *= i;
        }
        return "阶乘结果为"+temp;
    }

}
