package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
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

    /* Object Creation */
    //DcMotors motors
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor extend;
    public DcMotor tilt;

    //Servo motors
    public Servo leftClaw;
    public Servo rightClaw;

    //Other members
    HardwareMap hwMap;

    /* Class Variables */
    //Nothign Yet...
    
    //firstTime variables
    boolean firstTime = true;

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
    
    /**
     * Configures the motors for Autonomous
     */
    public void autoConfig() {
        /* Resets the motor encoders */
        resetEncoders();

        /* Makes the motors run using encoders */
        //Drive Motors
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        //Manipulator Motors
        //extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //tilt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Configures the motors to allow movement to a position
     */
    public void startAutoMovement() {
        /* Makes the motors run to a position */
        //Drive Motors
        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Manipulator Motors
        //extend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //tilt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Configures the motors to stop movement
     */
    public void stopAutoMovement() {
        /* Makes the motors run to a position */
        //Drive Motors
        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Manipulator Motors
        //extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //tilt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Makes the robot reset its encoders 
     */
    public void resetEncoders() {
        //Drive Motors
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Manipulator Motors
        //extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //tilt.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Configures the motors for TeleOp
     */
    public void teleopConfig() {
        /* Sets the motors to run without encoders */
        //Drive Motors
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //Manipulator Motor
        extend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        tilt.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}

//End of the HardwareRobot class