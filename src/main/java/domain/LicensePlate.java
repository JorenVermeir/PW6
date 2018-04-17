package domain;

import java.util.Objects;

public class LicensePlate {;
    private String LicensePlate;

    public LicensePlate(String LicensePlate){
        setLicensePlate(LicensePlate);
    }

    public void setLicensePlate(String Licenseplate){
        this.LicensePlate = Licenseplate;
    }

    @Override
    public String toString() {
        return LicensePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicensePlate that = (LicensePlate) o;
        return Objects.equals(LicensePlate, that.LicensePlate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(LicensePlate);
    }
}
