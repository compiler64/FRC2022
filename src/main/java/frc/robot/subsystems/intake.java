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

    private DoubleSolenoid intakeLift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);

    //TODO build the intake code

    public intake() {

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

    public void setSpeed(double speed) {
        m_motor_intake.set(ControlMode.PercentOutput, speed);
    }
}
