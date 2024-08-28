package entity;


/**
 *
 * @author Phoon Chong Seng
 */
public class EventAssignment {
    private String eventID, volunteerID;

    public EventAssignment(String eventID, String volunteerID) {
        this.eventID = eventID;
        this.volunteerID = volunteerID;
    }

    public EventAssignment() {
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }
    
}
