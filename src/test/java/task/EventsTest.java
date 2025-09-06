package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bossexceptions.BossException;
import commands.CommandsEnum;

/**
 * Test for the task command: event
 */
public class EventsTest {
    /**
     * Test that Event task created successfully
     *
     * @throws BossException If error encountered during parsing of task
     */
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

    /** Test that Event task created with invalid format throws BossException */
    @Test
    public void parseTask_eventTaskInvalidFormat_throwsBossException() {
        String[] testFormatStrings = {"project meeting from Mon 2pm to 4pm",
            "project meeting Mon 2pm /to 4pm"
        };

        String invalidFormatMessage = "Invalid format for event task.";
        for (String s: testFormatStrings) {
            BossException e = assertThrows(BossException.class, () -> Task.parseTask(CommandsEnum.EVENT, s));

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidFormatMessage));
        }
    }

    /** Test that Event task created with invalid date format throws BossException */
    @Test
    public void parseTask_eventTaskInvalidDateFormat_throwsBossException() {
        String[] testDateStrings = {"project meeting /from Mon 2pm to 4pm",
            "project meeting /from Mon 2pm-3pm",
        };

        String invalidDateMessage = "Invalid format for start and end date/timings.";
        for (String s: testDateStrings) {
            BossException e = assertThrows(BossException.class, () -> Task.parseTask(CommandsEnum.EVENT, s));

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidDateMessage));
        }
    }
}
