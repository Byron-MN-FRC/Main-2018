package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShiftDown extends Command {
	
	public ShiftDown() {
    	requires(Robot.shifters);
    }

    protected void initialize() {
    	Robot.shifters.pneumaticShiftDown();
		Drivetrain.motorLeftMaster.configMotionAcceleration(RobotMap.kLowGearAcceleration, RobotMap.kTimeoutMs);
		Drivetrain.motorRightMaster.configMotionAcceleration(RobotMap.kLowGearAcceleration, RobotMap.kTimeoutMs);
		Drivetrain.motorLeftMaster.configMotionCruiseVelocity(RobotMap.kLowGearCruiseVelocity, RobotMap.kTimeoutMs);
		Drivetrain.motorRightMaster.configMotionCruiseVelocity(RobotMap.kLowGearCruiseVelocity, RobotMap.kTimeoutMs);
		RobotMap.kRampRate = RobotMap.kLowGearRampRate;
//		RobotMap.kRampRateForwardLimit = RobotMap.kRampRateForwardLowGearLimit;
//		RobotMap.kRampRateBackwardLimit = RobotMap.kRampRateBackwardLowGearLimit;
		System.out.println("ShiftDown command ran");
		SmartDashboard.putBoolean("Shifted Up", false);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    	Robot.shifters.pneumaticShiftDown();
    }

    protected void interrupted() {
    }
}