package org.usfirst.frc.team4859.robot.subsystems;

//import org.usfirst.frc.team4859.robot.RobotMap;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	//public static Talon motorClimb = new Talon(RobotMap.talonIDClimbUp);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {}
    
    
    public void climbUp(double inputSpeed){
    	//motorClimb.set(inputSpeed);
    }

	public void climbStop() {
		//motorClimb.set(0);
	}
}