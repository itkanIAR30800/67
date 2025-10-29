

{0}------------------------------------------------

# Learn Java for FTC

Alan G. Smith

November 24, 2024

{1}------------------------------------------------

Cover Photo Credit: [Nastassia Bas on 123rf.com](#)  
All Rights Reserved.

FIRST Tech Challenge, and FTC are registered trademarks of For Inspiration and Recognition of Science and Technology (FIRST) which does not sponsor, authorize, or endorse this book.

REV, REV Control Hub, REV Expansion Hub, and Rev Robotics are trademarks of Rev Robotics which does not sponsor, authorize, or endorse this book.

## Learn Java for FTC

Copyright © 2020 - 2024 Alan G. Smith. All Rights Reserved.

The author can be contacted at: [alan@randomsmiths.com](mailto:alan@randomsmiths.com)

The hardcopy of the book can be purchased from Amazon at <https://www.amazon.com/dp/B08DBVKXLZ>

The most recent PDF is free at <https://github.com/alan412/LearnJavaForFTC>

ISBN: 9798644009886

{2}------------------------------------------------

This book is dedicated to:

My wife who after suffering through my first book  
encouraged me to write this one.

My FTC team that excites me about teaching

My father who spent many hours with me on the Vic 20,  
Commodore 64, and the robotic arm science project.  
Without his investment, I wouldn't be the engineer I am  
today.

FTC coaches everywhere that teach their students to think  
well and work hard

*Whatever you do, work at it with all your heart, as working  
for the Lord, not for men.*  
*Colossians 3:23 (NIV 1984)*

{3}------------------------------------------------

![](_page_3_Picture_0.jpeg)

{4}------------------------------------------------

# Contents

| <b>1. Introduction</b>               | <b>1</b>  |
|--------------------------------------|-----------|
| 1.1. Hardware                        | 1         |
| 1.1.1. Robot Controller              | 1         |
| 1.1.2. Programming Board             | 1         |
| 1.1.3. Driver Station                | 2         |
| 1.2. Our first OpMode                | 2         |
| 1.2.1. Some terminology              | 2         |
| 1.2.2. What is an OpMode?            | 3         |
| 1.2.3. Parts of an OpMode            | 3         |
| 1.2.4. Hello, World                  | 3         |
| 1.3. Now you try                     | 6         |
| 1.4. Comments                        | 8         |
| 1.5. Sending to the Robot Controller | 9         |
| 1.6. Gotchas                         | 10        |
| 1.7. Exercises                       | 10        |
| <b>2. Variables and Data Types</b>   | <b>13</b> |
| 2.1. Primitive Data Types            | 13        |
| 2.2. String                          | 15        |
| 2.3. Scope                           | 16        |
| 2.4. Exercises                       | 16        |
| <b>3. Gamepad and basic math</b>     | <b>17</b> |
| 3.1. Basic Math                      | 18        |
| 3.2. Other assignment operators      | 20        |
| 3.3. Exercises                       | 20        |
| <b>4. Making decisions</b>           | <b>21</b> |
| 4.1. If                              | 21        |
| 4.2. Else                            | 23        |
| 4.2.1. Else if                       | 23        |
| 4.3. Combinations                    | 24        |
| 4.4. While                           | 25        |

{5}------------------------------------------------

Contents

| 4.5.   | For                                                  | 26        |
|--------|------------------------------------------------------|-----------|
| 4.6.   | Exercises                                            | 27        |
| 5.     | <b>Class Members and Methods</b>                     | <b>29</b> |
| 5.1.   | Class Members                                        | 29        |
| 5.2.   | Class Methods                                        | 30        |
| 5.2.1. | Return Types                                         | 31        |
| 5.2.2. | Parameters                                           | 31        |
| 5.2.3. | Special Methods: Constructors                        | 32        |
| 5.2.4. | Another special method: toString                     | 33        |
| 5.3.   | Controlling access- Keep your private things private | 34        |
| 5.4.   | Creating your own classes                            | 34        |
| 5.5.   | static                                               | 39        |
| 5.6.   | Exercises                                            | 40        |
| 6.     | <b>Our first hardware</b>                            | <b>41</b> |
| 6.1.   | Configuration file                                   | 41        |
| 6.2.   | Mechanisms                                           | 43        |
| 6.3.   | OpMode                                               | 45        |
| 6.4.   | Making changes                                       | 46        |
| 6.5.   | Exercises                                            | 47        |
| 7.     | <b>Motors</b>                                        | <b>49</b> |
| 7.1.   | Editing Configuration File                           | 49        |
| 7.2.   | Mechanisms                                           | 50        |
| 7.3.   | OpMode                                               | 53        |
| 7.4.   | Motor as Sensor                                      | 53        |
| 7.5.   | Motors and Sensors together                          | 56        |
| 7.6.   | Motors and Gamepads                                  | 57        |
| 7.7.   | Exercises                                            | 58        |
| 8.     | <b>Servos</b>                                        | <b>59</b> |
| 8.1.   | Configuration File                                   | 59        |
| 8.2.   | Mechanisms                                           | 59        |
| 8.3.   | OpMode                                               | 61        |
| 8.4.   | Exercises                                            | 62        |
| 9.     | <b>Analog Sensors</b>                                | <b>63</b> |
| 9.1.   | Configuration File                                   | 63        |
| 9.2.   | Mechanisms                                           | 63        |
| 9.3.   | OpMode                                               | 65        |

{6}------------------------------------------------

| 9.4. Exercises                                | 66         |
|-----------------------------------------------|------------|
| <b>10. Color and Distance Sensors</b>         | <b>67</b>  |
| 10.1. Configuration File                      | 67         |
| 10.2. Mechanisms                              | 67         |
| 10.3. OpMode                                  | 69         |
| 10.4. Exercises                               | 71         |
| <b>11. Gyro (IMU)</b>                         | <b>73</b>  |
| 11.1. Configuration File                      | 73         |
| 11.2. Mechanisms                              | 73         |
| 11.3. OpMode                                  | 76         |
| 11.4. Exercises                               | 76         |
| <b>12. Dealing with State</b>                 | <b>77</b>  |
| 12.1. A simple example                        | 77         |
| 12.2. Autonomous state - Example              | 79         |
| 12.2.1. Using the switch statement            | 81         |
| 12.2.2. Switch with strings                   | 83         |
| 12.2.3. Enumerated types                      | 84         |
| 12.3. It's all relative                       | 86         |
| 12.4. Exercises                               | 88         |
| <b>13. Arrays</b>                             | <b>91</b>  |
| 13.1. ArrayList                               | 92         |
| 13.1.1. Making your own generic class         | 93         |
| 13.2. Exercises                               | 93         |
| <b>14. Inheritance</b>                        | <b>95</b>  |
| 14.1. Isa vs. hasa                            | 96         |
| 14.2. So why in the world would you use this? | 97         |
| 14.3. Exercises                               | 104        |
| <b>15. Rumble with Gamepad</b>                | <b>105</b> |
| 15.1. Exercises                               | 107        |
| <b>16. Computer Vision</b>                    | <b>109</b> |
| 16.1. April Tags                              | 109        |
| 16.1.1. The Opmode                            | 110        |
| 16.2. The empty processor                     | 113        |

{7}------------------------------------------------

## Contents

| 16.3. Our first vision processor                               | 115 |
|----------------------------------------------------------------|-----|
| 16.3.1. The processor                                          | 115 |
| 16.3.2. The opmode                                             | 117 |
| 16.3.3. Bonus - using EOCVSim (Optional)                       | 118 |
| 16.4. Expanding to 3 rectangles                                | 119 |
| 16.5. Actual computer vision...                                | 122 |
| 16.5.1. The opmode                                             | 125 |
| 16.6. Exercises                                                | 126 |
| 17. Javadoc                                                    | 127 |
| 17.1. Exercises                                                | 129 |
| 18. Finding things in FTC SDK                                  | 131 |
| 18.1. Exercise                                                 | 131 |
| 19. A few other topics                                         | 133 |
| 19.1. Math class                                               | 133 |
| 19.2. final                                                    | 134 |
| 19.3. Make telemetry prettier                                  | 135 |
| 19.4. Interfaces (implements)                                  | 136 |
| 19.4.1. When to use an interface instead of an abstract class? | 137 |
| 19.5. Exercises                                                | 137 |
| 20. Making Robots Drive                                        | 139 |
| 20.1. 2 motor drive                                            | 139 |
| 20.1.1. Two Motor Drive Mechanism                              | 139 |
| 20.1.2. OpMode                                                 | 141 |
| 20.2. 4 motor mecanum drive                                    | 142 |
| 20.2.1. Mecanum Mechanism                                      | 143 |
| 20.2.2. Robot oriented driving                                 | 146 |
| 20.2.3. Field oriented driving                                 | 147 |
| 20.3. Exercises                                                | 149 |
| 21. Some hardware to help with Odometry                        | 151 |
| 21.1. OctoQuad                                                 | 151 |
| 21.1.1. What is it?                                            | 151 |
| 21.1.2. Using it simply                                        | 151 |
| 21.1.3. Using it to get multiple encoders                      | 153 |
| 21.1.4. Using the cached attribute for clean programming       | 154 |
| 21.1.5. Other features                                         | 158 |
| 21.1.5.1. Velocity                                             | 158 |

{8}------------------------------------------------

| 21.1.5.2 Absolute encoders                      | 158        |
|-------------------------------------------------|------------|
| 21.2. Sparkfun Optical Tracking Odometry Sensor | 158        |
| 21.2.1. What is it?                             | 158        |
| 21.2.2. Using it                                | 159        |
| 21.3. Exercises                                 | 162        |
| <b>22. LEDs - Adding some bling feedback...</b> | <b>163</b> |
| 22.1. REV Digital LED Indicator                 | 163        |
| 22.2. Sparkfun QWIIC LED Stick                  | 164        |
| 22.3. REV Blinkin                               | 166        |
| 22.4. Exercises                                 | 169        |
| <b>23. Limelight 3A</b>                         | <b>171</b> |
| 23.1. Simple color example                      | 171        |
| 23.1.1. On the Limelight                        | 171        |
| 23.1.2. Your Java Code                          | 174        |
| 23.1.3. Changing Limelight Pipeline             | 176        |
| 23.1.4. Swapping between pipelines              | 176        |
| 23.2. Localization with AprilTags               | 178        |
| 23.2.1. On the Limelight                        | 178        |
| 23.2.2. Your Java Code                          | 179        |
| 23.3. Exercises                                 | 180        |
| <b>A. Making your own Programming Board</b>     | <b>181</b> |
| <b>B. LinearOpMode</b>                          | <b>183</b> |
| B.1. What is it?                                | 183        |
| B.2. Should you use it?                         | 184        |
| B.2.1. Benefits of LinearOpMode                 | 184        |
| B.2.2. Drawbacks of LinearOpMode                | 185        |
| <b>C. Sample Solutions</b>                      | <b>187</b> |
| C.1. Chapter 1 Solutions                        | 187        |
| C.2. Chapter 2 Solutions                        | 188        |
| C.3. Chapter 3 Solutions                        | 189        |
| C.4. Chapter 4 Solutions                        | 190        |
| C.5. Chapter 5 Solutions                        | 192        |
| C.6. Chapter 6 Solutions                        | 197        |
| C.7. Chapter 7 Solutions                        | 199        |
| C.8. Chapter 8 Solutions                        | 201        |
| C.9. Chapter 9 Solutions                        | 202        |

{9}------------------------------------------------

## Contents

| C.10. | Chapter 10 Solutions | 204 |
|-------|----------------------|-----|
| C.11. | Chapter 11 Solutions | 207 |
| C.12. | Chapter 12 Solutions | 208 |
| C.13. | Chapter 13 Solutions | 211 |
| C.14. | Chapter 14 Solutions | 213 |
| C.15. | Chapter 15 Solutions | 222 |
| C.16. | Chapter 16 Solutions | 222 |
| C.17. | Chapter 17 Solutions | 224 |
| C.18. | Chapter 18 Solutions | 227 |
| C.19. | Chapter 19 Solutions | 228 |
| C.20. | Chapter 20 Solutions | 229 |
| C.21. | Chapter 21 Solutions | 230 |
| D.    | Credits              | 233 |
|       | Index                | 235 |

{10}------------------------------------------------

# 1. Introduction

In coaching an FTC team<sup>1</sup>, I found that students wanted to be effective coders but had trouble figuring out where to start. When they took online courses, they ended up learning a lot of things that weren't helpful for FTC (or even usable). In addition, many of the online sources and even books teach bad habits. I started this as some slides for my team, but decided it would be better as a book that could be shared widely.

You'll notice that throughout the book some words are written in a different font like *this*. That means that it is code that needs to be exactly like that (capitalization, etc)

## 1.1. Hardware

### 1.1.1. Robot Controller

The Robot Controller, often abbreviated "RC", is the "brains" of your robot. It is what our programs run on. The RC can be either an Android phone or a REV Control Hub. When using an Android phone, it is connected to a REV Expansion Hub over USB. The REV Expansion Hub is what all motors, servos, and sensors connect to. A Rev Control Hub is new for the 2020-2021 FTC Season and is basically an Android phone and Expansion Hub in the same package instead of having them separate.

### 1.1.2. Programming Board

For this book, instead of a full robot we have made a simple *Programming Board* (just the electrical components that we are using) that we can use throughout the book so that we all have the same hardware. For directions on how to make your own, see [Appendix A](#).

<sup>1</sup>Go Quantum Quacks - FTC #16072

{11}------------------------------------------------

# 1. Introduction

## 1.1.3. Driver Station

![Screenshot of the Driver Station (DS) app interface. Annotations point to various elements: Name of this device (TeamNumber - letter - DS), Battery power of this phone, Robot Configuration, Battery power of RC phone, Press here to select an autonomous OpMode, This changes from INIT to a big PLAY symbol to a STOP symbol, This is where all telemetry data sent from robot is displayed, Showing connected gamepads, Three dots to configure, Network connected to (Team Number - letter - RC), How long it takes to communicate to robot. Should be small., Power coming into REV Expansion Hub. Fresh battery should be above 12V, Press here to select a TeleOp OpMode, A timer so you can see how much time your autonomous takes, OpMode selected.]()![](_page_11_Picture_2.jpeg)

The Driver Station, often abbreviated “DS”, is an Android Phone with 1 or 2 USB gamepads connected that are used during the game to drive the robot. Above is an example driver station with descriptions for everything on it. This changes slightly from year to year.

## 1.2. Our first OpMode

### 1.2.1. Some terminology

A little terminology before we get started.

**class** In Java all code is grouped together in classes. We’ll discuss exactly what classes are later in **chapter 5**. For now, just know that a class groups like code together and in Java, each class is in its own file that is named the same as the class with .java at the end.

**method** A method is a group of code within a class. Methods are the smallest group of code that can be executed. It is like a function in some languages or a MyBlock in EV3-G. We’ll talk more about this later in **section 5.2**.

**package** A directory in JAVA. It is where the code is located. Files in the same package have special privileges with each other. We’ll talk about this in **section 5.3**. And yes, a package can have packages within it.

{12}------------------------------------------------

### 1.2.2. What is an OpMode?

In FTC, An OpMode<sup>2</sup> is a program for our robot. We can have multiple OpModes. They are all stored in the TeamCode package.

### 1.2.3. Parts of an OpMode

OpModes are required to have two methods:

1. `init()` - This is run **once** when the driver presses INIT.
2. `loop()` - This is run **repeatedly** after driver presses PLAY but before STOP.

In addition, there are three optional methods. These are less common but can be very useful.

1. `init_loop()` - This is run **repeatedly** after driver presses INIT but before PLAY.
2. `start()` - This is run **once** when the driver presses PLAY.
3. `stop()` - This is run **once** when the driver presses STOP.

![Flowchart illustrating the execution sequence of an OpMode. The flow starts with 'OpMode selected'. Pressing 'INIT pressed' leads to 'init()'. 'init()' leads to 'init_loop()'. 'init_loop()' runs repeatedly (~50x a second) and leads to 'start()'. 'start()' leads to 'loop()'. 'loop()' runs repeatedly (~50x a second) and leads to 'stop()'. Pressing 'STOP pressed' leads to 'stop()'. 'stop()' leads back to 'OpMode selected'.]()![](_page_12_Figure_8.jpeg)

If you look over on the right, you'll see a diagram that explains roughly how it works. The solid ovals are required and the dashed ones are optional. After `stop()` is executed it goes back to the top.

I know this seems strange, but I promise it will make more sense as we continue.

### 1.2.4. Hello, World

Traditionally, the first program written in every programming language simply writes "Hello, World!" to the screen. But instead of writing to the robot's screen, we'll write to the screen on the Driver Station. (Throughout this book we will show the program in its entirety first, and then explain it afterwards. So if you

<sup>2</sup>You will likely run across LinearOpMode as many teams use it. There is a discussion in Appendix B for why we don't use it but it is probably best left for the end.

{13}------------------------------------------------

## 1. Introduction

see something that doesn't make sense, keep reading and hopefully it will be cleared up.)

Listing 1.1: HelloWorld.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class HelloWorld extends OpMode {
8     @Override
9     public void init() {
10         telemetry.addData("Hello", "World");
11     }
12
13     @Override
14     public void loop() {
15     }
16 }
17 }
```

Here is a breakdown of what this program does.

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
```

If you are working in Android Studio, you won't have to enter any of these lines as it will add them for you. Line 1 basically says where this file is located. 3 and 4 bring in code from the FTC SDK (Software Development Kit) so we can use them.

```
6 @TeleOp()
```

This is **CRITICAL**. If you forget this line, it won't show up on the Driver-Station as an OpMode to select from. Any line that starts with an @ is called an annotation. You can choose from @TeleOp() or @Autonomous(). You can optionally give it a name and a group, but if you leave those off then it will use your class name as the name. This works well enough, so we'll typically leave those pieces out. Another annotation that you'll see commonly is @Disabled. If you have that, then your code will compile but it won't be shown in the list of OpModes.<sup>3</sup>

<sup>3</sup>Our team often does that for test code that we don't want to distract us during a tournament but is VERY helpful to have where we can make it available quickly.

{14}------------------------------------------------

```
7 public class HelloWorld extends OpMode {  
17 }
```

public - means others can see it. Required for OpModes. We'll discuss this more in [section 5.3](#).

class - means we are defining a class

HelloWorld - this is the name of the class. It must be the same as the filename (except the filename has .java on it). By convention, it should be started with a capital letter and each new word is a capital letter (Pascal case). We'll talk more about classes in [chapter 5](#).

extends OpMode - This means the class is a child of OpMode. A child gets all of the behavior of its parent and then can add (or replace) functionality. We'll talk about what this means in [chapter 14](#).

a class is defined from the opening curly brace "{ " to the closing curly brace "}"

```
8 @Override  
9 public void init() {  
10 telemetry.addData("Hello", "World");  
11 }
```

@Override tells the compiler that we are meaning to override (replace) functionality in our parent class. We'll talk more about this in [chapter 14](#).

public means this method is callable from outside the class. We'll discuss this more in [section 5.3](#)

void means it doesn't return anything. We'll talk about return types in [subsection 5.2.1](#)

init is the name of a method. We'll talk more about methods in [section 5.2](#)

Inside of the parenthesis are any parameters passed in or none. (as in this case) We'll talk about parameters in [subsection 5.2.2](#)

The method is defined from the opening curly brace "{ " to the closing curly brace "}"

telemetry.addData(caption, value); This is very cool because it sends data to the driver station which lets us debug problems. In this case we sent back a string (a group of characters - we'll talk about strings in [section 2.2](#)), but you can also send back numbers or variables. You'll notice that this ends in a semi-colon ";" All statements in JAVA either end with a semi-colon or have a set of curly braces attached.

```
13 @Override  
14 public void loop() {
```

{15}------------------------------------------------

## 1. Introduction

```
15
16 }
```

This looks much the same as our `init()` method, but there is no code in the `loop()` method, so the program won't do anything here. (We included it because it is required.)

### 1.3. Now you try

Before you do this, you need to have your phones ready to go and Android Studio installed with a copy of the FTC SDK. For instructions, see the FTC document *Android Studio Guide*<sup>4</sup>.

You'll learn the best here if you type in the examples (and you'll get faster at Android Studio). While this may seem like it slows you down, it helps you learn faster. This is the only time in the book I'll mention "Now you try". For the rest, I suggest you type it in AFTER we have explained what it does and then try it. To start with, change the project area to show "Android" (by using the dropdown). If you are wondering why your Android Studio is white colored while mine is Dark, that is because I use the built-in theme "Darcula".<sup>5</sup>

![Screenshot of Android Studio showing the Project view. The user is right-clicking on the 'org.firstinspires.ftc.teamcode' folder under 'TeamCode' and selecting 'New' > 'Java Class' from the context menu.]()![](_page_15_Picture_6.jpeg)

1. Right click on `org.firstinspires.ftc.teamcode` under TeamCode
2. Select New > Java Class

![Screenshot of the 'New Java Class' dialog box in Android Studio, showing options for creating a new Java class, with 'Class' selected.]()![](_page_15_Picture_8.jpeg)

1. (If you are using Android Studio 4.x, it will look like this....)

<sup>4</sup>[https://ftc-docs.firstinspires.org/en/latest/programming\\_resources/android\\_studio\\_java/install/install.html](https://ftc-docs.firstinspires.org/en/latest/programming_resources/android_studio_java/install/install.html)

<sup>5</sup>To change your theme click File > Settings from the menu bar (or Android Studio > Preferences on macOS). Go to Appearance under Appearance and Behavior, and you'll see Theme.

{16}------------------------------------------------

1. Fill in the name as HelloWorld
2. Press “OK”
3. If you get another dialog box with a bunch of blanks, leave them blank and press “OK”
4. You’ll get a listing that will look like this

```
package org.firstinspires.ftc.teamcode;

public class HelloWorld {
```

![Screenshot of the 'New Java Class' dialog box in Android Studio. The Name field is filled with 'HelloWorld'. The Kind is set to 'Class'. The Superclass dropdown is open, showing 'OpMode' selected, along with other options like OpModeManager, OpModeManagerImpl, OpModeMeta, OpModeMetaAndClass, OpModeMetaAndInstance, OpModeNotifications, OpModeRegister, and OpModeRegistrarMethodManager.]()![](_page_16_Picture_3.jpeg)

1. (If you are using Android Studio 3.x, it will look like this...)
   1. Fill in the name as HelloWorld
   2. Fill in the Superclass as OpMode. (We’ll explain what this means in [chapter 14](#)) As you type it in, it will show you the matches. When you select it, it will fill in as `com.qualcomm.robotcore.eventloop.opmode`
   3. Press “OK”. You’ll get a file that will be like this:

```
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class HelloWorld extends OpMode {
```

1. It will have a red squiggle line under the class declaration. That is because you haven’t implemented the two required methods yet. You haven’t done anything wrong.

{17}------------------------------------------------

## 1. Introduction

Make yours look like the HelloWorld.java file in Listing 1.1 earlier. (You can start at line 6 and you'll watch it make the import statements as you type)

As you start typing, you'll notice that Android Studio is giving suggestions. You can either click on the one you want, or when it is at the top of the list then press tab.

This is the same pattern you'll follow for all OpModes in this book.

## 1.4. Comments

So far our programs have been only for the computer. But it turns out that you can put things in them that are only for the human readers. You can (and should) add comments to the program which the computer ignores and are for human readers only. Comments should explain things that are not obvious from the code such as why something is being done. In general, comments should explain why and not what. Please don't just put in a comment that repeats the code.

Java supports two forms of comments:

1. A single line comment. It starts with a `//` and tells the computer to ignore the rest of the line.

```
// This is a comment
```

1. The block comment style. It starts with a `/*` and continues until a `*/` is encountered. This can cross multiple lines. Below are three examples.

```
/* This is also a comment */
/* So is this */

/*
* And
* this
* as
* well */

```

In addition, there is a subset of this type of comment called a javadoc that we'll talk about in [chapter 17](#). This starts on a line with a `/**` and then goes until it sees `*/`. This is used for automatically creating documentation from your comments.

{18}------------------------------------------------

## 1.5. Sending to the Robot Controller

```
/**  
 * This is a javadoc comment  
 */
```

Here is what it looks like with comments added.

Listing 1.2: HelloWorldCommented.java

```
1 package org.firstinspires.ftc.teamcode;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 @TeleOp()  
7 public class HelloWorldCommented extends OpMode {  
8     /**  
9      * This is called when the driver presses INIT  
10     */  
11    @Override  
12    public void init() {  
13        // this sends to the driver station  
14        telemetry.addData("Hello", "World");  
15    }  
16  
17    /**  
18     * This is called repeatedly while OpMode is playing  
19     */  
20    @Override  
21    public void loop() {  
22        // intentionally left blank  
23    }  
24 }
```

## 1.5. Sending to the Robot Controller

1. Make sure your phones are setup as it describes in the FTC document *Configuring Your Android Devices*<sup>6</sup> and that they can see each other.
2. Connect the Robot Controller to the computer.
3. Press the green play arrow next to the name of the device on the top toolbar.

<sup>6</sup>https://github.com/ftctechnh/ftc\_app/wiki/Configuring-Your-Android-Devices

{19}------------------------------------------------

## 1. Introduction

1. Wait until you hear the sound from the Robot Controller and the Driver Station.
2. Now press the right arrow on the driver station to see the list of TeleOp OpModes. (The arrow on the left shows the list of Autonomous OpModes)
3. Select HelloWorld, and then press the big INIT button.
4. You should see “Hello: World” in the area where the Telemetry data is reported.

## 1.6. Gotchas

If your program won't compile (or it doesn't do what you expect), here are a few things to check that often confuse people:

- Java is case sensitive. In other words, `myVar` is different than `MyVar`
- Whitespace (spaces, tabs, blank lines) is all collapsed to the equivalent of a single space. It is for the human reader only.
- Blocks of code are encapsulated with curly braces `{` and `}`
- Every open parenthesis `(` must have a matching close parenthesis `)`
- Each program statement needs to end with a semicolon `;`. In general, this means that each line of your program will have a semicolon. Exceptions are:
  - Semicolons are not used when a code block follows - for example the class or method declarations we have seen so far
  - Semicolons (like everything) are ignored in comments
  - Semicolons are not used after the end curly brace. `}`

## 1.7. Exercises

After you have done the exercise, send it to the robot controller to make sure it works.

There are sample solutions in [Appendix C](#). However, you should struggle with them first and only look there when you are stuck. If you end up looking there, you should make up another exercise for yourself.

{20}------------------------------------------------

1. Change the code so that instead of saying “Hello: World” it says Hello and then your name.
2. Change the OpMode so it shows up in the Autonomous section of the Driver Station instead of the Teleop section.

{21}------------------------------------------------

![](_page_21_Picture_0.jpeg)

{22}------------------------------------------------

## 2. Variables and Data Types

A variable is a named location in memory where we can store information. While we don't have to, by convention we name variables starting with a lower case letter and then every word after that starts with a capital letter.<sup>1</sup> For example: motorSpeed or gyroHeading. In Java, we specify what type of information we are storing. *Primitive datatypes* are types that are built-in to Java.

We must declare a variable before we can use it. Declaring a variable requires that we specify the type and name. It is always followed by a ; (semi-colon).

```
// datatype name
int teamNumber;
double motorSpeed;
boolean touchSensorPressed;
```

The above variable types are int, double, and boolean (These are the three you'll use most often in FTC). We'll discuss these and the other primitive datatypes in the next section.

In Java, if you don't assign a value to a variable when you create it then it starts out being equal to 0. (or false for boolean)

To assign a value to a variable, you use the = operator like this:

```
teamNumber = 16072;
motorSpeed = 0.5;
touchSensorPressed = true;
```

You can assign a value to a variable multiple times and it will be equal to what you assigned it to most recently.

It's common to declare a variable and assign the value in one line!

For example, to assign 0.5 to a variable named motorSpeed of type double, we write:

```
double motorSpeed = 0.5;
```

### 2.1. Primitive Data Types

There are 8 primitive data types in Java:

<sup>1</sup>This is called camelCase because the upper case letters look like humps.

{23}------------------------------------------------

## 2. Variables and Data Types

1. byte - from the range -128 to 127
2. char - for holding a single unicode character
3. short - a smaller integer (almost never used in FTC)
4. int - this is short for integer. It is for numbers with no decimal.<sup>2</sup>
5. long - this is a larger integer. You can use it when you are concerned about running out of room in an int.<sup>3</sup>
6. float - this is for floating point numbers. It is smaller than a double so we typically convert to a double.
7. double - this is for floating point numbers. It can hold numbers with decimals.<sup>4</sup>
8. boolean - this can be either true or false. (Yes, it contains one or the other of these values.)

In the code below, there are examples of the three most typical primitive types for FTC.

Listing 2.1: PrimitiveTypes.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class PrimitiveTypes extends OpMode {
    @Override
    public void init() {
        int teamNumber = 16072;
        double motorSpeed = 0.5;
        boolean touchSensorPressed = true;

        telemetry.addData("Team Number", teamNumber);
        telemetry.addData("Motor Speed", motorSpeed);
        telemetry.addData("Touch Sensor", touchSensorPressed);
    }

    @Override
```

<sup>2</sup>It is also limited in the range from +2,147,483,647 to -2,147,483,648

<sup>3</sup>It is limited in the range from +9,223,372,036,854,775,807 to -9,223,372,036,854,775,808

<sup>4</sup>while technically it is limited, it is so large you can think of it as unlimited

{24}------------------------------------------------

```
public void loop() {  
}  
}
```

In the three lines below you'll see them defined. Notice how they all follow the same pattern:

```
int teamNumber = 16072;  
double motorSpeed = 0.5;  
boolean touchSensorPressed = true;
```

They are sent to the driver station using `telemetry.addData`. Again, you'll notice that they all follow the same pattern.

```
telemetry.addData("Team Number", teamNumber);  
telemetry.addData("Motor Speed", motorSpeed);  
telemetry.addData("Touch Sensor", touchSensorPressed);
```

## 2.2. String

A `String` is for holding text. You might be wondering why it is capitalized when all of the other data types we have seen so far aren't. This is because `String` is really a class. By convention, class names start with a Capital letter and then every other word is also capitalized. <sup>5</sup> We'll talk more about classes in chapter 5.

In the code below, there is an example of using a `String` data type.

Listing 2.2: UseString.java

```
package org.firstinspires.ftc.teamcode;  
  
import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
  
@TeleOp()  
public class UseString extends OpMode {  
    @Override  
    public void init() {  
        String myName = "Alan Smith";  
  
        telemetry.addData("Hello", myName);  
    }  
}
```

<sup>5</sup>This is called PascalCase because it was popularized by one of the lead designers of Turbo Pascal.

{25}------------------------------------------------

## 2. Variables and Data Types

```
14
15 @Override
16 public void loop() {
17
18 }
19 }
```

You'll notice that the pattern here is similar with `datatype variableName;` or `datatype variableName = initialValue;`

## 2.3. Scope

This may seem unimportant, but you'll see why it matters later. A variable is only usable within its scope. Its scope is from where it is declared until the end of the block it is defined within. A block is defined as any set of open and close curly braces. { }

A simple example:

```
public void loop(){
    int x = 5;
    // x is visible here
    {
        int y = 4;
        // x and y are visible here
    }
    // only x is visible here
}
```

## 2.4. Exercises

1. Change the string to have your name instead of mine in the code in [section 2.2](#)
2. Add a variable of type `int` that is called `grade` that has your grade in it. Use telemetry to send that to the driver station.

{26}------------------------------------------------

## 3. Gamepad and basic math

We can access the gamepads connected to the driver station from our OpMode. They are of the Gamepad class. We'll talk more about classes in [chapter 5](#). Since there are two of them, they are called gamepad1 and gamepad2.<sup>1</sup> The buttons on the gamepad are all boolean (true if they are pressed, false if they aren't). The d-pad is exposed as four buttons.<sup>2</sup> The joysticks are double with values between -1.0 and 1.0 (0.0 means in the center). There is one for each x (side to side) and one for each y (up and down). The x is negative to the left and positive to the right. For strange reasons, up is negative and down is positive. The left trigger and right trigger are also double with values between 0.0 and 1.0 (0.0 means not pressed, 1.0 means fully pressed). To get to these we use `variableName.memberName` Below, we show what the memberNames are for all of the parts of the gamepad. In the image below, the ones that are bolded are double (Sometimes we call these analog and the ones that are binary - digital)

![Diagram of a gamepad showing labeled members: left_trigger, left_bumper, right_trigger, right_bumper, back, start, y, b, x, a, dpad_down, dpad_left, dpad_right, dpad_up, left_stick_x, left_stick_y, left_stick_button (press in), right_stick_x, right_stick_y, right_stick_button (press in).]()![](_page_26_Picture_2.jpeg)

In the code below is an example of reading the Gamepad. The reason it is in `loop()` is because we want to update the telemetry as the gamepad changes. You'll remember that loop is called over and over again (approximately 50 times a second)

Listing 3.1: GamepadOpMode.java

<sup>1</sup>You might be wondering where these are declared. We'll talk about that in [chapter 14](#)

<sup>2</sup>Technically you can pull out the analog but that is a lot of work and not typically done.

{27}------------------------------------------------

### 3. Gamepad and basic math

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class GamepadOpMode extends OpMode {
    @Override
    public void init() {
    }

    @Override
    public void loop() {
        telemetry.addData("Left stick x", gamepad1.left_stick_x);
        telemetry.addData("Left stick y", gamepad1.left_stick_y);
        telemetry.addData("A button", gamepad1.a);
    }
}
```

![Lightbulb icon indicating a tip or important note.]()![](_page_27_Picture_2.jpeg)

You have to press the “Start” and “A” *simultaneously* on a gamepad to get the driver station to recognize gamepad 1 (and “Start” and “B” for gamepad2). Once the gamepad has been recognized the gamepad icon in the upper right corner of the DS (Driver Station) will be illuminated.

We talk about the new functionality (as of FTC SDK 6.0) of using rumble on gamepads that support it in [chapter 15](#).

#### 3.1. Basic Math

In the last section, we talked about how to read a gamepad. You probably noticed that reading the joystick gave us a number. Once something is a number, we own it. We can do any kind of math to it to get what we wanted. Below are some of the most common operators.

{28}------------------------------------------------

| Math Operator | Meaning                                                                                                                                                                                                                                                                                                                 |
|---------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| =             | assignment operator                                                                                                                                                                                                                                                                                                     |
| +             | addition operator                                                                                                                                                                                                                                                                                                       |
| -             | subtraction operator AND negative operator ( So saying -x is the same thing as saying (0 - x) )                                                                                                                                                                                                                         |
| *             | multiplication operator                                                                                                                                                                                                                                                                                                 |
| /             | division operator - be aware that if you are using integers only the whole part is kept. It is NOT rounded. For example: 5 / 2 == 2 ( == is how we describe two things are equal. We'll talk about it in section 4.1. )                                                                                                 |
| %             | modulo operator - This gives the remainder. For example: 5 % 2 == 1                                                                                                                                                                                                                                                     |
| ( and )       | These are parenthesis and they allow you to specify the order of operations just like in regular math. You can use these to tell the difference between 3 * (4 + 2) or (3 * 4) + 2 While there is a well defined order of operations, instead of memorizing that it makes more sense to use parenthesis to be specific. |

Below is an example of how we might set the speed forward we want to go based off of the joystick. In this case we are limiting our speed from -0.5 to 0.5. The joystick y-value is negative when you press it up and positive when you press it down which is backwards of how most people want to drive the robot, so we “negate” the value here to flip it (so negative is positive and vice versa)

Listing 3.2: MathOpMode.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class MathOpMode extends OpMode {
8     @Override
9     public void init() {
10     }
11
12     @Override
13     public void loop() {
14         double speedForward = -gamepad1.left_stick_y / 2.0;
```

{29}------------------------------------------------

### 3. Gamepad and basic math

```
15 telemetry.addData("Left stick y", gamepad1.left_stick_y);
16 telemetry.addData("speed Forward", speedForward);
17 }
18 }
```

The first thing we do is create a new variable and assign to it from another variable using math.

```
14 double speedForward = -gamepad1.left_stick_y / 2.0;
```

You'll notice that then we can send that variable directly using telemetry

```
16 telemetry.addData("speed Forward", speedForward);
```

### 3.2. Other assignment operators

There are some shortcuts where you can combine a math operator and an assignment operator. Below are some of the most common.

| Operator | Meaning                 | Example                            |
|----------|-------------------------|------------------------------------|
| ++       | increment               | x++ means the same as x = x + 1    |
| -        | decrement               | x-- means the same as x = x - 1    |
| +=       | Add and assignment      | x += 2 means the same as x = x + 2 |
| *=       | Multiply and assignment | x *= 2 means the same as x = x * 2 |
| /=       | divide and assignment   | x /= 2 means the same as x = x / 2 |
| %=       | modulo and assignment   | x %= 2 means the same as x = x % 2 |

### 3.3. Exercises

1. Add telemetry to show the right stick of gamepad1.
2. Add telemetry to show whether the b button is pressed on gamepad1
3. Report to the user the difference between the left joystick y and the right joystick y on gamepad1.
4. Report to the user the sum of the left and right triggers on gamepad1.

{30}------------------------------------------------

## 4. Making decisions

### 4.1. If

So far our programs have executed all of their code. Control structures allow you to change which code is executed and even to execute code multiple times.

The `if` statement is the first control structure we'll talk about. Here is an example of a program using it:

Listing 4.1: IfOpMode.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class IfOpMode extends OpMode {
    @Override
    public void init() {
    }

    @Override
    public void loop() {
        if(gamepad1.left_stick_y < 0){
            telemetry.addData("Left stick", " is negative");
        }

        telemetry.addData("Left stick y", gamepad1.left_stick_y);
    }
}
```

Can you figure out what this is doing?

`if` clauses start with `if(conditionalExpression)`. They then have either a single statement or a block of code. A block of code starts with an open curly brace `{`, then it has 0 or more statements, and then a close curly brace `}`.

The code in the block is *only* executed if the conditional expression inside the parenthesis is `true`.

{31}------------------------------------------------

#### 4. Making decisions

![Lightbulb icon indicating a tip or important note.]()![](_page_31_Picture_1.jpeg)

I **strongly** recommend using a block of code instead of a single statement. The reason why is that using a single statement can lead to unexpected errors. For example:

```
if(gamepad1.left_stick_y < 0)
    telemetry.addData("Left stick", " is negative");
    telemetry.addData("Looks like it is part of the if, but
    ↳ it isn't");
```

There are several conditional operators that we can use:

| <b>Operator</b> | <b>Meaning</b>              |
|-----------------|-----------------------------|
| ==              | is equal to                 |
| !=              | is not equal to             |
| <               | is less than                |
| >               | is greater than             |
| <=              | is less than or equal to    |
| >=              | is greater than or equal to |

![Lightbulb icon indicating a tip or important note.]()![](_page_31_Picture_6.jpeg)

A common mistake is trying to test for equality with the assignment operator = instead of the equality operator ==.

Not only can we use conditional operators, we can also use a boolean variable to make the decision. Here is an example:

Listing 4.2: IfOpMode2.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class IfOpMode2 extends OpMode {
8     @Override
9     public void init() {
10     }
11
12     @Override
```

{32}------------------------------------------------

```
13 public void loop() {  
14     if(gamepad1.a){  
15         telemetry.addData("A Button", "pressed");  
16     }  
17 }  
18 }
```

## 4.2. Else

An if statement can have an else clause which handles what should be done if the if expression is false. That sounds confusing, but here is an example:

Listing 4.3: IfElseOpMode.java

```
1 package org.firstinspires.ftc.teamcode;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 @TeleOp()  
7 public class IfElseOpMode extends OpMode {  
8     @Override  
9     public void init() {  
10     }  
11  
12     @Override  
13     public void loop() {  
14         if(gamepad1.left_stick_y < 0){  
15             telemetry.addData("Left stick", " is negative");  
16         }  
17         else{  
18             telemetry.addData("Left stick", " is positive");  
19         }  
20         telemetry.addData("Left stick y", gamepad1.left_stick_y);  
21     }  
22 }  
23 }
```

### 4.2.1. Else if

Since an else statement can have a single statement OR a block of code we can chain them together like this:

Listing 4.4: IfElseIfOpMode.java

```
1 package org.firstinspires.ftc.teamcode;
```

{33}------------------------------------------------

## 4. Making decisions

```
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class IfElseIfOpMode extends OpMode {
8     @Override
9     public void init() {
10     }
11
12     @Override
13     public void loop() {
14         if (gamepad1.left_stick_y < -0.5) {
15             telemetry.addData("Left stick", " is negative and large");
16         }
17         else if (gamepad1.left_stick_y < 0){
18             telemetry.addData("Left stick", " is negative and small");
19         }
20         else if (gamepad1.left_stick_y < 0.5){
21             telemetry.addData("Left stick", " is positive and small");
22         }
23         else {
24             telemetry.addData("Left stick", " is positive and large");
25         }
26         telemetry.addData("Left stick y", gamepad1.left_stick_y);
27     }
28 }
```

## 4.3. Combinations

Sometimes you want to test for more than one thing. For example, you may want to test if a variable is between two numbers. While you can use multiple `if` statements, it is often more convenient and readable to use logical combinations. There are four<sup>1</sup> simple ways that you can combine logical conditions. (and then you can combine these even further)

<sup>1</sup>Technically there are three, but you can use the XOR bitwise operator in a similar manner. Just be careful to make sure you are operating on boolean expressions and not ones that are integers or you'll get unexpected results.

{34}------------------------------------------------

| <b>Operator</b> | <b>Example</b>      | <b>Meaning</b>                                                                                                                                                |
|-----------------|---------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------|
| &&              | (A < 10) && (B > 5) | logical AND (return true if condition A AND condition B are true, otherwise return false.)                                                                    |
|                 | (A < 10)    (B > 5) | logical OR (return true if condition A OR condition B is true, otherwise return false.)                                                                       |
| !               | !(A < 10)           | logical NOT (return true if condition A is false, otherwise return false.)                                                                                    |
| ^               | (A < 10) ^ (B > 5)  | XOR (return true if either A or B is true but if they both are then return false). This is used more rarely than the others but is included for completeness. |

![Lightbulb icon indicating a tip or note.]()![](_page_34_Picture_2.jpeg)

A common mistake is accidentally using the single & instead of && or using the single | instead of ||. The single versions are for doing binary arithmetic operations. That is pretty rare in your Java FTC code so we won't be talking about it in this book.

One thing that might not be obvious is that you can use these to set a value for a boolean variable. So for example:

```
boolean bVar;
bVar = !bVar;
```

When it is declared, bVar will be false. (Since all boolean variables are initialized to false by default.) After the line bVar = !bVar it will be equal to true.

## 4.4. While

A while loop is much like an if statement except for after it is done it goes back to the beginning and checks the conditional again. What if we had the amount the robot had turned, but we wanted its heading (between -180 and 180). We could use code like this:

```
while(angle > 180){
```

{35}------------------------------------------------

#### 4. Making decisions

```
    angle -= 360;
}
while(angle < -180){
    angle += 360;
}
```

The reason it takes two while clauses is because one takes care of the case where we had turned more than 180 degrees in the positive direction, and the other takes care of the case where we had turned more than 180 degrees in the negative direction.<sup>2</sup>

You might be tempted to write code like

![Lightbulb icon indicating a tip or suggestion.]()![](_page_35_Picture_4.jpeg)

```
while(gamepad1.a){
    // do something
}
```

That code won't work in an OpMode because gamepad1 is only updated between calls to loop()

There is also a do...while loop which executes once regardless and checks the condition at the end instead of the beginning. This is pretty rare in Java FTC code but is included here for completeness. A quick example:

```
do{
    // code goes here
    a++;
}while(a < 10)
```

#### 4.5. For

There are two types of for loops. The traditional type looks like many programming languages, for(start; conditional; update) The start is executed once before we begin, the conditional is checked every time before we execute, the end is done at the end of EVERY time through.

```
for(int i = 0; i < 4; i++){
    // This code will happen 4 times
}
```

<sup>2</sup>If we were doing this for real, we would do it in radians. But we used degrees here to make the concept simpler.

{36}------------------------------------------------

This is often used, but in many cases it is to go through an array and you are better off using a for-each that we'll talk about when we talk about arrays in chapter 13.

## 4.6. Exercises

1. Make a “turbo button”. When `gamepad1.a` is not pressed, multiply the joystick by 0.5 and when it is pressed multiply by 1 and report to the user as Forward Speed.
2. Make a “crazy mode”. When `gamepad1.a` is pressed, report X as Y and Y as X. When it isn't pressed, report the joystick as normal....

{37}------------------------------------------------

![](_page_37_Picture_0.jpeg)

{38}------------------------------------------------

## 5. Class Members and Methods

A class is a model of something. It can contain data (members) and functions (methods). Whenever you create a class, it becomes a data type that people can make variables of that type. You can think of a class like a blueprint that can be used to make any number of identical things. (called “objects”) For example, the String data type is a class but we can have multiple objects of type String in our programs. Remember that we name classes starting with a Capital letter and then every other word in the class name is also capitalized.<sup>1</sup>

### 5.1. Class Members

So far, we have had variables in our methods but we can also have them belong to our class. To have them belong to our class, they need to be within the class body but outside of every method body. By convention, they are at the beginning of the class but they don't have to be. If they are in our class, then every method in our class can use them and when they get changed everyone sees the new value. However, every object (copy) has its own member variables<sup>2</sup>

Listing 5.1: ClassMemberOpMode.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class ClassMemberOpMode extends OpMode {
8     boolean initDone;
9
10    @Override
11    public void init() {
12        telemetry.addData("init Done", initDone);
13        initDone = true;
14    }

```

<sup>1</sup>Remember this is called Pascal Case.

<sup>2</sup>unless they are declared static which means they are shared between all objects of the class. We'll talk about this in section 5.5.

{39}------------------------------------------------

## 5. Class Members and Methods

```
15 @Override
16 public void loop() {
17     telemetry.addData("init Done", initDone);
18 }
19 }
20 }
```

Even though `initDone` gets updated in `init()`, nothing sends it to the driver station until `loop()` gets called for the first time.

You can use the `this` keyword to unambiguously say you are referring to the class member, but if there isn't a variable with the same name in your method then you can leave it off. That would look like `this.initDone`.

## 5.2. Class Methods

We can create new methods. A method has a return type (which is any data type), a name, and can take 0 or more parameters. A parameter is a way you can pass information into a method. Each parameter has a data type and a name. Inside the method, it is just like you had a variable defined inside the method with that data type and name. (but it received its value from whomever called the class method.)

By convention we name methods starting with a lowercase letter and then having each additional word in the name start with an uppercase letter<sup>3</sup> After its parameters, there is the method body which goes from the opening curly bracket { to the close curly bracket }.

Listing 5.2: `ClassMethodOpMode.java`

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class ClassMethodOpMode extends OpMode {
8
9     @Override
10    public void init() {
11    }
12
13    double squareInputWithSign(double input){
14        double output = input * input;
15        if(input < 0){
```

<sup>3</sup>Remember this is called camelCase

{40}------------------------------------------------

```
16 output = output * -1;
17 }
18 return output;
19 }
20
21 @Override
22 public void loop() {
23 double leftAmount = gamepad1.left_stick_x;
24 double fwdAmount = -gamepad1.left_stick_y;
25
26 telemetry.addData("Before X", leftAmount);
27 telemetry.addData("Before Y", fwdAmount);
28
29 leftAmount = squareInputWithSign(leftAmount);
30 fwdAmount = squareInputWithSign(fwdAmount);
31
32 telemetry.addData("After X", leftAmount);
33 telemetry.addData("After Y", fwdAmount);
34 }
35 }
```

### 5.2.1. Return Types

The return type is simply the data type in front of the name. You can also say that a method doesn't return anything. In that case, instead of the data type you put the keyword `void` before the name. To return the value you use the return statement. It is simply `return <value>;` You can return a variable or a constant (typed in number, string, etc.) You can see this done in the example above. As soon as the return keyword is executed the method returns to whomever called it.

If you have a class method that returns `void`, then you can either have a return with nothing after it like `return;` or you can omit the return statement and it will return at the end of the code.

### 5.2.2. Parameters

You probably noticed that the name had an open parenthesis ( after it. Then each parameter is listed like a variable (except no default assignment allowed). If there is more than one parameter, they are separated by a comma, Then at the end of the parameters is a close parenthesis )

So some examples of methods:

```
// returnType name(parameters)
double squareInputWithSign(double input){
```

{41}------------------------------------------------

## 5. Class Members and Methods

```
double output = input * input;
if(input < 0){
    output = output * -1;
}
return output;

void setMotorSpeed(double speed){
    motor.set(speed);
}

boolean isSensorPressed(){
    return touchSensor.isPressed();
}

double min(double x, double y){
    if(x < y){
        return x;
    }
    return y;
}
```

The `min` example may have surprised you because there isn't an `else`. There isn't any need because if `x < y`, then it will return out of the method. So the only way it will get to the `return y`; statement is if `x >= y`. So you could write it with an `else`, but that isn't necessary.

### 5.2.3. Special Methods: Constructors

A constructor is a special method in a Java class that has the same name as the class and it has no return type. It gets called whenever the class is initialized. (created). In Java you can have multiple constructors where each one has different parameters.

An example:

```
public class Point{
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
```

In this case, we had to use the `this` keyword because the class member is named the same as the parameter. Sometimes people will change the parameter name instead - like this:

```
public class Point{
```

{42}------------------------------------------------

```
int x;
int y;

public Point(int x_in, int y_in){
    x = x_in;
    y = y_in;
}
```

Or, people that are coming from other languages will sometimes start all class members with `m_` so it looks like this:

```
public class Point{
    int m_x;
    int m_y;

    public Point(int x, int y){
        m_x = x;
        m_y = y;
    }
}
```

Personally, I prefer the first option, but it is a preference. All three are legal options and will do the same thing.

#### 5.2.4. Another special method: `toString`

All objects in Java have a method called `toString()` This is used whenever we convert to a string (like when we send to `telemetry.addData`) The default has the name of the class and its hash code (typically NOT useful.) This makes it easier to debug when there are problems by showing what is inside the class.

So using our Point class example from above:

```
public class Point{
    int x;
    int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "Point " + x + " " + y;
    }
}
```

{43}------------------------------------------------

## 5. Class Members and Methods

You might be wondering why we use `@Override` when we are not extending another class. It turns out in Java that all classes extend the base class `Object`.

We are adding strings and numbers together here which may seem strange. The `String` class redefines (overloads) the `+` operator to mean concatenate (join) two strings together. It also overloads `+=` to concatenate and then assign the resultant string.<sup>4</sup> If it comes across something that isn't a string, it calls its `toString()` method which works (mostly) as you would expect for primitive types.

### 5.3. Controlling access- Keep your private things private

You can also modify all class methods and members with an access modifier. (that is who can access it.) By default, members and methods are all package-private. That means that only that class and other classes in the same package (directory) can see them. The options are: (from most to least restrictive)

- `private` - It can only be seen with the class. It cannot be accessed from outside the class.
- (default - none specified) - only that class and other classes in the same package (directory) can see them
- `protected` - It can only be seen with the class, its children, and other classes in the same package (We'll talk about children in [chapter 14](#))
- `public` - It can be seen from everywhere. (You have seen this on `init()` and `loop()` in your OpModes)

In general, you want to be as restrictive as makes sense. If you are modifying the access, it goes first.

### 5.4. Creating your own classes

Hopefully you have been following along, so you are a pro at making your own OpMode classes by now. We start the same (remember [section 1.3](#))

1. Right click on `org.firstinspires.ftc.teamcode`
2. Select New > Java Class

<sup>4</sup>No, in Java you can't overload operators in your own classes.

{44}------------------------------------------------

But in this case we are going to name it RobotLocation and it will have no Superclass so in Android Studio 3.x make sure the superclass is blank. (In Android Studio 4.x there is no place to put in superclass)

Listing 5.3: RobotLocation.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 public class RobotLocation{
4     double angle;
5
6     public RobotLocation(double angle){
7         this.angle = angle;
8     }
9
10    public double getHeading(){
11        double angle = this.angle;
12        while(angle > 180){
13            angle -= 360;
14        }
15        while(angle < -180){
16            angle += 360;
17        }
18        return angle;
19    }
20
21    @Override
22    public String toString(){
23        return "RobotLocation: angle (" + angle + ")";
24    }
25
26    public void turn(double angleChange){
27        angle += angleChange;
28    }
29    public void setAngle(double angle){
30        this.angle = angle;
31    }
32}
```

Let's talk about what makes up this class.

```
4 double angle;
```

Here is an example of the class member we talked about in section 5.1 Since it doesn't have an access modifier, it is default which means it is only available to this class and other classes in the same package.

```
6 public RobotLocation(double angle){
7     this.angle = angle;
```

{45}------------------------------------------------

## 5. Class Members and Methods

8

```
}
```

This is an example of a constructor like we talked about in subsection 5.2.3. You can tell a constructor because it has no return type and it has the same name as the class. Constructors typically have the public access modifier so a class can be created using it from anywhere. You'll notice that it assigns a value to the the class member. It uses the this keyword so that we can have the parameter named the same thing.

10

```
public double getHeading(){  
    double angle = this.angle;  
    while(angle > 180){  
        angle -= 360;  
    }  
    while(angle < -180){  
        angle += 360;  
    }  
    return angle;  
}
```

This is a public class method that returns the heading (so it needs to be within -180 and 180). This would be a great place for a comment describing the method. We left comments out of most source in the book since the text of the book comments them.

21

```
@Override  
public String toString(){  
    return "RobotLocation: angle (" + angle + ")";  
}
```

This is the special method toString() that we talked about in subsection 5.2.4.

26

```
public void turn(double angleChange){  
    angle += angleChange;  
}
```

This is a public class method where we can specify how much the robot is turning. You'll notice that since the parameter is not the same as the class member we are using that we don't have to use the this keyword for the class member. You'll also notice that we use the add and assign operator += as a shortcut.

29

```
public void setAngle(double angle){  
    this.angle = angle;  
}
```

{46}------------------------------------------------

Here is another public class method where we can set the angle.

You might have noticed that there is no way to get the angle out. (We can only get out the heading). We could absolutely add this method if we needed it.

Sometimes you'll see programmers take the lazy way out and make class members public so they don't have to write "setter" or "getter" methods (also called accessor methods). The problem with that is that it makes it hard for you to change the internals later without affecting other parts of your code.

For example, if you wanted to change it to keep things in radians internally:

Listing 5.4: RobotLocationRadians.java

```
package org.firstinspires.ftc.teamcode;

public class RobotLocationRadians {
    double angleRadians;

    public RobotLocationRadians(double angleDegrees) {
        this.angleRadians = Math.toRadians(angleDegrees);
    }

    public double getHeading() {
        double angle = this.angleRadians;
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        return Math.toDegrees(angle);
    }

    @Override
    public String toString() {
        return "RobotLocationRadians: angle (" + angleRadians + ")";
    }

    public void turn(double angleChangeDegrees) {
        angleRadians += Math.toRadians(angleChangeDegrees);
    }

    public void setAngle(double angleDegrees) {
        this.angleRadians = Math.toRadians(angleDegrees);
    }
}
```

I used some methods in the Math class so I wouldn't have to write the rou-

{47}------------------------------------------------

## 5. Class Members and Methods

tines to convert from Degrees to Radians and back. We talk about the Math class in section 19.1.<sup>5</sup>

You'll notice that the way your class is used doesn't have to change (I only renamed it so I could keep them in the same package. In practice, you wouldn't even rename your class.)

![Lightbulb icon indicating a tip or note.]()![](_page_47_Picture_3.jpeg)

Laziness is no longer a good excuse in Android Studio because you can right click on a member variable, select "Generate..." and choose getter and setter and Android Studio will make these methods for you!

It is interesting that we made our own class, but to be useful we need an OpMode that uses it.

Listing 5.5: UseRobotLocationOpMode.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp
7 public class UseRobotLocationOpMode extends OpMode {
8     RobotLocation robotLocation = new RobotLocation(0);
9
10    @Override
11    public void init() {
12        robotLocation.setAngle(0);
13    }
14
15    @Override
16    public void loop() {
17        if(gamepad1.a){
18            robotLocation.turn(0.1);
19        }
20        else if(gamepad1.b){
21            robotLocation.turn(-0.1);
22        }
23        telemetry.addData("Location", robotLocation);
24        telemetry.addData("Heading", robotLocation.getHeading());
25    }
26 }
```

This is the OpMode that uses our new class. The first 7 lines are the same so we'll start after that.

<sup>5</sup>In general, using a routine in a library is better than writing it yourself.

{48}------------------------------------------------

```
8 RobotLocation robotLocation = new RobotLocation(0);
```

This is a data member in our OpMode. You'll notice that it uses the `new` keyword. We use this whenever we are creating an instance of a class (or object). The `new` keyword tells the compiler to reserve room for it and call the constructor that matches the parameters you gave it. (type only, the names are ignored) Also by convention variables start with a lower case letter while the class starts with an upper case letter. They don't have to be named the same but often are.

```
10 @Override
11 public void init() {
12     robotLocation.setAngle(0);
13 }
```

Inside our `init()` method, we call the `setAngle()` method of the `robotLocation` object. The reason we call `setAngle()` here is in case we select the `opMode`, `init` it, run it and then stop and press `init` again. If we don't set it in `init()` then it will keep its value from the last time it was modified.

![Lightbulb icon indicating a tip or best practice.]()![](_page_48_Picture_5.jpeg)

As a best practice for FTC, your `init()` method should set things back to their expected default state.

```
15 @Override
16 public void loop() {
17     if(gamepad1.a) {
18         robotLocation.turn(0.1);
19     }
20     else if(gamepad1.b) {
21         robotLocation.turn(-0.1);
22     }
}
```

Obviously this doesn't turn the robot (because we don't have any motors hooked up), so perhaps `turn()` was an unfortunate naming choice. Run this and you'll get a feel for how fast `loop()` is called. Also, we don't allow the user to turn positively and negatively at the same time (since that makes no sense). Since it looks at `gamepad1.a` first, if they are both pressed then it will turn positively.

## 5.5. static

The `static` keyword means that it belongs to the type instead of the object. This can be used for methods (but then they can't access any non-static members

{49}------------------------------------------------

## 5. Class Members and Methods

or methods) or for class members.

For class members, it is used typically for constants when you want all instances to share it.

For methods, it is often used when you want to let someone call a method and they don't need to have an object of that type first.

### 5.6. Exercises

1. Add a double getAngle() method to RobotLocation and then display it in your opMode.
2. This exercise has two parts.
   1. Add a member of type double called x to your RobotLocation and add double getX(), void changeX(double change), and setX(double x) methods.
   2. Change the OpMode to have robotLocation.changeX(-0.1) called when gamepad1.dpad\_left is pressed and robotLocation.changeX(0.1) when gamepad1.dpad\_right is pressed
3. After you have done exercise 2, also add in support for y. Use gamepad1.dpad\_up for robotLocation.changeY(0.1) and gamepad1.dpad\_down for robotLocation.changeY(-0.1)

{50}------------------------------------------------

## 6. Our first hardware

Until this point, we have been in pure software that hasn't used any of our hardware. That is fine, but our robot will be pretty boring without any sensors, motors, or servos. This (and following chapters) assume you have a programming board setup like in [Appendix A](#)

### 6.1. Configuration file

This will feel like a lot of steps the first time, but soon it'll become very natural to run through them.

1. From either the Driver Station or the Robot Controller - select the three dots in the upper right
2. Select New in the upper left
3. After you press new, it should find your expansion hub. If it doesn't, please make sure your USB cable is connected between the phone and the expansion hub. (The letters and digits of your expansion hub will be unique to your hub.)
4. Press on "Expansion Hub Portal 1"

![Screenshots illustrating the configuration file setup process. The first screenshot shows the Driver Station/Robot Controller menu with three dots. The second screenshot shows the 'New Configuration' screen with a prompt to create a new configuration. The third screenshot shows the 'Configure from Template' screen listing 'Expansion Hub Portal 1'.]()![](_page_50_Picture_5.jpeg)

{51}------------------------------------------------

## 6. Our first hardware

1. While you can rename it from “Expansion Hub Portal 1”, I don’t see any reason to. You will see each expansion hub that is plugged in. If you only have 1, it should say “Expansion Hub 2”. Press on it.
2. This will give you a listing of all of the areas where you can have communication from your REV expansion hub. Press on “Digital Devices”
3. On Port 1, Change to “Digital Channel” (Earlier versions of the FTC SDK allowed “REV Touch Sensor” to also work as a Digital Channel, but newer ones have these different)
4. Change its name to be “touch\_sensor”
5. Press Done in the upper left (going up to Expansion Hub 2)
6. Press Done again (going up to Expansion Hub Portal 1)
7. Press Done again (going up to top level)
8. Press Save

13. Change name to “programming\_board”

![Screenshot showing the initial configuration screen for Expansion Hub Portal 1, listing Expansion Hub 2.]()![](_page_51_Picture_3.jpeg)

![Screenshot showing the configuration screen for Expansion Hub 2, listing Digital Devices, I2C Bus 0, I2C Bus 1, I2C Bus 2, and I2C Bus 3.]()![](_page_51_Picture_4.jpeg)

![Screenshot showing the configuration screen for Port 1, where the device is changed to 'REV Touch Sensor' and renamed to 'touch_sensor'.]()![](_page_51_Picture_5.jpeg)

![Screenshot showing the Save Configuration dialog, prompting for a name for the robot configuration, currently set to 'programming_board'.]()![](_page_51_Picture_6.jpeg)

{52}------------------------------------------------

1. Press OK
2. Press Activate under “programming\_board” The upper right should now say “programming\_board”
3. Press the left pointing arrow on the bottom. This will restart the robot
4. On the Driver Station, you should see “programming\_board” under the image of a robot.

## 6.2. Mechanisms

Until this point we have had everything in one package. At this point, we are going to split things into two packages. One will hold our mechanisms (For this book, we have one mechanism called the ProgrammingBoard.<sup>1</sup> On our real robot we would likely have multiple mechanisms.) The other will hold our opModes.

So there are now two classes:

This one is in the mechanisms package. To create a package, right click in the same place that we have to make a new class, but select new package and type in “mechanisms”.

That will make the package. Then right click on the package and select new class. This one should be “ProgrammingBoard1” and it should have no superclass.

Listing 6.1: ProgrammingBoard1.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;
2
3 import com.qualcomm.robotcore.hardware.DigitalChannel;
4 import com.qualcomm.robotcore.hardware.HardwareMap;
5
6 public class ProgrammingBoard1 {
7     private DigitalChannel touchSensor;
8
9     public void init(HardwareMap hwMap) {
10         touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
11         touchSensor.setMode(DigitalChannel.Mode.INPUT);
12     }
13 }
```

<sup>1</sup>To make writing the book easier, I added a number at the end so I could keep them all in one package. Normally you would just add things to your existing class instead of making a new one.

{53}------------------------------------------------

## 6. Our first hardware

```
14 public boolean getTouchSensorState() {  
15     return touchSensor.getState();  
16 }  
17 }
```

Line 1 should be put in for you by Android Studio.

Lines 3 & 4 will be put in as you type items.

Line 6 should start out that way as you create the class

```
7 private DigitalChannel touchSensor;
```

This line says that we have a class member of type DigitalChannel with a name of touchSensor. DigitalChannel comes from the FTC SDK. We'll talk about how to navigate the SDK to find out what is there in [chapter 18](#). This needs to be a class member since it is set in `init()` and used in other methods. We set it to `private` to make sure only our class can interact directly with it. This is a good practice for all hardware. Normally you would want to name it with what the sensor does (like `armInPositionTouchSensor`, but since this is part of a programming board it doesn't have more of a purpose than being a Touch Sensor.

```
9 public void init(HardwareMap hwMap) {
```

We have an `init()` method. We could have called it anything, but since we'll call it from our `init()` in our OpMode it seemed reasonable. While it might be tempting to make this the constructor, that limits what we can and can't do, so it is easier to follow the same structure. You'll notice that this takes one parameter of type `HardwareMap` and it is called `hwMap`. We could have called it `hardwareMap` but I am lazy so I took a shortcut. `HardwareMap` also comes from the FTC SDK and it is how our programs get information from the configuration file on the robot.

```
10 touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
```

This assigns to the variable `touchSensor` the hardware that is in the configuration file of type `DigitalChannel.class` and with a name of `touch_sensor`. This name has to match **EXACTLY** what is in the configuration file. It may seem strange to you that you don't have to use `new` here. That is because the `get()` method of `HardwareMap` does it for you.

```
11 touchSensor.setMode(DigitalChannel.Mode.INPUT);
```

It turns out that you can set each `DigitalChannel` as either `INPUT` or `OUTPUT`. Since we are reading from the touch sensor, we need to set it as an `INPUT`.

{54}------------------------------------------------

```
14 public boolean getTouchSensorState() {  
15     return touchSensor.getState();  
16 }
```

We create a class method so that those outside of our class can read the state of the touchSensor. This is better than making touchSensor public because nobody can change how it is configured.

## 6.3. OpMode

This one is in the opmodes package

Listing 6.2: TouchSensorOpMode.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard1;  
7  
8 @TeleOp()  
9 public class TouchSensorOpMode extends OpMode {  
10     ProgrammingBoard1 board = new ProgrammingBoard1();  
11     @Override  
12     public void init() {  
13         board.init(hardwareMap);  
14     }  
15  
16     @Override  
17     public void loop() {  
18         telemetry.addData("Touch sensor", board.getTouchSensorState());  
19     }  
20 }
```

The first few lines of this should look amazingly familiar by now.

```
10 ProgrammingBoard1 board = new ProgrammingBoard1();
```

Here we create a class member of type ProgrammingBoard1 named board and we set it equal to a new instance of ProgrammingBoard1 It has to be a class member so all of our methods can access it.

```
12 public void init() {  
13     board.init(hardwareMap);  
14 }
```

{55}------------------------------------------------

## 6. Our first hardware

Our init is very clean. It only calls the init of our board object. The variable hardwareMap is part of the OpMode and it is how we see how the robot is configured.

```
17 public void loop() {  
18     telemetry.addData("Touch sensor", board.getTouchSensorState());  
19 }
```

For the loop all we do is send to the telemetry the state of the touch sensor.

## 6.4. Making changes

One of the huge advantages of splitting things out is that we can isolate hardware “weirdness”. For example, you were probably surprised that pushing in the touch sensor returns false and it not pushed in was true. So let’s change that.

First, we’ll change our ProgrammingBoard class. The easiest way to do this is right click on the file, select copy. Then select paste and give it the new file name. Then you can just make the changes instead of typing everything in again.

Listing 6.3: ProgrammingBoard2.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;  
2  
3 import com.qualcomm.robotcore.hardware.DigitalChannel;  
4 import com.qualcomm.robotcore.hardware.HardwareMap;  
5  
6 public class ProgrammingBoard2 {  
7     private DigitalChannel touchSensor;  
8  
9     public void init(HardwareMap hwMap) {  
10         touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");  
11         touchSensor.setMode(DigitalChannel.Mode.INPUT);  
12     }  
13  
14     public boolean isTouchSensorPressed() {  
15         return !touchSensor.getState();  
16     }  
17 }
```

While we could have done code like:

```
public boolean isTouchSensorPressed(){  
    if(!touchSensor.getState()){ // if state is false, touch sensor is pressed  
        return true;  
    }
```

{56}------------------------------------------------

```
    }
    return false;
}
```

It turns out that doing it in one line does exactly the same thing. Also, since we changed the name of the method, we have to change it in the OpMode as well.

![Lightbulb icon indicating a tip or important note.]()![](_page_56_Picture_3.jpeg)

If we right click on a class method (or class member) name and Refactor->Rename in Android Studio then it will magically change it both in its declaration and everywhere it is called.

Listing 6.4: TouchSensorOpMode2.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard2;

@TeleOp()
public class TouchSensorOpMode2 extends OpMode {
    ProgrammingBoard2 board = new ProgrammingBoard2();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Touch pressed", board.isTouchSensorPressed());
    }
}
```

## 6.5. Exercises

1. Add a method `isTouchSensorReleased()` to the `ProgrammingBoard2` class and use it in your opMode
2. Have your opMode send “Pressed” and “Not Pressed” for the “Touch sensor” instead of `true` or `false`. There are lots of ways to do this.

{57}------------------------------------------------

![](_page_57_Picture_0.jpeg)

{58}------------------------------------------------

## 7. Motors

It is great that we have a sensor, but it is time to make things move!!

### 7.1. Editing Configuration File

1. From either the Driver Station or the Robot Controller - select the three dots in the upper right
2. Press edit under the “programming\_board” config that we made earlier
3. Press on “Expansion Hub Portal 1”
4. While you can rename it from “Expansion Hub Portal 1”, I don’t see any reason to. You will see each expansion hub that is plugged in. If you only have 1, it should say “Expansion Hub 2”. Press on it.

![Screenshots illustrating the steps for editing the configuration file. The first screenshot shows the three dots menu. The second shows the 'programming_board' configuration list with Edit, Activate, and Delete buttons. The third shows the configuration details for Expansion Hub Portal 1. The fourth shows the configuration details for Expansion Hub 2.]()![](_page_58_Picture_4.jpeg)

{59}------------------------------------------------

## 7. Motors

![Two screenshots showing the Android configuration interface for the Expansion Hub 2. The first screenshot shows the main menu with 'Motors' selected. The second screenshot shows the Motor configuration screen where Port 0 is set to 'Rev Robotics 40:1 HD Hex' and the motor name is set to 'motor'.]()![](_page_59_Picture_1.jpeg)

1. This will give you a listing of all of the areas where you can have communication from your REV expansion hub. Press on “Motors”
2. On Port 0, Change to “Rev Robotics 40:1 HD Hex Motor”
3. Change its name to be “motor”
4. Press Done in the upper left (going up to Expansion Hub 2)
5. Press Done again (going up to Expansion Hub Portal 1)
6. Press Done again (going up to top level)
7. Press Save
8. Press OK
9. Press Activate under “programming\_board” The upper right should now say “programming\_board”
10. Press the left pointing arrow on the bottom. This will restart the robot
11. On the Driver Station, you should see “programming\_board” under the image of a robot.

## 7.2. Mechanisms

Listing 7.1: ProgrammingBoard3.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;
2
3 import com.qualcomm.robotcore.hardware.DcMotor;
4 import com.qualcomm.robotcore.hardware.DigitalChannel;
5 import com.qualcomm.robotcore.hardware.HardwareMap;
```

{60}------------------------------------------------

```
public class ProgrammingBoard3 {  
    private DigitalChannel touchSensor;  
    private DcMotor motor;  
  
    public void init(HardwareMap hwMap) {  
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");  
        touchSensor.setMode(DigitalChannel.Mode.INPUT);  
        motor = hwMap.get(DcMotor.class, "motor");  
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  
    }  
  
    public boolean isTouchSensorPressed() {  
        return !touchSensor.getState();  
    }  
  
    public void setMotorSpeed(double speed){  
        motor.setPower(speed);  
    }  
}
```

Most of this should look the same as our last file, so we'll just talk about the changes

```
9 private DcMotor motor;
```

Here we are adding a variable of type DcMotor with name motor. Normally you would want to name the motor with what it does, but since this is part of a programming board - we'll just call in motor. DcMotor comes from the FTC SDK.

```
14 motor = hwMap.get(DcMotor.class, "motor");
```

This assigns to the variable motor the hardware that is in the configuration file of type DcMotor.class and with a name of motor. This name has to match **EXACTLY** what is in the configuration file.

```
15 motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
```

This sets how we want to use the motor. The choices are:

{61}------------------------------------------------

## 7. Motors

| <b>RunMode</b>         | <b>Meaning</b>                                                                                                                                                                                                                               |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| RUN_TO_POSITION        | The motor is to attempt to rotate in whatever direction is necessary to cause the encoder reading to advance or retreat from its current setting to the setting which has been provided through the <code>setTargetPosition()</code> method. |
| RUN_USING_ENCODER      | The motor is to do its best to run at targeted velocity.                                                                                                                                                                                     |
| RUN_WITHOUT_ENCODER    | The motor is simply to run at whatever velocity is achieved by applying a particular power level to the motor.                                                                                                                               |
| STOP_AND_RESET_ENCODER | The motor is to set the current encoder position to zero.                                                                                                                                                                                    |

We set it here to `DcMotor.RunMode.RUN_USING_ENCODER` which means that it uses the encoder on the motor so that we are setting a speed and it figures out how to modify power to get to that speed (if possible). We like this mode because if you set two motors to the same speed then they have a better chance at being at the same speed than in any other mode. (We have met teams that don't even plug in the encoders and they are having weird problems with the robot not driving straight.)

![Lightbulb icon indicating a tip or note.]()![](_page_61_Picture_3.jpeg)

While `RUN_TO_POSITION` can be very handy for single motors, we recommend AGAINST using it in a drive train because the different speeds for the different wheels trying to get to a position can cause wacky side effects. This is because each motor is trying to get to its position regardless of the other motors. So you can have a robot “wiggle”. In a perfect world without friction this would work the same. However, we don't live in that world. :-)

```
21 public void setMotorSpeed(double speed){  
22     motor.setPower(speed);  
23 }
```

This is a class method so that code outside our class can set the speed of the motor. This is better than exposing the `motor` as public because people can't accidentally change configuration. `setPower()` on a motor takes a `double` between `-1.0` and `1.0`. `-1.0` is full speed “backwards”, `0.0` is stopped, and `1.0` is full speed “forwards”.

{62}------------------------------------------------

## 7.3. OpMode

This one is in the opmodes package

Listing 7.2: MotorOpMode.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard4;

@TeleOp()
public class MotorOpMode extends OpMode {
    ProgrammingBoard4 board = new ProgrammingBoard4();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        board.setMotorSpeed(0.5);
    }
}
```

This has very little that is new, so we'll only talk about that.

```
public void loop() {
    board.setMotorSpeed(0.5);
}
```

Here we don't do anything conditional. We just set the motor to a speed of 0.5 (half way forwards) Technically we could have had a start() method that did this but since we have to have a loop() in our OpMode anyway, we went for the simple. Yes, it will tell the motor to go to the same speed over and over. It doesn't matter.

## 7.4. Motor as Sensor

The motor also has a rotation sensor built into it. We are using it when we say RUN\_USING\_ENCODER, but we can also read it and use it in our code. It'll need a method in the ProgrammingBoard class so we can read it.

Listing 7.3: ProgrammingBoard4.java

{63}------------------------------------------------

## 7. Motors

```
package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ProgrammingBoard4 {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;

    public void init(HardwareMap hwMap) {
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = motor.getMotorType().getTicksPerRev();
    }

    public boolean isTouchSensorPressed() {
        return !touchSensor.getState();
    }

    public void setMotorSpeed(double speed){
        motor.setPower(speed);
    }

    public double getMotorRotations(){
        return motor.getCurrentPosition() / ticksPerRotation;
    }
}
```

Most of this is the same so we'll just talk about the differences

```
private double ticksPerRotation;
```

This is a member variable where we will store the number of encoder ticks per rotation. We do this to make things easier for the opModes.

```
ticksPerRotation = motor.getMotorType().getTicksPerRev();
```

If we set the exact motor we have in the configuration, then we can do this to get the number of ticks per rev (revolution). I prefer to call them rotation since our students come from FLL teams where they are more used to that terminology. If you have additional gear changes after the motor, you'll have to calculate this. For example if you have a 2:1 gear reduction then you would simply multiply the number of ticks per rev at the motor by 2 to get the number of revolutions of your mechanism.

```
public double getMotorRotations(){
```

{64}------------------------------------------------

```
27 return motor.getCurrentPosition() / ticksPerRotation;
28 }
```

This is a class method where we return the number of motor rotations. To get the number of rotations from the number of encoder ticks, we simply divide the number of ticks by the number of ticks in a rotation. One nice thing about Java is that if there is math between an int and a double, the result will be a double. (However, be warned that dividing an int by an int always gives an int result even if it doesn't divide equally. So for example 5 / 2 will be 2.)

Listing 7.4: MotorOpMode2.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard4;
7
8 @TeleOp()
9 public class MotorOpMode2 extends OpMode {
10     ProgrammingBoard4 board = new ProgrammingBoard4();
11     @Override
12     public void init() {
13         board.init(hardwareMap);
14     }
15
16     @Override
17     public void loop() {
18         board.setMotorSpeed(0.5);
19         telemetry.addData("Motor rotations", board.getMotorRotations());
20     }
21 }
```

This only has one line added from before

```
19 telemetry.addData("Motor rotations", board.getMotorRotations());
```

Here we are simply sending to telemetry what we are seeing from the motor rotations.

![Lightbulb icon indicating a tip or note.]()![](_page_64_Picture_8.jpeg)

If your encoder counts are not going up when you are sending a positive speed to your motor, you probably have the power wires flipped going to the motor.

{65}------------------------------------------------

## 7. Motors

### 7.5. Motors and Sensors together

We don't need to make any change to our configuration file or our ProgrammingBoard file since they already have a motor and a sensor.

Listing 7.5: MotorSensorOpMode.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard4;

@TeleOp()
public class MotorSensorOpMode extends OpMode {
    ProgrammingBoard4 board = new ProgrammingBoard4();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(board.isTouchSensorPressed()) {
            board.setMotorSpeed(0.5);
        }
        else{
            board.setMotorSpeed(0.0);
        }
        telemetry.addData("Motor rotations", board.getMotorRotations());
    }
}
```

Remember that setting the motor speed to 0 makes it stop. You can set for each motor what you would like it to do when set to zero by calling `setZeroBehavior()` with either `DcMotor.ZeroPowerBehavior.BRAKE` or `DcMotor.ZeroPowerBehavior.FLOAT`

So in this case, when the touch sensor is pressed we move the motor “forward” at half speed. When it isn't, we stop it.

You may end up in a circumstance where you want “forward” to be the opposite direction of clockwise. (Like on the left hand side of your drive train). To do this, you simply call the motor's method `setDirection()` with `DcMotorSimple.Direction.REVERSE` and if you want to change it back you call it with `DcMotorSimple.Direction.FORWARD`. The motor remembers these settings.

So you might make your ProgrammingBoard class `init()` method look like

{66}------------------------------------------------

this:

```
...  
motor = hwMap.get(DcMotor.class, "motor");  
motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  
motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);  
motor.setDirection(DcMotorSimple.Direction.REVERSE);  
ticksPerRotation = motor.getMotorType().getTicksPerRev();  
...
```

## 7.6. Motors and Gamepads

And of course, we can use our Gamepad just like a sensor. (we are sensing what the human is doing.)

Listing 7.6: MotorGamepadOpMode.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard4;  
7  
8 @TeleOp()  
9 public class MotorGamepadOpMode extends OpMode {  
10     ProgrammingBoard4 board = new ProgrammingBoard4();  
11     @Override  
12     public void init() {  
13         board.init(hardwareMap);  
14     }  
15  
16     @Override  
17     public void loop() {  
18         if(gamepad1.a) {  
19             board.setMotorSpeed(0.5);  
20         }  
21         else{  
22             board.setMotorSpeed(0.0);  
23         }  
24         telemetry.addData("Motor rotations", board.getMotorRotations());  
25     }  
26 }
```

This is exactly the same as before except for using gamepad1.a instead of the touch sensor.

{67}------------------------------------------------

## 7. Motors

But we don't have to be limited to just the buttons. We can make it finer controlled by using an analog input from the gamepad

Listing 7.7: MotorGamepadOpMode2.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard4;

@TeleOp()
public class MotorGamepadOpMode2 extends OpMode {
    ProgrammingBoard4 board = new ProgrammingBoard4();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        double motorSpeed = gamepad1.left_stick_y;

        board.setMotorSpeed(motorSpeed);

        telemetry.addData("Motor speed", motorSpeed);
        telemetry.addData("Motor rotations", board.getMotorRotations());
    }
}
```

Yes, we could have used `gamepad1.left_stick_y` twice instead of making a `motorSpeed` variable. But I prefer to do it this way in case I want to do any math on the `motorSpeed` before using it.

## 7.7. Exercises

1. Add a method to the `ProgrammingBoard` that allows you to change the `ZeroPowerBehavior` of the motor, and then add to your `OpMode` where pressing `gamepad1.a` sets it to `BRAKE` and `gamepad1.b` sets it to `FLOAT`.
2. Make the joystick less sensitive in the middle without losing range by bringing in the `squareInputWithSign()` method from [section 5.2](#) into your `opMode` and using it.

{68}------------------------------------------------

## 8. Servos

### 8.1. Configuration File

Follow steps 1-5 of [section 7.1](#), but select Servos

1. On Port 0, Change to “Servo”
2. Change its name to be “servo”

Continue with steps 8 and on of [section 7.1](#)

![Screenshot of the Robot Configuration dialog showing Port 0 set to Servo, named 'servo'.]()![](_page_68_Picture_5.jpeg)

### 8.2. Mechanisms

Let's start with what we need to do to our ProgrammingBoard class.

Listing 8.1: ProgrammingBoard5.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;
2
3 import com.qualcomm.robotcore.hardware.DcMotor;
4 import com.qualcomm.robotcore.hardware.DigitalChannel;
5 import com.qualcomm.robotcore.hardware.HardwareMap;
6 import com.qualcomm.robotcore.hardware.Servo;
7
8 public class ProgrammingBoard5 {
9     private DigitalChannel touchSensor;
10    private DcMotor motor;
11    private double ticksPerRotation;
12    private Servo servo;
13
14    public void init(HardwareMap hwMap) {
15        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
16        touchSensor.setMode(DigitalChannel.Mode.INPUT);
17        motor = hwMap.get(DcMotor.class, "motor");
18        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
19        ticksPerRotation = motor.getMotorType().getTicksPerRev();
```

{69}------------------------------------------------

## 8. Servos

```
20    servo = hwMap.get(Servo.class, "servo");
21 }
22 public boolean isTouchSensorPressed() {
23     return !touchSensor.getState();
24 }
25
26 public void setMotorSpeed(double speed){
27     motor.setPower(speed);
28 }
29 public double getMotorRotations(){
30     return motor.getCurrentPosition() / ticksPerRotation;
31 }
32 public void setServoPosition(double position){
33     servo.setPosition(position);
34 }
35 }
```

This is very similar to the ones before. We'll just talk about the new parts.

```
12 private Servo servo;
```

Here we create a class member of type servo named servo. The Servo class comes from the FTC SDK. Again, we would use a more descriptive name on our robot.

```
20 servo = hwMap.get(Servo.class, "servo");
```

This assigns to the variable servo the hardware that is in the configuration file of type Servo.class and with a name of servo. This name has to match **EXACTLY** what is in the configuration file.

```
32 public void setServoPosition(double position){
33     servo.setPosition(position);
34 }
```

This allows code outside of our class to set the servo position. Typically we might expose a method for each position we want it to go to - for example setClawOpen() and setClawClose()

servo.setPosition() takes a double which is a fraction between 0.0 and 1.0 saying where in that range to move. We can programmatically change what that means with two methods:

1. servo.setDirection(Servo.Direction.REVERSE) flips your range. (and yes you can also call it with Servo.Direction.FORWARD to flip it back)
2. servo.scaleRange(double min, double max) sets the logical min and max. Then servo.setPosition() is a fraction between that range.<sup>1</sup> It is relative

<sup>1</sup>The min has to be less than the max, so you can't use this to flip the direction.

{70}------------------------------------------------

to the entire range, so you can set it back with `servo.scaleRange(0.0, 1.0)`.

As an example, you might have this in the `init()` method

```
...  
servo = hwMap.get(Servo.class, "servo");  
servo.setDirection(Servo.Direction.REVERSE);  
servo.scaleRange(0.5, 1.0); // only go from midpoint to far right point  
...
```

## 8.3. OpMode

This one is in the `opmodes` package

Listing 8.2: `ServoGamepadOpMode.java`

```
1 package org.firstinspires.ftc.teamcode.opmodes;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard5;  
7  
8 @TeleOp()  
9 public class ServoGamepadOpMode extends OpMode {  
10     ProgrammingBoard5 board = new ProgrammingBoard5();  
11     @Override  
12     public void init() {  
13         board.init(hardwareMap);  
14     }  
15  
16     @Override  
17     public void loop() {  
18         if(gamepad1.a) {  
19             board.setServoPosition(1.0);  
20         }  
21         else if(gamepad1.b){  
22             board.setServoPosition(0.0);  
23         }  
24         else{  
25             board.setServoPosition(0.5);  
26         }  
27     }  
28 }
```

The only new thing here is:

{71}------------------------------------------------

## 8. Servos

```
public void loop() {  
    if(gamepad1.a) {  
        board.setServoPosition(1.0);  
    }  
    else if(gamepad1.b){  
        board.setServoPosition(0.0);  
    }  
    else{  
        board.setServoPosition(0.5);  
    }  
}
```

You'll see that we are using chained if and else so that we only try to set the servo position to one location. Otherwise we will confuse the servo and you'll likely see some jitter on it. (although the last one will likely win since there is more time in between calls to loop() than within loop()

### 8.4. Exercises

1. Change the ProgrammingBoard class so that the servo is backwards and only goes from the midpoint to far left.
2. Change the opMode so that how far you push in gamepad1.left\_trigger determines the position of the servo.

{72}------------------------------------------------

## 9. Analog Sensors

We'll be using a potentiometer here, but the same concepts work for all analog sensors. It is very common to abbreviate potentiometer as "pot" because potentiometer is hard to spell.

### 9.1. Configuration File

Follow steps 1-5 of [section 7.1](#), but select Analog Input Devices

1. On Port 0, Change to "Analog Input"
2. Change its name to be "pot"

![Screenshot of the Robot Configuration dialog showing Port 0 set to Analog Input and named 'pot'.]()![](_page_72_Picture_5.jpeg)

Continue with steps 8 and on of [section 7.1](#)

### 9.2. Mechanisms

First, lets add support to our ProgrammingBoard class.

Listing 9.1: ProgrammingBoard6.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;
2
3 import com.qualcomm.robotcore.hardware.AnalogInput;
4 import com.qualcomm.robotcore.hardware.DcMotor;
5 import com.qualcomm.robotcore.hardware.DigitalChannel;
6 import com.qualcomm.robotcore.hardware.HardwareMap;
7 import com.qualcomm.robotcore.hardware.Servo;
8 import com.qualcomm.robotcore.util.Range;
9
10 public class ProgrammingBoard6 {
11     private DigitalChannel touchSensor;
12     private DcMotor motor;
13     private double ticksPerRotation;
14     private Servo servo;
```

{73}------------------------------------------------

## 9. Analog Sensors

```
15 private AnalogInput pot;
16
17 public void init(HardwareMap hwMap) {
18     touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
19     touchSensor.setMode(DigitalChannel.Mode.INPUT);
20     motor = hwMap.get(DcMotor.class, "motor");
21     motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
22     ticksPerRotation = motor.getMotorType().getTicksPerRev();
23     servo = hwMap.get(Servo.class, "servo");
24     pot = hwMap.get(AnalogInput.class, "pot");
25 }
26
27 public boolean isTouchSensorPressed() {
28     return !touchSensor.getState();
29 }
30
31 public void setMotorSpeed(double speed){
32     motor.setPower(speed);
33 }
34
35 public double getMotorRotations(){
36     return motor.getCurrentPosition() / ticksPerRotation;
37 }
38
39 public void setServoPosition(double position){
40     servo.setPosition(position);
41 }
42
43 public double getPotAngle(){
44     return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
45 }
46 }
```

Most of this is the same, so we'll just explain the new bits.

```
15 private AnalogInput pot;
```

We are declaring a class member of type AnalogInput with name pot. The AnalogInput class comes from the FTC SDK.

```
24 pot = hwMap.get(AnalogInput.class, "pot");
```

This assigns to the variable pot the hardware that is in the configuration file of type AnalogInput.class and with a name of pot. This name has to match **EXACTLY** what is in the configuration file.

```
39 public double getPotAngle(){
40     return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
41 }
```

This is a class method that returns the angle to potentiometer is currently at. It turns out that the AnalogInput class gives us a voltage. We could just expose that with a getPotVoltage() method, but then our other code has to

{74}------------------------------------------------

know about voltage when it makes more sense to think in terms of the angle it is pointing at. We use a cool trick here to translate from voltage to angle.

There is a utility class in the FTC SDK called `Range` that has a method called `scale()`. It will translate a number from one range to another one. So for example if you call

```
double output = Range.scale(25, 0, 100, 0.0, 1.0);
```

then it would figure out that the input (25) was 1/4 of the way between 0 and 100. It would then figure out what 1/4 between 0 and 1.0 is and would set output to 0.25.

In this case we know that the lowest possible voltage that could be detected is 0, the highest we can get by calling `pot.getMaxVoltage()`. We know our potentiometer can be between 0 and 270 degrees. So we use `Range.scale` to convert for us.

You might have noticed that you made a method call on a class instead of an object (a variable of type class). That is because it is a *static* method. This is an example of what we talked about in [section 5.5](#).

## 9.3. OpMode

Now we need an OpMode that can use it.

Listing 9.2: PotOpMode.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard5;
7 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard6;
8
9 @TeleOp()
10 public class PotOpMode extends OpMode {
11     ProgrammingBoard6 board = new ProgrammingBoard6();
12     @Override
13     public void init() {
14         board.init(hardwareMap);
15     }
16
17     @Override
18     public void loop() {
19         telemetry.addData("Pot Angle", board.getPotAngle());
20     }

```

{75}------------------------------------------------

## 9. Analog Sensors

```
21 }
```

Since we are doing the conversion in our ProgrammingBoard class, this becomes trivial. We are simply reporting the angle. This can be used on our robot to know what angle something is turned to.

### 9.4. Exercises

1. Make a class method for your ProgrammingBoard that exposes the pot in the range [0.0..1.0]
2. Now make an OpMode that sets the servo to the position that the pot is returning in that range. Then you can turn the pot and it will cause the servo to “follow” it.

{76}------------------------------------------------

# 10. Color and Distance Sensors

## 10.1. Configuration File

Follow steps 1-5 of [section 7.1](#), but select I2C Bus 1

![Screenshot of the Robot Configuration dialog showing Port 0 set to REV Color/Range Sensor, with the device name changed to sensor_color_distance.]()![](_page_76_Picture_3.jpeg)

1. On Port 0, Change to “REV Color/Range Sensor”
2. Change its name to be “sensor\_color\_distance”

Continue with steps 8 and on of [section 7.1](#)

## 10.2. Mechanisms

Let's start by making a change to our ProgrammingBoard class.

Listing 10.1: ProgrammingBoard7.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;
2
3 import com.qualcomm.robotcore.hardware.AnalogInput;
4 import com.qualcomm.robotcore.hardware.ColorSensor;
5 import com.qualcomm.robotcore.hardware.DcMotor;
6 import com.qualcomm.robotcore.hardware.DigitalChannel;
7 import com.qualcomm.robotcore.hardware.DistanceSensor;
8 import com.qualcomm.robotcore.hardware.HardwareMap;
9 import com.qualcomm.robotcore.hardware.Servo;
10 import com.qualcomm.robotcore.util.Range;
11
12 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
13
14 public class ProgrammingBoard7 {
15     private DigitalChannel touchSensor;
16     private DcMotor motor;
17     private double ticksPerRotation;
18     private Servo servo;
19     private AnalogInput pot;
```

{77}------------------------------------------------

## 10. Color and Distance Sensors

```
20 private ColorSensor colorSensor;
21 private DistanceSensor distanceSensor;

22 public void init(HardwareMap hwMap) {
23     touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
24     touchSensor.setMode(DigitalChannel.Mode.INPUT);
25     motor = hwMap.get(DcMotor.class, "motor");
26     motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
27     ticksPerRotation = motor.getMotorType().getTicksPerRev();
28     servo = hwMap.get(Servo.class, "servo");
29     pot = hwMap.get(AnalogInput.class, "pot");

30     colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
31     distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
32 }

33 public boolean isTouchSensorPressed() {
34     return !touchSensor.getState();
35 }

36 public void setMotorSpeed(double speed){
37     motor.setPower(speed);
38 }

39 public double getMotorRotations(){
40     return motor.getCurrentPosition() / ticksPerRotation;
41 }

42 public void setServoPosition(double position){
43     servo.setPosition(position);
44 }

45 public double getPotAngle(){
46     return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
47 }

48 public int getAmountRed(){
49     return colorSensor.red();
50 }

51 public double getDistance(DistanceUnit du){
52     return distanceSensor.getDistance(du);
53 }
54 }
55 }
56 }
57 }
```

Most of this is similar so we'll only talk about the new parts.

```
20 private ColorSensor colorSensor;
21 private DistanceSensor distanceSensor;
```

This is a little different. A REV ColorSensor can act as both a color sensor and a distance sensor.<sup>1</sup> So we make two variables - one for the colorSensor

<sup>1</sup>Although the distance sensor part of a color sensor is much less accurate and over a smaller range than a REV Distance sensor.

{78}------------------------------------------------

class and one for the DistanceSensor class. Both of these classes are in the FTC SDK.

```
32 colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");33 distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
```

Both of these follow the pattern we have seen before. The unusual part is that they use the SAME string for the sensor. Again, it has to match EXACTLY what is in the configuration file.

```
51 public int getAmountRed(){52     return colorSensor.red();53 }
```

This is a class method that returns the amount of red that the color sensor sees (between 0 and 255). The colorSensor class has several class methods that are useful.

| <b>Method</b> | <b>What it returns</b>                                                                 |
|---------------|----------------------------------------------------------------------------------------|
| red()         | Amount of red seen (0-255)                                                             |
| green()       | Amount of green seen (0-255)                                                           |
| blue()        | Amount of blue seen (0-255)                                                            |
| argb()        | An integer in the format #aarrggbb (where a is alpha, r is red, g is green, b is blue) |

```
54 public double getDistance(DistanceUnit du){55     return distanceSensor.getDistance(du);56 }
```

This uses a neat class included in the FTC SDK called DistanceUnit. It allows us to decide what units we want to work in and hopefully keeps us from making a NASA class mistake with units.<sup>2</sup> This is a simple pass through so we'll talk more about DistanceUnit as we discuss the OpMode.

## 10.3. OpMode

And we need an OpMode that can use it.

Listing 10.2: DistanceColorOpMode.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;2
```

<sup>2</sup>https://en.wikipedia.org/wiki/Mars\_Climate\_Orbiter

{79}------------------------------------------------

## 10. Color and Distance Sensors

```
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
7 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard7;
8
9 @TeleOp()
10 public class DistanceColorOpMode extends OpMode {
11     ProgrammingBoard7 board = new ProgrammingBoard7();
12     @Override
13     public void init() {
14         board.init(hardwareMap);
15     }
16
17     @Override
18     public void loop() {
19         telemetry.addData("Amount red", board.getAmountRed());
20         telemetry.addData("Distance (CM)", board.getDistance(DistanceUnit.CM));
21         telemetry.addData("Distance (IN)", board.getDistance(DistanceUnit.INCH));
22     }
23 }
```

A lot of this is similar, so let's talk about the new parts.

```
19 telemetry.addData("Amount red", board.getAmountRed());
```

This simply prints the amount of red seen by the color sensor

```
20 telemetry.addData("Distance (CM)", board.getDistance(DistanceUnit.CM));
21 telemetry.addData("Distance (IN)", board.getDistance(DistanceUnit.INCH));
```

This is showing the coolness of the `DistanceUnit` class. By passing in different values to `getDistance()`, we get it in the units we prefer. (you should prefer metric - but since a lot of the FTC specs are in Imperial, it is helpful to be able to do both.) The choices are:

| <b>Parameter</b>   | <b>Unit</b> |
|--------------------|-------------|
| DistanceUnit.MM    | millimeter  |
| DistanceUnit.CM    | centimeter  |
| DistanceUnit.INCH  | inch        |
| DistanceUnit.METER | meter       |

If you are using this with your class, you'll have to decide what unit you are going to store things in (I typically recommend CM, but that is up to you.) Then you can convert things like this:

```
public class Square{
    double length_cm = 10;
```

{80}------------------------------------------------

```
public double getLength(DistanceUnit du){  
    return du.fromCm(length_cm);  
}  
public void setLength(double length, DistanceUnit du){  
    length_cm = du.toCm(length);  
}  
}
```

## 10.4. Exercises

1. Add a method getAmountBlue() to the ProgrammingBoard and report it back by changing the OpMode
2. Make the motor stop when the distance sensor sees something closer than 10cm and go at half speed when farther than that.

{81}------------------------------------------------

![](_page_81_Picture_0.jpeg)

{82}------------------------------------------------

# 11. Gyro (IMU)

## 11.1. Configuration File

Unlike everything else, you don't need to add it to the robot configuration because it is already there as "imu". You can rename it or delete it.

## 11.2. Mechanisms

Let's start by adding support to our ProgrammingBoard class.

Listing 11.1: ProgrammingBoard8.java

```
package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class ProgrammingBoard8 {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;
    private Servo servo;
    private AnalogInput pot;
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;
    private IMU imu;

    public void init(HardwareMap hwMap) {
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
    }

```

{83}------------------------------------------------

## 11. Gyro (IMU)

```
29 touchSensor.setMode(DigitalChannel.Mode.INPUT);
30 motor = hwMap.get(DcMotor.class, "motor");
31 motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
32 ticksPerRotation = motor.getMotorType().getTicksPerRev();
33 servo = hwMap.get(Servo.class, "servo");
34 pot = hwMap.get(AnalogInput.class, "pot");

35 colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
36 distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
37 imu = hwMap.get(IMU.class, "imu");

38 RevHubOrientationOnRobot RevOrientation =
39     new RevHubOrientationOnRobot(RevHubOrientationOnRobot.←
40         ↳ LogoFacingDirection.UP,
41         RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

42 imu.initialize(new IMU.Parameters(RevOrientation));

43 }

44 public boolean isTouchSensorPressed() {
45     return !touchSensor.getState();
46 }

47 public void setMotorSpeed(double speed){
48     motor.setPower(speed);
49 }

50 public double getMotorRotations(){
51     return motor.getCurrentPosition() / ticksPerRotation;
52 }

53 public void setServoPosition(double position){
54     servo.setPosition(position);
55 }

56 public double getPotAngle(){
57     return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
58 }

59 public int getAmountRed(){
60     return colorSensor.red();
61 }

62 public double getDistance(DistanceUnit du){
63     return distanceSensor.getDistance(du);
64 }

65 public double getHeading(AngleUnit angleUnit) {
66     return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
67 }

68 }

69 }
```

Starting with the Control Hubs shipping in late 2022, the IMU chip changed. However, the FTC SDK changed in 8.1 to isolate the differences for us. While it

{84}------------------------------------------------

has a TON of capabilities, we are going to just barely tap into it here. (Original Rev Expansion Hubs had an IMU in them, newer ones do not.)

```
25 private IMU imu;
```

We create a class member of type IMU (you guessed it from the FTC SDK) with the name imu.

```
38 imu = hwMap.get(IMU.class, "imu");
```

First, we get the imu from the hardware map (just like we have done with other pieces of hardware). If you didn't change the name in your configuration (and you shouldn't), it will be "imu".

```
40 RevHubOrientationOnRobot RevOrientation =  
41 new RevHubOrientationOnRobot(RevHubOrientationOnRobot.<-  
42     <- LogoFacingDirection.UP,  
43     RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);  
44 imu.initialize(new IMU.Parameters(RevOrientation));
```

Next, we need to describe how the Rev Hub is oriented on our robot. This is simply done by stating what the orientation of the Logo and the orientation of the USB is. The valid choices are FORWARD, BACKWARD, UP, DOWN, LEFT, and RIGHT. (Yes, you can place your Hub at an angle. To do that look at the example SensorIMUNonOrthogonal.java in the Java examples that come with the SDK.)

Then we initialize the imu with parameters.

```
69 public double getHeading(AngleUnit angleUnit) {
```

We are creating a class method so code outside of our class can get the heading of the robot (actually REV hub). Much like we had DistanceUnit before, there is also a class called AngleUnit. There are two angle units supported: DEGREES and RADIANS.<sup>1</sup> AngleUnit will make sure everything is normalized (that means it will be within -180 and 180 degrees for DEGREES and between - $\pi$  and  $\pi$  for RADIANS).<sup>2</sup>

```
70 return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
```

Here we get the Yaw (amount it is turning around assuming the robot is staying level), but you could also get the pitch and the roll.

This is much simpler than the old SDK, but even this is good to hide behind a class method so you don't accidentally make a mistake.

<sup>1</sup>No love for gradians... - https://en.wikipedia.org/wiki/Gradian

<sup>2</sup>Yes, this means our RobotLocation class could have been much simpler.

{85}------------------------------------------------

## 11. Gyro (IMU)

### 11.3. OpMode

And here is our OpMode to use it.

Listing 11.2: GyroOpMode.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;

@TeleOp()
public class GyroOpMode extends OpMode {
    ProgrammingBoard8 board = new ProgrammingBoard8();

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Our Heading", board.getHeading(AngleUnit.DEGREES));
    }
}
```

Really the only thing that is new here is our telemetry in line 19. Put it on the programming board and turn it around and watch the telemetry change.

### 11.4. Exercises

1. Change the OpMode to also show the heading in RADIANS as well as DEGREES
2. Make the motor stopped when our heading is 0, go negative when our heading is negative, and positive when our heading is positive.

{86}------------------------------------------------

## 12. Dealing with State

State is where you remember what you have done and do something different because of what you have done in the past.

### 12.1. A simple example

So far we have always done something depending on whether a button is currently pressed. What if you wanted it to do something when you first pressed it (such as toggle a light)? Let's do that in an OpMode.

Listing 12.1: ToggleOpMode.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;

@TeleOp()
public class ToggleOpMode extends OpMode {
    ProgrammingBoard8 board = new ProgrammingBoard8();
    boolean aAlreadyPressed;
    boolean motorOn;

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.a && !aAlreadyPressed){
            motorOn = !motorOn;
            telemetry.addData("Motor", motorOn);
            if (motorOn) {
                board.setMotorSpeed(0.5);
            } else {
                board.setMotorSpeed(0.0);
            }
        }
    }
}
```

{87}------------------------------------------------

## 12. Dealing with State

```
29 aAlreadyPressed = gamepad1.a;
30 }
31 }
```

Let's break this down:

```
11 boolean aAlreadyPressed;
12 boolean motorOn;
```

Here we define two more class members. Since we don't initialize them and they are boolean they start out as false.

```
20 if(gamepad1.a && !aAlreadyPressed){
```

In this line we are saying if gamepad1.a is true (pressed) AND aAlreadyPressed is NOT true (false) then... (Remember that ! means NOT. So it makes false turn to true and true turn to false.)

```
21 motorOn = !motorOn;
```

This is a common shorthand. What it does is invert the boolean value. It does exactly the same thing as this code:

```
if(motorOn) {
    motorOn = false;
} else {
    motorOn = true;
}
```

Normally, I like to avoid shortcuts but in this case it is so common that most programmers would prefer the way it is done in the example.

```
23 if (motorOn) {
24     board.setMotorSpeed(0.5);
25 } else {
26     board.setMotorSpeed(0.0);
27 }
28 }
```

This actually turns on (or off) the motor. More than one programmer has forgotten this piece and been puzzled when changing the value of a variable called motorOn did not actually change the motor.

```
30 }
```

Here we set aAlreadyPressed to the value of gamepad1.a.

Let's think about how this code works. The first time a user presses the A button, it will come in and gamepad1.a will be true and aAlreadyPressed will be false. So it will toggle the motorOn class member and change the motor. If the

{88}------------------------------------------------

button is still held down the next time through, gamepad1.a will be true but so will aAlreadyPressed so it won't go into the if code block. Eventually our user gets bored and lets go of gamepad1.a. The first time through, gamepad1.a will be false and aAlreadyPressed will be true. But then aAlreadyPressed will be set to false and we'll be ready for our user to press gamepad1.a again.

Make sure you try this one and play with turning the motor on and off.

## 12.2. Autonomous state - Example

When writing autonomous code, you want to write it as separate steps. This allows you to test out parts of it separately.

Listing 12.2: AutoState1.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;
2
3 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
4 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;
7
8 @Autonomous()
9 public class AutoState1 extends OpMode {
10     ProgrammingBoard8 board = new ProgrammingBoard8();
11     int state;
12
13     @Override
14     public void init() {
15         board.init(hardwareMap);
16     }
17
18     @Override
19     public void start() {
20         state = 0;
21     }
22
23     @Override
24     public void loop() {
25         telemetry.addData("State", state);
26         if (state == 0) {
27             board.setServoPosition(0.5);
28             if (board.isTouchSensorPressed()) {
29                 state = 1;
30             }
31         } else if (state == 1) {
32             board.setServoPosition(0.0);
33             if (!board.isTouchSensorPressed()) {

```

{89}------------------------------------------------

## 12. Dealing with State

```
34 state = 2;
35 }
36 } else if (state == 2) {
37     board.setServoPosition(1.0);
38     board.setMotorSpeed(0.5);
39     if (board.getPotAngle() > 90) {
40         state = 3;
41     }
42 } else if (state == 3) {
43     board.setMotorSpeed(0.0);
44     state = 4;
45 } else {
46     telemetry.addData("Auto", "Finished");
47 }
48 }
49 }
```

Let's break this down:

```
11 int state;
```

Here we create our state variable to hold which state we are in. If we don't assign an initial value it is zero.

```
18 @Override
19 public void start() {
20     state = 0;
21 }
```

Since it should be zero, why do we assign it again in start(). Well, imagine that you test your auto. Press Stop, and then test it again. If we don't reset the variable here then it will be whatever it was at the end of your test.

```
25 telemetry.addData("State", state);
```

It is very helpful for debugging to send to the driver station what step in your auto program you are so you can figure out what is going on.

```
26 if (state == 0) {
27     board.setServoPosition(0.5);
28     if (board.isTouchSensorPressed()) {
29         state = 1;
30     }
31 } else if (state == 1) {
```

You can see here an example of using if/else chaining. Also, you'll notice that when the touch sensor is pressed, we change the value of state. So the next time through we'll go to the next chain.

But there is another way...

{90}------------------------------------------------

### 12.2.1. Using the switch statement

In Java, if you are comparing for a number of options you can use a switch statement. Here is the same program rewritten with a switch statement.

Listing 12.3: AutoState2.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;

@Autonomous()
public class AutoState2 extends OpMode {
    ProgrammingBoard8 board = new ProgrammingBoard8();
    int state;

    @Override
    public void init() {
        board.init(hardwareMap);
    }

    @Override
    public void start() {
        state = 0;
    }

    @Override
    public void loop() {
        telemetry.addData("State", state);
        switch (state) {
            case 0:
                board.setServoPosition(0.5);
                if (board.isTouchSensorPressed()) {
                    state = 1;
                }
                break;
            case 1:
                board.setServoPosition(0.0);
                if (!board.isTouchSensorPressed()) {
                    state = 2;
                }
                break;
            case 2:
                board.setServoPosition(1.0);
                board.setMotorSpeed(0.5);
                if (board.getPotAngle() > 90) {

```

{91}------------------------------------------------

## 12. Dealing with State

```
state = 3;
}
break;
case 3:
    board.setMotorSpeed(0.0);
    state = 4;
    break;
default:
    telemetry.addData("Auto", "Finished");
}
}
```

You may think that since this is more lines that it is worse, but let's look at it anyway. (It is personal preference based on which you feel is more readable and you can do things with if/else chaining that you can't do with a switch statement)

```
switch (state) {
```

A switch statement is written as switch( variable )

```
case 0:
```

Each case starts with the case keyword followed by the constant followed by a colon :

```
break;
```

All code is executed until it hits the break statement. At this point, it jumps to the closing brace of the switch statement.

![Lightbulb icon indicating a tip or note.]()![](_page_91_Picture_9.jpeg)

If you forget to put a break statement in, it will execute the next case as well. There are reasons why you might want to intentionally do this, but if it is intentional make sure you put a comment explaining why you are doing it because most people will assume it was a mistake.

```
default:
```

You can (but don't have to) have a default: clause. This will be executed if none of the other cases were a match.

But a problem with these two programs is that if you have to put one in the middle, you have to make lots of changes. We can do better....

{92}------------------------------------------------

12.2.2. Switch with stringsListing 12.4: AutoState3.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;
2
3 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
4 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;
7
8 @Autonomous()
9 public class AutoState3 extends OpMode {
10     ProgrammingBoard8 board = new ProgrammingBoard8();
11     String state = "START";
12
13     @Override
14     public void init() {
15         board.init(hardwareMap);
16     }
17
18     @Override
19     public void start() {
20         state = "START";
21     }
22
23     @Override
24     public void loop() {
25         telemetry.addData("State", state);
26         switch (state) {
27             case "START":
28                 board.setServoPosition(0.5);
29                 if (board.isTouchSensorPressed()) {
30                     state = "WAIT_FOR_SENSOR_RELEASE";
31                 }
32                 break;
33             case "WAIT_FOR_SENSOR_RELEASE":
34                 board.setServoPosition(0.0);
35                 if (!board.isTouchSensorPressed()) {
36                     state = "WAIT_FOR_POT_TURN";
37                 }
38                 break;
39             case "WAIT_FOR_POT_TURN":
40                 board.setServoPosition(1.0);
41                 board.setMotorSpeed(0.5);
42                 if (board.getPotAngle() > 90) {
43                     state = "STOP";
44                 }
45                 break;

```

{93}------------------------------------------------

## 12. Dealing with State

```
case "STOP":  
    board.setMotorSpeed(0.0);  
    state = "DONE";  
    break;  
default:  
    telemetry.addData("Auto", "Finished");  
}
```

Really all we have done is change state from an integer to a String. Now our code is easier to read (called self-documenting) and it is easier to add in another state. (Win-win!!)

But now if we have a typo in a string the compiler won't catch it, and we'll have a problem in our code. What if we could have the readability of strings, but have the compiler catch typos. We can....

### 12.2.3. Enumerated types

Listing 12.5: AutoState4.java

```
package org.firstinspires.ftc.teamcode.opmodes;  
  
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;  
import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
  
import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;  
  
@Autonomous()  
public class AutoState4 extends OpMode {  
    enum State {  
        START,  
        WAIT_FOR_SENSOR_RELEASE,  
        WAIT_FOR_POT_TURN,  
        STOP,  
        DONE  
    }  
  
    ProgrammingBoard8 board = new ProgrammingBoard8();  
    State state = State.START;  
  
    @Override  
    public void init() {  
        board.init(hardwareMap);  
    }  
  
    @Override
```

{94}------------------------------------------------

```
27 public void start() {  
28     state = State.START;  
29 }  
30  
31 @Override  
32 public void loop() {  
33     telemetry.addData("State", state);  
34     switch (state) {  
35         case START:  
36             board.setServoPosition(0.5);  
37             if (board.isTouchSensorPressed()) {  
38                 state = State.WAIT_FOR_SENSOR_RELEASE;  
39             }  
40             break;  
41         case WAIT_FOR_SENSOR_RELEASE:  
42             board.setServoPosition(0.0);  
43             if (!board.isTouchSensorPressed()) {  
44                 state = State.WAIT_FOR_POT_TURN;  
45             }  
46             break;  
47         case WAIT_FOR_POT_TURN:  
48             board.setServoPosition(1.0);  
49             board.setMotorSpeed(0.5);  
50             if (board.getPotAngle() > 90) {  
51                 state = State.STOP;  
52             }  
53             break;  
54         case STOP:  
55             board.setMotorSpeed(0.0);  
56             state = State.DONE;  
57             break;  
58         default:  
59             telemetry.addData("Auto", "Finished");  
60     }  
61 }  
62 }
```

Let's talk through some of this. This actually works exactly the same as our first switch statement except now it is more readable (and we can't assign values to it that we aren't expecting)

```
10 enum State {  
11     START,  
12     WAIT_FOR_SENSOR_RELEASE,  
13     WAIT_FOR_POT_TURN,  
14     STOP,  
15     DONE  
16 }
```

{95}------------------------------------------------

## 12. Dealing with State

enum is short for Enumerated. It is a way we can give names to values. We can add an accessor modifier to this so that the enum can be accessed outside the class, but we didn't in this case. By convention, we make all values of an enum ALL\_CAPS. They have a comma in between each one. Most of the time, it is best to put each one on its own line but you don't have to.

This is declaring a new type called state. It is just like making a class. An enum is actually a special class that extends java.lang.Enum. So yes, you can put methods and class members in it. But you don't need to and typically don't. (So yes, you could put an enum in its own file. And yes, you can create a class inside of a class.)

```
28 state = State.START;
```

Now instead of type String it is of type State. We initialize it to State.START. Note that we use the type followed by a dot . followed by the enum value. You probably noticed that Android Studio helped you type it in. Yet another huge benefit over a string.

```
33 telemetry.addData("State", state);
```

One of the really cool things about enum is that they implement toString automatically so when you print them you get human readable descriptions.

### 12.3. It's all relative

For a lot of autonomous programs, you may want things to occur for an amount of time or an amount of encoder ticks. To do this we need to save off the time or ticks when we started.

To get the time, all opModes have access to getRuntime() which returns a double that is the number of seconds since the opMode was created. This isn't very useful by itself because we don't know how long ago that was before "START" was pushed. There is also a resetRunTime() which makes the current time zero. (We often put this in our start() method)

Listing 12.6: AutoTime.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;
2
3 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
4 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;
7
8 @Autonomous()
```

{96}------------------------------------------------

```
9 public class AutoTime extends OpMode {  
10     enum State {  
11         START,  
12         SECOND_STEP,  
13         DONE  
14     }  
15  
16     ProgrammingBoard8 board = new ProgrammingBoard8();  
17     State state = State.START;  
18     double lastTime;  
19  
20     @Override  
21     public void init() {  
22         board.init(hardwareMap);  
23     }  
24  
25     @Override  
26     public void start() {  
27         state = State.START;  
28         resetRuntime();  
29         lastTime = getRuntime();  
30     }  
31  
32     @Override  
33     public void loop() {  
34         telemetry.addData("State", state);  
35         telemetry.addData("Runtime", getRuntime());  
36         telemetry.addData("Time in State", getRuntime() - lastTime);  
37         switch (state) {  
38             case START:  
39                 if (getRuntime() >= 3.0) {  
40                     state = State.SECOND_STEP;  
41                     lastTime = getRuntime();  
42                 }  
43                 break;  
44             case SECOND_STEP:  
45                 if (getRuntime() >= lastTime + 3.0) {  
46                     state = State.DONE;  
47                     lastTime = getRuntime();  
48                 }  
49                 break;  
50             default:  
51                 telemetry.addData("Auto", "Finished");  
52         }  
53     }  
54 }
```

Let's talk through some of the pieces here.

{97}------------------------------------------------

## 12. Dealing with State

```
25 @Override
26 public void start() {
27     state = State.START;
28     resetRuntime();
29     lastTime = getRuntime();
30 }
```

We are taking advantage of the optional `start()` method here. Remember that this is called ONCE when the OpMode is started. We moved setting of our `state` variable here because it seemed to make more sense, but leaving it in `init()` will work fine as well. We call `resetRuntime()` which will make our runtime zero. We could have just set `lastTime` to zero here, but we like getting the runtime as it keeps things more similar.

```
35 telemetry.addData("Runtime", getRuntime());
36 telemetry.addData("Time in State", getRuntime() - lastTime);
```

In the first one, we are showing our total runtime. In the second, we show our relative. This is done by keeping track of when we went into a state and then showing the difference.

```
39 if (getRuntime() >= 3.0) {
```

It is **really** important that we compare with a `>=` instead of an `==` because the runtime increments in sub-milliseconds so the odds of it being exact are very low.

```
41 lastTime = getRuntime();
```

When we get ready to change states, we set the `lastTime` variable. We could have called `resetRuntime()` but then we wouldn't be able to know also our runtime as well as time in state.

```
45 if (getRuntime() >= lastTime + 3.0) {
```

Here you'll notice that we are comparing to `lastTime + 3.0` (Obviously if we wanted it to be 5 seconds instead, we would make it `+ 5.0`)

Hopefully, it doesn't take much imagination to do the same thing with encoder ticks (using a `lastEncoder` value)

## 12.4. Exercises

1. Make a program that ramps your motor to full speed (.25 for 250ms, .50 for 250ms, .75 for 250ms, 1.0) and goes at full speed until the touch sensor is pressed.

{98}------------------------------------------------

2. Make a program that turns the motor until the distance sensor is less than 10cm OR 5 seconds has passed and then turns the servo.

{99}------------------------------------------------

![](_page_99_Picture_0.jpeg)

{100}------------------------------------------------

## 13. Arrays

An array can hold a fixed number of values of one type. Imagine that we had four motors on our drive train. Instead of code like:

```
DcMotor motor1;
DcMotor motor2;
DcMotor motor3;
DcMotor motor4;
```

we could have:

```
DcMotor[] motors = new DcMotor[4]
```

The pattern is:

```
variableType[] variableName = new variableType[arraySize];
```

We can access each motor with an index. The index of an Array start with an index of 0. So it might look like this:

```
motors[0] = hwMap.get(DcMotor.class, "front_left");
motors[1] = hwMap.get(DcMotor.class, "front_right");
motors[2] = hwMap.get(DcMotor.class, "back_left");
motors[3] = hwMap.get(DcMotor.class, "back_right");
```

This may seem interesting, but not all that useful until you start using other things you have learned

```
void stopAllMotors(){
    for(int i = 0; i < 4; i++){
        motors[i].setPower(0.0);
    }
}
```

This is done so often that Java has a cool shortcut for it. This is called the for..each

```
void stopAllMotors(){
    for(DcMotor motor : motors){
        motor.setPower(0.0);
    }
}
```

{101}------------------------------------------------

## 13. Arrays

The format here is `for( variableType variableName : arrayName )`  
Below is an example op mode using for

Listing 13.1: ArrayOpMode.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class ArrayOpMode extends OpMode {
    String[] words = {"Zeroth", "First", "Second", "Third", "Fourth", "Fifth", "←
    ↳ Infinity"};
    int wordIndex;
    double DELAY_SECS = 0.5;

    double nextTime;

    @Override
    public void init() {
        wordIndex = 0;
    }

    @Override
    public void loop() {
        if (nextTime < getRuntime()) {
            wordIndex++;
            if (wordIndex >= words.length) {
                wordIndex = words.length - 1;
            }
            nextTime = getRuntime() + DELAY_SECS;
        }
        telemetry.addLine(words[wordIndex]);
    }
}
```

### 13.1. ArrayList

This is all great, but an array can't grow or shrink in size. For that there is ArrayList.

```
ArrayList<int> items = new ArrayList<>();
```

The angle brackets are new. That means the type is a “Generic”. What that means is that you specify what type the class uses when you define your

{102}------------------------------------------------

object. So this is creating an `ArrayList` that holds integers. (It could be any type including classes)

A few common methods:

```
items.add(4); // this adds this element to the end of the list
items.get(index); // returns the element at the index of the list (starts at 0)
items.clear(); // removes all items from list
items.size(); // returns the number of elements in the list

ArrayList<int> secondList = new ArrayList<>();
secondList.add(5);
secondList.add(6);
items.addAll(secondList); // adds all elements in second list to first list
```

### 13.1.1. Making your own generic class

Making generic classes is not done much in FTC, but I'll include it here for completeness

```
public class MyClass<T>{
    private T member;
    public void set(T var) { member = var; }
    public T get() { return member; }
}
```

Everywhere that T is gets replaced when you use the class.

## 13.2. Exercises

1. Modify the `opMode` to send the chorus of a song you know at a fixed rate on telemetry. Once it gets to the end, it should send it again.
2. Modify your solution for exercise 1 to use `ArrayList<String>` instead of arrays.

{103}------------------------------------------------

![](_page_103_Picture_0.jpeg)

{104}------------------------------------------------

## 14. Inheritance

In Java, when you create a class it always “inherits” from a class. If you don’t use the `extends` keyword then it is inheriting from the `Object` class in Java. So what does this really do?

Let’s start with a simple example and then we’ll show how it can be useful in FTC. (We are going to put all of these in the `org.firstinspires.ftc.teamcode` package (directory))

Listing 14.1: `SuperClass.java`

```
1 package org.firstinspires.ftc.teamcode;
2
3 public class SuperClass {
4     public String a() {
5         return "a";
6     }
7
8     public String b() {
9         return "b";
10    }
11 }
```

Listing 14.2: `ChildClass.java`

```
1 package org.firstinspires.ftc.teamcode;
2
3 public class ChildClass extends SuperClass {
4     @Override
5     public String a() {
6         return "A";
7     }
8
9     public String c() {
10        return "c";
11    }
12 }
```

Listing 14.3: `SimpleInheritance.java`

```
1 package org.firstinspires.ftc.teamcode;
2
```

{105}------------------------------------------------

## 14. Inheritance

```
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class SimpleInheritance extends OpMode {
    SuperClass super_obj = new SuperClass();
    ChildClass child_obj = new ChildClass();

    @Override
    public void init() {
        telemetry.addData("Parent a", super_obj.a());
        telemetry.addData("Parent b", super_obj.b());
        telemetry.addData("Child a", child_obj.a());
        telemetry.addData("Child b", child_obj.b());
        telemetry.addData("Child c", child_obj.c());
    }

    @Override
    public void loop() {
    }
}
```

Can you guess what will show up on the telemetry screen? Try it. Were you right?

You can think about inheritance as your new class containing all of the super class (often called “parent”) plus its new stuff. This is shown in the diagram on the right. If you have a class method with the exact same name and parameters, then it will replace it. You should put an @Override annotation on it so that everyone knows that was intentional. (You actually don’t have to but it is good practice to do it.)

![Diagram illustrating inheritance: ChildClass contains SuperClass, which contains Object.]()![](_page_105_Picture_4.jpeg)

### 14.1. Isa vs. hasa

So now there is a question. If you can get the contents of another class by either deriving from it or having it as a class member, which should you do?

This is typically called “isa” vs “hasa” (short for *is a* and *has a*) So you should derive from it if your class is of that type, but include it if you simply have it as a class member if it just is one of the things you have. Generally I like to

{106}------------------------------------------------

## 14.2. So why in the world would you use this?

start having it as a class member and only derive from another class if that is really clearly what I need to do.

### 14.2. So why in the world would you use this?

It is time for the largest word in this book - *polymorphism* - that is. When you are derived from another class you can be treated either as your class or your superclass. This will be the longest example in the book (5 files!!), but I hope it will help you take your programming to the next level.

We are going to make an OpMode that we can use to test out our wiring. (**HIGHLY** recommend this for your robot. Once you have it, you'll find out how useful it is over and over again to determine whether something is a software or electrical/mechanical problem.

Listing 14.4: TestItem.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;
2
3 import org.firstinspires.ftc.robotcore.external.Telemetry;
4
5 abstract public class TestItem {
6     private String description;
7
8     protected TestItem(String description) {
9         this.description = description;
10     }
11
12     public String getDescription() {
13         return description;
14     }
15
16     abstract public void run(boolean on, Telemetry telemetry);
17 }
```

There is really only one new thing in this file but it shows up twice. It is the keyword **abstract**.

```
5 abstract public class TestItem {
```

When **abstract** is before a class it means that no objects can be made of the type of this class. (In other words it is only meant to have other classes derive from it.)

```
16 abstract public void run(boolean on, Telemetry telemetry);
```

{107}------------------------------------------------

## 14. Inheritance

When abstract is before a class method it means that there is no body of this class method, but classes that derive from it that aren't abstract **MUST** implement it. (OpMode defines init() and loop() as abstract methods). Why in the world would you create a method that does nothing? Well if you require derived classes to have it, then each class can have their own implementation but you are guaranteed they have one.<sup>1</sup>

Listing 14.5: TestMotor.java

```
package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestMotor extends TestItem {
    private double speed;
    private DcMotor motor;

    public TestMotor(String description, double speed, DcMotor motor) {
        super(description);
        this.speed = speed;
        this.motor = motor;
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        if (on) {
            motor.setPower(speed);
        } else {
            motor.setPower(0.0);
        }
        telemetry.addData("Encoder:", motor.getCurrentPosition());
    }
}
```

A few notes here.

```
public class TestMotor extends TestItem {
```

You'll see here that this extends the TestItem class we made earlier.

```
super(description);
```

The super keyword refers to the class we derived from. Since this calls super() that is calling our superclass constructor. This is considered the correct way to implement a constructor in a child class.

<sup>1</sup>There is another way to accomplish this in Java called Interfaces that we'll discuss in section 19.4.

{108}------------------------------------------------

## 14.2. So why in the world would you use this?

Everything else in this file we have seen before

Listing 14.6: TestAnalogInput.java

```
package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestAnalogInput extends TestItem {
    private AnalogInput analogInput;
    private double min;
    private double max;

    public TestAnalogInput(String description, AnalogInput analogInput, double min, ←
        → double max) {
        super(description);
        this.analogInput = analogInput;
        this.min = min;
        this.max = max;
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData("Voltage: ", analogInput.getVoltage());
        telemetry.addData("In Range:",
            Range.scale(analogInput.getVoltage(),
                0, analogInput.getMaxVoltage(),
                min, max));
    }
}
```

This class should look very much like TestMotor to you. The one difference is we always read from the analogInput instead of using an if statement. (A rule I follow is you should only have to tell it to run a test if it causes something to change)

Listing 14.7: ProgrammingBoard9.java

```
package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
```

{109}------------------------------------------------

## 14. Inheritance

```
10 import com.qualcomm.robotcore.hardware.IMU;
11 import com.qualcomm.robotcore.hardware.Servo;
12 import com.qualcomm.robotcore.util.Range;
13
14 import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
15 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
16
17 import java.util.ArrayList;
18
19 public class ProgrammingBoard9 {
20     private DigitalChannel touchSensor;
21     private DcMotor motor;
22     private double ticksPerRotation;
23     private Servo servo;
24     private AnalogInput pot;
25     private ColorSensor colorSensor;
26     private DistanceSensor distanceSensor;
27     private IMU imu;
28
29     public void init(HardwareMap hwMap) {
30         touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
31         touchSensor.setMode(DigitalChannel.Mode.INPUT);
32         motor = hwMap.get(DcMotor.class, "motor");
33         motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
34         ticksPerRotation = motor.getMotorType().getTicksPerRev();
35         servo = hwMap.get(Servo.class, "servo");
36         pot = hwMap.get(AnalogInput.class, "pot");
37
38         colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
39         distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
40         imu = hwMap.get(IMU.class, "imu");
41
42         RevHubOrientationOnRobot RevOrientation =
43             new RevHubOrientationOnRobot(RevHubOrientationOnRobot.<-
44                 LogoFacingDirection.UP,
45                 RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
46
47         imu.initialize(new IMU.Parameters(RevOrientation));
48     }
49
50     public boolean isTouchSensorPressed() {
51         return !touchSensor.getState();
52     }
53
54     public void setMotorSpeed(double speed) {
55         motor.setPower(speed);
56     }
57
58     public double getMotorRotations() {

```

{110}------------------------------------------------

## 14.2. So why in the world would you use this?

```
58 return motor.getCurrentPosition() / ticksPerRotation;
59 }
60
61 public void setServoPosition(double position) {
62     servo.setPosition(position);
63 }
64
65 public double getPotAngle() {
66     return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
67 }
68
69 public int getAmountRed() {
70     return colorSensor.red();
71 }
72
73 public double getDistance(DistanceUnit du) {
74     return distanceSensor.getDistance(du);
75 }
76
77 public double getHeading(AngleUnit angleUnit) {
78     return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
79 }
80
81 public ArrayList<TestItem> getTests() {
82     ArrayList<TestItem> tests = new ArrayList<>();
83     tests.add(new TestMotor("PB Motor", 0.5, motor));
84     tests.add(new TestAnalogInput("PB Pot", pot, 0, 270));
85     return tests;
86 }
87 }
```

You'll notice that this has a new method at the end of it.

```
82 ArrayList<TestItem> tests = new ArrayList<>();
```

This says we will return an ArrayList containing elements of type TestItem

```
83 tests.add(new TestMotor("PB Motor", 0.5, motor));
```

Here we create the variable tests of type ArrayList<TestItem> and assign a newArrayList to it. The <> is a shortcut since it is defined on the other side of our assignment.

```
84 tests.add(new TestAnalogInput("PB Pot", pot, 0, 270));
85 return tests;
```

Here we add our two new tests to it. Note that we had to have the new keyword and this calls their constructor. Also note that if we had three motors, we wouldn't need 3 classes - we would just have 3 copies of the line tests.add(new TestMotor.... with a different description, speed, and motor variable.

{111}------------------------------------------------

}

and we return our list of tests. Now for our OpMode

## Listing 14.8: TestWiring.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard9;
7 import org.firstinspires.ftc.teamcode.mechanisms.TestItem;
8
9 import java.util.ArrayList;
10
11 @TeleOp
12 public class TestWiring extends OpMode {
13 ProgrammingBoard9 board = new ProgrammingBoard9();
14 ArrayList<TestItem> tests;
15 boolean wasDown, wasUp;
16 int testNum;
17
18 @Override
19 public void init() {
20 board.init(hardwareMap);
21 tests = board.getTests();
22 }
23
24 @Override
25 public void loop() {
26 // move up in the list of test
27 if (gamepad1.dpad_up && !wasUp) {
28 testNum--;
29 if (testNum < 0) {
30 testNum = tests.size() - 1;
31 }
32 }
33 wasUp = gamepad1.dpad_up;
34
35 // move down in the list of tests
36 if (gamepad1.dpad_down && !wasDown) {
37 testNum++;
38 if (testNum >= tests.size()) {
39 testNum = 0;
40 }
41 }
42 wasDown = gamepad1.dpad_down;
```

{112}------------------------------------------------

## 14.2. So why in the world would you use this?

```
43 //Put instructions on the telemetry
44 telemetry.addLine("Use Up and Down on D-pad to cycle through choices");
45 telemetry.addLine("Press A to run test");
46 //put the test on the telemetry
47 TestItem currTest = tests.get(testNum);
48 telemetry.addData("Test:", currTest.getDescription());
49 //run or don't run based on a
50 currTest.run(gamepad1.a, telemetry);
51 }
52 }
53 }
```

A few things to point out here that I hope will inspire you.

```
14 ArrayList<TestItem> tests;
15 boolean wasDown, wasUp;
16 int testNum;
```

Our list of tests as a member variable, wasDown and wasUp (like in section 12.1) and testNum to keep track of which test number we are on. For wasDown and wasUp, you see a shortcut where if you have multiple variables of the same type you can define them together with a comma.

```
26 // move up in the list of test
27 if (gamepad1.dpad_up && !wasUp) {
28     testNum--;
29     if (testNum < 0) {
30         testNum = tests.size() - 1;
31     }
32 }
33 wasUp = gamepad1.dpad_up;
34
35 // move down in the list of tests
36 if (gamepad1.dpad_down && !wasDown) {
37     testNum++;
38     if (testNum >= tests.size()) {
39         testNum = 0;
40     }
41 }
42 wasDown = gamepad1.dpad_down;
```

This uses the gamepad1.dpad\_up and gamepad1.dpad\_down to let us scroll through the list of tests. (Right now there are only 2 but it should give the idea). We made the decision to “wrap” around, but you could make the decision to not wrap. It is up to you.

```
45 telemetry.addLine("Use Up and Down on D-pad to cycle through choices");
46 telemetry.addLine("Press A to run test");
```

{113}------------------------------------------------

## 14. Inheritance

We haven't used `telemetry.addLine` before but it is just like `telemetry.addData` except it only has one parameter.

```
47 //put the test on the telemetry
48 TestItem currTest = tests.get(testNum);
49 telemetry.addData("Test:", currTest.getDescription());
```

This gets the test and then sends its description after "Test" with telemetry so the driver station can see what test they will be running.

```
51 currTest.run(gamepad1.a, telemetry);
```

`run()` takes a boolean for whether to run the test or not. We just pass in `gamepad1.a` directly here.

### 14.3. Exercises

1. Add a test for the touchSensor. you'll need a `TestDigitalChannel` class and add it to the `getTests()` method in `ProgrammingBoard`. (No change needed to `OpMode`)
2. Add a test for the servo, you'll need a `TestServo` class - hint your constructor probably needs an "on" value and an "off" value for the servo. You'll also need to add it to the `getTests()`
3. Change `ProgrammingBoard2` through `ProgrammingBoard9` to derive from the one before it (ie, `ProgrammingBoard2` extends `ProgrammingBoard1`) adding only what is necessary each time. Make sure all your `OpModes` still work!! (Hint: you'll have to change private members to protected so the child can access it)<sup>2</sup>

<sup>2</sup>The reason we didn't do this in the book is that you would likely only have the most recent version of a mechanism in your code instead of multiple versions.

{114}------------------------------------------------

## 15. Rumble with Gamepad

Starting with FTC SDK 7.0, the Gamepad is no longer only an input device. If you are using a gamepad that has rumble support, you can send information back to the humans holding the controller. You'll remember that in our OpMode, the gamepads are gamepad1 and gamepad2.

There are several simple ways to use the rumble. First, you can simply set the rumble with the amount of time to rumble. If the gamepad is currently rumbling, it is replaced with this amount of time. For example:

Listing 15.1: GamepadSimpleRumbleOpMode.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class GamepadSimpleRumbleOpMode extends OpMode {
    @Override
    public void init() {
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            gamepad1.rumble(100);
        }
    }
}
```

Second, you can send a number of blips. For this to work well, you want to make sure you aren't interrupting your own pattern. Here is an example:

Listing 15.2: GamepadRumbleBlipsOpMode.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
```

{115}------------------------------------------------

## 15. Rumble with Gamepad

```
public class GamepadRumbleBlipsOpMode extends OpMode {  
    boolean wasA;  
  
    @Override  
    public void init() {  
    }  
  
    @Override  
    public void loop() {  
        if (gamepad1.a && !wasA) {  
            gamepad1.rumbleBlips(3);  
        }  
        wasA = gamepad1.a;  
    }  
}
```

Third, if you have a gamepad that supports it you can even send different amounts of rumble to the left and right rumble. Be aware that the programmer can make things more subtle than the driver can probably detect during a match. This simple example changes the intensity of the rumble based off of how far the triggers are pushed in.

Listing 15.3: GamepadRumbleOpMode.java

```
package org.firstinspires.ftc.teamcode;  
  
import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
import com.qualcomm.robotcore.hardware.Gamepad;  
  
@TeleOp()  
public class GamepadRumbleOpMode extends OpMode {  
    @Override  
    public void init() {  
    }  
  
    @Override  
    public void loop() {  
        telemetry.addLine("Press left trigger for left rumble, and right trigger for  
        right rumble");  
        gamepad1.rumble(gamepad1.left_trigger, gamepad1.right_trigger, Gamepad.  
        RUMBLE_DURATION_CONTINUOUS);  
    }  
}
```

There could be a number of reasons why using gamepad rumble might be useful. For example: the robot might want to let the driver know when it has successfully picked up a piece. Another common reason is to give EVEN more

{116}------------------------------------------------

feedback about entering endgame. For example, here is an opmode that does just that.

Listing 15.4: GamepadRumbleEndGameOpMode.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class GamepadRumbleEndGameOpMode extends OpMode {
    boolean inEndGame;
    double endGameTime;

    @Override
    public void init() {
        inEndGame = false;
    }

    @Override
    public void start() {
        endGameTime = getRuntime() + 90;
    }

    @Override
    public void loop() {
        if ((getRuntime() > endGameTime) && !inEndGame) {
            gamepad1.rumbleBlips(3);
            inEndGame = true;
        }
    }
}
```

## 15.1. Exercises

1. Write a program that rumbles when the touch sensor is first pressed. (Use one of the programming board files from earlier that has a touch sensor)

{117}------------------------------------------------

![](_page_117_Picture_0.jpeg)

{118}------------------------------------------------

## 16. Computer Vision

Typically in FTC, there is a task where you need to determine the placement of an object in order to do something in autonomous. While there are lots of ways to solve this, often OpenCV (CV stands for Computer Vision) is a simple and elegant way to solve the problem.

Prior to FTC SDK 8.2 (in preparation for the CENTERSTAGE season - 2023-2024), you had to install EasyOpenCV. However, now it is included in the FTC SDK. Not only that, but there is new code for a VisionPortal which makes things like AprilTags and OpenCV pipelines much easier. It also makes TensorFlow easier, but our experience is that AprilTags and OpenCV are typically much easier to get to work than TensorFlow.

OpenCV basically looks at each frame from the camera and makes a matrix out of the color values from the camera for each pixel. Once we have sensor data as numbers, we own it and can do a lot with it.

OpenCV is a HUGE subject, and we will not fully address it in this short chapter. There are entire books written about OpenCV. Luckily for most years, you can accomplish the autonomous determination of the placement of an object with a very simple program.

But before we even look at OpenCV, let's take a look at AprilTags where the FTC SDK provides you with the ability to detect AprilTags without having to even write a pipeline.

### 16.1. April Tags

Developed at the University of Michigan, AprilTag is like a 2D barcode or a simplified QR Code. It contains a numeric ID code and can be used for location and orientation. Here is an example of the AprilTag from the family tag36h11 (what FTC has said will be used for CENTERSTAGE) for id 42.

{119}------------------------------------------------

## 16. Computer Vision

![A black and white image of an AprilTag, a square pattern used for computer vision.]()![](_page_119_Picture_1.jpeg)

For more information, see the excellent section on FTCDocs: [https://ftc-docs.firstinspires.org/en/latest/apriltag/vision\\_portal/apriltag\\_intro/apriltag-intro.html](https://ftc-docs.firstinspires.org/en/latest/apriltag/vision_portal/apriltag_intro/apriltag-intro.html)

### 16.1.1. The Opmode

Don't panic when you see all these new things, we'll explain what they do.

Listing 16.1: SimpleAprilTags.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@Autonomous()
public class SimpleAprilTags extends OpMode {
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;

    @Override
    public void init() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName,
                aprilTagProcessor);
    }

    @Override
    public void init_loop() {
        List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetetections()
                ;
        StringBuilder idsFound = new StringBuilder();
    }
}
```

{120}------------------------------------------------

```
29 for (AprilTagDetection detection : currentDetections) {  
30     idsFound.append(detection.id);  
31     idsFound.append(' ');  
32 }  
33 telemetry.addData("April Tags", idsFound);  
34 }  
35  
36 @Override  
37 public void start() {  
38     visionPortal.stopStreaming();  
39 }  
40  
41 @Override  
42 public void loop() {  
43 }  
44 }
```

You probably remember that all of the `import` statements are automatically added by Android Studio.

```
13 @Autonomous()
```

Unlike the rest of our OpModes, we are setting this one up as an Autonomous OpMode.

```
15 private AprilTagProcessor aprilTagProcessor;
```

We are creating a variable of type `AprilTagProcessor` that is named `aprilTagProcessor`. This is a processor provided by the FTC SDK that will look for April Tags and tell us about all the ones it found.

```
16 private VisionPortal visionPortal;
```

We are creating a variable of type `VisionPortal` that is named `visionPortal`. This is provided by the FTC SDK and gives us an easy way to handle the camera and get information about the stream.

Inside of `init()`, we handle the code we only want executed once.

```
20 WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
```

This line of code creates our webcam and uses our old friend `getting` from the `hardwareMap`. Here we used the default “Webcam 1”, but it has to match what your webcam is in your config file on the robot.

```
21 aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
```

This creates our processor. In this case, we are creating the `AprilTagProcessor` with all the defaults. To see examples of how to create with other than the

{121}------------------------------------------------

## 16. Computer Vision

defaults, you can look at the ConceptAprilTag sample that comes with the FTC SDK.

```
22 visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, ←
    ↳ aprilTagProcessor);
```

This creates our vision Portal. In this case, we are creating it with all defaults and then passing in our webCam and our aprilTagProcessor. That is all that is needed in our `init` method.

The next thing you probably noticed is we have code in the `init_loop()` method. This is called over and over again after the `opMode` has gone through `init` but before play has been pressed. For many years, you want to do your vision processing here so as soon as autonomous starts you have already acquired the vision of what to do.

```
27 List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections()←
    ↳ ;
```

This line is getting us the list of april tags that were detected by the aprilTagProcessor. (This list could be empty) You may not have seen the angle brackets `<` and `>` before. This lets us say that the List has to contain elements of a certain type which is inside the angle brackets.<sup>1</sup>

```
28 StringBuilder idsFound = new StringBuilder();
```

This line is creating a variable `idsFound` of type `StringBuilder`. The reason this is not of type `String` is that we are going to do a lot of appending to it and this is a more efficient way of doing that.

```
29 for (AprilTagDetection detection : currentDetections) {
```

This line is saying for each detection found, go through the loop with the variable `detection` equal to that place in the list.

```
30 idsFound.append(detection.id);
31 idsFound.append(' '');
```

These lines put the id of the april tag found followed by a space. The reason we follow it by the space is because otherwise we wouldn't be able to tell the difference of one found with an id of 13 or two found with ids of 1 and 3. The reason we use a space instead of a comma is that nobody will notice a space at the end, but a comma at the end looks strange.

```
33 telemetry.addData("April Tags", idsFound);
```

<sup>1</sup>Technically this is a template class, where the class gets built when you use it for the first time. But templating is outside of the scope of this book.

{122}------------------------------------------------

This puts the string out on telemetry. If no ids were found, then the variable `idsFound` will be empty.

We also have code in our `start()` method. You'll remember that it is called once when the play button is pressed.

```
38 visionPortal.stopStreaming();
```

Here we stop the streaming, so we won't be using up the processor power once our autonomous starts. That is all you have to do for AprilTags.

## 16.2. The empty processor

That is very cool, but what about making our own vision processor. It turns out we can do that!

Every processor has to implement the `VisionProcessor` interface. That means you have to have a method for each method described in the interface. This is similar to deriving from an abstract class like we do with `OpMode`. The main differences are that a class can only derive from one class, but can implement multiple interfaces. Instead of using the keyword `extends` like you do for deriving from a class, you use the keyword `implements`.

In order to implement the `VisionProcessor` interface, your class has to have 3 methods. Below is the full code for an empty processor.

Listing 16.2: `EmptyProcessor.java`

```
1 package org.firstinspires.ftc.teamcode.processors;
2
3 import android.graphics.Canvas;
4
5 import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
6 import org.firstinspires.ftc.vision.VisionProcessor;
7 import org.opencv.core.Mat;
8
9 public class EmptyProcessor implements VisionProcessor {
10     @Override
11     public void init(int width, int height, CameraCalibration calibration) {
12     }
13
14     @Override
15     public Object processFrame(Mat frame, long captureTimeNanos) {
16         return null;
17     }
18
19     @Override
```

{123}------------------------------------------------

## 16. Computer Vision

```
21 public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, ←
    ← float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext)←
    ← {
22 }
23 }
```

The first method is `init`. It takes the width, the height, and the calibration (camera calibration data). This is guaranteed to be called before the other methods and is a good place for things you need to do once.

The second method is `processFrame`. It gets called once per frame of video. It receives two parameters. The first is `frame` of type `Mat` which is a Matrix that describes the frame. This is an OpenCV type. The second is the time at which the frame was captured in nanoseconds. A nano second is one billionth of a second. You will probably get this call about 30 times a second, so expect this number to be significantly larger each time. Most of the time, you probably can ignore this. It returns a variable which is an `Object` which means you can return anything that is derived from an `Object`. All classes in Java derive from `Object` even without your saying that explicitly. One very important thing that is different about `processFrame` compared to a typical OpenCV pipeline is that you don't want to modify the frame being passed in because all other processors will also be getting the same frame.

The third method is `onDrawFrame`. This allows us to draw on top of the frame to be seen both on the camera view and on the image you can see on the driver station.

For the camera view, you can either plug an HDMI cable into the Control Hub (easiest way) or you can use `scrcpy` - <https://github.com/Genymobile/scrcpy> which will let you see the screen wirelessly.

On the driver station, you can click on the three dots for the main menu to view the camera stream (this only works while in `init`, once you click play it won't let you see this.) This only updates each time you touch the screen.

`onDrawFrame` receives several parameters.

1. `canvas` of type `Canvas` which is what we will draw on.
2. `onScreenWidth` - this is the width of the canvas in pixels
3. `onScreenHeight` - this is the height of the canvas in pixels
4. `scaleBmpPxToCanvasPx` - this helps us convert from the coordinates in the `processFrame` to those on the canvas
5. `scaleCanvasDensity` - this lets us draw text annotations that are the same regardless of the screen size

{124}------------------------------------------------

6. userContext- this has the object that was returned by the previous processFrame.

That is all there is to a vision processor. With the basics out of the way, let's make our first vision processor.

## 16.3. Our first vision processor

For our first vision processor, we'll simply draw a rectangle on top of the camera feed. This will require us to create a processor and make an opMode in order to use the processor.

### 16.3.1. The processor

Here is the full processor.

Listing 16.3: DrawRectangleProcessor.java

```
package org.firstinspires.ftc.teamcode.processors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class DrawRectangleProcessor implements VisionProcessor {
    public Rect rect = new Rect(20, 20, 50, 50);

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        return null;
    }

    private android.graphics.Rect makeGraphicsRect(Rect rect, float <->
        <-> scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
    }
}
```

{125}------------------------------------------------

## 16. Computer Vision

```
int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);  
  
return new android.graphics.Rect(left, top, right, bottom);  
}  
  
@Override  
public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, ←  
    ← float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext)←  
    ← {  
    Paint rectPaint = new Paint();  
    rectPaint.setColor(Color.RED);  
    rectPaint.setStyle(Paint.Style.STROKE);  
    rectPaint.setStrokeWidth(scaleCanvasDensity * 4);  
  
    canvas.drawRect(makeGraphicsRect(rect, scaleBmpPxToCanvasPx), rectPaint);  
}  
}
```

We'll start with the variables we have as members of the class

```
public Rect rect = new Rect(20, 20, 50, 50);
```

This creates a rectangle. This is in camera coordinates. One thing we have to be careful of is that both `android.graphics` and `org.opencv.core` have `Rect` classes and they are NOT the same. This one should be the OpenCV one. This is in the order: x, y, width, height of the rectangle. (where x and y are of the upper left corner)

```
private android.graphics.Rect makeGraphicsRect(Rect rect, float ←  
    ← scaleBmpPxToCanvasPx) {  
    int left = Math.round(rect.x * scaleBmpPxToCanvasPx);  
    int top = Math.round(rect.y * scaleBmpPxToCanvasPx);  
    int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);  
    int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);  
  
    return new android.graphics.Rect(left, top, right, bottom);  
}
```

This converts from our OpenCV camera rectangle to an `android.graphics.Rect`. These are different because the OpenCV rect has top, left, width and height while the `android.graphics` one has left, top, right, and bottom. Also the OpenCV one is in camera coordinates while the `android` one is in screen coordinates. You'll notice that we use `Math.round` which converts from a float to an integer (rounding down or up like you would expect based off the input. ie. 1.6 becomes 2 and 1.4 becomes 1.) We are multiplying the openCV rectangle by `scaleBmpPxToCanvasPx` for the conversion.

{126}------------------------------------------------

### 16.3. Our first vision processor

```
public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, ←
    float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext)←
    {
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(scaleCanvasDensity * 4);

        canvas.drawRect(makeGraphicsRect(rect, scaleBmpPxToCanvasPx), rectPaint);
    }
