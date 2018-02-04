package org.usfirst.frc.team4859.robot;

import org.usfirst.frc.team4859.robot.autonomous.DriveTurnLeft90;
import org.usfirst.frc.team4859.robot.autonomous.DriveTurnRight90;
import org.usfirst.frc.team4859.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {	
	private final Joystick joystick = new Joystick(0);
	
	Button turnRight90 = new JoystickButton(joystick, 12);
	Button turnLeft90 = new JoystickButton(joystick, 11);
	
	Button shiftUp = new JoystickButton(joystick, 5);
	Button shiftDown = new JoystickButton(joystick, 3);
	
//	Button acquireIntake = new JoystickButton(joystick, 1);
//	Button acquireOuttake = new JoystickButton(joystick, 2);
//	
//	Button climbStart = new JoystickButton(joystick, 12);
//
//	Button tunnelUp = new JoystickButton(joystick, 2);
//	Button tunnelDown = new JoystickButton(joystick, 2);
//
//	Button liftAcquire = new JoystickButton(joystick, 12);
//	Button liftDefault = new JoystickButton(joystick, 12);
//	Button liftSwitch = new JoystickButton(joystick, 11);
//	Button liftScale = new JoystickButton(joystick, 9);
//	Button liftClimb = new JoystickButton(joystick, 7);
 
	public OI() {

		shiftUp.whenPressed(new ShiftUp());
		shiftDown.whenPressed(new ShiftDown());
		
		turnRight90.whenPressed(new DriveTurnRight90());
		turnLeft90.whenPressed(new DriveTurnLeft90());
		
//		climbStart.whileHeld(new ClimbStart(RobotMap.kClimbSpeed));

//		acquireIntake.whileHeld(new AcquirerIntake(RobotMap.acquireIntakeSpeed));
//		acquireOuttake.whileHeld(new AcquirerOuttake(RobotMap.acquireOuttakeSpeed));
//		
//		tunnelUp.whileHeld(new TunnelUp());
//		tunnelDown.whileHeld(new TunnelDown());
//
//		liftAcquire.whenPressed(new LiftAcquire(RobotMap.liftAcquireHeight));
//		liftDefault.whenPressed(new LiftDefault(RobotMap.liftDefaultHeight));
//		liftSwitch.whenPressed(new LiftSwitch(RobotMap.liftSwitchHeight));
//		liftScale.whenPressed(new LiftScale(RobotMap.liftScaleHeight));
//		liftClimb.whenPressed(new LiftClimb(RobotMap.liftClimbHeight));
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
}