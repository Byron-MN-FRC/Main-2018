package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.commands.LiftToHeight;
import org.usfirst.frc.team4859.robot.commands.ShiftDown;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoStraight extends CommandGroup {
	
    public  AutoStraight() {
		System.out.printf("AutoStraight called");
		
		addParallel(new ShiftDown());
		addParallel(new LiftToHeight("default", 1));
    	addSequential(new DriveStraightDistance(108, 5));
    }
}