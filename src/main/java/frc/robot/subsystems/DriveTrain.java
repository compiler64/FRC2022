package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXSimCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private AHRS m_gyro;
    
    

    // simulation
    TalonSRXSimCollection m_leftDriveSim = motorLM.getSimCollection();
    TalonSRXSimCollection m_rightDriveSim = motorRM.getSimCollection();

    
    // These classes help us simulate our drivetrain
    public DifferentialDrivetrainSim m_drivetrainSimulator;
    // The Field2d class shows the field in the sim GUI
    private Field2d m_fieldSim = new Field2d();

    DifferentialDrivetrainSim m_driveSim = new DifferentialDrivetrainSim(DCMotor.getMiniCIM(3), 5, 5, Units.lbsToKilograms(150), Units.inchesToMeters(3), Units.inchesToMeters(25), null);
    final int kCountsPerRev = 4096;  //Encoder counts per revolution of the motor shaft.
    final double kSensorGearRatio = 1; //Gear ratio is the ratio between the *encoder* and the wheels.  On the AndyMark drivetrain, encoders mount 1:1 with the gearbox shaft.
    final double kGearRatio = 10.71; //Switch kSensorGearRatio to this gear ratio if encoder is on the motor instead of on the gearbox.
    final double kWheelRadiusInches = 3;
    final int k100msPerSecond = 10;

    DifferentialDriveOdometry m_odometry; 

    private double leftSpeed = 0;
    private double rightSpeed = 0;

    /**
     * Creates a new DriveTrain subsystem.
     */
    public DriveTrain(Gyro gyro) {
        Shuffleboard.getTab("main").addNumber("Left Speed", () -> leftSpeed);
        Shuffleboard.getTab("main").addNumber("Right Speed", () -> rightSpeed);

        Shuffleboard.getTab("main").addNumber("left encoder", () -> getLeftEncoderDistance());
        Shuffleboard.getTab("main").addNumber("right encoder", () -> getRightEncoderDistance());
        Shuffleboard.getTab("main").addNumber("average encoder distance", () -> getAverageEncoderDistance());
        SmartDashboard.putData(m_fieldSim);

        m_gyro = gyro.gyroscope;
        m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d());

        motorLM.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute); 
        motorLM.setSensorPhase(true);
        motorRM.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);

        resetEncoders();
       
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
    public void resetLeftEncoder() {
        motorLM.setSelectedSensorPosition(0.0);
    }
    public void resetRightEncoder() {
        motorRM.setSelectedSensorPosition(0.0);
    }
    
    /**
     * Resets the encoders.
     */
    public void resetEncoders() {
        resetLeftEncoder();
        resetRightEncoder();
    }

    // simulation
    public void periodic() {
        m_odometry.update(m_gyro.getRotation2d(),
            nativeUnitsToDistanceMeters(motorLM.getSelectedSensorPosition()),
            nativeUnitsToDistanceMeters(motorRM.getSelectedSensorPosition()));
        m_fieldSim.setRobotPose(m_odometry.getPoseMeters());
    }
    public void simulationPeriodic() {
        m_leftDriveSim.setBusVoltage(RobotController.getBatteryVoltage());
        m_rightDriveSim.setBusVoltage(RobotController.getBatteryVoltage());
        m_driveSim.setInputs(motorLM.getMotorOutputVoltage(), motorRM.getMotorOutputVoltage());
        m_driveSim.update(0.02);

        m_leftDriveSim.setQuadratureRawPosition(
            distanceToNativeUnits(m_driveSim.getLeftPositionMeters()));
        m_leftDriveSim.setQuadratureVelocity(
            velocityToNativeUnits(m_driveSim.getLeftVelocityMetersPerSecond()));
        m_rightDriveSim.setQuadratureRawPosition(
            distanceToNativeUnits(-m_driveSim.getRightPositionMeters()));
        m_rightDriveSim.setQuadratureVelocity(
            velocityToNativeUnits(-m_driveSim.getRightVelocityMetersPerSecond()));

    }
    private int distanceToNativeUnits(double positionMeters){
        double wheelRotations = positionMeters/(2 * Math.PI * Units.inchesToMeters(kWheelRadiusInches));
        double motorRotations = wheelRotations * kSensorGearRatio;
        int sensorCounts = (int)(motorRotations * kCountsPerRev);
        return sensorCounts;
    }
    private int velocityToNativeUnits(double velocityMetersPerSecond){
        double wheelRotationsPerSecond = velocityMetersPerSecond/(2 * Math.PI * Units.inchesToMeters(kWheelRadiusInches));
        double motorRotationsPerSecond = wheelRotationsPerSecond * kSensorGearRatio;
        double motorRotationsPer100ms = motorRotationsPerSecond / k100msPerSecond;
        int sensorCountsPer100ms = (int)(motorRotationsPer100ms * kCountsPerRev);
        return sensorCountsPer100ms;
    }
    private double nativeUnitsToDistanceMeters(double sensorCounts){
        double motorRotations = (double)sensorCounts / kCountsPerRev;
        double wheelRotations = motorRotations / kSensorGearRatio;
        double positionMeters = wheelRotations * (2 * Math.PI * Units.inchesToMeters(kWheelRadiusInches));
        return positionMeters;
      }
}
