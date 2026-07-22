package org.firstinspires.ftc.teamcode.FRQs;

public class Bottle {

    private double waterMaxCap = 1000;
    private double waterBottleAmt = 0;

    public Bottle(double waterMaxCap){
        this.waterMaxCap = waterMaxCap;
        this.waterBottleAmt = waterMaxCap;
    }
    public double updateWaterAmt(double waterBottleRemoveAmt){
        waterBottleAmt = waterBottleAmt - waterBottleRemoveAmt;
        if (waterBottleAmt <= (0.25)*waterMaxCap)
            waterBottleAmt = 1000;
        return waterBottleAmt;
    }
}
