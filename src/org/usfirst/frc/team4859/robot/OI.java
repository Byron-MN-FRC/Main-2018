package org.usfirst.frc.team4859.robot;

import org.usfirst.frc.team4859.robot.autonomous.DriveTurnLeft90;
import org.usfirst.frc.team4859.robot.autonomous.DriveTurnRight90;
import org.usfirst.frc.team4859.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {	
	private final Joystick joystick = new Joystick(0);
	private final Joystick xbox = new Joystick(1);
	
	Button turnRight90 = new JoystickButton(joystick, 12);
	Button turnLeft90 = new JoystickButton(joystick, 11);
	
	Button shiftUp = new JoystickButton(joystick, 5);
	Button shiftDown = new JoystickButton(joystick, 3);
	
//	Button acquire = new JoystickButton(joystick, 1);
	
//	Button setLiftSwitch = new JoystickButton(xbox, 5);
//	Button setLiftScaleLow = new JoystickButton(xbox, 1);
//	Button setLiftScaleNorm = new JoystickButton(xbox, 2);
//	Button setLiftScaleHigh = new JoystickButton(xbox, 4);
//	Button setLiftClimb = new JoystickButton(xbox, 6);
//	
//	Button liftToHeight = new JoystickButton(joystick, 7);
//	
//	Button acquireIntake = new JoystickButton(joystick, 1);
//	Button acquireOuttake = new JoystickButton(joystick, 2);
//	
//	Button climbStart = new JoystickButton(joystick, 12);
//
//	Button tunnelIntake = new JoystickButton(joystick, 2);
//	Button tunnelShoot = new JoystickButton(joystick, 2);
//

 
	public OI() {

		shiftUp.whenPressed(new ShiftUp());
		shiftDown.whenPressed(new ShiftDown());
		
		turnRight90.whenPressed(new DriveTurnRight90());
		turnLeft90.whenPressed(new DriveTurnLeft90());
		
//		acquire.whenPressed(new Acquire("acquire", RobotMap.acquireIntakeSpeed, RobotMap.tunnelIntakeSpeed));
//		acquire.whenReleased(new AcquireStop("default"))
//		
//		setLiftSwitch.whenPressed(new LiftSwitch(RobotMap.liftSwitchHeight));
//		setLiftScaleLow.whenPressed(new SetLiftScaleLow());
//		setLiftScaleNorm.whenPressed(new SetLiftScale());
//		setLiftScaleHigh.whenPressed(new SetLiftScaleHigh());
//		setLiftClimb.whenPressed(new SetLiftClimb());
//		
//		liftToHeight.whenPressed(new LiftToHeight(RobotMap.liftSetHeight);
//		
//		climbStart.whileHeld(new ClimbStart(RobotMap.kClimbSpeed));
//
//		acquireIntake.whileHeld(new AcquirerIntake(RobotMap.acquireIntakeSpeed));
//		acquireOuttake.whileHeld(new AcquirerOuttake(RobotMap.acquireOuttakeSpeed));
//		
//		tunnelIntake.whileHeld(new TunnelIntake(RobotMap.tunnelIntakeSpeed));
//		tunnelShoot.whileHeld(new TunnelShoot(RobotMap.tunnelIntakeSpeed));

	}
	
	public Joystick getJoystick() {
		return joystick;
	}
}