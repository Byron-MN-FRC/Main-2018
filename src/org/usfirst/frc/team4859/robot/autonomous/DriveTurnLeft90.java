package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveTurnLeft90 extends Command {
	
    public DriveTurnLeft90() {
        requires(Robot.kDrivetrain);
    }

    protected void initialize() {
    	setTimeout(1);
    	Drivetrain.motorLeftMaster.setSelectedSensorPosition(0, 0, 10);
		Drivetrain.motorRightMaster.setSelectedSensorPosition(0, 0, 10);
    	Robot.kDrivetrain.driveTurnDistance(27200);
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