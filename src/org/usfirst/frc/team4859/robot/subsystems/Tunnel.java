package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Tunnel extends Subsystem {
	public static Talon motorTunnelLeft = new Talon(RobotMap.talonIDTunnelLeft);
	public static Talon motorTunnelRight = new Talon(RobotMap.talonIDTunnelRight);
	
    public void initDefaultCommand() {}
    
    public void tunnelIntake(double inputSpeed) {
    	if(RobotMap.isPowerCubeInBox) {
    		motorTunnelLeft.set(0);
        	motorTunnelRight.set(0);
    	} else {
    		motorTunnelLeft.set(inputSpeed);
        	motorTunnelRight.set(inputSpeed);
    	}
    	
    }
    
    public void tunnelShoot(double inputSpeed) {
    	motorTunnelLeft.set(-inputSpeed);
    	motorTunnelRight.set(-inputSpeed);
    }
    
    public void tunnelStop() {
    	motorTunnelLeft.set(0);
    	motorTunnelRight.set(0);
    }  
}