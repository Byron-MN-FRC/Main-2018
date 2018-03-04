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
	public static WPI_TalonSRX motorLeftFollower = new WPI_TalonSRX(RobotMap.talonIDLeftFollower);
	
	public static WPI_TalonSRX motorRightMaster = new WPI_TalonSRX(RobotMap.talonIDRightMaster);
	public static WPI_TalonSRX motorRightFollower = new WPI_TalonSRX(RobotMap.talonIDRightFollower);
	
	public static SpeedControllerGroup drivetrainLeft = new SpeedControllerGroup(motorLeftMaster);
	public static SpeedControllerGroup drivetrainRight = new SpeedControllerGroup(motorRightMaster);
	
	public static DifferentialDrive drivetrain = new DifferentialDrive(drivetrainLeft, drivetrainRight);
	
	private double y = 0;
	private double twist = 0;
	private double yChange = 0;
	private double yLimitedJoystick = 0;
	private double encoderVelocityL = 0;
	private double encoderVelocityR = 0;
	private double encoderLastVelocityL = 0;
	private double encoderLastVelocityR = 0;
	
	public Drivetrain() {
		motorConfig();
		
		drivetrain.setSafetyEnabled(false);
	}
	
	public void initDefaultCommand () {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public void driveWithJoystick(Joystick joystickP0) {
		y = -joystickP0.getY();
		twist = joystickP0.getTwist();
		
		// Y Acceleration Limiting
		encoderVelocityL = motorLeftMaster.getSelectedSensorVelocity(RobotMap.kPIDSlot);
		encoderVelocityR = motorRightMaster.getSelectedSensorVelocity(RobotMap.kPIDSlot);
		
		if ((encoderVelocityL > (encoderLastVelocityL + 1)) && (encoderVelocityR > (encoderLastVelocityR + 1))) {
			if ((motorLeftMaster.getSelectedSensorVelocity(0) + motorRightMaster.getSelectedSensorVelocity(0))/2 < 0) {
				yChange = y - yLimitedJoystick;
				if (yChange > RobotMap.kRampRateTipLimit) yChange = RobotMap.kRampRateTipLimit;
				else if (yChange <= RobotMap.kRampRateTipLimit) yChange = -RobotMap.kRampRateTipLimit;
				yLimitedJoystick += yChange;
			} else {
				yChange = y - yLimitedJoystick;
				if (yChange > RobotMap.kRampRateLimit) yChange = RobotMap.kRampRateLimit;
				else if (yChange <= RobotMap.kRampRateLimit) yChange = -RobotMap.kRampRateLimit;
				yLimitedJoystick += yChange;
			}
			y = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowY", yLimitedJoystick) : ThrottleLookup.calcJoystickCorrection("NormY", yLimitedJoystick);
		} else y = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowY", y) : ThrottleLookup.calcJoystickCorrection("NormY", y);
		encoderLastVelocityL = encoderVelocityL;
		encoderLastVelocityR = encoderVelocityR;
		
		// Apply translations to the values from the controller
//		y = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowY", y) : ThrottleLookup.calcJoystickCorrection("NormY", y);
		twist = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowT", twist) : ThrottleLookup.calcJoystickCorrection("NormT", twist);
		
		SmartDashboard.putString("Robot Mode", (RobotMap.pMode) ? "Slow" : "Normal");	
//		SmartDashboard.putNumber("Joystick Y", y);
//		SmartDashboard.putNumber("Joystick Twist", twist);
		
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
		motorLeftFollower.set(ControlMode.Follower, RobotMap.talonIDLeftMaster);
		motorRightFollower.set(ControlMode.Follower, RobotMap.talonIDRightMaster);
		
		// Set current limits
		motorLeftMaster.configContinuousCurrentLimit(RobotMap.kDriveContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorLeftFollower.configContinuousCurrentLimit(RobotMap.kDriveContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorRightMaster.configContinuousCurrentLimit(RobotMap.kDriveContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorRightFollower.configContinuousCurrentLimit(RobotMap.kDriveContinuousCurrentLimit, RobotMap.kTimeoutMs);
		
		motorLeftMaster.configPeakCurrentDuration(RobotMap.kDriveCurrentPeakDuration, RobotMap.kTimeoutMs);
		motorLeftFollower.configPeakCurrentDuration(RobotMap.kDriveCurrentPeakDuration, RobotMap.kTimeoutMs);
		motorRightMaster.configPeakCurrentDuration(RobotMap.kDriveCurrentPeakDuration, RobotMap.kTimeoutMs);
		motorRightFollower.configPeakCurrentDuration(RobotMap.kDriveCurrentPeakDuration, RobotMap.kTimeoutMs);
		
		motorLeftMaster.enableCurrentLimit(true);
		motorLeftFollower.enableCurrentLimit(true);
		motorRightMaster.enableCurrentLimit(true);
		motorRightFollower.enableCurrentLimit(true);
		
		// Configure feedback devices
		motorLeftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
		motorRightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);

		// Set relevant frame periods to be at least as fast as periodic rate
		motorLeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 2, RobotMap.kTimeoutMs);
		motorLeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 2, RobotMap.kTimeoutMs);
		motorLeftMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 2, RobotMap.kTimeoutMs);
		
		motorRightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 2, RobotMap.kTimeoutMs);
		motorRightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 2, RobotMap.kTimeoutMs);
		motorRightMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 2, RobotMap.kTimeoutMs);

		// Set closed loop gains in slot 0
		motorLeftMaster.selectProfileSlot(RobotMap.kPIDSlot, 0);
		motorLeftMaster.config_kF(0, RobotMap.kF, RobotMap.kTimeoutMs);
		motorLeftMaster.config_kP(0, RobotMap.kP, RobotMap.kTimeoutMs);
		motorLeftMaster.config_kI(0, RobotMap.kI, RobotMap.kTimeoutMs);
		motorLeftMaster.config_kD(0, RobotMap.kD, RobotMap.kTimeoutMs);
		motorLeftMaster.config_IntegralZone(0, 0, RobotMap.kTimeoutMs);
		motorLeftMaster.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kDriveAllowableError, RobotMap.kTimeoutMs);
		
		motorRightMaster.selectProfileSlot(RobotMap.kPIDSlot, 0);
		motorRightMaster.config_kF(0, RobotMap.kF, RobotMap.kTimeoutMs);
		motorRightMaster.config_kP(0, RobotMap.kP, RobotMap.kTimeoutMs);
		motorRightMaster.config_kI(0, RobotMap.kI, RobotMap.kTimeoutMs);
		motorRightMaster.config_kD(0, RobotMap.kD, RobotMap.kTimeoutMs);
		motorRightMaster.config_IntegralZone(0, 0, RobotMap.kTimeoutMs);
		motorRightMaster.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kDriveAllowableError, RobotMap.kTimeoutMs);

		// Set acceleration and cruise velocity
		motorLeftMaster.configMotionAcceleration(RobotMap.kLowGearAcceleration, RobotMap.kTimeoutMs);
		motorLeftMaster.configMotionCruiseVelocity(RobotMap.kLowGearCruiseVelocity, RobotMap.kTimeoutMs);
		motorRightMaster.configMotionAcceleration(RobotMap.kLowGearAcceleration, RobotMap.kTimeoutMs);
		motorRightMaster.configMotionCruiseVelocity(RobotMap.kLowGearCruiseVelocity, RobotMap.kTimeoutMs);
		
		// Zero encoder
		motorLeftMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		motorRightMaster.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		
		// Set ramp rates
		motorLeftMaster.configOpenloopRamp(RobotMap.kRampRate,RobotMap.kTimeoutMs);
		motorRightMaster.configOpenloopRamp(RobotMap.kRampRate,RobotMap.kTimeoutMs);
		
		System.out.println("Motor configuration ran");
	}
}