package com.bielinski.ticTacToe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameStatusCheckerTest {

    @Test
    public void shouldReturnTrueWhenCheckWinnerForXXXOO_O__Board() {
        //given
        int[] checkBoard = {
                Player.PLAYER_X_CODE, Player.PLAYER_X_CODE, Player.PLAYER_X_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE
        };
        //when
        boolean isWinner = GameStatusChecker.isWinner(checkBoard);
        //then
        assertThat(isWinner).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenCheckWinnerForOO_XXX_O_Board() {
        //given
        int[] checkBoard = {
                Player.PLAYER_O_CODE, Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_X_CODE, Player.PLAYER_X_CODE, Player.PLAYER_X_CODE,
                Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE
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
                Player.PLAYER_O_CODE, Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_X_CODE, Player.PLAYER_X_CODE, Player.PLAYER_X_CODE
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
                Player.PLAYER_X_CODE, Player.PLAYER_O_CODE, Player.PLAYER_O_CODE,
                Player.PLAYER_X_CODE, Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_X_CODE, Player.PLAYER_X_CODE, Player.PLAYER_O_CODE
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
                Player.PLAYER_O_CODE, Player.PLAYER_X_CODE, Player.PLAYER_O_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_X_CODE, Player.PLAYER_X_CODE,
                Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE, Player.PLAYER_O_CODE
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
                Player.PLAYER_X_CODE, Player.PLAYER_O_CODE, Player.PLAYER_X_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_O_CODE, Player.PLAYER_X_CODE,
                Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE
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
                Player.PLAYER_X_CODE, Player.PLAYER_O_CODE, Player.PLAYER_O_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_X_CODE, Player.PLAYER_X_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_O_CODE, Player.PLAYER_X_CODE
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
                Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE, Player.PLAYER_X_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE
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
                Player.PLAYER_X_CODE, Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_O_CODE, Player.PLAYER_O_CODE,
                Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE
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
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE
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
                Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE,
                Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE,
                Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_X_CODE
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
                Player.PLAYER_X_CODE, Player.PLAYER_O_CODE, Player.PLAYER_X_CODE,
                Player.PLAYER_O_CODE, Player.PLAYER_O_CODE, Player.PLAYER_X_CODE,
                Player.PLAYER_X_CODE, Player.PLAYER_X_CODE, Player.PLAYER_O_CODE
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
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE
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
                Player.PLAYER_X_CODE, Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE, Player.NONE_PLAYER_CODE,
                Player.NONE_PLAYER_CODE, Player.NONE_PLAYER_CODE, Player.PLAYER_O_CODE
        };
        //when
        boolean isDraw = GameStatusChecker.isDraw(checkBoard);
        //then
        assertThat(isDraw).isFalse();
    }
}