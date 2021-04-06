package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//мы создаем SessionFactory, Получаем из нее Session, создаем объект обычным джава кодом, начинаем транзакцию, сохраняем объект с помощью save, закрываем сессию через коммит или ролбек если нужно откатить на 1 транзакцию.
public class Test5 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        try {//Тут мы удаляем челоека по какому-то значению(по имени)
            Session session = factory.getCurrentSession();
            session.beginTransaction();

//            Employee emp = session.get(Employee.class, 1); Тут мы создаем объект и передаем ему четко id человека которого хотим удалить.
//            session.delete(emp);

            session.createQuery("delete Employee where name = 'Lexa'").executeUpdate(); //Создаем sql запрос через тел delete и указываем имя.

            session.getTransaction().commit();//Закрываем сессию.


            System.out.println("Done");

            }
        finally{
            factory.close();
        }
    }
}
