package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LiftToHeight extends Command {
	
	private String position = "";
	private double time = 0;
	
    public LiftToHeight(String inputPosition) {
    	requires(Robot.lifter);
    	position = inputPosition;
    	time = 0;
    }
    
    public LiftToHeight(String inputPosition, double inputTime) {
    	requires(Robot.lifter);
    	position = inputPosition;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	Robot.lifter.liftToHeight(position);
    	System.out.println("LiftToHeight command ran");
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.lifter.liftToHeight(position);
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    }
}