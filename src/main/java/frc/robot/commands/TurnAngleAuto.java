package frc.robot.commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TurnAngleAuto extends CommandBase {
    private DriveTrain m_driveTrain;
    private Gyro m_gyro;
    private double m_angle;
    private double m_speed;

    public TurnAngleAuto(DriveTrain driveTrain, Gyro gyro, double angle, double speed) {
        m_driveTrain = driveTrain;
        m_angle = angle;
        m_speed = speed;
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
        // the command is finished if the angle is at least m_angle
        return m_gyro.getAngle() >= m_angle;
    }
}
