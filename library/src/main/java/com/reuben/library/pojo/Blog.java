package com.reuben.library.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Blog {
    private int id;
    private String author;
    private List<Bowen> bowens;
}
