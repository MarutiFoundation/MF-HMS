package com.mfp.api.controller;


import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/receptionist")
public class ReceptionistController {
	
	private static Logger LOG = LogManager.getLogger(ReceptionistController.class);

}
