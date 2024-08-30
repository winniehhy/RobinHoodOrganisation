package entity;

/**
 *
 * @ author Lee Zun Wei
 */

public class Volunteer {
    String id;
    String name;
    String phone;
    int age;  

    public Volunteer(){
    }

    public Volunteer(String id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age; 
        this.phone = phone; 
    }

    public String getVolunteerID(){
        return id;
    }

    public void setVolunteerID(String id){
        this.id = id;
    }

    public String getVolunteerName(){
        return name;
    }

    public void setVolunteerName(String name){
        this.name = name;
    }

    public int getVolunteerAge(){
        return age;
    }

    public void setVolunteerAge(int age){
        this.age = age;
    }

    public String getVolunteerPhone(){
        return phone;
    }

    public void setVolunteerPhone(String phone){
        this.phone = phone;
    }

    public String displayVolunteerDetail(){
        return String.format("%-12s %-25s %-7d %-12s", id, name, age, phone);
    }

    @Override
    public String toString() {
        return String.format("%-12s %-25s %-7d %-12s", id, name, age, phone);
                /*", Assigned Event: " + (assignedEvent != null ? assignedEvent : "None" )*/
    }
}

