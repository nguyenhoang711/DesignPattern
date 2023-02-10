package com.nguyenhoang.lesson8revision.dto;

import com.nguyenhoang.lesson8revision.entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//DTO: các đối tượng dùng để xuất ra cho người dùng
public class AccountDTO {
    private int id;

    private String userName;

    private String departmentName;

    private String departmentType;
}
