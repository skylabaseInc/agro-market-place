package com.skylabase.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skylabase.model.Farmer;
import com.skylabase.service.FarmerService;

@RestController
@RequestMapping("/farmers")
public class FarmerRestController {

	@Autowired
	private FarmerService farmerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Farmer>> getFarmers() {
		final List<Farmer> farmers = farmerService.findAll();
		
		if (farmers.isEmpty()) {
			return new ResponseEntity<List<Farmer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Farmer>>(farmers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Farmer> getFarmer(@PathVariable("id") String id) {
		final Farmer farmer = farmerService.findById(id);
		if (farmer == null) {
			return new ResponseEntity<Farmer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Farmer>(farmer, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
		if (farmerService.exists(farmer)) {
			return new ResponseEntity<Farmer>(HttpStatus.CONFLICT);
		}
		final Farmer created = farmerService.create(farmer);
		return new ResponseEntity<Farmer>(created, HttpStatus.CREATED);
	}
	
//	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
//	public ResponseEntity<Farmer> updateFarmer(@PathVariable("id") String id, @RequestBody Farmer farmer) {
//		final Farmer existing = farmerService.findById(id);
//		if (existing == null) {
//			return new ResponseEntity<Farmer>(HttpStatus.NOT_FOUND);
//		}
//		
//	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Farmer> deleteUser(@PathVariable("id") String id) {
		final Farmer farmer = farmerService.findById(id);
		if (farmer == null) {
			return new ResponseEntity<Farmer>(HttpStatus.NOT_FOUND);
		}
		farmerService.delete(farmer);
		return new ResponseEntity<Farmer>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public ResponseEntity<Farmer> deleteAllUsers() {
		farmerService.deleteAll();
		return new ResponseEntity<Farmer>(HttpStatus.NO_CONTENT);
	}
}
