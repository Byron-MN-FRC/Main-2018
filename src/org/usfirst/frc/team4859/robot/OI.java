package org.usfirst.frc.team4859.robot;

import org.usfirst.frc.team4859.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {	
	private final Joystick joystick = new Joystick(0);
	private final Joystick xbox = new Joystick(1);
	
	// Main Driver Buttons
	Button shiftUp = new JoystickButton(joystick, 5);
	Button shiftDown = new JoystickButton(joystick, 3);
	
	Button acquire = new JoystickButton(joystick, 1);
	Button shoot = new JoystickButton(joystick, 2);
	
	Button liftToHeight = new JoystickButton(joystick, 6);
	
//	Button turnRight90 = new JoystickButton(joystick, 12);
//	Button turnLeft90 = new JoystickButton(joystick, 11);
	
	// Secondary Driver Buttons
	Button setLiftSwitch = new JoystickButton(xbox, 5);
	Button setLiftScaleLow = new JoystickButton(xbox, 1);
	Button setLiftScaleNorm = new JoystickButton(xbox, 2);
	Button setLiftScaleHigh = new JoystickButton(xbox, 4);
	Button setLiftClimb = new JoystickButton(xbox, 6);
	
	// Buttons for mechanism testing
//	Button acquireIntake = new JoystickButton(joystick, 7);
//	Button acquireOuttake = new JoystickButton(joystick, 12);
//	
//	Button climbStart = new JoystickButton(joystick, 12);
//
//	Button tunnelIntake = new JoystickButton(joystick, 8);
//	Button tunnelShoot = new JoystickButton(joystick, 11);
 
	public OI() {
		
		// Main Driver Buttons
		shiftUp.whenPressed(new ShiftUp());
		shiftDown.whenPressed(new ShiftDown());
		
//		turnRight90.whenPressed(new DriveTurnRight90());
//		turnLeft90.whenPressed(new DriveTurnLeft90());
		
		acquire.whenPressed(new Acquire(RobotMap.liftDownSpeed, RobotMap.acquireIntakeSpeed, RobotMap.tunnelIntakeSpeed));
		acquire.whenReleased(new AcquireStop("default"));
		
		shoot.whenPressed(new Shoot(RobotMap.acquireShootSpeed, RobotMap.tunnelShootSpeed));
		shoot.whenReleased(new ShootStop());
		
		// Secondary Driver Buttons
		setLiftSwitch.whenPressed(new SetLiftSwitch());
		setLiftScaleLow.whenPressed(new SetLiftScaleLow());
		setLiftScaleNorm.whenPressed(new SetLiftScale());
		setLiftScaleHigh.whenPressed(new SetLiftScaleHigh());
		setLiftClimb.whenPressed(new SetLiftClimb());
		
		liftToHeight.whenPressed(new LiftToHeight());
		
		// Buttons for mechanism testing
//		climbStart.whileHeld(new ClimbUp(RobotMap.climbSpeed));

//		acquireIntake.whenPressed(new AcquirerIntake(RobotMap.acquireIntakeSpeed));
//		acquireIntake.whenReleased(new AcquirerStop());
//		
//		acquireOuttake.whenPressed(new AcquirerOuttake(RobotMap.acquireShootSpeed));
//		acquireOuttake.whenReleased(new AcquirerStop());
//		
//		tunnelIntake.whenPressed(new TunnelIntake(RobotMap.tunnelIntakeSpeed));
//		tunnelIntake.whenReleased(new TunnelStop());
//		
//		tunnelShoot.whileHeld(new TunnelShoot(RobotMap.tunnelIntakeSpeed));
//		tunnelShoot.whenPressed(new TunnelStop());
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
}