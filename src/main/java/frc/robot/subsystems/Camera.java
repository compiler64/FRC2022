package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import static frc.robot.Constants.*;

/**
 * The subsystem that gets data from the camera.
 */
public class Camera extends SubsystemBase {
    private NetworkTable table;
    private NetworkTableEntry tv;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry pipeline;
    private NetworkTableEntry ledMode;
    private boolean validTarget;
    private double x;
    private double y;
    private double area;
    private double mode;

    /**
     * Creates a new Camera subsystem.
     */
    public Camera() {
        //post to shuffleboard periodically
        table = NetworkTableInstance.getDefault().getTable("limelight");
        Shuffleboard.getTab("main").addBoolean("Limelight Valid Target", () -> validTarget);
        Shuffleboard.getTab("main").addNumber("Limelight X", () -> x);
        Shuffleboard.getTab("main").addNumber("Limelight Y", () -> y);
        Shuffleboard.getTab("main").addNumber("Limelight Area", () -> area);
        Shuffleboard.getTab("main").addNumber("Pipeline Number", () -> mode);
        setPipeline();
        ledMode = table.getEntry("ledMode");
        setLED(false);
    }

    /**
     * Turn the camera LED on or off.
     * @param on {@literal true} if the LED should be on, {@literal false} otherwise
     */
    public void setLED(boolean on) {
        ledMode.setNumber(on ? 3 : 1);
    }
    
    /**
     * Update the camera info fields.
     */
    public void display() {
        // get the entries periodically
        tv = table.getEntry("tv");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        ledMode = table.getEntry("ledMode");
        pipeline = table.getEntry("getpipe");

        //read values periodically
        validTarget = tv.getDouble(0.0) == 1;  // true if value=1, false if value=0
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
        mode = pipeline.getDouble(0);
    }

    /**
     * Sets the pipeline to 0 if the current alliance is red, 1 if the current alliance is blue.
     */
    public void setPipeline() {
        // set the pipeline to the alliance pipeline
        setPipelineTo(getAlliancePipeline());
    }

    /**
     * Sets the pipeline to number.
     * @param number the new pipeline number
     */
    public void setPipelineTo(int number) {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(number);
    }

    /**
     * Returns true when the camera is detecting a ball of the right color.
     * @return if the camera has a valid target
     */
    public boolean hasValidTarget() {
        return validTarget;
    }

    /**
     * Gets the x-coordinate of the ball.
     * @return the x-coordinate of the ball
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the ball.
     * @return the y-coordinate of the ball
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the area of the target.
     * @return the area of the target
     */
    public double getArea() {
        return area;
    }

    public static int getOppositePipeline(int pipeline) {
        return 1 - pipeline;
    }

    public static int getAlliancePipeline() {
        // set number to BLUE_PIPELINE if the alliance is blue, else RED_PIPELINE
        return (DriverStation.getAlliance() == Alliance.Blue) ? BLUE_PIPELINE : RED_PIPELINE;
    }

    public static int getNonAlliancePipeline() {
        return getOppositePipeline(getAlliancePipeline());
    }
}
