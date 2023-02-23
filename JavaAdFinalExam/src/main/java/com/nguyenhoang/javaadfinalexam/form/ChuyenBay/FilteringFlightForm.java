package com.nguyenhoang.javaadfinalexam.form.ChuyenBay;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class FilteringFlightForm {
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date minStartTime;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date maxStartTime;
}
