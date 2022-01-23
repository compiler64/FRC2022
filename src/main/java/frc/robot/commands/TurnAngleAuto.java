package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

/**
 * Makes the robot turn by a given angle at a fixed speed. It will keep turning until the gyroscope detects the correct angle.
 */
public class TurnAngleAuto extends CommandBase {
    private DriveTrain m_driveTrain;
    private Gyro m_gyro;
    private double m_angle;
    private double m_speed;

    public TurnAngleAuto(DriveTrain driveTrain, Gyro gyro, double angle, double speed) {
        m_driveTrain = driveTrain;
        m_gyro = gyro;
        m_angle = angle;
        m_speed = speed;

        addRequirements(driveTrain, gyro);
    }

    @Override
    public void initialize() {
        // get the sign of the angle
        double sign = Math.signum(m_angle);

        m_driveTrain.setLeftMotors(sign * m_speed);
        m_driveTrain.setRightMotors(-sign * m_speed);

        m_gyro.reset();
    }

    @Override
    public void end(boolean interrupted) {
        // stop both motors
        m_driveTrain.setBothMotors(0);
    }

    @Override
    public boolean isFinished() {
        double sign = Math.signum(m_angle);
        // the command is finished if the angle is at least m_angle
        return sign * m_gyro.getAngle() >= sign * m_angle;
    }
}