```

In this method, we go ahead and setup the rectPaint variable. You can see we set the color, the style (STROKE means just outline it, the other options are FILL (the default) or STROKE\_AND\_FILL), and the strokeWidth. The reason we set it to 4 times the scaleCanvasDensity is that way it will be easy to see regardless of how large the image we are drawing on is.<sup>2</sup>

We then draw the rectangle.

#### 16.3.2. The opmode

Here is the entire opmode, but you'll see that the only differences between it and the opMode for using the AprilTagProcessor are that this uses our DrawRectangleProcessor instead.

Listing 16.4: SimpleOpenCV.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.processors.DrawRectangleProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous()
public class SimpleOpenCV extends OpMode {

    private DrawRectangleProcessor drawRectangleProcessor;
    private VisionPortal visionPortal;

    @Override
    public void init() {
```

<sup>2</sup>Some of you are wondering why we do this every time we call onDrawFrame. We could indeed check to see if rectPaint is set and if not, go set it. That would be better, but I think less clear for teaching.

{127}------------------------------------------------

## 16. Computer Vision

```
18 drawRectangleProcessor = new DrawRectangleProcessor();
19 visionPortal = VisionPortal.easyCreateWithDefaults(
20     hardwareMap.get(WebcamName.class, "Webcam 1"), drawRectangleProcessor←
21     ↳ );
22 }
23
24 @Override
25 public void init_loop() {
26 }
27
28 @Override
29 public void start() {
30     visionPortal.stopStreaming();
31 }
32
33 @Override
34 public void loop() {
35 }
36 }
```

