package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.Range;

public class Drive {
    /* Class Variables */
    //Nothing yet...

    //Object Creation
    HardwareRobot robot;

    /**
     * Constructor
     */
    public Drive() {
        //Instance Creation
        robot = HardwareRobot.getInstance();
    }
    
    /**
     * Tank Drive movement code
     */
    public void tankDrive(double drive, double turn) {
        //Calculate the powers
        double leftPower  = drive + turn;
        double rightPower = drive - turn;

        //Clamps the powers
        leftPower  = Range.clip(leftPower, -1.0, 1.0);
        rightPower = Range.clip(rightPower, -1.0, 1.0);

        //Sets motor power
        robot.leftDrive.setPower (leftPower );
        robot.rightDrive.setPower(rightPower);
    }

    /**
     * Autonomous driving
     */
    public void autoForward(double feet) {
        //Variables
        double autoPower = 0.50;

        //Feet to ticks
        double target = feetToTicks(feet);
        int targetInteger = (int)target;

        //Sets the desired target position
        robot.leftDrive.setTargetPosition(targetInteger);
        robot.rightDrive.setTargetPosition(targetInteger);

        //Allow movement
        robot.startAutoMovement();

        //Sets the motor power
        robot.leftDrive.setPower(autoPower);
        robot.rightDrive.setPower(autoPower);

        //Stop movement
        if (robot.rightDrive.getCurrentPosition() > (target - 100) ) {
            robot.stopAutoMovement();
        }
    }

    /**
     * Converts feet to encoder ticks
     */
    private double feetToTicks(double targetFeet) {
        final double TICKS_PER_FOOT = 0.00;         //This value is currently unknown
        double ticks = targetFeet * TICKS_PER_FOOT;

        return ticks;
    }

}

//End of the Drive class