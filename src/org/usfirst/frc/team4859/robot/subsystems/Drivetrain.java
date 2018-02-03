package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.ThrottleLookup.ThrottleLookup;
import org.usfirst.frc.team4859.robot.commands.DriveWithJoystick;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {
	
	
	public static WPI_TalonSRX motorLeftMaster = new WPI_TalonSRX(RobotMap.talonIDLeftMaster);
	public static WPI_TalonSRX motorLeftFollower1 = new WPI_TalonSRX(RobotMap.talonIDLeftFollower1);
	public static WPI_TalonSRX motorLeftFollower2 = new WPI_TalonSRX(RobotMap.talonIDLeftFollower2);
	
	public static WPI_TalonSRX motorRightMaster = new WPI_TalonSRX(RobotMap.talonIDRightMaster);
	public static WPI_TalonSRX motorRightFollower1 = new WPI_TalonSRX(RobotMap.talonIDRightFollower1);
	public static WPI_TalonSRX motorRightFollower2 = new WPI_TalonSRX(RobotMap.talonIDRightFollower2);
	
	public static SpeedControllerGroup drivetrainLeft = new SpeedControllerGroup(motorLeftMaster);
	public static SpeedControllerGroup drivetrainRight = new SpeedControllerGroup(motorRightMaster);
	
	public static DifferentialDrive drivetrain = new DifferentialDrive(drivetrainLeft, drivetrainRight);
	
	public Drivetrain() {
		motorLeftFollower1.set(ControlMode.Follower, RobotMap.talonIDLeftMaster);
		motorLeftFollower2.set(ControlMode.Follower, RobotMap.talonIDLeftMaster);
		
		motorRightFollower1.set(ControlMode.Follower, RobotMap.talonIDRightMaster);
		motorRightFollower2.set(ControlMode.Follower, RobotMap.talonIDRightMaster);
		
		drivetrain.setSafetyEnabled(false);
	}
	
	public void initDefaultCommand () {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public void driveWithJoystick(Joystick joystickP0) {
		double y = -joystickP0.getY();
		double x = -joystickP0.getX();
		double twist = joystickP0.getTwist();
		
		// Apply translations to the values from the controller
		y = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowY", y) : ThrottleLookup.calcJoystickCorrection("NormY", y);
		x = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowX", x) : ThrottleLookup.calcJoystickCorrection("NormX", x);
		twist = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowT", twist) : ThrottleLookup.calcJoystickCorrection("NormT", twist);
		
		SmartDashboard.putString("Robot Mode", (RobotMap.pMode) ? "Slow" : "Normal");	
		
//		SmartDashboard.putNumber("Left 1", motorLeftMaster.getOutputCurrent());
//		SmartDashboard.putNumber("Left 2", motorLeftFollower.getOutputCurrent());
//		SmartDashboard.putNumber("Right 1", motorRightMaster.getOutputCurrent());
//		SmartDashboard.putNumber("Right 2", motorRightFollower.getOutputCurrent());
		
		drivetrain.arcadeDrive(y, twist);
	}
	
	public void driveStraight(double inputSpeed) {
		drivetrain.arcadeDrive(inputSpeed, 0);
	}
	
	public void driveTurn(double inputSpeed) {
		drivetrain.arcadeDrive(0, inputSpeed);
	}
	
	public void driveToWall(double inputSpeed) {
		drivetrain.arcadeDrive(0, inputSpeed);
  }
  
	public void driveStraightDistance(double inputDistance) {
		motorLeftMaster.set(ControlMode.MotionMagic, inputDistance);
		motorRightMaster.set(ControlMode.MotionMagic, inputDistance);
	}
	
	public void driveTurnDistance(double inputDistance) {
		motorLeftMaster.set(ControlMode.MotionMagic, inputDistance);
		motorRightMaster.set(ControlMode.MotionMagic, -inputDistance);
	}
	
	public void driveBackwards(double inputSpeed) {
		drivetrain.arcadeDrive(-inputSpeed, 0);
	}
	
	public void driveStop() {
		drivetrain.arcadeDrive(0, 0);
	}
	
}