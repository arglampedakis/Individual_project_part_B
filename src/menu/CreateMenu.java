package menu;

import constants.Questions;
import static utilities.AssignmentUtils.*;
import static utilities.BaseUtils.sc;
import static utilities.CourseUtils.*;
import static utilities.StudentUtils.*;
import static utilities.TrainerUtils.*;

/**
 *
 * @author glamb
 */
public class CreateMenu {

    public static void CreateMenu() {

        String choice;
        do {
            System.out.println(Questions.OPTIONS);
            System.out.print(Questions.CREATE_MENU);
            choice = sc.next();

            switch (choice) {
                case "1":
                    createAndInsertCourseToDB();
                    break;
                case "2":
                    createAndInsertTrainerToDB();
                    break;
                case "3":
                    createAndInsertStudentToDB();
                    break;
                case "4":
                    insertAssignmentToDB(createAssignment());
                    break;
                default:
                    System.out.println(Questions.TRY_AGAIN);
                    break;
                case "m": //exit while
            }
        } while (!choice.equals("m"));
    }
}
