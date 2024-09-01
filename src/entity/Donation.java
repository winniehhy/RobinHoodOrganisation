package entity;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @ author Ho Zhi Xuen
 */
public class Donation {
    private String donorName;
    private String doneeName;
    private int amount;
    private Date donationDate;
    private Date distributionDate;
    private int donationType; //new

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public Donation() {
    }
    
    public Donation(String donorName, String doneeName, int amount, Date donationDate, Date distributionDate) {
        this.donorName = donorName;
        this.doneeName = doneeName;
        this.amount = amount;
        this.donationDate = donationDate;
        this.distributionDate = distributionDate;
    }

    public Donation(Donation other) {
        this.donorName = other.donorName;
        this.doneeName = other.doneeName;
        this.amount = other.amount;
        this.donationDate = other.donationDate;
        this.distributionDate = other.distributionDate;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDoneeName() {
        return doneeName;
    }

    public void setDoneeName(String doneeName) {
        this.doneeName = doneeName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public Date getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(Date distributionDate) {
        this.distributionDate = distributionDate;
    }
    
    public int getDonationType() {
        return donationType;
    }

    public void setDonationType(int donationType) {
        this.donationType = donationType;
    }

    public String displayTable() {
        return String.format(
            " | %-20s | %-20s | %-8d | %-17s | %-17s |",
            donorName,
            doneeName != null ? doneeName : "N/A",
            amount,
            donationDate != null ? formatter.format(donationDate) : "N/A",
            distributionDate != null ? formatter.format(distributionDate) : "N/A"
        );
    } 

    @Override
    public String toString() {
        return "[" +
                "Donor: " + donorName +
                ", Donee: " + (doneeName != null ? doneeName : "N/A") +
                ", Amount: RM " + amount +
                ", Donation Date: " + (donationDate != null ? formatter.format(donationDate) : "N/A") +
                ", Distribution Date: " + (distributionDate != null ? formatter.format(distributionDate) : "N/A") +
                ']';
    }

    public String getDonationDetails() {
        return "Donor: " + donorName +
                ", Donation Date: " + (donationDate != null ? formatter.format(donationDate) : "N/A") +
                ", Amount: RM " + amount;
    }
}
