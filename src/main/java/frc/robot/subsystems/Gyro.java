package frc.robot.subsystems;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Controls the navx gyro
 */
public class Gyro extends SubsystemBase {
    AHRS gyroscope = new AHRS(SPI.Port.kMXP);

    /**
     * Creates a new Gyro subsystem.
     */
    public Gyro() {
        super();
        reset();
        // Shuffleboard.getTab("main").add(gyroscope);
    }

    /**
     * Resets the gyro.
     */
    public void reset() {
        gyroscope.reset();
    }

    /**
     * Gets the angle of the gyro.
     * @return the angle of the gyro
     */
    public double getAngle() {
        return gyroscope.getAngle();
    }
}