package com.example.livescoreapp;

import java.util.List;

public class LiveScoresResponse {

    public int results; // Represents the number of results
    public List<Response> response; // Represents the list of responses

    // Nested response class
    public static class Response {
        public Fixture fixture;
        public League league;
        public Teams teams;
        public Goals goals;
    }

    public static class Fixture {
        public int id;
        public String date;
    }

    public static class League {
        public int id;
        public String name;
        public String country;
    }

    public static class Teams {
        public Team home;
        public Team away;
    }

    public static class Team {
        public int id;
        public String name;
        public String logo; // Add this field to hold the logo URL
    }

    public static class Goals {
        public int home;
        public int away;
    }
}
