package SaveStudent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import StudentEntity.Student;

public class SaveStudentCls {

	public static void main(String[] args) {
		// Create Factory of Session
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		// Get session object from factory
		Session sessionObj = factory.getCurrentSession();
		try {
			// Perform add new student to database
			// create a student object
			Student tempStudent = new Student("Vanndy", "Sun", "setha.thay@gmail.com");	
			// start transaction
			sessionObj.beginTransaction();
			// save the student
			sessionObj.save(tempStudent);
			// commit the transaction
			sessionObj.getTransaction().commit();
			
		}finally {
			factory.close();
		}
	}

}
