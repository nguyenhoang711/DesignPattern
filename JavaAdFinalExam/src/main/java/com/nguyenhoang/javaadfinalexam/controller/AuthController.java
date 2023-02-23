package com.nguyenhoang.javaadfinalexam.controller;

import java.security.Principal;

import com.nguyenhoang.javaadfinalexam.dto.LoginInfoDTO;
import com.nguyenhoang.javaadfinalexam.entity.Staff;
import com.nguyenhoang.javaadfinalexam.service.IStaffService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "api/v1/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IStaffService service;

	@GetMapping("/login")
	public LoginInfoDTO login(Principal principal) {

		String username = principal.getName();
		Staff entity = service.getStaffByUserName(username);

		// convert entity --> dto
		LoginInfoDTO dto = modelMapper.map(entity, LoginInfoDTO.class);

		return dto;
	}
}
