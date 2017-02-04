package com.skylabase.agromarketplace.rest.controllers;

import com.skylabase.agromarketplace.model.Role;
import com.skylabase.agromarketplace.service.RoleService;
import com.skylabase.agromarketplace.model.Privilege;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.Collection;
import java.util.List;

/**
 * Controller that handles requests to {@code Role} objects.
 */
@RestController
@Api("Roles")
@RequestMapping(RoleRestController.ROLE_REQUEST_MAPPING)
public class RoleRestController {

    public static final String ROLE_REQUEST_MAPPING = "/api/v1/roles";

    @Autowired
    private RoleService roleService;

    /**
     * Get a list of roles from the system.
     *
     * @return ResponseEntity containing a list of all roles in the system.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Get roles", notes = "Returns a list of all roles in the system.")
    public ResponseEntity<Page<Role>> getRoles(Pageable pageable) {
        final Page<Role> roles = roleService.listAllByPage(pageable);
        if (roles == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    /**
     * Get a role with a particular id.
     *
     * @return ResponseEntity containing the role if found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get roles", notes = "Returns a list of all roles in the system.")
    public ResponseEntity<Role> getRole(@PathVariable("id") Role role) {
        if (role == null) {
            return new ResponseEntity<>(role, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    /**
     * Create a new Role.
     *
     * @param role the role to create
     * @return ResponseEntity containing the created role
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a new Role.", notes = "Create a new Role and return the newly created Role.")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        if (roleService.exists(role)) {
            return new ResponseEntity<Role>(HttpStatus.CONFLICT);
        }
        final Role savedRole = roleService.create(role);
        final HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/").buildAndExpand("").toUri());
        return new ResponseEntity<Role>(savedRole, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Role> updateRole() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Update not supported on this resource.");
    }

    /**
     * Delete a role with a particular Id.
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a Role from the system", notes = "Delete a Role from the system.")
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Role role) {
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleService.delete(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
