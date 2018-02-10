package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbUp extends Command {
	
	private double speed = 0;
	
    public ClimbUp(double inputSpeed) {
    	requires(Robot.climber);
    	speed = inputSpeed;
    }

    protected void initialize() {
    	System.out.println("ClimbUp command ran");
    }

    protected void execute() {
    	Robot.climber.climbUp(speed);
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