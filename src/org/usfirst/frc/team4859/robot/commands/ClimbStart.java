package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbStart extends Command {
	
	private double time = 0;
	
    public ClimbStart() {
    	requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.climbStart();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.climbStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.climber.climbStop();
    }
}
