package domain;

public class LicensePlateResult {

    private LicensePlate plate;
    private double precent;

    @Override
    public String toString() {
        return "LicensePlateResult{" +
                "plate=" + plate +
                ", precent=" + precent +
                '}';
    }

    public LicensePlate getPlate() {
        return plate;
    }

    public double getPercent() {
        return precent;
    }

    public LicensePlateResult(String s){
        String[] a = s.split(" ");
        if(a.length != 2){
            throw new IllegalArgumentException("Faulty String");
        }
        plate = new LicensePlate(a[0]);
        try{
            precent = Double.parseDouble(a[1]);
        }catch (NumberFormatException e){
            precent = 0.0;
        }
    }
}
