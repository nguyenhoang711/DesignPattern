package com.nguyenhoang.javaadfinalexam.form.ChuyenBay;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@NoArgsConstructor
public class UpdatingFlightForm {
    private int id;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date startTime;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date endTime;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @Positive(message = "{All.creating.form.number.Positive}")
    private int phiCongId;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @Positive(message = "{All.creating.form.number.Positive}")
    private int tuyenBayId;
}
