package com.airparking.apms.api.district.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.district.service.DistrictService;
import com.airparking.apms.server.handler.AbstractController;

/**
 * Created by ryan on 2016-01-28.
 */
@Controller("district")
public class DistrictController extends AbstractController {
    @Autowired
    private DistrictService districtService;
}