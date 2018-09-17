package com.bielinski.ticTacToe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AIminimaxTest {

    @Test
    public void shouldReturnMiddleElementWhenAllFieldsAreOccupied() {
        //given
        int minValue = Integer.MIN_VALUE;
        int[] fieldPointValue = new int[9];
        for (int i = 0; i < fieldPointValue.length; i++) {
            fieldPointValue[i] = minValue;
        }
        //when
        int bestFieldIndex = AIminimax.findBestFieldIndex(fieldPointValue);
        //then
        assertThat(bestFieldIndex).isEqualTo(fieldPointValue.length / 2);
    }

    @Test
    public void shouldReturn1WhenFieldWithIndex1HaveMaxValue() {
        //given
        int[] fieldPointValue = {
                256, 512, 256,
                128, 64, 32,
                4, 8, 16
        };
        //when
        int bestFieldIndex = AIminimax.findBestFieldIndex(fieldPointValue);
        //then
        assertThat(bestFieldIndex).isEqualTo(1);
    }

}