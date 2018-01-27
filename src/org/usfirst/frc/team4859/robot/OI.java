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
 
	public OI() {

		shiftUp.whenPressed(new ShiftUp());
		shiftDown.whenPressed(new ShiftDown());
		
		acquireIntake.whileHeld(new AcquirerIntake());
		acquireOuttake.whileHeld(new AcquirerOuttake());
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
}