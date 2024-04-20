package com.ohgiraffers.jpaproject.living.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_customer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerCode;
    private String customerName;
    private String customerGender;
    private int customerAge;
    private int categoryNo;
    private int roomCode;


    public void modifyCustomerName(String customerName) {
    this.customerName = customerName;
    }
    public void modifyCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }
    public void modifyCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }
    public void modifyCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }
    public void modifyRoomCode(int roomCode) {
        this.roomCode = roomCode;
    }
}
