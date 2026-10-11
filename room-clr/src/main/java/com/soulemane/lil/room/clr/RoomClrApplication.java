package com.soulemane.lil.room.clr;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@SpringBootApplication
public class RoomClrApplication {

  public static void main(String[] args) {
    SpringApplication.run(RoomClrApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate(){
    return new RestTemplate();
  }

  @Bean
  public CommandLineRunner run(RestTemplate restTemplate) {
    return args -> {
      ResponseEntity<List<Room>> rooms = restTemplate.exchange("http://localhost:8080/api/rooms",
          HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
          });
      rooms.getBody().forEach(System.out::println);
    };
  }

}