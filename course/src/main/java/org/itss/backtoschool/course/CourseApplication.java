package org.itss.backtoschool.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.itss.backtoschool.course.controller.TrafficController;
import org.itss.backtoschool.course.service.TrafficService;

@SpringBootApplication
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

}
