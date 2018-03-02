package com.hibernate.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hibernate.demo.model.Teacher;

public class TeacherTest {
private static SessionFactory sessionFactory = null;
	
	@BeforeClass
	public static void beforeTest() {
		Configuration cfg = new AnnotationConfiguration();
		sessionFactory = cfg.configure().buildSessionFactory();
	}
	
	@Test
	public void addTeacherTest() {
		Teacher s = new Teacher();
		s.setId(1);
		s.setName("teacher1");
		s.setPosition("³õ¼¶");
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(s);
		session.getTransaction().commit();
		System.out.println(s.getId());
		session.close();
		
	}
	
	@Test
	public void updateTeacherTest() {
		Session session1 = sessionFactory.openSession();
		session1.beginTransaction();
		Teacher s = (Teacher)session1.get(Teacher.class, 1);
		s.setPosition("³õ¼¶2");
		session1.getTransaction().commit();
		session1.close();
	}
	
	@AfterClass
	public static void afterTest() {
		sessionFactory.close();
	}
}
