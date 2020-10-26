package Tests;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class Test_Testing {
    public int a;
    public int b;

    @Before
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
