package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import static frc.robot.Constants.*;

/**
 * Tests the encoders in the drive train subsystem.
 */
public class EncoderTest extends SequentialCommandGroup {
    public EncoderTest (DriveTrain driveTrain, Gyro gyro, Pneumatics pneumatics, Camera camera, Intake intake, Shooter shooter) {
        Command[] commands = {
            new DriveDistanceAuto(driveTrain, gyro, 17, AUTO_SPEED, false),
            new ChangeHeading(driveTrain, gyro, -90, AUTO_SPEED, true),
        };

    addCommands(commands);
    }
    
}
