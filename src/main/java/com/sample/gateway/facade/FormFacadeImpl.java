package com.sample.gateway.facade;

import com.sample.gateway.service.FormService;
import com.sample.gateway.view.FieldsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FormFacadeImpl implements FormFacade {

    @Autowired
    FormService formService;

    @Override
    public List<FieldsView> getFormData(Long serviceId, Long userId) throws Exception {
        return formService.getFormData(serviceId, userId);
    }

    @Override
    public Boolean submitFormData(Map<String, String> formData, Long serviceId, Long userId) throws Exception {
        return formService.submitFormData(formData, userId, serviceId);
    }
}
