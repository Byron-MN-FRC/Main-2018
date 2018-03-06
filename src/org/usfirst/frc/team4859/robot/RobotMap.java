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
	
	public static int talonIDLiftStage1 = 8;
	public static int talonIDLiftStage2 = 7;
	
	public static int talonIDTunnelLeft = 10;
	public static int talonIDTunnelRight = 11;
	public static int talonIDTunnelTop = 12;
	
	// Command numbers
	public static double kTunnelIntakeSpeed = 0.7;
	public static double kTunnelShootSpeed = 1;
	
	public static double kClimbSpeed = 1;
	
	public static double kLiftDownSpeed = 0.5;
	public static double kLiftUpSpeed = 0.3;
	
	// Lifter heights
	public static final Map<String, Integer[]> liftPosition = new HashMap<String, Integer[]> () {

        private static final long serialVersionUID = 1L;

    {	   //name                      encoder units
        put("acquire",   new Integer[]  { 0		} );
        put("default",   new Integer[]  { 7300	} ); // 6
        put("switch",    new Integer[]  { 22000	} ); // 35
        put("scaleLow",  new Integer[]  { 39900	} ); // 58
        put("scaleNorm", new Integer[]  { 47000	} ); // 70
        put("scaleHigh", new Integer[]  { 52000	} ); // 82
        put("climb",     new Integer[]  { 52000	} ); // 82
    }};
    
    /* Example of how to get values:
     * liftPosition.get("switch")[0];
     */
    
    public static String liftSetHeight = "switch";
    
    public static boolean liftDirectionFront = true;
    
    // Drivetrain ramp rates
    public static double kRampRate = 0.05; // Time to get from 0 to max
    public static double kRampRateLimit = 0.025; // Joystick increment for y limiting (this is the rate of change in 1/20 seconds)
    public static double kRampRateTipLimit = 0.005;
    public static double kLowGearRampRate = 0.05;
    public static double kHighGearRampRate = 0.05;
	
	// Lift sensors
	public static boolean isPowerCubeInBox = false;
	public static boolean isLiftDown = false;
	public static boolean liftPrecisionMode = false;
	
	// Current limiting
	// Drivetrain (CIMs)
	public static int kDriveContinuousCurrentLimit = 40; // Amps
	public static int kDriveCurrentPeakDuration = 2000; // Milliseconds
	
	// Lift (CIM)
	public static int kLiftStage1ContinuousCurrentLimit = 40;
	public static int kLiftStage1CurrentPeakDuration = 2000;
	
//	public static int kLiftStage2ContinuousCurrentLimit = 30;
//	public static int kLiftStage2CurrentPeakDuration = 2000;
	
	// Closed loop values
	public static int kTimeoutMs = 20;
	public static int kPIDSlot = 0;
	
	// Lift
	public static double kLiftStage1P = 1.25;
	public static double kLiftStage1I = 0.0001;
	public static double kLiftStage1D = 0.0;
	public static double kLiftStage1F = 0.262;
	public static int kLiftStage1AllowableError = 50;
	
	public static double kLiftStage2P = 1.25;
	public static double kLiftStage2I = 0.0001;
	public static double kLiftStage2D = 0.0;
	public static double kLiftStage2F = 0.262;
	public static int kLiftStage2AllowableError = 50;
	
	public static int kLiftStage1Acceleration = 20000;
	public static int kLiftStage1CruiseVelocity = 4500;
	
	public static int kLiftStage2Acceleration = 20000;
	public static int kLiftStage2CruiseVelocity = 4500;
	
	// Drivetrain
	public static double kP = 0.24;
	public static double kI = 0.000178;
	public static double kD = 0.0;
	public static double kF = 0.12;
	public static int kDriveAllowableError = 100;
	public static double gyroCorrection = 0;
	
	public static int kHighGearAcceleration = 6000;
	public static int kHighGearCruiseVelocity = 8500;
	
	public static int kLowGearAcceleration = 6000;
	public static int kLowGearCruiseVelocity = 8500;
	
	// Turn Ratio for encoder ticks
	public static double decoderTurnRatio = 34000 / 90;
	
	// Robot numbers for closed loop (in inches)
	public static double robotWidth = 27;

	//wheel diameter * pi / encoder units per revolution / 2 (sprocket 2:1 reduction) * magic number = 1264.77
	public static double driveEncoderUnitsPerInch = 1 / (6 * Math.PI) * 4096 * 2 * 2.9102;
	
	// Precision mode
	public static boolean pMode = false;
	
	// Autonomous selector variables
	public static boolean switchSameSide = true;
	public static boolean scaleSameSide = true;
	
	public static boolean targetScale = false;
	
	public static char location = ' ';
	public static char targetSide = ' ';
	public static char oppositeTargetSide = ' ';
	public static double delayInSeconds = 0;
	public static boolean shootCubeAuton = false;
}