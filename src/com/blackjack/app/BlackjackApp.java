package com.blackjack.app;

import com.apps.util.SplashApp;

class BlackjackApp implements SplashApp {
    public static void main(String[] args) {
        Game blackjack = new Game();
        blackjack.welcome();
    }

    @Override
    public void start() {

    }
}