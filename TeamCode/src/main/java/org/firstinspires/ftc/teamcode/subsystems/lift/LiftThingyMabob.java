package org.firstinspires.ftc.teamcode.subsystems.lift;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.util.InterpLUT;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class LiftThingyMabob {
    public static double kP = 0, kI = 0, kD = 0;
    public static double kS = 0;
   public static double kG = 0;
    private  DcMotorEx liftMotor;
    private PIDController Liftpid;
    private InterpLUT kGLookup;
    private Telemetry telemetry;

    private double targetAngle;


    public LiftThingyMabob(HardwareMap hardwareMap, Telemetry telemetry) {
        liftMotor = hardwareMap.get(DcMotorEx.class, "lift");
        this.telemetry = telemetry;
        Liftpid = new PIDController(kP, kI, kD);
    }

    public void update() {

        double curAngle = liftMotor.getCurrentPosition();
        Liftpid.setPID(kP, kI, kD);



        double pidPower = Liftpid.calculate(curAngle, targetAngle);
        double frictionFF = Math.signum(targetAngle - curAngle) * kS;
        double totalPower = kG + frictionFF + pidPower;

        liftMotor.setPower(totalPower);


        telemetry.addLine("lift");
        telemetry.addData("lift current angle", curAngle);
        telemetry.addData("lift power", totalPower);
        telemetry.addData("lift target angle", targetAngle);
        telemetry.addData("lift error", (targetAngle - curAngle));

    }


    public void setTargetAngle(double target) {
        this.targetAngle = target;
    }

}