package com.nguyenhoang.javaadfinalexam.controller;

import com.nguyenhoang.javaadfinalexam.dto.FromToTuyenDTO;
import com.nguyenhoang.javaadfinalexam.dto.TuyenBayDTO;
import com.nguyenhoang.javaadfinalexam.entity.TuyenBay;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.CreatingFlightLineForm;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.FilteringFlightLineForm;
import com.nguyenhoang.javaadfinalexam.repository.ITuyenBayRepository;
import com.nguyenhoang.javaadfinalexam.service.ITuyenBayService;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.UpdatingFlightLineForm;
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
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(value = "/api/final/v1/tuyens")
@Validated
public class TuyenBayController {

    @Autowired
    private ITuyenBayService service;

    @Autowired
    private ITuyenBayRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    //localhost:8080/api/final/v1/tuyens?search=Minh&minPrice=7000000&maxPrice=10000000&pageNumber=1
    public Page<TuyenBayDTO> getAllTuyenBay(Pageable pageable,
                                            @RequestParam(name = "search", required = false) String search,
                                            @Valid FilteringFlightLineForm form
    ){
        Page<TuyenBay> entity = service.getAllTuyenBays(pageable,search,form);

        //convert from entity -->dto
        List<TuyenBayDTO> dtos = modelMapper.map(entity.getContent(),
                new TypeToken<List<TuyenBayDTO>>(){}.getType());

        return new PageImpl<>(dtos,pageable,entity.getTotalElements());
    }

    //lay ra thanh pho den va thanh pho di
    @GetMapping(value = "/cities")
//    @PreAuthorize("hasAnyAuthority('PILOT','ADMIN')")
    public List<FromToTuyenDTO> getAllCities(){
        List<TuyenBay> tuyens = repository.findAll();

        //convert from tuyen to dtos
        return modelMapper.map(tuyens, new TypeToken<List<FromToTuyenDTO>>(){}.getType());
    }

    //check exists
    @GetMapping(value = "/city/{from}/{to}/exists")
    public boolean existsByCities(@PathVariable(name = "from") String fromCity,
                                  @PathVariable(name = "to") String toCity)
    {
        return service.existByCities(fromCity,toCity);
    }

    @PostMapping
    public void createTuyenBay(@RequestBody @Valid CreatingFlightLineForm form){
        service.createFlightLine(form);
    }

    @PutMapping(value = "/{id}")
    public void updateTuyenBay(@PathVariable(name = "id") int id,
                               @RequestBody @Valid UpdatingFlightLineForm form){
        form.setId(id);
        service.updateFlightLine(form);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteTuyenBay(@PathVariable(name = "id") int id){
        service.deleteFlightLine(id);
    }


    @DeleteMapping
    //localhost:8080/api/final/v1/tuyens/del?ids=2,5,6
    public void deleteTuyenBays(@RequestParam(name = "ids") List<Integer> ids){
        service.deleteTuyenBays(ids);
    }
}
