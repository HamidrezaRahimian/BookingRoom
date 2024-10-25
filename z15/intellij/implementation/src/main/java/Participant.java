public class Participant {
    private String name;
    private String email;

    public Participant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void notifyBooking(String roomName, String meetingPurpose) {
        System.out.println("Notification: " + name + ", you have been invited to a meeting in " + roomName + " for: " + meetingPurpose);
    }
}
