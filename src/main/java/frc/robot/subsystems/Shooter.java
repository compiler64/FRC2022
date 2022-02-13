package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import  com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

/**
 * The subsystem that controls the flywheel and the indexing wheel.
 */
public class Shooter extends SubsystemBase {
    private CANSparkMax m_motorFlywheel = new CANSparkMax(PortMap.MOTOR_FLYWHEEL_ID, MotorType.kBrushless);
    private VictorSPX m_motorIndexingWheel = new VictorSPX(PortMap.MOTOR_INDEXING_WHEEL_ID);

    /**
     * Creates a new Shooter subsystem.
     */
    public Shooter() {

    }

    /**
     * Sets the speed of the flywheel.
     * @param speed the new flywheel speed
     */
    public void setFlywheelSpeed(double speed) {
        m_motorFlywheel.set(speed);
    }

    /**
     * Sets the speed of the indexing wheel.
     * @param speed the new indexing wheel speed
     */
    public void setIndexingWheelSpeed(double speed) {
        m_motorIndexingWheel.set(ControlMode.PercentOutput, speed);
    }
}
