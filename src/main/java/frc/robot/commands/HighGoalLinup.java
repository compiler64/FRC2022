// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;

public class HighGoalLinup extends CommandBase {
    DriveTrain m_driveTrain;
    Camera m_camera;

    /** Creates a new HighGoalLinup. */
    public HighGoalLinup(DriveTrain drivetrain, Camera camera) {
        m_driveTrain = drivetrain;
        m_camera = camera;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_camera.setPipelineTo(2);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (m_camera.getY() < -3) {
            // drive forward slightly
            m_driveTrain.setBothMotors(.15);
        } else if (m_camera.getY() > 3) {
            // drive backward slightly
            m_driveTrain.setBothMotors(-.15);
        }

        if (m_camera.getX() > 3) {
            m_driveTrain.setLeftMotors(-.15);
            m_driveTrain.setRightMotors(.15);
        } else if (m_camera.getX() < -3) {
            m_driveTrain.setRightMotors(-.15);
            m_driveTrain.setLeftMotors(.15);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_driveTrain.setBothMotors(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_camera.getX() < 1 && m_camera.getX() > -1 && m_camera.getY() < 1 && m_camera.getY() > -1;
    }
}
