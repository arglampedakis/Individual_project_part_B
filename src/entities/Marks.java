package entities;

/**
 *
 * @author glamb
 */
public class Marks {

    private double pOralMark;
    private double pTotalMark;

    public double getpOralMark() {
        return pOralMark;
    }

    public void setpOralMark(double pOralMark) {
        this.pOralMark = pOralMark;
    }

    public double getpTotalMark() {
        return pTotalMark;
    }

    public void setpTotalMark(double pTotalMark) {
        this.pTotalMark = pTotalMark;
    }

    @Override
    public String toString() {
        return "Personal Oral Mark: " + pOralMark + ", Personal Total Mark: " + pTotalMark;
    }

}
