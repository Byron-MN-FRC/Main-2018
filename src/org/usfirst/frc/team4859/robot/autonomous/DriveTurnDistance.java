package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTurnDistance extends Command {

	private double time;
	private double distance;
	
    public DriveTurnDistance(double inputAngle, double inputTime) {
        requires(Robot.kDrivetrain);
        
        distance = Robot.angleToDistance(inputAngle);
        time = inputTime;
		System.out.printf("DriveStraight called with inputAngle %f, calculated Distance %f, Time %f%n", 
				inputAngle, distance, inputTime);

    }

    protected void initialize() {
    	setTimeout(time);
    	Drivetrain.motorLeftMaster.setSelectedSensorPosition(0, 0, 10);
		Drivetrain.motorRightMaster.setSelectedSensorPosition(0, 0, 10);
    	Robot.kDrivetrain.driveTurnDistance(distance);
    }

    protected void execute() {

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