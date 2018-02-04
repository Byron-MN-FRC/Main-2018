package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TunnelShoot extends Command {
	
	private double speed = 0;
	private double time = 0;
	
    public TunnelShoot(double inputSpeed) {
    	requires(Robot.tunnel);
    	speed = inputSpeed;
    }
    
    public TunnelShoot(double inputSpeed, double inputTime) {
    	requires(Robot.tunnel);
    	speed = inputSpeed;
    	time = inputTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.tunnel.tunnelShoot(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.tunnel.tunnelStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.tunnel.tunnelStop();
    }
}