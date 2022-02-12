package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

/**
 * Controls the climbing.
 */
public class Hanger extends SubsystemBase {
    private CANSparkMax extenderMotor = new CANSparkMax(PortMap.MOTOR_CLIMBER_EXTEND_ID, MotorType.kBrushless);
    private RelativeEncoder extenderEncoder;
    private CANSparkMax rotatorMotor = new CANSparkMax(PortMap.MOTOR_CLIMBER_ROTATE_ID, MotorType.kBrushless);
    private RelativeEncoder rotatorEncoder;

    /**
     * Creates a new Hanger subsystem.
     */
    public Hanger() {
        extenderEncoder = extenderMotor.getEncoder();
        rotatorEncoder = rotatorMotor.getEncoder();
    }

    /**
     * Sets the speed of the extender.
     * @param extenderSpeed the new extender speed
     */
    public void setExtenderSpeed(double extenderSpeed) {
        extenderMotor.set(extenderSpeed);
    }

    /**
     * Sets the speed of the rotator.
     * @param rotatorSpeed the new rotator speed
     */
    public void setRotatorSpeed(double rotatorSpeed) {
        rotatorMotor.set(rotatorSpeed);
    }

    /**
     * Gets the position of the extender.
     * @return the extender position
     */
    public double getExtenderPosition() {
        return extenderEncoder.getPosition();
    }

    /**
     * Gets the position of the rotator.
     * @return the rotator position
     */
    public double getRotatorPosition() {
        return rotatorEncoder.getPosition();
    }
}
