package org.usfirst.frc.team4859.robot;

import java.util.HashMap;
import java.util.Map;

// IMPORTNANT NOTE: DO NOT MAKE VALUES NEGATIVE. The "down," "reverse," and "intake" variables are positive but are flipped when pushed to the motors
public class RobotMap {
	
	// Motor IDs
	public static int talonIDRightMaster = 1;
	public static int talonIDRightFollower = 2;
	
	public static int talonIDLeftMaster = 4;
	public static int talonIDLeftFollower = 5;
	
	public static int talonIDLiftStage1 = 7;
//	public static int talonIDLiftStage2 = 8;
	
	public static int talonIDTunnelLeft = 3;
	public static int talonIDTunnelRight = 2;
	public static int talonIDTunnelTop = 1;
	
	// Command numbers
	public static double kTunnelIntakeSpeed = 0.4;
	public static double kTunnelShootSpeed = 1;
	
	public static double kClimbSpeed = 1;
	
	public static double kLiftDownSpeed = 0.15;
	public static double kLiftUpSpeed = 0.3;
	
	// Lifter heights
	public static final Map<String, Integer[]> liftPosition = new HashMap<String, Integer[]> () {

        private static final long serialVersionUID = 1L;

    {	   //name                      stage1 stage2 (in encoder units)
        put("acquire",   new Integer[]  { 0		, 0 	} ); //56200 max
        put("default",   new Integer[]  { 6000  , 0 	} );
        put("switch",    new Integer[]  { 0		, 25500	} );
        put("scaleLow",  new Integer[]  { 33250	, 31250	} );
        put("scaleNorm", new Integer[]  { 44850	, 31250	} );
        put("scaleHigh", new Integer[]  { 55000	, 31250	} );
        put("climb",     new Integer[]  { 55000	, 31250	} );
    }};
    
    /* Example of how to get values:
     * liftPosition.get("switch")[0];
     */
    
    public static String liftSetHeight = "switch";
	
	// Power cube detection
	public static boolean isPowerCubeInBox = false;
	public static boolean isLiftDown = false;
	
	// Current limiting
	public static int kDriveContinuousCurrentLimit = 30; // Amps
	public static int kDriveCurrentPeakDuration = 2000; // Milliseconds
	
	// Stage 1 is a Mini CIM
	public static int kLiftStage1ContinuousCurrentLimit = 40;
	public static int kLiftStage1CurrentPeakDuration = 2000;
	
	// Stage 2 is a BAG Motor
	public static int kLiftStage2ContinuousCurrentLimit = 30;
	public static int kLiftStage2CurrentPeakDuration = 2000;
	
	// Closed loop values
	public static int kTimeoutMs = 10;
	public static int kPIDSlot = 0;
	
	// Lift values
	public static double kLiftStage1P = 1.35;
	public static double kLiftStage1I = 0.0007;
	public static double kLiftStage1D = 0.000;
	public static double kLiftStage1F = 0.37;
	public static int kLiftStage1AllowableError = 90;
	
	public static int kLiftStage1Acceleration = 12600;
	public static int kLiftStage1CruiseVelocity = 3150;
	
	public static double kLiftStage2P = 1.8;
	public static double kLiftStage2I = 0.0008;
	public static double kLiftStage2D = 0.000;
	public static double kLiftStage2F = 0.53;
	public static int kLiftStage2AllowableError = 75;
	
	public static int kLiftStage2Acceleration = 9000;
	public static int kLiftStage2CruiseVelocity = 2250;
	
	// Drivetrain values
	public static double kRampRate = 0.2; //default
	
	public static double kHighGearRampRate = 0.15;
	public static double kLowGearRampRate = 0.15;
	
	public static double kP = 0.24;
	public static double kI = 0.000174;
	public static double kD = 0.00;
	public static double kF = 0.12;
	public static int kDriveAllowableError = 90;
	
	public static int kHighGearAcceleration = 6000;
	public static int kHighGearCruiseVelocity = 8500;
	
	public static int kLowGearAcceleration = 6000;
	public static int kLowGearCruiseVelocity = 8500;
	
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
	
	// Turn Ratio for encoder ticks
	public static double decoderTurnRatio = 25000 / 90;
	public static double secondsRatio = 40;
}