package com.UFV.prExt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitaryTestsTest {

    UnitaryTests prueba1 = new UnitaryTests();
    @Test
    void test1() {
        assertEquals(true, prueba1.test1(2,1));
    }
}