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

}

//End of the Drive class