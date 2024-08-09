package com.wealth.telegram.repository;

import com.wealth.telegram.model.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

}
