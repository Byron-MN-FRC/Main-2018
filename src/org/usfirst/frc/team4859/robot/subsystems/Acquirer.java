package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Acquirer extends Subsystem {
	// Create motors here
	public static Talon motorAcquireLeft = new Talon(RobotMap.talonIDAquireRight);
	public static Talon motorAcquireRight = new Talon(RobotMap.talonIDAquireRight);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {}
    
    public void acquireIntake(double inputSpeed) {
    	motorAcquireLeft.set(-inputSpeed);
    	motorAcquireLeft.set(inputSpeed);
    }
    
    public void acquireOuttake(double inputSpeed) {
    	motorAcquireLeft.set(inputSpeed);
    	motorAcquireLeft.set(-inputSpeed);
    }
    
    public void acquireStop() {
    	motorAcquireLeft.set(0);
    	motorAcquireLeft.set(0);
    }
}