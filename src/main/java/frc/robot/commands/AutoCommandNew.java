package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;

import static frc.robot.Constants.*;

public class AutoCommandNew extends SequentialCommandGroup {
    public AutoCommandNew(DriveTrain driveTrain, Gyro gyro, Pneumatics pneumatics, Camera camera, Intake intake, Shooter shooter) {
        Command[] commands = {
                new TurnAngleAuto(driveTrain, gyro, 20, AUTO_SPEED),
                new FaceBall(driveTrain, camera, AUTO_SPEED),
                new DriveToBall(driveTrain, camera, AUTO_SPEED),
                new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
                new TurnToAngle(driveTrain, gyro, 180, AUTO_SPEED),
                new DriveDistanceAuto(driveTrain, 16.3, AUTO_SPEED),
                new TurnAngleAuto(driveTrain, gyro, 135, AUTO_SPEED),
                new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
                new DriveDistanceAuto(driveTrain, 3.7, AUTO_SPEED),
                new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
                new LoadBall(intake, AUTO_BELT_SPEED, BALL_LOAD_TIME),
                new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
                new TurnFlywheel(shooter, 0),
                /*
                 * if we are going for the middle ball as well do this
                 * later put in if statement
                 */
                new DriveDistanceAuto(driveTrain, -1, AUTO_SPEED),
                new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
                new DriveDistanceAuto(driveTrain, 2.6, AUTO_SPEED),
                new TurnAngleAuto(driveTrain, gyro, 135, AUTO_SPEED),
                new FaceBall(driveTrain, camera, AUTO_SPEED),
                new DriveToBall(driveTrain, camera, AUTO_SPEED),
                new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
                new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
                new DriveDistanceAuto(driveTrain, 16, AUTO_SPEED),
                new TurnAngleAuto(driveTrain, gyro, -135, AUTO_SPEED),
                new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
                // new ParallelCommandGroup(
                    new DriveDistanceAuto(driveTrain, 3.6, AUTO_SPEED),
                    // new LoadBall(shooter) // there is only one ball
                // ),
                new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
                new TurnFlywheel(shooter, 0),
                /* 
                 * if after that we go for the balls at the terminal
                 * not finished yet
                 */
                new DriveDistanceAuto(driveTrain, -1, AUTO_SPEED),
                new TurnAngleAuto(driveTrain, gyro, -128, AUTO_SPEED),
                new DriveDistanceAuto(driveTrain, -1, AUTO_SPEED),
        };

        addCommands(commands);
    }
}
