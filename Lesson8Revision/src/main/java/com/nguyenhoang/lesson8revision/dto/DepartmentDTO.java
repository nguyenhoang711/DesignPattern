package com.nguyenhoang.lesson8revision.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nguyenhoang.lesson8revision.entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
//DTO: các đối tượng dùng để xuất ra cho người dùng
public class DepartmentDTO {
    private String name;

    private int totalMember;

    //định dạng dữ liệu date trả về
    @JsonFormat(pattern= "dd-MM-yyyy")
    private Date createdDate;

    private String type;

    private List<AccountDTO> accounts;

    @Data
    @NoArgsConstructor
    //thêm class AccountDTO để truy vấn đến các thuộc tính bên trong của nó
    static class AccountDTO {
        //format tên thuộc tính trả về
        @JsonProperty("accountId")
        private int id;

        private String userName;
    }
}
