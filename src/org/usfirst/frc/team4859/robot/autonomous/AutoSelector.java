package org.usfirst.frc.team4859.robot.autonomous;

import org.usfirst.frc.team4859.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSelector extends CommandGroup {

	private char location = ' ';
	private char targetSide = ' ';
	private char oppositeTargetSide = ' ';

	public AutoSelector(char inputLocation, char inputTargetSide) {
		location = inputLocation;
		targetSide = inputTargetSide;
		oppositeTargetSide = 'L'; // default
		if (targetSide == 'L') { oppositeTargetSide = 'R'; }
	}
	
/*	private void deliverCube() {
		if (RobotMap.targetScale) {
			System.out.println("Lift mechanism command");
		} 
		System.out.println("Deliver cube command");		
	}
*/

	public void driveToTarget() {
		
		// Determine path to target based on starting position of robot
		switch(location) {
		case 'C':
			addSequential(new DriveStraight(.2,2));
			if (targetSide == 'L') {
				addSequential(new DriveTurn(-.2,2));
				addSequential(new DriveTurn(.2,2));
				
				if(RobotMap.targetScale) addSequential(new DriveStraight(.2,8));
				else addSequential(new DriveStraight(0.2, 3));
				
				addSequential(new DriveTurn(.2,5));
			} else {
				addSequential(new DriveTurn(.2,2));
				addSequential(new DriveTurn(-.2,2));
				
				if(RobotMap.targetScale) addSequential(new DriveStraight(0.2,8));
				else addSequential(new DriveStraight(0.2, 5));

				addSequential(new DriveTurn(-.2,5));
			}
			//deliverCube();
			break;
		case 'L':
			addSequential(new DriveTurn(-.2,5));
			addSequential(new DriveTurn(.2,5));
			if (targetSide == 'L') {
				
				if(RobotMap.targetScale) addSequential(new DriveStraight(.2,8));
				else addSequential(new DriveStraight(0.2, 5));
				
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
			addSequential(new DriveTurn(.2,5));
			addSequential(new DriveTurn(-.2,5));
			if (targetSide == 'R') {
				
				if(RobotMap.targetScale) addSequential(new DriveStraight(.2,8));
				else addSequential(new DriveStraight(0.2, 5));
				
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
			System.out.println("Drive Forward");
	    	addSequential(new DriveStraight(.3,5));
			break;
		} 		
	}


	
}
