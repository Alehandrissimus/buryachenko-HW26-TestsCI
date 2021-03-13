package com.example.buryachenko_hw22_arch

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class FailingTest {

    @Test
    fun `some test`(a: Int = 10) {
        assertEquals(19, 0)
    }

}