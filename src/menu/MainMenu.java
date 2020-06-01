package menu;

import constants.Questions;
import static utilities.BaseUtils.sc;

/**
 *
 * @author glamb
 */
public class MainMenu {

    public static void mainMenu() {
        System.out.println(" =============");
        System.out.println(" | Main menu |");
        System.out.println(" =============\n");

        String choice;
        do {
            System.out.println(Questions.OPTIONS);
            System.out.print(Questions.MAIN_MENU);
            choice = sc.next();

            switch (choice) {
                case "1":
                    CreateMenu.CreateMenu();
                    break;
                case "2":
                    AdvancedMenu.AdvancedMenu();
                    break;
                case "3":
                    DisplayMenu.displayMenu();
                    break;
                default:
                    System.out.println(Questions.TRY_AGAIN);
                    break;
                case "e": //exit while
            }
        } while (!choice.equals("e"));

    }

}
