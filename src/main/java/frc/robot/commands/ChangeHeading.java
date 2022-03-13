package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;

public class ChangeHeading extends CommandBase {
    private DriveTrain m_driveTrain;
    private Gyro m_gyro;
    private double m_angle;
    private DoubleSupplier m_angleSupplier;
    private double m_speed;
    // private double staticSpeed;
    private boolean m_breakAtEnd;
    private double speedBuffer;
    // private double turnRate;

    public ChangeHeading(DriveTrain driveTrain, Gyro gyro, double angle, double speed, boolean breakAtEnd) {
        m_driveTrain = driveTrain;
        m_angle = angle;
        m_speed = speed;
        m_gyro = gyro;
        // staticSpeed = speed;
        m_breakAtEnd = breakAtEnd;

        addRequirements(driveTrain);
    }

    public ChangeHeading(DriveTrain driveTrain, Gyro gyro, DoubleSupplier angleSupplier, double speed, boolean breakAtEnd) {
        m_driveTrain = driveTrain;
        m_angleSupplier = angleSupplier;
        m_speed = speed;
        m_gyro = gyro;
        // staticSpeed = speed;
        m_breakAtEnd = breakAtEnd;

        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        if (m_angleSupplier != null) {
            m_angle = m_angleSupplier.getAsDouble();
        }
        m_gyro.reset();
    }
    
    @Override
    public void execute() {

        speedBuffer = Math.abs(m_speed * 0.75);
        
        if (Math.abs(m_gyro.getAngle() - m_angle) < 10) {
            speedBuffer = Math.abs(m_speed * 0.45);
        }

        if (m_gyro.getAngle() > m_angle) {
            if (m_speed + speedBuffer > 1) {
                m_driveTrain.setRightMotors(m_speed);
                m_driveTrain.setLeftMotors(m_speed - speedBuffer);
            } else {
                m_driveTrain.setRightMotors(m_speed + speedBuffer);
                m_driveTrain.setLeftMotors(m_speed);
            }
            
        } else if (m_gyro.getAngle() < m_angle) {
            if (m_speed + speedBuffer > 1) {
                m_driveTrain.setLeftMotors(m_speed);
                m_driveTrain.setRightMotors(m_speed - speedBuffer);
            } else {
                m_driveTrain.setLeftMotors(m_speed + speedBuffer);
                m_driveTrain.setRightMotors(m_speed);
            }
        } else {
            m_driveTrain.setBothMotors(m_speed);
        }
    }

    @Override
    public boolean isFinished() {
        return (m_gyro.getAngle() <= (m_angle + 1)) && (m_gyro.getAngle() >= (m_angle - 1));
    }

    @Override
    public void end(boolean interrupted) {
        if (m_breakAtEnd) {
            m_driveTrain.setBothMotors(0);
        }
    }
}
