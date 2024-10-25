import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConferenceRoomTest {
    private ConferenceRoom conferenceRoom;

    @BeforeEach
    public void setUp() {
        conferenceRoom = new ConferenceRoom("Room A", 10, "Projector, Whiteboard", "1st Floor");
    }

    @Test
    public void testGetName() {
        assertEquals("Room A", conferenceRoom.getName());
    }

    @Test
    public void testGetCapacity() {
        assertEquals(10, conferenceRoom.getCapacity());
    }

    @Test
    public void testGetEquipment() {
        assertEquals("Projector, Whiteboard", conferenceRoom.getEquipment());
    }

    @Test
    public void testGetLocation() {
        assertEquals("1st Floor", conferenceRoom.getLocation());
    }

    @Test
    public void testBookRoom() {
        conferenceRoom.bookRoom();
        assertTrue(conferenceRoom.isBooked());
    }

    @Test
    public void testReleaseRoom() {
        conferenceRoom.bookRoom();
        conferenceRoom.releaseRoom();
        assertFalse(conferenceRoom.isBooked());
    }

    @Test
    public void testToString() {
        String expected = "Room A (Capacity: 10, Equipment: Projector, Whiteboard, Location: 1st Floor, Booked: false)";
        assertEquals(expected, conferenceRoom.toString());
    }
}
