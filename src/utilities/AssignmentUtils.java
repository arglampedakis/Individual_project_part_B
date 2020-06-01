package utilities;

import constants.*;
import entities.*;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static utilities.BaseUtils.sc;

/**
 *
 * @author glamb
 */
public class AssignmentUtils extends BaseUtils {

    public static Assignment createAssignment() {
        System.out.print(Questions.TITLE);
        String title = sc.next();
        System.out.print(Questions.DESCRIPTION);
        sc.nextLine();
        String desription = sc.nextLine();
        double oralMark = -1;
        double totalMark = -2;
        do {
            System.out.print(Questions.A_ORAL_MARK);
            oralMark = sc.nextDouble();
            System.out.print(Questions.A_TOTAL_MARK);
            totalMark = sc.nextDouble();
        } while (validAssignmentMarks(oralMark, totalMark));
        Assignment assignment = new Assignment();
        assignment.setAssignementTitle(title);
        assignment.setDescription(desription);
        assignment.setOralMark(oralMark);
        assignment.setTotalMark(totalMark);

        return assignment;
    }

    public static void insertAssignmentToDB(Assignment assignment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.INSERT_ASSIGNMENT_Q;
            BigDecimal oralMark = BigDecimal.valueOf(assignment.getOralMark()); // convert Double to Decimal
            BigDecimal totalMark = BigDecimal.valueOf(assignment.getTotalMark()); // convert Double to Decimal

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, assignment.getAssignementTitle());
            preparedStatement.setString(2, assignment.getDescription());
            preparedStatement.setBigDecimal(3, oralMark);
            preparedStatement.setBigDecimal(4, totalMark);

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

    public static ArrayList<Assignment> getAllAssignments() {
        ArrayList<Assignment> assignmentsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.ALL_ASSIGNMENTS_Q;

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ASSIGNMENT_ID");
                String title = resultSet.getString("TITLE");
                String description = resultSet.getString("A_DESCRIPTION");
                double oralMark = resultSet.getBigDecimal("ORAL_MARK").doubleValue();
                double totalMark = resultSet.getBigDecimal("TOTAL_MARK").doubleValue();
                Assignment assignment = new Assignment();
                assignment.setAssignmentId(id);
                assignment.setAssignementTitle(title);
                assignment.setDescription(description);
                assignment.setOralMark(oralMark);
                assignment.setTotalMark(totalMark);
                assignmentsList.add(assignment);

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
        return assignmentsList;
    }

    public static ArrayList<Assignment> getAssignmentsPerCourse(int courseId) {
        ArrayList<Assignment> assignmentsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.ASSIGNMENTS_PER_COURSE_Q;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ASSIGNMENT_ID");
                String title = resultSet.getString("TITLE");
                String description = resultSet.getString("A_DESCRIPTION");
                double oralMark = resultSet.getBigDecimal("ORAL_MARK").doubleValue();
                double totalMark = resultSet.getBigDecimal("TOTAL_MARK").doubleValue();
                LocalDateTime subDateTime = resultSet.getObject("SUBMISSION_DATETIME", LocalDateTime.class);
                Assignment assignment = new Assignment();
                assignment.setAssignmentId(id);
                assignment.setAssignementTitle(title);
                assignment.setDescription(description);
                assignment.setOralMark(oralMark);
                assignment.setTotalMark(totalMark);
                assignment.setSubDateTime(subDateTime);
                assignmentsList.add(assignment);
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
        return assignmentsList;
    }