### 16.3.3. Bonus - using EOCVSim (Optional)

One of the fantastic things about the FTC community is that people share what they have made to help others. There is a simulator that people have made that allow you to test your processors (or OpenCV pipelines) with images (either photos or videos) that you have taken. This allows you to work even though people may be busy with the robot.

You can get the EasyOpenCV Simulator from: <https://github.com/deltacv/EOCV-Sim> It is a JAR file which you can run directly.

A few tips:

- Put your processors in a directory called processors under your teamcode. This will allow you to quickly point the simulator to that directory and it will rebuild them as the source code changes.
- Make sure to make variables public that you want to be able to change in the simulator. (After you are done tuning, you may move them back to no longer being public.)
- Changing values in the simulator does NOT change them in your code. You need to make sure to do that as well.
- You can use the simulator with the webcam on your computer, but even more helpful is the ability to point it to still images or video that you have

{128}------------------------------------------------

## 16.4. Expanding to 3 rectangles

recorded. You can take a picture from where the camera on the robot is and use that.

- Thanks to FTC Team 14169 for providing some images of their TSE from the FreightFrenzy season. These are located in the `/opencvImages` directory at <https://github.com/alan412/LearnJavaForFTC> You can use these to try out your processors.

![Screenshot of the EasyOpenCV Simulator v3.5.0-dev showing a camera view with a red rectangle drawn on the image. The simulator interface shows the Pipeline section with DrawRectangleProcessor selected, and the Sources section listing various image files.]()![](_page_128_Picture_2.jpeg)

