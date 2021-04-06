package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

//мы создаем SessionFactory, Получаем из нее Session, создаем объект обычным джава кодом, начинаем транзакцию, сохраняем объект с помощью save, закрываем сессию через коммит или ролбек если нужно откатить на 1 транзакцию.
public class Test4 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();

        try {//Тут мы меняет значение какого то поля
            Session session = factory.getCurrentSession();
            session.beginTransaction();

//            Employee emp = session.get(Employee.class, 1); - вручную указав id человека мы меняем ему ЗП
//            emp.setSalary(1500); //передав в новый объект все характеристики человека, мы меняем ему ЗП через сеттер

            session.createQuery("update Employee set salary = 1000 " +
                    "where name = 'Elena'").executeUpdate(); // тут указав sql запрос, мы апдейтим информацию, назначаем ЗП, ГДЕ имя человека "Елена"

            session.getTransaction().commit();//Закрываем сессию


            System.out.println("Done");

            }
        finally{
            factory.close();
        }
    }
}
