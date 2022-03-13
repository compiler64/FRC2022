package frc.robot.commands;

import static frc.robot.Constants.*;

import frc.robot.subsystems.Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;
import frc.robot.subsystems.Intake;

/**
 * Runs the autonomous commands in order.
 * Makes the robot drive back, pick up a ball, shoot two balls, get two balls from the terminal, and shoot those balls.
 */
public class TerminalAutoCommand  extends SequentialCommandGroup {
    public TerminalAutoCommand(DriveTrain driveTrain, Gyro gyro, Camera camera, Intake intake, Transport transport, Shooter shooter) {
        
        Command[] commands = {
            new FollowBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, transport, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, gyro, 6, AUTO_SPEED, false),
            new ChangeHeading(driveTrain, gyro, 22, AUTO_SPEED, false),

            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),

            // if low goal
            new DriveDistanceAuto(driveTrain, gyro, 4, AUTO_SPEED, true),

            // new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
            new DriveDistanceAuto(driveTrain, gyro, -3, AUTO_SPEED, false),
            new TurnAngleAuto(driveTrain, gyro, 132, AUTO_SPEED),

            // new EnableHighGear(driveTrain, true),
            new DriveDistanceAuto(driveTrain, gyro, 15, AUTO_SPEED, true), //top speed possible if not already enabled
            // new EnableHighGear(driveTrain, false),
            new FollowBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, transport, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
            
            new TurnAngleAuto(driveTrain, gyro, 130, AUTO_SPEED),
            // new EnableHighGear(driveTrain, true),
            new DriveDistanceAuto(driveTrain, gyro, 19, AUTO_SPEED, false), //top speed possible if not already enabled
            // new EnableHighGear(driveTrain, false),
            new ChangeHeading(driveTrain, gyro, 132, AUTO_SPEED, false),
            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            new DriveDistanceAuto(driveTrain, gyro, 2.4, AUTO_SPEED, true),
            // new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
            new TurnFlywheel(shooter, 0),
            // get out of the way
            new DriveDistanceAuto(driveTrain, gyro, -1.5, AUTO_SPEED, false),
            new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, gyro, 8, AUTO_SPEED, true),
        };
        addCommands(commands);
    }
}
