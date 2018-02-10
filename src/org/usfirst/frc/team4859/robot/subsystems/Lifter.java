package org.usfirst.frc.team4859.robot.subsystems;

//import org.usfirst.frc.team4859.robot.RobotMap;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {
	// Create motors here
	//public static WPI_TalonSRX motorLifter = new WPI_TalonSRX(RobotMap.talonIDLifter);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public Lifter() {
		motorConfig();
	}
	
    public void initDefaultCommand() {}
    
    public void liftStop() {
    	//motorLifter.set(0);
    }
    
    public void liftAcquire(double inputDistance) {
    	//motorLifter.set(ControlMode.MotionMagic, inputDistance);
    }
    
    public void liftDefault(double inputDistance) {
    	//motorLifter.set(ControlMode.MotionMagic, inputDistance);
    }
    
    public void liftSwitch(double inputDistance) {
    	//motorLifter.set(ControlMode.MotionMagic, inputDistance);
    }
    
    public void liftScale(double inputDistance) {
    	//motorLifter.set(ControlMode.MotionMagic, inputDistance);
    }
    
    public void liftClimb(double inputDistance) {
    	//motorLifter.set(ControlMode.MotionMagic, inputDistance);
    }
    
	private void motorConfig() {
		// Configure feedback devices
		//motorLifter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, RobotMap.kTimeoutMs);

		// Set relevant frame periods to be at least as fast as periodic rate
		//motorLifter.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, RobotMap.kTimeoutMs);
		//motorLifter.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, RobotMap.kTimeoutMs);
		//motorLifter.setStatusFramePeriod(StatusFrameEnhanced.Status_9_MotProfBuffer, 10, RobotMap.kTimeoutMs);

		// Set closed loop gains in slot 0
		//motorLifter.selectProfileSlot(RobotMap.kPIDSlot, 0);
		//motorLifter.config_kF(0, RobotMap.kF, RobotMap.kTimeoutMs);
		//motorLifter.config_kP(0, RobotMap.kP, RobotMap.kTimeoutMs);
		//motorLifter.config_kI(0, RobotMap.kI, RobotMap.kTimeoutMs);
		//motorLifter.config_kD(0, RobotMap.kD, RobotMap.kTimeoutMs);
		//motorLifter.config_IntegralZone(0, 0, RobotMap.kTimeoutMs);
		//motorLifter.configAllowableClosedloopError(RobotMap.kPIDSlot, RobotMap.kAllowableError, RobotMap.kTimeoutMs);

		// Set acceleration and cruise velocity
		//motorLifter.configMotionCruiseVelocity(RobotMap.kLifterCruiseVelocity, RobotMap.kTimeoutMs);
		//motorLifter.configMotionAcceleration(RobotMap.kLifterAcceleration, RobotMap.kTimeoutMs);

		// Zero encoder
		//motorLifter.setSelectedSensorPosition(0, 0, RobotMap.kTimeoutMs);
		
		System.out.println("Lifter motor config ran");
	}
}