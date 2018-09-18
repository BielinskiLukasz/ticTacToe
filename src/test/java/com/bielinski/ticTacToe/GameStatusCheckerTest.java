package com.bielinski.ticTacToe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameStatusCheckerTest {

    @Test
    public void shouldReturnTrueWhenCheckWinnerForXXXOO_O__Board() {
        //given
        int[] checkBoard = {
                1, 1, 1,
                -1, -1, 0,
                -1, 0, 0
        }; //TODO Replace 1 and -1 with PLAYER signs (Whole project)!
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCheckWinnerForOO_XXX_O_Board() {
        //given
        int[] checkBoard = {
                -1, -1, 0,
                1, 1, 1,
                0, -1, 0
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCheckWinnerForOO_O__XXXBoard() {
        //given
        int[] checkBoard = {
                -1, -1, 0,
                -1, 0, 0,
                1, 1, 1
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCheckWinnerForXOOXO_XXOBoard() {
        //given
        int[] checkBoard = {
                1, -1, -1,
                1, -1, 0,
                1, 1, -1
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCheckWinnerForOXOOXX_XOBoard() {
        //given
        int[] checkBoard = {
                -1, 1, -1,
                -1, 1, 1,
                0, 1, -1
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCheckWinnerForXOXOOXO_XBoard() {
        //given
        int[] checkBoard = {
                1, -1, 1,
                -1, -1, 1,
                -1, 0, 1
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCheckWinnerForXOOOXXOOXBoard() {
        //given
        int[] checkBoard = {
                1, -1, -1,
                -1, 1, 1,
                -1, -1, 1
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCheckWinnerFor_OXOX_X__Board() {
        //given
        int[] checkBoard = {
                0, -1, 1,
                -1, 1, 0,
                1, 0, 0
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCheckWinnerForXX_OOOX__Board() {
        //given
        int[] checkBoard = {
                1, 1, 0,
                -1, -1, -1,
                1, 0, 0
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenCheckWinnerFor_________Board() {
        //given
        int[] checkBoard = {
                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenCheckWinnerForX_X_O_X_XBoard() {
        //given
        int[] checkBoard = {
                1, 0, 1,
                0, -1, 0,
                1, 0, 1
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isFalse();
    }

    @Test
    public void shouldReturnTrueWhenCheckDrawForX_X_O_X_XBoard() {
        //given
        int[] checkBoard = {
                1, -1, 1,
                -1, -1, 1,
                1, 1, -1
        };
        //when
        boolean isDraw = GameStatusChecker.isDraw(checkBoard);
        //then
        assertThat(isDraw).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenCheckDrawFor_________BoardWhen() {
        //given
        int[] checkBoard = {
                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        };
        //when
        boolean isDraw = GameStatusChecker.isDraw(checkBoard);
        //then
        assertThat(isDraw).isFalse();
    }

    @Test
    public void shouldReturnFalseWhenCheckDrawForX___O___OBoard() {
        //given
        int[] checkBoard = {
                1, 0, 0,
                0, -1, 0,
                0, 0, -1
        };
        //when
        boolean isDraw = GameStatusChecker.isDraw(checkBoard);
        //then
        assertThat(isDraw).isFalse();
    }


}