package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.intake;

/**
 * Runs the autonomous commands in order.
 */
@Deprecated
public class AutoCommand extends CommandBase {
    // private DriveTrain m_driveTrain;
    // private Gyro m_gyro;

    // private FaceBall faceBall1;
    // private DriveDistanceAuto distance1;
    // private TurnAngleAuto angle2;
    // private DriveDistanceAuto distance3;
    // private TurnAngleAuto angle4;

    private Command[] commands;
    private int commandNumber = 0;

    private Timer timer = new Timer();

    /**
     * Creates a new AutoCommand command.
     * @param driveTrain the drive train of the robot
     * @param gyro the gyro of the robot
     * @param pneumatics the pneumatics of the robot
     * @param camera the camera of the robot
     * @param intake the intake of the robot
     * @param shooter the shooter of the robot
     */
    public AutoCommand(DriveTrain driveTrain, Gyro gyro, Pneumatics pneumatics, Camera camera, intake intake, Shooter shooter) {
        commands = new Command[] {
            // the lower left ball
            new TurnAngleAuto(driveTrain, gyro, 20, AUTO_SPEED),
            new FaceBall(driveTrain, camera, AUTO_SPEED),
            new DriveToBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_INTAKE_TIME),
            new TurnToAngle(driveTrain, gyro, 180, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, 16.3, AUTO_SPEED),
            new TurnAngleAuto(driveTrain, gyro, 135, AUTO_SPEED),
            new TurnFlywheel(shooter, AUTO_INTAKE_SPEED),
            new DriveDistanceAuto(driveTrain, 3.7, AUTO_SPEED),
            new ShootBall(shooter),
            new LoadBall(shooter),
            new ShootBall(shooter),
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
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_INTAKE_TIME),
            new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, 16, AUTO_SPEED),
            new TurnAngleAuto(driveTrain, gyro, -135, AUTO_SPEED),
            new TurnFlywheel(shooter, AUTO_INTAKE_SPEED),
            new DriveDistanceAuto(driveTrain, 3.6, AUTO_SPEED),
            new LoadBall(shooter),
            new ShootBall(shooter),
            new TurnFlywheel(shooter, 0),
            /* 
            * if after that we go for the balls at the terminal
            */
            new DriveDistanceAuto(driveTrain, -1, AUTO_SPEED),
            new TurnAngleAuto(driveTrain, gyro, -128, AUTO_SPEED),
            new DriveDistanceAuto(driveTrain, -1, AUTO_SPEED),

        };
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
        if (commandNumber < commands.length) {
            commands[commandNumber].cancel();
        }
    }

    @Override
    public boolean isFinished() {
        // autonomous is finished when the command number is the length of the array
        return commandNumber >= commands.length || timer.get() >= AUTO_TIME;
    }
}
