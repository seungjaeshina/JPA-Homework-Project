package com.ohgiraffers.jpaproject.living.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDTO {
    private int categoryNo;
    private String categoryName;
    private int roomCode;
}
