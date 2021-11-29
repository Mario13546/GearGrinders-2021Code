package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

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

        // Initialize the DcMotors motors
        frontLeft  = hwMap.get(DcMotor.class, "leftDrive");
        backLeft   = hwMap.get(DcMotor.class, "rightDrive");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        //Makes the robot run without encoders (it doesn't have them anyway)
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set all motors to zero power
        leftDrive.setPower(0.00);
        rightDrive.setPower(0.00);
    }
    
}

//End of the HardwareRobot class