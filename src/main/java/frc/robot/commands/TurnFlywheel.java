package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class TurnFlywheel extends CommandBase {
    private Shooter m_shooter;
    private double m_speed;

    public TurnFlywheel(Shooter shooter, double speed) {
        m_shooter = shooter;
        m_speed = speed;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        m_shooter.setFlywheelSpeed(m_speed);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
