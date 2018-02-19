package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ShootStop extends Command {
	
	private double time = 0;
	
    public ShootStop() {
    	requires(Robot.tunnel);
    	time = 0;
    }
    
    public ShootStop(double inputTime) {
    	requires(Robot.tunnel);
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	System.out.println("Acquire command ran");
    }

    protected void execute() {
    	Robot.tunnel.tunnelStop();
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.tunnel.tunnelStop();
    }

    protected void interrupted() {
    	Robot.tunnel.tunnelStop();
    }
}