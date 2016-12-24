package com.skylabase.service;

import com.skylabase.Authorized;
import com.skylabase.model.Farmer;
import com.skylabase.model.PrivilegeConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Farmer specific Service api.
 * 
 * @author ivange
 */
public interface FarmerService {

    /**
     * Find a farmer by Id.
     *
     * @param id the id of the farmer to get
     * @return the farmer object if found else return null
     */
    @Authorized(PrivilegeConstants.GET_USERS)
    public Farmer findById(long id);

    /**
     * Find a farmer by farmername.
     *
     * @param farmername the farmername of the farmer to find
     * @param pageable
     * @return the farmer if found else null
     */
    @Authorized(PrivilegeConstants.GET_USERS)
    public Page<Farmer> findByUsername(String username, Pageable pageable);

    /**
     * Get a list of all farmers from the system.
     *
     * @return list containing all farmers in the system
     * @param pageable
     */
    @Authorized(PrivilegeConstants.GET_USERS)
    public Page<Farmer> findAll(Pageable pageable);

    /**
     * Creates a new farmer in the system.
     *
     * @param farmer the farmer to create
     * @return the farmer created
     */
    @Authorized(PrivilegeConstants.CREATE_USERS)
    public Farmer create(Farmer farmer);

    /**
     * Updates and existing farmer.
     *
     * @param farmer to be updated
     * @return the updated farmer
     */
    @Authorized(PrivilegeConstants.EDIT_USERS)
    public Farmer update(Farmer farmer);

    /**
     * Deletes a farmer from the system.
     *
     * @param farmerId the farmer to be deleted
     */
    @Authorized(PrivilegeConstants.DELETE_USERS)
    public void delete(long farmerId);

    /**
     * Checks if given farmer exists.
     *
     * @param farmerId the farmer been checked for existence
     * @return true if farmer exist else false
     */
    @Authorized(PrivilegeConstants.VIEW_USERS)
    public boolean exists(long farmerId);
}
