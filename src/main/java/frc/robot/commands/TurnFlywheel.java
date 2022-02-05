package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake;

public class TurnFlywheel extends CommandBase {
    private intake m_intake;
    private double m_flywheelSpeed;

    public TurnFlywheel(intake intake, double flywheelSpeed) {
        m_intake = intake;
        m_flywheelSpeed = flywheelSpeed;
    }

    @Override
    public void initialize() {
        m_intake.setFlywheelSpeed(m_flywheelSpeed);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
