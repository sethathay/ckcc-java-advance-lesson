package demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import entity.Student;

public class StudentDemoSave {

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
			Student tempStudent = new Student("Paul", "School", "school.paul@gmail.com");
			// start a transaction
			session.beginTransaction();
			// save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			//Student getStudent = session.get(Student.class, tempStudent.getId());
			//System.out.println(getStudent.toString());
		}finally {
			factory.close();
		}
	}
}
