package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import bossexceptions.BossException;
import commands.CommandsEnum;

/**
 * Test for the task command: todo
 */
public class ToDosTest {
    /**
     * Test that ToDo task created successfully
     *
     * @throws BossException If error encountered during parsing of task
     */
    @Test
    public void parseTask_createTodoTask_success() throws BossException {
        try {
            Task todo = Task.parseTask(
                    CommandsEnum.TODO, false, "borrow book"
            );

            assertEquals("[T][ ] borrow book", todo.toString());
        } catch (BossException e) {
            throw new BossException("exception thrown in parseTask_createDeadlineTask_success: " + e.getMessage());
        }
    }
}
