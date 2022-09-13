package com.code5150;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void naiveCorrect() {
        var foobar = new Foobar();
        assertEquals(foobar.naiveFooBar(15),
                "1\n2\nFoo\n4\nBar\nFoo\n7\n8\nFoo\nBar\n11\nFoo\n13\n14\nFooBar\n");
    }
    @Test
    public void naiveEqualsStream() {
        var foobar = new Foobar();
        assertEquals(foobar.naiveFooBar(100), foobar.naiveStreamFooBar(100));
        assertEquals(foobar.naiveFooBar(1000), foobar.naiveStreamFooBar(1000));
        assertEquals(foobar.naiveFooBar(10000), foobar.naiveStreamFooBar(10000));
        assertEquals(foobar.naiveFooBar(75757653), foobar.naiveStreamFooBar(75757653));
    }

    @Test
    public void naiveEqualsParallel() {
        var foobar = new Foobar();
        assertEquals(foobar.naiveFooBar(100), foobar.multithreadedFooBar(100));
        assertEquals(foobar.naiveFooBar(1000), foobar.multithreadedFooBar(1000));
        assertEquals(foobar.naiveFooBar(10000), foobar.multithreadedFooBar(10000));
        assertEquals(foobar.naiveFooBar(75757653), foobar.multithreadedFooBar(75757653));
    }

    @Test
    public void naiveEqualsPattern() {
        var foobar = new Foobar();
        assertEquals(foobar.naiveFooBar(100), foobar.patternFooBar(100));
        assertEquals(foobar.naiveFooBar(1000), foobar.patternFooBar(1000));
        assertEquals(foobar.naiveFooBar(10000), foobar.patternFooBar(10000));
        assertEquals(foobar.naiveFooBar(75757653), foobar.patternFooBar(75757653));
    }
}
