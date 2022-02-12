package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;

import static frc.robot.Constants.*;

/**
 * Makes the robot drive forward until a ball of the right color is at the intake.
 */
public class DriveToBall extends CommandBase {
    private DriveTrain m_driveTrain;
    private Camera m_camera;
    private double m_speed;

    /**
     * Creates a new DriveToBall command.
     * @param driveTrain the drive train of the robot
     * @param camera the camera of the robot
     * @param speed the speed to drive at
     */
    public DriveToBall(DriveTrain driveTrain, Camera camera, double speed) {
        m_driveTrain = driveTrain;
        m_camera = camera;
        m_speed = speed;

        addRequirements(driveTrain, camera);
    }

    @Override
    public void initialize() {
        m_driveTrain.setBothMotors(m_speed);
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrain.setBothMotors(0);
    }

    @Override
    public boolean isFinished() {
        return m_camera.getY() >= CAMERA_Y_OF_BALL_AT_INTAKE;
    }
}
