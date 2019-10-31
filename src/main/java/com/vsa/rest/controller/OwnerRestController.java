package com.vsa.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vsa.dao.service.OwnerService;
import com.vsa.rest.model.Owner;
import com.vsa.rest.model.Response;
import com.vsa.rest.util.Constants;

/**
 * Implentattion of Owner Rest Controller
 * 
 * @author Hanumant
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = {
		RequestMethod.GET })
@RequestMapping("/owner")
public class OwnerRestController {
	@Autowired
	private OwnerService ownerService;

	@GetMapping("/owners")
	public ResponseEntity<Response> getAllOwners() {
		List<Owner> owners = ownerService.getAllOwner();
		Response response = new Response();
		response.setResult(owners);
		response.setType(Constants.SUCCESS);
		return ResponseEntity.ok(response);
	}
}
