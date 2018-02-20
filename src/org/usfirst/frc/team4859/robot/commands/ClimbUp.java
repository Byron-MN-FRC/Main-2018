package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbUp extends Command {
	
    public ClimbUp() {
    	requires(Robot.lifter);
    }

    protected void initialize() {
    	System.out.println("ClimbUp command ran");
    }

    protected void execute() {
    	Robot.lifter.liftDown(RobotMap.kClimbSpeed);
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    	Robot.lifter.liftStop();
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    }
}