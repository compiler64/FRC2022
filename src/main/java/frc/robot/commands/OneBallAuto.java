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
 * Autonomous command that shoots a ball and then drives backwards out of the tarmac.
 */
public class OneBallAuto extends SequentialCommandGroup {
    /**
     * Creates a new OneBallAuto command.
     * @param driveTrain the drive train of the robot
     * @param gyro the gyro of the robot
     * @param camera the camera of the robot
     * @param intake the intake of the robot
     * @param transport the transport of the robot
     * @param shooter the shooter of the robot
     */
    public OneBallAuto(DriveTrain driveTrain, Gyro gyro, Camera camera, Intake intake, Transport transport, Shooter shooter) {
        Command[] commands = {
            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            new WaitCommand(0.25),
            new ShootBall(shooter, transport, INDEXING_WHEEL_SPEED, TRANSFER_SPEED, BALL_SHOOT_TIME),
            new TurnFlywheel(shooter, 0),
            new DriveDistanceAuto(driveTrain, gyro, -7, AUTO_SPEED, true),
        };

        addCommands(commands);
    }
}