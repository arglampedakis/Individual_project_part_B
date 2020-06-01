package utilities;

import constants.Queries;
import constants.Questions;
import entities.Marks;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static utilities.AssignmentUtils.selectAssignmentOfStudentFromCourse;
import static utilities.CourseUtils.selectACourseOfAStudent;

import static utilities.StudentUtils.selectOneStudent;

/**
 *
 * @author glamb
 */
public class MarksUtils extends BaseUtils {

    public static Marks createMarks() {
        Marks marks = new Marks();
        System.out.print(Questions.P_ORAL_MARK);
        double pOralMark = sc.nextDouble();
        System.out.print(Questions.P_TOTAL_MARK);
        double pTotalMark = sc.nextDouble();
        marks.setpOralMark(pOralMark);
        marks.setpTotalMark(pTotalMark);
        return marks;
    }

    public static void updatePersonalMarks(Marks marks, int studentId, int courseID, int assignmentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.GRADE_STUDENT_ASSIGNMENT_Q;
            BigDecimal pOralMark = BigDecimal.valueOf(marks.getpOralMark()); // convert Double to Decimal
            BigDecimal pTotalMark = BigDecimal.valueOf(marks.getpTotalMark()); // convert Double to Decimal

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBigDecimal(1, pOralMark);
            preparedStatement.setBigDecimal(2, pTotalMark);
            preparedStatement.setInt(3, studentId);
            preparedStatement.setInt(4, courseID);
            preparedStatement.setInt(5, assignmentId);

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

    public static void setMarksOfStudents() {

        int studentId = selectOneStudent();
        int courseId = selectACourseOfAStudent(studentId);
        int assignmentId = selectAssignmentOfStudentFromCourse(studentId, courseId);
        Marks marks;
        do {
            marks = createMarks();
            if (validPersonalMarks(assignmentId, marks)) {
                System.out.println("Personal Marks cannot be greater than Assignment's Max Marks.");
            } else {
                updatePersonalMarks(marks, studentId, courseId, assignmentId);
            }
        } while (validPersonalMarks(assignmentId, marks));
    }
}
