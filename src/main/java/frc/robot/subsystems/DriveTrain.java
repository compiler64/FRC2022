package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

import static frc.robot.Constants.*;

/**
 * The drive train subsystem. Controls the motors.
 */
public class DriveTrain extends SubsystemBase {
    private TalonSRX motorLF = new TalonSRX(PortMap.MOTOR_LF_ID);
    private TalonSRX motorLM = new TalonSRX(PortMap.MOTOR_LM_ID);
    private TalonSRX motorLR = new TalonSRX(PortMap.MOTOR_LR_ID);
    private TalonSRX motorRF = new TalonSRX(PortMap.MOTOR_RF_ID);
    private TalonSRX motorRM = new TalonSRX(PortMap.MOTOR_RM_ID);
    private TalonSRX motorRR = new TalonSRX(PortMap.MOTOR_RR_ID);
  

    private double leftSpeed = 0;
    private double rightSpeed = 0;

    /**
     * Creates a new DriveTrain subsystem.
     */
    public DriveTrain() {
        Shuffleboard.getTab("main").addNumber("Left Speed", () -> leftSpeed);
        Shuffleboard.getTab("main").addNumber("Right Speed", () -> rightSpeed);

        Shuffleboard.getTab("main").addNumber("left encoder", () -> getLeftEncoderDistance());
        Shuffleboard.getTab("main").addNumber("right encoder", () -> getRightEncoderDistance());

        motorLM.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        motorLM.setSensorPhase(true);
        motorRM.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

       
    }


    // set the speed of the left motors
    public void setLeftMotors(double speed) {
        // System.out.println("Setting left motor speed to " + speed);
        motorLF.set(ControlMode.PercentOutput, -speed);
        motorLM.set(ControlMode.PercentOutput, -speed);
        motorLR.set(ControlMode.PercentOutput, -speed);
        leftSpeed = speed;
    }

    // set the speed of the right motors
    public void setRightMotors(double speed) {
        motorRF.set(ControlMode.PercentOutput, speed);
        motorRM.set(ControlMode.PercentOutput, speed);
        motorRR.set(ControlMode.PercentOutput, speed);
        rightSpeed = speed;
    }

    // set both motors
    public void setBothMotors(double speed) {
        setRightMotors(speed);
        setLeftMotors(speed);
    }

    /**
     * Gets the distance of the left encoder.
     * 
     * @return the distance of the left encoder
     */
    public double getLeftEncoderDistance() {
        return motorLM.getSelectedSensorPosition() * 4096 * WHEEL_CIRCUMFERENCE;
    }

    /**
     * Gets the distance of the right encoder.
     * 
     * @return the distance of the right encoder
     */
    public double getRightEncoderDistance() {
        return motorRM.getSelectedSensorPosition() * 4096 * WHEEL_CIRCUMFERENCE;
    }

    /**
     * Gets the average distance of the two encoders.
     * 
     * @return the average distance of the two encoders
     */
    public double getAverageEncoderDistance() {
        return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
    }
    public void resetLeftEncoder() {
        motorLM.setSelectedSensorPosition(0);
    }
    public void resetRightEncoder() {
        motorRM.setSelectedSensorPosition(0);
    }
    
    /**
     * Resets the encoders.
     */
    public void resetEncoders() {
        resetLeftEncoder();
        resetRightEncoder();
    }
}
