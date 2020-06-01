package entities;

import java.util.Objects;

/**
 *
 * @author glamb
 */
public class Trainer {

    private int trainerId;
    private String trainerFirstName;
    private String trainerLastName;
    private String subject;

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getTrainerFirstName() {
        return trainerFirstName;
    }

    public void setTrainerFirstName(String trainerFirstName) {
        this.trainerFirstName = trainerFirstName;
    }

    public String getTrainerLastName() {
        return trainerLastName;
    }

    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "First Name: " + trainerFirstName + ", Last Name: " + trainerLastName + ", Subject: " + subject;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Trainer other = (Trainer) obj;
        if (!Objects.equals(this.trainerFirstName.toLowerCase(), other.trainerFirstName.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.trainerLastName.toLowerCase(), other.trainerLastName.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.subject.toLowerCase(), other.subject.toLowerCase())) {
            return false;
        }
        return true;
    }

}
