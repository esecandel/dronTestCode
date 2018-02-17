package com.idealista.scandel.testcode.controller;

import javax.validation.constraints.NotNull;

public class DroneRequest {

    @NotNull
    private double coordenadaX;
    @NotNull
    private double coordenadaY;
    @NotNull
    private int range;

    public DroneRequest(double coordenadaX, double coordenadaY, int range) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.range = range;
    }

    double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "DroneRequest{" +
                "coordenadaX=" + coordenadaX +
                ", coordenadaY=" + coordenadaY +
                ", range=" + range +
                '}';
    }
}
