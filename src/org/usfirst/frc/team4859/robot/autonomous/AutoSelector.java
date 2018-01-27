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
	
	private void deliverCube() {
		if (RobotMap.targetScale) {
			System.out.println("Lift mechanism command");
		} 
		System.out.println("Deliver cube command");		
	}
	
	private void driveForward() {
		System.out.println("Drive to switch");	
		if (RobotMap.targetScale) {
			System.out.println("Driver further to scale");
		} 
	}

	private void turn(char direction) {
		System.out.println("Turn to " + direction);	
	}

	private void driveToWall() {
		System.out.println("Drive toward wall");	
		if (location == 'C') {
			System.out.println("Drive further (started at center)");
		}
	}
	
	private void driveAroundSwitch(char direction) {
		System.out.println("Drive Around Switch to " + direction);
		if (RobotMap.targetScale) {
			System.out.println("Driver further to scale");
		} 
	}

	public void driveToTarget() {
		
		// Determine path to target based on starting position of robot
		switch(location) {
		case 'C':
			turn(targetSide);
			driveToWall();
			turn(oppositeTargetSide);
			driveForward();
			deliverCube();
			break;
		case 'L':
			turn('L');
			driveToWall();
			turn('R');
			if (targetSide == 'L') {
				driveForward();
			} else {
				driveAroundSwitch('R');
			}
			deliverCube();
			break;
		case 'R':
			turn('R');
			driveToWall();
			turn('L');
			if (targetSide == 'R') {
				driveForward();
			} else {
				driveAroundSwitch('L');
			}
			deliverCube();
			break;
		default:
			System.out.println("Do Nothing");
	    	addSequential(new DriveStop(0));
			break;
		} 		
	}


	
}
