package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStop extends Command {
	
	private double time;
	
	public DriveStop(double inputTime) {
        requires(Robot.kDrivetrain);
        
        time = inputTime;
		System.out.printf("DriveStop called with Time %f%n", inputTime);

    }

    protected void initialize() {
    	Robot.kDrivetrain.driveStop();
    	setTimeout(time);
    }

    protected void execute() {
    	Robot.kDrivetrain.driveStop();
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.kDrivetrain.driveStop();
    }
	
	protected void interrupted() {
    	Robot.kDrivetrain.driveStop();
	}
}