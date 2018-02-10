package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LiftToHeight extends Command {
	
	private double distance = 0;
	private double time = 0;
	
    public LiftToHeight(double inputDistance) {
    	requires(Robot.lifter);
    	distance = inputDistance;
    	time = 0;
    }
    
    public LiftToHeight(double inputDistance, double inputTime) {
    	requires(Robot.lifter);
    	distance = inputDistance;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	Robot.lifter.liftToHeight(distance);
    	System.out.println("LiftToHeight command ran");
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.lifter.liftToHeight(distance);
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    }
}