package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Shooter extends SubsystemBase {
    private CANSparkMax m_motor_flywheel = new CANSparkMax(PortMap.MOTOR_FLYWHEEL_ID, MotorType.kBrushless);

    public Shooter() {

    }

    public void setFlywheelSpeed(double speed) {
        m_motor_flywheel.set(speed);
    }

    public void loadBall() {
        // TODO load ball
    }

    public void shootBall() {
        // TODO shoot ball
    }
}
