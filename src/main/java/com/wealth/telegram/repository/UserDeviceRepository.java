package com.wealth.telegram.repository;

import com.wealth.telegram.model.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {

}
