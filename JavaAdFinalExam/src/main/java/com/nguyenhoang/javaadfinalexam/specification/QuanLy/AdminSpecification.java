package com.nguyenhoang.javaadfinalexam.specification.QuanLy;

import com.nguyenhoang.javaadfinalexam.entity.QuanTri;
import com.nguyenhoang.javaadfinalexam.form.QuanTri.FilteringManagerForm;
import com.nguyenhoang.javaadfinalexam.utils.Utils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AdminSpecification {
    @SuppressWarnings("deprecation")
    public static Specification<QuanTri> buildWhere(String search, FilteringManagerForm form){
        Specification<QuanTri> where = null;

        //search by fullName
        if(!StringUtils.isEmpty(search)){
            search = Utils.formatSearch(search);

            CustomAdminSpecification fullName = new CustomAdminSpecification("fullName",search);
            where = Specification.where(fullName);
        }

        //filter by Gender
        if(form != null && form.getGender() != null){
            CustomAdminSpecification gender =new CustomAdminSpecification("gender",form.getGender());

            if(where != null) where = where.and(gender);
            else where = Specification.where(gender);
        }

        //filter by min nam KN
        if(form != null && form.getMinNamKN() != null){
            CustomAdminSpecification minKN = new CustomAdminSpecification("minKN", form.getMinNamKN());

            if(where != null) where = where.and(minKN);
            else where = Specification.where(minKN);
        }

        //filter by max nam KN
        if(form != null && form.getMaxNamKN() != null){
            CustomAdminSpecification maxKN = new CustomAdminSpecification("maxKN", form.getMaxNamKN());

            if(where != null) where = where.and(maxKN);
            else where = Specification.where(maxKN);
        }
        return where;
    }
}
