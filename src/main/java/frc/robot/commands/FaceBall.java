package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;

/**
 * Makes the robot turn to face a ball of the alliance color.
 */
public class FaceBall extends CommandBase {
    private DriveTrain m_driveTrain;
    private Camera m_camera;

    private double m_speed;
    private double m_sign = 0;

    /**
     * Creates a new FaceBall command.
     * @param driveTrain the drive train of the robot
     * @param camera the camera of the robot
     * @param speed the factor to multiply the speed by
     */
    public FaceBall(DriveTrain driveTrain, Camera camera, double speed) {
        m_driveTrain = driveTrain;
        m_camera = camera;
        m_speed = speed;

        addRequirements(driveTrain, camera);
    }

    @Override
    public void initialize() {
        m_sign = Math.signum(m_camera.getX());

        m_driveTrain.setLeftMotors(m_sign * m_speed);
        m_driveTrain.setRightMotors(-m_sign * m_speed);

        m_camera.setLED(true);
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrain.setBothMotors(0);
        m_camera.setLED(false);
    }

    @Override
    public boolean isFinished() {
        return m_sign * m_camera.getX() <= 0;
    }
}
