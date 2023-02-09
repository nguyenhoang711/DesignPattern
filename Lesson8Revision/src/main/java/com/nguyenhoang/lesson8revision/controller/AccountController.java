package com.nguyenhoang.lesson8revision.controller;


import com.nguyenhoang.lesson8revision.dto.AccountDTO;
import com.nguyenhoang.lesson8revision.entity.Account;
import com.nguyenhoang.lesson8revision.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/v1/accounts")
@RestController
public class AccountController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAccountService service;
    @GetMapping
    public Page<AccountDTO> getAllAccountBySearch(Pageable pageable, @RequestParam(name = "search", required = false) String search){
        //lay ra cac entity
        Page<Account> entities = service.getAllAccountsByName(pageable,search);

        //convert from entities --> dto
        List<AccountDTO> dtos = modelMapper.map(
                entities.getContent(),
                new TypeToken<List<AccountDTO>>(){}.getType()
        );
        Page<AccountDTO> dtoPages = new PageImpl<>(dtos,pageable,entities.getTotalElements());
        return dtoPages;
    }

    @GetMapping(value = "/{id}")
    public AccountDTO getAccountByID(@PathVariable(name = "id") int id){
        Account entity = service.getAccountByID(id);

        return modelMapper.map(entity,AccountDTO.class);
    }
}
