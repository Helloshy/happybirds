package com.kapphk.web.service.web.imethod.system;

import java.util.List;

import com.kapphk.web.bean.system.BeanaAppVersions;
import com.kapphk.web.utils.Result;

public interface BeanAppVersionsService {

	public Result searchList(int page, int rows, Result rs) throws Exception;

	public Result saveData(BeanaAppVersions bean, Result rs) throws Exception;

	public Result delete(Result rs, List<Long> ids) throws Exception;

}
