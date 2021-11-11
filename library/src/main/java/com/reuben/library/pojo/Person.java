package com.reuben.library.pojo;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private int id;
    private LocalDateTime birthday;
    private String nation;


}
