package utilities;

import constants.*;
import entities.Course;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author glamb
 */
public class CourseUtils extends BaseUtils {

    public static Course createCourse() {
        System.out.print(Questions.TITLE);
        String title = sc.next();
        System.out.print(Questions.TYPE);
        String type = chooseTypeOfCourse();
        System.out.print(Questions.STREAM);
        String stream = sc.next();
        System.out.print(Questions.START_DATE);
        LocalDate startDate = validateStartDateOfCourse(stringToLocalDate(sc.next()));
        System.out.print(Questions.END_DATE);
        LocalDate endDate = validateEndDateOfCourse(stringToLocalDate(sc.next()), startDate);

        Course course = new Course();
        course.setCourseTitle(title);
        course.setCourseType(type);
        course.setCourseStream(stream);
        course.setCourseStartDate(startDate);
        course.setCourseEndDate(endDate);

        return course;
    }

    private static String chooseTypeOfCourse() {
        String result = "";
        String choice;
        do {
            choice = sc.next();
            switch (choice) {
                case "1":
                    result = "Full Time";
                    break;
                case "2":
                    result = "Part Time";
                    break;
                default:
                    System.out.print(Questions.TRY_AGAIN);
            }
        } while ((!choice.equals("1") && !choice.equals("2")));

        return result;
    }

    public static void insertCourseToDB(Course course) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.INSERT_COURSE_Q;

            java.sql.Date startDate = java.sql.Date.valueOf(course.getCourseStartDate()); //convert LocalDate to sql.Date
            java.sql.Date endDate = java.sql.Date.valueOf(course.getCourseEndDate());

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course.getCourseTitle());
            preparedStatement.setString(2, course.getCourseType());
            preparedStatement.setString(3, course.getCourseStream());
            preparedStatement.setDate(4, startDate);
            preparedStatement.setDate(5, endDate);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println(Questions.INSERTED_TO_DB);
            } else {
                System.out.println(Questions.FAILED);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(Questions.FAILED);
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> coursesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.ALL_COURSES_Q;

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("COURSE_ID");
                String title = resultSet.getString("TITLE");
                String type = resultSet.getString("C_TYPE");
                String stream = resultSet.getString("STREAM");
                java.sql.Date startDate = resultSet.getDate("START_DATE");
                java.sql.Date endDate = resultSet.getDate("END_DATE");
                LocalDate startDateConverted = startDate.toLocalDate();// convert sql.Date to LocalDate
                LocalDate endDateConverted = endDate.toLocalDate(); // convert sql.Date to LocalDate
                Course course = new Course();
                course.setCourseId(id);
                course.setCourseTitle(title);
                course.setCourseType(type);
                course.setCourseStream(stream);
                course.setCourseStartDate(startDateConverted);
                course.setCourseEndDate(endDateConverted);
                coursesList.add(course);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {

                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return coursesList;
    }

    public static void displayAllCourses() {
        System.out.println("Here's a list of all Courses:");
        displayAList((ArrayList<Object>) (Object) getAllCourses());
    }

    public static int selectOneCourse() {
        ArrayList<Course> allCourses = getAllCourses();
        System.out.println("These are the available courses:");
        displayAList((ArrayList<Object>) (Object) allCourses);
        System.out.print("Type the number of the Course you want to select: ");
        int choice = sc.nextInt();
        int courseId = allCourses.get(choice - 1).getCourseId();
        return courseId;
    }

    public static Course selectObjectCourse() {
        ArrayList<Course> allCourses = getAllCourses();
        System.out.println("These are the available courses: ");
        displayAList((ArrayList<Object>) (Object) allCourses);
        System.out.print("Type the number of the Course you want to select: ");
        int choice = sc.nextInt();
        Course course = allCourses.get(choice - 1);
        return course;
    }

    public static ArrayList<Course> getCoursesOfStudent(int studentId) {
        ArrayList<Course> coursesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.COURSES_OF_STUDENT_Q;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("COURSE_ID");
                String title = resultSet.getString("TITLE");
                String type = resultSet.getString("C_TYPE");
                String stream = resultSet.getString("STREAM");
                java.sql.Date startDate = resultSet.getDate("START_DATE");
                java.sql.Date endDate = resultSet.getDate("END_DATE");
                LocalDate startDateConverted = startDate.toLocalDate();// convert sql.Date to LocalDate
                LocalDate endDateConverted = endDate.toLocalDate(); // convert sql.Date to LocalDate
                Course course = new Course();
                course.setCourseId(id);
                course.setCourseTitle(title);
                course.setCourseType(type);
                course.setCourseStream(stream);
                course.setCourseStartDate(startDateConverted);
                course.setCourseEndDate(endDateConverted);
                coursesList.add(course);

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {

                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return coursesList;
    }

    public static int selectACourseOfAStudent(int studentId) {
        ArrayList<Course> coursesList = getCoursesOfStudent(studentId);
        System.out.println("The student participates in the following courses: ");
        displayAList((ArrayList<Object>) (Object) coursesList);
        System.out.print(Questions.SELECT_ID);
        int choice = sc.nextInt();
        int courseid = coursesList.get(choice - 1).getCourseId();
        return courseid;
    }

    public static void createAndInsertCourseToDB() {
        Course course = createCourse();
        if (checkDublicateCourse(course)) {
            System.out.println(Questions.EXISTS);
        } else {
            insertCourseToDB(course);
        }
    }
}
