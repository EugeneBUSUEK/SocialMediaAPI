package com.test.socialmedia.support.helper;

import java.util.Date;

public class DateHelper {
    public static Date getCurrentUtilDate() {
        return new Date(System.currentTimeMillis());
    }

    public static java.sql.Date getCurrentSQLDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }
}
