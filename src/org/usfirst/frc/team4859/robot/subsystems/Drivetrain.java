package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.ThrottleLookup.ThrottleLookup;
import org.usfirst.frc.team4859.robot.commands.DriveWithJoystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {
	
	
	public static WPI_TalonSRX motorFrontLeft = new WPI_TalonSRX(RobotMap.talonIDFrontLeft);
	public static WPI_TalonSRX motorFrontRight = new WPI_TalonSRX(RobotMap.talonIDFrontRight);
	
	public static WPI_TalonSRX motorBackLeft = new WPI_TalonSRX(RobotMap.talonIDBackLeft);
	public static WPI_TalonSRX motorBackRight = new WPI_TalonSRX(RobotMap.talonIDBackRight);
	
	public static SpeedControllerGroup drivetrainLeft = new SpeedControllerGroup(motorFrontLeft, motorBackLeft);
	public static SpeedControllerGroup drivetrainRight = new SpeedControllerGroup(motorFrontRight, motorBackRight);
	
	

	public static DifferentialDrive drivetrain = new DifferentialDrive(drivetrainLeft, drivetrainRight);
	
	public static DigitalOutput lightStrip = new DigitalOutput(0);
	public static AnalogInput gearSensor = new AnalogInput(0);
	public static AnalogOutput gearLED = new AnalogOutput(1);
	
	public Drivetrain() {
		
		drivetrain.setSafetyEnabled(false);
	}
	
	public void initDefaultCommand () {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public void driveWithJoystick(Joystick joystickP0) {
		double y = joystickP0.getY();
		double x = joystickP0.getX();
		double twist = joystickP0.getTwist();
		
		// Apply translations to the values from the controller
		y = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowY", y) : ThrottleLookup.calcJoystickCorrection("NormY", y);
		x = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowX", x) : ThrottleLookup.calcJoystickCorrection("NormX", x);
		twist = (RobotMap.pMode) ? ThrottleLookup.calcJoystickCorrection("SlowT", twist) : ThrottleLookup.calcJoystickCorrection("NormT", twist);
		
		// Apply flip if the flip button is toggled
		if (RobotMap.fMode) {
			y *= -1;
			x *= -1;
		}
		
		SmartDashboard.putString("Robot Mode", (RobotMap.pMode) ? "Slow" : "Normal");	
		
		SmartDashboard.putNumber("Left 1", motorFrontLeft.getOutputCurrent());
		SmartDashboard.putNumber("Left 2", motorBackLeft.getOutputCurrent());
		SmartDashboard.putNumber("Right 1", motorFrontRight.getOutputCurrent());
		SmartDashboard.putNumber("Right 2", motorBackRight.getOutputCurrent());
		
		drivetrain.arcadeDrive(y, twist);
	}
	
	public void driveStraight(double inputSpeed) {
		drivetrain.arcadeDrive(inputSpeed, 0);
	}
	
	public void driveBackwards(double inputSpeed) {
		drivetrain.arcadeDrive(-inputSpeed, 0);
	}
	
	public void driveStop() {
		drivetrain.arcadeDrive(0, 0);
	}
}