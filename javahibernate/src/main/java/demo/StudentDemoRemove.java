package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import entity.Student;

public class StudentDemoRemove {

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
			
			System.out.println("\n\nDeleting student");
			session.delete(myStudent);
			
			session.createQuery("delete from Student where email='setha.thay@live.com'").executeUpdate();
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}finally {
			factory.close();
		}
	}
}
