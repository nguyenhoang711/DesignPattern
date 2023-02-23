package com.nguyenhoang.javaadfinalexam.form.TuyenBay;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class CreatingFlightLineForm {
    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @NotNull(message = "{All.creating.form.*.NotNull}")
    private String fromCity;

    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @NotNull(message = "{All.creating.form.*.NotNull}")
    private String toCity;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @Positive(message = "{All.creating.form.number.Positive}")
    private double price;
}
