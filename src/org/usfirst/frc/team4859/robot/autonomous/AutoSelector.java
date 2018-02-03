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
		
		//double speed = 0.56D;
		//double turnspeed = 0.49D;
		// Determine path to target based on starting position of robot
		switch(location) {
		case 'C':
			//driving forward from wall
			//addSequential(new DriveStraight(speed,4));
			addSequential(new DriveStraightDistance(96,6));
			if (targetSide == 'L') {
				//First left turn
				//addSequential(new DriveTurn(-turnspeed,2.05));
				addSequential(new DriveTurnDistance(-90,2));
				
				//Going to the wall
				//addSequential(new DriveStraight(speed, 3.2));
				addSequential(new DriveStraightDistance(54, 4));
				
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
					addSequential(new DriveStraightDistance(162,12));
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
	}
}