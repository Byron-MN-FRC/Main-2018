package org.usfirst.frc.team4859.robot.autonomous;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSelector extends CommandGroup {

	private char location = ' ';
	private char targetSide = ' ';

	public AutoSelector() { 
		location = RobotMap.location;
		targetSide = RobotMap.targetSide;
		driveToTarget();
	}
	


	public void driveToTarget() {
		addSequential(new DriveStop(RobotMap.delayInSeconds));
		System.out.printf("%s Autonomous--> [Location=%c] [Target=%s] [Target Side=%c]%n", 
				new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
				location, RobotMap.targetName, targetSide);
		
		// Determine path to target based on starting position of robot
		switch(location) {
		case 'C':
			addSequential(new DriveStraight(.4,4));
			if (targetSide == 'L') {
				addSequential(new DriveTurn(-.4,4));
				
				if(RobotMap.targetScale) addSequential(new DriveStraight(.4,8));
				else addSequential(new DriveStraight(0.4, 3));
				
				addSequential(new DriveTurn(.4,5));
			} else {
				addSequential(new DriveTurn(.4,2));
				
				if(RobotMap.targetScale) addSequential(new DriveStraight(0.4,8));
				else addSequential(new DriveStraight(0.4, 5));

				addSequential(new DriveTurn(-.4,5));
			}
			//deliverCube();
			break;
		case 'L':
			addSequential(new DriveTurn(-.4,5));
			
			if (targetSide == 'L') {
				if(RobotMap.targetScale) addSequential(new DriveStraight(.4,8));
				else addSequential(new DriveStraight(0.4, 5));
			} else {
				addSequential(new DriveStop(0));
				//driveAroundSwitch('R');
			/*OPPOSITE SIDE SCALE
				if(RobotMap.targetScale) addSequential(new DriveStraight(.3,8));
				else addSequential(new DriveStraight(0.3, 5));
			}
			*/
			//deliverCube();
			break;
			}
		case 'R':
			addSequential(new DriveTurn(.4,5));
			if (targetSide == 'R') {
				
				if(RobotMap.targetScale) addSequential(new DriveStraight(.4,8));
				else addSequential(new DriveStraight(0.4, 5));
				
			} else {
				addSequential(new DriveStop(0));
		/*OPPOSITE SIDE SCALE
				//driveAroundSwitch('L');
				addSequential(new DriveStraight(.3,5));
				addSequential(new DriveTurn(.3,5));
				*/
			}
			//deliverCube();
			break;
		default:
			System.out.println("SHOULD NOT EVER GET HERE:Drive Forward");
	    	addSequential(new DriveStraight(.3,5));
			break;
		} 		
	}
}