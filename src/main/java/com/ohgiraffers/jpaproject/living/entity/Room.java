package com.ohgiraffers.jpaproject.living.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {

    @Id
    private int roomCode;
    private int totalRoom;
}
