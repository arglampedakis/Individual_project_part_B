package utilities;

/**
 *
 * @author glamb
 */
import constants.*;
import entities.Trainer;
import java.sql.*;
import java.util.ArrayList;

public class TrainerUtils extends BaseUtils {

    public static Trainer createTrainer() {
        System.out.print(Questions.FIRST_NAME);
        String firstName = sc.next();
        System.out.print(Questions.LAST_NAME);
        String lastName = sc.next();
        System.out.print(Questions.SUBJECT);
        String subject = sc.next();
        Trainer trainer = new Trainer();
        trainer.setTrainerFirstName(firstName);
        trainer.setTrainerLastName(lastName);
        trainer.setSubject(subject);
        return trainer;
    }

    public static void insertTrainerToDB(Trainer trainer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.INSERT_TRAINER_Q;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, trainer.getTrainerFirstName());
            preparedStatement.setString(2, trainer.getTrainerLastName());
            preparedStatement.setString(3, trainer.getSubject());

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

    public static ArrayList<Trainer> getAllTrainers() {
        ArrayList<Trainer> trainersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.ALL_TRAINERS_Q;

            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("TRAINER_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String subject = resultSet.getString("T_SUBJECT");
                Trainer trainer = new Trainer();
                trainer.setTrainerId(id);
                trainer.setTrainerFirstName(firstName);
                trainer.setTrainerLastName(lastName);
                trainer.setSubject(subject);
                trainersList.add(trainer);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Something went wrong!");
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
        return trainersList;
    }

    public static ArrayList<Trainer> getTrainersPerCourse(int courseId) {
        ArrayList<Trainer> trainersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.TRAINERS_PER_COURSE_Q;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("TRAINER_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String subject = resultSet.getString("T_SUBJECT");
                Trainer trainer = new Trainer();
                trainer.setTrainerId(id);
                trainer.setTrainerFirstName(firstName);
                trainer.setTrainerLastName(lastName);
                trainer.setSubject(subject);
                trainersList.add(trainer);
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
        return trainersList;
    }

    public static void registerTrainerToCourseDB(int trainerId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(MYSQL_JDC_DRIVER);

            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String query = Queries.INSERT_TRAINERS_PER_COURSE_Q;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, trainerId);

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

    public static void addTrainerToCourse() {
        int trainerId = TrainerUtils.selectOneTrainer();
        int courseId = CourseUtils.selectOneCourse();
        if (checkIfTrainerPerCourseExists(trainerId, courseId)) {
            System.out.println("Trainer is already teaching in this Course!");
        } else {
            registerTrainerToCourseDB(trainerId, courseId);
        }
    }

    public static void displayAllTrainers() {
        System.out.println("Here's a list of all Trainers:");
        displayAList((ArrayList<Object>) (Object) getAllTrainers());
    }

    public static int selectOneTrainer() {
        ArrayList<Trainer> allTrainers = getAllTrainers();
        displayAllTrainers();
        System.out.print("Type the number of Trainer you want to select: ");
        int choice = sc.nextInt();
        int trainerId = allTrainers.get(choice - 1).getTrainerId();
        return trainerId;
    }

    public static void displayTrainersPerCourse() {
        int courseId = CourseUtils.selectOneCourse();
        System.out.println("The following trainers are teaching in the selected course: ");
        ArrayList<Trainer> trainers = getTrainersPerCourse(courseId);
        displayAList((ArrayList<Object>) (Object) trainers);
    }

    public static void createAndInsertTrainerToDB() {
        Trainer trainer = createTrainer();
        if (checkDublicateTrainer(trainer)) {
            System.out.println(Questions.EXISTS);
        } else {
            insertTrainerToDB(trainer);
        }
    }
}
