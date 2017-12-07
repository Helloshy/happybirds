package com.kapphk.web.service.web.imethod.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.kapphk.web.utils.GridReq;
import com.kapphk.web.utils.Result;

public interface BeanReportBorrowService {

	public Result searchList(Result rs, String string, String startTime, String endTime,
			GridReq gridReq)throws Exception;

	public void exportExcel(HttpServletResponse response);

	public Result importExcel(Result rs, MultipartFile file)throws Exception;

}
