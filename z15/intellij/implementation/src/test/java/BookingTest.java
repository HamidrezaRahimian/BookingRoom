import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

public class BookingTest {
    private Booking booking;
    private ConferenceRoom room;
    private Participant participant;

    @BeforeEach
    public void setUp() {
        room = Mockito.mock(ConferenceRoom.class);
        when(room.getName()).thenReturn("Room B");
        when(room.getCapacity()).thenReturn(15);
        when(room.getEquipment()).thenReturn("Teleconferencing");
        when(room.getLocation()).thenReturn("2nd Floor");

        LocalDateTime startTime = LocalDateTime.now().plusDays(1);
        LocalDateTime endTime = startTime.plusHours(2);
        booking = new Booking(room, startTime, endTime, "Jane Doe", "Team Meeting");
        participant = new Participant("John Smith", "john.smith@example.com");
    }

    @Test
    public void testGetRoom() {
        assertEquals(room, booking.getRoom());
    }

    @Test
    public void testGetStartTime() {
        assertNotNull(booking.getStartTime());
    }

    @Test
    public void testGetEndTime() {
        assertNotNull(booking.getEndTime());
    }

    @Test
    public void testGetReserver() {
        assertEquals("Jane Doe", booking.getReserver());
    }

    @Test
    public void testGetMeetingPurpose() {
        assertEquals("Team Meeting", booking.getMeetingPurpose());
    }

    @Test
    public void testAddParticipant() {
        booking.addParticipant(participant);
        assertEquals(1, booking.getParticipants().size());
        assertEquals(participant, booking.getParticipants().get(0));
    }

    @Test
    public void testToString() {
        String expected = "Booking{room=Room B, startTime=" + booking.getStartTime() + ", endTime=" + booking.getEndTime() + ", reserver='Jane Doe', purpose='Team Meeting', participants=0}";
        assertEquals(expected, booking.toString());
    }
}
