package com.nguyenhoang.finalexam.dto.auth;


import com.nguyenhoang.finalexam.entity.Account;
import lombok.Data;

@Data
public class LoginInfoDto {

	private int id;

	private String username;

	private String fullName;

	private String firstName;

	private String lastName;

	private Account.Role role;

}
