package com.nguyenhoang.lesson8revision.controller;


import com.nguyenhoang.lesson8revision.dto.DepartmentDTO;
import com.nguyenhoang.lesson8revision.entity.Department;
import com.nguyenhoang.lesson8revision.form.DepartmentFilterForm;
import com.nguyenhoang.lesson8revision.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/departments")
public class DepartmentController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IDepartmentService service;

    @GetMapping
    //search theo tên department hoặc lấy theo username
    //localhost:8080/api/v1/departments?search=anh
    public Page<DepartmentDTO> getAllDepartment(Pageable pageable, @RequestParam(name = "search", required = false) String search){
        Page<Department> entityPages = service.getAllDepartmentByName(pageable,search);
        //convert from entities --> dtos
        List<DepartmentDTO> dtos = modelMapper.map(entityPages.getContent(),
                //mapping với page
                new TypeToken<List<DepartmentDTO>>(){}.getType());
        return new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
    }

    @GetMapping(value = "/filter")
    //localhost:8080/api/v1/departments/filter?maxCreatedDate=12-03-2020&minCreatedDate=10-03-2020
    //search theo date Range
    public Page<DepartmentDTO> getDepartmentByRange(Pageable pageable, @RequestParam(name = "search", required = false) String search
    , DepartmentFilterForm filterForm){
        Page<Department> entityPages = service.getAllDepartmentByDateRange(pageable,search,filterForm);
        //convert from entities to dtos
        List<DepartmentDTO> dtos = modelMapper.map(entityPages.getContent(),
                new TypeToken<List<DepartmentDTO>>(){}.getType());
        Page<DepartmentDTO> dtoPages = new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
        return dtoPages;
    }

    //get department by date
    @GetMapping(value = "/date")
    //localhost:8080/api/v1/departments/date?createdDate=10-03-2020
    public Page<DepartmentDTO> getDepartmentByDate(Pageable pageable, @RequestParam(name = "search",required = false)String date,DepartmentFilterForm filterForm){
        Page<Department> entityPages = service.getAllDepartmentByDate(pageable,date,filterForm);

        //convert from entities to dtos
        List<DepartmentDTO> dtos = modelMapper.map(entityPages.getContent(),
                new TypeToken<List<DepartmentDTO>>(){}.getType());

        Page<DepartmentDTO> dtoPages = new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
        return dtoPages;
    }
}
