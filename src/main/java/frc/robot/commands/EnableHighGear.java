package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

/**
 * Enables or disables high gear for the robot.
 * Sets the high gear solenoid to "on"/"off" for 100ms and then sets it to "centered".
 */
public class EnableHighGear extends CommandBase {
    private DriveTrain m_driveTrain;
    private boolean m_enable;
    private int timePassed;

    /**
     * Creates a new EnableHighGear command.
     * @param pneumatics the pneumatics of the robot
     * @param enable {@literal true} if the high gear should be enabled,
     * {@literal false} if the high gear should be disabled
     */
    public EnableHighGear(DriveTrain driveTrain, boolean enable) {
        m_driveTrain = driveTrain;
        m_enable = enable;

        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        if (m_enable) {
            m_driveTrain.setHighGear("on");
        } else {
            m_driveTrain.setHighGear("off");
        }
        timePassed = 0;
    }

    @Override
    public void execute() {
        timePassed++;
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrain.setHighGear("centered");
    }

    @Override
    public boolean isFinished() {
        return (timePassed >= 5); // finishes after 100ms
    }
}
