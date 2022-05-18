package com.blackjack.client;

import com.blackjack.app.BlackjackApp;
import com.apps.util.SplashApp;


/*
 *Gets the system going and thats it.
 * No actual work is done in here, ever
 *
 * */

class Main implements SplashApp {
    public static void main(String[] args) {
        BlackjackApp app = new BlackjackApp();
        app.initialize();
    }

    @Override
    public void start() {

    }
}
