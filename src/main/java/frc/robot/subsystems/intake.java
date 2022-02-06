package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Intake extends SubsystemBase {
    private TalonSRX m_motor_intake = new TalonSRX(PortMap.MOTOR_INTAKE_ID);

    // TODO motorType m_motor_belt = new Motor(PortMap.MOTOR_BELT_ID); // unsure on type

    private DoubleSolenoid intakeLift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);

    public Intake() {

    }
    
    public void setIntakeLift(String state) {
        if (state.equals("up")) {
            setIntakeLift(Value.kForward);
        } else if (state.equals("down")) {
            setIntakeLift(Value.kReverse);
        } else if (state.equals("off")) {
            setIntakeLift(Value.kOff);
        } else {
            throw new IllegalArgumentException("setIntakeLift argument must be"
                    + "\"up\", \"down\", or \"off\", but was " + state + ".");
        }
    }

    public void setIntakeLift(Value value) {
        intakeLift.set(value);
    }

    public void lower() {
        setIntakeLift("down");
    }

    public void raise() {
        setIntakeLift("up");
    }

    public void setIntakeSpeed(double intakeSpeed) {
        m_motor_intake.set(ControlMode.PercentOutput, intakeSpeed);
    }

    public void setBeltSpeed(double beltSpeed) {
        // TODO m_motor_belt.set(beltSpeed);
    }

    public void setSpeed(double intakeSpeed, double beltSpeed) {
        setIntakeSpeed(intakeSpeed);
        setBeltSpeed(beltSpeed);
    }
}
