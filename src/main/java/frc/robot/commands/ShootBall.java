package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * Makes the robot shoot a ball by turning the indexing wheel.
 * Only works if the flywheel is already on.
 */
public class ShootBall extends CommandBase {
    private Shooter m_shooter;
    private Timer m_timer = new Timer();
    private double m_indexingWheelSpeed;
    private double m_shootTime;

    /**
     * Creates a new ShootBall command.
     * @param shooter the shooter subsystem of the robot
     * @param indexingWheelSpeed the speed of the indexing wheel
     * @param shootTime the time it should take to shoot the ball
     */
    public ShootBall(Shooter shooter, double indexingWheelSpeed, double shootTime) {
        m_shooter = shooter;
        m_indexingWheelSpeed = indexingWheelSpeed;
        m_shootTime = shootTime;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        m_timer.reset();
        m_shooter.setIndexingWheelSpeed(m_indexingWheelSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setIndexingWheelSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return m_timer.get() >= m_shootTime;
    }
}
