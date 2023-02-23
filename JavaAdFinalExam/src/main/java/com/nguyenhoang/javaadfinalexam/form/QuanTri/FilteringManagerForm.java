package com.nguyenhoang.javaadfinalexam.form.QuanTri;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
public class FilteringManagerForm {
    @Pattern(regexp = "Male|Female|Undefined", message = "Giới tính phải là Nam, Nữ hoặc Khác")
    private String gender;

    @PositiveOrZero(message = "Phải là số nguyên dương hoặc bằng 0")
    private Integer minNamKN;

    @PositiveOrZero(message = "Phải là số nguyên dương hoặc bằng 0")
    private Integer maxNamKN;

}
