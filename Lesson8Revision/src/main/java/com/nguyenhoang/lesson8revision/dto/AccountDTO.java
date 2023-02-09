package com.nguyenhoang.lesson8revision.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//DTO: các đối tượng dùng để xuất ra cho người dùng
public class AccountDTO {
    private String userName;

    private String departmentName;
}
