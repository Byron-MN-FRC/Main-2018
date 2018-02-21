package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
	
	private double time = 0;
	
    public Shoot() {
    	requires(Robot.tunnel);
    	requires(Robot.lifter);
    	time = 0;
    }
    
    public Shoot(double inputTime) {
    	requires(Robot.tunnel);
    	requires(Robot.lifter);
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	Robot.lifter.liftToHeight(RobotMap.liftSetHeight);
    	System.out.println("Shoot command ran");
    }

    protected void execute() {
    	Robot.tunnel.tunnelShoot(RobotMap.kTunnelShootSpeed);
    }

    protected boolean isFinished() {
    	if (time <= 0) return false;
    	else return isTimedOut();
    }

    protected void end() {
    	Robot.tunnel.tunnelStop();
    	Robot.lifter.liftDown(RobotMap.kLiftDownSpeed);
    }

    protected void interrupted() {
    	Robot.tunnel.tunnelStop();
    	Robot.lifter.liftDown(RobotMap.kLiftDownSpeed);
    }
}