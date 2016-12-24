package com.skylabase.rest.controllers;

import com.skylabase.model.Address;
import com.skylabase.service.AddressService;
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

@RestController
@RequestMapping("/addresses")
@Api(value = "Address")
public class AddressesRestController {

    @Autowired
    private AddressService addressService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get all locations", notes = "Returns a list of all addresses.")
    public ResponseEntity<Page<Address>> getAddresses(@RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                                      @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        final Page<Address> locations = addressService.findAll(new PageRequest(pgNum, pgSize));
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get location with given addressId", notes = "Returns address with given addressId")
    public ResponseEntity<Address> getAddress(@PathVariable("addressId") long addressId) {
        final Address address = addressService.findById(addressId);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create an address", notes = "Returns created address")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        address = addressService.create(address);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update an existing address", notes = "Returns the updated address.")
    public ResponseEntity<Address> updateAddress(@PathVariable("addressId") String addressId, @RequestBody Address address) {
        address = addressService.update(address);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete an address")
    public ResponseEntity<Void> deleteAddress(@PathVariable("addressId") Long addressId) {
        addressService.delete(addressId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
