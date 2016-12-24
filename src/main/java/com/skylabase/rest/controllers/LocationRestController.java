package com.skylabase.rest.controllers;

import java.util.List;

import com.skylabase.model.Address;
import com.skylabase.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/addresses")
@Api(value="Address")
public class LocationRestController {

	@Autowired
	private AddressService addressService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value = "Get all locations", notes = "Returns a list of all addresses.")
	public ResponseEntity<List<Address>> getLocations() {
		final List<Address> locations = addressService.findAll();
		if (locations == null) {
			return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Address>>(locations, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ApiOperation(value = "Get location with given id", notes = "Returns address with given id")
	public ResponseEntity<Address> getLocation(@PathVariable("id") Long id) {
		final Address location = addressService.findById(id);
		if (location == null) {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Address>(location, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Create a location", notes = "Returns created address")
	public ResponseEntity<Address> createLocation(@RequestBody Address location) {
		final Address created = addressService.create(location);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
		return new ResponseEntity<Address>(created, HttpStatus.CREATED);
	}
	/*
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ApiOperation(value = "Update an existing location", notes = "Returns the updated location.")
	public ResponseEntity<Address> updateLocation(@PathVariable("id") String id, @RequestBody Address location) {
		final Address existing = locationService.findById(id);
		if (existing == null) {
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		}
		existing.setCity(location.getCity());
		existing.setState(location.getState());
		existing.setStreet(location.getStreet());
		locationService.update(existing);
		return new ResponseEntity<Address>(existing, HttpStatus.OK);
	}
	*/
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete a address")
	public ResponseEntity<Void> deleteLocation(@PathVariable("id") Long id) {
		final Address location = addressService.findById(id);
		if (location == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		addressService.delete(location);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
