package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pneumatics;

/**
 * Enables or disables high gear for the robot.
 */
public class EnableHighGear extends CommandBase {
    private Pneumatics m_pneumatics;
    private boolean m_enable;

    /**
     * Creates a new EnableHighGear command.
     * @param pneumatics the pneumatics of the robot
     * @param enable {@literal true} if the high gear should be enabled,
     * {@literal false} if the high gear should be disabled
     */
    public EnableHighGear(Pneumatics pneumatics, boolean enable) {
        m_pneumatics = pneumatics;
        m_enable = enable;
    }

    @Override
    public void initialize() {
        m_pneumatics.setSingle(0, m_enable);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
