package com.nguyenhoang.lesson8revision.controller;


import com.nguyenhoang.lesson8revision.dto.AccountDTO;
import com.nguyenhoang.lesson8revision.entity.Account;
import com.nguyenhoang.lesson8revision.form.AccountFilterForm;
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
    //localhost:8080/api/v1/accounts?search=anh
    //localhost:8080/api/v1/accounts?search=Ma
    public Page<AccountDTO> getAllAccountBySearch(Pageable pageable, @RequestParam(name = "search", required = false) String search
    , AccountFilterForm filterForm){
        //lay ra cac entity
        Page<Account> entities = service.getAllAccountsByName(pageable,search,filterForm);

        //convert from entities --> dto
        List<AccountDTO> dtos = modelMapper.map(
                entities.getContent(),
                new TypeToken<List<AccountDTO>>(){}.getType()
        );
        Page<AccountDTO> dtoPages = new PageImpl<>(dtos,pageable,entities.getTotalElements());
        return dtoPages;
    }

    @GetMapping(value = "/{id}")
    //localhost:8080/api/v1/accounts/2
    public AccountDTO getAccountByID(@PathVariable(name = "id") int id){
        Account entity = service.getAccountByID(id);

        return modelMapper.map(entity,AccountDTO.class);
    }

    @GetMapping(value = "/type")
    public Page<AccountDTO> getAccountsByDepType(@RequestParam(name = "name",required = false) String search, Pageable pageable, AccountFilterForm form){
        Page<Account> entityPages = service.filterAccountsByDepType(pageable,search,form);
        //convert from entities to dtos
        List<AccountDTO> dtos = modelMapper.map(entityPages.getContent(),
                new TypeToken<List<AccountDTO>>(){}.getType());

        Page<AccountDTO> dtoPages = new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
        return dtoPages;
    }
}
