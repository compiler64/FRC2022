package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class LoadBall extends CommandBase {
    private Shooter m_shooter;

    public LoadBall(Shooter shooter) {
        m_shooter = shooter;
    }

    @Override
    public void initialize() {
        m_shooter.loadBall();
    }

    @Override
    public boolean isFinished() {
        // TODO return true only when finished loading
        return true;
    }
}
