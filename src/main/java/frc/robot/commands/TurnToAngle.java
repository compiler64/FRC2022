package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

/**
 * Turns the robot to a given angle.
 */
public class TurnToAngle extends CommandBase {
    private DriveTrain m_driveTrain;
    private Gyro m_gyro;
    private double m_angle;
    private double m_speed;

    /**
     * Creates a new TurnToAngle command.
     * @param driveTrain the drive train of the robot
     * @param gyro the gyro of the robot
     * @param angle the angle to turn to
     * @param speed the speed to turn at
     */
    public TurnToAngle(DriveTrain driveTrain, Gyro gyro, double angle, double speed) {
        m_driveTrain = driveTrain;
        m_gyro = gyro;
        m_angle = angle;
        m_speed = speed;

        addRequirements(driveTrain, gyro);
    }

    @Override
    public void initialize() {
        double old_angle = m_gyro.getAngle();
        double sign = Math.signum(180 - (m_angle - old_angle));

        m_driveTrain.setLeftMotors(sign * m_speed);
        m_driveTrain.setRightMotors(-sign * m_speed);
    }

    @Override
    public void end(boolean interrupted) {
        // stop both motors
        m_driveTrain.setBothMotors(0);
    }

    @Override
    public boolean isFinished() {
        double sign = Math.signum(m_speed);
        return sign * m_gyro.getAngle() >= sign * m_angle;
    }
}
