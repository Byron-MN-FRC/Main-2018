package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;

import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.command.Command;

public class ShootStop extends Command {
	
	private double time = 0;
	
    public ShootStop() {
    	requires(Robot.tunnel);
    	requires(Robot.lifter);
    	time = 0;
    }
    
    public ShootStop(double inputTime) {
    	requires(Robot.tunnel);
    	requires(Robot.lifter);
    	time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    	Robot.lifter.liftToHeight("default");
    	System.out.println("ShootStop command ran");
    	Robot.cameraBackward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
		Robot.cameraForward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
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
    	Robot.lifter.liftDown(RobotMap.kLiftDownSpeed);
    }

    protected void interrupted() {
    	Robot.tunnel.tunnelStop();
    	Robot.lifter.liftStop();
    }
}