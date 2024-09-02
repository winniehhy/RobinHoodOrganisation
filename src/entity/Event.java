package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Phoon Chong Seng
 */
public class Event {
    private String EventID,EventName, EventDetail;
    private Date EventDate;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public Event(String EventID, String EventName, String EventDetail, Date EventDate) {
        this.EventID = EventID;
        this.EventName = EventName;
        this.EventDetail = EventDetail;
        this.EventDate = EventDate;
    }

    public Event(){
        
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    public String getEventDetail() {
        return EventDetail;
    }

    public void setEventDetail(String EventDetail) {
        this.EventDetail = EventDetail;
    }

    public String getEventID() {
        return EventID;
    }

    public void setEventID(String EventID) {
        this.EventID = EventID;
    }

    public Date getEventDate() {
        return EventDate;
    }

    public void setEventDate(Date EventDate) {
        this.EventDate = EventDate;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }
    
    public String toTable(){
        return String.format("%-10s%-20s%-30s%-30s",EventID,EventName,EventDetail,EventDate);
    }
    
    @Override
    public String toString(){
        return "Event ID: "+ EventID + "\n" +
                "Event Name: "+ EventName + "\n" +
                "Event Detail: "+ EventDetail + "\n"+
                "Event Date: "+ EventDate + "\n";
    }
}
