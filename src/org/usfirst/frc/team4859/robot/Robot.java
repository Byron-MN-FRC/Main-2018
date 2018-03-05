/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                         */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.regex.Pattern;

import org.usfirst.frc.team4859.robot.ThrottleLookup.MiniPID;
import org.usfirst.frc.team4859.robot.autonomous.AutoSelector;
import org.usfirst.frc.team4859.robot.autonomous.AutoStraight;
import org.usfirst.frc.team4859.robot.autonomous.DriveStraightDistance;
import org.usfirst.frc.team4859.robot.autonomous.RobotTurnDegrees;
import org.usfirst.frc.team4859.robot.commands.LiftToHeight;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4859.robot.subsystems.Lifter;
import org.usfirst.frc.team4859.robot.subsystems.Set;
import org.usfirst.frc.team4859.robot.subsystems.Shifters;
import org.usfirst.frc.team4859.robot.subsystems.Tunnel;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final Drivetrain kDrivetrain = new Drivetrain();
	public static Shifters shifters = new Shifters();
	public static Tunnel tunnel = new Tunnel();
	public static Lifter lifter = new Lifter();
	public static Set set = new Set();
	public static OI oi;
	
	//MiniPID miniPID = new MiniPID(135, 1.2, 15);
	MiniPID miniPID;
	
	public static UsbCamera cameraBackward = CameraServer.getInstance().startAutomaticCapture("Backward", 0);
	public static UsbCamera cameraForward = CameraServer.getInstance().startAutomaticCapture("Forward", 1);
	
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
//	public static DigitalInput boxSensor = new DigitalInput(0);
//	public static DigitalOutput boxLED = new DigitalOutput(1);
	public static AnalogInput liftLimitSwitch = new AnalogInput(2);
  
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		SmartDashboard.putString("Robot Start Pos (L,R, or C)", "Put letter here");
		SmartDashboard.putString("Scale", "Put Y or N here");
		SmartDashboard.putString("Shoot Cube", "Y");
		SmartDashboard.putNumber("Auton Delay", 0.0);

		//gyro.calibrate();
		cameraBackward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 15);
		cameraForward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 15);
		
		SmartDashboard.putNumber("Auton P-value", 170.0);
		SmartDashboard.putNumber("Auton I-Value", 1.95);
		SmartDashboard.putNumber("Auton D-Value", 0);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		// Initially lift the cage up
		m_autonomousCommand = new LiftToHeight("default",4);
		m_autonomousCommand.start();
		
		String robotPos = SmartDashboard.getString("Robot Start Pos (L,R, or C)", "Non Received");
		String location = String.valueOf(robotPos.toUpperCase().charAt(0));
		String targetScale = SmartDashboard.getString("Scale", "N");
		String shootCubeStr = SmartDashboard.getString("Shoot Cube", "Y").toUpperCase();
		RobotMap.shootCubeAuton = (shootCubeStr.equals("Y"));
		SmartDashboard.putString("xxxxxx", shootCubeStr);
		String shoot = RobotMap.shootCubeAuton ? "Cube will be delivered.%n" : "Cube will not be delivered.%n";
		System.out.printf(shoot);

		//Allow selection of MiniPID tuning values on drive station
		double p = SmartDashboard.getNumber("Auton P-value", 170.0);
		double i = SmartDashboard.getNumber("Auton I-Value", 1.95);
		double d = SmartDashboard.getNumber("Auton D-Value", 0.0);
		miniPID = new MiniPID(p, i, d);
		
		gyro.reset();
		RobotMap.delayInSeconds = SmartDashboard.getNumber("Auton Delay", 0);
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		boolean validGameString = Pattern.matches("[LR]{3}", gameData.toUpperCase());
		boolean validRobotPos = Pattern.matches("[LCR]{1}", location);
		boolean validDelay = false;
		
		if(RobotMap.delayInSeconds >= 0 && RobotMap.delayInSeconds < 15) validDelay = true;
		
		if (!validGameString || !validRobotPos || !validDelay) {
		 	System.out.println("Gamedata is invalid! Running AutoStraight routine.");
		 	HandleBadData(location);
		} else {
			RobotMap.targetSide = gameData.charAt(0); //default to switch side
			RobotMap.location = location.charAt(0);
			RobotMap.targetScale = false;
			if (targetScale.equalsIgnoreCase("Y")) { 
				RobotMap.targetScale = true; 
				RobotMap.targetSide = gameData.charAt(1);
			} 
			
			m_autonomousCommand = new AutoSelector();
			m_autonomousCommand.start();
		}		
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		if (liftLimitSwitch.getVoltage() < 2) RobotMap.isLiftDown = false;
		else{
			RobotMap.isLiftDown = true;
			Lifter.motorLift.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		}
		
		RobotMap.gyroCorrection = miniPID.getOutput(gyro.getAngle(), 0);
		SmartDashboard.putNumber("PID", miniPID.getOutput(gyro.getAngle(), 0));
