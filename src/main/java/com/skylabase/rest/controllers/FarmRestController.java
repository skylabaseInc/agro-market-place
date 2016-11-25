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

import com.skylabase.model.Farm;
import com.skylabase.service.FarmService;

@RestController
@RequestMapping("/farms")
public class FarmRestController {
	
	@Autowired
	private FarmService farmService;
	
	/**
	 * Get a list of all farms in the system.
	 * 
	 * @return the list of farms
	 * 
	 * @see FarmService#findAll()
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Farm>> getFarms() {
		List<Farm> farms = farmService.findAll();

		if (farms.isEmpty()) {
			return new ResponseEntity<List<Farm>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Farm>>(farms, HttpStatus.OK);
	}

	/**
	 * Get Farm with given farm id.
	 * 
	 * @param id
	 *            the id of the farm to return
	 * @return the farm of given id
	 * 
	 * @see FarmService#findById(String)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Farm> getFarm(@PathVariable("id") String id) {
		Farm farm = farmService.findById(id);
		if (farm == null) {
			return new ResponseEntity<Farm>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Farm>(farm, HttpStatus.OK);
	}

	/**
	 * Create a new farm in the system.
	 * 
	 * @param farm
	 *            the farm to be created
	 * @return an HttpStatus.CREATED if farm was successfully created
	 * 
	 * @see FarmService#create(Farm)
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
		if (farmService.exists(farm)) {
			return new ResponseEntity<Farm>(HttpStatus.CONFLICT);
		}

		Farm created = farmService.create(farm);
		HttpHeaders headers = new HttpHeaders();
		 headers.setLocation(ServletUriComponentsBuilder
			      .fromCurrentRequest().path("/").buildAndExpand("").toUri());
		return new ResponseEntity<Farm>(created, headers, HttpStatus.CREATED);
	}

	/**
	 * Updates an existing farm.
	 * 
	 * @param id
	 *            the id of the farm been updated
	 * @param updated
	 *            an updated instance to persist
	 * @return the updated farm or an HttpStatus.NOT_FOUND if the farm been
	 *         updated does not exist
	 * 
	 * @see FarmService#update(Farm)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Farm> updateFarm(@PathVariable("id") String id, @RequestBody Farm updated) {
		Farm currentFarm = farmService.findById(id);

		if (currentFarm == null) {
			return new ResponseEntity<Farm>(HttpStatus.NOT_FOUND);
		}

		currentFarm.setName(updated.getName());
		currentFarm.setDescription(updated.getDescription());
		currentFarm.setLocationId(updated.getLocationId());
		currentFarm.setOwnerId(updated.getOwnerId());
		currentFarm.setProducts(updated.getProducts());
		
		return new ResponseEntity<Farm>(currentFarm, HttpStatus.OK);
	}

	/**
	 * Deletes a farm from the system.
	 * 
	 * @param id
	 *            the id of the farm been deleted
	 * @return if farm was not found an HttpStatus.NOT_FOUND is returned else an
	 *         HttpStatus.NO_CONTENT is returned
	 * 
	 * @see FarmService#delete(Farm)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Farm> deleteFarm(@PathVariable("id") String id) {
		Farm farm = farmService.findById(id);
		if (farm == null) {
			return new ResponseEntity<Farm>(HttpStatus.NOT_FOUND);
		}

		farmService.delete(farm);
		return new ResponseEntity<Farm>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Deletes all farms from the system
	 * 
	 * @return an HttpStatus.NO_CONTENT
	 * 
	 * @see FarmService#deleteAll()
	 */
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Farm> deleteAllFarms() {
		farmService.deleteAll();
		return new ResponseEntity<Farm>(HttpStatus.NO_CONTENT);
	}

}
