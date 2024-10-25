import com.tngtech.jgiven.junit5.ScenarioTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class JGivenRoomManagerBookingTest extends ScenarioTest<JGivenRoomManagerSteps, JGivenRoomManagerSteps, JGivenRoomManagerSteps> {

    @Test
    public void test_room_booking_scenario() {
        LocalDateTime startTime = LocalDateTime.now().plusDays(2); // Set a valid start time 2 tagen ab jetzt
        LocalDateTime endTime = startTime.plusHours(2); // Duration is 2 stunde

        given().a_room_manager()
                .and().an_available_room_named_$with_capacity_$located_$with_equipment$("Room A", 10, "1st Floor", "Projector, Whiteboard");

        when().the_room_is_booked_from_$to$(startTime, endTime);

        then().booking_should_succeed_with_message_$("Room Room A successfully booked from " + startTime + " to " + endTime + ".");
    }
}
