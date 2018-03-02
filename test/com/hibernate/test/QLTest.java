package com.hibernate.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import com.hibernate.demo.model.Category;
import com.hibernate.demo.model.Message;
import com.hibernate.demo.model.Topic;

public class QLTest {

private static SessionFactory sf;
	
	@Test
	public void testSchemaExport() {
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
	}
	
	@Test
	public void testSave() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		for(int i=0; i<10; i++) {
			Category c = new Category();
			c.setName("c" + i);
			session.save(c);
		}
		
		for(int i=0; i<10; i++) {
			Category c = new Category();
			c.setId(1);
			Topic t = new Topic();
			t.setCategory(c);
			t.setTitle("t" + i);
			session.save(t);
			
		}
		
		for(int i=0; i<10; i++) {
			
			Topic t = new Topic();
			t.setId(1);
			Message m = new Message();
			m.setContent("m" + i);
			m.setTopic(t);
			session.save(m);
			
		}

		session.getTransaction().commit();
		session.close();
	}
	@Test
	public void testHQL_01() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category");
		List<Category> categories = (List<Category>)q.list();
		for(Category c : categories) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void teshHQL_02() {
		sf = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c where c.id > :min and c.id < :max")
				.setParameter("min", 2)
				.setParameter("max", 8);
		List<Category> categories = (List<Category>)q.list();
		for(Category c : categories) {
			System.out.println(c.getName());
		}
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void testHQL_07() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Category c order by c.name desc");
		q.setMaxResults(4);
		q.setFirstResult(2);
		List<Category> categories = (List<Category>)q.list();
		for(Category c : categories) {
			System.out.println(c.getId() + "-" + c.getName());
		}
		session.getTransaction().commit();
		session.close();
		
	}
}
