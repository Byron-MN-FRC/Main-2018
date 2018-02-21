package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class LiftStop extends Command {
	
    public LiftStop() {
    	requires(Robot.lifter);
    }

    protected void initialize() {
    	Robot.lifter.liftStop();
    	System.out.println("LiftStop command ran");
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.lifter.liftStop();
    }

    protected void interrupted() {
    	Robot.lifter.liftStop();
    }
}