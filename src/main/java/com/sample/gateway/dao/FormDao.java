package com.sample.gateway.dao;

import com.sample.gateway.entity.FormField;

import java.util.List;

public interface FormDao {

    List<FormField> getMandatoryFormFields(Long serviceId);
}