If you notice the Tuner section, you can change values and see them take effect. Be aware that this is only for variables that are public and that they only take effect from that point forward. For example, if you are trying to be clever and create your android graphics rectangle only once and not every time through then it won't update.<sup>3</sup>

## 16.4. Expanding to 3 rectangles

We are going to expand to 3 rectangles where we can change which one is selected and draw that one in a different color. Don't forget that you'll also have to change your opMode to use this processor. First here is all the code for the new processor:

Listing 16.5: ThreeRectanglesProcessor.java

```
1 package org.firstinspires.ftc.teamcode.processors;
```

<sup>3</sup>Don't ask how I found this out....

{129}------------------------------------------------

## 16. Computer Vision

```
2
3 import android.graphics.Canvas;
4 import android.graphics.Color;
5 import android.graphics.Paint;
6
7 import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
8 import org.firstinspires.ftc.vision.VisionProcessor;
9 import org.opencv.core.Mat;
10 import org.opencv.core.Rect;
11
12 public class ThreeRectanglesProcessor implements VisionProcessor {
13     public Rect rectLeft = new Rect(110, 42, 40, 40);
14     public Rect rectMiddle = new Rect(160, 42, 40, 40);
15     public Rect rectRight = new Rect(210, 42, 40, 40);
16     Selected selection = Selected.NONE;
17
18     @Override
19     public void init(int width, int height, CameraCalibration calibration) {
20     }
21
22     @Override
23     public Object processFrame(Mat frame, long captureTimeNanos) {
24         return null;
25     }
26
27     private android.graphics.Rect makeGraphicsRect(Rect rect, float <->
28         <-> scaleBmpPxToCanvasPx) {
29         int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
30         int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
31         int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
32         int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);
33
34         return new android.graphics.Rect(left, top, right, bottom);
35     }
36
37     @Override
38     public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, <->
39         <-> float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) <->
40         <-> {
41         Paint selectedPaint = new Paint();
42         selectedPaint.setColor(Color.RED);
43         selectedPaint.setStyle(Paint.Style.STROKE);
44         selectedPaint.setStrokeWidth(scaleCanvasDensity * 4);
45
46         Paint nonSelectedPaint = new Paint(selectedPaint);
47         nonSelectedPaint.setColor(Color.GREEN);
48
49         android.graphics.Rect drawRectangleLeft = makeGraphicsRect(rectLeft, <->
50             <-> scaleBmpPxToCanvasPx);

```

