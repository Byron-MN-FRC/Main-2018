package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Tunnel extends Subsystem {
	public static WPI_TalonSRX motorTunnelLeft = new WPI_TalonSRX(RobotMap.talonIDTunnelLeft);
	public static WPI_TalonSRX motorTunnelRight = new WPI_TalonSRX(RobotMap.talonIDTunnelRight);
	
    public void initDefaultCommand() {}
    
    public void tunnelIntake(double inputSpeed) {
    	motorTunnelLeft.set(ControlMode.PercentOutput, -inputSpeed);
    	motorTunnelRight.set(ControlMode.PercentOutput, -inputSpeed);
    }
    
    public void tunnelShoot(double inputSpeed) {
    	motorTunnelLeft.set(ControlMode.PercentOutput, inputSpeed);
    	motorTunnelRight.set(ControlMode.PercentOutput, inputSpeed);
    }
    
    public void tunnelStop() {
    	motorTunnelLeft.set(0);
    	motorTunnelRight.set(0);
    }  
}