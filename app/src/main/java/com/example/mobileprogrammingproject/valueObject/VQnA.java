package com.example.mobileprogrammingproject.valueObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class VQnA {

    private int userId;
    private String content;
    private String creationDate;
    private String title;
}
