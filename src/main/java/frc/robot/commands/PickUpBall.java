package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;

/**
 * Makes the robot pick up a ball in front of the intake.
 */
public class PickUpBall extends CommandBase {
    private Timer m_timer = new Timer();
    private Intake m_intake;
    private Transport m_transport;
    private double m_intakeSpeed;
    private double m_beltSpeed;
    private double m_time;

    /**
     * Creates a new PickUpBall command.
     * @param intake the intake of the robot
     * @param intakeSpeed the speed of the intake
     * @param beltSpeed the speed of the conveyor belt
     * @param time the time it should take to pick up the ball
     */
    public PickUpBall(Intake intake, Transport transport, double intakeSpeed, double beltSpeed, double time) {
        m_intake = intake;
        m_transport = transport;
        m_intakeSpeed = intakeSpeed;
        m_time = time;
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        m_intake.lower();
        m_intake.setSpeed(m_intakeSpeed);
        m_transport.run(m_beltSpeed);

        m_timer.start();
        m_timer.reset();
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.raise();
        m_intake.setSpeed(0);
        m_transport.run(0);
    }

    @Override
    public boolean isFinished() {
        return m_timer.get() >= m_time;
    }
}
