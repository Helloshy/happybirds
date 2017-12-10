package com.airparking.apms.api.parkPrice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.parkPrice.service.ParkPriceExtService;
import com.airparking.apms.server.handler.AbstractController;
/**
 * Created by ryan on 2016-07-28.
 */
@Controller("ParkPriceExt")
public class ParkPriceExtController extends AbstractController {
    @Autowired
    private ParkPriceExtService parkPriceExtService;
}