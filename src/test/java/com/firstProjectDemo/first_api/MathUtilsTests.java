package com.firstProjectDemo.first_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTests {

    @Test
    void testAdd(){
        MathUtils mathUtils=new MathUtils();
        int expected=2;
        int actual=mathUtils.add(1,1);
        assertEquals(expected,actual);
        System.out.println("This test passed");
    }

    @Test
    void testComputerCircleRadius(){
        MathUtils mathUtils=new MathUtils();
        assertEquals( 314.1592653589793,mathUtils.computeCircleArea(10),"Should give back the area of circle on the given radius at it");
    }

    @Test
    void testDivide(){
        MathUtils mathUtils=new MathUtils();
        assertThrows(ArithmeticException.class,()->mathUtils.divide(1,0),"Divide by 0 should throw");
    }
}
