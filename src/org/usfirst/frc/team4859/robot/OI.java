package org.usfirst.frc.team4859.robot;

import org.usfirst.frc.team4859.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {	
	private final Joystick joystick = new Joystick(0);
	
	Button pneumaticShiftUp = new JoystickButton(joystick, 5);
	Button pneumaticShiftDown = new JoystickButton(joystick, 3);
	
 
	public OI() {

		pneumaticShiftUp.whenPressed(new PneumaticShiftUp(0.1));
		pneumaticShiftDown.whenPressed(new PneumaticShiftDown(0.1));
		
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
}