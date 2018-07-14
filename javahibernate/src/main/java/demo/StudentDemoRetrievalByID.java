package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import entity.Student;

public class StudentDemoRetrievalByID {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// use the session object to save java object
			// create a student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Daffy", "Duck", "daffy.duck@gmail.com");
			// start a transaction
			session.beginTransaction();
			// save the student object
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			
			System.out.println("Saved student generated id:" + tempStudent.getId());
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student stuObj = session.get(Student.class, tempStudent.getId());
			System.out.println(stuObj);
			session.getTransaction().commit();
		}finally {
			factory.close();
		}
	}
}
