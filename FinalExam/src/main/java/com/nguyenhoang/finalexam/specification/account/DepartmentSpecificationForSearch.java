package com.nguyenhoang.finalexam.specification.account;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.nguyenhoang.finalexam.entity.Department;
import com.nguyenhoang.finalexam.utils.Utils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;

public class DepartmentSpecificationForSearch {

	@SuppressWarnings("deprecation")
	public static Specification<Department> buildWhere(String search) {

		Specification<Department> where = null;

		if (!StringUtils.isEmpty(search)) {

			search = Utils.formatSearch(search);

			CustomSpecification2 name = new CustomSpecification2("name", search);
			where = Specification.where(name);
		}

		return where;
	}
}

@SuppressWarnings("serial")
@RequiredArgsConstructor
class CustomSpecification2 implements Specification<Department> {

	@NonNull
	private String field;
	@NonNull
	private Object value;

	@Override
	public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (field.equalsIgnoreCase("name")) {
			return criteriaBuilder.like(root.get("name"), "%" + value.toString() + "%");
		}

		return null;
	}
}
