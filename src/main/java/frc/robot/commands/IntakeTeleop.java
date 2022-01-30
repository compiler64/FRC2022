package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.intake;

public class IntakeTeleop extends CommandBase {
    private intake m_intake;
    private Camera m_camera;

    public IntakeTeleop(intake intake, Camera camera) {
        m_intake = intake;
        m_camera = camera;
    }

    @Override
    public void initialize() {
        m_camera.setPipelineTo(Camera.getNonAlliancePipeline());
    }

    @Override
    public void execute() {
        // intake if the button was pressed and there is no ball of the wrong color
        if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_INTAKE, true) && !badBall()) {
            intake();
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

    public void intake() {
        // TODO call a method on m_intake
    }
}
