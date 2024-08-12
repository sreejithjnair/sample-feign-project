package com.sample.gateway.service;

import com.sample.gateway.view.FieldsView;

import java.util.List;
import java.util.Map;

public interface FormService {

    List<FieldsView> getFormData(Long serviceId, Long userId) throws Exception;

    Boolean submitFormData(Map<String, String> formData, Long serviceId, Long userId) throws Exception;
}
