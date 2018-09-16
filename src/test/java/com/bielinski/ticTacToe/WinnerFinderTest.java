package com.bielinski.ticTacToe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinnerFinderTest {

    @Test
    public void shouldReturnTrueForXXXOO_O__Board() {
        //given
        int[] checkBoard = {
                1, 1, 1,
                -1, -1, 0,
                -1, 0, 0
        }; //TODO Replace 1 and -1 with PLAYER signs (Whole project)!
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueForOO_XXX_O_Board() {
        //given
        int[] checkBoard = {
                -1, -1, 0,
                1, 1, 1,
                0, -1, 0
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueForOO_O__XXXBoard() {
        //given
        int[] checkBoard = {
                -1, -1, 0,
                -1, 0, 0,
                1, 1, 1
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueForXOOXO_XXOBoard() {
        //given
        int[] checkBoard = {
                1, -1, -1,
                1, -1, 0,
                1, 1, -1
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueForOXOOXX_XOBoard() {
        //given
        int[] checkBoard = {
                -1, 1, -1,
                -1, 1, 1,
                0, 1, -1
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueForXOXOOXO_XBoard() {
        //given
        int[] checkBoard = {
                1, -1, 1,
                -1, -1, 1,
                -1, 0, 1
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueForXOOOXXOOXBoard() {
        //given
        int[] checkBoard = {
                1, -1, -1,
                -1, 1, 1,
                -1, -1, 1
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueFor_OXOX_X__Board() {
        //given
        int[] checkBoard = {
                0, -1, 1,
                -1, 1, 0,
                1, 0, 0
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueForXX_OOOX__Board() {
        //given
        int[] checkBoard = {
                1, 1, 0,
                -1, -1, -1,
                1, 0, 0
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnFalseFor_________Board() {
        //given
        int[] checkBoard = {
                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isFalse();
    }

    @Test
    public void shouldReturnFalseForX_X_O_X_XBoard() {
        //given
        int[] checkBoard = {
                1, 0, 1,
                0, -1, 0,
                1, 0, 1
        };
        //when
        boolean isWinner = WinnerFinder.isWinner(checkBoard);
        //then
        assertThat(isWinner).isFalse();
    }

    @Test
    public void shouldReturnTrueForX_X_O_X_XBoard() {
        //given
        int[] checkBoard = {
                1, -1, 1,
                -1, -1, 1,
                1, 1, -1
        };
        //when
        boolean isDraw = WinnerFinder.isDraw(checkBoard);
        //then
        assertThat(isDraw).isTrue();
    }

    @Test
    public void shouldReturnFalseForX_X_O_X_OBoard() {
        //given
        int[] checkBoard = {
                1, 0, 1,
                0, -1, 0,
                1, 0, -1
        };
        //when
        boolean isDraw = WinnerFinder.isDraw(checkBoard);
        //then
        assertThat(isDraw).isFalse();
    }


}