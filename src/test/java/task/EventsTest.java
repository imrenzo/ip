package task;

import bossexceptions.BossException;
import commands.CommandsEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventsTest {
    @Test
    public void parseTask_createEventTask_success() throws BossException {
        try {
            Task event = Task.parseTask(
                    CommandsEnum.EVENT, false, "project meeting /from Mon 2pm /to 4pm"
            );

            assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", event.toString());
        } catch (BossException e) {
            throw new BossException("exception thrown in parseTask_createDeadlineTask_success: " + e.getMessage());
        }
    }

    @Test
    public void parseTask_eventTaskInvalidFormat_throwsBossException() {
        String[] testFormatStrings = {"project meeting from Mon 2pm to 4pm",
                "project meeting Mon 2pm /to 4pm"
        };

        String invalidFormatMessage = "Invalid format for event task.";
        for (String s: testFormatStrings) {
            BossException e = assertThrows(BossException.class,
                    () -> Task.parseTask(CommandsEnum.EVENT, s)
            );

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidFormatMessage));
        }
    }

    @Test
    public void parseTask_eventTaskInvalidDateFormat_throwsBossException() {
        String[] testDateStrings = {"project meeting /from Mon 2pm to 4pm",
                "project meeting /from Mon 2pm-3pm",
        };

        String invalidDateMessage = "Invalid format for start and end date/timings.";
        for (String s: testDateStrings) {
            BossException e = assertThrows(BossException.class,
                    () -> Task.parseTask(CommandsEnum.EVENT, s)
            );

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidDateMessage));
        }
    }
}
