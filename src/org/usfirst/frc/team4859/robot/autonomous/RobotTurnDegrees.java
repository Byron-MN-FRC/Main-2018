package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class RobotTurnDegrees extends Command {
	double degrees = 0;
    public RobotTurnDegrees(double inputdegrees) {
        requires(Robot.kDrivetrain);
        degrees = inputdegrees;
		System.out.printf("RobotTurnDegrees called with inputDegrees %f%n", 
				degrees);
    }

    protected void initialize() {
    	setTimeout(1.5);
    	Drivetrain.motorLeftMaster.setSelectedSensorPosition(0, 0, 10);
		Drivetrain.motorRightMaster.setSelectedSensorPosition(0, 0, 10);
    	Robot.kDrivetrain.driveTurnDistance(degrees * RobotMap.decoderTurnRatio);
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