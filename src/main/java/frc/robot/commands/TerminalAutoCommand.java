package frc.robot.commands;

import static frc.robot.Constants.*;

import frc.robot.subsystems.Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

public class TerminalAutoCommand  extends SequentialCommandGroup {
    public TerminalAutoCommand(DriveTrain driveTrain, Gyro gyro, Pneumatics pneumatics, Camera camera, Intake intake, Shooter shooter) {
        Command[] commands = {
            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
            new TurnFlywheel(shooter, 0),
            new TurnAngleAuto(driveTrain, gyro, 140, AUTO_SPEED),
            // new DriveDistanceAuto(driveTrain, 12, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, 6, AUTO_SPEED),
            new FaceBall(driveTrain, camera, AUTO_SPEED),
            new DriveToBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, 94, AUTO_SPEED),
            new EnableHighGear(pneumatics, true),
            new DriveDistanceAuto(driveTrain, 18, AUTO_SPEED), //top speed possible if not already enabled
            new EnableHighGear(pneumatics, false),
            new FaceBall(driveTrain, camera, AUTO_SPEED),
            new DriveToBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_BELT_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, 130, AUTO_SPEED),
            new EnableHighGear(pneumatics, true),
            new DriveDistanceAuto(driveTrain, 19.5, AUTO_SPEED), //top speed possible if not already enabled
            new EnableHighGear(pneumatics, false),
            new TurnAngleAuto(driveTrain, gyro, 132, AUTO_SPEED),
            new TurnFlywheel(shooter, AUTO_FLYWHEEL_SPEED),
            new DriveDistanceAuto(driveTrain, 2.4, AUTO_SPEED),
            new ShootBall(shooter, INDEXING_WHEEL_SPEED, BALL_SHOOT_TIME),
            new TurnFlywheel(shooter, 0),
            // TODO get out of the way
        };
        addCommands(commands);
    }
}