{130}------------------------------------------------

#### 16.4. Expanding to 3 rectangles

```
47 android.graphics.Rect drawRectangleMiddle = makeGraphicsRect(rectMiddle, ←
48     ← scaleBmpPxToCanvasPx);
49 android.graphics.Rect drawRectangleRight = makeGraphicsRect(rectRight, ←
50     ← scaleBmpPxToCanvasPx);
51
52 switch (selection) {
53     case LEFT:
54         canvas.drawRect(drawRectangleLeft, selectedPaint);
55         canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
56         canvas.drawRect(drawRectangleRight, nonSelectedPaint);
57         break;
58     case MIDDLE:
59         canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
60         canvas.drawRect(drawRectangleMiddle, selectedPaint);
61         canvas.drawRect(drawRectangleRight, nonSelectedPaint);
62         break;
63     case RIGHT:
64         canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
65         canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
66         canvas.drawRect(drawRectangleRight, selectedPaint);
67         break;
68     case NONE:
69         canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
70         canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
71         canvas.drawRect(drawRectangleRight, nonSelectedPaint);
72         break;
73 }
74
75 public enum Selected {
76     NONE,
77     LEFT,
78     MIDDLE,
79     RIGHT
80 }
```

Most of this should look incredibly familiar. We created an enumerated type like we talked about in subsection 12.2.3. We now have 3 rectangles instead of 1, and in our onDrawFrame we draw the one that is selected in a different color.

Until we add the code in 16.5 to do the actual computer vision, all three of these rectangles will be drawn in green because the member variable selection is set to Selected.NONE. If you set it to Selected.LEFT, Selected.MIDDLE, or Selected.RIGHT then you would see the corresponding rectangle change to be red.

{131}------------------------------------------------

## 16. Computer Vision

### 16.5. Actual computer vision...

Here is a processor that does actual computer vision. Again, don't forget to change the opMode to use this one.

Listing 16.6: FirstVisionProcessor.java

```
package org.firstinspires.ftc.teamcode.processors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class FirstVisionProcessor implements VisionProcessor {
    public Rect rectLeft = new Rect(110, 42, 40, 40);
    public Rect rectMiddle = new Rect(160, 42, 40, 40);
    public Rect rectRight = new Rect(210, 42, 40, 40);
    Selected selection = Selected.NONE;

    Mat submat = new Mat();
    Mat hsvMat = new Mat();

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.cvtColor(frame, hsvMat, Imgproc.COLOR_RGB2HSV);

        double satRectLeft = getAvgSaturation(hsvMat, rectLeft);
        double satRectMiddle = getAvgSaturation(hsvMat, rectMiddle);
        double satRectRight = getAvgSaturation(hsvMat, rectRight);

        if ((satRectLeft > satRectMiddle) && (satRectLeft > satRectRight)) {
            return Selected.LEFT;
        } else if ((satRectMiddle > satRectLeft) && (satRectMiddle > satRectRight)) {
            return Selected.MIDDLE;
        }
        return Selected.RIGHT;
    }
}
```

{132}------------------------------------------------

```
43
44 protected double getAvgSaturation(Mat input, Rect rect) {
45 submat = input.submat(rect);
46 Scalar color = Core.mean(submat);
47 return color.val[1];
48 }
49
50 private android.graphics.Rect makeGraphicsRect(Rect rect, float ←-
          ,→ scaleBmpPxToCanvasPx) {
51 int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
52 int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
53 int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
54 int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);
55
56 return new android.graphics.Rect(left, top, right, bottom);
57 }
58
59 @Override
60 public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, ←-
          ,→ float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext)←-
          ,→ {
61 Paint selectedPaint = new Paint();
62 selectedPaint.setColor(Color.RED);
63 selectedPaint.setStyle(Paint.Style.STROKE);
64 selectedPaint.setStrokeWidth(scaleCanvasDensity * 4);
65
66 Paint nonSelectedPaint = new Paint(selectedPaint);
67 nonSelectedPaint.setColor(Color.GREEN);
68
69 android.graphics.Rect drawRectangleLeft = makeGraphicsRect(rectLeft, ←-
             ,→ scaleBmpPxToCanvasPx);
70 android.graphics.Rect drawRectangleMiddle = makeGraphicsRect(rectMiddle, ←-
             ,→ scaleBmpPxToCanvasPx);
71 android.graphics.Rect drawRectangleRight = makeGraphicsRect(rectRight, ←-
             ,→ scaleBmpPxToCanvasPx);
72
73 selection = (Selected) userContext;
74 switch (selection) {
75 case LEFT:
76 canvas.drawRect(drawRectangleLeft, selectedPaint);
77 canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
78 canvas.drawRect(drawRectangleRight, nonSelectedPaint);
79 break;
80 case MIDDLE:
81 canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
82 canvas.drawRect(drawRectangleMiddle, selectedPaint);
83 canvas.drawRect(drawRectangleRight, nonSelectedPaint);
84 break;
85 case RIGHT:
```

{133}------------------------------------------------

## 16. Computer Vision

```
86 canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
87 canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
88 canvas.drawRect(drawRectangleRight, selectedPaint);
89 break;
90 case NONE:
91 canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
92 canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
93 canvas.drawRect(drawRectangleRight, nonSelectedPaint);
94 break;
95 }
96 }
97
98 public Selected getSelection() {
99 return selection;
100 }
101
102 public enum Selected {
103 NONE,
104 LEFT,
105 MIDDLE,
106 RIGHT
107 }
108 }
```

We now have code inside our processFrame

```
30 Imgproc.cvtColor(frame, hsvMat, Imgproc.COLOR_RGB2HSV);
```

This converts the colorspace from RGB (Red, green, blue) to HSV (Hue, Saturation, Value). This is often a really useful colorspace for doing image detection in FTC since the background is the gray mats.<sup>4</sup> The Hue is the shade of color (useful when we are looking for something specific), the Saturation is how much of the color there is (so gray has a low saturation), and value is how bright it is. (This is sometimes called Brightness which means HSB)

```
32 double satRectLeft = getAvgSaturation(hsvMat, rectLeft);
33 double satRectMiddle = getAvgSaturation(hsvMat, rectMiddle);
34 double satRectRight = getAvgSaturation(hsvMat, rectRight);
```

Here we get the average saturation of each rectangle by calling a method we made to get the average saturation of a rectangle. Let's go there for a second

```
44 protected double getAvgSaturation(Mat input, Rect rect) {
45 submat = input.submat(rect);
46 Scalar color = Core.mean(submat);
47 return color.val[1];
48 }
```

<sup>4</sup>In the real world YUV or YCbCr is often more useful.

{134}------------------------------------------------

Here we create a submatrix based off of the rectangle passed in. We then get the average (or mean) of each pixel in this rectangle. Since we are working in HSV, the 0<sup>th</sup> is Hue, the 1<sup>st</sup> is Saturation, and the 2<sup>nd</sup> is Value.

```
36 if ((satRectLeft > satRectMiddle) && (satRectLeft > satRectRight)) {  
37     return Selected.LEFT;  
38 } else if ((satRectMiddle > satRectLeft) && (satRectMiddle > satRectRight)) {  
39     return Selected.MIDDLE;  
40 }  
41 return Selected.RIGHT;
```

This returns which rectangle has the highest saturation (ie, the least gray). By doing it this way, we can detect any type of TSE<sup>5</sup>. Often the trick in using OpenCV is figuring out what is the easiest way to look for what you need to make a decision. You'll notice that if we don't know, we return that it is the one on the right. This is because if we can't tell, we should just guess one because we have a 1 out of 3 chance of being correct. For diagnostics, you might want to not do this.

```
73 selection = (Selected) userContext;
```

Here we take the userContext and cast it to what we know is returned (our enumerated type) and we save it in our class.

```
98 public Selected getSelection() {  
99     return selection;  
100 }
```

We add this getSelection method so that the opMode can get the selection to make a decision.

### 16.5.1. The opmode

Listing 16.7: FirstVisionOpmode.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;  
4 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
5  
6 import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;  
7 import org.firstinspires.ftc.teamcode.processors.FirstVisionProcessor;  
8 import org.firstinspires.ftc.vision.VisionPortal;  
9  
10 @Autonomous()
```

<sup>5</sup>unless someone makes a TSE gray

{135}------------------------------------------------

## 16. Computer Vision

```
public class FirstVisionOpmode extends OpMode {  
  
    private FirstVisionProcessor visionProcessor;  
    private VisionPortal visionPortal;  
  
    @Override  
    public void init() {  
        visionProcessor = new FirstVisionProcessor();  
        visionPortal = VisionPortal.easyCreateWithDefaults(  
            hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);  
    }  
  
    @Override  
    public void init_loop() {  
    }  
  
    @Override  
    public void start() {  
        visionPortal.stopStreaming();  
    }  
  
    @Override  
    public void loop() {  
        telemetry.addData("Identified", visionProcessor.getSelection());  
    }  
}
```

In our opmode, you'll see that in the loop method we send to telemetry what the vision processor saw before the opmode was started.

### 16.6. Exercises

1. Using our FirstVisionProcessor, and the programming board from [chapter 8](#) write an opmode that moves a servo to 0 if the TSE is in the left rectangle, 90 if it is in the middle rect, and 180 if it is in the right rectangle.

{136}------------------------------------------------

## 17. Javadoc

We talked earlier about a special kind of comment called a Javadoc. There are several huge benefits from commenting this way. The FTC SDK is commented in this way and that is what generates the documentation.

1. Android Studio will pick it up and give help to people using your classes
2. Autogenerating documentation that will amaze the judges

There are 3 places you can put a Javadoc comment.

1. Before your class
2. Before each class member
3. Before each class method

A Javadoc comment looks like this:

```
/**  
 * This is a javadoc comment  
 */
```

If you write your class method declaration first, and then type in a /\*\* above it then it will automatically put @param for each parameter you have and a @return if your method returns anything.

```
/**  
 * gets our imu heading  
 *  
 * @param angleUnit this determines the angle unit (degrees/radians) that it will ←  
 * → return in  
 * @return returns the current angle with the offset in the angleUnit specified  
 */  
private double getHeading(AngleUnit angleUnit) {  
    Orientation angles;  
    angles = imu.getAngularOrientation(AxesReference.INTRINSIC,  
        AxesOrder.ZYX,  
        angleUnit);  
  
    return angles.firstAngle;  
}
```

{137}------------------------------------------------

## 17. Javadoc

![Lightbulb icon indicating a tip or note.]()![](_page_137_Picture_1.jpeg)

If you don't have anything more to say than the name, don't put in a comment. (For example - here is a BAD comment)

```
/* DO NOT DO THIS!!! - BAD EXAMPLE!! */  
  
/**  
 * This is the ProgrammingBoard class  
 */  
public class ProgrammingBoard{  
    ...
```

After you have done this, in Android Studio go to Tools... Generate JavaDoc... and you'll see a dialog like this:

![Screenshot of the 'Specify Generate JavaDoc Scope' dialog in Android Studio. The scope is set to 'Module 'TeamCode''. The output directory is set to '/Users/alan/Projects/javadoc'. Various checkboxes are checked, including 'Include test sources', 'Generate hierarchy tree', 'Generate navigation bar', 'Generate index', and 'Separate index per letter'. The 'Open generated documentation in browser' option is also checked.]()![](_page_137_Picture_5.jpeg)

A few changes that I recommend:

1. Do it just on Module "TeamCode"
2. Go ahead and tell it to generate the documentation on everything

{138}------------------------------------------------

3. Make sure you put it in its own directory because it creates a lot of files

## 17.1. Exercises

1. Add Javadoc comments to your ProgrammingBoard class
2. Add Javadoc comments to your TestMotor class (Because once you have TestWiring all working for your robot you'll want to show it to judges)

{139}------------------------------------------------

![](_page_139_Picture_0.jpeg)

{140}------------------------------------------------

## 18. Finding things in FTC SDK

So far, I have told you about things that are in the FTC SDK. But there is lots more that we haven't looked at. So now let's teach you how to go looking for yourself.

As FIRST has been working on shrinking the size of the SDK, they no longer ship the Javadoc with each SDK. You can find it online at <https://javadoc.io/doc/org.firstinspires.ftc> where you can either use it online or download it to your computer. (You can also run Javadoc like you did on your code, but point it to FtcRobotController as well.)

You'll probably notice that this looks just like the Javadoc you created in [chapter 17](#). Sure enough, that is what they use to create the documentation for the FTC SDK as well

For example - Look through the All Classes until you get to Telemetry in the lower left portion of the screen. Click on it. Then the main part of the browser will have more information out about our old friend. Wait did you see that there is a speak() method??

### 18.1. Exercise

1. Write an opMode that uses the telemetry.speak() method
2. Look through the documentation and find something we haven't done before and try it

{141}------------------------------------------------

![](_page_141_Picture_0.jpeg)

{142}------------------------------------------------

## 19. A few other topics

This is a place for a few other topics that I thought were important to mention but didn't really fit anywhere else

### 19.1. Math class

The java Math class has a lot of useful methods in it. They are all static so you don't need an object of type Math. Here is an example class to handle polar coordinates

Listing 19.1: Polar.java

```
package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Polar {
    double angle;
    double magnitude;

    public Polar(double x, double y) {
        angle = Math.atan2(y, x);
        magnitude = Math.hypot(x, y);
    }

    public double getAngle(AngleUnit angleUnit) {
        return angleUnit.fromRadians(angle);
    }

    public double getMagnitude() {
        return magnitude;
    }
}
```

You'll notice that we have a constructor that takes in x and y and converts it to polar coordinates.

The method called getAngle uses the AngleUnit to convert. As a bonus AngleUnit guarantees results to be normalized.

Some useful methods in this class: (all trig functions are in radians)

{143}------------------------------------------------

## 19. A few other topics

```
Math.abs(a) // take the absolute value
Math.acos(a) // take the arc cosine
Math.asin(a) // take the arc sin
Math.atan(a) // take the arc tan
Math.atan2(x, y) // This returns the angle theta from conversion of rectangular (x,y)←
    → to polar (r, theta)
Math.copySign(magnitude, sign) // returns the first argument with the sign (←
    → positive or negative) of the second
Math.cos(a) // take the cos
Math.hypot(x, y) // return the sqrt(x^2 + y^2)
Math.max(a, b) // returns the greater of a and b
Math.min(a, b) // returns the smaller of a and b
Math.random() // returns a double value with a positive sign greater than or equal ←
    → to 0.0 and less than 1.0
Math.signum(d) // returns -1.0 if d < 0, 0.0 if d == 0, 1.0 if d > 0
Math.sin(a) // take the sin
Math.sqrt(a) // take the square root
Math.tan(a) // take the tangent
Math.toDegrees(radians) // convert radians to degrees - I prefer using AngleUnit
Math.toRadians(degrees) // convert degrees to radians - I prefer using AngleUnit
```

## 19.2. final

final is a keyword that can be applied to a variable, a method or a class.

```
final int THRESHOLD = 5;
```

- final applied to a variable makes the variable a constant. Modifying it later will cause a compiler error. It either needs to be initialized immediately (or if it is part of a class, then in the constructor) By convention, we name these “variables” in ALL\_CAPS to signify that they are constants. (unless they are initialized in the constructor because then they are different per instance.)

```
public class SuperClass{
    public String a(){
        return "a";
    }
    final public String b(){
        return "b";
    }
}
```

{144}------------------------------------------------

- final applied to a method means that even if a new class extends this class, this method cannot be overridden

```
final class A{  
    // methods and members  
}
```

- final applied to a class means that no class can extend this one.

## 19.3. Make telemetry prettier

There are some additional ways we can make our telemetry easier to see. We'll mention a few of them here.

Listing 19.2: MoreTelemetry.java

```
package org.firstinspires.ftc.teamcode;  
  
import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
  
@TeleOp()  
public class MoreTelemetry extends OpMode {  
    @Override  
    public void init() {  
        telemetry.addData("Run time", "%0.2f", getRuntime());  
    }  
  
    @Override  
    public void loop() {  
        telemetry.addData("Right Joystick",  
            "x:%+.2f y:% .2f", gamepad1.right_stick_x,  
            gamepad1.right_stick_y);  
  
        telemetry.addLine("Left joystick | ")  
            .addData("x", gamepad1.left_stick_x)  
            .addData("y", gamepad1.left_stick_y);  
    }  
}
```

Let's talk through the three different telemetry examples here.

```
telemetry.addData("Run time", "%0.2f", getRuntime());
```

Here is our old friend addData but this time the string looks weird and there is another parameter. The string is called a format string. It can have text and

{145}------------------------------------------------

## 19. A few other topics

values. Every value is started with a % sign and has how to show the number. Below are the most common ones for FTC.

| Conversion | Description                                                                   |
|------------|-------------------------------------------------------------------------------|
| 'b', 'B'   | boolean - if the argument isn't a boolean, then it will show true unless null |
| 'd'        | decimal - for integers                                                        |
| 'f'        | decimal number - for floating point (float and double)                        |
| %          | show a literal '%' character                                                  |

For f, you can give it a precision after the . which is the maximum number of digits to show. If you want it to always show that number of digits (zero pad) then put 0.2 for example.

```
15 telemetry.addData("Right Joystick",
16 "x:%+.2f y:% .2f", gamepad1.right_stick_x,
17 gamepad1.right_stick_y);
```

Here is an example where we are showing more than one value on the same line. The + is a flag saying to always show the sign (without it only shows the sign if it is negative.) The space on the second one says to have a space if positive instead of the positive sign. (The reason you might want this is so that the numbers don't jump as the negative sign comes in place.)

```
19 telemetry.addLine("Left joystick | ")
20 .addData("x", gamepad1.left_stick_x)
21 .addData("y", gamepad1.left_stick_y);
```

This has another way of showing multiple things per line by putting multiple addData after an addLine

## 19.4. Interfaces (implements)

Interfaces are similar to inheritance but are subtly different. Whereas a child class is a type of its super class, an interface is instead a “contract” that a class that implements it has to have certain method(s). A class can both be derived from a super class and implement multiple interfaces.

Let's give an example<sup>1</sup>, first showing how we create an interface:

<sup>1</sup>Yes, this is a contrived example, because you can always get the full name of a class in Java with `this.getClass().getName()` and just the last part of the class with `this.getClass().getSimpleName()`

{146}------------------------------------------------

Listing 19.3: SampleInterface.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 public interface SampleInterface {
4     public String getName();
5 }
```

and then here is a class that implements the interface

Listing 19.4: SampleClass.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 public class SampleClass implements SampleInterface {
4     @Override
5     public String getName() {
6         return "SampleClass";
7     }
8 }
```

If your class says it implements an interface, but it doesn't have all of the methods in it then the compiler will give an error.

#### 19.4.1. When to use an interface instead of an abstract class?

The short version is that if you need code shared then it should be an abstract class because in an interface, each class that implements it will have to have the code in it again.

Another way to think about it is that a class can only inherit from one class, but it can implement multiple interfaces.

My personal opinion is that you are probably better off using simple inheritance unless you have something you need to do that requires multiple interfaces.

## 19.5. Exercises

1. Use the Polar class to make a new OpMode that reports the joysticks on the gamepad in polar coordinates - show the angle in degrees
2. Add the final keyword to various places to cause compiler errors so you can see what they look like
3. Use formatting so the telemetry for exercise 1 always shows the positive or negative sign and no decimals

{147}------------------------------------------------

![](_page_147_Picture_0.jpeg)

{148}------------------------------------------------

## 20. Making Robots Drive

I have had people tell me that while this book has been helpful to them, that it would be nice to have a section on making robots drive. The beginning thing we need to think about is that it is difficult to be the driver in an FTC match. For 2 minutes everyone is staring at you and all of your mistakes. So as the programmers we need to think about how to make life as easy as possible for our drivers by coming up with control schemes that make it harder for them to make mistakes.

### 20.1. 2 motor drive

#### 20.1.1. Two Motor Drive Mechanism

The easiest way to make a robot drive is to have one powered wheel on each side. (The most common way to do this is with unpowered wheels on the front of the robot and the powered ones in the back.)

First, we'll create the mechanism.

Listing 20.1: TwoMotorDrive.java

```
package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TwoMotorDrive {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public void init(HardwareMap hardwareMap) {
        leftMotor = hardwareMap.get(DcMotor.class, "left_motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right_motor");

        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
```

{149}------------------------------------------------

## 20. Making Robots Drive

```
19 public void setPowers(double leftPower, double rightPower) {  
20     double largest = 1.0;  
21     largest = Math.max(largest, Math.abs(leftPower));  
22     largest = Math.max(largest, Math.abs(rightPower));  
23  
24     leftMotor.setPower(leftPower / largest);  
25     rightMotor.setPower(rightPower / largest);  
26 }  
27 }  
28 }
```

Now, we'll go through this one bit at a time

```
8 private DcMotor leftMotor;  
9 private DcMotor rightMotor;
```

Here we define our two motors. Notice that we gave them names that make sense. Also, they are private which means that only methods inside the class can use them.

```
11 public void init(HardwareMap hardwareMap) {  
12     leftMotor = hardwareMap.get(DcMotor.class, "left_motor");  
13     rightMotor = hardwareMap.get(DcMotor.class, "right_motor");  
14  
15     leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  
16     rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  
17     leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);  
18 }
```

This gets our motors from the hardwareMap, sets them as using encoders, and then also sets the left one as being REVERSE which allows us to treat it with respect to the wheel instead of in respect to the motor.

![Lightbulb icon indicating a tip or note.]()![](_page_149_Picture_7.jpeg)

If your robot drives backwards with this code, change which motor you have set to be REVERSE

```
20 public void setPowers(double leftPower, double rightPower) {  
21     double largest = 1.0;  
22     largest = Math.max(largest, Math.abs(leftPower));  
23     largest = Math.max(largest, Math.abs(rightPower));  
24  
25     leftMotor.setPower(leftPower / largest);  
26     rightMotor.setPower(rightPower / largest);  
27 }
```

You might have expected this to just set the power of each motor. The problem with doing this is that turns are determined by the relative speeds of the motor. If you send 1.2 as the speed to the motor, the motor will treat it as 1.0.

{150}------------------------------------------------

So this code makes sure that the values being sent to the motors are within the range -1..1 (inclusive)

### 20.1.2. OpMode

Some teams use what is called “TankDrive” and map each joystick to each motor. While this is the easiest way to program, we can do much better than that. We’ll make what is called “ArcadeDrive”. In this way, the amount the stick is forward or back determines how fast it goes forward or reverse and the amount to the side determines how much it turns at the same time.

Listing 20.2: ArcadeDrive.java

```
package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.TwoMotorDrive;

@TeleOp()
public class ArcadeDrive extends OpMode {
    TwoMotorDrive drive = new TwoMotorDrive();

    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double right = gamepad1.left_stick_x;

        drive.setPowers(forward + right, forward - right);
    }
}
```

Now we’ll go through some of the interesting pieces.

```
TwoMotorDrive drive = new TwoMotorDrive();
```

We have our mechanism as a member. This allows us to have the details of the drive in the TwoMotorDrive class and makes each class simpler.

```
@Override
public void loop() {
    double forward = -gamepad1.left_stick_y;
```

{151}------------------------------------------------

## 20. Making Robots Drive

```
20 double right = gamepad1.left_stick_x;
```

Here we call `drive.setPowers` with the amount of forward plus the amount of right to the left wheel and the amount of forward minus the amount of right to the right wheel. (You can convince yourself this is correct because to turn right the left wheel needs to travel further.)

You can try this in the simulator or on an actual robot. Many people will feel that this turns too fast. But the joystick is just providing us a number. We can make it different easily.

Listing 20.3: BetterArcadeDrive.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.TwoMotorDrive;
7
8 @TeleOp()
9 public class BetterArcadeDrive extends OpMode {
10     TwoMotorDrive drive = new TwoMotorDrive();
11
12     @Override
13     public void init() {
14         drive.init(hardwareMap);
15     }
16
17     @Override
18     public void loop() {
19         double forward = -gamepad1.left_stick_y;
20         double right = gamepad1.left_stick_x / 2;
21
22         drive.setPowers(forward + right, forward - right);
23     }
24 }
```

You'll notice that we only changed line 19 and now it turns slower. We could do all sorts of things such as allow it to turn at full speed when a button was pressed. That will be one of the exercises.

## 20.2. 4 motor mecanum drive

Mecanum drive is a very cool (albeit expensive wheels) that allows you to drive in every direction. This is what the cool kids call a “holonomic drive” Part of what makes this drive so cool is that the complication is in the wheels (bought)

{152}------------------------------------------------

and the wheels are easy to mount. I recommend the GoBilda chassis (make sure you have gotten your FTC discount)

But nothing in life is free. For this incredible flexibility, you give up some acceleration and traction.

![Diagram of a 4-wheel mecanum drive chassis. The wheels are mounted at 45 degrees to the chassis corners. Red arrows indicate the direction of force vectors applied to the wheels.]()![](_page_152_Picture_3.jpeg)

The wheels have bearings at 45 degrees. You can do the vector math to convince yourself, but courtesy of FTC16072 (Quantum Quacks) it is:

$$\begin{aligned}leftFrontPower &= forward + right + rotate \\ rightFrontPower &= forward - right - rotate \\ leftBackPower &= forward - right + rotate \\ rightBackPower &= forward + right - rotate\end{aligned}$$

### 20.2.1. Mecanum Mechanism

First, we'll create the mechanism.

Listing 20.4: MecanumDrive.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;
2
3 import com.qualcomm.robotcore.hardware.DcMotor;
4 import com.qualcomm.robotcore.hardware.HardwareMap;
5
6 public class MecanumDrive {
7     private DcMotor frontLeftMotor;
8     private DcMotor frontRightMotor;
9     private DcMotor backLeftMotor;
10    private DcMotor backRightMotor;
11
12    public void init(HardwareMap hardwareMap) {
13        frontLeftMotor = hardwareMap.dcMotor.get("front_left_motor");
14        frontRightMotor = hardwareMap.dcMotor.get("front_right_motor");
15        backLeftMotor = hardwareMap.dcMotor.get("back_left_motor");
```

{153}------------------------------------------------

## 20. Making Robots Drive

```
backRightMotor = hardwareMap.dcMotor.get("back_right_motor");

backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);

frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
}

private void setPowers(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
    double maxSpeed = 1.0;
    maxSpeed = Math.max(maxSpeed, Math.abs(frontLeftPower));
    maxSpeed = Math.max(maxSpeed, Math.abs(frontRightPower));
    maxSpeed = Math.max(maxSpeed, Math.abs(backLeftPower));
    maxSpeed = Math.max(maxSpeed, Math.abs(backRightPower));

    frontLeftPower /= maxSpeed;
    frontRightPower /= maxSpeed;
    backLeftPower /= maxSpeed;
    backRightPower /= maxSpeed;

    frontLeftMotor.setPower(frontLeftPower);
    frontRightMotor.setPower(frontRightPower);
    backLeftMotor.setPower(backLeftPower);
    backRightMotor.setPower(backRightPower);
}

// Thanks to FTC16072 for sharing this code!!
public void drive(double forward, double right, double rotate) {
    double frontLeftPower = forward + right + rotate;
    double frontRightPower = forward - right - rotate;
    double backLeftPower = forward - right + rotate;
    double backRightPower = forward + right - rotate;

    setPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
}

```

Now, we'll go through this one bit at a time.

```
private DcMotor frontRightMotor;
private DcMotor backLeftMotor;
private DcMotor backRightMotor;

```

Here we define our four motors. Notice that we gave them names that make sense. Also, they are private which means that only methods inside the class

{154}------------------------------------------------

can use them.

```
frontLeftMotor = hardwareMap.dcMotor.get("front_left_motor");
frontRightMotor = hardwareMap.dcMotor.get("front_right_motor");
backLeftMotor = hardwareMap.dcMotor.get("back_left_motor");
backRightMotor = hardwareMap.dcMotor.get("back_right_motor");

backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);

frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
}
```

This gets our motors from the hardwareMap, sets them as using encoders, and then also sets the left ones as being REVERSE which allows us to treat it with respect to the wheel instead of in respect to the motor.

![Lightbulb icon indicating a tip or note.]()![](_page_154_Picture_4.jpeg)

If your robot drives backwards with this code, change which motors you have set to be REVERSE

```
double maxSpeed = 1.0;
maxSpeed = Math.max(maxSpeed, Math.abs(frontLeftPower));
maxSpeed = Math.max(maxSpeed, Math.abs(frontRightPower));
maxSpeed = Math.max(maxSpeed, Math.abs(backLeftPower));
maxSpeed = Math.max(maxSpeed, Math.abs(backRightPower));

frontLeftPower /= maxSpeed;
frontRightPower /= maxSpeed;
backLeftPower /= maxSpeed;
backRightPower /= maxSpeed;

frontLeftMotor.setPower(frontLeftPower);
frontRightMotor.setPower(frontRightPower);
backLeftMotor.setPower(backLeftPower);
backRightMotor.setPower(backRightPower);
}
```

You might have expected this to just set the power of each motor. The problem with doing this is that turns are determined by the relative speeds of the motor. If you send 1.2 as the speed to the motor, the motor will treat it as 1.0. So this code makes sure that the values being sent to the motors are within the range -1..1 (inclusive)

```
public void drive(double forward, double right, double rotate) {
    double frontLeftPower = forward + right + rotate;
```

{155}------------------------------------------------

## 20. Making Robots Drive

```
48 double frontRightPower = forward - right - rotate;
49 double backLeftPower = forward - right + rotate;
50 double backRightPower = forward + right - rotate;

51 setPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
53 }
54 }
```

You'll notice that this looks very similar to the formulas given up above, but this time it is in code.

### 20.2.2. Robot oriented driving

While you can use lots of schemes for driving and you should come up with what makes the most sense for your team, a common scheme is to use the left joystick for moving the robot and the right joystick for rotating the robot.

Listing 20.5: SimpleMecanumDriveOpMode.java

```
1 package org.firstinspires.ftc.teamcode.opmodes;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
7
8 @TeleOp()
9 public class SimpleMecanumDriveOpMode extends OpMode {
10 MecanumDrive drive = new MecanumDrive();
11
12 @Override
13 public void init() {
14 drive.init(hardwareMap);
15 }
16
17 @Override
18 public void loop() {
19 double forward = -gamepad1.left_stick_y;
20 double right = gamepad1.left_stick_x;
21 double rotate = gamepad1.right_stick_x;
22
23 drive.drive(forward, right, rotate);
24 }
25 }
```

Now we'll go through some of the interesting pieces.

```
10 MecanumDrive drive = new MecanumDrive();
```

{156}------------------------------------------------

We have our mechanism as a member. This allows us to have the details of the drive in the MecanumDrive class and makes each class simpler.

```
public void loop() {  
    double forward = -gamepad1.left_stick_y;  
    double right = gamepad1.left_stick_x;  
    double rotate = gamepad1.right_stick_x;  
  
    drive.drive(forward, right, rotate);  
}
```

This has us read the joysticks and use it to drive the robot. Simple enough!!

### 20.2.3. Field oriented driving

This is all fine and good, but can we take advantage of the fact that the robot can drive in any direction to make it drive field relative? Of course...

Listing 20.6: FieldRelativeMecanumDriveOpMode.java

```
package org.firstinspires.ftc.teamcode.opmodes;  
  
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;  
import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
import com.qualcomm.robotcore.hardware.IMU;  
  
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;  
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;  
  
@TeleOp()  
public class FieldRelativeMecanumDriveOpMode extends OpMode {  
    MecanumDrive drive = new MecanumDrive();  
    IMU imu;  
  
    @Override  
    public void init() {  
        drive.init(hardwareMap);  
  
        imu = hardwareMap.get(IMU.class, "imu");  
        RevHubOrientationOnRobot revHubOrientationOnRobot =  
            new RevHubOrientationOnRobot(RevHubOrientationOnRobot.  
                LogoFacingDirection.UP,  
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);  
  
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));  
    }  
  
    private void driveFieldRelative(double forward, double right, double rotate) {
```

{157}------------------------------------------------

## 20. Making Robots Drive

```
29 double robotAngle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS←
    ↳ );
