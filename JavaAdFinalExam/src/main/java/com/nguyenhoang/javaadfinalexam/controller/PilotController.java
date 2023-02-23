package com.nguyenhoang.javaadfinalexam.controller;


import com.nguyenhoang.javaadfinalexam.dto.PhiCongDTO;
import com.nguyenhoang.javaadfinalexam.dto.PilotNameDTO;
import com.nguyenhoang.javaadfinalexam.entity.PhiCong;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.CreatingPilotForm;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.FilteringPilotsForm;
import com.nguyenhoang.javaadfinalexam.form.PhiCong.UpdatingPilotsForm;
import com.nguyenhoang.javaadfinalexam.repository.IPhiCongRepository;
import com.nguyenhoang.javaadfinalexam.service.IPhiCongService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/final/v1/pilots")
@Validated
public class PilotController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IPhiCongService service;

    @Autowired
    private IPhiCongRepository repository;

    @GetMapping
    public Page<PhiCongDTO> getAllPilot(Pageable pageable,
                                        @RequestParam(name = "search", required = false) String search,
                                        @Valid FilteringPilotsForm form){
        Page<PhiCong> entityPages = service.getAllPilot(pageable,search,form);

        //convert from entity -->dtos
        List<PhiCongDTO> dtos =modelMapper.map(entityPages.getContent(),
                new TypeToken<List<PhiCongDTO>>(){}.getType());

        return new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
    }

    @GetMapping(value = "/all")
//    @PreAuthorize("hasAnyAuthority('PILOT','ADMIN')")
    public List<PilotNameDTO> getAllPhiCong(){
        List<PhiCong> entities = repository.findAll();
        //convert to dtos
        List<PilotNameDTO> dtos = modelMapper.map(entities,new TypeToken<List<PilotNameDTO>>(){}.getType());
        return dtos;
    }

    @PostMapping
    public void createPilot(@RequestBody @Valid CreatingPilotForm form){
        service.createPilot(form);
    }

    @PutMapping(value = "/{id}")
    public void updatePilot(@PathVariable(name = "id") int id,
                            @RequestBody @Valid UpdatingPilotsForm form){
        form.setId(id);
        service.updatePilotInfo(form);
    }
}
