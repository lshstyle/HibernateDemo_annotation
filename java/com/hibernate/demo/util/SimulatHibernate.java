package com.hibernate.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.hibernate.demo.model.Student;


public class SimulatHibernate {

	private static final String url = "jdbc:mysql://192.168.9.123:3306/ecar?";
	private static final String username = "roadparking";
	private static final String pwd = "road123456%2017";
	private static final String tableName = "student";
	
	public static Map<String, String> fieldMap = new HashMap<String, String>();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		executeSql();
	}
	
	public static void executeSql() throws Exception {
		int index = 1;
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, username, pwd);
		Student student = new Student();
		student.setAge(3);
		student.setId(3);
		student.setName("s3");
		String sql = createInsertSql(student);
		PreparedStatement ps = conn.prepareStatement(sql);
		Set<String> methods = fieldMap.keySet();
		for (String method : methods) {
			String methodStr = fieldMap.get(method);
			Method m = student.getClass().getMethod(methodStr);
			Class clazz = m.getReturnType();
			System.out.println(clazz);
			if ("java.lang.String".equals(clazz.getName())) {
				ps.setString(index++,m.invoke(student).toString());
			}
			if ("int".equals(clazz.getName())) {
				ps.setInt(index++, Integer.valueOf(m.invoke(student).toString()));
			}
		}
		ps.executeUpdate();
	}
	
	public static String createInsertSql(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String sql = "insert into " + tableName ;
		String cols = "";
		String values = "";
		for (Field f : fields) {
			System.out.println(f.getName());
			String fieldName = f.getName();
			String methodName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1); 
			System.out.println(methodName);
			fieldMap.put(fieldName, methodName);
		}
		Set<String> columns = fieldMap.keySet();
		for (String s : columns) {
			cols += "," + s;
			values += "," + "?";
		}
		cols = cols.replaceFirst(",", "");
		values = values.replaceFirst(",", "");
		sql += "(" + cols + ") values " + "(" + values + ")";
		System.out.println(sql);
		return sql;
	}

}
