package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//мы создаем SessionFactory, Получаем из нее Session, создаем объект обычным джава кодом, начинаем транзакцию, сохраняем объект с помощью save, закрываем сессию через коммит или ролбек если нужно откатить на 1 транзакцию.
public class Test2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        try {//Производим все тоже самое что и в Test1, только тут мы получаем данный объект.
            Session session = factory.getCurrentSession();
            Employee emp = new Employee("Elena", "Pypkova", "Sales", 800);
            session.beginTransaction();
            session.save(emp);
            session.getTransaction().commit();

            int myId = emp.getId();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, myId);
            session.getTransaction().commit();
            System.out.println(employee);

            System.out.println("Done");

            }
        finally{
            factory.close();
        }
    }
}
