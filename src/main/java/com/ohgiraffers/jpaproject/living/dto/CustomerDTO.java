package com.ohgiraffers.jpaproject.living.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDTO {
    private int customerCode;
    private String customerName;
    private String customerGender;
    private int customerAge;
    private int categoryNo;
    private int roomCode;
}
