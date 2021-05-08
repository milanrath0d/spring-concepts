package org.milan;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.Size;

/**
 * Commands class
 *
 * @author Milan Rathod
 */
@ShellComponent
public class Commands {

    private boolean connected;

    /**
     * Example of Key, Prefix option of ShellMethod annotation.
     */
    @ShellMethod(key = "sum", value = "Add two integers", prefix = "-")
    public int add(int a, @ShellOption(defaultValue = "2") int b) {
        return a + b;
    }

    /**
     * Parameter Arity
     */
    @ShellMethod(key = "mul", value = "Multiply two integers")
    public int multiply(@ShellOption(arity = 2) int[] numbers) {
        return numbers[0] * numbers[1];
    }

    /**
     * Bean Validation API
     */
    @ShellMethod("Change the Password")
    public String changePassword(@Size(min = 6, max = 40) String password) {
        return "success";
    }

    @ShellMethod(value = "connect to server", group = "connection")
    public void connect(@ShellOption(defaultValue = "test") String username, @ShellOption(defaultValue = "test") String password) {
        connected = true;
    }

    @ShellMethod(value = "disconnect from server", group = "connection")
    public void disconnect() {
        connected = false;
    }

    @ShellMethod("download from server")
    public void download() {

    }

    @ShellMethodAvailability({"download"})
    public Availability customAvailability() {
        return connected ? Availability.available() : Availability.unavailable("first connect to download");
    }
}
