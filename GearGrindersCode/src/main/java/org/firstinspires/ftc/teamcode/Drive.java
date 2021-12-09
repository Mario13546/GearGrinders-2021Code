package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.Range;

public class Drive {
    //Object Creation
    HardwareRobot robot;

    /* Class Variables */
    double leftDriveStartPosition ;
    double rightDriveStartPosition;

    //firstTime variable
    private boolean firstTime = true;

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
    public int autoForward(double power, double rightInches, double leftInches) {
        //Variables
        final double AUTO_POWER = power;

        //Gets the start position for the motors
        if (firstTime == true) {
            //Makes firstTime false
            firstTime = false;

            //Gets the starting position
            leftDriveStartPosition  = robot.getRightEncoder();
            rightDriveStartPosition = robot.getLeftEncoder();
        }

        //Feet to ticks
        double leftTarget = inchesToTicks(rightInches);
        double rightTarget = inchesToTicks(leftInches);
        int    leftTargetInteger  = (int)leftDriveStartPosition + (int)leftTarget;
        int    rightTargetInteger = (int)rightDriveStartPosition + (int)rightTarget;

        //Sets the desired target position
        robot.leftDrive .setTargetPosition(leftTargetInteger);
        robot.rightDrive.setTargetPosition(rightTargetInteger);

        //Allow movement
        robot.startAutoMovement();

        //Sets the motor power
        robot.leftDrive .setPower(AUTO_POWER);
        robot.rightDrive.setPower(AUTO_POWER);

        //Stop movement
        if (robot.rightDrive.getCurrentPosition() > (leftTargetInteger - 0.001) ) {
            firstTime = true;
            robot.stopAutoMovement();

            return HardwareRobot.DONE;
        }
        else if (robot.rightDrive.getCurrentPosition() > (rightTargetInteger - 0.001) ) {
            firstTime = true;
            robot.stopAutoMovement();

            return HardwareRobot.DONE;
        }
        else {
            return HardwareRobot.CONT;
        }
    }

    /**
     * Converts inches to encoder ticks
     */
    public double inchesToTicks(double targetInches) {
        final double TICKS_PER_INCH = 93.5/48000;      //This comes to be around 0.0019479167 ticks per inch
        double ticks = targetInches * TICKS_PER_INCH;

        return ticks;
    }

}

//End of the Drive class