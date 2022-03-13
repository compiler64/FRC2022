package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

/**
 * Makes the robot shoot a ball by turning the indexing wheel.
 * Only works if the flywheel is already on.
 */
public class ShootBall extends CommandBase {
    private Shooter m_shooter;
    private Timer m_timer = new Timer();
    private double m_indexingWheelSpeed;
    private double m_shootTime;
    private Transport m_transport;
    private double m_transferSpeed;


    /**
     * Creates a new ShootBall command.
     * @param shooter the shooter subsystem of the robot
     * @param indexingWheelSpeed the speed of the indexing wheel
     * @param shootTime the time it should take to shoot the ball
     */
    public ShootBall(Shooter shooter, Transport transport, double indexingWheelSpeed, double transferSpeed, double shootTime) {
        m_shooter = shooter;
        m_transport = transport;
        m_indexingWheelSpeed = indexingWheelSpeed;
        m_shootTime = shootTime;
        m_transferSpeed = transferSpeed;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        m_timer.start();
        m_timer.reset();
        m_transport.run(m_transferSpeed);
        m_shooter.setIndexingWheelSpeed(m_indexingWheelSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setIndexingWheelSpeed(0);
        m_transport.run(0);
    }

    @Override
    public boolean isFinished() {
        return m_timer.get() >= m_shootTime;
    }
}
