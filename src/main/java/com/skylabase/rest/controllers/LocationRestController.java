package com.skylabase.rest.controllers;

import java.util.List;

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

import com.skylabase.model.Location;
import com.skylabase.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/locations")
@Api(value="Location")
public class LocationRestController {

	@Autowired
	private LocationService locationService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ApiOperation(value = "Get all locations", notes = "Returns a list of all locations.")
	public ResponseEntity<List<Location>> getLocations() {
		final List<Location> locations = locationService.findAll();
		if (locations == null) {
			return new ResponseEntity<List<Location>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ApiOperation(value = "Get location with given id", notes = "Returns location with given id")
	public ResponseEntity<Location> getLocation(@PathVariable("id") String id) {
		final Location location = locationService.findById(id);
		if (location == null) {
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Location>(location, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ApiOperation(value = "Create a location", notes = "Returns created location")
	public ResponseEntity<Location> createLocation(@RequestBody Location location) {
		final Location created = locationService.create(location);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
		return new ResponseEntity<Location>(created, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ApiOperation(value = "Update an existing location", notes = "Returns the updated location.")
	public ResponseEntity<Location> updateLocation(@PathVariable("id") String id, @RequestBody Location location) {
		final Location existing = locationService.findById(id);
		if (existing == null) {
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		}
		existing.setCity(location.getCity());
		existing.setState(location.getState());
		existing.setStreet(location.getStreet());
		locationService.update(existing);
		return new ResponseEntity<Location>(existing, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete a location")
	public ResponseEntity<Void> deleteLocation(@PathVariable("id") String id) {
		final Location location = locationService.findById(id);
		if (location == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		locationService.delete(location);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ApiOperation(value = "Delete all locations")
	public ResponseEntity<Void> deleteAllLocations() {
		locationService.deleteAll();
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
