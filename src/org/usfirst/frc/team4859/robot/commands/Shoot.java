package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
	
	private double acquireSpeed = 0;
	private double tunnelSpeed = 0;
	private double time = 0;
	
    public Shoot(double inputAcquireShootSpeed, double inputTunnelShootSpeed) {
    	requires(Robot.acquirer);
    	requires(Robot.tunnel);
    	acquireSpeed = inputAcquireShootSpeed;
    	tunnelSpeed = inputTunnelShootSpeed;
    	time = 0;
    }
    
    public Shoot(double inputAcquireShootSpeed, double inputTunnelShootSpeed, double inputTime) {
    	requires(Robot.acquirer);
    	requires(Robot.tunnel);
    	acquireSpeed = inputAcquireShootSpeed;
    	tunnelSpeed = inputTunnelShootSpeed;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	System.out.println("Acquire command ran");
    }

    protected void execute() {
    	Robot.acquirer.acquireOuttake(acquireSpeed);
    	Robot.tunnel.tunnelShoot(tunnelSpeed);
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.acquirer.acquireStop();
    	Robot.tunnel.tunnelStop();
    }

    protected void interrupted() {
    	Robot.acquirer.acquireStop();
    	Robot.tunnel.tunnelStop();
    }
}