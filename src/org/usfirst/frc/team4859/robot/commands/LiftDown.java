package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class LiftDown extends Command {
	
    public LiftDown() {
    	requires(Robot.lifter);
    }

    protected void initialize() {
    	System.out.println("LiftDown command ran");
    }

    protected void execute() {
    	Robot.lifter.liftDown(RobotMap.kLiftStage1DownSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.lifter.liftDown(RobotMap.kLiftStage1DownSpeed);
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    }
}