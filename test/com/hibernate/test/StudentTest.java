package com.hibernate.test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import com.hibernate.demo.model.Sex;
import com.hibernate.demo.model.Student;

public class StudentTest {
	private static SessionFactory sessionFactory = null;
	
	@BeforeClass
	public static void beforeTest() {
		Configuration cfg = new AnnotationConfiguration();
		sessionFactory = cfg.configure().buildSessionFactory();
	}
	
	@Test
	public void addStudentTest() {
		Student s = new Student();
		s.setName("s4");
		s.setAge(4);
		s.setSex(Sex.BOY);
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(s);
		session.getTransaction().commit();
		session.close();
	}
	
	@AfterClass
	public static void afterTest() {
		sessionFactory.close();
	}
}
