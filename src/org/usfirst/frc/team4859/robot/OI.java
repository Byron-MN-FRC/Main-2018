package org.usfirst.frc.team4859.robot;

import org.usfirst.frc.team4859.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {	
	private final Joystick joystick = new Joystick(0);
	private final Joystick xbox = new Joystick(1);
	
	// Main Driver Buttons
	protected Button shiftUp = new JoystickButton(joystick, 5);
	protected Button shiftDown = new JoystickButton(joystick, 3);
	
	protected Button acquire = new JoystickButton(joystick, 1);
	
	protected Button shoot = new JoystickButton(joystick, 2);
	protected Button shootOpposite = new JoystickButton(joystick, 4);
	
	protected Button liftToHeight = new JoystickButton(joystick, 6);
	
	// Secondary Driver Buttons 
	// Lift heights
	protected Button setLiftVault = new JoystickButton(xbox, 8);
	protected Button setLiftSwitch = new JoystickButton(xbox, 3);
	protected Button setLiftScaleLow = new JoystickButton(xbox, 1);
	protected Button setLiftScaleNorm = new JoystickButton(xbox, 2);
	protected Button setLiftScaleHigh = new JoystickButton(xbox, 4);
	
	// Direction
	protected Button setForward = new JoystickButton(xbox, 5);
	protected Button setBackward = new JoystickButton(xbox, 6);
	
	// Lift control
	protected Button liftDefault = new JoystickButton(xbox, 10);
	protected Button liftStop = new JoystickButton(xbox, 9);
	protected Button liftPMode = new JoystickButton(xbox, 7);
	
	// Buttons for mechanism testing
//	Button liftUp = new JoystickButton(xbox, 4);
//	Button liftDown = new JoystickButton(xbox, 3);
//	
//	Button climbStart = new JoystickButton(joystick, 12);
//
//	Button tunnelIntake = new JoystickButton(joystick, 8);
//	Button tunnelShoot = new JoystickButton(joystick, 11);
 
	public OI() {
		
		// Main Driver Buttons
		shiftUp.whenPressed(new ShiftUp());
		shiftDown.whenPressed(new ShiftDown());
		
		acquire.whenPressed(new Acquire());
		acquire.whenReleased(new AcquireStop("default"));
		
		shoot.whenPressed(new Shoot());
		shoot.whenReleased(new ShootStop());
		
		shootOpposite.whenPressed(new ShootOpposite());
		shootOpposite.whenReleased(new ShootStop());
		
		liftToHeight.whenPressed(new LiftToHeight());
		
		// Secondary Driver Buttons
		setLiftVault.whenPressed(new SetLiftVault());
		setLiftSwitch.whenPressed(new SetLiftSwitch());
		setLiftScaleLow.whenPressed(new SetLiftScaleLow());
		setLiftScaleNorm.whenPressed(new SetLiftScale());
		setLiftScaleHigh.whenPressed(new SetLiftScaleHigh());
		
		setForward.whenPressed(new SetForward());
		setBackward.whenPressed(new SetBackward());
		
		// Lift control
		liftDefault.whenPressed(new LiftDefault());
		liftDefault.whenReleased(new LiftToHeight("default", 0.1));
		liftStop.whenPressed(new LiftStop());
		liftPMode.toggleWhenPressed(new LiftPrecisionMode());
		
		// Buttons for mechanism testing
//		
//		liftUp.whenPressed(new LiftUp());
//		liftDown.whenPressed(new LiftDown());
//		
//		climbStart.whileHeld(new ClimbUp(RobotMap.climbSpeed));
//		
//		tunnelIntake.whenPressed(new TunnelIntake(1));
//		tunnelIntake.whenReleased(new TunnelStop());
//		
//		tunnelShoot.whileHeld(new TunnelShoot(RobotMap.tunnelIntakeSpeed));
//		tunnelShoot.whenPressed(new TunnelStop());
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
}