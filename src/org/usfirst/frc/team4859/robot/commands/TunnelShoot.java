package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class TunnelShoot extends Command {
	
	private double time = 0;
	
    public TunnelShoot() {
    	requires(Robot.tunnel);
    	time = 0;
    }
    
    public TunnelShoot(double inputTime) {
    	requires(Robot.tunnel);
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	System.out.println("TunnelShoot command ran");
    }

    protected void execute() {
    	Robot.tunnel.tunnelShoot(RobotMap.kTunnelShootSpeed);
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.tunnel.tunnelStop();
    }

    protected void interrupted() {
    	Robot.tunnel.tunnelStop();
    }
}