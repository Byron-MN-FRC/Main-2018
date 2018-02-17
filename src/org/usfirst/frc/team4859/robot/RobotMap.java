package org.usfirst.frc.team4859.robot;

import java.util.HashMap;
import java.util.Map;

public class RobotMap {
	
	// Motor IDs
	public static int talonIDRightMaster = 1;
	public static int talonIDRightFollower1 = 2;
	public static int talonIDRightFollower2 = 3;
	
	public static int talonIDLeftMaster = 4;
	public static int talonIDLeftFollower1 = 5;
	public static int talonIDLeftFollower2 = 6;
	
	public static int talonIDLiftStage1 = 7;
	public static int talonIDLiftStage2 = 8;
	
	public static int talonIDAcquireLeft = 0;
	public static int talonIDAcquireRight = 1;
	
	public static int talonIDTunnelLeft = 3;
	public static int talonIDTunnelRight = 2;

	public static int talonIDClimbUp = 8;
	
	// Command numbers
	public static double acquireIntakeSpeed = 0.5;
	public static double acquireOuttakeSpeed = 0.5;
	
	public static double climbSpeed = 0.5;
	
	// Lifter heights
	public static final Map<String,Double[]> liftPosition = new HashMap<String, Double[]> () {/**
         * 
         */
        private static final long serialVersionUID = 1L;

    {	   //name                      stage1 stage2 (in inches)
        put("acquire",   new Double[]  { 0.0, 0.0 } );
        put("default",   new Double[]  { 6.0, 0.0 } );
        put("switch",    new Double[]  { 0.0, 24.75 } );
        put("scaleLow",  new Double[]  { 18.0, 39.5 } );
        put("scaleNorm", new Double[]  { 30.0, 39.5 } );
        put("scaleHigh", new Double[]  { 40.0, 39.5 } );
        put("climb",     new Double[]  { 40.0, 39.5 } );        
    }};
    
    /* Example of how to get values:
     * liftPosition.get("switch")[0];
     */
    
    public static String liftSetHeight = "";
	
	// Power cube detection
	public static boolean isPowerCubeInBox = false;
	
	// Current limiting
	public static int kDriveContinuousCurrentLimit = 30; // Amps
	public static int kDriveCurrentPeakDuration = 2000; // Milliseconds
	
	// Stage 1 is a Mini CIM
	public static int kLiftStage1ContinuousCurrentLimit = 45;
	public static int kLiftStage1CurrentPeakDuration = 2000;
	
	// Stage 2 is a BAG Motor
	public static int kLiftStage2ContinuousCurrentLimit = 35;
	public static int kLiftStage2CurrentPeakDuration = 2000;
	
	// Closed loop values
	public static int kTimeoutMs = 10;
	public static int kPIDSlot = 0;
	
	// Lift values
	public static double kLiftStage1P = 0.22;
	public static double kLiftStage1I = 0.0015;
	public static double kLiftStage1D = 0.000;
	public static double kLiftStage1F = 0.09;
	public static int kLiftStage1AllowableError = 90;
	
	public static int kLiftStage1Acceleration = 2720;
	public static int kLiftStage1CruiseVelocity = 2720;
	
	public static double kLiftStage2P = 1.8;
	public static double kLiftStage2I = 0.001;
	public static double kLiftStage2D = 0.000;
	public static double kLiftStage2F = 0.53;
	public static int kLiftStage2AllowableError = 40;
	
	public static int kLiftStage2Acceleration = 10000;
	public static int kLiftStage2CruiseVelocity = 2250;
	
	public static double liftEncoderUnitsPerInch = 0;
	
	// Drivetrain values
	public static double kP = 0.25;
	public static double kI = 0.00015;
	public static double kD = 0.00;
	public static double kF = 0.12;
	public static int kAllowableError = 90;
	
	public static int kHighGearAcceleration = 9000;
	public static int kHighGearCruiseVelocity = 9000;
	
	public static int kLowGearAcceleration = 9000;
	public static int kLowGearCruiseVelocity = 9000;
	
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
	public static double secondsRatio = 25;
}