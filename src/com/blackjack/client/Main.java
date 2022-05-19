package com.blackjack.client;

import com.blackjack.app.BlackjackApp;
import com.apps.util.SplashApp;


class Main implements SplashApp {
    public static void main(String[] args) {
        BlackjackApp app = new BlackjackApp();
        app.initialize();
    }

    @Override
    public void start() {

    }
}
