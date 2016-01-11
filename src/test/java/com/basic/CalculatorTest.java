package com.basic;




import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Created by fcj on 16/1/10.
 */
public class CalculatorTest {

    @Mock
    private Calculator calculator;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);


        //   calculator = new Calculator();
    }

    @Test
    public void testAbs(){

/*        int expected = 4;
        int actual = calculator.abs(-4);
        assertEquals(expected,actual);*/
        when(calculator.abs(-20)).thenReturn(20);
        assertEquals(20,calculator.abs(-20));
    }


}
