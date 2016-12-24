package com.skylabase.rest.controllers;

import com.skylabase.exceptions.ItemNotFoundException;
import com.skylabase.model.Farmer;
import com.skylabase.service.FarmerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * RestController that handles request for {@link Farmer} objects
 *
 * @see Farmer
 * @see FarmerService
 */
@Api(value = "Farmers Rest Controller")
@RestController
@RequestMapping(value = "/api/v1/farmers")
public class FarmersRestController {

    @Autowired
    private FarmerService farmerService;

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new Farmer.", notes = "Returns the created Farmer.")
    public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
        farmer = farmerService.create(farmer);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/")
                .buildAndExpand("")
                .toUri());
        return new ResponseEntity<>(farmer, headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all Farmers or search Farmers by farmerName.",
            notes = "Partition results into pages of the given size and return the given page number.")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Farmer>> getFarmers(@RequestParam(value = "name", defaultValue = "", required = false) String name,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                                   @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        Page<Farmer> farmers;
        if (!name.isEmpty()) {
            farmers = farmerService.findByUsername(name, new PageRequest(pgNum, pgSize));
        } else {
            farmers = farmerService.findAll(new PageRequest(pgNum, pgSize));
        }
        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{farmerId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get the farmer with the given farmerId")
    public ResponseEntity<Farmer> getFarmer(@PathVariable(value = "farmerId") long farmerId) {
        Farmer farmer = farmerService.findById(farmerId);
        return new ResponseEntity<>(farmer, HttpStatus.OK);
    }

    @RequestMapping(value = "/{farmerId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update the existing farmer with given farmerId")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable(value = "farmerId") long farmerId,
                                               @RequestBody Farmer farmer) {
        if (!farmerService.exists(farmerId)) {
            throw new ItemNotFoundException("no Farmer with such an id");
        }
        farmer = farmerService.update(farmer);
        return new ResponseEntity<>(farmer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{farmerId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete the Farmer with given farmerId")
    public ResponseEntity<Farmer> deleteFarmer(@PathVariable("farmerId") long farmerId) {
        farmerService.delete(farmerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
