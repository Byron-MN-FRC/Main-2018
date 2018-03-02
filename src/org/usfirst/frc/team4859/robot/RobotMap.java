package org.usfirst.frc.team4859.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;

// IMPORTNANT NOTE: DO NOT MAKE VALUES NEGATIVE. The "down," "reverse," and "intake" variables are positive but are flipped when pushed to the motors
public class RobotMap {
	
	// Motor IDs
	public static int talonIDRightMaster = 1;
	public static int talonIDRightFollower = 2;
	
	public static int talonIDLeftMaster = 4;
	public static int talonIDLeftFollower = 5;
	
	public static int talonIDLift = 7;
	
	public static int talonIDTunnelLeft = 10;
	public static int talonIDTunnelRight = 11;
	public static int talonIDTunnelTop = 12;
	
	// Command numbers
	public static double kTunnelIntakeSpeed = 0.7;
	public static double kTunnelShootSpeed = 1;
	
	public static double kClimbSpeed = 1;
	
	public static double kLiftDownSpeed = 0.2;
	public static double kLiftUpSpeed = 0.3;
	
	// Lifter heights
	public static final Map<String, Integer[]> liftPosition = new HashMap<String, Integer[]> () {

        private static final long serialVersionUID = 1L;

    {	   //name                      encoder units
        put("acquire",   new Integer[]  { 0		} );
        put("default",   new Integer[]  { 3000	} );
        put("switch",    new Integer[]  { 9000	} );
        put("scaleLow",  new Integer[]  { 33250	} );
        put("scaleNorm", new Integer[]  { 44850	} );
        put("scaleHigh", new Integer[]  { 55000	} );
        put("climb",     new Integer[]  { 55000	} );
    }};
    
    /* Example of how to get values:
     * liftPosition.get("switch")[0];
     */
    
    public static String liftSetHeight = "switch";
    
    public static boolean liftDirectionFront = true;
    
    // Drivetrain ramp rates
    public static double kRampRate = 0.0;
    public static double kLowGearRampRate = 0.0;
    public static double kHighGearRampRate = 0.0;
	
	// Lift sensors
	public static boolean isPowerCubeInBox = false;
	public static boolean isLiftDown = false;
	
	// Current limiting
	// Drivetrain (CIMs)
	public static int kDriveContinuousCurrentLimit = 30; // Amps
	public static int kDriveCurrentPeakDuration = 2000; // Milliseconds
	
	// Lift (CIM)
	public static int kLiftContinuousCurrentLimit = 40;
	public static int kLiftCurrentPeakDuration = 2000;
	
	// Closed loop values
	public static int kTimeoutMs = 10;
	public static int kPIDSlot = 0;
	
	// Lift
	public static double kLiftP = 1.35;
	public static double kLiftI = 0.0007;
	public static double kLiftD = 0.000;
	public static double kLiftF = 0.37;
	public static int kLiftAllowableError = 90;
	
	public static int kLiftAcceleration = 12600;
	public static int kLiftCruiseVelocity = 3150;
	
	// Drivetrain
	public static double kP = 0.24;
	public static double kI = 0.000174;
	public static double kD = 0.0;
	public static double kF = 0.12;
	public static int kDriveAllowableError = 90;
	
	public static int kHighGearAcceleration = 6000;
	public static int kHighGearCruiseVelocity = 8500;
	
	public static int kLowGearAcceleration = 6000;
	public static int kLowGearCruiseVelocity = 8500;
	
	// Turn Ratio for encoder ticks
	public static double decoderTurnRatio = 25000 / 90;
	public static double secondsRatio = 40;
	
	// Robot numbers for closed loop (in inches)
	public static double robotWidth = 27;

	//			   				wheel diameter * pi / encoder units per revolution / 2 (sprocket 2:1 reduction) * magic number = 1264.77
	public static double driveEncoderUnitsPerInch = 1 / (6 * Math.PI) * 4096 * 2 * 2.9102;
	
	// Precision mode
	public static boolean pMode = false;
	
	// Autonomous selector variables
	public static boolean targetScale = false;
	
	public static char location = ' ';
	public static char targetSide = ' ';
	public static char oppositeTargetSide = ' ';
	public static double delayInSeconds = 0;
	
	public static int counter = 0;
	
	public static UsbCamera cameraBackward = CameraServer.getInstance().startAutomaticCapture("Backward", 0);
	public static UsbCamera cameraForward = CameraServer.getInstance().startAutomaticCapture("Forward", 1);
}