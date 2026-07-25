package org.firstinspires.ftc.teamcode.utils.drivetrain;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class ColorSensorWrapper {
    public static double minR = 0, maxR = 0;
    public static double minG = 0, maxG = 0;
    public static double minB = 0, maxB = 0;

    private double rawRed, rawGreen, rawBlue;
    private double filteredRed, filteredGreen, filteredBlue;
    private double totalColor = 0;

    private final ColorSensor colorSensor;
    private final Telemetry telemetry;

    public ColorSensorWrapper(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        this.colorSensor = hardwareMap.get(ColorSensor.class, "color sensor");


    }
    public void update(){
        double filteredRedPercentage;
        double filteredGreenPercentage;
        double filteredBluePercentage;
        rawRed = colorSensor.red();
        rawGreen = colorSensor.green();
        rawBlue = colorSensor.blue();
        totalColor = rawRed + rawGreen + rawBlue;
        filteredRedPercentage = rawRed/totalColor;
        filteredGreenPercentage = rawGreen/totalColor;
        filteredBluePercentage = rawBlue/totalColor;
        filteredRed = 255*filteredRedPercentage;
        filteredGreen = 255*filteredGreenPercentage;
        filteredBlue = 255*filteredBluePercentage;

        

        telemetry.addLine("COLOR SENSOR----");
        telemetry.addData("red", rawRed);
        telemetry.addData("green", rawGreen);
        telemetry.addData("blue", rawBlue);
        telemetry.addData("red", filteredRedPercentage);
        telemetry.addData("green", filteredGreenPercentage);
        telemetry.addData("blue", filteredBluePercentage);
        telemetry.addData("red", filteredRed);
        telemetry.addData("green", filteredGreen);
        telemetry.addData("blue", filteredBlue);
    }

    public boolean isYellowBallThere() {
        return inRange(filteredRed, minR, maxR) && inRange(filteredGreen, minG, maxG) && inRange(filteredBlue, minB, maxB);
    }

    public boolean inRange(double n, double lower, double upper) {
        return n >= lower && n <= upper;
    }
}
