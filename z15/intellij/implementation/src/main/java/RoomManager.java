import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private List<ConferenceRoom> rooms;
    private List<Booking> bookings;

    public RoomManager() {
        rooms = new ArrayList<>();
        bookings = new ArrayList<>();
        initializeRooms();
    }

    private void initializeRooms() {
        rooms.add(new ConferenceRoom("Room A", 10, "Projector, Whiteboard", "1st Floor"));
        rooms.add(new ConferenceRoom("Room B", 20, "Teleconferencing", "2nd Floor"));
        rooms.add(new ConferenceRoom("Room C", 15, "Video Equipment", "3rd Floor"));
    }

    public List<ConferenceRoom> getAvailableRooms(LocalDateTime startTime, LocalDateTime endTime) {
        List<ConferenceRoom> availableRooms = new ArrayList<>();
        for (ConferenceRoom room : rooms) {
            if (!room.isBooked()) {
                boolean isAvailable = true;
                for (Booking booking : bookings) {
                    if (booking.getRoom() == room &&
                            !(endTime.isBefore(booking.getStartTime()) || startTime.isAfter(booking.getEndTime()))) {
                        isAvailable = false;
                        break;
                    }
                }
                if (isAvailable) {
                    availableRooms.add(room);
                }
            }
        }
        return availableRooms;
    }

    public String bookRoom(ConferenceRoom room, LocalDateTime startTime, LocalDateTime endTime, String reserver, String meetingPurpose) {
        if (room.isBooked()) {
            return "Room is already booked!";
        }

        // Check if the booking is within 24 hours
        if (startTime.isBefore(LocalDateTime.now().plusHours(24))) {
            return "Bookings can only be made at least 24 hours in advance.";
        }

        room.bookRoom();
        Booking booking = new Booking(room, startTime, endTime, reserver, meetingPurpose);
        bookings.add(booking);
        return "Room " + room.getName() + " successfully booked from " + startTime + " to " + endTime + ".";
    }

    public String cancelBooking(ConferenceRoom room) {
        for (Booking booking : bookings) {
            if (booking.getRoom() == room) {
                if (LocalDateTime.now().isAfter(booking.getStartTime().minusHours(24))) {
                    return "Cancellations are only allowed at least 24 hours before the meeting.";
                }
                room.releaseRoom();
                bookings.remove(booking);
                return "Booking for room " + room.getName() + " has been canceled.";
            }
        }
        return "No booking found for this room.";
    }

    public List<ConferenceRoom> getRooms() {
        return rooms;
    }

    public List<ConferenceRoom> suggestRoomsForParticipants(int participantsCount) {
        List<ConferenceRoom> suggestedRooms = new ArrayList<>();
        for (ConferenceRoom room : rooms) {
            if (room.getCapacity() >= participantsCount) {
                suggestedRooms.add(room);
            }
        }
        return suggestedRooms;
    }
}
