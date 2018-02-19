package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AcquirerStop extends Command {
	
	private double time = 0;
	
    public AcquirerStop() {
    	requires(Robot.acquirer);
    	time = 0;
    }
    
    public AcquirerStop(double inputTime) {
    	requires(Robot.acquirer);
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	System.out.println("AcquirerStop command ran");
    }

    protected void execute() {
    	Robot.acquirer.acquireStop();
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