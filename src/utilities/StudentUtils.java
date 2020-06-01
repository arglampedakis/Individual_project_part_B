package utilities;

import constants.*;
import entities.Student;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author glamb
 */
public class StudentUtils extends BaseUtils {

    public static Student createStudent() {
        System.out.print(Questions.FIRST_NAME);
        String firstName = sc.next();
        System.out.print(Questions.LAST_NAME);
        String lastName = sc.next();
        System.out.print(Questions.BIRTHDATE);
        LocalDate dateOfBirth = validateYearOfBirth(stringToLocalDate(sc.next()));
        System.out.print(Questions.TUITION_FEES);
        double tuitionFees = sc.nextDouble();
        Student student = new Student();
        student.setStudentFirstName(firstName);
        student.setStudentLastName(lastName);
        student.setDateOfBirth(dateOfBirth);
        student.setTuitionFees(tuitionFees);
        return student;
    }

    public static void insertStudentToDB(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.INSERT_STUDENT_Q;
            BigDecimal fees = BigDecimal.valueOf(student.getTuitionFees()); // convert Double to Decimal
            java.sql.Date date = java.sql.Date.valueOf(student.getDateOfBirth()); // Convert LocalDate to java.sql.Date

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getStudentFirstName());
            preparedStatement.setString(2, student.getStudentLastName());
            preparedStatement.setDate(3, date);
            preparedStatement.setBigDecimal(4, fees);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println(Questions.INSERTED_TO_DB);
            } else {
                System.out.println(Questions.FAILED);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(Questions.FAILED);
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public static ArrayList<Student> getAllStudents() {
        ArrayList<Student> studentsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.ALL_STUDENTS_Q;

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("STUDENT_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                LocalDate dateOfBirth = resultSet.getDate("DATE_OF_BIRTH").toLocalDate();
                double tuitionFees = resultSet.getBigDecimal("TUITION_FEES").doubleValue();
                Student student = new Student();
                student.setStudentId(id);
                student.setStudentFirstName(firstName);
                student.setStudentLastName(lastName);
                student.setDateOfBirth(dateOfBirth);
                student.setTuitionFees(tuitionFees);
                studentsList.add(student);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(Questions.FAILED);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (preparedStatement != null) {

                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return studentsList;
    }

    public static ArrayList<Student> getStudentsPerCourse(int courseId) {
        ArrayList<Student> studentsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.STUDENTS_PER_COURSE_Q;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("STUDENT_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                LocalDate dateOfBirth = resultSet.getDate("DATE_OF_BIRTH").toLocalDate();
                double tuitionFees = resultSet.getBigDecimal("TUITION_FEES").doubleValue();
                Student student = new Student();
                student.setStudentId(id);
                student.setStudentFirstName(firstName);
                student.setStudentLastName(lastName);
                student.setDateOfBirth(dateOfBirth);
                student.setTuitionFees(tuitionFees);
                studentsList.add(student);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(Questions.FAILED);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (preparedStatement != null) {

                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return studentsList;
    }

    public static void enrollStudentToCourseDB(int studentId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.INSERT_STUDENTS_PER_COURSE_Q;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, studentId);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println(Questions.INSERTED_TO_DB);
            } else {
                System.out.println(Questions.FAILED);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(Questions.FAILED);
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public static void registerStudentToCourse() {
        int studentId = StudentUtils.selectOneStudent();
        int courseId = CourseUtils.selectOneCourse();
        if (checkIfStudentPerCourseExists(studentId, courseId)) {
            System.out.println("Student already participates in that course!");
        } else {
            enrollStudentToCourseDB(studentId, courseId);
        }
    }

    public static void displayAllStudents() {
        System.out.println("Here's a list of all Students:");
        displayAList((ArrayList<Object>) (Object) getAllStudents());
    }

    public static int selectOneStudent() {
        ArrayList<Student> allStudents = getAllStudents();
        displayAllStudents();
        System.out.print("Type the number of the Student you want to select: ");
        int choice = sc.nextInt();
        int studentId = allStudents.get(choice - 1).getStudentId();
        return studentId;
    }

    public static Map<Student, Integer> getStudentsWithManyCourses() {
        Map<Student, Integer> studentsMap = new HashMap<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.STDS_WITH_MANY_COURSES_Q;

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("STUDENT_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                LocalDate dateOfBirth = resultSet.getDate("DATE_OF_BIRTH").toLocalDate();
                double tuitionFees = resultSet.getBigDecimal("TUITION_FEES").doubleValue();
                int count = resultSet.getInt("NUM_OF_COURSES");
                Student student = new Student();
                student.setStudentId(id);
                student.setStudentFirstName(firstName);
                student.setStudentLastName(lastName);
                student.setDateOfBirth(dateOfBirth);
                student.setTuitionFees(tuitionFees);
                studentsMap.put(student, count);

            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(Questions.FAILED);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (preparedStatement != null) {

                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        return studentsMap;
    }

    public static void displayStudentsWithManyCourses() {
        Map<Student, Integer> studentsMap = getStudentsWithManyCourses();
        AtomicInteger ordinal = new AtomicInteger(1);
        studentsMap.forEach((Student student, Integer count) -> {
            System.out.println(ordinal + ". " + student + ", participates in " + count + " Courses!");
            ordinal.getAndIncrement();
        });
    }

    public static void displayStudentsPerCourse() {
        int courseId = CourseUtils.selectOneCourse();
        System.out.println("The following students are participitaing in the selected course: ");
        ArrayList<Student> students = getStudentsPerCourse(courseId);
        displayAList((ArrayList<Object>) (Object) students);
    }

    public static void createAndInsertStudentToDB() {
        Student student = createStudent();
        if (checkDublicateStudent(student)) {
            System.out.println(Questions.EXISTS);
        } else {
            insertStudentToDB(student);
        }
    }
}
