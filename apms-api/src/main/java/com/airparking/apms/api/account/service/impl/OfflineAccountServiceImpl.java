package com.airparking.apms.api.account.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airparking.apms.api.account.entity.OfflineAccount;
import com.airparking.apms.api.account.mapper.OfflineAccountMapper;
import com.airparking.apms.api.account.service.OfflineAccountService;
import com.airparking.core.base.service.AbstractService;

/**
 * Created by ryan on 2016-07-27.
 */
@Service("offlineAccountService")
public class OfflineAccountServiceImpl extends AbstractService implements OfflineAccountService {
  @Autowired
  private OfflineAccountMapper offlineAccountMapper;

  @Override
  public List<OfflineAccount> findOfflineAccount(Long parkId) {
    return offlineAccountMapper.findOfflineAccount(parkId);
  }

  @Override
  public List<OfflineAccount> findByToday(Long parkId, Date start, Date end) {
    return offlineAccountMapper.findByToday(parkId, start, end);
  }

  @Override
  public OfflineAccount findByUsername(String username, Long parkId) {
    return offlineAccountMapper.findByUsername(username, parkId);
  }

  @Override
  public void save(OfflineAccount account) {
    offlineAccountMapper.add(account);
  }

  @Override
  public void update(OfflineAccount account) {
    offlineAccountMapper.update(account);
  }
}