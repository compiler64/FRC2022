package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.*;

import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.intake;

/**
 * Runs the autonomous commands in order.
 */
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
     * @param pneumatics the pneumatics subsystem of the robot
     * @param camera the camera of the robot
     * @param intake the intake of the robot
     */
    public AutoCommand(DriveTrain driveTrain, Gyro gyro, Pneumatics pneumatics, Camera camera, intake intake) {
        // m_driveTrain = driveTrain;
        // m_gyro = gyro;

        // faceBall1 = new FaceBall(driveTrain, camera, AUTO_SPEED);
        // if (ENCODERS_READY) distance1 = new DriveDistanceAuto(driveTrain, AUTO_DISTANCE_1, AUTO_SPEED);
        // angle2 = new TurnAngleAuto(driveTrain, gyro, AUTO_ANGLE_2, AUTO_SPEED);
        // if (ENCODERS_READY) distance3 = new DriveDistanceAuto(driveTrain, AUTO_DISTANCE_3, AUTO_SPEED);
        // angle4 = new TurnAngleAuto(driveTrain, gyro, AUTO_ANGLE_4, AUTO_SPEED);

        // if (ENCODERS_READY)
        //     commands = new Command[] {faceBall1, distance1,  angle2, distance3, angle4};
        // else
        //     commands = new Command[] {faceBall1, angle2, angle4};

        commands = new Command[] {
            // the lower left ball
            new TurnAngleAuto(driveTrain, gyro, 20, AUTO_SPEED),
            new FaceBall(driveTrain, camera, AUTO_SPEED),
            new DriveToBall(driveTrain, camera, AUTO_SPEED),
            new PickUpBall(intake, AUTO_INTAKE_SPEED, AUTO_INTAKE_TIME),
            new TurnToAngle(driveTrain, gyro, 180, AUTO_SPEED),
            // TODO new DriveDistanceAuto(driveTrain, 16.3 feet, AUTO_SPEED),
            new TurnAngleAuto(driveTrain, gyro, 135, AUTO_SPEED),
            new TurnFlywheel(intake, AUTO_INTAKE_SPEED),
            // TODO new DriveDistanceAuto(driveTrain, 3.7 feet, AUTO_SPEED),
            // TODO new ShootBall(),
            // TODO new LoadBall(),
            // TODO new ShootBall(),
            new TurnFlywheel(intake, 0),
            /* 
            * if we are going for the middle ball as well do this
            * later put in if statement
            */
            // TODO new DriveDistanceAuto(driveTrain, -1 foot, AUTO_SPEED),
            new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
            // TODO new DriveDistanceAuto(driveTrain, 2.6 feet, AUTO_SPEED),
            new TurnAngleAuto(driveTrain, gyro, 135, AUTO_SPEED),
            new FaceBall(driveTrain, camera, AUTO_SPEED),
            new DriveToBall(driveTrain, camera, AUTO_SPEED),
            // TODO new PickUpBall(),
            new TurnAngleAuto(driveTrain, gyro, 180, AUTO_SPEED),
            // TODO new DriveDistanceAuto(driveTrain, 16 feet, AUTO_SPEED),
            new TurnAngleAuto(driveTrain, gyro, -135, AUTO_SPEED),
            new TurnFlywheel(intake, AUTO_INTAKE_SPEED),
            // TODO new DriveDistanceAuto(driveTrain, 3.6 feet, AUTO_SPEED),
            // TODO new LoadBall(),
            // TODO new ShootBall(),
            new TurnFlywheel(intake, 0),
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
