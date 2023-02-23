package com.nguyenhoang.javaadfinalexam.form.TuyenBay;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class FilteringFlightLineForm {

    @Positive(message = "{All.creating.form.number.Positive}")
    private Double minPrice;

    @Positive(message = "{All.creating.form.number.Positive}")
    private Double maxPrice;
}
