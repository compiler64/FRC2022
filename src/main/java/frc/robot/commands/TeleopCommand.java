package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

import static frc.robot.Constants.*;

/**
 * Runs all of the teleop commands at the same time:
 * DriveTeleop, IntakeShooterTeleop, ClimberTeleop.
 */
public class TeleopCommand extends ParallelCommandGroup {
    /**
     * Creates a new TeleopCommand.
     * @param driveTrain the drive train subsystem
     * @param gyro the gyro subsystem
     * @param camera the camera subsystem
     * @param intake the intake subsystem
     * @param shooter the shooter subsystem
     * @param transport the transport subsystem
     * @param hanger the hanger subsystem
     */
    public TeleopCommand(DriveTrain driveTrain, Gyro gyro, Camera camera, Intake intake, Shooter shooter, Transport transport, Hanger hanger) {
        Command[] commands = {
            new DriveTeleop(driveTrain),
            new IntakeShooterTeleop(shooter, transport, intake, FLYWHEEL_SPEED, INDEXING_WHEEL_SPEED, TRANSFER_SPEED, AUTO_INTAKE_SPEED),
            new ClimberTeleop(hanger),
        };

        addCommands(commands);
    }
}