30 // convert to polar
31 double theta = Math.atan2(forward, right);
32 double r = Math.hypot(forward, right);
33 // rotate angle
34 theta = AngleUnit.normalizeRadians(theta - robotAngle);
35
36 // convert back to cartesian
37 double newForward = r * Math.sin(theta);
38 double newRight = r * Math.cos(theta);
39
40 drive.drive(newForward, newRight, rotate);
41 }
42
43 @Override
44 public void loop() {
45 double forward = -gamepad1.left_stick_y;
46 double right = gamepad1.left_stick_x;
47 double rotate = gamepad1.right_stick_x;
48
49 driveFieldRelative(forward, right, rotate);
50 }
51 }
```

Instead of going through everything, we'll only talk about the new bits. For details about using the IMU, see [chapter 11](#).

A little math refresher (or new math if you haven't seen it before.) Typically we work with coordinates in the x and y (called Cartesian), but you can also work with r and  $\theta$ . Where  $\theta$  is the angle and r is the distance.

NOTE TO ANYONE WHO WANTS TO RETOUCH THIS PLEASE REMEMBER TO UNGROUP THE PLANE

![Diagram showing a polar coordinate system centered at the origin. The horizontal axis is labeled X, and the vertical axis is labeled Y. Concentric circles represent distance (r), and radial lines represent angle (theta) in degrees, ranging from 0° to 360°. Key angles marked are 0°, 45°, 90°, 135°, 180°, 225°, 270°, and 315°.]()![](_page_157_Figure_5.jpeg)

Hopefully this helps you see how you can cover the same space with a different coordinate system (called Polar)

In this coordinate scheme rotating is easy, you just change the angle. So how do we convert from Cartesian to Polar and back again? With our friends from trigonometry...

{158}------------------------------------------------

![Diagram showing a point P in a Cartesian coordinate system (x, y). The origin is O. The distance from O to P is r. The angle between the x-axis and the line segment OP is theta. The x-coordinate of P is r cos(theta) and the y-coordinate is r sin(theta).]()![](_page_158_Picture_1.jpeg)

```
30 // convert to polar
31 double theta = Math.atan2(forward, right);
32 double r = Math.hypot(forward, right);
```

So this code changes from Cartesian to Polar by discovering what the angle is (using arctan, `Math.atan2` is the method in our math library that does that for us) and the hypotenuse. (We could have done  $\sqrt{a^2 + b^2}$  but it is much simpler and cleaner to just use the `Math.hypot` method)

```
33 // rotate angle
34 theta = AngleUnit.normalizeRadians(theta - robotAngle);
```

Then we rotate it by subtracting our angle of the gyro from the angle in our polar. (`normalizeRadians` is code that makes sure our angle is between  $-\pi$  and  $\pi$ . Yes, that is not really 0 to 360 like I showed before. If you really want to use degrees, you can.)

```
36 // convert back to cartesian
37 double newForward = r * Math.sin(theta);
38 double newRight = r * Math.cos(theta);
```

This converts it back to cartesian, exactly like the diagram above.

```
40 drive.drive(newForward, newRight, rotate);
```

and then we simply call our drive method with the new values.

## 20.3. Exercises

1. Make the arcade drive (2 wheel drive) only be able to go half as fast unless the A button is pressed in and then it can go full speed.
2. Change the mecanum drive to only rotate at half the speed
3. **CHALLENGE** - Make the right joystick snap the robot to the angle of where the joystick is pressed - sample solution NOT given. HINT: you'll need to get the theta of the joystick and figure out which way to make the robot rotate to get closer.

{159}------------------------------------------------

![](_page_159_Picture_0.jpeg)

{160}------------------------------------------------

## 21. Some hardware to help with Odometry

Odometry is the use of data from motion sensors to estimate change in position over time.<sup>1</sup>

There is some new hardware available for FTC students starting with FTC SDK 9.2. (Released in Summer of 2024)

### 21.1. OctoQuad

#### 21.1.1. What is it?

An OctoQuad is a device that allows you to plug in up to 8 encoders and read the value of them over I2C. This allows us to read from encoders without giving up the precious encoder ports that go along with the motors. This allows us to use encoders for things where they may not have made sense before.

#### 21.1.2. Using it simply

The easiest thing we can do is to get a single position from the OctoQuad.

Listing 21.1: SimpleOctoQuadOpMode.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;
4 import com.qualcomm.hardware.digitalchickenlabs.OctoQuadBase;
5 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
6 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
7
8 @TeleOp
9 public class SimpleOctoQuadOpMode extends OpMode {
10     OctoQuad octoQuad;
11     final int ENCODER_POSITION = 0;
12
13     @Override
14     public void init() {
15         octoQuad = hardwareMap.get(OctoQuad.class, "octoquad");

```

<sup>1</sup>From Wikipedia: <https://en.wikipedia.org/wiki/Odometry>

{161}------------------------------------------------

## 21. Some hardware to help with Odometry

```
16    octoQuad.setSingleEncoderDirection(  
17        ENCODER_POSITION, OctoQuadBase.EncoderDirection.FORWARD);  
18    octoQuad.saveParametersToFlash();  
19    octoQuad.resetAllPositions();  
20}  
21  
22    @Override  
23    public void loop() {  
24        telemetry.addData("Octoquad position", octoQuad.readSinglePosition(  
25            ← ENCODER_POSITION));  
26    }  
27}
```

Let's go through this a bit at a time and explain all the pieces.

```
10    OctoQuad octoQuad;
```

This should look familiar. Just like we have a servo of class `Servo` and a motor of class `DcMotor`, we have a variable named `octoQuad` of class `OctoQuad`

```
11    final int ENCODER_POSITION = 0;
```

Good practice is to avoid magic numbers, so here we define which port our encoder is plugged into in the OctoQuad. (If it is plugged into a different one, change it here)

Like all opmodes, we have an `init` and a `loop`.

Let's look at the `init` first.

```
16    octoQuad.setSingleEncoderDirection(  
17        ENCODER_POSITION, OctoQuadBase.EncoderDirection.FORWARD);  
18    octoQuad.saveParametersToFlash();  
19    octoQuad.resetAllPositions();
```

This is just like every other piece of hardware we have gotten access to except for it is of type `OctoQuad`.

```
17    octoQuad.setSingleEncoderDirection(  
18        ENCODER_POSITION, OctoQuadBase.EncoderDirection.FORWARD);  
19    octoQuad.saveParametersToFlash();
```

We should set the encoder direction. If the numbers are counting down when you expect them to count up, set this as `REVERSE` instead. We do this every time just in case another opmode has changed it to something else before this one runs.

```
19    octoQuad.resetAllPositions();
```

We save it to flash so that if there is a power blip the OctoQuad will come back with the right settings. (Don't worry about writing to flash unnecessarily. The OctoQuad is smart enough that if you give it the same parameters it had before, it won't actually rewrite the flash.)

{162}------------------------------------------------

```
20 }
```

This resets all counters to zero, so we know it is only counting the ones from here forward.

```
24 telemetry.addData("Octoquad position", octoQuad.readSinglePosition(←  
    ← ENCODER_POSITION));  
25 }  
26 }
```

Our loop doesn't do anything except display on telemetry the encoder position.

### 21.1.3. Using it to get multiple encoders

Probably the most common example for why someone would want to use an OctoQuad has to do with using tracking wheels. (Often called dead-wheel odometry in FTC). In this case, we would have three tracking wheels hooked up to encoders. I recommend the GoBilda Odometry Pod - <https://www.gobilda.com/odometry-pod-43mm-width-48mm-wheel/>

Here is an example that shows all three.

Listing 21.2: MultipleOctoQuadOpMode.java

```
1 package org.firstinspires.ftc.teamcode;  
2  
3 import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;  
4 import com.qualcomm.hardware.digitalchickenlabs.OctoQuadBase;  
5 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
6 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
7  
8 @TeleOp  
9 public class MultipleOctoQuadOpmode extends OpMode {  
10     final int ENCODER_POSITION_LEFT = 0;  
11     final int ENCODER_POSITION_RIGHT = 1;  
12     final int ENCODER_POSITION_CROSS = 2;  
13     OctoQuad octoQuad;  
14  
15     @Override  
16     public void init() {  
17         octoQuad = hardwareMap.get(OctoQuad.class, "octoquad");  
18         octoQuad.setSingleEncoderDirection(  
19             ENCODER_POSITION_LEFT, OctoQuadBase.EncoderDirection.REVERSE);  
20         octoQuad.setSingleEncoderDirection(  
21             ENCODER_POSITION_RIGHT, OctoQuadBase.EncoderDirection.FORWARD);  
22         octoQuad.setSingleEncoderDirection(  
23             ENCODER_POSITION_CROSS, OctoQuadBase.EncoderDirection.FORWARD);
```

{163}------------------------------------------------

## 21. Some hardware to help with Odometry

```
24
25    octoQuad.saveParametersToFlash();
26    octoQuad.resetAllPositions();
27 }
28
29 @Override
30 public void loop() {
31     int[] positions = octoQuad.readAllPositions();
32     telemetry.addData("Left Position", positions[ENCODER_POSITION_LEFT]);
33     telemetry.addData("Right Position", positions[ENCODER_POSITION_RIGHT]);
34     telemetry.addData("Cross Position", positions[ENCODER_POSITION_CROSS]);
35 }
36 }
```

The only thing here that probably isn't obvious from before is that we read all the positions. While we could use `octoQuad.readPositionRange` for a slight optimization if they are all in order, it is so slight that it probably isn't worth the effort.

### 21.1.4. Using the cached attribute for clean programming

We might have multiple mechanisms that each use the OctoQuad for different things. For example, let's say that we have three Odometry pods. Here is a recommended way you might do that.

You might start with an OdometryPod class that describes an OdometryPod

Listing 21.3: OdometryPod.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;
2
3 import com.qualcomm.hardware.digitalchickenlabs.CachingOctoQuad;
4 import com.qualcomm.hardware.digitalchickenlabs.OctoQuadBase;
5
6 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
7
8 public class OdometryPod {
9     private final CachingOctoQuad octoQuad;
10     private final int channel;
11     private final double TICKS_PER_REV = 2000;
12     private final double WHEEL_DIAMETER_CM = 4.8; // 48 MM wheel is 4.8 CM
13     private final double WHEEL_CIRCUMFERENCE_CM = Math.PI * WHEEL_DIAMETER_CM;
14     private final double CM_PER_ENCODER_TICK = WHEEL_CIRCUMFERENCE_CM / TICKS_PER_REV
15         <- ;
16
17     OdometryPod(CachingOctoQuad octoQuad, int channel, OctoQuadBase.EncoderDirection <-
18         direction) {
19         this.channel = channel;
```

{164}------------------------------------------------

```
18 this.octoQuad = octoQuad;
19 octoQuad.setSingleEncoderDirection(channel, direction);
20 }
21
22 private int readPosition() {
23     return octoQuad.readSinglePosition_Caching(channel);
24 }
25
26 public double getDistance(DistanceUnit distanceUnit) {
27     return distanceUnit.fromCm(readPosition()) * CM_PER_ENCODER_TICK;
28 }
29 }
```

One of the neat things about classes is that we can have multiple instances. So we only need to describe an Odometry Pod once and then we can have three instances instead of having three different classes with a lot of repeated code.

```
8 public class OdometryPod {
```

You'll notice that we call it a CachingOctoQuad here which gives us a different interface.

```
10 private final int channel;
11 private final double TICKS_PER_REV = 2000;
12 private final double WHEEL_DIAMETER_CM = 4.8; // 48 MM wheel is 4.8 CM
13 private final double WHEEL_CIRCUMFERENCE_CM = Math.PI * WHEEL_DIAMETER_CM;
```

This is the math that helps us give our users useful information (like how far the wheel has gone) without them needing to know information about the pod.

The ticks per revolution comes from the encoder datasheet.

You'll recall that circumference =  $c = \pi d$  - a lot of times people learn it as  $c = 2\pi r$  but since the diameter is twice the radius, we can just skip a step and measure the wheel's diameter.

Since we now know the wheel circumference and the ticks per revolution, we can think of that as the wheel circumference is the same as the distance the wheel goes in one revolution so to get the centimeters per tick, we just need to divide the wheel circumference by the number of ticks per revolution.

```
15 OdometryPod(CachingOctoQuad octoQuad, int channel, OctoQuadBase.EncoderDirection ↔
16 ↔ direction) {
17 this.channel = channel;
18 this.octoQuad = octoQuad;
19 octoQuad.setSingleEncoderDirection(channel, direction);
```

Our constructor deals with the things that we need to know and those that are different per class (so they can't be class constants). In this case, we need

{165}------------------------------------------------

## 21. Some hardware to help with Odometry

a reference to the octoQuad as well as what channel our encoder is on and we need to set the direction.

```
25
26 public double getDistance(DistanceUnit distanceUnit) {
27 return distanceUnit.fromCm(readPosition() * CM_PER_ENCODER_TICK);
```

One of my favorite classes to use is DistanceUnit because then people can use whatever units they are familar with and all of the conversion gets done for you automatically.

And then you might have a robot class that had a mecanum drive and three odometry pods

Listing 21.4: Robot.java

```
1 package org.firstinspires.ftc.teamcode.mechanisms;
2
3 import com.qualcomm.hardware.digitalchickenlabs.CachingOctoQuad;
4 import com.qualcomm.hardware.digitalchickenlabs.OctoQuadBase;
5 import com.qualcomm.robotcore.hardware.HardwareMap;
6
7
8 public class Robot {
9 final int CHANNEL_LEFT = 0, CHANNEL_RIGHT = 1, CHANNEL_CROSS = 2;
10 public OdometryPod leftPod, rightPod, crossPod;
11 public MecanumDrive mecanumDrive;
12 CachingOctoQuad octoQuad;
13
14 public void init(HardwareMap hardwareMap) {
15 mecanumDrive.init(hardwareMap);
16 octoQuad = hardwareMap.get(CachingOctoQuad.class, "octoquad");
17
18 leftPod = new OdometryPod(octoQuad, CHANNEL_LEFT, OctoQuadBase.←-
              ,→ EncoderDirection.REVERSE);
19 rightPod = new OdometryPod(octoQuad, CHANNEL_RIGHT, OctoQuadBase.←-
              ,→ EncoderDirection.FORWARD);
20 crossPod = new OdometryPod(octoQuad, CHANNEL_CROSS, OctoQuadBase.←-
              ,→ EncoderDirection.FORWARD);
21
22 octoQuad.saveParametersToFlash();
23 octoQuad.resetAllPositions();
24 octoQuad.setCachingMode(CachingOctoQuad.CachingMode.AUTO);
25 }
26
27 public void resetPods() {
28 octoQuad.resetAllPositions();
29 }
30 }
```

{166}------------------------------------------------

You can see from this that we create the odometry pods and then save their parameters. We reset all positions and then set the caching mode to AUTO. There are 3 caching modes:

| Caching Mode | What it means                                                                                                                                                                                                                            |
|--------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| NONE         | No caching, every read goes to the device                                                                                                                                                                                                |
| MANUAL       | Everything is read from cache, to read from the device you have to call refreshCache                                                                                                                                                     |
| AUTO         | When you read a particular position a second time since the cache has been refreshed, it refreshes the cache. But reading different positions independently doesn't cause a performance problem because they are reading from the cache. |

And then you might have an opmode that let you drive and showed the distance each of the pods had gone

Listing 21.5: RobotOctoQuadOpmode.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.Robot;

@TeleOp()
public class RobotOctoQuadOpmode extends OpMode {
    Robot robot;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.x) {
            robot.resetPods();
        }
        robot.mecanumDrive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, ←
            ↳ gamepad1.right_stick_x);
        telemetry.addData("Left Pod (in)", robot.leftPod.getDistance(DistanceUnit.←
            ↳ INCH));
        telemetry.addData("Right Pod (in)", robot.leftPod.getDistance(DistanceUnit.←
            ↳ INCH));
    }
}
```

{167}------------------------------------------------

## 21. Some hardware to help with Odometry

```
26 telemetry.addData("Cross Pod (in)", robot.leftPod.getDistance(DistanceUnit.←  
    ← INCH));  
27 }  
28 }
```

Here we allow the pods to be reset using the x button on the controller, and it shows the distance each of the pods have gone on the telemetry.

### 21.1.5. Other features

#### 21.1.5.1. Velocity

Some time you don't care about the current position, but you want to know the velocity (ie, how fast is it rotating). You can access that with either `readSingleVelocity` OR `readSingleVelocity_Caching` or of course with `readAllVelocities`. For each channel, you can set the sampling time (1 - 255 ms) and the velocity is the change from the last sample to the one before that. To set the sampling time, use `setSingleVelocitySampleInterval`

For more details, see the official FTC SDK samples.

#### 21.1.5.2. Absolute encoders

If you are using an encoder that has absolute encoders as well (giving the position, not just the count) you can use the OctoQuad for reading that. Often you'll want to setup the first four connectors for relative encoders and the second four as absolute encoders. For more details, see the official FTC SDK samples.

## 21.2. Sparkfun Optical Tracking Odometry Sensor

### 21.2.1. What is it?

This sensor takes pictures and compares each frame to the one before to estimate which direction you have moved in. It also has an IMU onboard and uses sensor fusion (a fancy way for when you combine an accurate sensor that only updates occasionally with a less accurate one that updates rapidly) to be able to give you back an estimated position.

You can buy this from <https://www.sparkfun.com/products/24904> - Don't forget to buy a QWIIC to STEMMA cable so you can plug it into your Control or Expansion Hub. (<https://www.sparkfun.com/products/25596>).

{168}------------------------------------------------

21.2.2. Using itThe easiest thing we can do with it is to get it to tell us where it thinks the robot is. (we can push the robot around with our hands and see it update)

Listing 21.6: UseSparkfunOTOS.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class UseSparkfunOTOS extends OpMode {
    SparkFunOTOS sparkfunOTOS;

    @Override
    public void init() {
        sparkfunOTOS = hardwareMap.get(SparkFunOTOS.class, "otos");
        configureOTOS();
    }

    private void configureOTOS() {
        sparkfunOTOS.setLinearUnit(DistanceUnit.INCH);
        sparkfunOTOS.setAngularUnit(AngleUnit.DEGREES);
        sparkfunOTOS.setOffset(new SparkFunOTOS.Pose2D(0, 0, 0));
        sparkfunOTOS.setLinearScalar(1.0);
        sparkfunOTOS.setAngularScalar(1.0);
        sparkfunOTOS.resetTracking();
        sparkfunOTOS.setPosition(new SparkFunOTOS.Pose2D(0, 0, 0));
        sparkfunOTOS.calibrateImu(255, false);
    }

    public void init_loop(){
        telemetry.addData("Samples left to calibrate", sparkfunOTOS.
            getImuCalibrationProgress());
    }

    @Override
    public void loop() {
        SparkFunOTOS.Pose2D pos = sparkfunOTOS.getPosition();
        telemetry.addData("X (inch)", pos.x);
        telemetry.addData("Y (inch)", pos.y);
        telemetry.addData("Heading (degrees)", pos.h);
    }
}
```

{169}------------------------------------------------

## 21. Some hardware to help with Odometry

```
42 }
```

Let's go through this a bit at a time and explain all the pieces.

```
12 SparkFunOTOS sparkfunOTOS;
```

This should look familiar. Just like we have a servo of class `Servo` and a motor of class `DCMotor`, we have a variable named `sparkfunOTOS` of class `SparkFunOTOS`

```
16 sparkfunOTOS = hardwareMap.get(SparkFunOTOS.class, "otos");
```

This should look familiar, it is how we get all of our items from the hardware map

```
17 configureOTOS();
```

While we could put all of our configuration in the `init()` method, it is better practice to split it out into its own method. This sensor doesn't keep its configuration across power resets, so we should configure it at the beginning of each `opMode`.

```
20 private void configureOTOS() {
```

We make it private because nobody outside of us should be asking for it to be configured.

```
21 sparkfunOTOS.setLinearUnit(DistanceUnit.INCH);
```

We can set which units for x and y we want the sensor to report in. We talked about the `DistanceUnit` class in [section 10.3](#).

```
22 sparkfunOTOS.setAngularUnit(AngleUnit.DEGREES);
```

We can set which unit for degrees we want the sensor to report for the heading. You can choose `DEGREES` or `RADIANS`.

```
23 sparkfunOTOS.setOffset(new SparkFunOTOS.Pose2D(0, 0, 0));
```

This describes the offset of the sensor from the center of your robot. X is negative to the left, positive to the right. Y is negative behind the center and forward of the center. The heading is negative for clockwise with relation to the robot or positive for counterclockwise.

```
24 sparkfunOTOS.setLinearScalar(1.0);  
25 sparkfunOTOS.setAngularScalar(1.0);
```

These are to help calibrate the sensors if the reportings you are getting are off. They can be in the range from 0.872 to 1.127 in increments of 0.001. The

{170}------------------------------------------------

## 21.2. Sparkfun Optical Tracking Odometry Sensor

suggestion is to calibrate the angular scalar first by turning the robot around 10 times and then get the error and set the AngularScalar to the inverse of the error. So if you get the sensor saying -15 degrees after turning the robot perfectly 10 times, then you would set the scalar to  $\frac{3,600}{3,585} = 1.004$ . (Once around a circle is 360 degrees, so 10 times around is 3,600)

To calibrate the linear scalar, move the robot a known distance and measure the error. Do this multiple times at multiple speeds to get an average, then set the linear scalar to the inverse of the error. For example, if you move the robot 100 inches and the sensor reports 103 inches, set the linear scalar to  $\frac{100}{103} = 0.971$ .

You can tell that for both of these the formula is  $scalar = \frac{expected}{reported}$ . Since it is going to multiply the reported number by this scalar that will get you the expected result.

```
26 sparkfunOTOS.resetTracking();
```

This resets the tracking part of the system, getting rid of any past errors.

```
27 sparkfunOTOS.setPosition(new SparkFunOTOS.Pose2D(0,0,0));
```

This probably looks similar to setting the offset, but the offset is the relation of the sensor to the robot. This is the position of the robot on the field. That way you can set it based off of where you know the robot is. (either a starting position or because you have seen an AprilTag like we talked about in section 16.1

```
28 sparkfunOTOS.calibrateImu(255, false);
```

The first parameter here (255) is the number of samples to use in calibrating the IMU. It can be in the range of 1 to 255. (each one takes 2.4ms, so 255 is about 612ms) The second parameter here is whether to wait until done or not. It can be true or false. We set it to false so we can go about our business, but you do want to wait for it finish before moving the robot.

```
31 public void init_loop(){  
32     telemetry.addData("Samples left to calibrate", sparkfunOTOS.  
33         getImuCalibrationProgress());  
34 }
```

This will put on our telemetry how many samples are left to calibrate the IMU so we can make sure we don't move the robot until it tells us there are zero left.

```
36 public void loop() {  
37     SparkFunOTOS.Pose2D pos = sparkfunOTOS.getPosition();
```

{171}------------------------------------------------

## 21. Some hardware to help with Odometry

```
38 telemetry.addData("X (inch)", pos.x);39 telemetry.addData("Y (inch)", pos.y);40 telemetry.addData("Heading (degrees)", pos.h);41 }
```

This gets the position and puts it on telemetry for us.

### 21.3. Exercises

1. Combine the Sparkfun Optical Tracking Odometry Sensor with the April Tags code to be even more accurate for where you are on the field.

{172}------------------------------------------------

## 22. LEDs - Adding some bling feedback...

I have had some people ask me about giving some examples of how to add LEDs to your robot. While some teams add these just for the cool factor (which should not be underestimated), other teams add them to give feedback to the drivers on the current state of the robot.

### 22.1. REV Digital LED Indicator

REV sells a 4 pack of “Digital LED Indicator” (<https://www.revrobotics.com/rev-31-2010/>) which is composed of a red and a green LED. (If you light up both of them, then you get yellow)

These are very easy to mount and give a nice way of showing state. Imagine a game where the possession limit was 2. (like CENTERSTAGE). Your robot could show green if it was ready to intake, yellow if it had one pixel, and red if it had two pixels.

These connect into the Digital IO portion of your hub and when configured the green needs to be the lower of the two numbers for the port and the red should be the higher one.

Listing 22.1: DigitalLEDIndicatorOpMode.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5 import com.qualcomm.robotcore.hardware.LED;
6
7 @TeleOp
8 public class DigitalLEDIndicatorOpMode extends OpMode {
9     LED frontLED_red;
10    LED frontLED_green;
11
12    @Override
13    public void init() {
14        frontLED_green = hardwareMap.get(LED.class, "front_led_green");
15        frontLED_red = hardwareMap.get(LED.class, "front_led_red");
16    }
17
18    @Override
```

{173}------------------------------------------------

## 22. LEDs - Adding some bling feedback...

```
public void loop() {  
    if (gamepad1.a) {  
        frontLED_red.on();  
    } else {  
        frontLED_red.off();  
    }  
    if (gamepad1.b) {  
        frontLED_green.on();  
    } else {  
        frontLED_green.off();  
    }  
}
```

This is pretty straightforward.

```
LED frontLED_red;  
LED frontLED_green;
```

We use the LED class for each LED.

```
frontLED_green = hardwareMap.get(LED.class, "front_led_green");  
frontLED_red = hardwareMap.get(LED.class, "front_led_red");
```

This is just like our hardwareMap.get we have used for all other hardware.

```
public void loop() {  
    if (gamepad1.a) {  
        frontLED_red.on();  
    } else {  
        frontLED_red.off();  
    }  
    if (gamepad1.b) {  
        frontLED_green.on();  
    } else {  
        frontLED_green.off();  
    }  
}
```

This is pretty self explanatory. On your real robot you would likely use either the output of other sensors or variables to decide when to turn them on.

### 22.2. Sparkfun QWIIC LED Stick

Sparkfun sells a LED Stick with 10 LEDs on it that you can program individually. (<https://www.sparkfun.com/products/18354>)

If you get this, make sure you also pick up this cable (<https://www.sparkfun.com/products/25596>) to make it easy to connect to your Hub (either Control or Expansion Hub). This plugs into the I2C ports (right side).

{174}------------------------------------------------

Listing 22.2: LEDStickOpMode.java

```
package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.hardware.sparkfun.SparkFunLEDStick;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LEDStickOpMode extends OpMode {
    private SparkFunLEDStick ledStick;

    @Override
    public void init() {
        ledStick = hardwareMap.get(SparkFunLEDStick.class, "back_leds");
        int[] ledColors = {Color.RED, Color.YELLOW, Color.GREEN, Color.YELLOW, Color.←
            RED,
            Color.YELLOW, Color.GREEN, Color.YELLOW, Color.RED, Color.YELLOW};
        ledStick.setColors(ledColors);
        ledStick.setBrightness(5); // Between 0 and 31
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            ledStick.setColor(Color.BLUE);
        } else if (gamepad1.b) {
            ledStick.setColor(Color.RED);
        } else if (gamepad1.left_bumper) {
            ledStick.turnAllOff();
        } else {
            ledStick.setColor(Color.GREEN);
        }
    }
}
```

This is pretty straightforward and is a great way to add bling to your robot. While you can set each LED to a different color, be aware that may make it hard for the driver to see what is going on. Setting the color of the whole stick to the same color can be a blinding way to send information. You can also put this on the underside of your robot so it will make it glow underneath.

```
ledStick = hardwareMap.get(SparkFunLEDStick.class, "back_leds");
```

We use the SparkFunLEDStick class for the stick.

```
int[] ledColors = {Color.RED, Color.YELLOW, Color.GREEN, Color.YELLOW, Color.←
    RED,
```

{175}------------------------------------------------

## 22. LEDs - Adding some *bling* feedback...

```
17 Color.YELLOW, Color.GREEN, Color.YELLOW, Color.RED, Color.YELLOW};
18 ledStick.setColors(ledColors);
```

You can use the Android Colors to set the colors of each LED individually by creating an array and then sending it to the LED using the `setColors` method.

```
19 ledStick.setBrightness(5); // Between 0 and 31
```

Here we set the brightness to 5 so it isn't as blinding but you can set it from 0 (off) to 31 (full power)

```
25 ledStick.setColor(Color.BLUE);
```

In addition to setting the colors individually, you can set all of them at once. (You can also set the color of just an individual LED with `setColor(position, color)`)

## 22.3. REV Blinkin

REV sells a Blinkin LED Driver (<https://www.revrobotics.com/rev-11-1105/>). It allows you to connect a long LED strip and then control it like you are controlling a servo.

This is the most expensive of the solutions we are looking at in this chapter.

Listing 22.3: BlinkinOpMode.java

```
1 package org.firstinspires.ftc.teamcode;
2 import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5 import com.qualcomm.robotcore.hardware.LED;
6
7 @TeleOp
8 public class BlinkinOpMode extends OpMode{
9     RevBlinkinLedDriver blinkinLedDriver;
10
11    @Override
12    public void init() {
13        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
14    }
15
16    @Override
17    public void loop() {
18        if(gamepad1.a){
19            blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_PARTY_PALETTE);
20        } else{

```

{176}------------------------------------------------

```
21 blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
22 }
23 }
24 }
```

There are really only two things to note here:

```
12 blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
```

We use the RevBlinkinLedDriver for the class

```
18 blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.<--  
    <-- BEATS_PER_MINUTE_PARTY_PALETTE);
```

It only has one method that we use and that is setting the pattern. All of the possible patterns are below. The ones that are CP1 have the color set with a screwdriver on the until under color pattern1. CP2 is for color pattern 2, the ones that have both CP1 and CP2 use both of those.

```
RAINBOW_RAINBOW_PALETTE,
RAINBOW_PARTY_PALETTE,
RAINBOW_OCEAN_PALETTE,
RAINBOW_LAVA_PALETTE,
RAINBOW_FOREST_PALETTE,
RAINBOW_WITH_GLITTER,
CONFETTI,
SHOT_RED,
SHOT_BLUE,
SHOT_WHITE,
SINELON_RAINBOW_PALETTE,
SINELON_PARTY_PALETTE,
SINELON_OCEAN_PALETTE,
SINELON_LAVA_PALETTE,
SINELON_FOREST_PALETTE,
BEATS_PER_MINUTE_RAINBOW_PALETTE,
BEATS_PER_MINUTE_PARTY_PALETTE,
BEATS_PER_MINUTE_OCEAN_PALETTE,
BEATS_PER_MINUTE_LAVA_PALETTE,
BEATS_PER_MINUTE_FOREST_PALETTE,
FIRE_MEDIUM,
FIRE_LARGE,
TWINKLES_RAINBOW_PALETTE,
TWINKLES_PARTY_PALETTE,
TWINKLES_OCEAN_PALETTE,
TWINKLES_LAVA_PALETTE,
TWINKLES_FOREST_PALETTE,
COLOR_WAVES_RAINBOW_PALETTE,
COLOR_WAVES_PARTY_PALETTE,
COLOR_WAVES_OCEAN_PALETTE,
```

{177}------------------------------------------------

## 22. LEDs - Adding some *bling* feedback...

```
COLOR_WAVES_LAVA_PALETTE,
COLOR_WAVES_FOREST_PALETTE,
LARSON_SCANNER_RED,
LARSON_SCANNER_GRAY,
LIGHT_CHASE_RED,
LIGHT_CHASE_BLUE,
LIGHT_CHASE_GRAY,
HEARTBEAT_RED,
HEARTBEAT_BLUE,
HEARTBEAT_WHITE,
HEARTBEAT_GRAY,
BREATH_RED,
BREATH_BLUE,
BREATH_GRAY,
STROBE_RED,
STROBE_BLUE,
STROBE_GOLD,
STROBE_WHITE,

/* CP1: Color 1 Pattern */
CP1_END_TO_END_BLEND_TO_BLACK,
CP1_LARSON_SCANNER,
CP1_LIGHT_CHASE,
CP1_HEARTBEAT_SLOW,
CP1_HEARTBEAT_MEDIUM,
CP1_HEARTBEAT_FAST,
CP1_BREATH_SLOW,
CP1_BREATH_FAST,
CP1_SHOT,
CP1_STROBE,

/* CP2: Color 2 Pattern */
CP2_END_TO_END_BLEND_TO_BLACK,
CP2_LARSON_SCANNER,
CP2_LIGHT_CHASE,
CP2_HEARTBEAT_SLOW,
CP2_HEARTBEAT_MEDIUM,
CP2_HEARTBEAT_FAST,
CP2_BREATH_SLOW,
CP2_BREATH_FAST,
CP2_SHOT,
CP2_STROBE,

/* CP1_2: Color 1 and 2 Pattern */
CP1_2_SPARKLE_1_ON_2,
CP1_2_SPARKLE_2_ON_1,
```

{178}------------------------------------------------

```
CP1_2_COLOR_GRADIENT,
CP1_2_BEATS_PER_MINUTE,
CP1_2_END_TO_END_BLEND_1_TO_2,
CP1_2_END_TO_END_BLEND,
CP1_2_NO_BLENDING,
CP1_2_TWINKLES,
CP1_2_COLOR_WAVES,
CP1_2_SINELON,
/*
 * Solid color
 */
HOT_PINK,
DARK_RED,
RED,
RED_ORANGE,
ORANGE,
GOLD,
YELLOW,
LAWN_GREEN,
LIME,
DARK_GREEN,
GREEN,
BLUE_GREEN,
AQUA,
SKY_BLUE,
DARK_BLUE,
BLUE,
BLUE_VIOLET,
VIOLET,
WHITE,
GRAY,
DARK_GRAY,
BLACK;
```

## 22.4. Exercises

These exercises do not have solutions in the appendix

1. If you have either the Sparkfun LED stick or the Blinkin, go add colored LEDs to your robot that go along with your team's brand.
2. For any of the three, make them light up when a color sensor sees a game element of your alliance color.

{179}------------------------------------------------

![](_page_179_Picture_0.jpeg)

{180}------------------------------------------------

## 23. Limelight 3A

For the Into the Deep season, FTC made the Limelight 3A legal. <https://limelightvision.io/collections/products/products/limelight-3a> This is a device that has not only a camera but also a processor so you can make your own vision pipelines. This is much like we covered in Chapter [chapter 16](#), but this time instead of it being in Java code and running alongside with your other robot control code it is running on a different device.

This has both benefits and downsides. On the benefits, it has a much easier interface where you can quickly see the effects of your changes and it doesn't use up any of the processor and loop time that is shared with your robot controller. On the downside, it is another piece and type of configuration that you need to make sure you take control of.

### 23.1. Simple color example

Let's start by looking for game elements that are blue.

#### 23.1.1. On the Limelight

The first thing you need to do is connect your Limelight to a computer and after the lights are blinking on the front go to <http://limelight.local:5801> in your browser.

Select a pipeline - Here we are using Pipeline 0, but as long as it matches in your Java code and on the limelight, you can use any of the 10 (0-9) pipelines available. If it isn't letting you change the pipeline number, it is often because you need to press the "Start Ignoring NetworkTables index"

{181}------------------------------------------------

## 23. Limelight 3A

![Screenshot of the Limelight 3A Input tab showing camera settings (Color/Retroreflective, 320x240 90fps, Normal orientation, Exposure 983) and a live stream of colored blocks.]()![](_page_181_Picture_1.jpeg)

The first tab we want to work with is the “Input” tab. Here we set the pipeline type - For detecting Colors it should be “Color/Retroreflective”. We can change whether it is dealing with what the camera sees or a snapshot. This is useful for taking a snapshot when you are on a field and then working with it later. (To take a snapshot, you press the “Take Snapshot” button under the image on the right.) The resolution of the camera can be set here. In general you want the lowest resolution that will work because it takes less effort to process the images. The “Stream Orientation” allows you to mount the camera in different orientations. The Exposure should be set to the lowest number where you can clearly see the game elements. It does not need to be bright.

![Screenshot of the Limelight 3A Thresholding tab showing Magic Wands settings (Hue 100, Saturation 0, Value 0) and a live stream of colored blocks.]()![](_page_181_Picture_3.jpeg)

Now it is time to go to the second tab, which is “Thresholding”. Select the Hue and drag it (both the lower and the upper) until it is clearly finding the blue sample. While there are magic wands to use “eyedropper”, I find that it is too selective and is easier to leave everything else. The “Color” Pipelines

{182}------------------------------------------------

in Limelight use HSV. You can see an explanation of color spaces in Chapter chapter 16.

Saturation is how “pure” the color is. More washed out is lower. You can set this if you are having problems with too many things being detected. You may want to raise this so you don’t get problems with the light reflecting off of the shiny metal.

Value is the darkness of a color. You should increase this so black won’t come through the filter.

Under the image, you should change Show from “Color” to “Threshold” so you will see what is coming through the filter.

![Screenshot of the Limelight 3A software interface showing the Contour Filtering tab. The image displays colored blocks, and a blue block is highlighted by a crosshair. The filter settings show Sort Mode: Closest, Area (% of image): 0.0010, Fullness (% of blue rect): 10, W/H Ratio (yellow rect): 0.0000, Direction Filter: None, Smart Speckle Rejection: 0, Target: Single Target, Grouping: None. The Color tab is selected, showing color values.]()![](_page_182_Picture_5.jpeg)

Now it is time to go to the third tab (Contour Filtering). This does filtering after the items applied in the second tab. Sort Mode allows you to decide which item has priority. I selected “Closest” which means the closest to your crosshairs (middle of the screen unless you move it on the output tab)

Area allows you to reject very small items. Fullness allows you to reject items that aren’t mostly that color. (This is useful in Into the Deep when the samples are rectangles, but may not be as useful in the future.)

W/H ratio is useful when the items are always a certain ratio but since in Into the Deep they are randomly distributed, it isn’t useful. Direction Filter allows you to only select items that are pointing a different way. (Say for example if you could only pick up items in a certain orientation)

{183}------------------------------------------------

## 23. Limelight 3A

![Screenshot of the Limelight 3A software interface showing the Output tab. Settings include Targeting Region (Center), Contour Simplification (5), Send Raw (No), Send JSON over NT? (No), Crosshair Mode (Single Crosshair), and Crosshair A settings (Calibrate XY, Only X, Only Y, Reset XY). The stream shows a target image with a crosshair and color data.]()![](_page_183_Picture_1.jpeg)

The fourth (and last tab) is the Output tab. Here you can change where your cross hairs are. (say for example if your camera is offset from how you pick up the blocks)

I then recommend setting the pipeline name (press the pencil icon on the top row) and downloading it. The icon with the arrow pointing down. This will give you a file that you can save and then upload again to make sure you have your settings backed up.

### 23.1.2. Your Java Code

Now that you have your pipeline all ready, we have to talk to the Limelight with our Java code.

Listing 23.1: SimpleLimelightOpMode.java

```
1 package org.firstinspires.ftc.teamcode;
2
3 import com.qualcomm.hardware.limelightvision.LLResult;
4 import com.qualcomm.hardware.limelightvision.LLResultTypes;
5 import com.qualcomm.hardware.limelightvision.Limelight3A;
6 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
7 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
8
9 import java.util.List;
10
11 @TeleOp
12 public class SimpleLimelightOpMode extends OpMode {
13     Limelight3A limelight3A;
14     @Override
15     public void init() {
16         limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
17     }

```

{184}------------------------------------------------

```
18
19 public void start(){
20     limelight3A.pipelineSwitch(0);
21     limelight3A.start();
22 }
23
24 @Override
25 public void loop() {
26     LLResult llResult = limelight3A.getLatestResult();
27     if(llResult != null && llResult.isValid()){
28         telemetry.addData("Tx", llResult.getTx());
29         telemetry.addData("Ty", llResult.getTy());
30         telemetry.addData("Ta", llResult.getTa());
31     }
32     else{
33         telemetry.addLine("None found");
34     }
35 }
36 }
```

There are three main parts to our program now. The `init()`, the `start()`, and the `loop()`.

```
13 Limelight3A limelight3A;
14 @Override
15 public void init() {
16     limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
17 }
```

The Limelight is of type Limelight3A and this assumes you have named it limelight in your configuration file. Obviously if you name it differently there, you'll have to fix it here.

```
19 public void start(){
20     limelight3A.pipelineSwitch(0);
21     limelight3A.start();
22 }
```

This could be all in `init()` but since the Limelight consumes more power when it is running, it seems like a good idea to start it here. We also switch the pipeline. It is fast enough to switch that we could do it after we start it but it seems like a good practice to be using the right pipeline before starting.

```
26 LLResult llResult = limelight3A.getLatestResult();
```

We get the result from the Limelight

```
27 if(llResult != null && llResult.isValid()){
28     telemetry.addData("Tx", llResult.getTx());
```

{185}------------------------------------------------

## 23. Limelight 3A

```
29 telemetry.addData("Ty", llResult.getTy());
30 telemetry.addData("Ta", llResult.getTa());
31 }
```

Here we only print the result if the last one we got was valid. We also have to make sure that llResult was not null so we won't get a crash when we call it and it is null. (If you call getLatestResult before a result has been returned, then it will be null. Since we start it in start(), and then immediately check it our first time it could be null.) Tx is the x of the target, Ty is the y of the target, and Ta is the angle of the target. (All are relative to the crosshairs.)

We could do something different if we didn't see any valid targets.

### 23.1.3. Changing Limelight Pipeline

Now, let's change our pipeline so instead of finding the blue game elements the Limelight will return the red game elements it sees. Make sure you do this one under Pipeline 1. If it won't let you change the pipeline number then you need to press the box at the top that says Start Ignoring NetworkTables Index.

![Screenshot of the Limelight 3A software interface showing the 'red_pipeline' settings. The Magic Wand settings are configured for red targets: Hue 19, Saturation 50, Value 60, Erosion Steps 0, Dilation Steps 0, X-Crop -1, Y-Crop -1. The 'Invert Hue Selection?' option is set to 'Yes'.]()![](_page_185_Picture_6.jpeg)

The thing that is tricky is that you need to set "Invert Hue selection" to yes since you are looking for a red target (red is at the bottom and top of Hue, so it is the only color you need to do this for.) Then change the hue until it is identifying the red sample.

### 23.1.4. Swapping between pipelines

Listing 23.2: TwoPipelinesLimelightOpMode.java

```
1 package org.firstinspires.ftc.teamcode;
```

{186}------------------------------------------------

```
2
3 import com.qualcomm.hardware.limelightvision.LLResult;
4 import com.qualcomm.hardware.limelightvision.LLResultTypes;
5 import com.qualcomm.hardware.limelightvision.Limelight3A;
6 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
7 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
8
9 import java.util.List;
10
11 @TeleOp
12 public class TwoPipelinesLimelightOpMode extends OpMode {
13     Limelight3A limelight3A;
14     @Override
15     public void init() {
16         limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
17     }
18     public void start() {
19         limelight3A.pipelineSwitch(0);
20         limelight3A.start();
21     }
22
23     @Override
24     public void loop() {
25         if (gamepad1.a) {
26             limelight3A.pipelineSwitch(0);
27         } else if (gamepad1.b) {
28             limelight3A.pipelineSwitch(1);
29         }
30         LLResult llResult = limelight3A.getLatestResult();
31         if (llResult != null) {
32             telemetry.addData("Pipeline Num", llResult.getPipelineIndex());
33             if (llResult.isValid()) {
34                 telemetry.addData("Tx", llResult.getTx());
35                 telemetry.addData("Ty", llResult.getTy());
36                 telemetry.addData("Ta", llResult.getTa());
37             }
38         }
39     }
40 }
```

The only difference between this one and the earlier one is where we switch which pipeline is active and displaying which pipeline is active. First, the switching.

```
25 if (gamepad1.a) {
26     limelight3A.pipelineSwitch(0);
27 } else if (gamepad1.b) {
28     limelight3A.pipelineSwitch(1);
29 }
```

{187}------------------------------------------------

## 23. Limelight 3A

This uses the A button to select the pipeline zero and the B button to select the pipeline one. (Very tough, huh....)

31

```
if(!Result != null) {
```

This sends which pipeline was used to get the result.

## 23.2. Localization with AprilTags

Limelight also has AprilTags support and you can load an image of the field into the Limelight saying where the april tags are and it can give you back your robot's position.

### 23.2.1. On the Limelight

![Screenshot of the Limelight 3A software interface showing the Input tab configured for AprilTags. The stream displays a detected AprilTag (ID 12) on a field image.]()![](_page_187_Picture_8.jpeg)

Simply change the Pipeline Tag to be AprilTags on the Input screen

![Screenshot of the Limelight 3A software interface showing the Input tab configured for AprilTags localization. The stream displays a detected AprilTag (ID 14) on a field image.]()![](_page_187_Picture_10.jpeg)

{188}------------------------------------------------

Make sure you have the current year's field loaded. To change the field map, See the upload button next to "Field Map Floor" on the "Advanced" tab. You can then set the view to Robot Pose in Field and you can see your robot placed on the field as you move it around.

### 23.2.2. Your Java Code

Listing 23.3: AprilTagsLimelightOpMode.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp
public class AprilTagsLimelightOpMode extends OpMode {
    Limelight3A ll3a;
    @Override
    public void init() {
        ll3a = hardwareMap.get(Limelight3A.class, "limelight");
        ll3a.pipelineSwitch(2);
    }
    public void start() {
        ll3a.start();
    }
    @Override
    public void loop() {
        LLResult llResult = ll3a.getLatestResult();

        if (llResult != null && llResult.isValid()) {
            Pose3D pose3d = llResult.getBotpose();
            telemetry.addData("Bot Pose", pose3d);
        }
    }
}
```

You'll notice that this is very straightforward. If we see an AprilTag (there is a valid LLResult) then we'll put our BotPose in Telemetry. Obviously it doesn't require much imagination to use this in autonomous to know where your robot is.

{189}------------------------------------------------

### 23. Limelight 3A

#### 23.3. Exercises

1. Make a pipeline that looks for yellow blocks (no solution given)
2. Make a teleop that if it doesn't see any targets rumbles the gamepad (no solution given)

{190}------------------------------------------------

## A. Making your own Programming Board

The ProgrammingBoard has a number of electrical components:

- REV Expansion Hub (<http://www.revrobotics.com/rev-31-1153/>)
- REV Potentiometer (<http://www.revrobotics.com/rev-31-1155/>)
- REV Color Sensor (<http://www.revrobotics.com/rev-31-1557/>)
- REV Touch Sensor (<http://www.revrobotics.com/rev-31-1425/>)
- REV 40:1 HD Hex Motor (<http://www.revrobotics.com/rev-41-1301/>)
- REV SRS Servo (<http://www.revrobotics.com/rev-41-1097/>)

It should be connected in the following way:

- REV 40:1 HD Hex Motor - Power and encoder to Motor 0
- REV Potentiometer - connected to Analog/Digital 0:1
- REV Color Sensor - connected to I2C 1
- REV Touch Sensor - connected to Analog/Digital 2:3
- REV SRS Servo - connected to Servo 0

Here is an example CAD from one of my students<sup>1</sup> of a way to assemble it using all mechanical parts from the REV FTC Kit.

<sup>1</sup>Thanks, Eric!!

{191}------------------------------------------------

A. Making your own Programming Board

![A photograph of a custom-built programming board or robotics setup. The board is mounted vertically on a gray surface. It features a central black component labeled 'REV ROBOTICS'. A white cylindrical object, possibly a motor or sensor, extends horizontally from the left side. The board is constructed using metal rails and connectors.]()![](_page_191_Picture_1.jpeg)

{192}------------------------------------------------

## B. LinearOpMode

### B.1. What is it?

LinearOpMode is a class derived from opMode that instead of having the five methods of an OpMode has only one. runOpMode(). Everything then occurs in that method. You are now responsible to update telemetry whenever you want it sent to the driver station, waiting for the Start button to be pressed, and checking to see if the opModeIsActive()

Here is our HelloWorld as a LinearOpMode

Listing B.1: HelloWorldLinear.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class HelloWorldLinear extends LinearOpMode {

    @Override
    public void runOpMode() {
        telemetry.addData("Hello", "World");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
        }
    }
}
```

So you can compare, here it is again from chapter 1

Listing B.2: HelloWorld.java

```
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class HelloWorld extends OpMode {
```

{193}------------------------------------------------

## B. LinearOpMode

```
8 @Override
9 public void init() {
10     telemetry.addData("Hello", "World");
11 }
12
13 @Override
14 public void loop() {
15 }
16 }
17 }
```

### B.2. Should you use it?

I think that you are better off using OpMode instead of LinearOpMode but since a lot of the sample code and many (most?) teams do I think it is worth elaborating here why that is my opinion so you can make your own decision. There are teams I highly respect that use LinearOpMode so even if you disagree we can still be friends. :-)

#### B.2.1. Benefits of LinearOpMode

The reason LinearOpMode exists is that it allows code to be written that is more similar to how code is often taught. Instead of using state machines like we did in [chapter 12](#), it allows simple code like:

```
...
board.setMotorSpeed(0.5);
while(!board.touchSensorPressed()){
}
board.setMotorSpeed(0.0);
...
```

as opposed to code like:

```
...
switch(state){
case State.BEGIN:
    board.setMotorSpeed(0.5);
    state = State.WAIT_FOR_TOUCH;
    break;
case State.WAIT_FOR_TOUCH:
    if(board.touchSensorPressed){
        state = State.STOP;
    }
    break;

```

{194}------------------------------------------------

```
case State.STOP:
    board.setMotorSpeed(0.0);
    break;
```

The other large benefit is much of the sample code available online is written this way.

### B.2.2. Drawbacks of LinearOpMode

1. LinearOpMode is derived from OpMode. If you look at the implementation of LinearOpMode, the start() method creates a thread and calls the user class runOpMode(). This means you have now introduced another thread into the system. Instead of variables like gamepad being updated between calls to your OpMode, they could be updated at anytime.
2. Your code is all in one main control method instead of being broken out into logical methods for the five methods in the OpMode. For both OpMode and LinearOpMode you should use class methods to break your code out into logical pieces to make it easier to read and maintain. Many professional programmers get nervous whenever a method is longer than fits on one screen.
3. You also are no longer protected from a loop taking too long so you don't respond in time to the driver station.
4. State machines are typically used in commercial embedded projects. Why not choose to learn how to do that now?

{195}------------------------------------------------

![](_page_195_Picture_0.jpeg)

{196}------------------------------------------------

## C. Sample Solutions

These are here if you get stuck, but they are not the only way to solve the exercises.

### C.1. Chapter 1 Solutions

Listing C.1: Exercise\_1\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp
7 public class Exercise_1_1 extends OpMode {
8     @Override
9     public void init() {
10         telemetry.addData("Hello", "Alan");
11     }
12
13     @Override
14     public void loop() {
15     }
16 }
17 }
```

Listing C.2: Exercise\_1\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
4 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
5
6 @Autonomous
7 public class Exercise_1_2 extends OpMode {
8     @Override
9     public void init() {
10         telemetry.addData("Hello", "Alan");
11     }
12 }
```

{197}------------------------------------------------

## C. Sample Solutions

```
12
13     @Override
14     public void loop() {
15
16     }
17 }
```

### C.2. Chapter 2 Solutions

Listing C.3: Exercise\_2\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class Exercise_2_1 extends OpMode {
8     @Override
9     public void init() {
10         String myName = "Your Name";
11
12         telemetry.addData("Hello", myName);
13     }
14
15     @Override
16     public void loop() {
17
18     }
19 }
```

Listing C.4: Exercise\_2\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class Exercise_2_2 extends OpMode {
8     @Override
9     public void init() {
10         String myName = "Your Name";
11         int grade = 38;
12
13         telemetry.addData("Hello", myName);
```

{198}------------------------------------------------

```
14 telemetry.addData("Grade", grade);
15 }
16
17 @Override
18 public void loop() {
19 }
20 }
21 }
```

## C.3. Chapter 3 Solutions

Listing C.5: Exercise\_3\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class Exercise_3_1 extends OpMode {
8     @Override
9     public void init() {
10     }
11
12     @Override
13     public void loop() {
14         telemetry.addData("Right stick x", gamepad1.right_stick_x);
15         telemetry.addData("Right stick y", gamepad1.right_stick_y);
16     }
17 }
```

Listing C.6: Exercise\_3\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class Exercise_3_2 extends OpMode {
8     @Override
9     public void init() {
10     }
11
12     @Override
13     public void loop() {
```

{199}------------------------------------------------

## C. Sample Solutions

```
14 telemetry.addData("B button", gamepad1.b);
15 }
16 }
```

Listing C.7: Exercise\_3\_3.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class Exercise_3_3 extends OpMode {
8     @Override
9     public void init() {
10     }
11
12     @Override
13     public void loop() {
14         telemetry.addData("Diff left y and right y",
15             gamepad1.left_stick_y - gamepad1.right_stick_y);
16     }
17 }
```

Listing C.8: Exercise\_3\_4.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class Exercise_3_4 extends OpMode {
8     @Override
9     public void init() {
10     }
11
12     @Override
13     public void loop() {
14         telemetry.addData("sum triggers",
15             gamepad1.left_trigger + gamepad1.right_trigger);
16     }
17 }
```

## C.4. Chapter 4 Solutions

{200}------------------------------------------------

## Listing C.9: Exercise\_4\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp
7 public class Exercise_4_1 extends OpMode {
8 @Override
9 public void init() {
10
11 }
12
13 @Override
14 public void loop() {
15 double fwdSpeed = gamepad1.left_stick_y;
16
17 if (!gamepad1.a) {
18 fwdSpeed *= 0.5;
19 }
20 telemetry.addData("Forward Speed", fwdSpeed);
21 }
22 }
```

## Listing C.10: Exercise\_4\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp
7 public class Exercise_4_2 extends OpMode {
8 @Override
9 public void init() {
10
11 }
12
13 @Override
14 public void loop() {
15 double ySpeed = gamepad1.left_stick_y;
16 double xSpeed = gamepad1.left_stick_x;
17
18 if (gamepad1.a) { // crazy mode
19 ySpeed = gamepad1.left_stick_x;
20 xSpeed = gamepad1.left_stick_y;
21
22 }
23 telemetry.addData("X Speed", xSpeed);
```

{201}------------------------------------------------

## C. Sample Solutions

```
24 telemetry.addData("Y Speed", ySpeed);
25 }
26 }
```

### C.5. Chapter 5 Solutions

Listing C.11: RobotLocation\_5\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 public class RobotLocation_5_1 {
4     double angle;
5
6     public RobotLocation_5_1(double angle) {
7         this.angle = angle;
8     }
9
10    public double getHeading() {
11        double angle = this.angle;
12        while (angle > 180) {
13            angle -= 360;
14        }
15        while (angle < -180) {
16            angle += 360;
17        }
18        return angle;
19    }
20
21    @Override
22    public String toString() {
23        return "RobotLocation: angle (" + angle + ")";
24    }
25
26    public void turn(double angleChange) {
27        angle += angleChange;
28    }
29
30    public void setAngle(double angle) {
31        this.angle = angle;
32    }
33
34    public double getAngle() {
35        return angle;
36    }
37}
```

{202}------------------------------------------------

Listing C.12: UseRobotLocationOpMode\_5\_1.java

```
package org.firstinspires.ftc.teamcode.solutions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class UseRobotLocationOpMode_5_1 extends OpMode {
    RobotLocation_5_1 robotLocation = new RobotLocation_5_1(0);

    @Override
    public void init() {
        robotLocation.setAngle(0);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            robotLocation.turn(0.1);
        } else if (gamepad1.b) {
            robotLocation.turn(-0.1);
        }
        telemetry.addData("Location", robotLocation);
        telemetry.addData("Heading", robotLocation.getHeading());
        telemetry.addData("Angle", robotLocation.getAngle());
    }
}
```

Listing C.13: RobotLocation\_5\_2.java

```
package org.firstinspires.ftc.teamcode.solutions;

public class RobotLocation_5_2 {
    double angle;
    double x;

    public RobotLocation_5_2(double angle) {
        this.angle = angle;
    }

    public double getHeading() {
        double angle = this.angle;
        while (angle > 180) {
            angle -= 360;
        }
        while (angle < -180) {
            angle += 360;
        }
    }
}
```

{203}------------------------------------------------

## C. Sample Solutions

```
19 return angle;
20 }
21
22 @Override
23 public String toString() {
24     return "RobotLocation: angle (" + angle + ") x (" + x + ")";
25 }
26
27 public void turn(double angleChange) {
28     angle += angleChange;
29 }
30
31 public void setAngle(double angle) {
32     this.angle = angle;
33 }
34
35 public double getAngle() {
36     return angle;
37 }
38
39 public double getX() {
40     return x;
41 }
42
43 public void changeX(double change) {
44     x += change;
45 }
46
47 public void setX(double x) {
48     this.x = x;
49 }
50 }
```

Listing C.14: UseRobotLocationOpMode\_5\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp
7 public class UseRobotLocationOpMode_5_2 extends OpMode {
8     RobotLocation_5_2 robotLocation = new RobotLocation_5_2(0);
9
10    @Override
11    public void init() {
12        robotLocation.setAngle(0);
13    }
14 }
```

{204}------------------------------------------------

```
15
16 @Override
17 public void loop() {
18     if (gamepad1.a) {
19         robotLocation.turn(0.1);
20     } else if (gamepad1.b) {
21         robotLocation.turn(-0.1);
22     }
23     if (gamepad1.dpad_left) {
24         robotLocation.changeX(-0.1);
25     } else if (gamepad1.dpad_right) {
26         robotLocation.changeX(0.1);
27     }
28     telemetry.addData("Location", robotLocation);
29     telemetry.addData("Heading", robotLocation.getHeading());
30 }
31 }
```

Listing C.15: RobotLocation\_5\_3.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 public class RobotLocation_5_3 {
4     double angle;
5     double x, y;
6
7     public RobotLocation_5_3(double angle) {
8         this.angle = angle;
9     }
10
11     public double getHeading() {
12         double angle = this.angle;
13         while (angle > 180) {
14             angle -= 360;
15         }
16         while (angle < -180) {
17             angle += 360;
18         }
19         return angle;
20     }
21
22     @Override
23     public String toString() {
24         return "RobotLocation: angle (" + angle + ") pos (" + x + ", " + y + ")";
25     }
26
27     public void turn(double angleChange) {
28         angle += angleChange;
29     }
```

{205}------------------------------------------------

## C. Sample Solutions

```
30
31 public void setAngle(double angle) {
32     this.angle = angle;
33 }
34
35 public double getAngle() {
36     return angle;
37 }
38
39 public double getX() {
40     return x;
41 }
42
43 public void changeX(double change) {
44     x += change;
45 }
46
47 public void setX(double x) {
48     this.x = x;
49 }
50
51 public double getY() {
52     return y;
53 }
54
55 public void changeY(double change) {
56     y += change;
57 }
58
59 public void setY(double y) {
60     this.y = y;
61 }
62
63 }
64
```

Listing C.16: UseRobotLocationOpMode\_5\_3.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp
7 public class UseRobotLocationOpMode_5_3 extends OpMode {
8     RobotLocation_5_3 robotLocation = new RobotLocation_5_3(0);
9
10     @Override
```

{206}------------------------------------------------

```
12 public void init() {  
13     robotLocation.setAngle(0);  
14 }  
15  
16 @Override  
17 public void loop() {  
18     if (gamepad1.a) {  
19         robotLocation.turn(0.1);  
20     } else if (gamepad1.b) {  
21         robotLocation.turn(-0.1);  
22     }  
23     if (gamepad1.dpad_left) {  
24         robotLocation.changeX(-0.1);  
25     } else if (gamepad1.dpad_right) {  
26         robotLocation.changeX(0.1);  
27     }  
28     if (gamepad1.dpad_up) {  
29         robotLocation.changeY(-0.1);  
30     } else if (gamepad1.dpad_down) {  
31         robotLocation.changeY(0.1);  
32     }  
33     telemetry.addData("Location", robotLocation);  
34     telemetry.addData("Heading", robotLocation.getHeading());  
35 }  
36 }
```

## C.6. Chapter 6 Solutions

Listing C.17: ProgrammingBoard\_6\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.hardware.DigitalChannel;  
4 import com.qualcomm.robotcore.hardware.HardwareMap;  
5  
6 public class ProgrammingBoard_6_1 {  
7     private DigitalChannel touchSensor;  
8  
9     public void init(HardwareMap hwMap) {  
10         touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");  
11         touchSensor.setMode(DigitalChannel.Mode.INPUT);  
12     }  
13  
14     public boolean isTouchSensorPressed() {  
15         return !touchSensor.getState();  
16     }  
17 }
```

{207}------------------------------------------------

## C. Sample Solutions

```
17 public boolean isTouchSensorReleased() {  
18     return touchSensor.getState();  
19 }  
20 }  
21 }
```

Listing C.18: TouchSensorOpMode\_6\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 @TeleOp()  
7 public class TouchSensorOpMode_6_1 extends OpMode {  
8     ProgrammingBoard_6_1 board = new ProgrammingBoard_6_1();  
9  
10    @Override  
11    public void init() {  
12        board.init(hardwareMap);  
13    }  
14  
15    @Override  
16    public void loop() {  
17        telemetry.addData("Touch sensor Released", board.isTouchSensorReleased());  
18    }  
19 }
```

