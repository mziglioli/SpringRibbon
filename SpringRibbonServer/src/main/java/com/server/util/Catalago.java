package com.server.util;

public class Catalago {

	// server configuration
	public static final String SERVER = "http://localhost";

	// DB configuration
	public static final String DB_NAME = "test";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "marcelo12";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true";

	// hibernate confi
	public static final String DB_DDL = "hibernate.hbm2ddl.auto";
	public static final String DB_DDL_VALUE = "update";
	public static final String DB_DIALECT = "hibernate.dialect";
	public static final String DB_DIALECT_VALUE = "org.hibernate.dialect.MySQL5Dialect";
	public static final String DB_SHOW_SQL = "show_sql";
	public static final String DB_SHOW_SQL_VALUE = "true";

	// ROLES
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String HAS_ROLE_USER = "hasRole('ROLE_USER')";

	// URL main pages
	public static final String URL_BASE = "/";
	public static final String URL_ID = "/{id}";

	public static final String URL_USER = "/user";

}
