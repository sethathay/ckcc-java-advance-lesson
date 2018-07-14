package demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import entity.Student;

public class StudentDemoHQL {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			
			// start a transaction
			session.beginTransaction();
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			System.out.println("\n\n Query All Student");
			System.out.println("======================================");
			displayStudents(theStudents);
			
			// query last name 'Doe'
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
			
			System.out.println("\n\n Query Students with Last Name: Doe");
			System.out.println("======================================");
			displayStudents(theStudents);
			
			// query last name 'Doe' or first name 'Diffy'
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe' OR s.firstName = 'Daffy'").getResultList();
			
			System.out.println("\n\n Query Students with Last Name: Doe OR First Name : Diffy");
			System.out.println("======================================");
			displayStudents(theStudents);
			
			// query student where email LIKE '%yahoo.com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%yahoo.com'").list();
			
			System.out.println("\n\n Query Students with Email with yahoo.com");
			System.out.println("======================================");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		}finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		// display the students
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}
}
