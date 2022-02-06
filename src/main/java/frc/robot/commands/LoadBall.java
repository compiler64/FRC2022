package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class LoadBall extends CommandBase {
    private Intake m_intake;
    private Timer m_timer = new Timer();
    private double m_beltSpeed;
    private double m_loadTime;

    public LoadBall(Intake intake, double beltSpeed, double loadTime) {
        m_intake = intake;
        m_beltSpeed = beltSpeed;
        m_loadTime = loadTime;

        addRequirements(intake);
    }

    @Override
    public void initialize() {
        m_timer.reset();
        m_intake.setBeltSpeed(m_beltSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.setBeltSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return m_timer.get() >= m_loadTime;
    }
}
