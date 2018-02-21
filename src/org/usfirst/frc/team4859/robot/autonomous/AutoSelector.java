package org.usfirst.frc.team4859.robot.autonomous;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.commands.LiftToHeight;
import org.usfirst.frc.team4859.robot.commands.ShiftDown;
import org.usfirst.frc.team4859.robot.commands.Shoot;
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
		addSequential(new DriveStraightDistance(45, 45/RobotMap.secondsRatio));
		turn(targetSide, 90);
		addSequential(new DriveStraightDistance(65*multiplier, 65/RobotMap.secondsRatio*multiplier)); // toward outside wall
		if (targetSide == 'L') turn('R', 90);
		else turn('L', 90);
		if(RobotMap.targetScale) {
			System.out.println("lift to scale height");
			addSequential(new DriveStraightDistance(205, 205/RobotMap.secondsRatio));
			addParallel(new LiftToHeight("scaleNorm", 2));
			addSequential(new DriveStop(0.5));
			if (targetSide == 'L') turn('R', 90);
			else turn('L', 90);
			
		} else {
			System.out.println("lift to switch height");
			addParallel(new LiftToHeight("switch", 2));
			addSequential(new DriveStop(0.5));
			addSequential(new DriveStraightDistance(50, 50/RobotMap.secondsRatio));
		}
	}
	
	public void driveSameSide() {
		if (RobotMap.targetScale) {
			System.out.println("Lift to scale height");
			addSequential(new DriveStraightDistance(280, 280/RobotMap.secondsRatio));
			addParallel(new LiftToHeight("scaleNorm", 2));
			addSequential(new DriveStop(0.5));
			turn(oppositeSide, 90);
			addSequential(new DriveStraightDistance(10, 10/RobotMap.secondsRatio));
		} else {
			System.out.println("Lift to switch height");
			addSequential(new DriveStraightDistance(150, 150/RobotMap.secondsRatio));
			addParallel(new LiftToHeight("switch", 2));
			addSequential(new DriveStop(0.5));
			turn(oppositeSide, 90);
			addSequential(new DriveStraightDistance(24, 24/RobotMap.secondsRatio));
		}
    }
	
	
	public void turn (char direction, double degrees) {
		// Turn to direction specified
		if (direction == 'R') addSequential(new RobotTurnDegrees(degrees));
		else addSequential(new RobotTurnDegrees(-degrees));	
	}
	
	public void deliverCube () {
		System.out.println("Shoot cube out front");
		addSequential(new Shoot(2));
	}
	
	public void driveOppositeSide() {
		// Drive past switch
		addSequential(new DriveStraightDistance(205, 205/RobotMap.secondsRatio));
		turn(oppositeSide,90); // Turn away from starting side
		// Cross the field
		addSequential(new DriveStraightDistance(200, 200/RobotMap.secondsRatio));
		
		// Go to scale or switch 
		if(RobotMap.targetScale) {
			turn(location,90);
			System.out.println("lift to scale height");
			addSequential(new DriveStraightDistance(24,24/RobotMap.secondsRatio));
			addParallel(new LiftToHeight("scaleNorm", 2));
			addSequential(new DriveStop(0.5));
		} else {
			System.out.println("lift to switch height");
			addParallel(new LiftToHeight("switch", 2));
			addSequential(new DriveStop(0.5));
			turn(oppositeSide,135);
			//addSequential(new DriveStraightDistance(12,.5));
		}
	}
	
	public void driveToTarget() {
		// Delay start based on drive team input
		addParallel(new ShiftDown());
		addParallel(new LiftToHeight("default", 1));
		addSequential(new DriveStop(RobotMap.delayInSeconds));
		System.out.printf("%s Autonomous--> [Location=%c] [Scale=%b] [Target Side=%c]%n", 
				new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
				location, RobotMap.targetScale, targetSide);

		// Determine path to target based on starting position of robot
		switch(location) {
		case 'C':
			driveFromCenter();
			deliverCube();
			break;
		case 'L':
			if (targetSide == 'L') driveSameSide();
			else driveOppositeSide();
			deliverCube();
			break;
		case 'R':
			if (targetSide == 'R') driveSameSide();
			else driveOppositeSide();
			deliverCube();
			break;
		default:
			System.out.println("SHOULD NOT EVER GET HERE:Drive Forward");
			addSequential(new DriveStraightDistance(108, 5));
			break;
		} 		
	}
}