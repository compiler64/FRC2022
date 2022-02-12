package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

/**
 * Controls the climbing.
 */
public class Hanger extends SubsystemBase {
    private CANSparkMax extenderMotor = new CANSparkMax(PortMap.MOTOR_CLIMBER_EXTEND_ID, MotorType.kBrushless);
    private RelativeEncoder extenderEncoder;
    // private CANSparkMax rotatorMotor = new CANSparkMax(PortMap.MOTOR_CLIMBER_ROTATE_ID, MotorType.kBrushless);
    // private RelativeEncoder rotatorEncoder;

    // TODO uncomment pneumatics
    private DoubleSolenoid rotatorSolenoid = null; // new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 3, 4);

    /**
     * Creates a new Hanger subsystem.
     */
    public Hanger() {
        extenderEncoder = extenderMotor.getEncoder();
        // rotatorEncoder = rotatorMotor.getEncoder();
    }

    /**
     * Sets the speed of the extender.
     * @param extenderSpeed the new extender speed
     */
    public void setExtenderSpeed(double extenderSpeed) {
        extenderMotor.set(extenderSpeed);
    }

    // /**
    //  * Sets the speed of the rotator.
    //  * @param rotatorSpeed the new rotator speed
    //  */
    // public void setRotatorSpeed(double rotatorSpeed) {
    //     rotatorMotor.set(rotatorSpeed);
    // }

    /**
     * Sets the position of the rotator.
     * @param value the new value
     */
    public void setRotatorValue(Value value) {
        rotatorSolenoid.set(value);
    }

    /**
     * Gets the position of the extender.
     * @return the extender position
     */
    public double getExtenderPosition() {
        return extenderEncoder.getPosition();
    }

    /**
     * Gets the value of the rotator.
     * @return the rotator value
     */
    public Value getRotatorValue() {
        return rotatorSolenoid.get();
    }
}
