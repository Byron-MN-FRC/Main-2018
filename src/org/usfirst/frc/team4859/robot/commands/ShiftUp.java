package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;

public class ShiftUp extends Command {
	
    public ShiftUp() {
    	requires(Robot.shifters);
    }

    protected void initialize() {
    	Robot.shifters.pneumaticShiftUp();
    	Drivetrain.motorLeftMaster.configMotionAcceleration(RobotMap.kHighGearAcceleration, RobotMap.kTimeoutMs);
		Drivetrain.motorRightMaster.configMotionCruiseVelocity(RobotMap.kHighGearCruiseVelocity, RobotMap.kTimeoutMs);
		System.out.println("ShiftUp command ran");
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.shifters.pneumaticShiftUp();
    }

    protected void interrupted() {
    }
}