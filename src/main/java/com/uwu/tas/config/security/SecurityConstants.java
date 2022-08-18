package com.uwu.tas.config.security;

public class SecurityConstants {

    // admin token details
    public static final String ADMIN_CLIENT_ID = "admin";
    protected static final int ADMIN_ACCESS_TOKEN_VALIDITY_SECONDS = 2 * 60 * 60;
    protected static final int ADMIN_REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 30;

    // public user token details
    public static final String PUBLIC_USER_CLIENT_ID = "public_user";
    protected static final int PUBLIC_USER_ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;
    protected static final int PUBLIC_USER_REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 30 * 12;

    // vendor token details
    public static final String VENDOR_CLIENT_ID = "vendor";
    protected static final int VENDOR_ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;
    protected static final int VENDOR_REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 30 * 12;

    protected static final String GRANT_TYPE_PASSWORD = "password";
    protected static final String AUTHORIZATION_CODE = "authorization_code";
    protected static final String REFRESH_TOKEN = "refresh_token";
    protected static final String IMPLICIT = "implicit";
    protected static final String SCOPE_READ = "read";
    protected static final String SCOPE_WRITE = "write";
    protected static final String TRUST = "trust";
    protected static final String TOKEN_SIGN_IN_KEY = "as466gf";
}
