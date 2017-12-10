package com.airparking.apms.api.account.service;

import java.util.Date;
import java.util.List;

import com.airparking.apms.api.account.entity.OfflineAccount;

/**
 * Created by ryan on 2016-07-27.
 */
public interface OfflineAccountService {
  List<OfflineAccount> findOfflineAccount(Long parkId);

  List<OfflineAccount> findByToday(Long parkId, Date start, Date end);

  OfflineAccount findByUsername(String username, Long parkId);

  void save(OfflineAccount account);

  void update(OfflineAccount account);
}