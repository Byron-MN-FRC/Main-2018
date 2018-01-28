/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4859.robot.autonomous.AutoNothing;
import org.usfirst.frc.team4859.robot.autonomous.AutoStraight;
import org.usfirst.frc.team4859.robot.autonomous.AutoSelector;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4859.robot.subsystems.Shifters;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final Drivetrain kDrivetrain = new Drivetrain();
	public static OI m_oi;
	public static Shifters pneumatics = new Shifters();
  
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		SmartDashboard.putString("Robot Start Pos (L,R, or C)", "C");
		SmartDashboard.putString("Target", "Y");
		SmartDashboard.putNumber("Auton Delay", 0.0);

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

		String robotPos = SmartDashboard.getString("Robot Start Pos (L,R, or C)", "Non Received");
		char location = Character.toUpperCase(robotPos.charAt(0));
		
		String targetScale = SmartDashboard.getString("Target", "Y");
		
		RobotMap.delayInSeconds = SmartDashboard.getNumber("Auton Delay", 0);
		
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		
	//MAKE SURE GAME DATA IS GOOD
		// Please don't hurt me for this
		if (!gameData.equalsIgnoreCase("RRR") || !gameData.equalsIgnoreCase("LLL") || !gameData.equalsIgnoreCase("RLR") || !gameData.equalsIgnoreCase("LRL") || !robotPos.equalsIgnoreCase("L") || !robotPos.equalsIgnoreCase("C") || !robotPos.equalsIgnoreCase("R") || (RobotMap.delayInSeconds >= 0 && RobotMap.delayInSeconds <= 15)) {
			m_autonomousCommand = new AutoStraight();
			m_autonomousCommand.start();
		}
		else {
			char targetSide = gameData.charAt(0); // default to switch side
			// String targetScale = SmartDashboard.getString("Target", "Non Received");  // ?? is there a boolean
			if (targetScale.equalsIgnoreCase("Y")) { 
				RobotMap.targetScale = true; 
				targetSide = gameData.charAt(1);
			}
			AutoSelector Selection = new AutoSelector(location,targetSide);
			Selection.driveToTarget();
		}

	}
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}