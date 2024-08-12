package com.sample.gateway.dao;

import com.sample.gateway.entity.Service;

import java.util.Optional;

public interface ServiceDao {

    Optional<Service> getServiceById(Long serviceId);

}