    public static void assignAssignmentToCourseDB(int assignmentId, int courseId, LocalDateTime subDateTime) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.INSERT_ASSIGNMENTS_PER_COURSE_Q;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, assignmentId);
            java.sql.Timestamp timestamp = Timestamp.valueOf(subDateTime);
            preparedStatement.setTimestamp(3, timestamp);

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

    public static void displayAllAssignments() {
        System.out.println("Here's a list of all Assignments: ");
        displayAList((ArrayList<Object>) (Object) getAllAssignments());
    }

    public static int selectOneAssignment() {
        ArrayList<Assignment> allAssignments = getAllAssignments();
        displayAllAssignments();
        System.out.print("Type the number of the Assignment you want to select: ");
        int choice = sc.nextInt();
        int assignmentId = allAssignments.get(choice - 1).getAssignmentId();
        return assignmentId;
    }

    public static void addAssignmentToCourse() {
        int assignmentId = AssignmentUtils.selectOneAssignment();
        Course course = CourseUtils.selectObjectCourse();
        if (checkIfAssignmentPerCourseExists(assignmentId, course.getCourseId())) {
            System.out.println("Assignement already added to this Course!");
        } else {
            LocalDate startDate = course.getCourseStartDate();
            LocalDate endDate = course.getCourseEndDate();
            int courseId = course.getCourseId();
            System.out.print(Questions.SUBMISSION_DATETIME);
            LocalDateTime subDateTime = validateAssignmentSubDate(stringToLocalDateTime(sc.next()), startDate, endDate);
            assignAssignmentToCourseDB(assignmentId, courseId, subDateTime);
        }
    }

    public static void displayAssignmentsPerCourse() {
        int courseId = CourseUtils.selectOneCourse();
        ArrayList<Assignment> assignments = getAssignmentsPerCourse(courseId);
        int i = 1;
        for (Assignment assignment : assignments) {
            System.out.println(i + ". " + assignment + ", Submission Date Time: " + assignment.getSubDateTime());
            i++;
        }
        System.out.print("\n");
    }

    public static Map<Assignment, Marks> getAssignmentsPerStudentPerCourse(int studentId, int courseId) {
        Map<Assignment, Marks> assignmentsMarkMap = new HashMap<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.ASSGNMTS_PER_STD_PER_CRSE_Q;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                double pOralMark = -1;
                double pTotalMark = -1;
                int id = resultSet.getInt("A.ASSIGNMENT_ID");
                String title = resultSet.getString("A.TITLE");
                String description = resultSet.getString("A.A_DESCRIPTION");
                double oralMark = resultSet.getBigDecimal("A.ORAL_MARK").doubleValue();
                double totalMark = resultSet.getBigDecimal("A.TOTAL_MARK").doubleValue();
                try {
                    pOralMark = resultSet.getBigDecimal("APS.P_ORAL_MARK").doubleValue();
                    pTotalMark = resultSet.getBigDecimal("APS.P_TOTAL_MARK").doubleValue();
                } catch (NullPointerException e) {
                    //if catch, then the APS.P_ORAL_MARK and APS.P_TOTAL_MARK are Null
                    pOralMark = -1;
                    pTotalMark = -1;
                } finally {
                    Assignment assignment = new Assignment();
                    Marks marks = new Marks();
                    assignment.setAssignmentId(id);
                    assignment.setAssignementTitle(title);
                    assignment.setDescription(description);
                    assignment.setOralMark(oralMark);
                    assignment.setTotalMark(totalMark);
                    marks.setpOralMark(pOralMark);
                    marks.setpTotalMark(pTotalMark);
                    assignmentsMarkMap.put(assignment, marks);
                }
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
        return assignmentsMarkMap;
    }

    public static void displayAssignmentsPerStudentPerCourse(int studentId, int courseId) {
        Map<Assignment, Marks> assignmnetsMap = getAssignmentsPerStudentPerCourse(studentId, courseId);
        System.out.println("The following Assignments are assigned to the selected student from the selected course: ");
        assignmnetsMap.forEach((Assignment assignment, Marks mark) -> {
            if (mark.getpOralMark() == -1 || mark.getpTotalMark() == -1) {
                System.out.println("Assignment ID: " + assignment.getAssignmentId() + ". Title: " + assignment.getAssignementTitle() + ","
                        + " Description: " + assignment.getDescription() + ","
                        + " Student's Grades -> Oral Mark: NULL/" + assignment.getOralMark() + ","
                        + " Total Mark: NULL/" + assignment.getTotalMark());
            } else {
                System.out.println("Assignment ID: " + assignment.getAssignmentId() + ". Title: " + assignment.getAssignementTitle() + ","
                        + " Description: " + assignment.getDescription() + ","
                        + " Student's Grades -> Oral Mark: " + mark.getpOralMark() + "/" + assignment.getOralMark() + ","
                        + " Total Mark: " + mark.getpTotalMark() + "/" + assignment.getTotalMark());
            }
        });
    }

    public static int selectAssignmentOfStudentFromCourse(int studentId, int courseId) {
        displayAssignmentsPerStudentPerCourse(studentId, courseId);
        System.out.print(Questions.SELECT_ID);
        int assignmentId = sc.nextInt();
        return assignmentId;
    }

    public static void createAndInsertAssignmentToDB() {
        Assignment assignment = createAssignment();
        if (checkDublicateAssignment(assignment)) {
            System.out.println(Questions.EXISTS);
        } else {
            insertAssignmentToDB(assignment);
        }
    }
}
