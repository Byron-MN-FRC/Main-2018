package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class Acquire extends Command {
	
	private String position = "";
	private double acquireSpeed = 0;
	private double tunnelSpeed = 0;
	private double time = 0;
	
    public Acquire(String inputPosition, double inputAcquireSpeed, double inputTunnelSpeed) {
    	requires(Robot.lifter);
    	requires(Robot.acquirer);
    	requires(Robot.tunnel);
    	position = inputPosition;
    	acquireSpeed = inputAcquireSpeed;
    	tunnelSpeed = inputTunnelSpeed;
    	time = 0;
    }
    
    public Acquire(String inputPosition, double inputAcquireSpeed, double inputTunnelSpeed, double inputTime) {
    	requires(Robot.lifter);
    	requires(Robot.acquirer);
    	requires(Robot.tunnel);
    	position = inputPosition;
    	acquireSpeed = inputAcquireSpeed;
    	tunnelSpeed = inputTunnelSpeed;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	Robot.lifter.liftToHeight(position);
    	System.out.println("Acquire command ran");
    }

    protected void execute() {
    	Robot.acquirer.acquireIntake(acquireSpeed);
    	Robot.tunnel.tunnelIntake(tunnelSpeed);
    }

    protected boolean isFinished() {
    	if (time <= 0) return RobotMap.isPowerCubeInBox;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.lifter.liftToHeight(position);
    	Robot.acquirer.acquireStop();
    	Robot.tunnel.tunnelStop();
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    	Robot.acquirer.acquireStop();
    	Robot.tunnel.tunnelStop();
    }
}