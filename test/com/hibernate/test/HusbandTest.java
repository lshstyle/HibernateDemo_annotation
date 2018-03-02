package com.hibernate.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class HusbandTest {

private static SessionFactory sessionFactory = null;
	
//	@BeforeClass
//	public static void beforeTest() {
//		Configuration cfg = new AnnotationConfiguration();
//		sessionFactory = cfg.configure().buildSessionFactory();
//	}
	
	@Test
	public void schemaExportTest() {
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
	}
	
	
	
//	@AfterClass
//	public static void afterTest() {
//		sessionFactory.close();
//	}
//	
}
