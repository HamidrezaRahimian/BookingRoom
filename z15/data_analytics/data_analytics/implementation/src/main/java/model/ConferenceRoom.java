package model;

public class ConferenceRoom {
    private String name;
    private int capacity;
    private boolean isBooked;
    private String equipment;
    private String location;

    public ConferenceRoom(String name, int capacity, String equipment, String location) {
        this.name = name;
        this.capacity = capacity;
        this.isBooked = false;
        this.equipment = equipment;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        this.isBooked = true;
    }

    public void releaseRoom() {
        this.isBooked = false;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return name + " (Capacity: " + capacity + ", Equipment: " + equipment + ", Location: " + location + ", Booked: " + isBooked + ")";
    }
}
