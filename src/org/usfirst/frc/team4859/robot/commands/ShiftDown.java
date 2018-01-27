package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftDown extends Command {
	
    public ShiftDown() {
    	requires(Robot.shifters);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shifters.pneumaticShiftDown();
		Drivetrain.motorLeftMaster.configMotionAcceleration(2720, 10);
		Drivetrain.motorRightMaster.configMotionCruiseVelocity(2720, 10);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}