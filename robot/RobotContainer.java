// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.commands.TagDetectorCMD;
import frc.robot.subsystems.LEDsSubsystem;

public class RobotContainer {
  private final LimelightSubsystem m_limelight = new LimelightSubsystem();
  private final LEDsSubsystem m_LEDs = new LEDsSubsystem();
  private XboxController m_DriveController = new XboxController(Constants.OperatorInter.DriverController); 


  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(m_DriveController, XboxController.Button.kA.value).onTrue(new TagDetectorCMD(m_limelight, m_LEDs));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
