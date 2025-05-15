import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.milan.CircularA;
import org.milan.CircularB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Test class for Circular Dependency
 *
 * @author Milan Rathod
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@Disabled
public class CircularDependencyTest {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public CircularA getCircularA() {
        return new CircularA();
    }

    @Bean
    public CircularB getCircularB() {
        return new CircularB();
    }

    @Test
    public void givenCircularDependency_whenConstructorInjection_thenItfails() {
        CircularA circularA = applicationContext.getBean(CircularA.class);

        Assertions.assertEquals("Hi", circularA.getCircularB().getMessage());
    }


}
