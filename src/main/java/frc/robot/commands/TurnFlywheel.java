package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

/**
 * Starts or stops the flywheel.
 */
public class TurnFlywheel extends CommandBase {
    private Shooter m_shooter;
    private double m_speed;
    boolean oneround = false;

    /**
     * Creates a new TurnFlywheel command.
     * @param shooter the shooter subsystem of the robot
     * @param speed the speed to turn the flywheel at
     */
    public TurnFlywheel(Shooter shooter, double speed) {
        m_shooter = shooter;
        m_speed = speed;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        m_shooter.setFlywheelSpeed(m_speed);
    }

    @Override
    public void execute() {
        oneround = true;
    }

    @Override
    public boolean isFinished() {
        return oneround;
    }
}
