package com.bielinski.ticTacToe;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void shouldReturnPlayerXForPlayerO() {
        //given
        Player actualPlayer = Player.O;
        //when
        actualPlayer = actualPlayer.nextPlayer();
        //then
        assertThat(actualPlayer).isEqualTo(Player.X);
    }

    @Test
    public void shouldReturnPlayerOForPlayerO() {
        //given
        Player actualPlayer = Player.X;
        //when
        actualPlayer = actualPlayer.nextPlayer();
        //then
        assertThat(actualPlayer).isEqualTo(Player.O);
    }
}