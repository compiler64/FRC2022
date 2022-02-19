package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Shooter;

public class ShootTeleop extends CommandBase {
    private Shooter m_shooter;
    private double m_flywheelSpeed;
    private double m_indexerSpeed;
    private boolean m_flywheel_on;

    public ShootTeleop(Shooter shooter, double flywheelSpeed, double indexerSpeed) {
        m_shooter = shooter;
        m_flywheelSpeed = flywheelSpeed;
        m_indexerSpeed = indexerSpeed;
        m_flywheel_on = false;

        addRequirements(shooter);

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
            
        }
        if (Controllers.isButtonReleased(PortMap.XBOX_BUTTON_SHOOT_BALLS, true)) {
            m_shooter.setIndexingWheelSpeed(0);
            
        }
    }
}
