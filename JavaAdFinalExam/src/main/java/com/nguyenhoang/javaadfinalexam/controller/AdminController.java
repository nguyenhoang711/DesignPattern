package com.nguyenhoang.javaadfinalexam.controller;

import com.nguyenhoang.javaadfinalexam.dto.QuanTriDTO;
import com.nguyenhoang.javaadfinalexam.entity.QuanTri;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.CreatingManagerForm;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.FilteringManagerForm;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.UpdatingManagerForm;
import com.nguyenhoang.javaadfinalexam.service.IAdminService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/final/v1/admins")
@Validated
public class AdminController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAdminService service;

    @GetMapping
    public Page<QuanTriDTO> getAllAdmins(Pageable pageable,
                                         @RequestParam(name = "search", required = false) String search,
                                         @Valid FilteringManagerForm form){
        Page<QuanTri> entityPages = service.getAllAdmins(pageable,search,form);

        //convert from entity -->dtos
        List<QuanTriDTO> dtos = modelMapper.map(entityPages.getContent(),
                new TypeToken<List<QuanTriDTO>>(){}.getType());

        return new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
    }

    @PostMapping
    //localhost:8080/api/final/v1/admins
    public void createAdmin(@RequestBody @Valid CreatingManagerForm form){
        service.createAdmin(form);
    }

    @PutMapping(value = "/{id}")
    public void updateAdmin(@PathVariable(name = "id") int id,
                            @RequestBody @Valid UpdatingManagerForm form){
        form.setId(id);
        service.updateAdmin(form);
    }

}
