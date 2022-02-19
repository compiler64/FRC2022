package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

/**
 * Controls the climbing.
 */
public class Hanger extends SubsystemBase {
    private CANSparkMax extenderMotor1 = new CANSparkMax(PortMap.MOTOR_CLIMBER_EXTEND_ID_1, MotorType.kBrushless);
    private CANSparkMax extenderMotor2 = new CANSparkMax(PortMap.MOTOR_CLIMBER_EXTEND_ID_2, MotorType.kBrushless);
    private RelativeEncoder extenderEncoder;
    
    
    private DoubleSolenoid rotatorSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 3, 4);

    /**
     * Creates a new Hanger subsystem.
     */
    public Hanger() {
        extenderEncoder = extenderMotor1.getEncoder();
        extenderEncoder.setInverted(false);
        Shuffleboard.getTab("main").addNumber("extender height", () -> ((extenderEncoder.getPosition() / 5.3333) * (.005 * Math.PI)));
    }

    /**
     * Sets the speed of the extender.
     * @param extenderSpeed the new extender speed
     */
    public void setExtenderSpeed(double extenderSpeed) {
        extenderMotor1.set(extenderSpeed);
        extenderMotor2.set(extenderSpeed);
    }

    
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
