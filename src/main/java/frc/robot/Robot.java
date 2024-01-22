// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  TalonFX m1 = new TalonFX(1, "*");
  TalonFX m2 = new TalonFX(2, "*");

  Joystick j = new Joystick(0);

  VelocityTorqueCurrentFOC out = new VelocityTorqueCurrentFOC(0);

  double velocityTarget = 50;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    TalonFXConfiguration cfg = new TalonFXConfiguration();
    cfg.Slot0.kP = 4;
    cfg.Slot0.kS = 10;

    m1.getConfigurator().apply(cfg);
    m2.getConfigurator().apply(cfg);
  }

  @Override
  public void robotPeriodic() {
    m1.setControl(out.withVelocity(velocityTarget));
    m2.setControl(out.withVelocity(-velocityTarget*2));

    if(j.getRawButtonPressed(1)) {
      velocityTarget += 1;
    }
    if(j.getRawButtonPressed(2)) {
      velocityTarget -= 1;
    }
  }
}
