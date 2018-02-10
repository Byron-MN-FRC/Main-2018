package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AcquirerOuttake extends Command {
	
	private double speed = 0;
	private double time = 0;
	
    public AcquirerOuttake(double inputSpeed) {
    	requires(Robot.acquirer);
    	speed = inputSpeed;
    	time = 0;
    }
    
    public AcquirerOuttake(double inputSpeed, double inputTime) {
    	requires(Robot.acquirer);
    	speed = inputSpeed;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	System.out.println("AcquireOuttake command ran");
    }

    protected void execute() {
    	Robot.acquirer.acquireIntake(speed);
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.acquirer.acquireStop();
    }

    protected void interrupted() {
    	Robot.acquirer.acquireStop();
    }
}