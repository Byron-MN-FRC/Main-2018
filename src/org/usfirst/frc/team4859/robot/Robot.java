/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                         */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
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
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4859.robot.subsystems.Lifter;
import org.usfirst.frc.team4859.robot.subsystems.Set;
import org.usfirst.frc.team4859.robot.subsystems.Shifters;
import org.usfirst.frc.team4859.robot.subsystems.Tunnel;
import com.kauailabs.navx.frc.AHRS;

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
	
	//MiniPID miniPID = new MiniPID(135, 1.2, 0);
	MiniPID miniPID;
	
	public static AHRS navX = new AHRS(SerialPort.Port.kUSB1);
	public static UsbCamera cameraBackward = CameraServer.getInstance().startAutomaticCapture("Backward", 1);
	public static UsbCamera cameraForward = CameraServer.getInstance().startAutomaticCapture("Forward", 0);
	
//	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public static DigitalInput boxSensor = new DigitalInput(8);
	public static DigitalOutput boxLED = new DigitalOutput(7);
	public static AnalogInput liftStage1LimitSwitch = new AnalogInput(2);
	public static AnalogInput liftStage2LimitSwitch = new AnalogInput(3);
  
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		SmartDashboard.putString("Robot Start", "Z");
		SmartDashboard.putString("Scale", "Z");
		SmartDashboard.putString("Shoot", "Y");
//		SmartDashboard.putString("Optimal Path", "N");
		SmartDashboard.putNumber("Auton Delay", 0.0);
		
		SmartDashboard.putBoolean("Front Camera", RobotMap.liftDirectionFront);
    	SmartDashboard.putBoolean("Back Camera", !RobotMap.liftDirectionFront);
		SmartDashboard.putString("liftSetHeight", RobotMap.liftSetHeight);
		SmartDashboard.putBoolean("Lift PMode", RobotMap.liftPrecisionMode);

		cameraBackward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
		cameraForward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
		
		cameraBackward.setExposureManual(70);
		cameraForward.setExposureManual(50);
		
//		SmartDashboard.putNumber("Auton P-value", 170.0);
//		SmartDashboard.putNumber("Auton I-Value", 1.95);
//		SmartDashboard.putNumber("Auton I-Max", 100);
		
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
		Lifter.motorLiftStage1.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		Lifter.motorLiftStage2.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		String robotPos = SmartDashboard.getString("Robot Start", "z");
		String location = String.valueOf(robotPos.toUpperCase().charAt(0));
		String targetScale = SmartDashboard.getString("Scale", "N");
		String shootCubeStr = SmartDashboard.getString("Shoot", "Y").toUpperCase();
		RobotMap.shootCubeAuton = (shootCubeStr.equals("Y"));
//		String optimalPath = SmartDashboard.getString("Optimal Path", "N");
		String shoot = RobotMap.shootCubeAuton ? "Cube will be delivered.%n" : "Cube will not be delivered.%n";
		System.out.printf(shoot);

		//Allow selection of MiniPID tuning values on drive station
//		double p = SmartDashboard.getNumber("Auton P-value", 170.0);
//		double i = SmartDashboard.getNumber("Auton I-Value", 1.95);
//		double maxI = SmartDashboard.getNumber("Auton I-Max", 100);
		miniPID = new MiniPID(170, 1.95, 0);
		miniPID.setMaxIOutput(300);
		
		navX.reset();
