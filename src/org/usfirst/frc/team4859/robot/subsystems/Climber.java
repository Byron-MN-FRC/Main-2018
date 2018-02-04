package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	public static Talon motorClimbUp = new Talon(RobotMap.talonIDClimbUp);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {}
    
    
    public void climbStart(double inputSpeed){
    	motorClimbUp.set(inputSpeed);
    }

	public void climbStop(double inputSpeed) {
		
		
	}


	public void climbStart() {
		// TODO Auto-generated method stub
		
	}


	public void climbStop() {
		// TODO Auto-generated method stub
		
	}

    
}