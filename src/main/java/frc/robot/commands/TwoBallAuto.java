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

/**
 * Runs the autonomous commands in order.
 * Makes the robot drive back, pick up the leftmost ball, and shoot the two balls.
 */
public class TwoBallAuto extends SequentialCommandGroup {
    public TwoBallAuto(DriveTrain driveTrain, Gyro gyro, Camera camera, Intake intake, Transport transport, Shooter shooter) {
        Command[] commands = {
            new StartIntake(intake, transport, AUTO_INTAKE_SPEED, TRANSFER_SPEED),
            new WaitCommand(.01),
            new DriveDistanceAuto(driveTrain, gyro, 4, AUTO_SPEED, true),
            new WaitCommand(1),
            new StopIntake(intake, transport),
            new DriveDistanceAuto(driveTrain, gyro, -1, AUTO_SPEED, true),
            new TurnAngleAuto(driveTrain, gyro, 148, AUTO_SPEED),
            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            new WaitCommand(1.5),
            new ShootBall(shooter, transport, INDEXING_WHEEL_SPEED, TRANSFER_SPEED, BALL_SHOOT_TIME),
            new WaitCommand(1),
            new ShootBall(shooter, transport, INDEXING_WHEEL_SPEED, TRANSFER_SPEED, BALL_SHOOT_TIME),
            new TurnFlywheel(shooter, 0),
            //get out of the way
            //new DriveDistanceAuto(driveTrain, gyro, -7, AUTO_SPEED, false),
        };
        addCommands(commands);
    }
}
