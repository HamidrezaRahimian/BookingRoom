import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParticipantTest {
    private Participant participant;

    @BeforeEach
    public void setUp() {
        participant = new Participant("Alice Brown", "alice.brown@example.com");
    }

    @Test
    public void testGetName() {
        assertEquals("Alice Brown", participant.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("alice.brown@example.com", participant.getEmail());
    }

    @Test
    public void testNotifyBooking() {
        participant.notifyBooking("Room A", "Strategy Meeting");
        // Notification is printed in the console; no return value to assert
    }
}
