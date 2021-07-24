import java.sql.*;
import java.util.concurrent.TimeUnit;

/*
 - список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс и
   общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
   Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;
 - число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
   с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.).
*/

public class Lesson4 {
    Connection connection;
    Statement statement;

    {
        try {
            connection = new DBConnection().get();
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //  - ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени.
    //    Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
    public boolean hasScheduleBugs() {
        boolean retVal = false;
        Timestamp endMovie = new Timestamp(0);
        try {
            ResultSet resultSet = statement.executeQuery(
                    "select movie.name, schedule.since, session.length " +
                            "from schedule, movie, session " +
                            "where schedule.movie_id = movie.id and session.id = movie.session_id " +
                            "order by schedule.since");
            if (!resultSet.isBeforeFirst()) return false;
            while (true) {
                if (!resultSet.next()) break;
                if (endMovie != null && endMovie.after(resultSet.getTimestamp(2))) {
                    System.out.printf("%s, %s, %d,%n",
                            resultSet.getString(1),
                            resultSet.getTimestamp(2),
                            resultSet.getInt(3)
                    );
                    retVal = true;
                }
                endMovie.setTime(resultSet.getTimestamp(2).getTime() +
                        TimeUnit.MINUTES.toMillis(resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retVal;
    }

//     - перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
//       Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
    public boolean notExistsInterval(int interval) {
        boolean retVal = false;
        Timestamp endMovie = new Timestamp(0);
        try {
            ResultSet resultSet = statement.executeQuery(
                    "select movie.name, schedule.since, session.length " +
                            "from schedule, movie, session " +
                            "where schedule.movie_id = movie.id and session.id = movie.session_id " +
                            "order by schedule.since");
            if (!resultSet.isBeforeFirst()) return false;
            while (true) {
                if (!resultSet.next()) break;
                if (endMovie != null && endMovie.after(resultSet.getTimestamp(2))) {
                    System.out.printf("%s, %s, %d,%n",
                            resultSet.getString(1),
                            resultSet.getTimestamp(2),
                            resultSet.getInt(3)
                    );
                    retVal = true;
                }
                endMovie.setTime(resultSet.getTimestamp(2).getTime() +
                        TimeUnit.MINUTES.toMillis(resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retVal;
    }


    public static void main(String[] args) {
        Lesson4 movieData = new Lesson4();
        try {
            System.out.println("- SCHEDULE BUGS:");
            movieData.hasScheduleBugs();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}