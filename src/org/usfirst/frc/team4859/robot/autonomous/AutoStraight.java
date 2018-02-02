package org.usfirst.frc.team4859.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoStraight extends CommandGroup {
	
    public  AutoStraight() {
		System.out.println("In AutoStraight.java, adding DriveStraight");

    	addSequential(new DriveStraight(0.4, 4));
    }
}