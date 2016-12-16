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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/farmers")
@Api(value = "Farmer")
public class FarmerRestController {

	@Autowired
	private FarmerService farmerService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value = "Get farmers", notes = "Returns a list of all farmers.")
	public ResponseEntity<List<Farmer>> getFarmers() {
		final List<Farmer> farmers = farmerService.findAll();

		return new ResponseEntity<List<Farmer>>(farmers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ApiOperation(value = "Get a farmer", notes = "Returns a single farmer.")
	public ResponseEntity<Farmer> getFarmer(@PathVariable("id") Long id) {
		final Farmer farmer = farmerService.findById(id);
		if (farmer == null) {
			return new ResponseEntity<Farmer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Farmer>(farmer, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Create a new farmer", notes = "Returns the created farmer.")
	public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
		if (farmerService.exists(farmer)) {
			return new ResponseEntity<Farmer>(HttpStatus.CONFLICT);
		}
		final Farmer created = farmerService.create(farmer);
		return new ResponseEntity<Farmer>(created, HttpStatus.CREATED);
	}
	/*
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ApiOperation(value = "Update farmer", notes = "Returns the updated farmer.")
	public ResponseEntity<Farmer> updateFarmer(@PathVariable("id") String id, @RequestBody Farmer farmer) {
		final Farmer existing = farmerService.findById(id);
		if (existing == null) {
			return new ResponseEntity<Farmer>(HttpStatus.NOT_FOUND);
		}
		existing.setUsername(farmer.getUsername());
		existing.setPhoneNumber(farmer.getPhoneNumber());
		existing.setFarms(farmer.getFarms());
		existing.setEmail(farmer.getEmail());
		existing.setLocationId(farmer.getLocationId());
		
		farmerService.update(existing);
		return new ResponseEntity<Farmer>(farmer, HttpStatus.OK);
	}
	*/
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete farmer.")
	public ResponseEntity<Farmer> deleteFarmer(@PathVariable("id") Long id) {
		final Farmer farmer = farmerService.findById(id);
		if (farmer == null) {
			return new ResponseEntity<Farmer>(HttpStatus.NOT_FOUND);
		}
		farmerService.delete(farmer);
		return new ResponseEntity<Farmer>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete all farmers.")
	public ResponseEntity<Farmer> deleteAllFarmers() {
		farmerService.deleteAll();
		return new ResponseEntity<Farmer>(HttpStatus.NO_CONTENT);
	}
}
