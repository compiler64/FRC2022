package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class intake extends SubsystemBase {
    private TalonSRX m_motor_intake = new TalonSRX(PortMap.MOTOR_INTAKE_ID);
    private TalonSRX m_motor_flywheel = new TalonSRX(PortMap.MOTOR_FLYWHEEL_ID);

    //TODO build the intake code

    public intake() {

    }

    public void setIntakeSpeed(double speed) {
        m_motor_intake.set(ControlMode.PercentOutput, speed);
    }

    public void setFlywheelSpeed(double speed) {
        m_motor_flywheel.set(ControlMode.PercentOutput, speed);
    }
}
