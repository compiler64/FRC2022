package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

public class ShootTeleop extends CommandBase {
    private Shooter m_shooter;
    private Intake m_intake;
    private double m_flywheelSpeed;
    private double m_indexerSpeed;
    private double m_beltSpeed;
    private boolean m_flywheel_on;

    public ShootTeleop(Shooter shooter, Intake intake, double flywheelSpeed, double indexerSpeed, double beltSpeed) {
        m_shooter = shooter;
        m_intake = intake;
        m_flywheelSpeed = flywheelSpeed;
        m_indexerSpeed = indexerSpeed;
        m_beltSpeed = beltSpeed;
        m_flywheel_on = false;

        addRequirements(shooter, intake);

        Shuffleboard.getTab("main").addBoolean("Flywheel On", () -> m_flywheel_on);
    }

    @Override
    public void execute() {
        // the flywheel
        if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_START_FLYWHEEL, true)) {
            m_flywheel_on = !m_flywheel_on;
            m_shooter.setFlywheelSpeed(m_flywheel_on ? m_flywheelSpeed : 0);
        }

        // the indexer and transfer
        if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_SHOOT_BALLS, true)) {
            m_shooter.setIndexingWheelSpeed(m_indexerSpeed);
            m_intake.setBeltSpeed(m_beltSpeed);
        }
        if (Controllers.isButtonReleased(PortMap.XBOX_BUTTON_SHOOT_BALLS, true)) {
            m_shooter.setIndexingWheelSpeed(0);
            m_intake.setBeltSpeed(0);
        }
    }
}