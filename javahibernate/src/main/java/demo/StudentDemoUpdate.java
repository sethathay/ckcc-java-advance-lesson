package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import entity.Student;

public class StudentDemoUpdate {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			int studentId = 8;
			// start a transaction
			session.beginTransaction();
			// retrieve student based on the id: primary key
			System.out.println("\n\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("\n" + myStudent);
			
			System.out.println("\n\nUpdating student");
			myStudent.setFirstName("Puthnith");
			System.out.println("\n" + myStudent);
			
			session.createQuery("update Student set email='setha.thay@live.com' where id=11").executeUpdate();
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}finally {
			factory.close();
		}
	}
}
