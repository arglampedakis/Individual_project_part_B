package validations;

import entities.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import static utilities.AssignmentUtils.*;
import static utilities.BaseUtils.sc;
import static utilities.CourseUtils.*;
import static utilities.StudentUtils.*;
import static utilities.TrainerUtils.*;

/**
 *
 * @author glamb
 */
public class Validations {

    protected static LocalDate stringToLocalDate(String dateString) {
        String date = validateDateString(dateString);//checking if the given String can be converted to localDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);//converting String to LocalDate
        return localDate;
    }

    protected static String validateDateString(String date) {
        if (date.length() != 10) {// the length of the String "DD-MM-YYYY" is 10
            System.out.print("Invalid format, please try again: ");
            return validateDateString(sc.next());
        }
        if (date.charAt(2) != '-' || date.charAt(5) != '-') {//Checking the position of '-' 
            System.out.print("Invalid format, please try again: ");
            return validateDateString(sc.next());
        }
        for (int i = 0; i < date.length(); i++) {// Checking if the rest of the string contains digits
            if (i == 2 || i == 5) {//skipping the position of '-' that were checked previously
                continue;
            }
            if (!(Character.isDigit(date.charAt(i)))) {//checking for digits
                System.out.print("Invalid format, please try again: ");
                return validateDateString(sc.next());
            }
        }//the format of the String is now "DD-MM-YYYY"
        if (validateDate(date) == false) {//validating that the given date is legit
            System.out.print("The date provided is incorrect. Please try again: ");
            return validateDateString(sc.next());
        }
        return date;
    }

    protected static boolean validateDate(String date) {
        String day = date.substring(0, 2);//Extract the DD from "DD-MM-YYYY"
        int dayToInt = Integer.parseInt(day);//then convert the string to int

        String month = date.substring(3, 5);// Extract the MM from "DD-MM-YYYY"
        int monthToInt = Integer.parseInt(month);//then convert the string to int

        String year = date.substring(6, 10);// Extract the YYYY from "DD-MM-YYYY"
        int yearToInt = Integer.parseInt(year);//then convert the string to int

        if (dayToInt > 32 || dayToInt < 0 || monthToInt < 0 || monthToInt > 13 || yearToInt < 1900 || yearToInt > 2100) {
            //Checking if the DD isbetween 1-31, if the MM is between 1-12 and if the YYYY is between 1900-2100
            return false;
        }
        if ((dayToInt >= 29) && (monthToInt == 2) && !((yearToInt % 4) == 0)) {
            //In case of February (2nd month) check if the the DD is greater than 28 for non-leap year
            return false;
        }
        if (((monthToInt == 2) && (dayToInt >= 30) && (yearToInt % 4 == 0))) {
            return false; //In case of February (2nd month) check if the the DD is greater than 29 for leap year 
        }
        if ((monthToInt == 04 || monthToInt == 6 || monthToInt == 9 || monthToInt == 11)) {
            if (dayToInt >= 31) {//check for months with 30 days (April=4, June=6, September=9, November=11)
                return false;
            }
        }
        return true;
    }

    protected static LocalDate validateYearOfBirth(LocalDate dateOfBirth) {
        int yearOfBirth = dateOfBirth.getYear();

        if (yearOfBirth < 1930) {
            System.out.print("You're too old for this stuff, please try again: ");
            return validateYearOfBirth(stringToLocalDate(sc.next()));
        } else if (yearOfBirth > 2003) {
            System.out.print("Only adults allowed, sry bro. Please try again: ");
            return validateYearOfBirth(stringToLocalDate(sc.next()));
        } else {
            return dateOfBirth;
        }
    }

    protected static LocalDate validateStartDateOfCourse(LocalDate chosenDate) {
        LocalDate currentDate = LocalDate.now();
        if (chosenDate.isAfter(currentDate)) {
            return chosenDate;
        } else {
            System.out.print("Current date is " + currentDate + ". Please insert a future date: ");
        }
        return validateStartDateOfCourse(stringToLocalDate(sc.next()));
    }

    protected static LocalDate validateEndDateOfCourse(LocalDate chosenDate, LocalDate startDate) {
        if (chosenDate.isAfter(startDate)) {
            return chosenDate;
        } else {
            System.out.print("The End date should be after the Start Date. Please insert a valid date: ");
        }
        return validateEndDateOfCourse(stringToLocalDate(sc.next()), startDate);
    }

