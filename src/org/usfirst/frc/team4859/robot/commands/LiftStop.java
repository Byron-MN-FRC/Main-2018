package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftStop extends Command {
	
    public LiftStop() {
    	requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lifter.liftStop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lifter.liftStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lifter.liftStop();
    }
}
