package com.nguyenhoang.javaadfinalexam.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuanTriDTO {
    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String gender;

    private int namKN;
}
