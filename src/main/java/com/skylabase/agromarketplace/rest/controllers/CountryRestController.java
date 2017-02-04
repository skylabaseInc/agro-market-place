package com.skylabase.agromarketplace.rest.controllers;

import com.skylabase.agromarketplace.service.CountryService;
import com.skylabase.agromarketplace.model.Country;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

@RestController
@Api("Country")
@RequestMapping(CountryRestController.COUNTRY_REQUEST_MAPPING)
public class CountryRestController {

    @Autowired
    private CountryService countryService;

    public static final String COUNTRY_REQUEST_MAPPING = "/api/v1/countries";

    /**
     * Get all countries from the system.
     *
     * @param pageable a pageable object that provides pagination information
     * @return paginated list of countries
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Country>> getAllCountries(Pageable pageable) {
       final Page<Country> countries = countryService.listAllByPage(pageable);
       if (countries == null) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Get a particular country
     *
     * @param country the country to get
     * @return country
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Country> getCountry(@PathVariable("id") Country country) {
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(country, HttpStatus.OK);
    }

    /**
     * Create a new country.
     *
     * @param country the country to create
     * @return the created country
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        final Country savedCountry = countryService.create(country);
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
        return new ResponseEntity<>(savedCountry, headers, HttpStatus.CREATED);
    }

    /**
     * Update an existing country
     * @return the country to be updated
     * @throws OperationNotSupportedException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Country> updateCountry() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Update on country not yet supported.");
    }

    /**
     * Delete a given country
     *
     * @param country the country to delete
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCountry(@PathVariable("id") Country country) {
        if (country == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryService.delete(country);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}