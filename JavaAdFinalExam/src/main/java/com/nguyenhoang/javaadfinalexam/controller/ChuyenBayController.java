package com.nguyenhoang.javaadfinalexam.controller;


import com.nguyenhoang.javaadfinalexam.dto.ChuyenBayDTO;
import com.nguyenhoang.javaadfinalexam.entity.ChuyenBay;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.CreatingFlightForm;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.FilteringFlightForm;
import com.nguyenhoang.javaadfinalexam.form.ChuyenBay.UpdatingFlightForm;
import com.nguyenhoang.javaadfinalexam.service.IChuyenBayService;
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
@RequestMapping(value = "/api/final/v1/flights")
@Validated
public class ChuyenBayController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IChuyenBayService service;

    @GetMapping

    public Page<ChuyenBayDTO> getAllFlights(Pageable pageable,
                                            @RequestParam(name = "search", required = false) String search,
                                            FilteringFlightForm form){
        Page<ChuyenBay> entityPages = service.getAllChuyenBay(pageable, search,form);

        //convert from entity to dtos
        List<ChuyenBayDTO> dtos= modelMapper.map(entityPages.getContent(),
                new TypeToken<List<ChuyenBayDTO>>(){}.getType());

        return new PageImpl<>(dtos,pageable,entityPages.getTotalElements());
    }

    @PostMapping
    public void createChuyenBay(@RequestBody @Valid CreatingFlightForm form){
        service.createChuyenBay(form);
    }


    @PutMapping(value = "/{id}")
    //localhost:8080/api/final/v1/flights/4
    public void updateChuyenBay(@PathVariable(name = "id") int id, @RequestBody @Valid UpdatingFlightForm form){
        form.setId(id);
        service.updateChuyenBay(form);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteByID(@PathVariable(name = "id") int id){
        service.deleteFlight(id);
    }

    @DeleteMapping
    public void deleteChuyenBays(@RequestParam(name = "ids") List<Integer> ids){
        service.deleteChuyenBays(ids);
    }
}
