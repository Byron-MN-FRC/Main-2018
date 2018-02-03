package org.usfirst.frc.team4859.robot;

public class RobotMap {
	
	// Motor IDs
	public static int talonIDRightMaster = 1;
	public static int talonIDRightFollower1 = 2;
	public static int talonIDRightFollower2 = 3;
	
	public static int talonIDLeftMaster = 4;
	public static int talonIDLeftFollower1 = 5;
	public static int talonIDLeftFollower2 = 6;
	
	public static int talonIDAquireLeft = 0;
	public static int talonIDAquireRight = 1;
	
	// Command speed numbers
	public static double acquireIntakeSpeed = 0.5;
	public static double acquireOuttakeSpeed = 0.5;
	
	// Closed loop values
	public static int kTimeoutMs = 10;
	public static int kPIDSlot = 0;
	
	public static double kP = 0.250;
	public static double kI = 0.002;
	public static double kD = 2.000;
	public static double kF = 0.085;
	public static int kAllowableError = 75;
	public static int kAcceleration = 9025;
	public static int kCruiseVelocity = 9025;
	
	// Robot numbers for closed loop (in inches)
	public static double robotWidth = 25.25;

	//			   				wheel diameter * pi / encoder units per revolution / 2 (sprocket 2:1 reduction)
	public static double encoderUnitsPerInch = ((6 * 3.141592654) / 4096) / 2;
	
	// Precision mode
	public static boolean pMode = false;
	
	// Autonomous selector variables
	public static boolean targetScale = false;
	public static String targetName = "Switch";
	
	public static char location = ' ';
	public static char targetSide = ' ';
	public static char oppositeTargetSide = ' ';
	public static double delayInSeconds = 0;
}