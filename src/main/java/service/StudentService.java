package service;


import entities.Student;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import util.HibernateUtil;

import java.util.List;

public class StudentService {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void saveStudent(Student student){

        try(Session session = sessionFactory.openSession()){

            Transaction beginTransaction = session.beginTransaction();

                session.persist(student);
               beginTransaction.commit();

        }
            catch (Exception e){
                e.printStackTrace();
            }

    }

    public Student getById(long studentId){

        try(Session session = sessionFactory.openSession()){

            Student student = session.getReference(Student.class, studentId);
            return student;

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public Student updateStudent(long studentId, Student student){
        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();

            Student oldStudent = session.get(Student.class, studentId);

            if(oldStudent != null){
                oldStudent.setName(student.getName());
                oldStudent.setFatherName(student.getFatherName());

                oldStudent = session.merge(oldStudent);
            }

            transaction.commit();

            return oldStudent;
        }

    }


    public List<Student> getAllStudentsHQL(){

        try(Session session = sessionFactory.openSession())
        {
            String getHQL = "FROM Student";
            Query query = session.createQuery(getHQL, Student.class);

            return query.list();
        }
    }


    public void deleteStudent(long studentId){

        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();

            Student student = session.get(Student.class, studentId);

            if(student != null){
                session.remove(student);

            }
            transaction.commit();

        }
    }

    public List<Student> getAllStudentsHQL(){

        try(Session session = sessionFactory.openSession()){

            String getHQL = "FROM Student";
            Query query = session.createQuery(getHQL, Student.class);
            return query.list();

        }
    }


    public Student getStudentByNameHQL(String name){
        try(Session session = sessionFactory.openSession()){

            String getByNameHql = "FROM Student where name = :studentName";

            Query query = session.createQuery(getByNameHql, Student.class);

            query.setParameter("studentName", name);
            return query.uniqueResult();

        }
    }

    // use criteria api
    // get all student of same college

    public List<Student> getStudentsByCollegeCriteria(String college){

        try(Session session = sessionFactory.openSession()){

            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);

            Root<Student> root = query.from(Student.class);

            query.select(root).where(criteriaBuilder.equal(root.get("college"), college));

            Query query2 = session.createQuery(query);

            return  query2.getResultList();

        }

    }

    public List<Student> getStudentWithPagination(int page, int pageSize){

        try(Session session = sessionFactory.openSession()){

            String pagiQuery = "FROM Student";

            Query query = session.createQuery(pagiQuery, Student.class);

            query.setFirstResult((page  -1) * pageSize );

            // if page is 1 and pageSize is 10 it will return result from 0-9.
            // if page is 2 and pageSize is 20 it will return result from 10-20

            query.setMaxResults(pageSize);

            return query.list();
        }

    }

    @Transactional
    public void printOrders(Long stuId){

        Student stud = studentRepo.findById(stuId).orElseThrow();

        List<Order> orders = stud.getOrders();

        for(Order order : orders){
            SOP(order.getId() + " "+ order.getProduct());
        }

    }

    // various methods in Hibernate session
    // save(Object entity) - inserts a new record, returns the id
    // persist(Object entity) - same as save, doesnt return id
    // merge(Object entity)	Merges changes from a detached entity.
    // delete(Object entity) deletes entity from DB
    //createQuery(String hql) - creates an HQL query
    //beginTRansaction = to begin a transaction
    // close() - to close the session


    Session session = factory.openSession();
    Transaction tx = session.beginTransaction();

    try{
        User user = session.get(User.class, 1);
        if(user != null)
        {
            session.delete(user);
            SOP("user deleted" + user.getName());
        }else{
            SOP("user not found");
        }

        tx.commit();

    }
    finally{
        session.close();
    }

}
