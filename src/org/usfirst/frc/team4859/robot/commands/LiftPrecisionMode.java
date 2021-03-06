package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class LiftPrecisionMode extends Command {
    
	protected void initialize() {
		RobotMap.kTunnelShootSpeed = 0.5;
		RobotMap.liftPrecisionMode = true;
	}
	
	protected void execute() {}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		RobotMap.kTunnelShootSpeed = 1;
		RobotMap.liftPrecisionMode = false;
	}
	
	protected void interrupted() {
		RobotMap.kTunnelShootSpeed = 1;
		RobotMap.liftPrecisionMode = false;
	}
}