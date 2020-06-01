package menu;

import constants.Questions;
import static utilities.BaseUtils.sc;
import utilities.*;

/**
 *
 * @author glamb
 */
public class AdvancedMenu {

    public static void AdvancedMenu() {

        String choice;
        do {
            System.out.println(Questions.OPTIONS);
            System.out.print(Questions.ADVANCED_MENU);
            choice = sc.next();

            switch (choice) {
                case "1":
                    StudentUtils.registerStudentToCourse();
                    break;
                case "2":
                    AssignmentUtils.addAssignmentToCourse();
                    break;
                case "3":
                    TrainerUtils.addTrainerToCourse();
                    break;
                case "4":
                    MarksUtils.setMarksOfStudents();
                    break;
                default:
                    System.out.println(Questions.TRY_AGAIN);
                    break;
                case "m": //exit while
            }
        } while (!choice.equals("m"));
    }

}
