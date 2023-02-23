package com.nguyenhoang.javaadfinalexam.controller;

import com.nguyenhoang.javaadfinalexam.dto.PilotNameDTO;
import com.nguyenhoang.javaadfinalexam.dto.StaffDTO;
import com.nguyenhoang.javaadfinalexam.entity.PhiCong;
import com.nguyenhoang.javaadfinalexam.entity.Staff;
import com.nguyenhoang.javaadfinalexam.form.Staff.FilteringStaffForm;
import com.nguyenhoang.javaadfinalexam.repository.IStaffRepository;
import com.nguyenhoang.javaadfinalexam.service.IStaffService;
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
@RequestMapping(value = "api/final/v1/staffs")
@Validated
public class StaffController {
    @Autowired
    private IStaffService service;

    @Autowired
    private IStaffRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<StaffDTO> getAllStaffs(Pageable pageable,
                                       @RequestParam(name = "search", required = false) String search,
                                       @Valid FilteringStaffForm form){
        Page<Staff> entityPages = service.getAllStaff(pageable,search,form);

        //convert from entity -->dtos
        List<StaffDTO> dtos = modelMapper.map(entityPages.getContent(),
                new TypeToken<List<StaffDTO>>(){}.getType());

        return new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
    }

    @GetMapping(value = "/{username}/exists")
    public boolean isExistsByUserName(@PathVariable(name = "username")String userName){
        return repository.existsByUsername(userName);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable(name = "id") int id){
        service.deleteStaff(id);
    }

    @DeleteMapping
    //localhost:8080/api/final/v1/staffs?ids=7,8,9
    public void deleteStaffs(@RequestParam(name = "ids") List<Integer> ids){
        service.deleteStaffs(ids);
    }
}
