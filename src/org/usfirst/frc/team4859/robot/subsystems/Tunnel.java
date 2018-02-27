package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Tunnel extends Subsystem {
	public static WPI_TalonSRX motorTunnelLeft = new WPI_TalonSRX(RobotMap.talonIDTunnelLeft);
	public static WPI_TalonSRX motorTunnelRight = new WPI_TalonSRX(RobotMap.talonIDTunnelRight);
	public static WPI_TalonSRX motorTunnelTop = new WPI_TalonSRX(RobotMap.talonIDTunnelTop);

    public void initDefaultCommand() {}
    
    public void tunnelIntake(double inputSpeed) {
    	motorTunnelLeft.set(inputSpeed);
    	motorTunnelRight.set(-inputSpeed);
    	motorTunnelTop.set(inputSpeed);
    }
    
    public void tunnelShoot(double inputSpeed) {
    	motorTunnelLeft.set(-inputSpeed);
    	motorTunnelRight.set(inputSpeed);
    	motorTunnelTop.set(-inputSpeed);
    }
    
    public void tunnelStop() {
    	motorTunnelLeft.set(0);
    	motorTunnelRight.set(0);
    	motorTunnelTop.set(0);
    }  
}