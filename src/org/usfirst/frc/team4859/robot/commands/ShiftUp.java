package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShiftUp extends Command {
	
    public ShiftUp() {
    	requires(Robot.shifters);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shifters.pneumaticShiftUp();
    	Drivetrain.motorLeftMaster.configMotionAcceleration(RobotMap.kHighGearAcceleration, RobotMap.kTimeoutMs);
		Drivetrain.motorRightMaster.configMotionCruiseVelocity(RobotMap.kHighGearCruiseVelocity, RobotMap.kTimeoutMs);
		SmartDashboard.putString("Shift", "High Gear");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shifters.pneumaticShiftUp();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}