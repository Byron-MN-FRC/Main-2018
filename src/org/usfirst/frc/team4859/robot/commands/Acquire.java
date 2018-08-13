package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class Acquire extends Command {
	
	private double time = 0;
	
    public Acquire() {
    	requires(Robot.lifter);
    	requires(Robot.tunnel);
    	time = 0;
    }
    
    public Acquire(double inputTime) {
    	requires(Robot.lifter);
    	requires(Robot.tunnel);
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	System.out.println("Acquire command ran");
    }

    protected void execute() {
    	Robot.tunnel.tunnelIntake(RobotMap.kTunnelIntakeSpeed);
    	Robot.lifter.liftDown(RobotMap.kLiftStage1DownSpeed);
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.lifter.liftStop();
    	Robot.tunnel.tunnelStop();
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    	Robot.tunnel.tunnelStop();
    }
}