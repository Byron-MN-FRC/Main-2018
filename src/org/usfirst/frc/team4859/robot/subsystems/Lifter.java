package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lifter extends Subsystem {
	public static WPI_TalonSRX motorLift = new WPI_TalonSRX(RobotMap.talonIDLift);
	
	public Lifter() {
		motorConfig();
	}
	
    public void initDefaultCommand() {}
    
    public void liftToHeight(String position) {
    	motorLift.set(ControlMode.MotionMagic, RobotMap.liftPosition.get(position)[0]);
//    	motorLiftStage2.set(ControlMode.MotionMagic, RobotMap.liftPosition.get(position)[1]);
    }
    
    public void liftDown(double inputSpeed) {
    	if(!RobotMap.isLiftDown) {
    		motorLift.set(-inputSpeed);
//        	motorLiftStage2.set(-inputSpeed);
    	} else {
    		motorLift.set(0);
//        	motorLiftStage2.set(0);
    	}
    }

    public void liftUp(double inputSpeed) {
    	motorLift.set(inputSpeed);
//    	motorLiftStage2.set(inputSpeed);
    }
    
    public void liftStop() {
    	motorLift.set(0);
//    	motorLiftStage2.set(0);
    }

	private void motorConfig() {
		// Configure feedback devices
		motorLift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
		
		// Make sure stage 1 sensors reads in the correct direction
		motorLift.setSensorPhase(true);

		// Set relevant frame periods to be at least as fast as periodic rate
		motorLift.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		motorLift.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
		motorLift.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 10, RobotMap.kTimeoutMs);

		// Set closed loop gains in slot 0
		motorLift.selectProfileSlot(RobotMap.kPIDSlot, 0);
		motorLift.config_kP(0, RobotMap.kLiftP, RobotMap.kTimeoutMs);
		motorLift.config_kI(0, RobotMap.kLiftI, RobotMap.kTimeoutMs);
		motorLift.config_kD(0, RobotMap.kLiftD, RobotMap.kTimeoutMs);
		motorLift.config_kF(0, RobotMap.kLiftF, RobotMap.kTimeoutMs);
		motorLift.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kLiftAllowableError, RobotMap.kTimeoutMs);
		
		//Current limiting
		motorLift.configContinuousCurrentLimit(RobotMap.kLiftContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorLift.configPeakCurrentDuration(RobotMap.kLiftCurrentPeakDuration, RobotMap.kTimeoutMs);
		
		motorLift.enableCurrentLimit(true);
		
		// Set acceleration and cruise velocity
		motorLift.configMotionCruiseVelocity(RobotMap.kLiftCruiseVelocity, RobotMap.kTimeoutMs);
		motorLift.configMotionAcceleration(RobotMap.kLiftAcceleration, RobotMap.kTimeoutMs);

		// Zero encoders
		motorLift.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		
		System.out.println("Lifter motor config ran");
	}
}