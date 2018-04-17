package com.bielinski.ticTacToe;

class Model {

    private Engine engine;
    private AI ai;

    Model() {
        this.engine = new Engine();
        this.ai = new AI();
    }
}
