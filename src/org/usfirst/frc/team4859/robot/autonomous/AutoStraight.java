package org.usfirst.frc.team4859.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoStraight extends CommandGroup {
	
    public  AutoStraight() {
    	addSequential(new DriveStraight(0.3, 2));
    }
}