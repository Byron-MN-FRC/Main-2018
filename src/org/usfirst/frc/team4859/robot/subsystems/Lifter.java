package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lifter extends Subsystem {
	public static WPI_TalonSRX motorLiftStage1 = new WPI_TalonSRX(RobotMap.talonIDLiftStage1);
//	public static WPI_TalonSRX motorLiftStage2 = new WPI_TalonSRX(RobotMap.talonIDLiftStage2);
	
	public Lifter() {
		motorConfig();
	}
	
    public void initDefaultCommand() {}
    
    public void liftToHeight(String position) {
    	motorLiftStage1.set(ControlMode.MotionMagic, RobotMap.liftPosition.get(position)[0]);
//    	motorLiftStage2.set(ControlMode.MotionMagic, RobotMap.liftPosition.get(position)[1]);
    }
    
    public void liftDown(double inputSpeed) {
    	if(!RobotMap.isLiftDown) {
    		motorLiftStage1.set(-inputSpeed);
//        	motorLiftStage2.set(-inputSpeed);
    	} else {
    		motorLiftStage1.set(0);
//        	motorLiftStage2.set(0);
    	}
    }

    public void liftUp(double inputSpeed) {
    	motorLiftStage1.set(inputSpeed);
//    	motorLiftStage2.set(inputSpeed);
    }
    
    public void liftStop() {
    	motorLiftStage1.set(0);
//    	motorLiftStage2.set(0);
    }

	private void motorConfig() {
		// Configure feedback devices
		motorLiftStage1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
//		motorLiftStage2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
		
		// Make sure stage 1 sensors reads in the correct direction
		motorLiftStage1.setSensorPhase(true);

		// Set relevant frame periods to be at least as fast as periodic rate
		motorLiftStage1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 5, RobotMap.kTimeoutMs);
		motorLiftStage1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 5, RobotMap.kTimeoutMs);
		motorLiftStage1.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 5, RobotMap.kTimeoutMs);
		
//		motorLiftStage2.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 5, RobotMap.kTimeoutMs);
//		motorLiftStage2.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 5, RobotMap.kTimeoutMs);
//		motorLiftStage2.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 5, RobotMap.kTimeoutMs);

		// Set closed loop gains in slot 0
		motorLiftStage1.selectProfileSlot(RobotMap.kPIDSlot, 0);
		motorLiftStage1.config_kP(0, RobotMap.kLiftStage1P, RobotMap.kTimeoutMs);
		motorLiftStage1.config_kI(0, RobotMap.kLiftStage1I, RobotMap.kTimeoutMs);
		motorLiftStage1.config_kD(0, RobotMap.kLiftStage1D, RobotMap.kTimeoutMs);
		motorLiftStage1.config_kF(0, RobotMap.kLiftStage1F, RobotMap.kTimeoutMs);
		motorLiftStage1.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kLiftStage1AllowableError, RobotMap.kTimeoutMs);
		
//		motorLiftStage2.selectProfileSlot(RobotMap.kPIDSlot, 0);
//		motorLiftStage2.config_kP(0, RobotMap.kLiftStage2P, RobotMap.kTimeoutMs);
//		motorLiftStage2.config_kI(0, RobotMap.kLiftStage2I, RobotMap.kTimeoutMs);
//		motorLiftStage2.config_kD(0, RobotMap.kLiftStage2D, RobotMap.kTimeoutMs);
//		motorLiftStage2.config_kF(0, RobotMap.kLiftStage2F, RobotMap.kTimeoutMs);
//		motorLiftStage2.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kLiftStage2AllowableError, RobotMap.kTimeoutMs);
		
		//Current limiting
		motorLiftStage1.configContinuousCurrentLimit(RobotMap.kLiftStage1ContinuousCurrentLimit, RobotMap.kTimeoutMs);
		motorLiftStage1.configPeakCurrentDuration(RobotMap.kLiftStage1CurrentPeakDuration, RobotMap.kTimeoutMs);
//		motorLiftStage2.configContinuousCurrentLimit(RobotMap.kLiftStage2ContinuousCurrentLimit, RobotMap.kTimeoutMs);
//		motorLiftStage2.configPeakCurrentDuration(RobotMap.kLiftStage2CurrentPeakDuration, RobotMap.kTimeoutMs);
		
		motorLiftStage1.enableCurrentLimit(true);
//		motorLiftStage2.enableCurrentLimit(true);
		
		// Set acceleration and cruise velocity
		motorLiftStage1.configMotionCruiseVelocity(RobotMap.kLiftStage1CruiseVelocity, RobotMap.kTimeoutMs);
		motorLiftStage1.configMotionAcceleration(RobotMap.kLiftStage1Acceleration, RobotMap.kTimeoutMs);
		
//		motorLiftStage2.configMotionCruiseVelocity(RobotMap.kLiftStage2CruiseVelocity, RobotMap.kTimeoutMs);
//		motorLiftStage2.configMotionAcceleration(RobotMap.kLiftStage2Acceleration, RobotMap.kTimeoutMs);

		// Zero encoders
		motorLiftStage1.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
//		motorLiftStage2.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		
		System.out.println("Lifter motor config ran");
	}
}