package com.skylabase.agromarketplace.rest.controllers;

import com.skylabase.agromarketplace.model.Role;
import com.skylabase.agromarketplace.service.RoleService;
import com.skylabase.agromarketplace.model.Privilege;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public ResponseEntity<List<Role>> getRoles() {
        final List<Role> roles = roleService.findAll();

        return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
    }

    /**
     * Get a role with a particular id.
     *
     * @param id the id of the role to get
     * @return ResponseEntity containing the role if found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get roles", notes = "Returns a list of all roles in the system.")
    public ResponseEntity<Role> getRole(@PathVariable("id") Long id) {
        final Role role = roleService.findById(id);
        if (role == null) {
            return new ResponseEntity<Role>(role, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Role>(role, HttpStatus.OK);
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
     * @param id the id of the Role to delete
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a Role from the system", notes = "Delete a Role from the system.")
    public ResponseEntity<Role> deleteRole(@PathVariable("id") Long id) {
        final Role role = roleService.findById(id);
        if (role == null) {
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
        roleService.delete(role);
        return new ResponseEntity<Role>(HttpStatus.OK);
    }

    /**
     * Get all privileges for this Role.
     *
     * @param roleId the role to get privileges
     * @return Privileges
     */
    @RequestMapping(value = "/{roleId}/privileges", method = RequestMethod.GET)
    @ApiOperation(value = "Get all privileges from Role", notes = "Get all privileges from Role")
    public ResponseEntity<Collection<Privilege>> getAllRolePrivileges(@PathVariable("id") Long roleId) {
        final Role role = roleService.findById(roleId);
        if (role == null) {
            return new ResponseEntity<Collection<Privilege>>(HttpStatus.NOT_FOUND);
        }
        final Collection<Privilege> privileges = role.getPrivileges();
        return new ResponseEntity<Collection<Privilege>>(privileges, HttpStatus.OK);
    }

    /**
     * Get a privilege from a particular Role.
     *
     * @param roleId the role to get privilege from
     * @param privilegeId the privilege to get
     * @return
     */
    @RequestMapping(value = "/{roleId}/privileges/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get a privilege with a particular id", notes = "Get a privilege with a particular id")
    public ResponseEntity<Privilege> getPrivilegeFromRole(@PathVariable("roleId") Long roleId, @PathVariable("id") Long privilegeId) {
        final Role role = roleService.findById(roleId);
        if (role == null) {
            return new ResponseEntity<Privilege>(HttpStatus.NOT_FOUND);
        }
        for (Privilege privilege: role.getPrivileges()) {
            if (privilege.getId().equals(privilegeId)) {
                return new ResponseEntity<Privilege>(privilege, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Privilege>(HttpStatus.NOT_FOUND);
    }

    /**
     * Add a privilege to a role.
     *
     * @param roleId the role to add new privilege
     * @param privilege the privilege to add
     * @return the added Privilege
     */
    @RequestMapping(value = "/{roleId}/privileges/", method = RequestMethod.POST)
    @ApiOperation(value = "Add a new privilege", notes = "Add a new privilege to a role")
    public ResponseEntity<Role> addPrivilege(@PathVariable("roleId") Long roleId, @RequestBody Privilege privilege) {
        final Role role = roleService.findById(roleId);
        if (role == null) {
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
        role.addPrivilege(privilege);
        roleService.update(role);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    /**
     * Remove a privilege from a role.
     *
     * @param roleId the role to remove privilege from
     * @param privilegeId the privilege to remove
     * @return the updated role.
     */
    @RequestMapping(value = "/{roleId}/privileges/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Remove a privilege from a Role", notes = "Remove an existing privilege from a particular role")
    public ResponseEntity<Role> removePrivilege(@PathVariable("roleId") Long roleId, @PathVariable("id") Long privilegeId) {
        final Role role = roleService.findById(roleId);
        if (role == null) {
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
        final Privilege privilege = getPrivilege(role, privilegeId);
        if (privilege == null) {
            return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
        }
        role.removePrivilege(privilege);
        roleService.update(role);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    private Privilege getPrivilege(Role role, Long id) {
        for (Privilege privilege: role.getPrivileges()) {
            if (privilege.getId().equals(id))
                return privilege;
        }
        return null;
    }
}
