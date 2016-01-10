package com.basic;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Created by fcj on 16/1/10.
 */
public class CalculatorTest {


    Calculator calculator;

    @Before
    public void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void testAbs(){

        int expected = 4;
        int actual = calculator.abs(-4);
        assertEquals(expected,actual);

    }


}
