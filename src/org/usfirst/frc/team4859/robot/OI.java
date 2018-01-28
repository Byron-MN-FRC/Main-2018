package org.usfirst.frc.team4859.robot;

import org.usfirst.frc.team4859.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {	
	private final Joystick joystick = new Joystick(0);
	
	Button shiftUp = new JoystickButton(joystick, 5);
	Button shiftDown = new JoystickButton(joystick, 3);
	
	Button acquireIntake = new JoystickButton(joystick, 1);
	Button acquireOuttake = new JoystickButton(joystick, 2);
	
	Button climbStart = new JoystickButton(joystick, 12);
	Button climbStop = new JoystickButton(joystick, 13);

	Button tunnelUp = new JoystickButton(joystick, 2);
	Button tunnelDown = new JoystickButton(joystick, 2);

	Button liftDown = new JoystickButton(joystick, 12);
	Button liftSwitch = new JoystickButton(joystick, 11);
	Button liftScale = new JoystickButton(joystick, 9);
	Button liftClimb = new JoystickButton(joystick, 7);
 
	public OI() {

		shiftUp.whenPressed(new ShiftUp());
		shiftDown.whenPressed(new ShiftDown());
		
		acquireIntake.whileHeld(new AcquirerIntake());
		acquireOuttake.whileHeld(new AcquirerOuttake());
		
		tunnelUp.whileHeld(new TunnelUp());
		tunnelDown.whileHeld(new TunnelDown());

		liftDown.whenPressed(new LiftDown());
		liftSwitch.whenPressed(new LiftSwitch());
		liftScale.whenPressed(new LiftScale());
		liftClimb.whenPressed(new LiftClimb());
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
}