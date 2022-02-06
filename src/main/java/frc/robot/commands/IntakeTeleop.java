package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.intake;

public class IntakeTeleop extends CommandBase {
    private intake m_intake;
    private Camera m_camera;
    private double m_intakeSpeed;
    private double m_beltSpeed;

    public IntakeTeleop(intake intake, Camera camera, double intakeSpeed, double beltSpeed) {
        m_intake = intake;
        m_camera = camera;
        m_intakeSpeed = intakeSpeed;
        m_beltSpeed = beltSpeed;

        addRequirements(intake, camera);
    }

    @Override
    public void initialize() {
        m_camera.setPipelineTo(Camera.getNonAlliancePipeline());
    }

    @Override
    public void execute() {
        // intake if the button was pressed and there is no ball of the wrong color
        // might need to be reconfigured to a toggle button or two buttons
        if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_INTAKE, true) && !badBall()) {
            m_intake.lower();
            intake(true);
        } else if (Controllers.isButtonReleased(PortMap.XBOX_BUTTON_INTAKE, true)) {
            intake(false);
            m_intake.raise();
        }
    }

    public boolean badBall() {
        // m_camera.setPipeline();

        // double goodBallArea;

        // if (m_camera.hasValidTarget()) {
        //     goodBallArea = m_camera.getArea();
        // }

        return m_camera.hasValidTarget();
    }

    public void intake(boolean on) {
        m_intake.setSpeed(on ? m_intakeSpeed : 0, on ? m_beltSpeed : 0);
    }
}
