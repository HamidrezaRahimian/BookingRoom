import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private ConferenceRoom room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String reserver;
    private String meetingPurpose;
    private List<Participant> participants;

    public Booking(ConferenceRoom room, LocalDateTime startTime, LocalDateTime endTime, String reserver, String meetingPurpose) {
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reserver = reserver;
        this.meetingPurpose = meetingPurpose;
        this.participants = new ArrayList<>();
    }

    public ConferenceRoom getRoom() {
        return room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getReserver() {
        return reserver;
    }

    public String getMeetingPurpose() {
        return meetingPurpose;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
        participant.notifyBooking(room.getName(), meetingPurpose);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "room=" + room.getName() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", reserver='" + reserver + '\'' +
                ", purpose='" + meetingPurpose + '\'' +
                ", participants=" + participants.size() +
                '}';
    }
}
