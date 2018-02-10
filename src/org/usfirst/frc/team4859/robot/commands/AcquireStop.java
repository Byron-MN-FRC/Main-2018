package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class AcquireStop extends Command {
	
	private double distance = 0;
	private double time = 0;
	
    public AcquireStop(double inputDistance) {
    	requires(Robot.lifter);
    	requires(Robot.acquirer);
    	requires(Robot.tunnel);
    	distance = inputDistance;
    	time = 0;
    }
	
    public AcquireStop(double inputDistance, double inputTime) {
    	requires(Robot.lifter);
    	requires(Robot.acquirer);
    	requires(Robot.tunnel);
    	distance = inputDistance;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	Robot.lifter.liftDefault(distance);
    	System.out.println("AcquireStop command ran");
    }

    protected void execute() {
    	Robot.acquirer.acquireStop();
    	Robot.tunnel.tunnelStop();
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.lifter.liftDefault(distance);
    	Robot.acquirer.acquireStop();
    	Robot.tunnel.tunnelStop();
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    	Robot.acquirer.acquireStop();
    	Robot.tunnel.tunnelStop();
    }
}