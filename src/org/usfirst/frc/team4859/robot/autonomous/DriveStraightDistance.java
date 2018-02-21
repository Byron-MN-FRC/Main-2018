package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightDistance extends Command {

	private double time;
	private double distance;
	
    public DriveStraightDistance(double inputDistance, double inputTime) {
        requires(Robot.kDrivetrain);
        distance = Robot.driveEncoderUnitConversion(inputDistance);
        time = inputTime;
		System.out.printf("DriveStraight called with inputDistance %f, calculated Distance %f, Time %f%n", 
															inputDistance, distance, inputTime);
    }

    protected void initialize() {
    	setTimeout(time);
		Drivetrain.motorLeftMaster.setSelectedSensorPosition(0, 0, 10);
		Drivetrain.motorRightMaster.setSelectedSensorPosition(0, 0, 10);
    	Robot.kDrivetrain.driveStraightDistance(distance);
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