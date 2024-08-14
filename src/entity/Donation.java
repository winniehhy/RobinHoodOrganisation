package entity;

import java.util.Date;

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

    public Donation() {
    }
    
    public Donation(String donorName, String doneeName, int amount, Date donationDate, Date distributionDate) {
        this.donorName = donorName;
        this.doneeName = doneeName;
        this.amount = amount;
        this.donationDate = donationDate;
        this.distributionDate = distributionDate;
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

    @Override
    public String toString() {
        return "Distributions [" +
                "Donor: " + donorName +
                ", Donee: " + doneeName +
                ", Amount: RM " + amount +
                ", Donation Date: " + donationDate +
                ", Distribution Date: " + distributionDate +
                ']';
    }
}
