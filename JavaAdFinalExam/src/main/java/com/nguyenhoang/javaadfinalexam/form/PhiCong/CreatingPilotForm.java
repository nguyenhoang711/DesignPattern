package com.nguyenhoang.javaadfinalexam.form.PhiCong;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class CreatingPilotForm {

    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @NotNull(message = "{All.creating.form.*.NotNull}")
    @Length(message = "{Staff.createStaff.form.name.Length}")
    private String firstName;

    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @NotNull(message = "{All.creating.form.*.NotNull}")
    @Length(message = "{Staff.createStaff.form.name.Length}")
    private String lastName;

    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @NotNull(message = "{All.creating.form.*.NotNull}")
    private String username;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @NotBlank(message = "{Staff.createStaff.form.password.NotBlank}")
    private String passWord;

    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @NotNull(message = "{All.creating.form.*.NotNull}")
    @Pattern(regexp = "Male|Female|Undefined", message = "Giới tính phải là Male, Female hoặc Undefined")
    private String gender;

    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @Pattern(regexp = "PILOT", message = "Role phải là PILOT")
    private String role;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @PositiveOrZero(message = "Phải là số nguyên dương hoặc bằng 0")
    private int soGioBay;
}
