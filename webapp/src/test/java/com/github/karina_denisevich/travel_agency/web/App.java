package com.github.karina_denisevich.travel_agency.web;

import com.github.karina_denisevich.travel_agency.web.load_balancer.StartBalancer;

import java.io.IOException;

public class App {

    private static final String[] ports = {"8081", "8082", "8083"};

    public static void main(String[] args) throws IOException {
        StartJetty.main(ports);
        StartBalancer.main(ports);
    }
}
