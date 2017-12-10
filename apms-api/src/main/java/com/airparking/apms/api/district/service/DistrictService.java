package com.airparking.apms.api.district.service;

import java.util.List;
import java.util.Map;

/**
 * Created by ryan on 2016-01-28.
 */
public interface DistrictService {
	List<Map> listAllEnabled(int level);
}