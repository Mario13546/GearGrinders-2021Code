package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareRobot {
    /* Singleton For Hardware Robot */
    private static HardwareRobot instance = null;

    public static synchronized HardwareRobot getInstance() {
        if (instance == null) {
            instance = new HardwareRobot();
        }

        return instance;
    }

    /* Class Variables */
    //DcMotors motors
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor extend;
    public DcMotor tilt;

    //Servo motors
    public Servo leftClaw;
    public Servo rightClaw;

    //firstTime variables
    boolean firstTime = true;

    //Other members
    HardwareMap hwMap = null;

    /**
     * Constructor
     */
    private HardwareRobot(){
        //Nothing
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Initialize the Drive motors
        leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");

        // Initialize the Grabber motors
        extend    = hwMap.get(DcMotor.class, "extend");
        tilt      = hwMap.get(DcMotor.class, "tilt");

        // Initialize the Grabber servos
        leftClaw  = hwMap.get(Servo.class, "left_claw");
        rightClaw = hwMap.get(Servo.class, "right_claw");

        // Sets all the motor directions
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        extend.setDirection(DcMotor.Direction.FORWARD);
        tilt.setDirection(DcMotor.Direction.FORWARD);

        // Makes the robot run without encoders (it doesn't have them anyway)
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        tilt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set all motors to zero power
        leftDrive.setPower(0.00);
        rightDrive.setPower(0.00);
        extend.setPower(0.00);
        tilt.setPower(0.00);

        // Sets the servos to their starting position
        leftClaw.setPosition(0.00);
        rightClaw.setPosition(0.00);
    }
    
}

//End of the HardwareRobot class