package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import bossexceptions.BossException;
import commands.CommandsEnum;

public class TaskTest {
    @Test
    public void parseTask_createsCorrectTaskStatus_success() throws BossException {
        String input = "project meeting /from 2025-02-11 2pm /to 4pm";
        Task doneEvent = Task.parseTask(CommandsEnum.EVENT, true, input);
        Task notDoneEvent = Task.parseTask(CommandsEnum.EVENT, false, input);

        assertEquals(
                "[E][X] project meeting (from: 2025-02-11 2pm to: 4pm)"
                , doneEvent.toString());

        assertEquals(
                "[E][ ] project meeting (from: 2025-02-11 2pm to: 4pm)"
                , notDoneEvent.toString());
    }

    @Test
    public void parseTask_taskInfoIsBlank_throwsBossException() {
        BossException e = assertThrows(BossException.class,
                () -> Task.parseTask(CommandsEnum.EVENT, "")
        );

        String expectedMessage = "Please enter a description for a EVENT task.";
        String actualMessage = e.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
