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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.skylabase.model.Farm;
import com.skylabase.service.FarmService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/farms")
@Api(value="Farm")
public class FarmRestController {

	@Autowired
	private FarmService farmService;

	/**
	 * Get Farm with given farm id.
	 * 
	 * @param id
	 *            the id of the farm to return
	 * @return the farm of given id
	 * 
	 * @see FarmService#findById(Long)
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get farm by id", notes = "Returns a farm")
	public ResponseEntity<Farm> getFarm(@PathVariable("id") Long id) {
		final Farm farm = farmService.findById(id);
		if (farm == null) {
			return new ResponseEntity<Farm>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Farm>(farm, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Search farms by owner", notes = "Returns a list of farms belonging to owner")
	public ResponseEntity<List<Farm>> getFarms(@RequestParam(value = "ownerId", required = false) Long ownerId) {
		final List<Farm> farms = farmService.findAll();
		return new ResponseEntity<List<Farm>>(farms, HttpStatus.OK);
	}

	/**
	 * Create a new farm in the system.
	 * 
	 * @param farm
	 *            the farm to be created
	 * @return an HttpStatus.CREATED if farm was successfully create
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create a new farm", notes = "Returns the created farm")
	public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
		if (farmService.exists(farm)) {
			return new ResponseEntity<Farm>(HttpStatus.CONFLICT);
		}

		Farm created = farmService.create(farm);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
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
	 *//*
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update an existing farm", notes = "Returns the updated farm.")
	public ResponseEntity<Farm> updateFarm(@PathVariable("id") Long id, @RequestBody Farm updated) {
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
    */
	/**
	 * Deletes a farm from the system.
	 * 
	 * @param id
	 *            the id of the farm been deleted
	 * @return if farm was not found an HttpStatus.NOT_FOUND is returned else an
	 *         HttpStatus.NO_CONTENT is returned
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete a farm")
	public ResponseEntity<Farm> deleteFarm(@PathVariable("id") Long id) {
		Farm farm = farmService.findById(id);
		if (farm == null) {
			return new ResponseEntity<Farm>(HttpStatus.NOT_FOUND);
		}

		farmService.delete(farm);
		return new ResponseEntity<Farm>(HttpStatus.NO_CONTENT);
	}
}
