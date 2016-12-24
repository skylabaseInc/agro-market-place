package com.skylabase.rest.controllers;

import com.skylabase.model.Farm;
import com.skylabase.service.FarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Farms Rest Controller")
@RestController
@RequestMapping(value = "/api/v1")
public class FarmsRestController {

    @Autowired
    private FarmService farmService;

    @RequestMapping(value = "/users/{userId}/farms", method = RequestMethod.POST)
    @ApiOperation(value = "Create a new Farm, belonging to an existing User.")
    public ResponseEntity<Farm> createFarm(@PathVariable(value = "userId") long userId,
                                           @RequestBody Farm farm) {
        farm = farmService.create(farm);
        return new ResponseEntity<>(farm, HttpStatus.CREATED);
    }

    @RequestMapping(value = "users/{userId}/farms/{farmId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get Farm by farmId.", notes = "Returns a Farm.")
    public ResponseEntity<Farm> getFarm(@PathVariable(value = "userId") long userId,
                                        @PathVariable(value = "farmId") long farmId) {
        final Farm farm = farmService.findById(farmId);
        return new ResponseEntity<>(farm, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/farms", method = RequestMethod.GET)
    @ApiOperation(value = "Get a list of Farms belonging to the User with given userId.",
            notes = "Can specify pageSize and pageNumber")
    public ResponseEntity<Page<Farm>> getFarmsByOwner(@PathVariable(value = "userId") long userId,
                                                      @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                                      @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        Page<Farm> farms = farmService.findByOwnerId(userId, new PageRequest(pgNum, pgSize));
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @RequestMapping(value = "/farms", method = RequestMethod.GET)
    @ApiOperation(value = "Get all Farms, and optionally a list of Farms whose names match the given name.",
            notes = "Can specify pageSize and pageNumber")
    public ResponseEntity<Page<Farm>> getFarms(@RequestParam(value = "name", defaultValue = "", required = false) String name,
                                               @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                               @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        Page<Farm> farms;
        if (!name.isEmpty()) {
            farms = farmService.findByNameLike(name, new PageRequest(pgNum, pgSize));
        } else {
            farms = farmService.findAll(new PageRequest(pgNum, pgSize));
        }
        return new ResponseEntity<>(farms, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/farms/{farmId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update an existing farm", notes = "Returns the updated farm.")
    public ResponseEntity<Farm> updateFarm(@PathVariable("farmId") long farmId,
                                           @PathVariable("userId") long userId,
                                           @RequestBody Farm updatedFarm) {
        updatedFarm = farmService.update(updatedFarm);
        return new ResponseEntity<>(updatedFarm, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users/{userId}/farms/{farmId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete the farm with the given farmId.",
            notes = "Must specify both the owner's userId and the farmId.")
    public ResponseEntity<Farm> deleteFarm(@PathVariable("farmId") long farmId,
                                           @PathVariable("userId") long userId) {
        farmService.delete(farmId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
