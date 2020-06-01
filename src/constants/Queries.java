package constants;

/**
 *
 * @author glamb
 */
public class Queries {

    //Select Queries
    public static final String ALL_STUDENTS_Q = "SELECT * FROM STUDENTS";

    public static final String ALL_TRAINERS_Q = "SELECT * FROM TRAINERS";

    public static final String ALL_ASSIGNMENTS_Q = "SELECT * FROM ASSIGNMENTS";

    public static final String ALL_COURSES_Q = "SELECT * FROM COURSES";

    public static final String STUDENTS_PER_COURSE_Q = "SELECT S.* "
            + "FROM COURSES C, STUDENTS S, STUDENTS_PER_COURSE SC "
            + "WHERE C.COURSE_ID = ? "
            + "AND C.COURSE_ID = SC.COURSE_ID "
            + "AND S.STUDENT_ID = SC.STUDENT_ID "
            + "ORDER BY C.COURSE_ID";

    public static final String TRAINERS_PER_COURSE_Q = "SELECT T.* "
            + "FROM COURSES C, TRAINERS T, TRAINERS_PER_COURSE TC "
            + "WHERE C.COURSE_ID = ? "
            + "AND C.COURSE_ID = TC.COURSE_ID "
            + "AND T.TRAINER_ID = TC.TRAINER_ID "
            + "ORDER BY C.COURSE_ID";

    public static final String ASSIGNMENTS_PER_COURSE_Q = "SELECT A.*,AC.* "
            + "FROM COURSES C, ASSIGNMENTS A, ASSIGNMENTS_PER_COURSE AC "
            + "WHERE C.COURSE_ID = ? "
            + "AND C.COURSE_ID = AC.COURSE_ID "
            + "AND A.ASSIGNMENT_ID = AC.ASSIGNMENT_ID "
            + "ORDER BY C.COURSE_ID";

    //I won't use this Query, I'll use "ASSGNMTS_PER_STD_PER_CRSE_Q" instead.
    public static final String ASGNMTS_PER_STD_PER_CRSE_WTH_JOIN_Q = "SELECT A.* FROM STUDENTS S JOIN STUDENTS_PER_COURSE SC ON SC.STUDENT_ID = S.STUDENT_ID "
            + "JOIN COURSES C ON C.COURSE_ID = SC.COURSE_ID "
            + "JOIN ASSIGNMENTS_PER_COURSE AC ON AC.COURSE_ID = C.COURSE_ID "
            + "JOIN ASSIGNMENTS A ON A.ASSIGNMENT_ID = AC.ASSIGNMENT_ID "
            + "ORDER BY S.STUDENT_ID";

    public static final String ASSGNMTS_PER_STD_PER_CRSE_Q = "SELECT A.*, APS.P_ORAL_MARK, APS.P_TOTAL_MARK "
            + "FROM ASSIGNMENTS_PER_STUDENT_PER_COURSE AS APS, ASSIGNMENTS AS A "
            + "WHERE APS.STUDENT_ID = ? "
            + "AND APS.COURSE_ID = ? "
            + "AND APS.ASSIGNMENT_ID = A.ASSIGNMENT_ID";

    public static final String STDS_WITH_MANY_COURSES_Q = "SELECT S.*, COUNT(*) AS NUM_OF_COURSES "
            + "FROM STUDENTS S, STUDENTS_PER_COURSE SC "
            + "WHERE S.STUDENT_ID = SC.STUDENT_ID "
            + "GROUP BY SC.STUDENT_ID "
            + "HAVING COUNT(*) >1 "
            + "ORDER BY S.STUDENT_ID";

    public static final String COURSES_OF_STUDENT_Q = "SELECT C.* "
            + "FROM COURSES C, STUDENTS_PER_COURSE SC "
            + "WHERE SC.STUDENT_ID = ? "
            + "AND SC.COURSE_ID = C.COURSE_ID";

    //Insert Queries
    public static final String INSERT_COURSE_Q = "INSERT INTO COURSES (TITLE, C_TYPE, STREAM, START_DATE, END_DATE) "
            + "VALUES (?, ?, ?, ?, ?)";

    public static final String INSERT_STUDENT_Q = "INSERT INTO STUDENTS (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, TUITION_FEES) "
            + "VALUES (?, ?, ?, ?)";

    public static final String INSERT_TRAINER_Q = "INSERT INTO TRAINERS (FIRST_NAME, LAST_NAME, T_SUBJECT) "
            + "VALUES (?, ?, ?)";

    public static final String INSERT_ASSIGNMENT_Q = "INSERT INTO ASSIGNMENTS (TITLE, A_DESCRIPTION, ORAL_MARK, TOTAL_MARK) "
            + "VALUES (?, ?, ?, ?)";

    public static final String INSERT_ASSIGNMENTS_PER_COURSE_Q = "INSERT INTO ASSIGNMENTS_PER_COURSE (COURSE_ID, ASSIGNMENT_ID, SUBMISSION_DATETIME) "
            + "VALUES (?, ?, ?)";

    public static final String INSERT_TRAINERS_PER_COURSE_Q = "INSERT INTO TRAINERS_PER_COURSE (COURSE_ID, TRAINER_ID) "
            + "VALUES (?, ?)";

    public static final String INSERT_STUDENTS_PER_COURSE_Q = "INSERT INTO STUDENTS_PER_COURSE (COURSE_ID, STUDENT_ID) "
            + "VALUES (?, ?)";
    //Update Queries
    public static final String GRADE_STUDENT_ASSIGNMENT_Q = "UPDATE ASSIGNMENTS_PER_STUDENT_PER_COURSE "
            + "SET P_ORAL_MARK = ?, P_TOTAL_MARK = ? "
            + "WHERE STUDENT_ID = ? "
            + "AND COURSE_ID = ? "
            + "AND ASSIGNMENT_ID = ?";
}
