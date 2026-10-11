package com.soulemane.lil.room.clr;

import java.util.UUID;

import lombok.Data;

@Data 
public class Room {
    private UUID id;
    private String name;
    private String number;
    private String info;
}
