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
		SmartDashboard.putNumber("Target", 0);
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
		/* Disable as will get input from drive team on target
		   in combination with FMS data to determine which 
		   command to call
		   
		   m_autonomousCommand = m_chooser.getSelected();
      */
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		*/
		String robotPos = SmartDashboard.getString("Robot Start Pos (L,R, or C)", "Non Received");
		char location = robotPos.charAt(0);
		double autonDelaySeconds = SmartDashboard.getNumber("Auton Delay", 0);
		int target = (int) SmartDashboard.getNumber("Target", 0);
		
		String gameData;
		String selection = "No Selection";
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		// (autonDelaySeconds);
		switch (target){
		case 0: // Switch
			if(gameData.charAt(0) == 'L') {
				selection = "Place Cube on left switch";
				switch(location) {
				case 'C':
					selection = "Turn left, Drive forward, Turn right, Place cube on switch";
					break;
				case 'L':
					selection = "Drive forward, place cube on switch";
					break;
				case 'R':
					selection = "Turn right, drive forward, turn left to go around switch, deliver cube to left side switch";
					break;
				} 
			} else {
				selection = "Place Cube on right switch";
				switch(location){
				case 'C':
					selection = "Turn right, Drive forward, Place cube on switch";
					break;
				case 'L':
					selection = "Go left, Drive forward, turn right to go around switch, deliver cube to switch";
					break;
				case 'R':
					selection = "Drive forward, place cube on switch";
					break;
					
				}
			}
			break;
		case 1: // Scale
			if(gameData.charAt(1) == 'L'){
				selection = "Place Cube on left scale";
				switch(location) {
				case 'C':
					selection = "Go left, Drive forward, Place cube on scale";
					break;
				case 'L':
					selection = "Drive forward to scale, place cube";
					break;
				case 'R':
					selection = "Drive forward, turn left after passing switch, go forward, place cube on scale";
					break;
				}
			} else {
				selection = "Place Cube on right scale";
				switch(location) {
				case 'C':
					selection = "Go forward to get past other bots, go right, go forward, deliver cube to scale";
					break;
				case 'L':
					selection = "Go forward past switch, turn right, go forward, deliver cube to scale";
					break;
				case 'R':
					selection = "Go forward, deliver cube to scale";
					break;
				}
			}
			break;
		default:
			selection = "Drive forwards";
			break;
			
			
		}
		
		SmartDashboard.putString("Value from FMS", selection);

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
