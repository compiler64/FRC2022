package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;

import static frc.robot.Constants.*;

public class TeleopCommand extends ParallelCommandGroup {
    public TeleopCommand(DriveTrain driveTrain, Gyro gyro, Pneumatics pneumatics, Camera camera, Intake intake, Shooter shooter, Hanger hanger) {
        Command[] commands = {
            new DriveTeleop(driveTrain),
            new IntakeTeleop(intake, camera, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED),
            new ShootTeleop(shooter, intake, AUTO_FLYWHEEL_SPEED, INDEXING_WHEEL_SPEED, AUTO_BELT_SPEED),
            new SingleSolenoid(pneumatics),
            new ClimberTeleop(hanger),
        };

        addCommands(commands);
    }
}
