package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

public class AutoCommand extends CommandBase {
    // private DriveTrain m_driveTrain;
    // private Gyro m_gyro;

    private DriveDistanceAuto distance1;
    private TurnAngleAuto angle2;
    private DriveDistanceAuto distance3;

    private Command[] commands;

    private int commandNumber = 0;

    public AutoCommand(DriveTrain driveTrain, Gyro gyro) {
        // m_driveTrain = driveTrain;
        // m_gyro = gyro;

        distance1 = new DriveDistanceAuto(driveTrain, Constants.AUTO_DISTANCE_1, Constants.AUTO_SPEED);
        angle2 = new TurnAngleAuto(driveTrain, gyro, Constants.AUTO_ANGLE_2, Constants.AUTO_SPEED);
        distance3 = new DriveDistanceAuto(driveTrain, Constants.AUTO_DISTANCE_3, Constants.AUTO_SPEED);

        commands = new Command[] {distance1,angle2, distance3};
    }

    @Override
    public void initialize() {
        commands[0].schedule();
    }

    @Override
    public void execute() {
        // if the current command is finished
        if (commandNumber < commands.length && commands[commandNumber].isFinished())
        {
            // increment commandNumber
            commandNumber++;
            if (commandNumber < commands.length)
            {
                // schedule the next command
                commands[commandNumber].schedule();
            }
        }
    }

    @Override
    public boolean isFinished() {
        // autonomous is finished when the command number is the length of the array
        return commandNumber >= commands.length;
    }
}
