package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import bossexceptions.BossException;
import commands.CommandsEnum;

/**
 * Test for the task command: deadline
 */
public class DeadlinesTest {
    /**
     * Test that Deadline task created successfully
     *
     * @throws BossException If error encountered during parsing of task
     */
    @Test
    public void parseTask_createDeadlineTask_success() throws BossException {
        try {
            Task deadline = Task.parseTask(
                    CommandsEnum.DEADLINE, true, "return book /by 01-11-2022 1pm"
            );

            assertEquals("[D][X] return book (by: 2022-11-01 1pm)", deadline.toString());
        } catch (BossException e) {
            throw new BossException("exception thrown in parseTask_createDeadlineTask_success: " + e.getMessage());
        }
    }

    /** Test that Deadline task created with invalid format throws BossException */
    @Test
    public void parseTask_deadlineInvalidFormat_throwsBossException() {
        String[] testFormatStrings = {"return book", // not /by keyword
            "return book /by" // no dates
        };

        String invalidFormatMessage = "Invalid format for deadline task.";
        for (String s: testFormatStrings) {
            BossException e = assertThrows(BossException.class, () -> Task.parseTask(CommandsEnum.DEADLINE, s));

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidFormatMessage));
        }
    }

    /** Test that Deadline task created with invalid date format throws BossException */
    @Test
    public void parseTask_deadlineInvalidDateFormat_throwsBossException() {
        String[] testDateStrings = {"return book /by Monday 11pm",
            "return book /by 2022-11-01"
        };

        String invalidDateMessage = "Invalid format for date and time.";
        for (String s: testDateStrings) {
            BossException e = assertThrows(BossException.class, () -> Task.parseTask(CommandsEnum.DEADLINE, s));

            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(invalidDateMessage));
        }
    }
}
