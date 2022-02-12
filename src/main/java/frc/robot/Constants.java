// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */


// 

public final class Constants {
    public static final double JOYSTICK_BUFFER = .1;
    public static final double MOTOR_POWER_FACTOR = 1;
    public static final double AUTO_SPEED = 0.3;
    public static final double AUTO_TIME = 15;
    public static final double AUTO_INTAKE_SPEED = 0.25; // TODO check this
    public static final double AUTO_BELT_SPEED = 0.25; //TODO check this
    public static final double AUTO_INTAKE_TIME = 1; // TODO check this
    public static final double AUTO_FLYWHEEL_SPEED = 1; // TODO check this
    public static final double BALL_LOAD_TIME = 1; // TODO check this
    public static final double BALL_SHOOT_TIME = 1; // TODO check this
    public static final double INDEXING_WHEEL_SPEED = 0.25; // TODO check this

    public static final boolean ENCODERS_READY = false;
    public static final double WHEEL_DIAMETER = 6.0 / 12.0; // TODO check this
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    // public static final double DISTANCE_PER_PULSE = WHEEL_CIRCUMFERENCE / 256.0;

    public static final double CLIMBER_EXTENDER_UPPER_LIMIT = 10; // TODO CHANGE THIS LATER!
    public static final double CLIMBER_EXTENDER_LOWER_LIMIT = 0; // TODO CHANGE THIS LATER!
    public static final double CLIMBER_ROTATOR_UPPER_LIMIT = 10; // TODO CHANGE THIS LATER!
    public static final double CLIMBER_ROTATOR_LOWER_LIMIT = 0; // TODO CHANGE THIS LATER!

    public static final int RED_PIPELINE = 0;
    public static final int BLUE_PIPELINE = 1;

    public static final double CAMERA_Y_OF_BALL_AT_INTAKE = -20;
    public static final double BAD_BALL_MAX_Y = 0;
}
