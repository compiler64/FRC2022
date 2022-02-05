package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake;

public class PickUpBall extends CommandBase {
    private Timer m_timer = new Timer();
    private intake m_intake;
    private double m_intakeSpeed;
    private double m_time;

    public PickUpBall(intake intake, double intakeSpeed, double time) {
        m_intake = intake;
        m_intakeSpeed = intakeSpeed;
        m_time = time;
    }

    @Override
    public void initialize() {
        m_intake.setIntakeSpeed(m_intakeSpeed);
        m_timer.reset();
    }

    @Override
    public boolean isFinished() {
        return m_timer.get() >= m_time;
    }
}
