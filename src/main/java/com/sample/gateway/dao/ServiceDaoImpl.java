package com.sample.gateway.dao;

import com.sample.gateway.entity.Service;
import com.sample.gateway.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServiceDaoImpl implements ServiceDao {

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public Optional<Service> getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId);
    }
}
