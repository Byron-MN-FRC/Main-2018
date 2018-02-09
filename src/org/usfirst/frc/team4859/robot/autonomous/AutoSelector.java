package org.usfirst.frc.team4859.robot.autonomous;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team4859.robot.RobotMap;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSelector extends CommandGroup {

	private char location = ' ';
	private char targetSide = ' ';
	private char oppositeSide = ' ';

	public AutoSelector() { 
		location = RobotMap.location;
		targetSide = RobotMap.targetSide;
		if (location == 'L') 
			oppositeSide = 'R';
		else
			oppositeSide = 'L';
		driveToTarget();
	}
	
	public void driveFromCenter() {
		// Drive to same side target (scale is 2x distance of switch & 1/2 distance in)
		double multiplier = RobotMap.targetScale ? 1.5 : 1 ; 
		// Driving forward from wall in all conditions
		addSequential(new DriveStraightDistance(100,2));
		if (targetSide == 'L') {
			addSequential(new DriveTurnLeft90()); 
			addSequential(new DriveStraightDistance(75*multiplier, 1.5*multiplier)); // toward outside wall
			addSequential(new DriveTurnRight90()); // turn toward (switch & scale)
		} else {
			addSequential(new DriveTurnRight90());
			addSequential(new DriveStraightDistance(50*multiplier, 1*multiplier)); // toward outside wall				
			addSequential(new DriveTurnLeft90());
		}
		if(RobotMap.targetScale) {
			addSequential(new DriveStraightDistance(225,4.5));
			turn(oppositeSide);
		} else
			addSequential(new DriveStraightDistance(12,.25));	
	}
	public void driveSameSide() {
		// Drive to same side target (scale is 2x distance of switch & 1/2 distance in)
		double multiplier = RobotMap.targetScale ? 2 : 1 ; 
		addSequential(new DriveStraightDistance(150*multiplier,3*multiplier));
		if (location == 'L') 
			addSequential(new DriveTurnRight90());
		else 
			addSequential(new DriveTurnLeft90());		
		addSequential(new DriveStraightDistance(24/multiplier,0.5/multiplier));	
	}
	
	public void turn (char direction) {
		// Turn to direction specified
		if (direction == 'R') addSequential(new DriveTurnRight90());
		else addSequential(new DriveTurnLeft90());	
	}
	
	public void deliverCube () {
		double height = RobotMap.targetScale ? 36 : 12;
		System.out.println("Lift cube to switch" + height);
		System.out.println("Shoot cube out front");
	}
	
	public void driveOppositeSide() {
		// Drive past switch
		addSequential(new DriveStraightDistance(225, 4.5));

		// Turn away from starting side
		turn(oppositeSide);

		// Cross the field
		addSequential(new DriveStraightDistance(225, 4.5));
		
		// Go to scale or switch 
		// (scale is 1/2 distance of switch in)
		double multiplier = 1;
		if(RobotMap.targetScale) {
			multiplier = 2;
			turn(targetSide);
			addSequential(new DriveStraightDistance(75,1.5));
			turn(targetSide);
		} else {
			turn(oppositeSide);
			addSequential(new DriveStraightDistance(75,1.5));
			turn(oppositeSide);
		}
		addSequential(new DriveStraightDistance(24/multiplier,0.5/multiplier));	
	}
	
	
	public void driveToTarget() {
		// Delay start based on drive team input
		addSequential(new DriveStop(RobotMap.delayInSeconds));
		
		System.out.printf("%s Autonomous--> [Location=%c] [Target=%s] [Target Side=%c]%n", 
				new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
				location, RobotMap.targetName, targetSide);
		
		// Determine path to target based on starting position of robot
		switch(location) {
		case 'C':
			driveFromCenter();
			//deliverCube();
			break;
		case 'L':
			if (targetSide == 'L') driveSameSide();
			else driveOppositeSide();
			//deliverCube();
			break;
		case 'R':
			if (targetSide == 'R') driveSameSide();
			else driveOppositeSide();
			//deliverCube();
			break;
		default:
			System.out.println("SHOULD NOT EVER GET HERE:Drive Forward");
			addSequential(new DriveStraightDistance(96,3));
			break;
		} 		
	}

	
	/*	
	 * 	public void driveFromLeft() {
		int multiplier = 1;
		if (RobotMap.targetScale) multiplier = 2;
		if (targetSide == 'L') {
			addSequential(new DriveStraightDistance(150*multiplier,3*multiplier));
			addSequential(new DriveTurnRight90());
		} else {
			// Drive past switch
			addSequential(new DriveStraightDistance(84, 4));
			addSequential(new DriveTurnRight90());
			addSequential(new DriveStraightDistance(160,9));
			if(RobotMap.targetScale) 
				addSequential(new DriveTurnLeft90());
			else
				addSequential(new DriveTurnRight90());
			addSequential(new DriveStraightDistance(84,4));
			if(RobotMap.targetScale) 
				addSequential(new DriveTurnRight90());
			else
				addSequential(new DriveTurnLeft90());
		}
		addSequential(new DriveStraightDistance(24,0.5));	
	}

	public void driveFromRight() {
		int multiplier = 1;
		if (RobotMap.targetScale) multiplier = 2;
		if (targetSide == 'R') {
			addSequential(new DriveStraightDistance(150*multiplier,3*multiplier));
			addSequential(new DriveTurnLeft90());
		} else {
			// Drive past switch
			addSequential(new DriveStraightDistance(84, 4));
			addSequential(new DriveTurnLeft90());
			addSequential(new DriveStraightDistance(150,9));
			if(RobotMap.targetScale) 
				addSequential(new DriveTurnRight90());
			else
				addSequential(new DriveTurnLeft90());
			addSequential(new DriveStraightDistance(84,4));
			if(RobotMap.targetScale) 
				addSequential(new DriveTurnRight90());
			else
				addSequential(new DriveTurnLeft90());
		}
		addSequential(new DriveStraightDistance(24, 0.5));
	}
	
	public void driveToTargetOld() {
		addSequential(new DriveStop(RobotMap.delayInSeconds));
		System.out.printf("%s Autonomous--> [Location=%c] [Target=%s] [Target Side=%c]%n", 
				new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
				location, RobotMap.targetName, targetSide);
		
		//double speed = 0.56D;
		//double turnspeed = 0.49D;
		// Determine path to target based on starting position of robot
		switch(location) {
		case 'C':
			//driving forward from wall
			//addSequential(new DriveStraight(speed,4));
			System.out.println("Case C here");
			addSequential(new DriveStraightDistance(96,3));
			if (targetSide == 'L') {
				//First left turn
				//addSequential(new DriveTurn(-turnspeed,2.05));
				addSequential(new DriveTurnDistance(-90,1));
				
				//Going to the wall
				//addSequential(new DriveStraight(speed, 3.2));
				addSequential(new DriveStraightDistance(66, 2));
				
				if(RobotMap.targetScale) {
					//addSequential(new DriveStraight(speed,3));
					addSequential(new DriveStraightDistance(114,8));

				}
				//Turns toward target (switch and scale)
				addSequential(new DriveTurnDistance(90,2));
				//addSequential(new DriveTurn(turnspeed,2.05));
			} else {
				addSequential(new DriveTurnDistance(90,2));
				//addSequential(new DriveTurn(turnspeed,2.05));
				addSequential(new DriveStraightDistance(54, 4));
				
				if(RobotMap.targetScale) {
					//addSequential(new DriveStraight(0.4,8));
					addSequential(new DriveStraightDistance(114,8));
				}
				addSequential(new DriveTurnDistance(-90,2));
				//addSequential(new DriveTurn(-turnspeed,2.05));
			}
			//addSequential(new DriveStraight(speed,1.5));
			addSequential(new DriveStraightDistance(6,1.5));
			//deliverCube();
			break;
		case 'L':
			
			if (targetSide == 'L') {
				if(RobotMap.targetScale) {
					addSequential(new DriveStraightDistance(324,18));
					//addSequential(new DriveStraight(speed,12));
				} else {
					addSequential(new DriveStraightDistance(84, 4));
					//addSequential(new DriveStraight(speed, 8));
				}
				addSequential(new DriveTurnDistance(-90,2));
				//addSequential(new DriveTurn(turnspeed,2.05));
				
			} else {
					if(RobotMap.targetScale) {
						addSequential(new DriveStraightDistance(150,9));
						addSequential(new DriveTurnDistance(90,5));
						addSequential(new DriveStraightDistance(96,6));
						addSequential(new DriveTurnDistance(-90,5));
					 } else{ 
						addSequential(new DriveStraightDistance(150,9));
						addSequential(new DriveTurnDistance(90,5));
						addSequential(new DriveStraightDistance(96,6));
						addSequential(new DriveTurnDistance(90,5));
					 }
			}
			addSequential(new DriveStraightDistance(6,1));
			//addSequential(new DriveStraight(speed, 1.5));
			break;
		case 'R':
			if (targetSide == 'R') {
				
				if(RobotMap.targetScale) {
					addSequential(new DriveStraightDistance(162,12));
					//addSequential(new DriveStraight(speed,12));
				} else{
					addSequential(new DriveStraightDistance(84, 4));
					//addSequential(new DriveStraight(speed, 8));
				}
				addSequential(new DriveTurnDistance(90,2));
				//addSequential(new DriveTurn(-turnspeed,2.05));
			} else {
				if(RobotMap.targetScale) {
					addSequential(new DriveStraightDistance(150,9));
					addSequential(new DriveTurnDistance(-90,5));
					addSequential(new DriveStraightDistance(96,6));
					addSequential(new DriveTurnDistance(90,5));
				}else{ 
					addSequential(new DriveStraightDistance(150,9));
					addSequential(new DriveTurnDistance(-90,5));
					addSequential(new DriveStraightDistance(96,6));
					addSequential(new DriveTurnDistance(-90,5));
					 					}
			}
			addSequential(new DriveStraightDistance(6,1));
			//addSequential(new DriveStraight(speed, 1.5));
			//deliverCube();
			break;
		default:
			System.out.println("SHOULD NOT EVER GET HERE:Drive Forward");
	    	addSequential(new DriveStraight(.3,5));
			break;
		} 		
	}*/
}