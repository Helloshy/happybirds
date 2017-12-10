package com.airparking.apms.api.parkPrice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.airparking.apms.api.parkPrice.service.StepPriceService;
import com.airparking.apms.server.handler.AbstractController;
/**
 * Created by ryan on 2016-07-28.
 */
@Controller("StepPrice")
public class StepPriceController extends AbstractController {
    @Autowired
    private StepPriceService stepPriceService;
}