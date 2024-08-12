package com.sample.gateway.dao;

import com.sample.gateway.entity.FormField;
import com.sample.gateway.entity.User;
import com.sample.gateway.repository.FormFieldRepository;
import com.sample.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FormDaoImpl implements FormDao {

    @Autowired
    FormFieldRepository formFieldRepository;

    @Override
    public List<FormField> getMandatoryFormFields(Long serviceId) {
        return formFieldRepository.findByMandatoryTrueAndServices_Id(serviceId);
    }
}
