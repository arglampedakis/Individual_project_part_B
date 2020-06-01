package utilities;

import java.util.Scanner;
import java.util.ArrayList;
import validations.Validations;

/**
 *
 * @author glamb
 */
public class BaseUtils extends Validations {

    public static final Scanner sc = new Scanner(System.in);
    protected static final String MYSQL_JDC_DRIVER = "com.mysql.cj.jdbc.Driver";

    protected static final String DB_URL = "jdbc:mysql://localhost/my_school?serverTimeZone=UTC";

    protected static final String USERNAME = "java_user";
    protected static final String PASSWORD = "password123";

    public static void displayAList(ArrayList<Object> list) {
        int i = 1;
        for (Object object : list) {
            System.out.println(i + ". " + object);
            i++;
        }
        System.out.print("\n");
    }

}
