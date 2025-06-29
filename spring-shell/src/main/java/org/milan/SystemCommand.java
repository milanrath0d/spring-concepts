package org.milan;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

/**
 * Commands class
 *
 * @author Milan Rathod
 */
@ShellComponent
public class SystemCommand {

    private boolean connected;

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
