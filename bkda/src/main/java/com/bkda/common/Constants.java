package com.bkda.common;

public final class Constants {
	public static final String DBTYPE_MYSQL = "MySQL";
    public static final String DBTYPE_POSTGRES = "Postgres";
    public static final String DBTYPE_H2 = "H2";
    
    public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    
    // definition of error codes
    public static final int ERROR_NOT_IMPLEMENTED = 1;
    
    public static final int ERROR_LOGIN_FAILED = 1001;
    public static final int ERROR_UNAUTHORIZED = 1002;
    public static final int ERROR_GRANT_TYPE_INVALID = 1003;
    public static final int ERROR_USERNAME_MISSING = 1004;
    public static final int ERROR_PASSWORD_MISSING = 1005;
    public static final int ERROR_PASSWORD_STRENGTH = 1006;
}

