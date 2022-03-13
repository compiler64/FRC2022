package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

import static frc.robot.Constants.*;

public class OneBallAuto extends SequentialCommandGroup {
    public OneBallAuto(DriveTrain driveTrain, Gyro gyro, Camera camera, Intake intake, Transport transport, Shooter shooter) {
        Command[] commands = {
            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            new WaitCommand(0.5),
            new ShootBall(shooter, transport, INDEXING_WHEEL_SPEED, TRANSFER_SPEED, BALL_SHOOT_TIME),
            new TurnFlywheel(shooter, 0),
            new DriveDistanceAuto(driveTrain, gyro, -7, AUTO_SPEED, true),
        };

        addCommands(commands);
    }
}