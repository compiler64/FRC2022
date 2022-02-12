package frc.robot.commands;

import java.util.function.DoubleSupplier;

import frc.robot.subsystems.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

/**
 * Makes the robot drive a given distance at a fixed speed.
 */
public class DriveDistanceAuto extends CommandBase {
    private DriveTrain m_driveTrain;
    private DoubleSupplier m_distanceSupplier;
    private double m_distance;
    private double m_speed;
    private Gyro m_gyro;
    private double speedBuffer;
    private double staticSpeed;

    /**
     * Creates a new DriveDistanceAuto command.
     * @param driveTrain the DriveTrain subsystem
     * @param distance the distance to travel
     * @param speed the speed to travel at
     */
    public DriveDistanceAuto(DriveTrain driveTrain, Gyro gyro, double distance, double speed) {
        m_driveTrain = driveTrain;
        m_distance = distance;
        m_speed = speed;
        m_gyro = gyro;

        addRequirements(driveTrain);
    }

    /**
     * Creates a new DriveDistanceAuto command, with the distance being calculated during initialization.
     * @param driveTrain the DriveTrain subsystem
     * @param distanceSupplier a function that returns the distance to travel
     * @param speed the speed to travel at
     */
    public DriveDistanceAuto(DriveTrain driveTrain, Gyro gyro, DoubleSupplier distanceSupplier, double speed) {
        m_driveTrain = driveTrain;
        m_distanceSupplier = distanceSupplier;
        m_speed = speed;
        staticSpeed = speed;
        m_gyro = gyro;

        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        // get the distance if a DoubleSupplier was passed to the constructor
        if (m_distanceSupplier != null) {
            m_distance = m_distanceSupplier.getAsDouble();
        }
        
        m_gyro.reset();
        // make the speed negative if the distance is negative
        m_speed *= Math.signum(m_distance);
        
        // set the speed of both motors to m_speed
        m_driveTrain.setBothMotors(m_speed);
        // reset the encoders
        if (Constants.ENCODERS_READY) {
            m_driveTrain.resetEncoders();
        }
    }
    @Override
    public void execute() {
        speedBuffer = Math.abs(m_gyro.getAngle() * m_speed / 5);
        if (m_gyro.getAngle() > 1) {
            m_driveTrain.setRightMotors(m_speed + speedBuffer);
            m_driveTrain.setLeftMotors(m_speed);
        } else if (m_gyro.getAngle() < -1) {
            m_driveTrain.setLeftMotors(m_speed + speedBuffer);
            m_driveTrain.setRightMotors(m_speed);
        } else {
            m_driveTrain.setBothMotors(m_speed);
        }

        if ((m_driveTrain.getAverageEncoderDistance() - m_distance) < 1) {
            // reduce speed
            m_speed = staticSpeed * .7;
        }
    }

    @Override
    public boolean isFinished() {
        // the command is finished if the distance is at least m_distance
        // return m_driveTrain.getAverageEncoderDistance() >= m_distance;

        return m_driveTrain.getAverageEncoderDistance() >= m_distance;
    }

    @Override
    public void end(boolean interrupted) {
        // if the command ends, stop both motors
        m_driveTrain.setBothMotors(0);
    }
}
