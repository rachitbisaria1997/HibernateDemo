import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class App {

    public static void main(String[] args) {
        System.out.println("hello world");

        Student student = new Student();

        student.setName("Rachit bisaria");
        student.setCollege("amity university");
        student.setActive(true);
        student.setPhone("1235125");
        student.setAbout("this is a dummy student");
        student.setFatherName("manoj bisaria");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.persist(student);
            System.out.println("student is saved successfully");
            transaction.commit();

            System.out.println();
        }
        catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }

    }
}
