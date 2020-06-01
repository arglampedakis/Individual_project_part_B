package entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author glamb
 */
public class Course {

    private int courseId;
    private String courseTitle;
    private String courseType;
    private String courseStream;
    private LocalDate courseStartDate;
    private LocalDate courseEndDate;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseStream() {
        return courseStream;
    }

    public void setCourseStream(String courseStream) {
        this.courseStream = courseStream;
    }

    public LocalDate getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(LocalDate courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public LocalDate getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(LocalDate courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    @Override
    public String toString() {
        return "Title: " + courseTitle + ", Type: " + courseType + ", Stream: " + courseStream + ", Start Date: " + courseStartDate + ", End Date: " + courseEndDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (!Objects.equals(this.courseTitle.toLowerCase(), other.courseTitle.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.courseType.toLowerCase(), other.courseType.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.courseStream.toLowerCase(), other.courseStream.toLowerCase())) {
            return false;
        }
        return true;
    }

}