//		RobotMap.gyroCorrection = gyro.getAngle()*120;
	}

	@Override
	public void teleopInit() {
		/*
		 *  This makes sure that the autonomous stops running when
		 *  teleop starts running. If you want the autonomous to
		 *  continue until interrupted by another command, remove
		 *  this line or comment it out.
		 */
		if (m_autonomousCommand != null) m_autonomousCommand.cancel();
		
		gyro.reset();
		Lifter.motorLift.set(0);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
//		if(boxSensor.get()) RobotMap.isPowerCubeInBox = true;
//      else RobotMap.isPowerCubeInBox = false;
//		
		if (liftLimitSwitch.getVoltage() < 2) RobotMap.isLiftDown = false;
		else {
			RobotMap.isLiftDown = true;
			Lifter.motorLift.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		}
		
		if(Lifter.motorLift.getSelectedSensorPosition(RobotMap.kPIDSlot) > 38000) RobotMap.pMode = true;
		else RobotMap.pMode = false;
		
		// SmartDashboard Logging
		SmartDashboard.putNumber("gyro", gyro.getAngle());
//		SmartDashboard.putNumber("vel", Drivetrain.motorLeftMaster.getSelectedSensorVelocity(0));
    	SmartDashboard.putBoolean("Front Camera", RobotMap.liftDirectionFront);
    	SmartDashboard.putBoolean("Back Camera", !RobotMap.liftDirectionFront);
//		SmartDashboard.putBoolean("IR", RobotMap.isPowerCubeInBox);
//		SmartDashboard.putNumber("IR Volt", boxSensor.getVoltage());
//		SmartDashboard.putBoolean("limit switch", RobotMap.isLiftDown);
//		SmartDashboard.putNumber("limit switch volt", liftLimitSwitch.getVoltage());
		SmartDashboard.putString("liftSetHeight", RobotMap.liftSetHeight);
		SmartDashboard.putNumber("lifter amps", Lifter.motorLift.getOutputCurrent());
	}

	public static double driveEncoderUnitConversion(double inches) {
		double encoderUnits = inches * RobotMap.driveEncoderUnitsPerInch;
		return encoderUnits;
	}
	
	public static double angleToDistance(double angle) {
		double arcLength = (Math.PI * RobotMap.robotWidth) * (angle/360);
		return driveEncoderUnitConversion(arcLength);
	}
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	public void HandleBadData(String location) {
		switch (location)
		{
		     case "S":
		 		m_autonomousCommand = new DriveStraightDistance(296,7);
		 		break;
		     case "9":
		    	 m_autonomousCommand = new RobotTurnDegrees(90);
		    	 break;
		     case "4":
		    	 m_autonomousCommand = new RobotTurnDegrees(45);
		    	 break;
		     default:
		    	 m_autonomousCommand = new AutoStraight();
		    	 break;
		}
		m_autonomousCommand.start();
	}
}