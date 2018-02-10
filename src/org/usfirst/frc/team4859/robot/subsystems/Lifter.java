package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {
	// Create motors here
//	public static WPI_TalonSRX motorLiftStage1 = new WPI_TalonSRX(RobotMap.talonIDLiftStage1);
//	public static WPI_TalonSRX motorLiftStage2 = new WPI_TalonSRX(RobotMap.talonIDLiftStage2);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Lifter() {
		motorConfig();
	}
	
    public void initDefaultCommand() {}
    
    public void liftStop() {
    	//motorLiftStage1.set(0);
    	//motorLiftStage2.set(0);
    }
    
    public void liftAcquire(double inputDistance) {
//    	motorLiftStage1.set(ControlMode.MotionMagic, inputDistance/2);
//    	motorLiftStage2.set(ControlMode.MotionMagic, inputDistance/2);
    }
    
    public void liftDefault(double inputDistance) {
//    	motorLiftStage1.set(ControlMode.MotionMagic, inputDistance/2);
//    	motorLiftStage2.set(ControlMode.MotionMagic, inputDistance/2);
    }
    
    public void liftToHeight(double inputDistance) {
//    	motorLiftStage1.set(ControlMode.MotionMagic, inputDistance/2);
//    	motorLiftStage2.set(ControlMode.MotionMagic, inputDistance/2);
    }
    
	private void motorConfig() {
		// Configure feedback devices
		//motorLiftStage1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);
		//motorLiftStage2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);

		// Set relevant frame periods to be at least as fast as periodic rate
		//motorLiftStage1.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		//motorLiftStage1.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
		//motorLiftStage1.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 10, RobotMap.kTimeoutMs);
		
		//motorLiftStage2.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		//motorLiftStage2.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
		//motorLiftStage2.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 10, RobotMap.kTimeoutMs);

		// Set closed loop gains in slot 0
		//motorLiftStage1.selectProfileSlot(RobotMap.kPIDSlot, 0);
		//motorLiftStage1.config_kP(0, RobotMap.kLiftStage1P, RobotMap.kTimeoutMs);
		//motorLiftStage1.config_kI(0, RobotMap.kLiftStage1I, RobotMap.kTimeoutMs);
		//motorLiftStage1.config_kD(0, RobotMap.kLiftStage1D, RobotMap.kTimeoutMs);
		//motorLiftStage1.config_kF(0, RobotMap.kLiftStage1F, RobotMap.kTimeoutMs);
		//motorLiftStage1.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kLiftStage1AllowableError, RobotMap.kTimeoutMs);
		
		//motorLiftStage2.selectProfileSlot(RobotMap.kPIDSlot, 0);
		//motorLiftStage2.config_kP(0, RobotMap.kLiftStage2P, RobotMap.kTimeoutMs);
		//motorLiftStage2.config_kI(0, RobotMap.kLiftStage2I, RobotMap.kTimeoutMs);
		//motorLiftStage2.config_kD(0, RobotMap.kLiftStage2D, RobotMap.kTimeoutMs);
		//motorLiftStage2.config_kF(0, RobotMap.kLiftStage2F, RobotMap.kTimeoutMs);
		//motorLiftStage2.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kLiftStage2AllowableError, RobotMap.kTimeoutMs);

		// Set acceleration and cruise velocity
		//motorLiftStage1.configMotionCruiseVelocity(RobotMap.kLiftStage1CruiseVelocity, RobotMap.kTimeoutMs);
		//motorLiftStage1.configMotionAcceleration(RobotMap.kLiftStage1Acceleration, RobotMap.kTimeoutMs);
		
		//motorLiftStage2.configMotionCruiseVelocity(RobotMap.kLiftStage2CruiseVelocity, RobotMap.kTimeoutMs);
		//motorLiftStage2.configMotionAcceleration(RobotMap.kLiftStage2Acceleration, RobotMap.kTimeoutMs);

		// Zero encoder
		//motorLiftStage1.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		//motorLiftStage2.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		
		System.out.println("Lifter motor config ran");
	}
}