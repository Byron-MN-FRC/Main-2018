package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Acquire extends Command {
	
	private double liftSpeed = 0;
	private double tunnelSpeed = 0;
	private double time = 0;
	
    public Acquire(double inputLiftSpeed, double inputTunnelSpeed) {
    	requires(Robot.lifter);
    	requires(Robot.tunnel);
    	liftSpeed = inputLiftSpeed;
    	tunnelSpeed = inputTunnelSpeed;
    	time = 0;
    }
    
    public Acquire(double inputLiftSpeed, double inputTunnelSpeed, double inputTime) {
    	requires(Robot.lifter);
    	requires(Robot.tunnel);
    	liftSpeed = inputLiftSpeed;
    	tunnelSpeed = inputTunnelSpeed;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	System.out.println("Acquire command ran");
    }

    protected void execute() {
    	Robot.tunnel.tunnelIntake(tunnelSpeed);
    	Robot.lifter.liftDown(liftSpeed);
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