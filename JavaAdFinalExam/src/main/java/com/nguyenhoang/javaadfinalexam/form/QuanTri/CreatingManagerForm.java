package com.nguyenhoang.javaadfinalexam.form.QuanTri;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.PrePersist;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class CreatingManagerForm {
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

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @Pattern(regexp = "ADMIN", message = "Role phải là ADMIN")
    private String role;

    @NotBlank(message = "{All.creating.form.*.NotBlank}")
    @NotNull(message = "{All.creating.form.*.NotNull}")
    private String gender;

    @NotNull(message = "{All.creating.form.*.NotNull}")
    @PositiveOrZero(message = "Phải là số nguyên dương hoặc bằng 0")
    private int namKN;
}