Listing C.19: TouchSensorOpMode\_6\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 @TeleOp()  
7 public class TouchSensorOpMode_6_2 extends OpMode {  
8     ProgrammingBoard_6_1 board = new ProgrammingBoard_6_1();  
9  
10    @Override  
11    public void init() {  
12        board.init(hardwareMap);  
13    }  
14  
15    @Override  
16    public void loop() {  
17        String touchSensorString = "Not Pressed";  
18        if (board.isTouchSensorPressed()) {  
19            touchSensorString = "Pressed";
```

{208}------------------------------------------------

```
20 }  
21 telemetry.addData("Touch sensor", touchSensorString);  
22 }  
23 }
```

## C.7. Chapter 7 Solutions

Listing C.20: ProgrammingBoard\_7\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.hardware.DcMotor;  
4 import com.qualcomm.robotcore.hardware.DigitalChannel;  
5 import com.qualcomm.robotcore.hardware.HardwareMap;  
6  
7 public class ProgrammingBoard_7_1 {  
8     private DigitalChannel touchSensor;  
9     private DcMotor motor;  
10    private double ticksPerRotation;  
11  
12    public void init(HardwareMap hwMap) {  
13        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");  
14        touchSensor.setMode(DigitalChannel.Mode.INPUT);  
15        motor = hwMap.get(DcMotor.class, "motor");  
16        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  
17        ticksPerRotation = motor.getMotorType().getTicksPerRev();  
18    }  
19  
20    public boolean isTouchSensorPressed() {  
21        return !touchSensor.getState();  
22    }  
23  
24    public void setMotorSpeed(double speed) {  
25        motor.setPower(speed);  
26    }  
27  
28    public double getMotorRotations() {  
29        return motor.getCurrentPosition() / ticksPerRotation;  
30    }  
31  
32    public void setMotorZeroBehavior(DcMotor.ZeroPowerBehavior zeroBehavior) {  
33        motor.setZeroPowerBehavior(zeroBehavior);  
34    }  
35 }
```

Listing C.21: MotorOpMode\_7\_1.java

{209}------------------------------------------------

## C. Sample Solutions

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5 import com.qualcomm.robotcore.hardware.DcMotor;
6
7 @TeleOp()
8 public class MotorOpMode_7_1 extends OpMode {
9     ProgrammingBoard_7_1 board = new ProgrammingBoard_7_1();
10
11    @Override
12    public void init() {
13        board.init(hardwareMap);
14    }
15
16    @Override
17    public void loop() {
18        double motorSpeed = gamepad1.left_stick_y;
19
20        board.setMotorSpeed(motorSpeed);
21        telemetry.addData("speed", motorSpeed);
22        if (gamepad1.a) {
23            board.setMotorZeroBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
24            telemetry.addData("Zero", "Brake");
25        } else if (gamepad1.b) {
26            board.setMotorZeroBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
27            telemetry.addData("Zero", "Float");
28        }
29    }
30 }
```

Listing C.22: MotorOpMode\_7\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class MotorOpMode_7_2 extends OpMode {
8     ProgrammingBoard_7_1 board = new ProgrammingBoard_7_1();
9
10    @Override
11    public void init() {
12        board.init(hardwareMap);
13    }
14
15    double squareInputWithSign(double input) {
```

{210}------------------------------------------------

```
16 double output = input * input;
17 if (input < 0) {
18     output = output * -1;
19 }
20 return output;
21 }
22
23 @Override
24 public void loop() {
25 double motorSpeed = squareInputWithSign(gamepad1.left_stick_y);
26
27 board.setMotorSpeed(motorSpeed);
28 }
29 }
```

## C.8. Chapter 8 Solutions

Listing C.23: ProgrammingBoard\_8\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.hardware.DcMotor;
4 import com.qualcomm.robotcore.hardware.DigitalChannel;
5 import com.qualcomm.robotcore.hardware.HardwareMap;
6 import com.qualcomm.robotcore.hardware.Servo;
7
8 public class ProgrammingBoard_8_1 {
9     private DigitalChannel touchSensor;
10     private DcMotor motor;
11     private double ticksPerRotation;
12     private Servo servo;
13
14     public void init(HardwareMap hwMap) {
15         touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
16         touchSensor.setMode(DigitalChannel.Mode.INPUT);
17         motor = hwMap.get(DcMotor.class, "motor");
18         motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
19         ticksPerRotation = motor.getMotorType().getTicksPerRev();
20         servo = hwMap.get(Servo.class, "servo");
21         servo.setDirection(Servo.Direction.REVERSE);
22         servo.scaleRange(0.5, 1.0);
23     }
24
25     public boolean isTouchSensorPressed() {
26         return !touchSensor.getState();
27     }
```

{211}------------------------------------------------

## C. Sample Solutions

```
28 public void setMotorSpeed(double speed) {  
29     motor.setPower(speed);  
30 }  
31  
32 public double getMotorRotations() {  
33     return motor.getCurrentPosition() / ticksPerRotation;  
34 }  
35  
36 public void setServoPosition(double position) {  
37     servo.setPosition(position);  
38 }  
39 }  
40
```

Listing C.24: servoGamepadOpMode\_8\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard5;  
7  
8 @TeleOp()  
9 public class ServoGamepadOpMode_8_2 extends OpMode {  
10     ProgrammingBoard5 board = new ProgrammingBoard5();  
11  
12     @Override  
13     public void init() {  
14         board.init(hardwareMap);  
15     }  
16  
17     @Override  
18     public void loop() {  
19         board.setServoPosition(gamepad1.left_trigger);  
20     }  
21 }
```

## C.9. Chapter 9 Solutions

Listing C.25: ProgrammingBoard\_9\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.hardware.AnalogInput;  
4 import com.qualcomm.robotcore.hardware.DcMotor;
```

{212}------------------------------------------------

```
5 import com.qualcomm.robotcore.hardware.DigitalChannel;
6 import com.qualcomm.robotcore.hardware.HardwareMap;
7 import com.qualcomm.robotcore.hardware.Servo;
8 import com.qualcomm.robotcore.util.Range;
9
10 public class ProgrammingBoard_9_1 {
11     private DigitalChannel touchSensor;
12     private DcMotor motor;
13     private double ticksPerRotation;
14     private Servo servo;
15     private AnalogInput pot;
16
17     public void init(HardwareMap hwMap) {
18         touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
19         touchSensor.setMode(DigitalChannel.Mode.INPUT);
20         motor = hwMap.get(DcMotor.class, "motor");
21         motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
22         ticksPerRotation = motor.getMotorType().getTicksPerRev();
23         servo = hwMap.get(Servo.class, "servo");
24         pot = hwMap.get(AnalogInput.class, "pot");
25     }
26
27     public boolean isTouchSensorPressed() {
28         return !touchSensor.getState();
29     }
30
31     public void setMotorSpeed(double speed) {
32         motor.setPower(speed);
33     }
34
35     public double getMotorRotations() {
36         return motor.getCurrentPosition() / ticksPerRotation;
37     }
38
39     public void setServoPosition(double position) {
40         servo.setPosition(position);
41     }
42
43     public double getPotAngle() {
44         return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
45     }
46
47     public double getPotRange() {
48         return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 1.0);
49     }
50 }
```

Listing C.26: Exercise\_9\_2.java

{213}------------------------------------------------

## C. Sample Solutions

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class Exercise_9_2 extends OpMode {
8     ProgrammingBoard_9_1 board = new ProgrammingBoard_9_1();
9
10    @Override
11    public void init() {
12        board.init(hardwareMap);
13    }
14
15    @Override
16    public void loop() {
17        double potValue = board.getPotRange();
18
19        telemetry.addData("Pot Value", potValue);
20
21        board.setServoPosition(potValue);
22    }
23 }
```

## C.10. Chapter 10 Solutions

Listing C.27: ProgrammingBoard\_10\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.hardware.AnalogInput;
4 import com.qualcomm.robotcore.hardware.ColorSensor;
5 import com.qualcomm.robotcore.hardware.DcMotor;
6 import com.qualcomm.robotcore.hardware.DigitalChannel;
7 import com.qualcomm.robotcore.hardware.DistanceSensor;
8 import com.qualcomm.robotcore.hardware.HardwareMap;
9 import com.qualcomm.robotcore.hardware.Servo;
10 import com.qualcomm.robotcore.util.Range;
11
12 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
13
14 public class ProgrammingBoard_10_1 {
15     private DigitalChannel touchSensor;
16     private DcMotor motor;
17     private double ticksPerRotation;
18     private Servo servo;
```

{214}------------------------------------------------

```
19 private AnalogInput pot;
20 private ColorSensor colorSensor;
21 private DistanceSensor distanceSensor;
22
23 public void init(HardwareMap hwMap) {
24     touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
25     touchSensor.setMode(DigitalChannel.Mode.INPUT);
26     motor = hwMap.get(DcMotor.class, "motor");
27     motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
28     ticksPerRotation = motor.getMotorType().getTicksPerRev();
29     servo = hwMap.get(Servo.class, "servo");
30     pot = hwMap.get(AnalogInput.class, "pot");

31
32     colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
33     distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
34 }
35
36 public boolean isTouchSensorPressed() {
37     return !touchSensor.getState();
38 }
39
40 public void setMotorSpeed(double speed) {
41     motor.setPower(speed);
42 }
43
44 public double getMotorRotations() {
45     return motor.getCurrentPosition() / ticksPerRotation;
46 }
47
48 public void setServoPosition(double position) {
49     servo.setPosition(position);
50 }
51
52 public double getPotAngle() {
53     return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
54 }
55
56 public int getAmountRed() {
57     return colorSensor.red();
58 }
59
60 public int getAmountBlue() {
61     return colorSensor.blue();
62 }
63
64 public double getDistance(DistanceUnit du) {
65     return distanceSensor.getDistance(du);
66 }
67 }
```

{215}------------------------------------------------

## C. Sample Solutions

Listing C.28: Exercise\_10\_1\_OpMode.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
7
8 @TeleOp()
9 public class Exercise_10_1_OpMode extends OpMode {
10     ProgrammingBoard_10_1 board = new ProgrammingBoard_10_1();
11
12     @Override
13     public void init() {
14         board.init(hardwareMap);
15     }
16
17     @Override
18     public void loop() {
19         telemetry.addData("Amount blue", board.getAmountBlue());
20         telemetry.addData("Distance (CM)", board.getDistance(DistanceUnit.CM));
21         telemetry.addData("Distance (IN)", board.getDistance(DistanceUnit.INCH));
22     }
23 }
```

Listing C.29: Exercise\_10\_2\_OpMode.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
7
8 @TeleOp()
9 public class Exercise_10_2_OpMode extends OpMode {
10     ProgrammingBoard_10_1 board = new ProgrammingBoard_10_1();
11
12     @Override
13     public void init() {
14         board.init(hardwareMap);
15     }
16
17     @Override
18     public void loop() {
19         double distanceCM = board.getDistance(DistanceUnit.CM);
```

{216}------------------------------------------------

```
20 if (distanceCM < 10.0) {  
21     board.setMotorSpeed(0.0);  
22 } else {  
23     board.setMotorSpeed(0.5);  
24 }  
25 telemetry.addData("Distance (CM)", distanceCM);  
26 }  
27 }
```

## C.11. Chapter 11 Solutions

Listing C.30: Exercise\_11\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;  
7 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;  
8  
9 @TeleOp()  
10 public class Exercise_11_1 extends OpMode {  
11     ProgrammingBoard8 board = new ProgrammingBoard8();  
12  
13     @Override  
14     public void init() {  
15         board.init(hardwareMap);  
16     }  
17  
18     @Override  
19     public void loop() {  
20         telemetry.addData("Our Heading (DEG)", board.getHeading(AngleUnit.DEGREES));  
21         telemetry.addData("Our Heading (RAD)", board.getHeading(AngleUnit.RADIANS));  
22     }  
23 }
```

Listing C.31: Exercise\_11\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5 import com.qualcomm.robotcore.util.Range;  
6  
7 import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
```

{217}------------------------------------------------

## C. Sample Solutions

```
8 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;
9
10 @TeleOp()
11 public class Exercise_11_2 extends OpMode {
12     ProgrammingBoard8 board = new ProgrammingBoard8();
13
14     @Override
15     public void init() {
16         board.init(hardwareMap);
17     }
18
19     @Override
20     public void loop() {
21         double headingDegrees = board.getHeading(AngleUnit.DEGREES);
22         double motorSpeed = Range.scale(headingDegrees, -180, 180, -1.0, 1.0);
23
24         telemetry.addData("Our Heading (DEG)", headingDegrees);
25         telemetry.addData("Motor Speed", motorSpeed);
26
27         board.setMotorSpeed(motorSpeed);
28     }
29 }
```

## C.12. Chapter 12 Solutions

Listing C.32: Exercise\_12\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
4 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;
7
8 @Autonomous()
9 public class Exercise_12_1 extends OpMode {
10     enum State {
11         START,
12         QUARTER_SPEED,
13         HALF_SPEED,
14         THREE_QUARTERS_SPEED,
15         FULL_SPEED,
16         DONE
17     }
18
19     ProgrammingBoard8 board = new ProgrammingBoard8();
```

