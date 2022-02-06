package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootBall extends CommandBase {
    private Shooter m_shooter;
    private Timer m_timer = new Timer();
    private double m_indexingWheelSpeed;
    private double m_shootTime;

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

    public boolean isFinished() {
        return m_timer.get() >= m_shootTime;
    }
}
