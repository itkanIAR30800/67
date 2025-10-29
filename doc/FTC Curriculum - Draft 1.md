### **FTC Robotics Curriculum Outline**

Notes (Mostafa): 

I used Gemini to generate this, but I did review a lot of it. It still would need some review especially around questions like:

* Does this Task/Resource/Hardware needed combination make sense?  
  * Better resources? (Especially CAD)  
  * Is that allotted time actually enough for this task?  
  * This Task can be replaced, refined, etc.

It references the Alan Smith PDF in the same folder. Especially the Software part.

---

This curriculum is designed to be a guided, task-based program for First Tech Challenge (FTC) robotics. The goal is to provide students with a series of challenges that encourage independent problem-solving and collaboration, rather than direct instruction.

---

### **Software**

**Resource:** [Learn Java for FTC (PDF)](https://drive.google.com/file/d/1ENTNJpHcZsK_7MkBGg-ox33cuMM4aFeH/view?usp=drive_link), along with other provided links.

#### **Software.Course1: Introduction to Java for FTC (10 Sessions)**

**Goal:** To establish a strong foundation in Java programming concepts and the basic structure of an FTC robot program.

* **Software.Course1.Session1 (Est. 2 hours):** Introduction to the Development Environment and Hardware  
  * **Task 1:** Getting Started  
    * **Goal:** Successfully set up the development environment.  
    * **Time Estimate:** 30 minutes  
    * **Hardware Needed:** Laptop or desktop computer.  
    * **Resource:** [Learn Java for FTC - Introduction](LearnJavaForFTC.html#ch1) and [Now you try](LearnJavaForFTC.html#ch1-3).  
  * **Task 2:** Understand the Driver Station  
    * **Goal:** Learn how the Driver Station (Android phone) interacts with the Control Hub and displays telemetry.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone with the app installed.  
    * **Resource:** [Learn Java for FTC - Driver Station](LearnJavaForFTC.html#ch1-1-3) and [Hello, World](LearnJavaForFTC.html#ch1-2-4). (Resource: [FTC-docs](https://ftc-docs.firstinspires.org/en/latest/control_hard_compon/ds_components/index.html))  
  * **Task 3:** Variables and Data  
    * **Goal:** Create variables for motor power and sensor readings, and display their values on the Driver Station.  
    * **Time Estimate:** 30 minutes  
    * **Hardware Needed:** Driver Station phone, Control Hub.  
    * **Resource:** [Learn Java for FTC - Variables and Data Types](LearnJavaForFTC.html#ch2).  
* **Software.Course1.Session2 (Est. 2.5 hours):** Basic Programming Concepts  
  * **Task 1:** Perform calculations  
    * **Goal:** Write code to scale a sensor reading to a desired motor power value.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Control Hub.  
    * **Resource:** [Learn Java for FTC - Basic Math](LearnJavaForFTC.html#ch3-1).  
  * **Task 2:** Basic Control Flow  
    * **Goal:** Use an `if/else` statement to change the robot's behavior based on a condition, such as a gamepad button press.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Driver Station phone, Gamepad.  
    * **Resource:** [Learn Java for FTC - Making decisions](LearnJavaForFTC.html#ch4).  
* **Software.Course1.Session3 (Est. 2.5 hours):** Gamepad Control  
  * **Task 1:** Create a loop  
    * **Goal:** Use a `while` loop to continuously read and update sensor values or motor power while the OpMode is running.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Control Hub.  
    * **Resource:** [Learn Java for FTC - While](LearnJavaForFTC.html#ch4-4).  
  * **Task 2:** Gamepad and Tele-Op  
    * **Goal:** Write a program that reads input from the gamepad's joysticks and buttons and displays the values on the telemetry.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Driver Station phone, Gamepad.  
    * **Resource:** [Learn Java for FTC - Gamepad and basic math](LearnJavaForFTC.html#ch3).  
* **Software.Course1.Session4 (Est. 1.5 hours):** Controlling a Single Motor  
  * **Task 1:** Control a single motor  
    * **Goal:** Use gamepad input to control the speed and direction of one motor.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Driver Station phone, Gamepad, Control Hub, one motor.  
    * **Resource:** [Learn Java for FTC - Motors](LearnJavaForFTC.html#ch7).  
* **Software.Course1.Session5 (Est. 1.5 hours):** Motors and Hardware Mapping  
  * **Task 1:** Motors and Hardware Mapping  
    * **Goal:** Create a `HardwareMap` to correctly identify and access your robot's motors, servos, and sensors.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Driver Station phone, Control Hub.  
    * **Resource:** [Learn Java for FTC - Configuration file](LearnJavaForFTC.html#ch6-1) and [HardwareMap](LearnJavaForFTC.html#ch6-2).  
* **Software.Course1.Session6 (Est. 3 hours):** Drivetrain and `LinearOpMode`  
  * **Task 1:** Control a four-motor drivetrain  
    * **Goal:** Use gamepad input to control the speed and direction of a standard four-motor tank or arcade drive.  
    * **Time Estimate:** 2 hours  
    * **Hardware Needed:** Driver Station phone, Gamepad, Control Hub, four motors.  
    * **Resource:** [Learn Java for FTC - 4 motor mecanum drive](LearnJavaForFTC.html#ch20-2).  
  * **Task 2:** Driving Basics and LinearOpMode  
    * **Goal:** Write a program that uses motor encoders to drive the robot a specific distance.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Gamepad, Control Hub, four motors with encoders.  
    * **Resource:** [Learn Java for FTC - Motor as Sensor](LearnJavaForFTC.html#ch7-4).  
* **Software.Course1.Session7 (Est. 2.5 hours):** Subsystems and Servos  
  * **Task 1:** Understand `LinearOpMode`  
    * **Goal:** Convert a basic OpMode into a `LinearOpMode` and understand the differences in structure and flow.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Control Hub.  
    * **Resource:** [Learn Java for FTC - Linear OpMode](LearnJavaForFTC.html#appendix-b).  
  * **Task 2:** Introduction to Subsystems  
    * **Goal:** Write a class that abstracts the control of a simple mechanism (e.g., a single servo claw) and its associated motors or servos.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Control Hub.  
    * **Resource:** [Learn Java for FTC - Class Members and Methods](LearnJavaForFTC.html#ch5) and [Creating your own classes](LearnJavaForFTC.html#ch5-4).  
  * **Task 3:** Servos and Sensors  
    * **Goal:** Write an OpMode that uses a gamepad button to move a servo between two predefined positions.  
    * **Time Estimate:** 30 minutes  
    * **Hardware Needed:** Driver Station phone, Gamepad, Control Hub, one servo.  
    * **Resource:** [Learn Java for FTC - Servos](LearnJavaForFTC.html#ch8).  
* **Software.Course1.Session8 (Est. 2.5 hours):** Integrating and Testing  
  * **Task 1:** Integrate your subsystem  
    * **Goal:** Add the subsystem you created into your main tele-op program and control it with the gamepad.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Driver Station phone, Gamepad, Control Hub, at least one motor and servo.  
    * **Resource:** [Learn Java for FTC - Mechanisms](LearnJavaForFTC.html#ch6-2).  
  * **Task 2:** Read a sensor  
    * **Goal:** Use a color, distance, or touch sensor to make a decision in your code (e.g., stop the robot when it is a certain distance from a wall).  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Control Hub, a sensor (color, distance, or touch).  
    * **Resource:** [Learn Java for FTC - Analog Sensors](LearnJavaForFTC.html#ch9) and [Color and Distance Sensors](LearnJavaForFTC.html#ch10).  
* **Software.Course1.Session9 (Est. 2.5 hours):** Autonomous Fundamentals  
  * **Task 1:** Autonomous Fundamentals  
    * **Goal:** Write a simple autonomous program that uses `getRuntime()` to move the robot forward for a set amount of time.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Driver Station phone, Control Hub, motors.  
    * **Resource:** [Learn Java for FTC - It's all relative](LearnJavaForFTC.html#ch12-3).  
  * **Task 2:** Use a basic state machine  
    * **Goal:** Create a simple state machine to control a sequence of autonomous actions (e.g., drive, stop, turn).  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Control Hub, motors.  
    * **Resource:** [Learn Java for FTC - Dealing with State](LearnJavaForFTC.html#ch12) and [Autonomous state](LearnJavaForFTC.html#ch12-2).  
* **Software.Course1.Session10 (Est. 2 hours):** Code Review and Debugging  
  * **Task 1:** Code Review and Debugging  
    * **Goal:** Read through your code and refactor it for clarity, adding comments and organizing methods.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer.  
    * **Resource:** [Learn Java for FTC - Comments](LearnJavaForFTC.html#ch1-4) and [Javadoc](LearnJavaForFTC.html#ch17).  
  * **Task 2:** Debug a bug  
    * **Goal:** Intentionally introduce an error into your code and use the debugger to find and fix it.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer, Driver Station phone, Control Hub.  
    * **Resource:** [Learn Java for FTC - Gotchas](LearnJavaForFTC.html#ch1-6).  #### **Software.Course2: Advanced Robotics Programming (10 Sessions)**

**Goal:** To teach advanced programming techniques, including motion control, state machines, and external libraries.

* **Software.Course2.Session1 (Est. 3 hours):** Mecanum Drive  
  * **Task 1:** Introduction to Mecanum Drive  
    * **Goal:** Explain how Mecanum wheels work and how to calculate motor powers for field-centric or robot-centric movement.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer, whiteboard/paper for demonstration.  
    * **Resource:** [Learn Java for FTC - 4 motor mecanum drive](LearnJavaForFTC.html#ch20-2) and [Holonomic Drive](LearnJavaForFTC.html#ch20-2). (Resource: GM0.org's `Mecanum Drive Tutorial` ([https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html](https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html)))  
  * **Task 2:** Program a Mecanum drivetrain  
    * **Goal:** Write a tele-op program for a Mecanum robot that allows for strafing and rotation.  
    * **Time Estimate:** 2 hours  
    * **Hardware Needed:** Driver Station phone, Gamepad, Control Hub, four mecanum wheels with motors.  
    * **Resource:** [Learn Java for FTC - Mecanum Mechanism](LearnJavaForFTC.html#ch20-2-1).  
* **Software.Course2.Session2 (Est. 3 hours):** Road Runner Basics  
  * **Task 1:** Road Runner Basics  
    * **Goal:** Integrate the Road Runner library into your FTC project and understand its basic structure for autonomous paths.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a stable internet connection.  
    * **Resource:** The `Road Runner Docs` ([https://rr.brott.dev/docs/](https://rr.brott.dev/docs/)).  
  * **Task 2:** Drive a simple path  
    * **Goal:** Use a `TrajectorySequence` to program the robot to drive in a straight line or a simple curve.  
    * **Time Estimate:** 2 hours  
    * **Hardware Needed:** Driver Station phone, Control Hub, motors with encoders.  
    * **Resource:** The `Road Runner Docs` ([https://rr.brott.dev/docs/](https://rr.brott.dev/docs/)).  
* **Software.Course2.Session3 (Est. 3 hours):** MeepMeep and Advanced Trajectories  
  * **Task 1:** MeepMeep Visualization  
    * **Goal:** Use the MeepMeep tool to visually create a complex autonomous path that includes turns and curves.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** The `MeepMeep` section on the Road Runner website ([https://learnroadrunner.com/tool/meepmeep.html](https://learnroadrunner.com/tool/meepmeep.html)).  
  * **Task 2:** Transfer the trajectory  
    * **Goal:** Convert the code from MeepMeep into your robot's Road Runner program.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer.  
    * **Resource:** `Road Runner Docs` ([https://rr.brott.dev/docs/](https://rr.brott.dev/docs/)).  
* **Software.Course2.Session4 (Est. 3 hours):** Pathing and State Machines  
  * **Task 1:** Advanced Trajectories  
    * **Goal:** Create an autonomous path that uses splines for smooth curves and precise turns to avoid obstacles.  
    * **Time Estimate:** 2 hours  
    * **Hardware Needed:** Driver Station phone, Control Hub, motors with encoders.  
    * **Resource:** The `Road Runner Docs` ([https://rr.brott.dev/docs/](https://rr.brott.dev/docs/)).  
  * **Task 2:** Follow a sequence of actions  
    * **Goal:** Program the robot to follow a sequence of different trajectories to reach multiple waypoints on the field.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Control Hub, motors with encoders.  
    * **Resource:** The `Road Runner Docs` ([https://rr.brott.dev/docs/](https://rr.brott.dev/docs/)).  
* **Software.Course2.Session5 (Est. 2.5 hours):** Advanced State Machines and Subsystems  
  * **Task 1:** Advanced State Machines  
    * **Goal:** Create a more robust state machine for a multi-step autonomous program that includes sensor feedback to transition between states.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Driver Station phone, Control Hub, motors, sensors.  
    * **Resource:** [Learn Java for FTC - Dealing with State](LearnJavaForFTC.html#ch12) and [Using the switch statement](LearnJavaForFTC.html#ch12-2-1).  
  * **Task 2:** Integrate subsystems with the state machine  
    * **Goal:** Control a lift or intake subsystem from within your autonomous state machine.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Control Hub, motors, and servos for the subsystem.  
    * **Resource:** [Learn Java for FTC - Mechanisms](LearnJavaForFTC.html#ch6-2).  
* **Software.Course2.Session6 (Est. 3 hours):** Advanced Sensors and Control  
  * **Task 1:** Advanced Sensors  
    * **Goal:** Use the robot's IMU to get its heading and use that data to achieve accurate turns.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Driver Station phone, Control Hub with IMU.  
    * **Resource:** [Learn Java for FTC - Gyro (IMU)](LearnJavaForFTC.html#ch11) and [IMU](LearnJavaForFTC.html#ch11-2).  
  * **Task 2:** Implement color detection  
    * **Goal:** Use a color sensor to detect a game element and make a decision based on its color.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Driver Station phone, Control Hub, a color sensor.  
    * **Resource:** [Learn Java for FTC - Color and Distance Sensors](LearnJavaForFTC.html#ch10).  
* **Software.Course2.Session7 (Est. 3 hours):** Control Theory  
  * **Task 1:** Control Theory Fundamentals  
    * **Goal:** Explain the basics of PID control and tune the `P` and `D` gains for a motor to get it to hold a position accurately.  
    * **Time Estimate:** 2 hours  
    * **Hardware Needed:** Driver Station phone, Control Hub, motor with an encoder.  
    * **Resource:** Learn Java for FTC - PID Control (not in current PDF).  
  * **Task 2:** Implement a simple feedback loop  
    * **Goal:** Use a sensor (e.g., an encoder) to create a feedback loop that corrects for motor slippage.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Driver Station phone, Control Hub, motor with an encoder.  
    * **Resource:** [Learn Java for FTC - Motor as Sensor](LearnJavaForFTC.html#ch7-4).  
* **Software.Course2.Session8 (Est. 3 hours):** Vision Processing  
  * **Task 1:** Vision Processing  
    * **Goal:** Explain the basics of computer vision and how it can be used in FTC.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer, webcam or phone with camera.  
    * **Resource:** [Learn Java for FTC - Computer Vision](LearnJavaForFTC.html#ch16) and [OpenCV](LearnJavaForFTC.html#ch16).  
  * **Task 2:** Use a vision library  
    * **Goal:** Implement a simple vision pipeline using a library like EasyOpenCV to detect the location of an object on the field.  
    * **Time Estimate:** 2 hours  
    * **Hardware Needed:** Driver Station phone with camera, Control Hub.  
    * **Resource:** [Learn Java for FTC - April Tags](LearnJavaForFTC.html#ch16-1) and [Actual computer vision](LearnJavaForFTC.html#ch16-5).  
* **Software.Course2.Session9 (Est. 2.5 hours):** Object-Oriented Design  
  * **Task 1:** Code Refactoring and Object-Oriented Design  
    * **Goal:** Refactor your `TeleOp` and `Autonomous` programs to use separate classes for each subsystem (drivetrain, lift, intake).  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer.  
    * **Resource:** [Learn Java for FTC - Class Members and Methods](LearnJavaForFTC.html#ch5) and [Creating your own classes](LearnJavaForFTC.html#ch5-4).  
  * **Task 2:** Use inheritance  
    * **Goal:** Create a base `OpMode` class to share common methods and variables across your tele-op and autonomous programs.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer.  
    * **Resource:** [Learn Java for FTC - Inheritance](LearnJavaForFTC.html#ch14).  
* **Software.Course2.Session10 (Est. 2.5 hours):** Git and Version Control  
  * **Task 1:** Git and Version Control  
    * **Goal:** Initialize a Git repository for your team's code and make your first commit.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer.  
    * **Resource:** [A simple guide to Git for beginners](https://www.google.com/search?q=https://www.youtube.com/watch%3Fv%3DR9Z2LgN8Q90).  
  * **Task 2:** Collaborate with Git  
    * **Goal:** Push your code to a shared repository and pull in changes from another teammate.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer.  
    * **Resource:** [Git & GitHub Crash Course For Beginners](https://www.google.com/search?q=https://www.youtube.com/watch%3Fv%3DFqSj5i0N4gE).

---

### **CAD**

**Resource:** The provided Onshape links.

#### **CAD.Course1: Introduction to Onshape (10 Sessions)**

**Goal:** To become proficient with the Onshape interface and the fundamental principles of CAD for robotics.

* **CAD.Course1.Session1 (Est. 1 hour):** Onshape Basics and Interface  
  * **Task 1:** Onshape Basics  
    * **Goal:** Successfully set up a free Onshape account and navigate the dashboard.  
    * **Time Estimate:** 30 minutes  
    * **Hardware Needed:** Computer with a web browser.  
  * **Task 2:** Learn the interface  
    * **Goal:** Create a new document, learn to navigate the 3D space, and understand the basic tools like Sketch, Extrude, and Revolve.  
    * **Time Estimate:** 30 minutes  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Learning Path: `Introduction to Onshape` ([https://learn.onshape.com/courses/intro-to-onshape](https://learn.onshape.com/courses/intro-to-onshape)) and `Intro to Onshape` ([https://learn.onshape.com/learn/article/intro-to-onshape](https://learn.onshape.com/learn/article/intro-to-onshape))  
* **CAD.Course1.Session2 (Est. 2 hours):** Basic Part Modeling  
  * **Task 1:** Basic Part Modeling  
    * **Goal:** Use sketch tools to create a simple shape like a square or a circle with specific dimensions.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Learning Path: `Introduction to CAD` ([https://learn.onshape.com/learning-paths/introduction-to-cad](https://learn.onshape.com/learning-paths/introduction-to-cad))  
  * **Task 2:** Turn your sketch into a 3D part  
    * **Goal:** Use the **Extrude** feature to give depth to your 2D sketch, creating a simple 3D part.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Learning Path: `Introduction to CAD` ([https://learn.onshape.com/learning-paths/introduction-to-cad](https://learn.onshape.com/learning-paths/introduction-to-cad))  
* **CAD.Course1.Session3 (Est. 2 hours):** Complex Parts and Features  
  * **Task 1:** More Complex Parts  
    * **Goal:** Create a part that requires multiple sketches and extrusions on different planes.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [Learning the basics of working with multiple sketches in Onshape CAD](https://www.youtube.com/watch?v=mJXmWGeyud8)  
  * **Task 2:** Use the `Hole` and `Fillet` features  
    * **Goal:** Add holes and rounded edges to your part for a more finished look.  
    * **Time Estimate:** 30 minutes  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [Onshape Hole Feature Help](https://cad.onshape.com/help/Content/hole.htm) and [Onshape Fillet Feature Help](https://cad.onshape.com/help/Content/fillet.htm).  
* **CAD.Course1.Session4 (Est. 2.5 hours):** Introduction to Assemblies  
  * **Task 1:** First Assembly  
    * **Goal:** Use the `Insert` feature to add standard FTC components from the Onshape FTC library into a new assembly.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape `FTC Catalog` ([https://learn.onshape.com/catalog?query=ftc](https://learn.onshape.com/catalog?query=ftc))  
  * **Task 2:** Use `Mate` connectors  
    * **Goal:** Use `Mate` features like "Fasten" and "Revolute" to connect two parts together.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Article: `Assemble a Robot` ([https://learn.onshape.com/learn/article/assemble-a-robot](https://learn.onshape.com/learn/article/assemble-a-robot))  
* **CAD.Course1.Session5 (Est. 2.5 hours):** Mates and Simulation  
  * **Task 1:** Understanding Mates  
    * **Goal:** Experiment with different mate types (`Revolute`, `Slider`, `Cylindrical`) to understand how they restrict movement.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Article: `Assemble a Robot` ([https://learn.onshape.com/learn/article/assemble-a-robot](https://learn.onshape.com/learn/article/assemble-a-robot))  
  * **Task 2:** Simulate a simple mechanism  
    * **Goal:** Create an assembly of two parts that can move relative to each other using a `Mate` connector.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [Onshape Assembly Simulation Help](https://cad.onshape.com/help/Content/simulation.htm).  
* **CAD.Course1.Session6 (Est. 3 hours):** Drivetrain CAD \- Chassis  
  * **Task 1:** Drivetrain CAD \- Chassis  
    * **Goal:** Create a new Part Studio and design the main frame of a four-wheeled drivetrain.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [Onshape for Robotics \- Design of a Robot Chassis](https://www.onshape.com/en/solutions/robotics) and [Onshape Robot Tutorial for Absolute Beginners](https://www.reddit.com/r/Onshape/comments/1m27m72/onshape_robot_tutorial_for_absolute_beginners/).  
  * **Task 2:** Assemble the chassis  
    * **Goal:** Assemble the chassis components you designed and make sure they are properly mated.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [Onshape Strafer Chassis Tutorial](https://www.youtube.com/watch?v=KdytrbNVKnE) and [CAD for Robotics \- Assemble a Drivetrain](https://learn.onshape.com/courses/assemble-a-robot).  
* **CAD.Course1.Session7 (Est. 3 hours):** Drivetrain CAD \- Motors and Wheels  
  * **Task 1:** Drivetrain CAD \- Motors and Wheels  
    * **Goal:** Add motors from the FTC Onshape library and mate them correctly to your chassis.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [Onshape Strafer Chassis Tutorial](https://www.youtube.com/watch?v=KdytrbNVKnE).  
  * **Task 2:** Add wheels  
    * **Goal:** Add standard FTC wheels and mate them to the motor output shafts.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [FTC Drivetrain CAD feedback \- Chief Delphi](https://www.chiefdelphi.com/t/ftc-drivetrain-cad-feedback/459854).  
* **CAD.Course1.Session8 (Est. 2 hours):** Subsystem Design  
  * **Task 1:** Subsystem Concepts  
    * **Goal:** Choose a simple FTC subproblem (e.g., a simple lift or a claw) and sketch out a design concept.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [FTC Game Manual 0: Design Strategy](https://gm0.org/en/latest/docs/design-skills/design-strategy.html)  
  * **Task 2:** Design a custom bracket  
    * **Goal:** Create a simple custom bracket in Onshape to connect two parts of your planned subsystem.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Article: `Creating Custom Components` ([https://learn.onshape.com/learn/article/cad-for-robotics-creating-custom-components](https://learn.onshape.com/learn/article/cad-for-robotics-creating-custom-components))  
* **CAD.Course1.Session9 (Est. 2 hours):** Collaboration  
  * **Task 1:** Sharing and Collaboration  
    * **Goal:** Share an Onshape document with a teammate and grant them editing permissions.  
    * **Time Estimate:** 30 minutes  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Article: `CAD for Robotics Collaboration` ([https://learn.onshape.com/learn/article/cad-for-robotics-collaboration](https://learn.onshape.com/learn/article/cad-for-robotics-collaboration))  
  * **Task 2:** Comment on a design  
    * **Goal:** Use the comment feature in Onshape to provide feedback on a teammate's design.  
    * **Time Estimate:** 30 minutes  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Article: `CAD for Robotics Collaboration` ([https://learn.onshape.com/learn/article/cad-for-robotics-collaboration](https://learn.onshape.com/learn/article/cad-for-robotics-collaboration))  
  * **Task 3:** Manufacturing and Documentation  
    * **Goal:** Create a basic drawing and Bill of Materials (BOM) for a custom part.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Article: `Communicating Your Design` ([https://learn.onshape.com/learn/article/cad-for-robotics-communicating-your-design](https://learn.onshape.com/learn/article/cad-for-robotics-communicating-your-design))  
* **CAD.Course1.Session10 (Est. 2 hours):** Prepare for Manufacturing  
  * **Task 1:** Prepare for manufacturing  
    * **Goal:** Export a part file in a format suitable for manufacturing (e.g., DXF for laser cutting, STL for 3D printing).  
    * **Time Estimate:** 2 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Article: `Manufacturing` ([https://learn.onshape.com/learn/article/cad-for-robotics-manufacturing](https://learn.onshape.com/learn/article/cad-for-robotics-manufacturing))

#### **CAD.Course2: Advanced CAD (10 Sessions)**

**Goal:** To learn advanced modeling techniques, design for manufacturing, and professional collaboration workflows.

* **CAD.Course2.Session1 (Est. 3 hours):** Design for Manufacturing  
  * **Task 1:** Design for Manufacturing (DFM)  
    * **Goal:** Explain the basic principles of DFM, focusing on how design choices affect manufacturing processes like 3D printing and laser cutting.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer.  
    * **Resource:** [FTC Game Manual 0: Design Strategy](https://gm0.org/en/latest/docs/design-skills/design-strategy.html)  
  * **Task 2:** Create a laser-cut part  
    * **Goal:** Design a custom part (e.g., a chassis plate) that can be easily laser-cut from a flat sheet of material.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
  * **Task 3:** Create a 3D-printed part  
    * **Goal:** Design a custom part that uses features like overhangs and support structures appropriate for 3D printing.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
* **CAD.Course2.Session2 (Est. 2.5 hours):** Advanced Features  
  * **Task 1:** Use Advanced Features \- `Sweep`  
    * **Goal:** Use the `Sweep` feature to create a part with a profile that follows a complex curve.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
  * **Task 2:** Use Advanced Features \- `Loft`  
    * **Goal:** Use the `Loft` feature to create a part by blending two or more profiles together.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
* **CAD.Course2.Session3 (Est. 2.5 hours):** Pattern and Move  
  * **Task 1:** Use Advanced Features \- `Pattern`  
    * **Goal:** Use the `Pattern` feature (linear or circular) to quickly create multiple instances of a feature or part.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
  * **Task 2:** Use Advanced Features \- `Move Face`  
    * **Goal:** Use the `Move Face` tool to make quick edits to an existing part without having to change the original sketches.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
* **CAD.Course2.Session4 (Est. 2 hours):** Assembly Simulation  
  * **Task 1:** Assembly Simulation \- Motion  
    * **Goal:** Create a simple gear train assembly and use `Mate` connectors to simulate its motion.  
    * **Time Estimate:** 2 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [Onshape Assembly Simulation Help](https://cad.onshape.com/help/Content/simulation.htm).  
* **CAD.Course2.Session5 (Est. 3 hours):** Featurescripts and Versioning  
  * **Task 1:** Use Featurescripts  
    * **Goal:** Find and use a Featurescript from the Onshape community to automate a complex task, such as generating gears or sprockets.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape Learning Center: [FeatureScript Fundamentals](https://learn.onshape.com/courses/featurescript-fundamentals).  
  * **Task 2:** Create a Version  
    * **Goal:** Create a named version of your CAD model at a major milestone (e.g., "Drivetrain Complete").  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape `Versions and History` ([https://learn.onshape.com/learn/article/introduction-to-onshape-versions-and-history](https://www.google.com/search?q=https://learn.onshape.com/learn/article/introduction-to-onshape-versions-and-history)).  
* **CAD.Course2.Session6 (Est. 2.5 hours):** Documentation  
  * **Task 1:** Bill of Materials (BOM)  
    * **Goal:** Generate a detailed Bill of Materials for your final robot assembly.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape `Communicating Your Design` ([https://learn.onshape.com/learn/article/cad-for-robotics-communicating-your-design](https://learn.onshape.com/learn/article/cad-for-robotics-communicating-your-design)).  
  * **Task 2:** Create a Technical Drawing  
    * **Goal:** Create a 2D technical drawing of a custom part with dimensions, tolerances, and a title block.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape `Communicating Your Design` ([https://learn.onshape.com/learn/article/cad-for-robotics-communicating-your-design](https://learn.onshape.com/learn/article/cad-for-robotics-communicating-your-design)).  
* **CAD.Course2.Session7 (Est. 2.5 hours):** Configurations and Subsystems  
  * **Task 1:** Use the Onshape `Configurations` tool  
    * **Goal:** Create a configurable part or assembly that can be easily modified (e.g., a chassis with different lengths).  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape `Configurations Help` ([https://cad.onshape.com/help/Content/configurations.htm](https://cad.onshape.com/help/Content/configurations.htm)).  
  * **Task 2:** Design an Intake Mechanism  
    * **Goal:** Design and model a subsystem, such as an intake or a lift, from scratch in a new Part Studio.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [Designing Our FTC Bot 2023-2024 \- Instructables](https://www.instructables.com/Designing-Our-FTC-Bot-2023-2024-1/)  
* **CAD.Course2.Session8 (Est. 2.5 hours):** Assembly Visualization  
  * **Task 1:** Assemble the Intake  
    * **Goal:** Assemble the intake subsystem with the drivetrain, ensuring all mates work correctly.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
  * **Task 2:** Create Exploded View  
    * **Goal:** Create an exploded view of a complex assembly to show how the parts fit together.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape `Exploded Views Help` ([https://cad.onshape.com/help/Content/exploded-views.htm](https://www.google.com/search?q=https://cad.onshape.com/help/Content/exploded-views.htm)).  
* **CAD.Course2.Session9 (Est. 2.5 hours):** Rendering and Collaboration  
  * **Task 1:** Render an Assembly  
    * **Goal:** Create a high-quality rendered image of your robot assembly for presentation or a design notebook.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape `Renderings Help` ([https://cad.onshape.com/help/Content/renderings.htm](https://www.google.com/search?q=https://cad.onshape.com/help/Content/renderings.htm)).  
  * **Task 2:** Collaborate with `Branches` and `Merge`  
    * **Goal:** Create a branch to work on a new feature and then merge it back into the main workspace.  
    * **Time Estimate:** 1 hour  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** Onshape `Branches and Merge Help` ([https://cad.onshape.com/help/Content/branches.htm](https://www.google.com/search?q=https://cad.onshape.com/help/Content/branches.htm)).  
* **CAD.Course2.Session10 (Est. 3 hours):** Design Review and Documentation  
  * **Task 1:** Robot Design Review  
    * **Goal:** Present your complete CAD model to your team and judges for a design review, explaining your design choices and trade-offs.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer with a web browser.  
    * **Resource:** [FTC Game Manual 0: Design Strategy](https://gm0.org/en/latest/docs/design-skills/design-strategy.html)  
  * **Task 2:** Robot Design Documentation  
    * **Goal:** Create a comprehensive design document that includes your CAD models, BOM, and a narrative of your design process.  
    * **Time Estimate:** 1.5 hours  
    * **Hardware Needed:** Computer.  
    * **Resource:** [FTC Design Award Resources](https://www.firstinspires.org/resource-library/ftc/design-award-resources).

---

### **Combined CAD-CODE-COMPETE**

**Goal:** To synthesize all skills by designing, building, and programming a complete robot for a past FTC game.

* **Combined.Session1 (Est. 3 days):** Robot Concept and CAD  
  * **Task 1:** Game Analysis  
    * **Goal:** Break down the rules and objectives of a past FTC game. Identify key scoring elements, field hazards, and robot requirements.  
    * **Time Estimate:** 1 day  
    * **Hardware Needed:** None.  
    * **Resource:** [Analyzing and Breaking Down the 2026 FTC Game](https://www.youtube.com/watch?v=p0O9iQno-AM).  
  * **Task 2:** Integrated Robot Concept  
    * **Goal:** As a team, create a single document outlining the robot's design concept, including a sketch of the chassis and a description of the planned mechanisms.  
    * **Time Estimate:** 1 day  
    * **Hardware Needed:** None.  
    * **Resource:** [Designing Our FTC Bot 2023-2024 \- Instructables](https://www.instructables.com/Designing-Our-FTC-Bot-2023-2024-1/).  
  * **Task 3:** Full Robot CAD  
    * **Goal:** The CAD team must design the complete robot in Onshape, including the drivetrain and all subsystems, ensuring they fit together correctly and can be built with available parts.  
    * **Time Estimate:** 1 day  
    * **Hardware Needed:** Computer with a web browser.  
* **Combined.Session2 (Est. 3 days):** Full Robot Code  
  * **Task 1:** Full Robot Code  
    * **Goal:** The software team must write a comprehensive program for the robot, including a robust tele-op and an autonomous mode that uses Road Runner and state machines to complete game objectives.  
    * **Time Estimate:** 3 days  
    * **Hardware Needed:** Computer, Driver Station phone, Gamepad, Control Hub.  
* **Combined.Session3 (Est. 2 days):** Build and Test  
  * **Task 1:** Final Build and Test  
    * **Goal:** As a team, assemble the physical robot from the CAD models and test all its functions.  
    * **Time Estimate:** 1.5 days  
    * **Hardware Needed:** A complete set of physical robot parts, tools, computer, Control Hub, Driver Station phone, Gamepad.  
  * **Task 2:** Final Competition Simulation  
    * **Goal:** Run the robot through a mock competition to evaluate its performance and prepare for a real event.  
    * **Time Estimate:** 0.5 days  
    * **Hardware Needed:** A complete robot, a full FTC field, computer, Control Hub, Driver Station phone, Gamepad.

---

### **Complete List of Resources**

* **Software Resources:**  
  * [Learn Java for FTC (PDF)](https://drive.google.com/file/d/1ENTNJpHcZsK_7MkBGg-ox33cuMM4aFeH/view?usp=drive_link)  
  * [Road Runner Docs](https://rr.brott.dev/docs/)  
  * [Road Runner FAQs](https://learnroadrunner.com/introduction.html#frequently-asked-questions)  
  * [GM0.org Mecanum Drive Tutorial](https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html)  
  * [FTC SDK Samples on GitHub](https://github.com/FIRST-Tech-Challenge/FtcRobotController/tree/master/FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples)  
  * [FTC-Sim Courses](https://ftcsim.org/#frontpageCourses)  
  * [MeepMeep Trajectory Tool](https://learnroadrunner.com/tool/meepmeep.html)  
  * [FIRST Tech Challenge Programming Resources](https://ftc-docs.firstinspires.org/en/latest/programming_resources/index.html)  
  * [FTC Driver Station Components](https://ftc-docs.firstinspires.org/en/latest/control_hard_compon/ds_components/index.html)  
* **CAD Resources:**  
  * [Onshape Learning Center](https://learn.onshape.com/)  
  * [Onshape Introduction to CAD Learning Path](https://learn.onshape.com/learning-paths/introduction-to-cad)  
  * [Onshape Intro to Onshape Course](https://learn.onshape.com/courses/intro-to-onshape)  
  * [Onshape FTC Catalog](https://learn.onshape.com/catalog?query=ftc)  
  * [Onshape Article: `Intro to Onshape`](https://www.google.com/search?q=%5Bhttps://learn.onshape.com/learn/article/intro-to-onshape%5D\(https://learn.onshape.com/learn/article/intro-to-onshape\))  
  * [Onshape Article: `Assemble a Robot`](https://www.google.com/search?q=%5Bhttps://learn.onshape.com/learn/article/assemble-a-robot%5D\(https://learn.onshape.com/learn/article/assemble-a-robot\))  
  * [Onshape Article: `CAD for Robotics Collaboration`](https://www.google.com/search?q=%5Bhttps://learn.onshape.com/learn/article/cad-for-robotics-collaboration%5D\(https://learn.onshape.com/learn/article/cad-for-robotics-collaboration\))  
  * [Onshape Article: `Communicating Your Design`](https://www.google.com/search?q=%5Bhttps://learn.onshape.com/learn/article/cad-for-robotics-communicating-your-design%5D\(https://learn.onshape.com/learn/article/cad-for-robotics-communicating-your-design\))  
  * [Onshape Article: `Creating Custom Components`](https://www.google.com/search?q=%5Bhttps://learn.onshape.com/learn/article/cad-for-robotics-creating-custom-components%5D\(https://learn.onshape.com/learn/article/cad-for-robotics-creating-custom-components\))  
  * [Onshape Article: `Manufacturing`](https://www.google.com/search?q=%5Bhttps://learn.onshape.com/learn/article/cad-for-robotics-manufacturing%5D\(https://learn.onshape.com/learn/article/cad-for-robotics-manufacturing\)) I have updated the `Software.Course1` to have 10 sessions as you requested. I split the 3-hour session "Controlling Motors" into two new sessions: "Controlling a Single Motor" and "Motors and Hardware Mapping", and renumbered all subsequent sessions to maintain a logical progression.

