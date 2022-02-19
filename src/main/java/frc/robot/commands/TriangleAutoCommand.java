package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

public class TriangleAutoCommand extends SequentialCommandGroup {
    public TriangleAutoCommand(DriveTrain driveTrain, Gyro gyro, Pneumatics pneumatics, Camera camera, Intake intake, Shooter shooter) {
        Command[] commands = {
            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            // new DriveDistanceAuto(driveTrain, gyro, 1, AUTO_SPEED, true),
            new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
            new DriveDistanceAuto(driveTrain, gyro, -1, AUTO_SPEED, true),
            new TurnAngleAuto(driveTrain, gyro, 140, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, gyro, 7.5, AUTO_SPEED, false),
           
            new FollowBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, 120, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, gyro, 4, AUTO_SPEED, false),
            new FollowBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, 110, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, gyro, 7, AUTO_SPEED, false),
            new ChangeHeading(driveTrain, gyro, 40, AUTO_SPEED, false),
            new DriveDistanceAuto(driveTrain, gyro, 1, AUTO_SPEED, true),
            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
            new LoadBall(intake, AUTO_BELT_SPEED, BALL_LOAD_TIME),
            new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
        };
        addCommands(commands);
    }
}