package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveDistanceAuto extends CommandBase {
    private DriveTrain m_driveTrain;
    private double m_distance;
    private double m_speed;

    public DriveDistanceAuto(DriveTrain driveTrain, double distance, double speed) {
        m_driveTrain = driveTrain;
        m_distance = distance;
        m_speed = speed;
    }

    @Override
    public void initialize() {
        // set the speed of both motors to m_speed
        m_driveTrain.setBothMotors(m_speed);
    }

    @Override
    public boolean isFinished() {
        // the command is finished if the distance is at least m_distance
        return m_driveTrain.getAverageEncoderDistance() >= m_distance;
    }

    @Override
    public void end(boolean interrupted) {
        // if the command ends, stop both motors
        m_driveTrain.setBothMotors(0);
    }
}
