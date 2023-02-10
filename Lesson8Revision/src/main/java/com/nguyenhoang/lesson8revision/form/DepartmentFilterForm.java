package com.nguyenhoang.lesson8revision.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
//form: dùng để nhập thông tin gửi lên hệ thống
public class DepartmentFilterForm {
    private String name;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    //dinh dang du lieu truyen vao
    private Date createdDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    //định dạng dữ liệu truyền vào
    private Date minCreatedDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    //định dạng dữ liệu truyền vào
    private Date maxCreatedDate;

    private Integer minYear;

    private Integer maxYear;

    private Integer minMembers;

    private Integer maxMembers;

    private Long minAccounts;

    private Long maxAccounts;
}
