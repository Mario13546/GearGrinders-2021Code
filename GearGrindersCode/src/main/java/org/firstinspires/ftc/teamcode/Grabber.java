package org.firstinspires.ftc.teamcode;

public class Grabber {
    /* Class Variables */
    //Nothing yet...

    //First Time
    boolean firstTime = true;

    //Object Creation
    HardwareRobot robot;

    /**
     * Constructor
     */
    public Grabber() {
        //Instance Creation
        robot = HardwareRobot.getInstance();
    }

    /**
     * Tilts the grabber arm
     */
    public void tiltArm(double tiltPower) {
        robot.tilt.setPower(tiltPower);
    }

    /**
     * Extends the robot's arm
     * <p>WARNING: THIS CAN CAUSE THE STRING TO SNAP
     */
    public void extendArm(double extendPower) {
        robot.extend.setPower(extendPower);
    }

    /**
     * Changes the physical position of the grabber
     */
    public void setGrabberPosition(Controls.GrabberState position) {
        //Left claw positions (constants)
        final double LEFT_OPEN    = -1.00;
        final double LEFT_CLOSED  =  1.00;

        //Right claw positions (constants)
        final double RIGHT_OPEN   =  1.00;
        final double RIGHT_CLOSED = -1.00;
        
        //What to do if the position is set to open
        if (position == Controls.GrabberState.OPEN) {
            robot.leftClaw.setPosition(LEFT_OPEN);
            robot.rightClaw.setPosition(RIGHT_OPEN);
        }
        //What to do if the position is set to closed
        else if (position == Controls.GrabberState.CLOSED) {
            robot.leftClaw.setPosition(LEFT_CLOSED);
            robot.rightClaw.setPosition(RIGHT_CLOSED);
        }
    }
}

//End of the Manipulator class