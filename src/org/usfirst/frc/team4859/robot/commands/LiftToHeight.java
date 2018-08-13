package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class LiftToHeight extends Command {
	
	private String position = "";
	private double time = 0;
	
	public LiftToHeight(String inputPosition, double inputTime) {
    	requires(Robot.lifter);
    	position = inputPosition;
    	time = inputTime;
    	RobotMap.liftSetHeight = position;
    }
	
	public LiftToHeight() {
    	requires(Robot.lifter);
    	time = 0;
    }

    protected void initialize() {
    	setTimeout(time);
    	System.out.println("LiftToHeight command ran");
    }

    protected void execute() {
    	if (time > 0) Robot.lifter.liftToHeight(position);
    	else Robot.lifter.liftToHeight(RobotMap.liftSetHeight);
    }

    protected boolean isFinished() {
    	if (time <= 0) return true;
    	else return isTimedOut();
    }

    protected void end() {
    	if (time > 0) Robot.lifter.liftToHeight(position);
    	else Robot.lifter.liftToHeight(RobotMap.liftSetHeight);
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    }
}