package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;
import static frc.robot.Constants.*;

/**
 * The drive train subsystem. Controls the motors and shifters.
 */
public class DriveTrain extends SubsystemBase {
    private TalonSRX motorLF = new TalonSRX(PortMap.MOTOR_LF_ID);
    private TalonSRX motorLM = new TalonSRX(PortMap.MOTOR_LM_ID);
    private TalonSRX motorLR = new TalonSRX(PortMap.MOTOR_LR_ID);
    private TalonSRX motorRF = new TalonSRX(PortMap.MOTOR_RF_ID);
    private TalonSRX motorRM = new TalonSRX(PortMap.MOTOR_RM_ID);
    private TalonSRX motorRR = new TalonSRX(PortMap.MOTOR_RR_ID);
    private DoubleSolenoid shifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

    //private double leftSpeed = 0;
    //private double rightSpeed = 0;

    private boolean highGearOn = false;
    /**
     * Creates a new DriveTrain subsystem.
     */
    public DriveTrain(Gyro gyro) {
        // Shuffleboard.getTab("main").addNumber("Left Speed", () -> leftSpeed).withPosition(0, 0);
        // Shuffleboard.getTab("main").addNumber("Right Speed", () -> rightSpeed).withPosition(1, 0);

        // Shuffleboard.getTab("main").addNumber("left encoder", () -> getLeftEncoderDistance()).withPosition(0, 1);
        // Shuffleboard.getTab("main").addNumber("right encoder", () -> getRightEncoderDistance()).withPosition(1, 1);
        // Shuffleboard.getTab("main").addNumber("average encoder distance", () -> getAverageEncoderDistance()).withSize(2, 1).withPosition(2, 0);
        Shuffleboard.getTab("main").addBoolean("highGearOn", () -> highGearOn);

        motorLM.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute); 
        motorLM.setSensorPhase(true);
        motorRM.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

        resetEncoders();
       
    }


    /**
     * Set the speed of the left motors.
     * @param speed the motor speed, as a fraction of the maximum
     */
    public void setLeftMotors(double speed) {
        motorLF.set(ControlMode.PercentOutput, -speed);
        motorLM.set(ControlMode.PercentOutput, -speed);
        motorLR.set(ControlMode.PercentOutput, -speed);
        //leftSpeed = speed;
    }

    /**
     * Set the speed of the right motors.
     * @param speed the motor speed, as a fraction of the maximum
     */
    public void setRightMotors(double speed) {
        motorRF.set(ControlMode.PercentOutput, speed);
        motorRM.set(ControlMode.PercentOutput, speed);
        motorRR.set(ControlMode.PercentOutput, speed);
        //rightSpeed = speed;
    }

    /**
     * Set both motors to the same speed.
     * @param speed the motor speed, as a fraction of the maximum
     */
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
        return (motorLM.getSelectedSensorPosition() / (4096 * 7.75)) * WHEEL_CIRCUMFERENCE;
    }

    /**
     * Gets the distance of the right encoder.
     * 
     * @return the distance of the right encoder
     */
    public double getRightEncoderDistance() {
        return (motorRM.getSelectedSensorPosition() / (4096 * 7.75)) * WHEEL_CIRCUMFERENCE;
    }

    /**
     * Gets the average distance of the two encoders.
     * 
     * @return the average distance of the two encoders
     */
    public double getAverageEncoderDistance() {
        return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
    }

    /**
     * Resets the left encoder.
     */
    public void resetLeftEncoder() {
        motorLM.setSelectedSensorPosition(0.0);
    }

    /**
     * Resets the right encoder.
     */
    public void resetRightEncoder() {
        motorRM.setSelectedSensorPosition(0.0);
    }
    
    /**
     * Resets both encoders.
     */
    public void resetEncoders() {
        resetLeftEncoder();
        resetRightEncoder();
    }

    /**
     * Sets the state of the high gear.
     * @param value the new state of the high gear (up, off, or centered)
     */
    public void setHighGear(String value) {
        if (value.equals("on")) {
            shifter.set(Value.kForward);
            highGearOn = true;
        } else if (value.equals("off")) {
            shifter.set(Value.kReverse);
            highGearOn = false;
        } else if (value.equals("centered")) {
            shifter.set(Value.kOff);
        } else {
            throw new IllegalArgumentException("setHighGear argument must be"
            + "\"up\", \"off\", or \"centered\", but was " + value + ".");
        }
    }
}
