package org.usfirst.frc.team4859.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Tunnel extends Subsystem {
	// Create motors here
		//public static Talon motorTunnelLeft = new Talon(RobotMap.talonIDAquireRight);
		//public static Talon motorTunnelRight = new Talon(RobotMap.talonIDAquireRight);
		
	    // Put methods for controlling this subsystem
	    // here. Call these from Commands.

    public void initDefaultCommand() {}
    
    public void tunnelUp() {
    	//motorTunnelLeft.set(-inputSpeed);
    	//motorTunnelLeft.set(inputSpeed);
    }
    
    public void tunnelDown() {
    	//motorTunnelLeft.set(inputSpeed);
    	//motorTunnelLeft.set(-inputSpeed);
    }
    
    public void tunnelStop() {
    	//motorTunnelLeft.set(0);
    	//motorTunnelLeft.set(0);
    }
    
}