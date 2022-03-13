package frc.robot.commands;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;
import frc.robot.subsystems.Intake;

/**
 * Runs the autonomous commands in order.
 * Makes the robot shoot one ball, drive in a triangle, pick up two balls, and shoot two balls.
 */
public class TriangleAutoCommand extends SequentialCommandGroup {
    public TriangleAutoCommand(DriveTrain driveTrain, Gyro gyro, Camera camera, Intake intake, Transport transport, Shooter shooter) {
        Command[] commands = {
            // new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            new WaitCommand(1), // Just long enough to get the flywheel to speed, may need to be changed
            // new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
            new DriveDistanceAuto(driveTrain, gyro, -1.5, AUTO_SPEED, true),
            new TurnAngleAuto(driveTrain, gyro, -100, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, gyro, 6.2, AUTO_SPEED, false),
           
            // new FollowBall(driveTrain, camera, AUTO_SPEED),
            // new PickUpBall(intake, transport, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, -1.65, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, gyro, 4.1, AUTO_SPEED, false),
           
            // new FollowBall(driveTrain, camera, AUTO_SPEED),
            // new PickUpBall(intake, transport, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, -110, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, gyro, 7, AUTO_SPEED, false),
            new ChangeHeading(driveTrain, gyro, 40, AUTO_SPEED, false),
            new DriveDistanceAuto(driveTrain, gyro, 1, AUTO_SPEED, true),
            // new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            // new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
            // new LoadBall(transport, AUTO_BELT_SPEED, BALL_LOAD_TIME),
            // new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
            // new TurnFlywheel(shooter, 0),
            // // get out of the way
            // new DriveDistanceAuto(driveTrain, gyro, -1.5, AUTO_SPEED, false),
            // new TurnAngleAuto(driveTrain, gyro, 160, AUTO_SPEED),
            // new DriveDistanceAuto(driveTrain, gyro, 7, AUTO_SPEED, true),
        };
        addCommands(commands);
    }
}