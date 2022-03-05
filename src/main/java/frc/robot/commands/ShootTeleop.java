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
    private boolean m_indexing_wheel_on = false;

    public ShootTeleop(Shooter shooter, double flywheelSpeed, double indexerSpeed) {
        m_shooter = shooter;
        m_flywheelSpeed = flywheelSpeed;
        m_indexerSpeed = indexerSpeed;
        m_flywheel_on = false;

        addRequirements(shooter);

        Shuffleboard.getTab("main").addBoolean("Flywheel On", () -> m_flywheel_on);
        Shuffleboard.getTab("main").addBoolean("Indexing wheel On", () -> m_indexing_wheel_on);

    }

    @Override
    public void execute() {
        // the flywheel
        if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_START_FLYWHEEL, true)) {
            m_flywheel_on = !m_flywheel_on;
            m_shooter.setFlywheelSpeed(m_flywheel_on ? m_flywheelSpeed : 0);
        }
        

        // the indexer and transfer
        if (Controllers.GetRawAxis(PortMap.XBOX_R_TRIGGER, true) > .50) {
            m_shooter.setIndexingWheelSpeed(m_indexerSpeed);
            m_indexing_wheel_on = true;
            
        }
        if (Controllers.GetRawAxis(PortMap.XBOX_R_TRIGGER, true) < .50) {
            m_shooter.setIndexingWheelSpeed(0);
            m_indexing_wheel_on = false;
        }
    }
}
