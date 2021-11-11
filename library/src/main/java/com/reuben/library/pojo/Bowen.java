package com.reuben.library.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class Bowen {
    private int id;
    private String content;
    @ToString.Exclude
    private Blog blog;
    private List<String> comment;


}
