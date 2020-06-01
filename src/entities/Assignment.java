package entities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author glamb
 */
public class Assignment {

    private int assignmentId;
    private String assignementTitle;
    private String description;
    private double oralMark;
    private double totalMark;
    private LocalDateTime subDateTime;

    public LocalDateTime getSubDateTime() {
        return subDateTime;
    }

    public void setSubDateTime(LocalDateTime subDateTime) {
        this.subDateTime = subDateTime;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignementTitle() {
        return assignementTitle;
    }

    public void setAssignementTitle(String assignementTitle) {
        this.assignementTitle = assignementTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOralMark() {
        return oralMark;
    }

    public void setOralMark(double oralMark) {
        this.oralMark = oralMark;
    }

    public double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(double totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public String toString() {
        return "Title: " + assignementTitle + ", Description: " + description + ", Oral Mark: " + oralMark + ", Total Mark: " + totalMark;
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
        final Assignment other = (Assignment) obj;
        if (!Objects.equals(this.assignementTitle.toLowerCase(), other.assignementTitle.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.description.toLowerCase(), other.description.toLowerCase())) {
            return false;
        }
        return true;
    }

}
