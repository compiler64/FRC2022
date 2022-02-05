package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;

import static frc.robot.Constants.*;

public class DriveToBall extends CommandBase {
    private DriveTrain m_driveTrain;
    private Camera m_camera;
    private double m_speed;

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
