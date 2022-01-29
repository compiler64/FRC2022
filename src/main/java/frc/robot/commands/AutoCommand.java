package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

/**
 * Runs the autonomous commands in order.
 */
public class AutoCommand extends CommandBase {
    // private DriveTrain m_driveTrain;
    // private Gyro m_gyro;

    private DriveDistanceAuto distance1;
    private TurnAngleAuto angle2;
    private DriveDistanceAuto distance3;
    private TurnAngleAuto angle4;

    private Command[] commands;
    private int commandNumber = 0;

    private Timer timer = new Timer();

    public AutoCommand(DriveTrain driveTrain, Gyro gyro) {
        // m_driveTrain = driveTrain;
        // m_gyro = gyro;

        if (ENCODERS_READY) distance1 = new DriveDistanceAuto(driveTrain, AUTO_DISTANCE_1, AUTO_SPEED);
        angle2 = new TurnAngleAuto(driveTrain, gyro, AUTO_ANGLE_2, AUTO_SPEED);
        if (ENCODERS_READY) distance3 = new DriveDistanceAuto(driveTrain, AUTO_DISTANCE_3, AUTO_SPEED);
        angle4 = new TurnAngleAuto(driveTrain, gyro, AUTO_ANGLE_4, AUTO_SPEED);

        if (ENCODERS_READY)
            commands = new Command[] {distance1,  angle2, distance3, angle4};
        else
            commands = new Command[] {angle2, angle4};
    }

    @Override
    public void initialize() {
        commands[0].schedule();
        timer.reset();
    }

    @Override
    public void execute() {
        // if the current command is finished
        if (commandNumber < commands.length && commands[commandNumber].isFinished())
        {
            commands[commandNumber].cancel();
            // increment commandNumber
            commandNumber++;
            System.out.println("Command number: " + commandNumber);
            if (commandNumber < commands.length)
            {
                System.out.println("Scheduling command: " + commandNumber);
                // schedule the next command
                commands[commandNumber].schedule();
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        commands[commandNumber].cancel();
    }

    @Override
    public boolean isFinished() {
        // autonomous is finished when the command number is the length of the array
        return commandNumber >= commands.length || timer.get() >= AUTO_TIME;
    }
}
