package com.sample.gateway.repository;

import com.sample.gateway.entity.FormField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormFieldRepository extends JpaRepository<FormField, Long> {

    List<FormField> findByMandatoryTrueAndServices_Id(Long serviceId);
}
