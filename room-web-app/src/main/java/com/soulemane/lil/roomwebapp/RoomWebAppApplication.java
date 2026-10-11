package com.soulemane.lil.roomwebapp;

import com.soulemane.lil.roomwebapp.data.repository.RoomRepository;
import com.soulemane.lil.roomwebapp.data.repository.StaffRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RoomWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomWebAppApplication.class, args);
	}

}
