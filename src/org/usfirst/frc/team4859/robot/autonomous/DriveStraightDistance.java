package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
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
		Drivetrain.motorLeftMaster.setSelectedSensorPosition(0, RobotMap.kPIDSlot, RobotMap.kTimeoutMs);
		Drivetrain.motorRightMaster.setSelectedSensorPosition(0, RobotMap.kPIDSlot, RobotMap.kTimeoutMs);
    	Robot.kDrivetrain.driveStraightDistance(distance);
    }

    protected void execute() {
    		Drivetrain.motorLeftMaster.configMotionCruiseVelocity(RobotMap.kLowGearCruiseVelocity - (int)RobotMap.gyroCorrection, RobotMap.kTimeoutMs);
    		Drivetrain.motorRightMaster.configMotionCruiseVelocity(RobotMap.kLowGearCruiseVelocity + (int)RobotMap.gyroCorrection, RobotMap.kTimeoutMs);
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