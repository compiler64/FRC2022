package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

import static frc.robot.Constants.*;

/**
 * Runs the autonomous commands in order.
 */
public class AutoCommandNew extends SequentialCommandGroup {
    /**
     * Creates a new AutoCommand command.
     * @param driveTrain the drive train of the robot
     * @param gyro the gyro of the robot
     * @param pneumatics the pneumatics of the robot
     * @param camera the camera of the robot
     * @param intake the intake of the robot
     * @param shooter the shooter of the robot
     */
    public AutoCommandNew(DriveTrain driveTrain, Gyro gyro, Camera camera, Intake intake, Transport transport, Shooter shooter) {
        Command[] commands = {
                new TurnAngleAuto(driveTrain, gyro, 20, AUTO_SPEED),
                new FaceBall(driveTrain, camera, AUTO_SPEED),
                new DriveToBall(driveTrain, camera, AUTO_SPEED),
                new PickUpBall(intake, transport, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
                new TurnToAngle(driveTrain, gyro, 180, AUTO_SPEED),
                new DriveDistanceAuto(driveTrain, gyro, 16.3, AUTO_SPEED, true),
                new TurnAngleAuto(driveTrain, gyro, 135, AUTO_SPEED),
                new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
                new DriveDistanceAuto(driveTrain, gyro, 3.7, AUTO_SPEED, true),
                new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
                new LoadBall(transport, AUTO_BELT_SPEED, BALL_LOAD_TIME),
                new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
                new TurnFlywheel(shooter, 0),
                /*
                 * if we are going for the middle ball as well do this
                 * later put in if statement
                 */
                new DriveDistanceAuto(driveTrain, gyro, -1, AUTO_SPEED, true),
                new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
                new DriveDistanceAuto(driveTrain, gyro, 2.6, AUTO_SPEED, true),
                new TurnAngleAuto(driveTrain, gyro, 135, AUTO_SPEED),
                new FaceBall(driveTrain, camera, AUTO_SPEED),
                new DriveToBall(driveTrain, camera, AUTO_SPEED),
                new PickUpBall(intake, transport, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
                new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
                new DriveDistanceAuto(driveTrain, gyro, 16, AUTO_SPEED, true),
                new TurnAngleAuto(driveTrain, gyro, -135, AUTO_SPEED),
                new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
                // new ParallelCommandGroup(
                    new DriveDistanceAuto(driveTrain, gyro, 3.6, AUTO_SPEED, true),
                    // new LoadBall(shooter) // there is only one ball
                // ),
                new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
                new TurnFlywheel(shooter, 0),
                /* 
                 * if after that we go for the balls at the terminal
                 * not finished yet
                 */
                new DriveDistanceAuto(driveTrain, gyro, -1, AUTO_SPEED, true),
                new TurnAngleAuto(driveTrain, gyro, -128, AUTO_SPEED),
                new DriveDistanceAuto(driveTrain, gyro, -1, AUTO_SPEED, true),
        };

        addCommands(commands);
    }
}