    protected static LocalDateTime stringToLocalDateTime(String dateTime) {
        LocalDateTime localDateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        try {
            localDateTime = LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            System.out.print("Invalid input. Please try again: ");
            return stringToLocalDateTime(sc.next());
        }

        return localDateTime;
    }

    protected static LocalDateTime validateAssignmentSubDate(LocalDateTime subDate, LocalDate courseStartDate, LocalDate courseEndDate) {
        LocalDate chosenDate = subDate.toLocalDate();
        if (chosenDate.isAfter(courseStartDate) && chosenDate.isBefore(courseEndDate)) {
            return subDate;
        } else {
            System.out.println("The Submission date time should be after the Start Date and before the End Date of the Course!");
            System.out.println("For this specific course, the Start date is " + courseStartDate + " and the end date is: " + courseEndDate);
            System.out.print("Please insert a date time in between the Start and End dates: ");
            return validateAssignmentSubDate(stringToLocalDateTime(sc.next()), courseStartDate, courseEndDate);
        }
    }

    protected static boolean checkIfStudentPerCourseExists(int studentId, int courseId) {
        boolean exists = false;
        ArrayList<Course> courses = getCoursesOfStudent(studentId);
        for (Course course : courses) {
            if (course.getCourseId() == courseId) {
                exists = true;
            }
        }
        return exists;
    }

    protected static boolean checkIfAssignmentPerCourseExists(int assignmentId, int courseId) {
        boolean exists = false;
        ArrayList<Assignment> assignments = getAssignmentsPerCourse(courseId);
        for (Assignment assignment : assignments) {
            if (assignment.getAssignmentId() == assignmentId) {
                exists = true;
            }
        }
        return exists;
    }

    protected static boolean checkIfTrainerPerCourseExists(int trainerId, int courseId) {
        boolean exists = false;
        ArrayList<Trainer> trainers = getTrainersPerCourse(courseId);
        for (Trainer trainer : trainers) {
            if (trainer.getTrainerId() == trainerId) {
                exists = true;
            }
        }
        return exists;
    }

    protected static boolean checkDublicateStudent(Student studentToCheck) {
        boolean exists = false;
        ArrayList<Student> students = getAllStudents();
        for (Student student : students) {
            if (studentToCheck.getStudentFirstName().equalsIgnoreCase(student.getStudentFirstName())
                    && studentToCheck.getStudentLastName().equalsIgnoreCase(student.getStudentLastName())
                    && studentToCheck.getDateOfBirth().isEqual(student.getDateOfBirth())) {
                exists = true;
            }
        }
        return exists;
    }

    protected static boolean checkDublicateCourse(Course courseToCheck) {
        boolean exists = false;
        ArrayList<Course> courses = getAllCourses();
        for (Course course : courses) {
            if (courseToCheck.getCourseTitle().equalsIgnoreCase(course.getCourseTitle())
                    && courseToCheck.getCourseType().equalsIgnoreCase(course.getCourseType())
                    && courseToCheck.getCourseStream().equalsIgnoreCase(course.getCourseStream())) {
                exists = true;
            }
        }
        return exists;
    }

    protected static boolean checkDublicateTrainer(Trainer trainerToCheck) {
        boolean exists = false;
        ArrayList<Trainer> trainers = getAllTrainers();
        for (Trainer trainer : trainers) {
            if (trainerToCheck.equals(trainer)) {
                exists = true;
            }
        }
        return exists;
    }

    protected static boolean checkDublicateAssignment(Assignment assignmentToCheck) {
        boolean exists = false;
        ArrayList<Assignment> assignments = getAllAssignments();
        for (Assignment assignment : assignments) {
            if (assignmentToCheck.equals(assignment)) {
                exists = true;
            }
        }
        return exists;
    }

    protected static boolean validPersonalMarks(int assignmentId, Marks marks) {
        Assignment asgnmnt = new Assignment();
        ArrayList<Assignment> assignmentsList = getAllAssignments();

        for (Assignment assignment : assignmentsList) {
            if (assignment.getAssignmentId() == assignmentId) {
                asgnmnt = assignment;
            }
        }

        if (asgnmnt.getOralMark() < marks.getpOralMark() || asgnmnt.getTotalMark() < marks.getpTotalMark()) {
            return true;
        } else {
            return false;
        }
    }

    protected static boolean validAssignmentMarks(double oralMark, double totalMark) {
        if (oralMark >= totalMark) {
            System.out.println("Oral Mark cannot be equal or greater than Total Mark.");
            return true;
        } else {
            return false;
        }
    }

}