{218}------------------------------------------------

```
20 State state = State.START;
21 double lastStepTime;
22
23 @Override
24 public void init() {
25 board.init(hardwareMap);
26 }
27
28 @Override
29 public void start() {
30 state = State.START;
31 }
32
33 @Override
34 public void loop() {
35 telemetry.addData("State", state);
36 switch (state) {
37 case START:
38 board.setMotorSpeed(0.250);
39 state = State.QUARTER_SPEED;
40 lastStepTime = getRuntime();
41 break;
42 case QUARTER_SPEED:
43 if (getRuntime() > lastStepTime + .250) {
44 board.setMotorSpeed(0.500);
45 state = State.HALF_SPEED;
46 lastStepTime = getRuntime();
47 }
48 break;
49 case HALF_SPEED:
50 if (getRuntime() > lastStepTime + .250) {
51 board.setMotorSpeed(0.750);
52 state = State.THREE_QUARTERS_SPEED;
53 lastStepTime = getRuntime();
54 }
55 break;
56 case THREE_QUARTERS_SPEED:
57 if (getRuntime() > lastStepTime + .250) {
58 board.setMotorSpeed(1.00);
59 state = State.FULL_SPEED;
60 }
61 break;
62 case FULL_SPEED:
63 if (board.isTouchSensorPressed()) {
64 board.setMotorSpeed(0.0);
65 state = State.DONE;
66 }
67 break;
68 default:
```

{219}------------------------------------------------

## C. Sample Solutions

```
69 telemetry.addData("Auto", "Finished");
70 }
71 }
72 }
```

Listing C.33: Exercise\_12\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
4 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
5
6 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
7 import org.firstinspires.ftc.teamcode.mechanisms.ProgrammingBoard8;
8
9 @Autonomous()
10 public class Exercise_12_2 extends OpMode {
11     enum State {
12         START,
13         GO_UNTIL_DISTANCE,
14         TURN_SERVO,
15         DONE
16     }
17
18     ProgrammingBoard8 board = new ProgrammingBoard8();
19     State state = State.START;
20     double lastStepTime;
21
22     @Override
23     public void init() {
24         board.init(hardwareMap);
25         state = State.START;
26     }
27
28     @Override
29     public void loop() {
30         telemetry.addData("State", state);
31         switch (state) {
32             case START:
33                 board.setMotorSpeed(0.5);
34                 board.setServoPosition(0.0);
35                 resetRuntime();
36                 state = State.GO_UNTIL_DISTANCE;
37                 break;
38             case GO_UNTIL_DISTANCE:
39                 if ((board.getDistance(DistanceUnit.CM) < 10) || (getRuntime() > 5.0) <-->
40                     <--> ) {
41                     board.setMotorSpeed(0.0);
42                     state = State.TURN_SERVO;
```

{220}------------------------------------------------

```
42 }
43     break;
44 case TURN_SERVO:
45     board.setServoPosition(0.5);
46     state = State.DONE;
47     break;
48 default:
49     telemetry.addData("Auto", "Finished");
50 }
51 }
52 }
```

## C.13. Chapter 13 Solutions

Listing C.34: Exercise\_13\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class Exercise_13_1 extends OpMode {
8     String[] lines = {
9         "Then he waddled away",
10         "(Waddle waddle)",
11         "Then he waddled away",
12         "(Waddle waddle waddle)",
13         "Then he waddled away",
14         "(Waddle waddle)",
15         "'Til the very next day",
16         "(Bum bum bum bum bum ba-dum)";
17
18     int lineIndex;
19     double DELAY_SECS = 0.5;
20
21     double nextTime;
22
23     @Override
24     public void init() {
25         lineIndex = 0;
26     }
27
28     @Override
29     public void loop() {
30         if (nextTime < getRuntime()) {
31             lineIndex++;
32         }
33         nextTime = getRuntime() + DELAY_SECS;
34     }
35 }
```

{221}------------------------------------------------

## C. Sample Solutions

```
31 if (lineIndex >= lines.length) {  
32     lineIndex = lines.length - 1;  
33 }  
34 nextTime = getRuntime() + DELAY_SECS;  
35 }  
36 telemetry.addLine(lines[lineIndex]);  
37 }  
38 }
```

Listing C.35: Exercise\_13\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
5  
6 import java.util.ArrayList;  
7  
8 @TeleOp()  
9 public class Exercise_13_2 extends OpMode {  
10     ArrayList<String> lines = new ArrayList<>();  
11  
12     int lineIndex;  
13     double DELAY_SECS = 0.5;  
14  
15     double nextTime;  
16  
17     @Override  
18     public void init() {  
19         lineIndex = 0;  
20         lines.clear();  
21         lines.add("Then he waddled away");  
22         lines.add("Waddle waddle");  
23         lines.add("Then he waddled away");  
24         lines.add("Waddle waddle waddle");  
25         lines.add("Then he waddled away");  
26         lines.add("Waddle waddle");  
27         lines.add("Til the very next day");  
28         lines.add("Bum bum bum bum ba-dum");  
29         lines.add("");  
30     }  
31  
32     @Override  
33     public void loop() {  
34         if (nextTime < getRuntime()) {  
35             lineIndex++;  
36             if (lineIndex >= lines.size()) {  
37                 lineIndex = 0;  
38             }  
39         }  
40     }  
41 }
```

{222}------------------------------------------------

```
39         nextTime = getRuntime() + DELAY_SECS;
40     }
41     telemetry.addData(lines.get(lineIndex));
42 }
43 }
```

## C.14. Chapter 14 Solutions

Listing C.36: TestDigitalChannel\_14\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.hardware.DigitalChannel;
4
5 import org.firstinspires.ftc.robotcore.external.Telemetry;
6 import org.firstinspires.ftc.teamcode.mechanisms.TestItem;
7
8 public class TestDigitalChannel_14_1 extends TestItem {
9     private DigitalChannel digitalChannel;
10
11    public TestDigitalChannel_14_1(String description, DigitalChannel channel) {
12        super(description);
13        this.digitalChannel = channel;
14    }
15
16    @Override
17    public void run(boolean on, Telemetry telemetry) {
18        telemetry.addData("Sensor state: ", digitalChannel.getState());
19    }
20 }
```

Listing C.37: ProgrammingBoard\_14\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
4 import com.qualcomm.robotcore.hardware.AnalogInput;
5 import com.qualcomm.robotcore.hardware.ColorSensor;
6 import com.qualcomm.robotcore.hardware.DcMotor;
7 import com.qualcomm.robotcore.hardware.DigitalChannel;
8 import com.qualcomm.robotcore.hardware.DistanceSensor;
9 import com.qualcomm.robotcore.hardware.HardwareMap;
10 import com.qualcomm.robotcore.hardware.IMU;
11 import com.qualcomm.robotcore.hardware.Servo;
12 import com.qualcomm.robotcore.util.Range;
```

{223}------------------------------------------------

## C. Sample Solutions

```
14 import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
15 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
16 import org.firstinspires.ftc.teamcode.mechanisms.TestAnalogInput;
17 import org.firstinspires.ftc.teamcode.mechanisms.TestItem;
18 import org.firstinspires.ftc.teamcode.mechanisms.TestMotor;
19
20 import java.util.ArrayList;
21
22 public class ProgrammingBoard_14_1 {
23     private DigitalChannel touchSensor;
24     private DcMotor motor;
25     private double ticksPerRotation;
26     private Servo servo;
27     private AnalogInput pot;
28     private ColorSensor colorSensor;
29     private DistanceSensor distanceSensor;
30     private IMU imu;
31
32     public void init(HardwareMap hwMap) {
33         touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
34         touchSensor.setMode(DigitalChannel.Mode.INPUT);
35         motor = hwMap.get(DcMotor.class, "motor");
36         motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
37         ticksPerRotation = motor.getMotorType().getTicksPerRev();
38         servo = hwMap.get(Servo.class, "servo");
39         pot = hwMap.get(AnalogInput.class, "pot");
40
41         colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
42         distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
43         imu = hwMap.get(IMU.class, "imu");
44
45         RevHubOrientationOnRobot RevOrientation =
46             new RevHubOrientationOnRobot(RevHubOrientationOnRobot.<-
47                 LogoFacingDirection.UP,
48                 RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
49
50         imu.initialize(new IMU.Parameters(RevOrientation));
51     }
52
53     public boolean isTouchSensorPressed() {
54         return !touchSensor.getState();
55     }
56
57     public void setMotorSpeed(double speed) {
58         motor.setPower(speed);
59     }
60
61     public double getMotorRotations() {
62         return motor.getCurrentPosition() / ticksPerRotation;

```

{224}------------------------------------------------

```
62 }6364 public void setServoPosition(double position) {65 servo.setPosition(position);66 }6768 public double getPotAngle() {69 return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);70 }7172 public int getAmountRed() {73 return colorSensor.red();74 }7576 public double getDistance(DistanceUnit du) {77 return distanceSensor.getDistance(du);78 }7980 public double getHeading(AngleUnit angleUnit) {81 return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);82 }8384 public ArrayList<TestItem> getTests() {85 ArrayList<TestItem> tests = new ArrayList<>();86 tests.add(new TestMotor("PB Motor", 0.5, motor));87 tests.add(new TestAnalogInput("PB Pot", pot, 0, 270));88 tests.add(new TestDigitalChannel_14_1("PB Touch", touchSensor));8990 return tests;91 }92 }
```

Listing C.38: TestServo\_14\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;23 import com.qualcomm.robotcore.hardware.Servo;45 import org.firstinspires.ftc.robotcore.external.Telemetry;6 import org.firstinspires.ftc.teamcode.mechanisms.TestItem;78 public class TestServo_14_2 extends TestItem {9 private Servo servo;10 double onValue;11 double offValue;1213 public TestServo_14_2(String description, Servo servo, double offValue, double ←14 → onValue) {15 super(description);}
```

{225}------------------------------------------------

## C. Sample Solutions

```
15 this.servo = servo;
16 this.onValue = onValue;
17 this.offValue = offValue;
18 }
19
20 @Override
21 public void run(boolean on, Telemetry telemetry) {
22     if (on) {
23         servo.setPosition(onValue);
24     } else {
25         servo.setPosition(offValue);
26     }
27 }
28 }
```

Listing C.39: ProgrammingBoard\_14\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
4 import com.qualcomm.robotcore.hardware.AnalogInput;
5 import com.qualcomm.robotcore.hardware.ColorSensor;
6 import com.qualcomm.robotcore.hardware.DcMotor;
7 import com.qualcomm.robotcore.hardware.DigitalChannel;
8 import com.qualcomm.robotcore.hardware.DistanceSensor;
9 import com.qualcomm.robotcore.hardware.HardwareMap;
10 import com.qualcomm.robotcore.hardware.IMU;
11 import com.qualcomm.robotcore.hardware.Servo;
12 import com.qualcomm.robotcore.util.Range;
13
14 import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
15 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
16 import org.firstinspires.ftc.teamcode.mechanisms.TestAnalogInput;
17 import org.firstinspires.ftc.teamcode.mechanisms.TestItem;
18 import org.firstinspires.ftc.teamcode.mechanisms.TestMotor;
19
20 import java.util.ArrayList;
21
22 public class ProgrammingBoard_14_2 {
23     private DigitalChannel touchSensor;
24     private DcMotor motor;
25     private double ticksPerRotation;
26     private Servo servo;
27     private AnalogInput pot;
28     private ColorSensor colorSensor;
29     private DistanceSensor distanceSensor;
30     private IMU imu;
31
32     public void init(HardwareMap hwMap) {
```

{226}------------------------------------------------

```
33 touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
34 touchSensor.setMode(DigitalChannel.Mode.INPUT);
35 motor = hwMap.get(DcMotor.class, "motor");
36 motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
37 ticksPerRotation = motor.getMotorType().getTicksPerRev();
38 servo = hwMap.get(Servo.class, "servo");
39 pot = hwMap.get(AnalogInput.class, "pot");

40

41 colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
42 distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
43 imu = hwMap.get(IMU.class, "imu");

44

45 RevHubOrientationOnRobot RevOrientation =
46     new RevHubOrientationOnRobot(RevHubOrientationOnRobot.←
47         ↳ LogoFacingDirection.UP,
48         RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

49

50 imu.initialize(new IMU.Parameters(RevOrientation));
51 }

52 public boolean isTouchSensorPressed() {
53     return !touchSensor.getState();
54 }

55 public void setMotorSpeed(double speed) {
56     motor.setPower(speed);
57 }

58 public double getMotorRotations() {
59     return motor.getCurrentPosition() / ticksPerRotation;
60 }

61 public void setServoPosition(double position) {
62     servo.setPosition(position);
63 }

64 public double getPotAngle() {
65     return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
66 }

67 public int getAmountRed() {
68     return colorSensor.red();
69 }

70 public double getDistance(DistanceUnit du) {
71     return distanceSensor.getDistance(du);
72 }

73 public double getHeading(AngleUnit angleUnit) {

```

{227}------------------------------------------------

## C. Sample Solutions

```
81 return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
82 }
83
84 public ArrayList<TestItem> getTests() {
85     ArrayList<TestItem> tests = new ArrayList<>();
86     tests.add(new TestMotor("PB Motor", 0.5, motor));
87     tests.add(new TestAnalogInput("PB Pot", pot, 0, 270));
88     tests.add(new TestDigitalChannel_14_1("PB Touch", touchSensor));
89     tests.add(new TestServo_14_2("PB Servo", servo, 0.0, 1.0));
90
91     return tests;
92 }
93 }
```

Listing C.40: ProgrammingBoard\_14\_3\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.hardware.DigitalChannel;
4 import com.qualcomm.robotcore.hardware.HardwareMap;
5
6 public class ProgrammingBoard_14_3_1 {
7     DigitalChannel touchSensor;
8
9     public void init(HardwareMap hwMap) {
10         touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
11         touchSensor.setMode(DigitalChannel.Mode.INPUT);
12     }
13
14     public boolean getTouchSensorState() {
15         return touchSensor.getState();
16     }
17 }
```

Listing C.41: ProgrammingBoard\_14\_3\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 public class ProgrammingBoard_14_3_2 extends ProgrammingBoard_14_3_1 {
4     public boolean isTouchSensorPressed() {
5         return !touchSensor.getState();
6     }
7 }
```

Listing C.42: ProgrammingBoard\_14\_3\_3.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
```

{228}------------------------------------------------

```
3 import com.qualcomm.robotcore.hardware.DcMotor;
4 import com.qualcomm.robotcore.hardware.HardwareMap;
5
6 public class ProgrammingBoard_14_3_3 extends ProgrammingBoard_14_3_2 {
7     protected DcMotor motor;
8
9     public void init(HardwareMap hwMap) {
10         super.init(hwMap);
11         motor = hwMap.get(DcMotor.class, "motor");
12         motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
13     }
14
15     public void setMotorSpeed(double speed) {
16         motor.setPower(speed);
17     }
18 }
```

Listing C.43: ProgrammingBoard\_14\_3\_4.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.hardware.HardwareMap;
4
5 public class ProgrammingBoard_14_3_4 extends ProgrammingBoard_14_3_3 {
6     private double ticksPerRotation;
7
8     public void init(HardwareMap hwMap) {
9         super.init(hwMap);
10         ticksPerRotation = motor.getMotorType().getTicksPerRev();
11     }
12
13     public double getMotorRotations() {
14         return motor.getCurrentPosition() / ticksPerRotation;
15     }
16 }
```

Listing C.44: ProgrammingBoard\_14\_3\_5.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.hardware.HardwareMap;
4 import com.qualcomm.robotcore.hardware.Servo;
5
6 public class ProgrammingBoard_14_3_5 extends ProgrammingBoard_14_3_4 {
7     protected Servo servo;
8
9     public void init(HardwareMap hwMap) {
10         super.init(hwMap);
11         servo = hwMap.get(Servo.class, "servo");
```

{229}------------------------------------------------

## C. Sample Solutions

```
12 }  
13  
14 public void setServoPosition(double position) {  
15     servo.setPosition(position);  
16 }  
17 }
```

Listing C.45: ProgrammingBoard\_14\_3\_6.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.hardware.AnalogInput;  
4 import com.qualcomm.robotcore.hardware.HardwareMap;  
5 import com.qualcomm.robotcore.util.Range;  
6  
7 public class ProgrammingBoard_14_3_6 extends ProgrammingBoard_14_3_5 {  
8     protected AnalogInput pot;  
9  
10    public void init(HardwareMap hwMap) {  
11        pot = hwMap.get(AnalogInput.class, "pot");  
12    }  
13  
14    public double getPotAngle() {  
15        return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);  
16    }  
17 }
```

Listing C.46: ProgrammingBoard\_14\_3\_7.java

```
1 package org.firstinspires.ftc.teamcode.solutions;  
2  
3 import com.qualcomm.robotcore.hardware.ColorSensor;  
4 import com.qualcomm.robotcore.hardware.DistanceSensor;  
5 import com.qualcomm.robotcore.hardware.HardwareMap;  
6  
7 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;  
8  
9 public class ProgrammingBoard_14_3_7 extends ProgrammingBoard_14_3_6 {  
10    protected ColorSensor colorSensor;  
11    protected DistanceSensor distanceSensor;  
12  
13    public void init(HardwareMap hwMap) {  
14        super.init(hwMap);  
15        colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");  
16        distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");  
17    }  
18  
19    public int getAmountRed() {  
20        return colorSensor.red();
```

{230}------------------------------------------------

```
21 }
22
23 public double getDistance(DistanceUnit du) {
24     return distanceSensor.getDistance(du);
25 }
26 }
```

Listing C.47: ProgrammingBoard\_14\_3\_8.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
4 import com.qualcomm.robotcore.hardware.HardwareMap;
5 import com.qualcomm.robotcore.hardware.IMU;
6
7 import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
8
9 public class ProgrammingBoard_14_3_8 extends ProgrammingBoard_14_3_7 {
10     private IMU imu;
11
12     public void init(HardwareMap hwMap) {
13         super.init(hwMap);
14
15         imu = hwMap.get(IMU.class, "imu");
16
17         RevHubOrientationOnRobot RevOrientation =
18             new RevHubOrientationOnRobot(RevHubOrientationOnRobot.←
19                 → LogoFacingDirection.UP,
20                 RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
21
22         imu.initialize(new IMU.Parameters(RevOrientation));
23     }
24
25     public double getHeading(AngleUnit angleUnit) {
26         return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
27     }
28 }
```

Listing C.48: ProgrammingBoard\_14\_3\_9.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import org.firstinspires.ftc.teamcode.mechanisms.TestAnalogInput;
4 import org.firstinspires.ftc.teamcode.mechanisms.TestItem;
5 import org.firstinspires.ftc.teamcode.mechanisms.TestMotor;
6
7 import java.util.ArrayList;
8
9 public class ProgrammingBoard_14_3_9 extends ProgrammingBoard_14_3_8 {
```

{231}------------------------------------------------

## C. Sample Solutions

```
public ArrayList<TestItem> getTests() {  
    ArrayList<TestItem> tests = new ArrayList<>();  
    tests.add(new TestMotor("PB Motor", 0.5, motor));  
    tests.add(new TestAnalogInput("PB Pot", pot, 0, 270));  
    return tests;  
}
```

### C.15. Chapter 15 Solutions

Listing C.49: Exercise\_15\_1.java

```
package org.firstinspires.ftc.teamcode.solutions;  
  
import com.qualcomm.robotcore.eventloop.opmode.OpMode;  
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;  
  
@TeleOp()  
public class Exercise_15_1 extends OpMode {  
    ProgrammingBoard_6_1 board = new ProgrammingBoard_6_1();  
    boolean wasPressed;  
  
    @Override  
    public void init() {  
        board.init(hardwareMap);  
    }  
  
    @Override  
    public void loop() {  
        if (board.isTouchSensorPressed() && !wasPressed) {  
            gamepad1.rumbleBlips(3);  
        }  
        wasPressed = board.isTouchSensorPressed();  
    }  
}
```

### C.16. Chapter 16 Solutions

Listing C.50: Exercise\_16\_1.java

```
package org.firstinspires.ftc.teamcode.solutions;  
  
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;  
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
```

{232}------------------------------------------------

```
5
6 import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
7 import org.firstinspires.ftc.teamcode.processors.FirstVisionProcessor;
8 import org.firstinspires.ftc.vision.VisionPortal;
9
10 @Autonomous()
11 public class Exercise_16_1 extends OpMode {
12
13 private FirstVisionProcessor visionProcessor;
14 private VisionPortal visionPortal;
15 ProgrammingBoard_8_1 board = new ProgrammingBoard_8_1();
16
17 @Override
18 public void init() {
19     visionProcessor = new FirstVisionProcessor();
20     visionPortal = VisionPortal.easyCreateWithDefaults(
21         hardwareMap.get(WebcamName.class, "Webcam 1"), visionProcessor);
22     board.init(hardwareMap);
23 }
24
25 @Override
26 public void init_loop() {
27 }
28
29 @Override
30 public void start() {
31     visionPortal.stopStreaming();
32 }
33
34 @Override
35 public void loop() {
36     telemetry.addData("Identified", visionProcessor.getSelection());
37     switch (visionProcessor.getSelection()) {
38         case LEFT:
39         case NONE:
40             board.setServoPosition(0);
41             break;
42         case MIDDLE:
43             board.setServoPosition(90);
44             break;
45         case RIGHT:
46             board.setServoPosition(180);
47             break;
48     }
49 }
50 }
```

{233}------------------------------------------------

## C. Sample Solutions

### C.17. Chapter 17 Solutions

Listing C.51: ProgrammingBoard\_17\_1.java

```
package org.firstinspires.ftc.teamcode.solutions;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.mechanisms.TestAnalogInput;
import org.firstinspires.ftc.teamcode.mechanisms.TestItem;
import org.firstinspires.ftc.teamcode.mechanisms.TestMotor;

import java.util.ArrayList;

public class ProgrammingBoard_17_1 {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;
    private Servo servo;
    private AnalogInput pot;
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;
    private IMU imu;

    /**
     * This initializes our programming board and gets it ready for use.
     * It MUST be called before any of the other methods
     *
     * @param hwMap the hardware map from the opMode
     */
    public void init(HardwareMap hwMap) {
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = motor.getMotorType().getTicksPerRev();
        servo = hwMap.get(Servo.class, "servo");
        pot = hwMap.get(AnalogInput.class, "pot");
    }

```

{234}------------------------------------------------

```
46
47 colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
48 distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
49 imu = hwMap.get(IMU.class, "imu");

50
51 RevHubOrientationOnRobot RevOrientation =
52     new RevHubOrientationOnRobot(RevHubOrientationOnRobot.<--  
        LogoFacingDirection.UP,  
        RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

53
54
55 imu.initialize(new IMU.Parameters(RevOrientation));

56 }
57
58 /**
59 * @return whether the touch sensor is pressed or not
60 */
61 public boolean isTouchSensorPressed() {
62     return !touchSensor.getState();
63 }
64
65 /**
66 * @param speed the speed (-1.0 to 1.0) where negative is backwards
67 */
68 public void setMotorSpeed(double speed) {
69     motor.setPower(speed);
70 }
71
72 /**
73 * @return returns the number of rotations from the encoder
74 */
75 public double getMotorRotations() {
76     return motor.getCurrentPosition() / ticksPerRotation;
77 }
78
79 /**
80 * @param position the position (0.0-1.0) for the servo
81 */
82 public void setServoPosition(double position) {
83     servo.setPosition(position);
84 }
85
86 /**
87 * @return the angle (0 - 270) the potentiometer is pointed to
88 */
89 public double getPotAngle() {
90     return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
91 }
92
93 /**

```

{235}------------------------------------------------

## C. Sample Solutions

```
94     * @return the amount red (0-255) the color sensor sees
95     */
96
97     public int getAmountRed() {
98         return colorSensor.red();
99     }
100
101    /**
102     * @param du what units to return distance in
103     * @return distance seen by distance sensor
104     */
105
106     public double getDistance(DistanceUnit du) {
107         return distanceSensor.getDistance(du);
108     }
109
110    /**
111     * @param angleUnit what units to return the angle in
112     * @return the heading (Z axis of the IMU)
113     */
114     public double getHeading(AngleUnit angleUnit) {
115         return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
116     }
117
118    /**
119     * @return a list of tests for the hardware on the board - used by TestWiring
120     */
121
122     public ArrayList<TestItem> getTests() {
123         ArrayList<TestItem> tests = new ArrayList<>();
124         tests.add(new TestMotor("PB Motor", 0.5, motor));
125         tests.add(new TestAnalogInput("PB Pot", pot, 0, 270));
126         return tests;
127     }
128 }
```

Listing C.52: TestMotor\_17\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.hardware.DcMotor;
4
5 import org.firstinspires.ftc.robotcore.external.Telemetry;
6 import org.firstinspires.ftc.teamcode.mechanisms.TestItem;
7
8
9 public class TestMotor_17_2 extends TestItem {
10     private double speed;
11     private DcMotor motor;
```

{236}------------------------------------------------

```
12 /**
13 * @param description what to show for the name of this test
14 * @param speed the speed that "on" should run the motor at
15 * @param motor the motor to test
16 */
17
18 public TestMotor_17_2(String description, double speed, DcMotor motor) {
19     super(description);
20     this.speed = speed;
21     this.motor = motor;
22 }
23
24 /**
25 * Runs the motor test and reports encoder values to telemetry
26 *
27 * @param on whether to run the motor or stop the motor
28 * @param telemetry where to put the encoder results
29 */
30 @Override
31 public void run(boolean on, Telemetry telemetry) {
32     if (on) {
33         motor.setPower(speed);
34     } else {
35         motor.setPower(0.0);
36     }
37     telemetry.addData("Encoder:", motor.getCurrentPosition());
38 }
39 }
```

## C.18. Chapter 18 Solutions

Listing C.53: Exercise\_18\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 @TeleOp()
7 public class Exercise_18_1 extends OpMode {
8     boolean wasA;
9
10     @Override
11     public void init() {
12         telemetry.speak("Initialized");
13     }
```

{237}------------------------------------------------

## C. Sample Solutions

```
14
15     @Override
16     public void loop() {
17         if (gamepad1.a && !wasA) {
18             telemetry.speak("A button pressed");
19         }
20         wasA = gamepad1.a;
21     }
22 }
```

## C.19. Chapter 19 Solutions

Listing C.54: Exercise\_19\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
7 import org.firstinspires.ftc.teamcode.Polar;
8
9 @TeleOp()
10 public class Exercise_19_1 extends OpMode {
11     @Override
12     public void init() {
13     }
14
15     @Override
16     public void loop() {
17         Polar leftStick = new Polar(gamepad1.left_stick_x, -gamepad1.left_stick_y);
18         Polar rightStick = new Polar(gamepad1.right_stick_x, -gamepad1.right_stick_y)←
19             ↳ ;
20
21         telemetry.addData("Left", "%f", leftStick.getAngle(AngleUnit.DEGREES));
22         telemetry.addData("Right", "%f", rightStick.getAngle(AngleUnit.DEGREES));
23     }
24 }
```

Listing C.55: Exercise\_19\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
```

{238}------------------------------------------------

```
5
6 import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
7 import org.firstinspires.ftc.teamcode.Polar;
8
9 @TeleOp()
10 public class Exercise_19_2 extends OpMode {
11     @Override
12     public void init() {
13     }
14
15     @Override
16     public void loop() {
17         Polar leftStick = new Polar(gamepad1.left_stick_x, -gamepad1.left_stick_y);
18         Polar rightStick = new Polar(gamepad1.right_stick_x, -gamepad1.right_stick_y)←
19             ↳ ;
20
21         telemetry.addData("Left", "%+.0f", leftStick.getAngle(AngleUnit.DEGREES));
22         telemetry.addData("Right", "%+.0f", rightStick.getAngle(AngleUnit.DEGREES));
23     }
24 }
```

## C.20. Chapter 20 Solutions

Listing C.56: Exercise\_20\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.TwoMotorDrive;
7
8 @TeleOp()
9 public class Exercise_20_1 extends OpMode {
10     TwoMotorDrive drive = new TwoMotorDrive();
11
12     @Override
13     public void init() {
14         drive.init(hardwareMap);
15     }
16
17     @Override
18     public void loop() {
19         double forward = -gamepad1.left_stick_y;
20         double right = gamepad1.left_stick_x;
```

{239}------------------------------------------------

## C. Sample Solutions

```
21 // half speed unless TURBO button pressed
22 if (!gamepad1.a) {
23     forward = forward / 2;
24     right = right / 2;
25 }
26
27 drive.setPowers(forward + right, forward - right);
28 }
29 }
30 }
```

Listing C.57: Exercise\_20\_2.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
4 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
5
6 import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrive;
7
8 @TeleOp()
9 public class Exercise_20_2 extends OpMode {
10     MecanumDrive drive = new MecanumDrive();
11
12     @Override
13     public void init() {
14         drive.init(hardwareMap);
15     }
16
17     @Override
18     public void loop() {
19         double forward = -gamepad1.left_stick_y;
20         double right = gamepad1.left_stick_x;
21         double rotate = gamepad1.right_stick_x / 2;
22
23         drive.drive(forward, right, rotate);
24     }
25 }
```

## C.21. Chapter 21 Solutions

Listing C.58: Exercise\_21\_1.java

```
1 package org.firstinspires.ftc.teamcode.solutions;
2
3 import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
```

{240}------------------------------------------------

```
4 import com.qualcomm.robotcore.eventloop.opmode.OpMode;
5 import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
6
7 import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
8 import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
9 import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
10 import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
11 import org.firstinspires.ftc.vision.VisionPortal;
12 import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
13 import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
14 import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
15
16 import java.util.List;
17
18 @TeleOp
19 public class Exercise_21_1 extends OpMode {
20     private AprilTagProcessor aprilTagProcessor;
21     private VisionPortal visionPortal;
22     SparkFunOTOS sparkfunOTOS;
23
24     @Override
25     public void init() {
26         WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
27         sparkfunOTOS = hardwareMap.get(SparkFunOTOS.class, "otos");
28         configureOTOS();
29         aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
30         visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, ←
31             aprilTagProcessor);
32     }
33
34     private void configureOTOS() {
35         sparkfunOTOS.setLinearUnit(DistanceUnit.INCH);
36         sparkfunOTOS.setAngularUnit(AngleUnit.DEGREES);
37         sparkfunOTOS.setOffset(new SparkFunOTOS.Pose2D(0, 0, 0));
38         sparkfunOTOS.setLinearScalar(1.0);
39         sparkfunOTOS.setAngularScalar(1.0);
40         sparkfunOTOS.resetTracking();
41         sparkfunOTOS.setPosition(new SparkFunOTOS.Pose2D(0,0,0));
42         sparkfunOTOS.calibrateImu(255, false);
43     }
44
45     @Override
46     public void loop() {
47         List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections()←
48             ;
49         StringBuilder idsFound = new StringBuilder();
50         SparkFunOTOS.Pose2D pose2D = sparkfunOTOS.getPosition();

```

{241}------------------------------------------------

## C. Sample Solutions

```
51 for (AprilTagDetection detection : currentDetections) {  
52     sparkfunOTOS.setPosition(convertFromAprilTag(detection.metadata.←  
53         ← fieldPosition, detection.ftcPose, pose2D.h));  
54     idsFound.append(detection.id);  
55     idsFound.append(' ');  
56 }  
57 telemetry.addData("April Tags", idsFound);  
58 SparkFunOTOS.Pose2D pos = sparkfunOTOS.getPosition();  
59 telemetry.addData("X (inch)", pos.x);  
60 telemetry.addData("Y (inch)", pos.y);  
61 telemetry.addData("Heading (degrees)", pos.h);  
62 }  
63 private SparkFunOTOS.Pose2D convertFromAprilTag(VectorF fieldPosition, ←  
64     ← AprilTagPoseFtc pose, double heading){  
65     SparkFunOTOS.Pose2D pose2D = new SparkFunOTOS.Pose2D();  
66     pose2D.x = fieldPosition.get(0) + pose.x;  
67     pose2D.y = fieldPosition.get(1) + pose.y;  
68     pose2D.h = heading;  
69     // TODO: Convert from Camera position to Robot Center  
70     // this is different for every robot so you'll have to do this.  
71     return pose2D;  
72 }
```

{242}------------------------------------------------

## D. Credits

Thanks to the following people that provided feedback on earlier versions of the book to make it better. It is better for them, but I bear the responsibility for any and all errors. If you have comments, please put them in at <https://github.com/alan412/LearnJavaForFTC/issues/new/choose>

- Karen (FTC #18175 - Team Techies)
- Joshua (FTC #16072 - Quantum Quacks)
- Eli (FTC #8569 - RoboKnights)
- Teja (alumnae of FTC #16072 - Quantum Quacks)
- Dan (FTC #10273 - The Cat in the Hat Comes Back)
- Abigail (alumnae of FRC #3459 - Team PyroTech)
- Ellie (FTC #8569 - RoboKnights)
- Ryan (alumna of FTC #16072 - Quantum Quacks)
- Burton (FTC #11214 - Ground Shakers)
- Michael (alumna of FTC #4634 - FROGbots)
- Jason (FTC #18291 - Mech Warriors)
- Roy Brabson (FTC #7083 - Tundrabots)
- Ovies Brabson (FTC #7083 - Tundrabots)
- Sebastien Erives (FTC #12887 - Devolt Phobos)
- Michael Hoogasian

Thanks to FTC Team 14169 - PartyTime Carets for giving some photos of their TSE to be used in the Computer Vision chapter.

{243}------------------------------------------------

![](_page_243_Picture_0.jpeg)

{244}------------------------------------------------

# Index

%, 19  
( and ), 19  
\*, 19  
+, 19  
-, 19  
/, 19  
=, 19  
@Autonomous(), 4  
@Disabled(), 4  
@Override, 5  
@Teleop(), 4

**A**  
abstract, 97  
addData, 5  
Analog Sensors, 63  
AnalogInput, 64  
AngleUnit, 75  
annotation, 4  
AprilTags, 109  
Arcade Drive, 141  
ArrayList, 92  
Arrays, 91  
assignment operators, 20

**B**  
boolean, 14  
byte, 14

**C**  
Cartesian, 148  
char, 14  
class, 2, 5

Class Members, 29  
Class Methods, 30  
Classes, 29  
Color sensor, 67  
ColorSensor, 68  
Comments, 8  
conditional operators, 22  
Configuration file, 41  
Constructors, 32

**D**  
DcMotor, 51  
DcMotorSimple.Direction.FORWARD, 56  
DcMotorSimple.Direction.REVERSE, 56  
DcMotor.ZeroPowerBehavior.BRAKE, 56  
DcMotor.ZeroPowerBehavior.FLOAT, 56  
DigitalChannel, 44  
Distance Sensor, 67  
DistanceSensor, 69  
DistanceUnit, 70  
double, 14  
Driver Station, 2  
Driving, 139

**E**  
EasyOpenCV, 109  
else, 23  
enum, 86  
Enumerated types, 84

{245}------------------------------------------------

## INDEX

extends, 95

### F

false, 14

Field oriented driving, 147

Field relative driving, 147

final, 134

float, 14

for, 26

for..each, 91

FTC SDK, 131

### G

Gamepad, 17

getMaxVoltage(), 65

getRuntime(), 86

Gotchas, 10

Gyro, 73

gyro, 147

### H

HardwareMap, 44

hasa, 96

holonomic drive, 142

### I

if, 21

implements, 136

IMU, 73, 75, 147

Inheritance, 95

init, 5

init(), 3

init\_loop(), 3

INPUT, 44

int, 14

Isa, 96

### J

Javadoc, 127

### L

LinearOpMode, 183

long, 14

loop(), 3

### M

Math, 18

math, 17

Math class, 133

mecanum, 142

Mechanisms, 43

method, 2

Motors, 49

### O

OpenCV, 109

OpMode, 3

OUTPUT, 44

### P

package, 2

Parameters, 31

Polar, 148

polymorphism, 97

potentiometer, 63

private, 34

Programming Board, 1, 181

protected, 34

public, 5, 34

### R

Range, 65

resetRunTime(), 86

Return Types, 31

Robot Controller, 1

rotation sensor, 53

Rumble, 105

RUN\_TO\_POSITION, 52

RUN\_USING\_ENCODER, 52

RUN\_WITHOUT\_ENCODER, 52

### S

scale(), 65

{246}------------------------------------------------

Scope, 16, 34  
Servo.Direction.FORWARD, 60  
Servo.Direction.REVERSE), 60  
Servos, 59  
servo.scaleRange(), 60  
servo.setDirection(), 60  
servo.setPosition(), 60  
setDirection(), 56  
setPower(), 52  
setZeroBehavior(), 56  
short, 14  
start(), 3  
State, 77  
static, 39  
stop(), 3  
STOP\_AND\_RESET\_ENCODER, 52  
String, 15  
super(), 98  
switch, 81

## T

Tank Drive, 139, 141  
telemetry, 5, 135  
telemetry.addLine, 104  
toString(), 33  
true, 14

## V

Variables, 13  
Vision, 109  
VisionPortal, 109  
void, 5

## W

while, 25