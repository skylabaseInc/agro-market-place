package com.skylabase.rest.controllers;

import com.skylabase.model.City;
import com.skylabase.model.Country;
import com.skylabase.service.CountryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.naming.OperationNotSupportedException;
import java.util.List;

@RestController
@Api("Country")
@RequestMapping(CountriesRestController.COUNTRY_REQUEST_MAPPING)
public class CountriesRestController {

    @Autowired
    private CountryService countryService;

    public static final String COUNTRY_REQUEST_MAPPING = "/api/v1/countries";

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        final Country savedCountry = countryService.create(country);
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
        return new ResponseEntity<>(savedCountry, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Country>> getAllCountries(@RequestParam(value = "name", defaultValue = "", required = false) String name,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") String pageSize,
                                                         @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber) {
        int pgSize = Integer.valueOf(pageSize);
        int pgNum = Integer.valueOf(pageNumber);
        final Page<Country> countries = countryService.findAll(new PageRequest(pgNum, pgSize));
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Country> getCountry(@PathVariable("id") Long id) {
        final Country country = countryService.findById(id);
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Country> updateCountry() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Update on country not yet supported.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") Long id) {
        final Country country = countryService.findById(id);
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{countryId}/cities", method = RequestMethod.GET)
    public ResponseEntity<List<City>> getCities(@PathVariable("id") Long countryId) {
        final Country country = countryService.findById(countryId);
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(country.getCities(), HttpStatus.OK);
    }

    @RequestMapping(value = "{countryId}/cities", method = RequestMethod.POST)
    public ResponseEntity<Country> addCity(@PathVariable("id") Long countryId, @RequestBody City city) {
        final Country country = countryService.findById(countryId);
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        country.addCity(city);
        countryService.update(country);
        return new ResponseEntity<>(country, HttpStatus.OK);
    }
}
