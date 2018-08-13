package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

public class SetLiftVault extends Command {
    
    public SetLiftVault() {
    	requires(Robot.set);
    }

    protected void initialize() {
    	RobotMap.liftSetHeight = "vault";
    	System.out.println("SetLiftVault command ran");
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