package com.skylabase.agromarketplace.model;

/**
 * Container class of all system privileges.
 */
public final class PrivilegeConstants {

    private PrivilegeConstants() {
        // private constructor since class is not
        // meant to be instantiated
    }

    /**
     * Any user with this privilege will be able to create users.
     */
    public static final String CREATE_USERS = "Create users";

    /**
     * Any user with this privilege will be able view existing users.
     */
    public static final String VIEW_USERS = "View users";

    /**
     * Any user with this privilege will be able to query users.
     */
    public static final String GET_USERS = "Get users";

    /**
     * Any user with this privilege will be able to edit existing users.
     */
    public static final String EDIT_USERS = "Edit users";

    /**
     * Any user with this privilege will be able to delete existing users.
     */
    public static final String DELETE_USERS = "Delete users";
}
