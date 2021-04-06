package hibernate_test;

import hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//мы создаем SessionFactory, Получаем из нее Session, создаем объект обычным джава кодом, начинаем транзакцию, сохраняем объект с помощью save, закрываем сессию через коммит или ролбек если нужно откатить на 1 транзакцию.
public class Test1 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory(); // Создаем SessionFactory и передаем туда всю информацию о БД, класс который аннотируется.

        try {//помечаем в трай на случай возникновения ошибок
            Session session = factory.getCurrentSession(); //Тут мы создаем сессию
            Employee emp = new Employee("Lexa", "Jukov", "IT", 650); //иницыализируем объект и заполняем его данными.
            session.beginTransaction();//Начало работы Сессии
            session.save(emp);//Сохраняем новосозданный объект
            session.getTransaction().commit();//закрываем сессию через коммит, либо через РоллБэк если нужно откатить на одну сессию назад
            System.out.println("Done");

            }
        finally{//При ошибке просто закрываем сессию.
            factory.close();
        }
    }
}
