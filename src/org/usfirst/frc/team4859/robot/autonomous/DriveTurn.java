package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveTurn extends Command {

	private double time;
	private double speed;
	
    public DriveTurn(double inputSpeed, double inputTime) {
    	//turn right is positive, left is negative
        requires(Robot.kDrivetrain);
        speed = inputSpeed;
        time = inputTime;
		System.out.printf("DriveTurn called with Speed %f, Time %f%n", 
				inputSpeed, inputTime);
}

    protected void initialize() {
    	setTimeout(time);
    }

    protected void execute() {
    	Robot.kDrivetrain.driveTurn(speed);
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