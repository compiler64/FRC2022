package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Wait extends CommandBase {
    private double m_seconds;
    private Timer m_timer = new Timer();

    public Wait(double seconds) {
        m_seconds = seconds;
    }

    public void initialize() {
        m_timer.reset();
    }

    public boolean isFinished() {
        return m_timer.get() >= m_seconds;
    }
}
