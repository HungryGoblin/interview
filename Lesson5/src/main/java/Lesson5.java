import java.sql.SQLException;

public class Lesson5 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        StudentRepositoryEM studentRepositoryEM = new StudentRepositoryEM();
        System.out.println(studentRepositoryEM.findAll());
        for (int i = 0; i < 1000; i++) {
            studentRepositoryEM.insert(new Student(null, String.format("Student-%d", i), 1 + (int)(Math.random() * 5)));
        }
        System.out.println(studentRepositoryEM.findAll());
        System.out.println(studentRepositoryEM.findById(666L));
        studentRepositoryEM.deleteById(666L);

        StudentRepositoryRaw studentRepositoryRaw = new StudentRepositoryRaw();
        for (int i = 0; i < 1000; i++) {
            studentRepositoryRaw.insert(new Student(null, String.format("Student-%d", i), 1 + (int)(Math.random() * 5)));
        }
        System.out.println(studentRepositoryRaw.findAll());
        System.out.println(studentRepositoryRaw.findById(777L));
        studentRepositoryRaw.deleteById(777L);

    }
}
