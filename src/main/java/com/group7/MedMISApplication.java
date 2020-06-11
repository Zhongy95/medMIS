package com.group7;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.group7.sys.mapper"})
@MapperScan(basePackages = {"com.group7.bus.mapper"})
public class MedMISApplication {

  public static void main(String[] args) {
    SpringApplication.run(MedMISApplication.class, args);
  }
}
