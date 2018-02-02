package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightDistance extends Command {

	private double time;
	private double distance;
	
    public DriveStraightDistance(double inputDistance, double inputTime) {
        requires(Robot.kDrivetrain);
        
        distance = Robot.encoderUnitConversion(inputDistance);
        time = inputTime;
		System.out.printf("DriveStraight called with inputDistance %f, calculated Distancce %f, Time %f%n", 
				inputDistance, distance, inputTime);

    }

    protected void initialize() {
    	setTimeout(time);
    }

    protected void execute() {
    	Robot.kDrivetrain.driveStraightDistance(distance);
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