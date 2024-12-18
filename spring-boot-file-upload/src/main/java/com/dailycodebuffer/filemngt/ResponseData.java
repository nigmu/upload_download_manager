package com.dailycodebuffer.filemngt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
    private LocalDate uploadDate; // New field
    private LocalTime uploadTime; // New field
}