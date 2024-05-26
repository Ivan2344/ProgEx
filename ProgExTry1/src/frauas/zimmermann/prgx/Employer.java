package frauas.zimmermann.prgx;

import java.sql.Date;

public class Employer {
    private int employer_id;
    private String employer_name;
    private String address;
    private String email;
    private String phone_number;
    private String industry;
    private Date established_date;

    // Getters and Setters
    public int getEmployerId() {
        return employer_id;
    }

    public void setEmployerId(int employer_id) {
        this.employer_id = employer_id;
    }

    public String getEmployerName() {
        return employer_name;
    }

    public void setEmployerName(String employer_name) {
        this.employer_name = employer_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Date getEstablishedDate() {
        return established_date;
    }

    public void setEstablishedDate(Date established_date) {
        this.established_date = established_date;
    }
}