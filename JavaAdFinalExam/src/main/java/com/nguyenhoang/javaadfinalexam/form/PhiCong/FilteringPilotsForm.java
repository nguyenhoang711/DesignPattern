package com.nguyenhoang.javaadfinalexam.form.PhiCong;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class FilteringPilotsForm {
    @Pattern(regexp = "Male|Female|Undefined", message = "Giới tính phải là Nam, Nữ hoặc Khác")
    private String gender;

    @PositiveOrZero(message = "Phải là số nguyên dương hoặc bằng 0")
    private Integer minSoGiobay;

    @PositiveOrZero(message = "Phải là số nguyên dương hoặc bằng 0")
    private Integer maxSoGioBay;

}
