package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftAcquire extends Command {
	
	private double distance = 0;
	private double time = 0;
	
    public LiftAcquire(double inputDistance) {
    	requires(Robot.lifter);
    	distance = inputDistance;
    	time = 0;
    }
    
    public LiftAcquire(double inputDistance, double inputTime) {
    	requires(Robot.lifter);
    	distance = inputDistance;
    	time = inputTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    	Robot.lifter.liftAcquire(distance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (time <= 0) return RobotMap.powerCubeInBox;
    	else return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lifter.liftAcquire(distance);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lifter.liftStop();
    }
}