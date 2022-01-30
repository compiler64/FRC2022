package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;

/**
 * A command to make the robot follow a ball of the alliance color.
 */
public class FollowBall extends CommandBase {
    private DriveTrain m_driveTrain;
    private Camera m_camera;
    private double m_speed;

    /**
     * Create a new FollowBall command.
     * @param driveTrain the drive train of the robot
     * @param camera the camera of the robot
     * @param speed a factor to multiply the speed by
     */
    public FollowBall(DriveTrain driveTrain, Camera camera, double speed) {
        m_driveTrain = driveTrain;
        m_camera = camera;
        m_speed = speed;
    }

    @Override
    public void execute() {
        System.out.println("Executing FollowBall");
        // get the x-coordinate of the ball
        double x = m_camera.getX();
        // calculate the speed of each side of the robot
        double leftSpeed = m_speed * (1 + Math.min(x, 0) / 20);  // if x < 0 then the left motor will be slower
        double rightSpeed = m_speed * (1 - Math.max(x, 0) / 20);  // if x > 0 then the right motor will be slower
        // set the motor speeds to leftSpeed and rightSpeed
        System.out.println("Left: " + leftSpeed + ", right: " + rightSpeed);
        m_driveTrain.setLeftMotors(leftSpeed);
        m_driveTrain.setRightMotors(rightSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrain.setBothMotors(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
