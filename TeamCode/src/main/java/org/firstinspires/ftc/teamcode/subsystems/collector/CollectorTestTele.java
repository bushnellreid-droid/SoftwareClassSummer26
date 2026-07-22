package org.firstinspires.ftc.teamcode.subsystems.collector;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Config
@TeleOp(name = "test tele", group = "TeleOp")
public class CollectorTestTele extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Collector collector = new Collector(hardwareMap, telemetry);
        telemetry.setMsTransmissionInterval(20);
        telemetry.addLine("Ready");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()){

            if (gamepad1.right_trigger > 0.1){
                collector.setCollectorState(Collector.CollectorState.COLLECT);
            }
            else if (gamepad1.left_trigger > 0.1){
                collector.setCollectorState(Collector.CollectorState.EXTAKE);
            }else {
                collector.setCollectorState(Collector.CollectorState.OFF);
            }

            collector.update();
            telemetry.update();
        }

    }
}
