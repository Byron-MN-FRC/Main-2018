package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AcquireStop extends Command {
	
	private String position = "";
	private double time = 0;
	
    public AcquireStop(String inputPosition) {
    	requires(Robot.lifter);
    	requires(Robot.tunnel);
    	position = inputPosition;
    	time = 0;
    }
	
    public AcquireStop(String inputPosition, double inputTime) {
    	requires(Robot.lifter);
    	requires(Robot.tunnel);
    	position = inputPosition;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	Robot.lifter.liftToHeight(position);
    	System.out.println("AcquireStop command ran");
    }

    protected void execute() {
    	Robot.tunnel.tunnelStop();
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.lifter.liftToHeight(position);
    	Robot.tunnel.tunnelStop();
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    	Robot.tunnel.tunnelStop();
    }
}