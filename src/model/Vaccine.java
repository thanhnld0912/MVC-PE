package model;

import java.util.Date;
import java.util.Objects;
import java.text.SimpleDateFormat;
public class Vaccine {

    private String Name;
    private String vaccineCode;
    private int vaccineQuantity;
    private Date expirationDate;
    private double vaccinePrice;
    private Date lastInjectionDate;
    public Vaccine(String vaccineCode, String vaccineName, int vaccineQuantity, Date expirationDate, double vaccinePrice, Date lastInjectionDate) {
        this.vaccineCode = vaccineCode;
        this.Name = vaccineName;
        this.vaccineQuantity = vaccineQuantity;
        this.expirationDate = expirationDate;
        this.vaccinePrice = vaccinePrice;
        this.lastInjectionDate = lastInjectionDate;
    }
    public Vaccine() {

    }


    public String getName() {
        return Name;
    }

    public void setName(String vaccineName) {
        this.Name = vaccineName;
    }

    public String getVaccineCode() {
        return vaccineCode;
    }

    public void setVaccineCode(String vaccineCode) {
        this.vaccineCode = vaccineCode;
    }

    public int getVaccineQuantity() {
        return vaccineQuantity;
    }

    public void setVaccineQuantity(int vaccineQuantity) {
        this.vaccineQuantity = vaccineQuantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getVaccinePrice() {
        return vaccinePrice;
    }

    public void setVaccinePrice(double vaccinePrice) {
        this.vaccinePrice = vaccinePrice;
    }

    public Date getLastInjectionDate() {
        return lastInjectionDate;
    }

    public void setLastInjectionDate(Date lastInjectionDate) {
        this.lastInjectionDate = lastInjectionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vaccine vaccine = (Vaccine) o;
        return vaccineQuantity == vaccine.vaccineQuantity && Double.compare(vaccinePrice, vaccine.vaccinePrice) == 0 && Objects.equals(Name, vaccine.Name) && Objects.equals(vaccineCode, vaccine.vaccineCode) && Objects.equals(expirationDate, vaccine.expirationDate) && Objects.equals(lastInjectionDate, vaccine.lastInjectionDate);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        StringBuilder sb = new StringBuilder();
        sb.append("Code=").append(vaccineCode);
        sb.append(", Name=").append(Name);
        sb.append(", Quantity=").append(vaccineQuantity);
        sb.append(", Expiration Date=").append(dateFormat.format(expirationDate));
        sb.append(", Price=").append(vaccinePrice);
        sb.append(", Last Injection Date=").append(dateFormat.format(lastInjectionDate));

        return sb.toString();
    }
}
