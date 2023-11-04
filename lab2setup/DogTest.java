import static org.junit.Assert.*;
import org.junit.Test;

public class DogTest {
    Dog d = new Dog(3);
    @Test
    public void testSmall() {
        assertEquals("yip", d.noise());
    }

    @Test
    public void testLarge() {
        Dog d = new Dog(20);
        assertEquals("bark", d.noise());
    }
}
