package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class Controls {
    /* Enumerator for setting grabber states (words are easier to read than numbers) */
    public static enum GrabberState {
        OPEN,
        CLOSED;
    }
    GrabberState grabberState = GrabberState.OPEN;

    /* Class Variables */
    double driveSpeedMultiplier  = 1.00;
    double tiltSpeedMultiplier   = 0.50;
    double extendSpeedMultiplier = 0.50;
    double spinSpeedMultiplier   = 0.65;
    
    //Object Creation
    OpMode logitech;

    /**
     * Constructor
     */
    public Controls(OpMode opmode) {
        //Passes an OpMode into the class (allows for the use of controllers)
        logitech = opmode;
    }

    /**
     * Gamepad 1
     * Controls the driving of the robot 
     */
    public double leftDrivePower() {
        double power = logitech.gamepad1.left_stick_y;
        double drivePower = power * driveSpeedMultiplier;

        return drivePower;
    }

    public double rightDrivePower() {
        double power = logitech.gamepad1.right_stick_y;
        double turnPower = power * driveSpeedMultiplier;

        return turnPower;
    }

    /**
     * Gamepad 2
     * Gets the power for the tilting motor
     */
    public double tiltPower() {
        double power = -1 * logitech.gamepad2.left_stick_y;
        double tiltPower = power * tiltSpeedMultiplier; 

        return tiltPower;
    }
    
    /**
     * Gamepad 2
     * Gets the extend speed multiplier
     */
    public double extendPower() {
        double power = -1 * logitech.gamepad2.right_stick_y;
        double extendPower = power * extendSpeedMultiplier;
        
        return extendPower;
    }

    /**
     * Gamepad 2
     * Returns the power of the spiny motor
     * @return spinyPower
     */
    public double spinnerPower() {
        //Controller inputs
        float rightTrigger = logitech.gamepad2.right_trigger;
        float leftTrigger  = logitech.gamepad2.left_trigger ;

        //Decides what value to return and incorperates a dead band
        if ( (leftTrigger > 0.1) && (rightTrigger < 0.1) ) {
            return (double)leftTrigger;
        }
        else if ( (leftTrigger < 0.1) && (rightTrigger > 0.1) ) {
            return (double)rightTrigger * -1;
        }
        else {
            return 0.00;
        }
    } 

    /**
     * Gamepad 2
     * Sets the position of the grabber servos
     */
    public GrabberState getGrabberPosition() {
        //Gets controller inputs
        boolean rightBumper = logitech.gamepad2.right_bumper;
        boolean leftBumper  = logitech.gamepad2.left_bumper;

        //Checks if the right bumper is pressed
        if (rightBumper == true) {
            //Sets the grabber state to open
            grabberState = GrabberState.OPEN;
        }
        //Checks if the left bumper is pressed
        else if (leftBumper == true) {
            //Sets the grabber state to closed
            grabberState = GrabberState.CLOSED;
        }

        return grabberState;
    }

}

//End of the Controls class