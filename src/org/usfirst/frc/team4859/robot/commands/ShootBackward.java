package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

public class ShootBackward extends Command {
	
	private double tunnelSpeed = 0;
	private double time = 0;
	
    public ShootBackward(double inputTunnelShootSpeed) {
    	requires(Robot.tunnel);
    	tunnelSpeed = -inputTunnelShootSpeed;
    	time = 0;
    }
    
    public ShootBackward(double inputTunnelShootSpeed, double inputTime) {
    	requires(Robot.tunnel);
    	tunnelSpeed = -inputTunnelShootSpeed;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	System.out.println("Acquire command ran");
    }

    protected void execute() {
    	if ((-Lifter.motorLiftStage1.getSelectedSensorPosition(0) + Lifter.motorLiftStage2.getSelectedSensorPosition(0)) < 10000);
    	else Robot.tunnel.tunnelShoot(tunnelSpeed);
    	
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