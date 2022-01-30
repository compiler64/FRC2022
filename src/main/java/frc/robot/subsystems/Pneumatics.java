package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The subsystem that controls the pneumatics.
 */
public class Pneumatics extends SubsystemBase {
    Solenoid testSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

    /**
     * Create a new Pneumatics subsystem.
     */
    public Pneumatics() {
        
    }

    /**
     * Set the value of the solenoid.
     * @param port the port of the solenoid
     * @param value the value (true = on, false = off)
     */
    public void setSingle(int port, Boolean value) {
        testSolenoid.set(value);
        System.out.println("setting the solenoid");
    }
}