package com.nguyenhoang.javaadfinalexam.specification.TuyenBay;


import com.nguyenhoang.javaadfinalexam.entity.TuyenBay;
import com.nguyenhoang.javaadfinalexam.form.TuyenBay.FilteringFlightLineForm;
import com.nguyenhoang.javaadfinalexam.utils.Utils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class TuyenBaySpecification {
    @SuppressWarnings("deprecation")
    public static Specification<TuyenBay> buildWhere(String search, FilteringFlightLineForm form){
        Specification<TuyenBay> where = null;

        if (!StringUtils.isEmpty(search)) {

            search = Utils.formatSearch(search);

            CustomTuyenBaySpecification fromCity = new CustomTuyenBaySpecification("fromCity", search);
            CustomTuyenBaySpecification toCity = new CustomTuyenBaySpecification("toCity", search);
            where = Specification.where(fromCity).or(toCity);
        }

        //filter by min price
        if(form != null && form.getMinPrice() != null){
            //truyen xuong cho custom specification
            CustomTuyenBaySpecification minPrice = new CustomTuyenBaySpecification("minPrice",form.getMinPrice());

            if(where != null) where = where.and(minPrice);
            else where = Specification.where(minPrice);
        }

        //filter by max price
        if(form != null && form.getMaxPrice() != null){
            //truyen xuong cho custom specification
            CustomTuyenBaySpecification maxPrice = new CustomTuyenBaySpecification("maxPrice",form.getMaxPrice());

            if(where == null) where = Specification.where(maxPrice);
            else where = where.and(maxPrice);
        }
        return where;
    }
}
