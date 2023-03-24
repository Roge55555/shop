package com.effective.shop.sevice.impl;

import com.effective.shop.entity.RegistrationStatus;
import com.effective.shop.exceptions.NoSuchElementException;
import com.effective.shop.model.Status;
import com.effective.shop.repository.RegistrationStatusRepository;
import com.effective.shop.sevice.RegistrationStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationStatusServiceImpl implements RegistrationStatusService {

    private final RegistrationStatusRepository registrationStatusRepository;

    @Override
    public RegistrationStatus findByName(Status name) {
        return registrationStatusRepository.findByName(name).orElseThrow(() -> {
            throw new NoSuchElementException(name.name());
        });
    }
}
