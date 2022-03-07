package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.Range;

public class Drive {
    //Object Creation
    HardwareRobot robot;

    /* Class Variables */
    double leftDriveStartPosition ;
    double rightDriveStartPosition;
    
    //Constants
    final double ROBOT_RADIUS   = 16/2 ;  // 8in
    final double TICKS_PER_INCH = 60.79;

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
    public void tankDrive(double leftDrive, double rightDrive) {
        //Calculate the powers
        double leftPower  = leftDrive;
        double rightPower = rightDrive;

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
    public int autoDrive(double power, double rightInches, double leftInches) {
        //Variables
        double leftAutoPower  = power * 0.50;
        double rightAutoPower = power * 0.50;

        //Gets the start position for the motors
        if (firstTime == true) {
            //Makes firstTime false
            firstTime = false;

            //Gets the starting position
            leftDriveStartPosition  = robot.getRightEncoder();
            rightDriveStartPosition = robot.getLeftEncoder();
        }

        //Inches to ticks
        double leftTarget = inchesToTicks(rightInches) * -1;
        double rightTarget = inchesToTicks(leftInches) * -1;
        int    leftTargetInteger  = (int)leftDriveStartPosition + (int)leftTarget;
        int    rightTargetInteger = (int)rightDriveStartPosition + (int)rightTarget;

        //Sets the desired target position
        robot.leftDrive .setTargetPosition(leftTargetInteger);
        robot.rightDrive.setTargetPosition(rightTargetInteger);

        //Allow movement
        robot.startAutoMovement();

        //Sets the motor power
        robot.leftDrive .setPower(leftAutoPower) ;
        robot.rightDrive.setPower(rightAutoPower);

        //Stop movement
        if (leftTargetInteger > 0) {
            if (robot.rightDrive.getCurrentPosition() > (leftTargetInteger - TICKS_PER_INCH) ) {
                firstTime = true;
                robot.stopAutoMovement();
    
                return HardwareRobot.DONE;
            }
            else {
                return HardwareRobot.CONT;
            }
        }
        else if (rightTargetInteger > 0) {
            if (robot.rightDrive.getCurrentPosition() > (rightTargetInteger - TICKS_PER_INCH) ) {
                firstTime = true;
                robot.stopAutoMovement();
    
                return HardwareRobot.DONE;
            }
            else {
                return HardwareRobot.CONT;
            }
        }
        else if (leftTargetInteger < 0) {
            if (robot.rightDrive.getCurrentPosition() < (leftTargetInteger + TICKS_PER_INCH) ) {
                firstTime = true;
                robot.stopAutoMovement();
    
                return HardwareRobot.DONE;
            }
            else {
                return HardwareRobot.CONT;
            }
        }
        else if (rightTargetInteger < 0) {
            if (robot.rightDrive.getCurrentPosition() < (rightTargetInteger + TICKS_PER_INCH) ) {
                firstTime = true;
                robot.stopAutoMovement();
    
                return HardwareRobot.DONE;
            }
            else {
                return HardwareRobot.CONT;
            }
        }
        else {
            return HardwareRobot.CONT;
        }
    }

    /**
     * 
     */
    public int autoRotate(double power, double degrees) {
        //
        double leftAutoPower  = power * 0.50;
        double rightAutoPower = power * 0.50;

        //Degrees to ticks
        double ticks = degreesToTicks(degrees);

        //Movement
        double leftTarget  = ticks * -1;
        double rightTarget = ticks;
        int    leftTargetInteger  = (int)leftDriveStartPosition + (int)leftTarget;
        int    rightTargetInteger = (int)rightDriveStartPosition + (int)rightTarget;

        //Sets the desired target position
        robot.leftDrive .setTargetPosition(leftTargetInteger);
        robot.rightDrive.setTargetPosition(rightTargetInteger);

        //Allow movement
        robot.startAutoMovement();

        //Sets the motor power
        robot.leftDrive .setPower(leftAutoPower) ;
        robot.rightDrive.setPower(rightAutoPower);

        //Stop movement
        if (leftTargetInteger > 0) {
            if (robot.rightDrive.getCurrentPosition() > (leftTargetInteger - TICKS_PER_INCH) ) {
                firstTime = true;
                robot.stopAutoMovement();
    
                return HardwareRobot.DONE;
            }
            else {
                return HardwareRobot.CONT;
            }
        }
        else if (rightTargetInteger > 0) {
            if (robot.rightDrive.getCurrentPosition() > (rightTargetInteger - TICKS_PER_INCH) ) {
                firstTime = true;
                robot.stopAutoMovement();
    
                return HardwareRobot.DONE;
            }
            else {
                return HardwareRobot.CONT;
            }
        }
        else if (leftTargetInteger < 0) {
            if (robot.rightDrive.getCurrentPosition() < (leftTargetInteger + TICKS_PER_INCH) ) {
                firstTime = true;
                robot.stopAutoMovement();
    
                return HardwareRobot.DONE;
            }
            else {
                return HardwareRobot.CONT;
            }
        }
        else if (rightTargetInteger < 0) {
            if (robot.rightDrive.getCurrentPosition() < (rightTargetInteger + TICKS_PER_INCH) ) {
                firstTime = true;
                robot.stopAutoMovement();
    
                return HardwareRobot.DONE;
            }
            else {
                return HardwareRobot.CONT;
            }
        }
        else {
            return HardwareRobot.CONT;
        }
    }

    /**
     * Converts inches to encoder ticks
     */
    public double inchesToTicks(double targetInches) {
        double ticks = targetInches * TICKS_PER_INCH;
        
        return ticks;
    }

    public double degreesToTicks(double targetDegrees) {
        //Math
        double circumfrance = 2 * Math.PI * ROBOT_RADIUS;
        double distance     = circumfrance * (targetDegrees / 360);
        double ticks        = inchesToTicks(distance);
        
        ticks = ticks * 2;
        
        return ticks;
    }

}

//End of the Drive class