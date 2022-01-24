package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

/**
 * Makes the robot drive a given distance at a fixed speed.
 */
public class DriveDistanceAuto extends CommandBase {
    private DriveTrain m_driveTrain;
    private double m_distance;
    private double m_speed;

    /**
     * Creates a new DriveDistanceAuto command.
     * @param driveTrain the DriveTrain subsystem
     * @param distance the distance to travel
     * @param speed the speed to travel at
     */
    public DriveDistanceAuto(DriveTrain driveTrain, double distance, double speed) {
        m_driveTrain = driveTrain;
        m_distance = distance;
        m_speed = speed;

        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        // set the speed of both motors to m_speed
        m_driveTrain.setBothMotors(m_speed);
        // reset the encoders
        if (Constants.ENCODERS_READY) {
            m_driveTrain.resetEncoders();
        }
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
