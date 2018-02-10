package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbStop extends Command {
	
    public ClimbStop() {
    	requires(Robot.climber);
    }

    protected void initialize() {
    	System.out.println("ClimbStop command ran");
    }

    protected void execute() {
    	Robot.climber.climbStop();
    }

    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    	Robot.climber.climbStop();
    }

    protected void interrupted() {
    	Robot.climber.climbStop();
    }
}