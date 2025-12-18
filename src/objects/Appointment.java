package objects;

public class Appointment {
    private String date;
    private String time;
    private String reason;

    private Pet pet;
    private Owner owner;
    private Veterinarian veterinarian;

    // CONSTRUCTOR
    public Appointment(String date, String time, String reason, Pet pet, Owner owner, Veterinarian veterinarian) {
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.pet = pet;
        this.owner = owner;
        this.veterinarian = veterinarian;
    }

    //DEFAULT_CONSTRUCTOR
    public Appointment() {
        this.date = "Unknown";
        this.time = "Unknown";
        this.reason = "Not specified";
        this.pet = null;
        this.owner = null;
        this.veterinarian = null;
    }

    // SETTERS
    public void setDate(String date){
        this.date = date;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public void setPet(Pet pet){
        this.pet = pet;
    }
    public void setOwner(Owner owner){
        this.owner = owner;
    }
    public void setVeterinarian(Veterinarian veterinarian){
        this.veterinarian = veterinarian;
    }

    //GETTERS
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getReason() {
        return reason;
    }

    public Pet getPet() {
        return pet;
    }

    public Owner getOwner() {
        return owner;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    @Override
    public String toString() {
        return "objects.Appointment{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", reason='" + reason + '\'' +
                ", pet=" + pet +
                ", owner=" + owner +
                ", veterinarian=" + veterinarian +
                '}';
    }

    // ADDITIONAL METHODS
    public void reschedule(String newDate, String newTime) {
        if (newDate != null && !newDate.isEmpty() && newTime != null && !newTime.isEmpty()) {
            this.date = newDate;
            this.time = newTime;
        }
    }

    public boolean isToday(String todayDate) {
        return this.date.equals(todayDate);
    }
}
