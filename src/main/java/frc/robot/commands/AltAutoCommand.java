package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.Constants.*;

import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.intake;

public class AltAutoCommand extends SequentialCommandGroup {
    public AltAutoCommand(DriveTrain driveTrain, Gyro gyro, Pneumatics pneumatics, Camera camera, intake intake, Shooter shooter) {
        Command[] commands = {
            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            new DriveDistanceAuto(driveTrain, 1, AUTO_SPEED),
            new ShootBall(shooter),
            new TurnAngleAuto(driveTrain, gyro, 140, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, 12, AUTO_SPEED),
            new FaceBall(driveTrain, camera, AUTO_SPEED),
            new DriveToBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, 120, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, 12, AUTO_SPEED),
            new FaceBall(driveTrain, camera, AUTO_SPEED),
            new DriveToBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, 110, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, 18, AUTO_SPEED),
            new ShootBall(shooter),
            new LoadBall(shooter),
            new ShootBall(shooter),
        };
        addCommands(commands);
    }
}