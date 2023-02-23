package com.nguyenhoang.javaadfinalexam.form.TuyenBay;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class UpdatingFlightLineForm {

    private int id;

    @Positive(message = "{All.creating.form.number.Positive}")
    private double price;
}
