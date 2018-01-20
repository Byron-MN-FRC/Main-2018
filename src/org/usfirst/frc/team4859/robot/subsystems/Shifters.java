package org.usfirst.frc.team4859.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shifters extends Subsystem {
	DoubleSolenoid shifterLeft = new DoubleSolenoid(0, 1);
	DoubleSolenoid shifterRight = new DoubleSolenoid(2, 3);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {}
    
    public void pneumaticShiftUp(){
    	
    	shifterLeft.set(Value.kForward);
    	shifterRight.set(Value.kForward);
    	
    }
    
    public void pneumaticShiftDown(){
    	
    	shifterLeft.set(Value.kReverse);
    	shifterRight.set(Value.kReverse);
    	
    }
    
}

