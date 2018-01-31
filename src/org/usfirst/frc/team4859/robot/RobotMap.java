package org.usfirst.frc.team4859.robot;

public class RobotMap {
	
	public static int talonIDRightMaster = 1;
	public static int talonIDRightFollower1 = 2;
	public static int talonIDRightFollower2 = 3;
	
	public static int talonIDLeftMaster = 4;
	public static int talonIDLeftFollower1 = 5;
	public static int talonIDLeftFollower2 = 6;
	
	public static boolean pMode = false;
	public static boolean fMode = false;
	
	public static boolean targetScale = false;
	
	public static char location = ' ';
	public static char targetSide = ' ';
	public static char oppositeTargetSide = ' ';
	public static double delayInSeconds = 0;
	
  
	//									wheel diameter * pi / encoder units per revolution / 2 (sprocket 2:1 reduction)
	public static double encoderUnitsPerInch = ((6 * 3.141592654) / 4096) / 2;
}