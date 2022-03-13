package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Intake;

import static frc.robot.Constants.*;

/**
 * Controls the intake with an Xbox controller.
 */
public class IntakeTeleop extends CommandBase {
    private Intake m_intake;
    private Camera m_camera;
    private double m_intakeSpeed;
    private boolean isDown = false;
    private boolean wheelsOn = false;

    /**
     * Creates a new IntakeTeleop command.
     * @param intake the intake of the robot
     * @param camera the camera of the robot
     * @param intakeSpeed the speed of the intakle
     * @param beltSpeed the speed of the conveyor belt
     */
    public IntakeTeleop(Intake intake, Camera camera, double intakeSpeed, double beltSpeed) {
        m_intake = intake;
        m_camera = camera;
        m_intakeSpeed = intakeSpeed;
        

        addRequirements(intake, camera);
    }

    @Override
    public void initialize() {
        m_camera.setPipelineTo(Camera.getNonAlliancePipeline());
        m_camera.setLED(true);
    }

    @Override
    public void execute() {
        // intake if the button was pressed and there is no ball of the wrong color
        // might need to be reconfigured to a toggle button or two buttons
        if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_INTAKE_WHEELS, true) && !badBall()) {
            intake(!wheelsOn);
            wheelsOn = !wheelsOn;
        }

        if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_INTAKE_LIFT, false)) {
            if (isDown ) {
                m_intake.raise();
            } else {
                m_intake.lower();
            }
            isDown = !isDown;
        }
        

        if (Controllers.isButtonReleased(PortMap.XBOX_BUTTON_INTAKE_LIFT, false)) {
            m_intake.nutral();
        }
    }

    /**
     * Checks if there is a ball of the wrong color in front of the intake.
     * @return {@literal true} if there is a ball of the wrong color in front of the intake
     */
    public boolean badBall() {
        // m_camera.setPipeline();

        // double goodBallArea;

        // if (m_camera.hasValidTarget()) {
        //     goodBallArea = m_camera.getArea();
        // }

        return m_camera.hasValidTarget() && m_camera.getY() <= BAD_BALL_MAX_Y;
    }

    /**
     * Turns the intake on or off.
     * @param on {@literal true} if the intake should be on.
     */
    public void intake(boolean on) {
        m_intake.setSpeed(on ? m_intakeSpeed : 0);
    }

    @Override
    public void end(boolean interrupted) {
        m_camera.setLED(false);
    }
}
