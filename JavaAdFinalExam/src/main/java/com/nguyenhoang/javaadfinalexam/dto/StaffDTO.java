package com.nguyenhoang.javaadfinalexam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StaffDTO {
    private int id;

    private String fullName;

    private String gender;

    private String username;

    private String role;

}