//		gyro.reset();
		
		RobotMap.delayInSeconds = SmartDashboard.getNumber("Auton Delay", 0);
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
		boolean validGameString = Pattern.matches("[LR]{3}", gameData.toUpperCase());
		boolean validRobotPos = Pattern.matches("[LCR]{1}", location);
		boolean validDelay = false;
		
		if(RobotMap.delayInSeconds >= 0 && RobotMap.delayInSeconds < 15) validDelay = true;
		
		if (!validGameString || !validRobotPos || !validDelay) {
		 	System.out.println("Gamedata is invalid! Running AutoStraight routine.");
		 	System.out.println("gamedata=" + gameData);
		 	System.out.println("location=" + location);
		 	System.out.println("delay=" + RobotMap.delayInSeconds);
		 	HandleBadData(location);
		} else {
			RobotMap.targetSide = gameData.charAt(0); //default to switch side
			RobotMap.location = location.charAt(0);
			RobotMap.targetScale = false;
			if (targetScale.equalsIgnoreCase("Y")) { 
				RobotMap.targetScale = true; 
				RobotMap.targetSide = gameData.charAt(1);
			}
			
//			if (optimalPath.toUpperCase().charAt(0) == 'Y') RobotMap.optimalPath = true;
//			else RobotMap.optimalPath = false;
			
			if (gameData.toUpperCase().charAt(0) == location.charAt(0)) RobotMap.switchSameSide = true;
			else RobotMap.switchSameSide = false;
			
			if (gameData.toUpperCase().charAt(1) == location.charAt(0)) RobotMap.scaleSameSide = true;
			else RobotMap.scaleSameSide = false;
			
//			if(RobotMap.optimalPath) {
//				if(RobotMap.scaleSameSide && RobotMap.switchSameSide);
//				else if(RobotMap.targetScale && !RobotMap.scaleSameSide && RobotMap.switchSameSide) RobotMap.targetScale = false;
//				else if(!RobotMap.targetScale && !RobotMap.switchSameSide && RobotMap.scaleSameSide) RobotMap.targetScale = true;
//				else if(!RobotMap.scaleSameSide && !RobotMap.switchSameSide) location = "O";
//				else location = "S"; // Should not get here, drive straight
//			}
			
			m_autonomousCommand = new AutoSelector();
			m_autonomousCommand.start();
		}		
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		if(liftStage1LimitSwitch.getVoltage() < 2) RobotMap.isLiftStage1Down = false;
		else {
			RobotMap.isLiftStage1Down = true;
			Lifter.motorLiftStage1.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		}
		
		if(liftStage2LimitSwitch.getVoltage() < 2) RobotMap.isLiftStage2Down = false;
		else {
			RobotMap.isLiftStage2Down = true;
			Lifter.motorLiftStage2.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		}
		
		if(boxSensor.get()) RobotMap.isPowerCubeInBox = true;
		else RobotMap.isPowerCubeInBox = false;
		
		RobotMap.gyroCorrection = miniPID.getOutput(navX.pidGet(), 0);
		SmartDashboard.putNumber("PID", miniPID.getOutput(navX.pidGet(), 0));
//		RobotMap.gyroCorrection = miniPID.getOutput(gyro.getAngle(), 0);
//		SmartDashboard.putNumber("PID", miniPID.getOutput(gyro.getAngle(), 0));
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
		
		Lifter.motorLiftStage1.set(0);
		Lifter.motorLiftStage2.set(0);
		
		navX.reset();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		if(boxSensor.get()) RobotMap.isPowerCubeInBox = false;
		else RobotMap.isPowerCubeInBox = true;
		
		if(liftStage1LimitSwitch.getVoltage() < 2) RobotMap.isLiftStage1Down = false;
		else {
			RobotMap.isLiftStage1Down = true;
			Lifter.motorLiftStage1.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		}
		
		if(liftStage2LimitSwitch.getVoltage() < 2) RobotMap.isLiftStage2Down = false;
		else {
			RobotMap.isLiftStage2Down = true;
			Lifter.motorLiftStage2.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		}
		
		if(Lifter.motorLiftStage1.getSelectedSensorPosition(RobotMap.kPIDSlot) + Lifter.motorLiftStage2.getSelectedSensorPosition(RobotMap.kPIDSlot) > 38000) RobotMap.pMode = true;
//		if(Lifter.motorLiftStage1.getSelectedSensorPosition(RobotMap.kPIDSlot) > 38000) RobotMap.pMode = true;
		else RobotMap.pMode = false;
		
		// SmartDashboard Logging
    	SmartDashboard.putBoolean("Front Camera", RobotMap.liftDirectionFront);
    	SmartDashboard.putBoolean("Back Camera", !RobotMap.liftDirectionFront);
		SmartDashboard.putString("liftSetHeight", RobotMap.liftSetHeight);
		SmartDashboard.putBoolean("Lift PMode", RobotMap.liftPrecisionMode);
		SmartDashboard.putBoolean("IR", RobotMap.isPowerCubeInBox);
//		SmartDashboard.putNumber("gyro", navX.pidGet());
//		SmartDashboard.putNumber("PID", miniPID.getOutput(navX.pidGet(), 0));
//		SmartDashboard.putNumber("IR Volt", boxSensor.getVoltage());
//		SmartDashboard.putBoolean("limit switch stage1", RobotMap.isLiftStage1Down);
//		SmartDashboard.putBoolean("limit switch stage2", RobotMap.isLiftStage2Down);
//		SmartDashboard.putNumber("limit switch volt", liftLimitSwitch.getVoltage());
//		SmartDashboard.putNumber("lifter amps", Lifter.motorLift.getOutputCurrent());
	}

	public static double driveEncoderUnitConversion(double inches) {
		double encoderUnits = inches * RobotMap.driveEncoderUnitsPerInch;
		return encoderUnits;
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