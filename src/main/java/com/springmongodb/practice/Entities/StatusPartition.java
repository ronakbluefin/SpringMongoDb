package com.springmongodb.practice.Entities;

import org.springframework.data.annotation.Id;

public class StatusPartition {

    @Id
    private String id;
    private String status;
    private Integer partition;
}
