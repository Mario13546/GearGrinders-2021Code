package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Autonomous", group="Pushbot")
public class Auto extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareRobot robot   = HardwareRobot.getInstance(); // Uses the robot's hardware
    ElapsedTime   runtime = new ElapsedTime();
    Drive         drive   = new Drive(); 

    /* Class Variables */
    private double status = HardwareRobot.CONT;

    // Constants
    static final double DRIVE_SPEED = 0.75;
    static final double TURN_SPEED  = 0.50;
    static final double TICKS_PER_INCH = 60.79;

    @Override
    public void runOpMode() {
        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        
        //Rests the encoders
        robot.autoConfig();

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                          robot.leftDrive .getCurrentPosition(),
                          robot.rightDrive.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED, 12, 12, 5.0); // S1: Forward 12 Inches with 5 Sec timeout
        encoderRotate(TURN_SPEED, 90, 5.0); // S2: Rotate 90 degrees right
        
        telemetry.addData("Path", "Complete");
        telemetry.update();
    }
    
    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double power, double leftInches, double rightInches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Reset timeout
            runtime.reset();

            // Drives
            if (status == HardwareRobot.CONT) {
                status = drive.autoDrive(power, rightInches, leftInches);
            }
            else if (status == HardwareRobot.DONE) {
                status = HardwareRobot.CONT;
                return;
            }

            // Determine new target position, and pass to motor controller
            newLeftTarget  = robot.leftDrive .getCurrentPosition() + (int)drive.inchesToTicks(leftInches );
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int)drive.inchesToTicks(rightInches);
            
            // Keep looping while OpMode is still active, there is time left, and both motors are running.
            while (opModeIsActive() &&
                   (runtime.seconds() < timeoutS) &&
                   (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2", "Currently at %7d :%7d",
                                            robot.leftDrive.getCurrentPosition(),
                                            robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            sleep(250);   // optional pause after each move
        }
    }

    /*
     *  Method to perfmorm a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderRotate(double power, double degrees, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Reset timeout
            runtime.reset();

            // Drives
            if (status == HardwareRobot.CONT) {
                status = drive.autoRotate(power, degrees);
            }
            else if (status == HardwareRobot.DONE) {
                status = HardwareRobot.CONT;
                return;
            }

            // Determine new target position, and pass to motor controller
            newLeftTarget  = robot.leftDrive .getCurrentPosition() + (int)drive.degreesToTicks(degrees);
            newRightTarget = robot.rightDrive.getCurrentPosition() - (int)drive.degreesToTicks(degrees);
            
            // Keep looping while OpMode is still active, there is time left, and both motors are running.
            while (opModeIsActive() &&
                   (runtime.seconds() < timeoutS) &&
                   (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2", "Currently at %7d :%7d",
                                            robot.leftDrive.getCurrentPosition(),
                                            robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            sleep(250);   // optional pause after each move
        }
    }

}

//End of the Auto class