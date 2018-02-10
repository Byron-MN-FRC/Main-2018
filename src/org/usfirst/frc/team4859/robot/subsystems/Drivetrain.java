package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.ThrottleLookup.ThrottleLookup;
import org.usfirst.frc.team4859.robot.commands.DriveWithJoystick;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
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
		motorConfig();
		
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
		SmartDashboard.putNumber("Forward Backword", y);
		SmartDashboard.putNumber("Left Right", twist);
		
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
		System.out.println("driveStraightDistance = " + inputDistance);
		motorLeftMaster.set(ControlMode.MotionMagic, inputDistance);
		motorRightMaster.set(ControlMode.MotionMagic, -inputDistance);
	}
	
	public void driveTurnDistance(double inputDistance) {
		System.out.println("driveTurnDistance = " + inputDistance);
		motorLeftMaster.set(ControlMode.MotionMagic, inputDistance);
		motorRightMaster.set(ControlMode.MotionMagic, inputDistance);
	}
	
	public void driveBackwards(double inputSpeed) {
		drivetrain.arcadeDrive(-inputSpeed, 0);
	}
	
	public void driveStop() {
		drivetrain.arcadeDrive(0, 0);
	}
	
	private void motorConfig() {
		// Set followers
		motorLeftFollower1.set(ControlMode.Follower, RobotMap.talonIDLeftMaster);
		motorLeftFollower2.set(ControlMode.Follower, RobotMap.talonIDLeftMaster);
		
		motorRightFollower1.set(ControlMode.Follower, RobotMap.talonIDRightMaster);
		motorRightFollower2.set(ControlMode.Follower, RobotMap.talonIDRightMaster);
		
		// Set current limits
		motorLeftMaster.configContinuousCurrentLimit(RobotMap.kContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorLeftFollower1.configContinuousCurrentLimit(RobotMap.kContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorLeftFollower2.configContinuousCurrentLimit(RobotMap.kContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorRightMaster.configContinuousCurrentLimit(RobotMap.kContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorRightFollower1.configContinuousCurrentLimit(RobotMap.kContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorRightFollower2.configContinuousCurrentLimit(RobotMap.kContinuousCurrentLimit, RobotMap.kTimeoutMs);
		
		motorLeftMaster.configPeakCurrentDuration(RobotMap.kCurrentPeakDuration, RobotMap.kTimeoutMs);
		motorLeftFollower1.configPeakCurrentDuration(RobotMap.kCurrentPeakDuration, RobotMap.kTimeoutMs);
		motorLeftFollower2.configPeakCurrentDuration(RobotMap.kCurrentPeakDuration, RobotMap.kTimeoutMs);
		motorRightMaster.configPeakCurrentDuration(RobotMap.kCurrentPeakDuration, RobotMap.kTimeoutMs);
		motorRightFollower1.configPeakCurrentDuration(RobotMap.kCurrentPeakDuration, RobotMap.kTimeoutMs);
		motorRightFollower2.configPeakCurrentDuration(RobotMap.kCurrentPeakDuration, RobotMap.kTimeoutMs);
		
		motorLeftMaster.enableCurrentLimit(true);
		motorLeftFollower1.enableCurrentLimit(true);
		motorLeftFollower2.enableCurrentLimit(true);
		motorRightMaster.enableCurrentLimit(true);
		motorRightFollower1.enableCurrentLimit(true);
		motorRightFollower2.enableCurrentLimit(true);
		
		// Configure feedback devices
		motorLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
		motorRightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);

		// Set relevant frame periods to be at least as fast as periodic rate
		motorLeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		motorLeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
		motorLeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 10, RobotMap.kTimeoutMs);
		
		motorRightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		motorRightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
		motorRightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 10, RobotMap.kTimeoutMs);

		// Set closed loop gains in slot 0
		motorLeftMaster.selectProfileSlot(RobotMap.kPIDSlot, 0);
		motorLeftMaster.config_kF(0, RobotMap.kF, RobotMap.kTimeoutMs);
		motorLeftMaster.config_kP(0, RobotMap.kP, RobotMap.kTimeoutMs);
		motorLeftMaster.config_kI(0, RobotMap.kI, RobotMap.kTimeoutMs);
		motorLeftMaster.config_kD(0, RobotMap.kD, RobotMap.kTimeoutMs);
		motorLeftMaster.config_IntegralZone(0, 0, RobotMap.kTimeoutMs);
		motorLeftMaster.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kAllowableError, RobotMap.kTimeoutMs);
		
		motorRightMaster.selectProfileSlot(RobotMap.kPIDSlot, 0);
		motorRightMaster.config_kF(0, RobotMap.kF, RobotMap.kTimeoutMs);
		motorRightMaster.config_kP(0, RobotMap.kP, RobotMap.kTimeoutMs);
		motorRightMaster.config_kI(0, RobotMap.kI, RobotMap.kTimeoutMs);
		motorRightMaster.config_kD(0, RobotMap.kD, RobotMap.kTimeoutMs);
		motorRightMaster.config_IntegralZone(0, 0, RobotMap.kTimeoutMs);
		motorRightMaster.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kAllowableError, RobotMap.kTimeoutMs);

		// Set acceleration and cruise velocity
		motorLeftMaster.configMotionCruiseVelocity(RobotMap.kHighGearCruiseVelocity, RobotMap.kTimeoutMs);
		motorLeftMaster.configMotionAcceleration(RobotMap.kHighGearAcceleration, RobotMap.kTimeoutMs);
		
		motorRightMaster.configMotionCruiseVelocity(RobotMap.kHighGearCruiseVelocity, RobotMap.kTimeoutMs);
		motorRightMaster.configMotionAcceleration(RobotMap.kHighGearAcceleration, RobotMap.kTimeoutMs);

		// Zero encoder
		motorLeftMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		motorRightMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		
		System.out.println("Motor configuration ran");
	}
}