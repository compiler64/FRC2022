package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Shooter extends SubsystemBase {
    private TalonSRX m_motor_flywheel = new TalonSRX(PortMap.MOTOR_FLYWHEEL_ID);

    public Shooter() {

    }

    public void setFlywheelSpeed(double speed) {
        m_motor_flywheel.set(ControlMode.PercentOutput, speed);
    }
}
