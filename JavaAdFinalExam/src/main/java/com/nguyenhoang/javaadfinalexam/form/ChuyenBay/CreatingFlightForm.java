package com.nguyenhoang.javaadfinalexam.form.ChuyenBay;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
public class CreatingFlightForm {
    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @NotNull(message = "{All.creating.form.*.NotNull}")
    @Pattern(regexp = "AIRBUS|BOEING|COMAC", message = "The plane type must be AIRBUS, BOEING or COMAC")
    private String typePlane;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @FutureOrPresent(message = "Date time must be in the future or at the present")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date startTime;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @FutureOrPresent(message = "Date time must be in the future or at the present")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date endTime;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @Positive(message = "{All.creating.form.number.Positive}")
    private int tuyenBayId;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @Positive(message = "{All.creating.form.number.Positive}")
    private int phiCongId;
}
