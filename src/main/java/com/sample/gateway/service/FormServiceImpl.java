package com.sample.gateway.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sample.gateway.dao.FormDao;
import com.sample.gateway.dao.ServiceDao;
import com.sample.gateway.dao.UserDao;
import com.sample.gateway.entity.FormField;
import com.sample.gateway.entity.User;
import com.sample.gateway.integration.SampleApiClient;
import com.sample.gateway.view.FieldsView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FormServiceImpl implements FormService {

    @Autowired
    FormDao formDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ServiceDao serviceDao;

    @Autowired
    SampleApiClient sampleApiClient;

    @Override
    public List<FieldsView> getFormData(Long serviceId, Long userId) throws Exception {
        log.info("Received request to fetch missing fields in service");

        List<FormField> mandatoryFields = formDao.getMandatoryFormFields(serviceId);

        User user = userDao.getUserById(userId).orElseThrow(() -> new Exception("User details not found"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());

        Map<String, Object> userMap = mapper.convertValue(user, Map.class);
        List<FieldsView> requiredFields = mandatoryFields.stream()
                .filter(formField -> ObjectUtils.isEmpty(userMap.get(formField.getFieldName())))
                .map(formField -> mapper.convertValue(formField, FieldsView.class))
                .collect(Collectors.toList());
        return requiredFields;
    }

    @Override
    public Boolean submitFormData(Map<String, String> formData, Long serviceId, Long userId) throws Exception {

        User user = userDao.getUserById(userId).orElseThrow(() -> new Exception("User details not found"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.registerModule(new JavaTimeModule());

        //populating full data for validation
        Map<String, String> fullData = mapper.convertValue(user, Map.class);
        fullData.putAll(formData);
        //now the fullData has both available info from DB and the missing info user sent

        if (validateFields(fullData, serviceId)) {
            User updatedUser = mapper.convertValue(fullData, User.class);
            userDao.saveUser(updatedUser);
        } else {
            throw new Exception("Validation Failed. Please enter all the mandatory fields.");
        }

        String url = serviceDao.getServiceById(serviceId).orElseThrow().getUrl();

        log.info("Submitting form to Sample API: {}", user);
        Boolean obj = sampleApiClient.submitFormData(URI.create(url), userId, user);
        return obj;
    }

    private boolean validateFields(Map<String, String> formData, Long serviceId) {

        List<FormField> mandatoryFields = formDao.getMandatoryFormFields(serviceId);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());

        boolean isValid = mandatoryFields.stream()
                .filter(formField -> ObjectUtils.isEmpty(formData.get(formField.getFieldName())))
                .findAny().isEmpty();

        return isValid;
    }
}
