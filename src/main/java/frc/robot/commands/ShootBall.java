package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootBall extends CommandBase {
    private Shooter m_shooter;

    public ShootBall(Shooter shooter) {
        m_shooter = shooter;
    }

    @Override
    public void initialize() {
        // TODO shoot ball
    }

    public boolean isFinished() {
        return true;
    }
}
