package constants;

/**
 *
 * @author glamb
 */
public class Questions {

    // student and trainer Questions
    public static final String FIRST_NAME = "Type the First Name: ";

    public static final String LAST_NAME = "Type the Last Name: ";

    //Student questions
    public static final String BIRTHDATE = "Type Student's Date of Birth (dd-mm-yyyy): ";

    public static final String TUITION_FEES = "Type Student's Tuition Fees:";

    public static final String P_ORAL_MARK = "Give Student's Oral Mark: ";

    public static final String P_TOTAL_MARK = "Give Student's Total Mark: ";

    public static final String ENROLL_STUDENT = "Would you like to enroll the Student to an existing Course? (Y/N): ";

    //Trainer questions
    public static final String SUBJECT = "Type Trainer's Subject:";

    public static final String REGISTER_TRAINER = "Would you like to register the Trainer to an existing Course? (Y/N): ";

    //Course and Assignment Questions questions
    public static final String TITLE = "Give Title: ";

    // Course questions
    public static final String TYPE = "Choose Type of Course. 1.Full Time or 2.Part Time: ";

    public static final String STREAM = "Give Stream: ";

    public static final String START_DATE = "Give Start Date of Course (dd-mm-yyyy): ";

    public static final String END_DATE = "Give End Date of Course (dd-mm-yyyy): ";

    //Assignment questions
    public static final String A_ORAL_MARK = "Give the maximum Oral Mark of the Assignment: ";

    public static final String A_TOTAL_MARK = "Give the maximum Total Mark of the Assignment: ";

    public static final String DESCRIPTION = "Type the Description of the Assignment: ";

    public static final String SUBMISSION_DATETIME = "Type the Date and Time the students of the course will have to submit the Assignment (yyyy-mm-ddThh:mm:ss): ";

    // Other Questions
    public static final String SELECT_ID = "Type the ID here: ";

    public static final String OPTIONS = "\nThese are the available options:";

    public static final String TRY_AGAIN = "Wrong option, please try again: ";

    public static final String EXISTS = "Already exists!";

    public static final String INSERTED_TO_DB = "Successfully registered to Data Base!";

    public static final String FAILED = "Something went wrong!";

    public static final String CONNECTING_TO_DB = "Connecting to database...";

    public static final String CONNECTED_TO_DB = "Connected to Data Base!";

    // Menus
    public static final String ADVANCED_MENU = "(1)Enroll a Student to an existing Course"
            + "\n(2)Add an Assignment to an existing Course"
            + "\n(3)Add a Trainer to an existing Course"
            + "\n(4)Grade a Student's Assignment (instead of inserting to AssignmentsPerStudentPerCourse as it happens automatically)"
            + "\n(m)Move back to Main Menu"
            + "\nChoose an option: ";

    public static final String CREATE_MENU = "(1)Create a Course"
            + "\n(2)Create a Trainer"
            + "\n(3)Create a Student"
            + "\n(4)Create an Assignment"
            + "\n(m)Move back to Main Menu"
            + "\nChoose an option: ";

    public static final String DISPLAY_MENU = "(1)Display all Courses"
            + "\n(2)Display all Trainers"
            + "\n(3)Display all Students"
            + "\n(4)Display all Assignments"
            + "\n(5)Display all Students of a Course"
            + "\n(6)Display all Trainers of a Course"
            + "\n(7)Display all Assignments of a Course"
            + "\n(8)Display all Assignments that have been assigned to a Student from a Course"
            + "\n(9)Display all Students that participate to more than one Courses"
            + "\n(m)Move back to Main Menu"
            + "\nChoose an option: ";

    public static final String MAIN_MENU = "(1)Create Menu"
            + "\n(2)Advanced Menu"
            + "\n(3)Display Menu"
            + "\n(e)Exit "
            + "\nChoose an option: ";

}
