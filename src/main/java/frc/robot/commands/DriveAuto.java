package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

public class DriveAuto extends CommandBase{
    public void turnTo(float degrees) {
        double error = degrees - Gyro.getAngle();
    }
}
