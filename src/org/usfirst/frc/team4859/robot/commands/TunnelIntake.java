package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

	public class TunnelIntake extends Command {
		
		private double time = 0;
		
	    public TunnelIntake () {
	    	requires(Robot.tunnel);
	    	time = 0;
	    }
	    
	    public TunnelIntake (double inputTime) {
	    	requires(Robot.tunnel);
	    	time = inputTime;
	    }

	    protected void initialize() {
	    	setTimeout(time);
	    	System.out.println("TunnelIntake command ran");
	    }

	    protected void execute() {
	    	Robot.tunnel.tunnelIntake(RobotMap.kTunnelIntakeSpeed);
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