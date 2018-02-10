package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

	public class TunnelIntake extends Command {
		
		private double speed = 0;
		private double time = 0;
		
	    public TunnelIntake (double inputSpeed) {
	    	requires(Robot.tunnel);
	    	speed = inputSpeed;
	    	time = 0;
	    }
	    
	    public TunnelIntake (double inputSpeed, double inputTime) {
	    	requires(Robot.tunnel);
	    	speed = inputSpeed;
	    	time = inputTime;
	    }

	    protected void initialize() {
	    	setTimeout(time);
	    	System.out.println("TunnelIntake command ran");
	    }

	    protected void execute() {
	    	Robot.tunnel.tunnelIntake(speed);
	    }

	    protected boolean isFinished() {
	    	if (time <= 0) return RobotMap.isPowerCubeInBox;
	    	else return isTimedOut();
	    }

	    protected void end() {
	    	Robot.tunnel.tunnelStop();
	    }

	    protected void interrupted() {
	    	Robot.tunnel.tunnelStop();
	    }
	}