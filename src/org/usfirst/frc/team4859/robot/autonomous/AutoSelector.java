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
		addSequential(new DriveStraightDistance(75,2));
		turn(targetSide, 90);
		addSequential(new DriveStraightDistance(75*multiplier, 1.5*multiplier)); // toward outside wall
		if (targetSide == 'L') turn('R', 90);
		else turn('L', 90);

		if(RobotMap.targetScale) {
			addSequential(new DriveStraightDistance(225,4.5));
			if (targetSide == 'L') turn('R', 90);
			else turn('L', 90);
		} else
			addSequential(new DriveStraightDistance(12,.25));	
	}
	
	public void driveSameSide() {
		if (RobotMap.targetScale) {
			addSequential(new DriveStraightDistance(275,5));
			turn(oppositeSide,45);
		} else {
			addSequential(new DriveStraightDistance(150,3));
			turn(oppositeSide,90);
			addSequential(new DriveStraightDistance(12,0.25));	
		}
    }
	
	
	public void turn (char direction, double degrees) {
		// Turn to direction specified
		if (direction == 'R') addSequential(new RobotTurnDegrees(degrees));
		else addSequential(new RobotTurnDegrees(-degrees));	
	}
	
	public void deliverCube () {
		double height = RobotMap.targetScale ? 36 : 12;
		System.out.println("Lift cube to switch" + height);
		System.out.println("Shoot cube out front");
	}
	
	public void driveOppositeSide() {
		// Drive past switch
		addSequential(new DriveStraightDistance(225, 225/RobotMap.secondsRatio));
		turn(oppositeSide,90); // Turn away from starting side
		// Cross the field
		addSequential(new DriveStraightDistance(200, 200/RobotMap.secondsRatio));
		
		// Go to scale or switch 
		if(RobotMap.targetScale) {
			turn(location,90);
		} else {
			turn(oppositeSide,90);
			addSequential(new DriveStraightDistance(12,.5));
		}
	}
	
	
	public void driveToTarget() {
		// Delay start based on drive team input
		addSequential(new DriveStop(RobotMap.delayInSeconds));
		
		System.out.printf("%s Autonomous--> [Location=%c] [Scale=%b] [Target Side=%c]%n", 
				new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
				location, RobotMap.targetScale, targetSide);

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
}