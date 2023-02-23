package com.nguyenhoang.javaadfinalexam.form.QuanTri;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
public class UpdatingManagerForm {
    private int id;

    @Pattern(regexp = "ADMIN", message = "Role phải là ADMIN")
    private String role;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @PositiveOrZero(message = "Phải là số nguyên dương hoặc bằng 0")
    private int namKN;
}
