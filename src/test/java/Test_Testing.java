import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Test_Testing {
    public int a;
    public int b;

    @BeforeAll
    public void prep(){
        a = 3;
        b = 6;
    }

    @Test
    public void my_test_assert_true(){
        assertTrue(6 == 6);
    }

    @Test
    public void my_test_assert_true_before(){
        assertTrue(a != 6);
    }

    @Test
    public void my_test_assert_not_equal(){
        assertNotEquals(a,6);
    }

    @Test
    public void my_test_assert_equal(){
        assertEquals(b,6);
    }
}
