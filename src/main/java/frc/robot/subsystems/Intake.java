package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

/**
 * Controls the intake, the intake lift, and the conveyor belt.
 */
public class Intake extends SubsystemBase {
    private TalonSRX m_motor_intake = new TalonSRX(PortMap.MOTOR_INTAKE_ID);

    VictorSPX m_motor_belt = new VictorSPX(PortMap.MOTOR_BELT_ID);

    
    private DoubleSolenoid intakeLift =  new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);

    /**
     * Creates a new Intake subsystem.
     */
    public Intake() {

    }
    
    /**
     * Sets the state of the intake lift.
     * @param state one of {@literal "up"}, {@literal "down"}, or {@literal "off"}.
     */
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

    /**
     * Sets the state of the intake lift.
     * @param value the new {@link Value} of the lift
     */
    public void setIntakeLift(Value value) {
        intakeLift.set(value);
    }

    /**
     * Lowers the intake lift.
     */
    public void lower() {
        setIntakeLift("down");
    }

    /**
     * Raises the intake lift.
     */
    public void raise() {
        setIntakeLift("up");
    }

    /**
     * Sets the speed of the intake.
     * @param intakeSpeed the new intake speed
     */
    public void setIntakeSpeed(double intakeSpeed) {
        m_motor_intake.set(ControlMode.PercentOutput, intakeSpeed);
    }

    /**
     * Sets the speed of the conveyor belt.
     * @param beltSpeed the new belt speed
     */
    public void setBeltSpeed(double beltSpeed) {
        m_motor_belt.set(ControlMode.PercentOutput, beltSpeed);
    }

    /**
     * Sets the speed of both the intake and the conveyor belt.
     * @param intakeSpeed the new intake speed
     * @param beltSpeed the new belt speed
     */
    public void setSpeed(double intakeSpeed, double beltSpeed) {
        setIntakeSpeed(intakeSpeed);
        setBeltSpeed(beltSpeed);
    }
}
