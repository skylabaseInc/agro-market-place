package com.skylabase.rest.controllers;

import com.skylabase.model.City;
import com.skylabase.model.Country;
import com.skylabase.service.CountryService;
import io.swagger.annotations.Api;
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

import javax.naming.OperationNotSupportedException;
import java.util.List;

@RestController
@Api("Country")
@RequestMapping(CountryRestController.COUNTRY_REQUEST_MAPPING)
public class CountryRestController {

    @Autowired
    private CountryService countryService;

    public static final String COUNTRY_REQUEST_MAPPING = "/api/v1/countries";

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getAllCountries() {
        final List<Country> countries = countryService.findAll();
        return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Country> getCountry(@PathVariable("id") Long id) {
        final Country country = countryService.findById(id);
        if (country == null) {
            return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Country>(country, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        if (countryService.exists(country)) {
            return new ResponseEntity<Country>(HttpStatus.CONFLICT);
        }
        final Country savedCountry = countryService.create(country);
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
        return new ResponseEntity<Country>(savedCountry, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Country> updateCountry() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Update on country not yet supported.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Country> deleteCountry(@PathVariable("id") Long id) {
        final Country country = countryService.findById(id);
        if (country == null) {
            return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
        }
        countryService.delete(country);
        return new ResponseEntity<Country>(HttpStatus.OK);
    }

    @RequestMapping(value = "{countryId}/cities", method = RequestMethod.GET)
    public ResponseEntity<List<City>> getCities(@PathVariable("id") Long countryId) {
        final Country country = countryService.findById(countryId);
        if (country == null) {
            return new ResponseEntity<List<City>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<City>>(country.getCities(), HttpStatus.OK);
    }

    @RequestMapping(value = "{countryId}/cities", method = RequestMethod.POST)
    public ResponseEntity<Country> addCity(@PathVariable("id") Long countryId, @RequestBody City city) {
        final Country country = countryService.findById(countryId);
        if (country == null) {
            return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
        }
        country.addCity(city);
        countryService.update(country);
        return new ResponseEntity<Country>(country, HttpStatus.OK);
    }
}
