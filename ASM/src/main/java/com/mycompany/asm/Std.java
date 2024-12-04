package com.mycompany.asm;

class Std {
    private String id;
    private String n; // Name
    private double s; // Score

    public Std(String id, String n, double s) {
        this.id = id;
        this.n = n;
        this.s = s;
    }

    // Getter for ID
    public String getId() {
        return id;
    }

    // Getter for Name
    public String getN() {
        return n;
    }

    // Setter for Name
    public void setN(String n) {
        this.n = n;
    }

    // Getter for Score
    public double getS() {
        return s;
    }

    // Setter for Score
    public void setS(double s) {
        this.s = s;
    }

    // Getter for Rating
    public String getR() {
        if (s < 5.0) return "Fail";
        else if (s < 6.5) return "Medium";
        else if (s < 7.5) return "Good";
        else if (s < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Score: %.2f, Rating: %s", id, n, s, getR());
    }
}
