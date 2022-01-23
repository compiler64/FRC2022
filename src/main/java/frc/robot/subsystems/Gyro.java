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

    public Gyro() {
        Shuffleboard.getTab("main").add(gyroscope);
    }
}