package com.bielinski.ticTacToe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class AIMinimaxTest {

    @Test
    public void shouldReturnMiddleElementWhenAllFieldsAreOccupied() {
        //given
        int minValue = Integer.MIN_VALUE;
        int[] fieldPointValue = new int[9];
        for (int i = 0; i < fieldPointValue.length; i++) {
            fieldPointValue[i] = minValue;
        }
        //when
        int bestFieldIndex = AIMinimax.findBestFieldIndex(fieldPointValue);
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
        int bestFieldIndex = AIMinimax.findBestFieldIndex(fieldPointValue);
        //then
        assertThat(bestFieldIndex).isEqualTo(1);
    }

    @Test
    public void shouldReturn4WhenStart() {
        //given
        int[] boardsFields = {
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertThat(bestFieldIndex).isEqualTo(4);
    }

    @Test
    public void shouldReturn4IfPlayerDidntStartThere() {
        //given
        int[] boardsFields = {
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertThat(bestFieldIndex).isEqualTo(4);
    }

    @Test
    public void shouldReturn4forAIToWinTheGame() {
        //given
        int[] boardsFields = {
                Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertThat(bestFieldIndex).isEqualTo(4);
    }

    @Test
    public void shouldReturn0WhenItsOnlyOneEmptyField() {
        //given
        int[] boardsFields = {
                Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE, Player.PLAYER_O_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_O_CODE, Player.PLAYER_X_CODE,
                Player.PLAYER_X_CODE, Player.PLAYER_X_CODE, Player.PLAYER_O_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertThat(bestFieldIndex).isEqualTo(0);
    }

    @Test
    public void shouldReturn2WhenItsOnlyOneEmptyField() {
        //given
        int[] boardsFields = {
                Player.PLAYER_O_CODE, Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_O_CODE, Player.PLAYER_X_CODE,
                Player.PLAYER_X_CODE, Player.PLAYER_X_CODE, Player.PLAYER_O_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertThat(bestFieldIndex).isEqualTo(2);
    }

    @Test
    public void shouldReturn3Or4forAIToWinTheGame() {
        //given
        int[] boardsFields = {
                Player.PLAYER_O_CODE, Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertTrue(bestFieldIndex == 3 || bestFieldIndex == 4);
    }

    @Test
    public void shouldReturn4toPreventThePlayerFromWinning() {
        //given
        int[] boardsFields = {
                Player.PLAYER_O_CODE, Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE,
                Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertThat(bestFieldIndex).isEqualTo(4);
    }

    @Test
    public void shouldReturn5toPreventThePlayerFromWinning() {
        //given
        int[] boardsFields = {
                Player.PLAYER_O_CODE, Player.PLAYER_X_CODE, Player.PLAYER_X_CODE,
                Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE, Player.PLAYER_X_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertThat(bestFieldIndex).isEqualTo(5);
    }

    @Test
    public void shouldReturn1Or7toPreventThePlayerFromWinning() {
        //given
        int[] boardsFields = {
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE,
                Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertTrue(bestFieldIndex == 1 || bestFieldIndex == 7);
    }

    @Test
    public void shouldReturn2Or6toPreventThePlayerFromWinning() {
        //given
        int[] boardsFields = {
                Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE
        };
        //when
        int bestFieldIndex = AIMinimax.chooseFieldForAI(boardsFields);
        //then
        assertTrue(bestFieldIndex == 2 || bestFieldIndex == 6);
    }
}
