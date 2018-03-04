package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.command.Command;

public class LiftDefault extends Command {
	
    public LiftDefault() {
    	requires(Robot.lifter);
    }

    protected void initialize() {
    	System.out.println("LiftDown command ran");
    	Robot.lifter.liftToHeight("default");
    	Robot.cameraBackward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
		Robot.cameraForward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.lifter.liftToHeight("default");
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    }
}