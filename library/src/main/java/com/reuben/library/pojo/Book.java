package com.reuben.library.pojo;

import lombok.Data;

import java.text.DateFormat;
import java.text.DecimalFormat;

@Data
public class Book {
    int id;
    String name;
    String author;
    String publisher;
    String version;
}
