package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {

	private double time;
	private double speed;
	
    public DriveStraight(double inputSpeed, double inputTime) {
        requires(Robot.kDrivetrain);
        
        speed = inputSpeed;
        time = inputTime;
    }

    protected void initialize() {
    	setTimeout(time);
    }

    protected void execute() {
    	Robot.kDrivetrain.driveStraight(speed);
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