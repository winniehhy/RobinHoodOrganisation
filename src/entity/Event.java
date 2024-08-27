/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Phoon Chong Seng
 */
public class Event {
    private String EventName, EventDetail, EventID;
    private Date EventDate;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public Event(String EventName, String EventDetail, String EventID, Date EventDate) {
        this.EventName = EventName;
        this.EventDetail = EventDetail;
        this.EventID = EventID;
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
        return String.format("%-10s%-20s%-20s%-30s",EventID,EventName,EventDetail,EventDate);
    }
    
    @Override
    public String toString(){
        return "Event ID: "+ EventID + "\n" +
                "Event Name: "+ EventName + "\n" +
                "Event Detail: "+ EventDetail + "\n"+
                "Event Date: "+ EventDate + "\n";
    }
}
