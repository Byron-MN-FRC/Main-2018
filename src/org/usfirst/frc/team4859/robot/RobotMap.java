package org.usfirst.frc.team4859.robot;

public class RobotMap {
	
	public static int talonIDRightMaster = 1;
	public static int talonIDRightFollower1 = 2;
	public static int talonIDRightFollower2 = 3;
	
	public static int talonIDLeftMaster = 4;
	public static int talonIDLeftFollower1 = 5;
	public static int talonIDLeftFollower2 = 6;
	
	public static int kTimeoutMs = 10;
	public static int kPIDSlot = 0;
	
	public static double kP = 0.22;
	public static double kI = 0.002;
	public static double kD = 2.000;
	public static double kF = 0.085;
	public static int kAllowableError = 90;
	public static int kAcceleration = 9025;
	public static int kCruiseVelocity = 8000;
	
	public static boolean pMode = false;
	public static boolean fMode = false;
	
	public static boolean targetScale = false;
	public static String targetName = "Switch";
	
	public static char location = ' ';
	public static char targetSide = ' ';
	public static char oppositeTargetSide = ' ';
	public static double delayInSeconds = 0;
	
	// Inches
	public static double robotWidth = 27;
	
	// Inches			   				wheel diameter * pi / encoder units per revolution / 2 (sprocket 2:1 reduction)
	public static double encoderUnitsPerInch = ((6 * 3.141592654) / 4096) / 4;
}