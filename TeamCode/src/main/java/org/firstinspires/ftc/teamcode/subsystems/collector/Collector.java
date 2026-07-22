package org.firstinspires.ftc.teamcode.subsystems.collector;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Collector {
    private static double collectPower = 0.99;
    private static double extakePower = -0.99;
    private static double offPower = 0;
    private DcMotorEx intakeMotor;

    public enum CollectorState {
        OFF,
        COLLECT,
        EXTAKE
    }

    private CollectorState collectorState;

    public Collector(HardwareMap hardwareMap, Telemetry telemetry) {
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intake");
       intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }
    public void update(){
        switch(collectorState){
            case OFF:
                intakeMotor.setPower(offPower);
                break;
            case COLLECT:
                intakeMotor.setPower(collectPower);
                break;
            case EXTAKE:
                intakeMotor.setPower(extakePower);
                break;
        }
           telemetry.addLine("COLLECTOR");
        telemetry.addData("Collector Motor Power",intakeMotor.getPower());
        telemetry.addData("Collector State",collectorState);
    }

    public CollectorState getCollectorState() {
        return collectorState;
    }
    public void setCollectorState(Collector.CollectorState collectorState) {
        this.collectorState = collectorState;
    }
}




