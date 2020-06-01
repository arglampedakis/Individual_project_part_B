package menu;

import constants.Questions;
import static utilities.BaseUtils.sc;
import utilities.*;
import static utilities.CourseUtils.selectACourseOfAStudent;
import static utilities.StudentUtils.selectOneStudent;

/**
 *
 * @author glamb
 */
public class DisplayMenu {

    public static void displayMenu() {

        String choice;
        do {
            System.out.println(Questions.OPTIONS);
            System.out.print(Questions.DISPLAY_MENU);
            choice = sc.next();

            switch (choice) {
                case "1":
                    CourseUtils.displayAllCourses();
                    break;
                case "2":
                    TrainerUtils.displayAllTrainers();
                    break;
                case "3":
                    StudentUtils.displayAllStudents();
                    break;
                case "4":
                    AssignmentUtils.displayAllAssignments();
                    break;
                case "5":
                    StudentUtils.displayStudentsPerCourse();
                    break;
                case "6":
                    TrainerUtils.displayTrainersPerCourse();
                    break;
                case "7":
                    AssignmentUtils.displayAssignmentsPerCourse();
                    break;
                case "8":
                    int studentId = selectOneStudent();
                    int courseId = selectACourseOfAStudent(studentId);
                    AssignmentUtils.displayAssignmentsPerStudentPerCourse(studentId, courseId);
                    break;
                case "9":
                    StudentUtils.displayStudentsWithManyCourses();
                    break;
                default:
                    System.out.println(Questions.TRY_AGAIN);
                    break;
                case "m": //exit while
            }
        } while (!choice.equals("m"));

    }
}
