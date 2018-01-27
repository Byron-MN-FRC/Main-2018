package org.usfirst.frc.team4859.robot;

public class RobotMap {
	
	public static int talonIDLeftMaster = 3;
	public static int talonIDRightMaster = 1;
	
	public static int talonIDLeftFollower = 4;
	public static int talonIDRightFollower = 2;
	
	public static boolean pMode = false;
	public static boolean fMode = false;
	
	//									wheel diameter * pi / encoder units per revoltion
	public static double encoderUnitsPerInch = (6 * 3.141592654) / 4096;
}