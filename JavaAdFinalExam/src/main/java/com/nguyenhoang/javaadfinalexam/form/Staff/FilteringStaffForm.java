package com.nguyenhoang.javaadfinalexam.form.Staff;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class FilteringStaffForm {
    @Pattern(regexp = "Male|Female|Undefined", message = "Giới tính là 1 trong 3 Male, Female hoặc Undefined")
    private String gender;

    @Pattern(regexp = "ADMIN|PILOT", message = "Role phải là ADMIN hoặc PILOT")
    private String role;
}
