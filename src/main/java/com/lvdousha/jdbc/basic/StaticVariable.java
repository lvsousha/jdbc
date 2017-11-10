package com.lvdousha.jdbc.basic;

public class StaticVariable {

	public final static String HIVE_CLASS_NAME = "org.apache.hive.jdbc.HiveDriver";

	public final static String HIVE_URL = "jdbc:hive2://10.194.9.61:10001/default";
	
	public final static String MYSQL_CLASS_NAME = "com.mysql.jdbc.Driver";

	public final static String MYSQL_URL = "jdbc:mysql://localhost:3306/lvdousha";
	
	public final static String SQLSERVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public final static String SQLSERVER_URL = "jdbc:sqlserver://localhost:1433;databaseName=second";
	
	public final static String ORACLE_CLASS_NAME = "oracle.jdbc.OracleDriver";

	public final static String ORACLE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	
	public final static String GP_CLASS_NAME = "com.pivotal.jdbc.GreenplumDriver";
	
	public final static String GP_URL = "jdbc:pivotal:greenplum://192.168.1.121:5432;DatabaseName=gpadmin";
	
	
}
