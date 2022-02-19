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

public class TwoBallAuto extends SequentialCommandGroup {
    public TwoBallAuto(DriveTrain driveTrain, Gyro gyro, Pneumatics pneumatics, Camera camera, Intake intake, Shooter shooter) {
        Command[] commands = {
            new FollowBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
            // if low goal
            new DriveDistanceAuto(driveTrain, gyro, 6, AUTO_SPEED, false),
            new ChangeHeading(driveTrain, gyro, 22, AUTO_SPEED, false),

            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),

            // if low goal
            new DriveDistanceAuto(driveTrain, gyro, 4, AUTO_SPEED, true),

            new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
        };
        addCommands(commands);
    }
}
