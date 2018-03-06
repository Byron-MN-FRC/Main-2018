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
		addSequential(new DriveStraightDistance(45, 2.5));
		turn(targetSide, 90);
		addSequential(new DriveStraightDistance(62*multiplier, 4*multiplier)); // toward outside wall
		if (targetSide == 'L') turn('R', 90);
		else turn('L', 90);
		if(RobotMap.targetScale) {
			System.out.println("lift to scale height");
			addSequential(new DriveStraightDistance(205, 6));
			addParallel(new LiftToHeight("scaleNorm", 2));
			addSequential(new DriveStop(0.5));
			if (targetSide == 'L') turn('R', 90);
			else turn('L', 90);
			
		} else {
			System.out.println("lift to switch height");
			addParallel(new LiftToHeight("switch", 2));
			addSequential(new DriveStraightDistance(40, 3));
		}
	}
	
	public void driveSameSide() {
		if (RobotMap.targetScale) {
			System.out.println("Lift to scale height");
			//addSequential(new DriveStraightDistance(297, 8));
			addSequential(new DriveStraightDistance(258, 7));
			addParallel(new LiftToHeight("scaleHigh", 4));
			addSequential(new DriveStop(1));
			turn(oppositeSide, 45);
			addSequential(new DriveStraightDistance(13, 1));
		} else {
			System.out.println("Lift to switch height");
			addSequential(new DriveStraightDistance(148, 4.75));
			addParallel(new LiftToHeight("switch", 2));
			//addSequential(new DriveStop(0.5));
			turn(oppositeSide, 90);
			addSequential(new DriveStraightDistance(18, 5));
		}
    }
	
	
	public void turn (char direction, double degrees) {
		// Turn to direction specified
		if (direction == 'R') addSequential(new RobotTurnDegrees(degrees));
		else addSequential(new RobotTurnDegrees(-degrees));	
	}
	
	public void deliverCube () {
		System.out.println("Shoot cube out front");
		addSequential(new Shoot(1));
		addSequential(new LiftToHeight("default", 1));
	}
	
	public void driveOppositeSide() {
		// Drive past switch
		
		// If the scale is on the same side don't push the cubes to the other side
		if(RobotMap.scaleSameSide && !RobotMap.switchSameSide) addSequential(new DriveStraightDistance(216, 5));
		else addSequential(new DriveStraightDistance(206, 5));
		
		turn(oppositeSide,90); // Turn away from starting side
		// Cross the field
		
		
		// Go to scale or switch 
		if(RobotMap.targetScale) {
			addSequential(new DriveStraightDistance(194, 4.75));
			addParallel(new LiftToHeight("scaleNorm", 4));
			turn(location, 90);
			System.out.println("lift to scale height");
			addSequential(new DriveStraightDistance(45, 2));
			addSequential(new DriveStop(1));
		} else {
			// If the cubes aren't being pushed, then don't overshoot and turn backwards to shoot
			if(RobotMap.scaleSameSide && !RobotMap.switchSameSide) {
				addSequential(new DriveStraightDistance(185, 5.50));
				System.out.println("lift to switch height");
				addParallel(new LiftToHeight("switch", 2));
				addSequential(new DriveStop(0.5));
				turn(oppositeSide,90);
				addSequential(new DriveStraightDistance(8,.5));
			} else {
				addSequential(new DriveStraightDistance(213, 5.50));
				System.out.println("lift to switch height");
				addParallel(new LiftToHeight("switch", 2));
				addSequential(new DriveStop(0.5));
				turn(oppositeSide,145);
				addSequential(new DriveStraightDistance(8,.5));
			}
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
			if (RobotMap.shootCubeAuton) { deliverCube(); }
			break;
		case 'L':
			if (targetSide == 'L') driveSameSide();
			else driveOppositeSide();
			if (RobotMap.shootCubeAuton) { deliverCube(); }
			break;
		case 'R':
			if (targetSide == 'R') driveSameSide();
			else driveOppositeSide();
			if (RobotMap.shootCubeAuton) { deliverCube(); }
			break;
		default:
			System.out.println("SHOULD NOT EVER GET HERE:Drive Forward");
			addSequential(new DriveStraightDistance(296, 7));
			break;
		} 		
	}
}