package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
	
	private String position = "";
	private double time = 0;
	
    public Shoot() {
    	requires(Robot.tunnel);
    	requires(Robot.lifter);
    	time = 0;
    }
    
    public Shoot(String inputPosition, double inputTime) {
    	requires(Robot.tunnel);
    	requires(Robot.lifter);
    	position = inputPosition;
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	if (time > 0) Robot.lifter.liftToHeight(position);
    	else Robot.lifter.liftToHeight(RobotMap.liftSetHeight);
    	System.out.println("Shoot command ran");
    }

    protected void execute() {
    	
    	if(RobotMap.liftDirectionFront) Robot.tunnel.tunnelShoot(RobotMap.kTunnelShootSpeed);
    	else {
    		if (Lifter.motorLiftStage1.getSelectedSensorPosition(0) < 15000);
        	else Robot.tunnel.tunnelShoot(-RobotMap.kTunnelShootSpeed);
    	}
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.tunnel.tunnelStop();
    	if (time > 0) Robot.lifter.liftToHeight(position);
    	else Robot.lifter.liftToHeight(RobotMap.liftSetHeight);
    }

    protected void interrupted() {
    	Robot.tunnel.tunnelStop();
    	Robot.lifter.liftStop();
    }
}