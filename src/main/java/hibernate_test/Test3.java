package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//мы создаем SessionFactory, Получаем из нее Session, создаем объект обычным джава кодом, начинаем транзакцию, сохраняем объект с помощью save, закрываем сессию через коммит или ролбек если нужно откатить на 1 транзакцию.
public class Test3 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        try {//Тут мы получаем полностью всех людей из БД которые имеют следующие характеристики.
            Session session = factory.getCurrentSession();
            session.beginTransaction();

           // List<Employee> emps = session.createQuery("from Employee").getResultList(); - тут мы получили всех людей
            List<Employee> emps = session.createQuery("from Employee " + "where name = 'Artem' AND salary>650").getResultList(); //тут мы получаем человека с конкретными параметрами

            for(Employee e: emps){ // Проходимся в цикле и выводим всех людей на экран
                System.out.println(e);
            }




            session.getTransaction().commit(); //закрываем сессию


            System.out.println("Done");

            }
        finally{
            factory.close();
        }
    }
}
