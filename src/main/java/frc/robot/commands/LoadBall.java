package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Transport;

/**
 * Loads a ball from the belt to the indexing wheel.
 */
public class LoadBall extends CommandBase {
    private Transport m_transport;
    private Timer m_timer = new Timer();
    private double m_beltSpeed;
    private double m_loadTime;

    /**
     * Creates a new LoadBall command.
     * @param transport the intake of the robot
     * @param beltSpeed the speed of the conveyor belt
     * @param loadTime the time it should take to load the ball
     */
    public LoadBall(Transport transport, double beltSpeed, double loadTime) {
        m_transport = transport;
        m_beltSpeed = beltSpeed;
        m_loadTime = loadTime;

        addRequirements(transport);
    }

    @Override
    public void initialize() {
        m_timer.reset();
        m_transport.run(m_beltSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        m_transport.run(0);
    }

    @Override
    public boolean isFinished() {
        return m_timer.get() >= m_loadTime;
    }
}
