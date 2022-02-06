package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class intake extends SubsystemBase {
    private TalonSRX m_motor_intake = new TalonSRX(PortMap.MOTOR_INTAKE_ID);

    // motorType m_motor_belt = new Motor(PortMap.MOTOR_BELT_ID);

    private DoubleSolenoid intakeLift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);

    //TODO build the intake code

    public intake() {

    }
    public void setIntakeLift(String state) {
        if (state == "up") {
            intakeLift.set(Value.kForward);
        } else if (state == "down") {
            intakeLift.set(Value.kReverse);
        } else {
            intakeLift.set(Value.kOff);
        }
        
    }
    public void lower() {
        setIntakeLift("down");
    }

    public void raise() {
        setIntakeLift("up");
    }

    public void setSpeed(double intakeSpeed, double beltSpeed) {
        m_motor_intake.set(ControlMode.PercentOutput, intakeSpeed);
        // m_motor_belt.set(beltSpeed);
    }
}
