package org.milan;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author Milan Rathod
 */
@ShellComponent
public class CalculatorCommand {

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
}
