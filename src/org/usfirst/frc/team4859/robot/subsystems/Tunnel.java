package org.usfirst.frc.team4859.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Tunnel extends Subsystem {
	//public static Talon motorTunnelLeft = new Talon(RobotMap.talonIDTunnelLeft);
	//public static Talon motorTunnelRight = new Talon(RobotMap.talonIDTunnelRight);

    public void initDefaultCommand() {}
    
    public void tunnelIntake(double inputSpeed) {
    	//motorTunnelLeft.set(-inputSpeed);
    	//motorTunnelLeft.set(inputSpeed);
    }
    
    public void tunnelShoot(double inputSpeed) {
    	//motorTunnelLeft.set(inputSpeed);
    	//motorTunnelLeft.set(-inputSpeed);
    }
    
    public void tunnelStop() {
    	//motorTunnelLeft.set(0);
    	//motorTunnelLeft.set(0);
    }  
}