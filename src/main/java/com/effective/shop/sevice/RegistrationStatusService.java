package com.effective.shop.sevice;

import com.effective.shop.entity.RegistrationStatus;
import com.effective.shop.model.Status;

public interface RegistrationStatusService {

    RegistrationStatus findByName(Status name);
}
