package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class SetForward extends Command {
    
    public SetForward() {
    	requires(Robot.set);
    }

    protected void initialize() {
    	RobotMap.liftDirectionFront = true;
    	System.out.println("SetForward command ran");
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}