#!/usr/bin/env python3
"""
Convert datalab-output-LearnJavaForFTC.pdf.html to a cleaner version:
1. Remove all <div class="page"> wrappers
2. Remove all img alt attributes
3. Add proper anchor IDs for TOC navigation
4. Apply beautiful styling
"""

from bs4 import BeautifulSoup
import re
import sys

def create_toc_structure():
    """Generate the table of contents structure"""
    return """
        <nav>
            <h2 class="toc-title">Table of Contents</h2>
            <ul class="toc">
                <li class="toc-chapter"><a href="#intro">Introduction</a></li>
                
                <li class="toc-chapter"><a href="#ch1">1. Introduction</a></li>
                <li class="toc-section"><a href="#ch1-1">1.1. Hardware</a></li>
                <li class="toc-subsection"><a href="#ch1-1-1">1.1.1. Robot Controller</a></li>
                <li class="toc-subsection"><a href="#ch1-1-2">1.1.2. Programming Board</a></li>
                <li class="toc-subsection"><a href="#ch1-1-3">1.1.3. Driver Station</a></li>
                <li class="toc-section"><a href="#ch1-2">1.2. Our first OpMode</a></li>
                <li class="toc-subsection"><a href="#ch1-2-1">1.2.1. Some terminology</a></li>
                <li class="toc-subsection"><a href="#ch1-2-2">1.2.2. What is an OpMode?</a></li>
                <li class="toc-subsection"><a href="#ch1-2-3">1.2.3. Parts of an OpMode</a></li>
                <li class="toc-subsection"><a href="#ch1-2-4">1.2.4. Hello, World</a></li>
                <li class="toc-section"><a href="#ch1-3">1.3. Now you try</a></li>
                <li class="toc-section"><a href="#ch1-4">1.4. Comments</a></li>
                <li class="toc-section"><a href="#ch1-5">1.5. Sending to the Robot Controller</a></li>
                <li class="toc-section"><a href="#ch1-6">1.6. Gotchas</a></li>
                <li class="toc-section"><a href="#ch1-7">1.7. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch2">2. Variables and Data Types</a></li>
                <li class="toc-section"><a href="#ch2-1">2.1. Primitive Data Types</a></li>
                <li class="toc-section"><a href="#ch2-2">2.2. String</a></li>
                <li class="toc-section"><a href="#ch2-3">2.3. Scope</a></li>
                <li class="toc-section"><a href="#ch2-4">2.4. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch3">3. Gamepad and basic math</a></li>
                <li class="toc-section"><a href="#ch3-1">3.1. Basic Math</a></li>
                <li class="toc-section"><a href="#ch3-2">3.2. Other assignment operators</a></li>
                <li class="toc-section"><a href="#ch3-3">3.3. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch4">4. Making decisions</a></li>
                <li class="toc-section"><a href="#ch4-1">4.1. If</a></li>
                <li class="toc-section"><a href="#ch4-2">4.2. Else</a></li>
                <li class="toc-subsection"><a href="#ch4-2-1">4.2.1. Else if</a></li>
                <li class="toc-section"><a href="#ch4-3">4.3. Combinations</a></li>
                <li class="toc-section"><a href="#ch4-4">4.4. While</a></li>
                <li class="toc-section"><a href="#ch4-5">4.5. For</a></li>
                <li class="toc-section"><a href="#ch4-6">4.6. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch5">5. Class Members and Methods</a></li>
                <li class="toc-section"><a href="#ch5-1">5.1. Class Members</a></li>
                <li class="toc-section"><a href="#ch5-2">5.2. Class Methods</a></li>
                <li class="toc-subsection"><a href="#ch5-2-1">5.2.1. Return Types</a></li>
                <li class="toc-subsection"><a href="#ch5-2-2">5.2.2. Parameters</a></li>
                <li class="toc-subsection"><a href="#ch5-2-3">5.2.3. Special Methods: Constructors</a></li>
                <li class="toc-subsection"><a href="#ch5-2-4">5.2.4. Another special method: toString</a></li>
                <li class="toc-section"><a href="#ch5-3">5.3. Controlling access</a></li>
                <li class="toc-section"><a href="#ch5-4">5.4. Creating your own classes</a></li>
                <li class="toc-section"><a href="#ch5-5">5.5. static</a></li>
                <li class="toc-section"><a href="#ch5-6">5.6. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch6">6. Our first hardware</a></li>
                <li class="toc-section"><a href="#ch6-1">6.1. Configuration file</a></li>
                <li class="toc-section"><a href="#ch6-2">6.2. Mechanisms</a></li>
                <li class="toc-section"><a href="#ch6-3">6.3. OpMode</a></li>
                <li class="toc-section"><a href="#ch6-4">6.4. Making changes</a></li>
                <li class="toc-section"><a href="#ch6-5">6.5. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch7">7. Motors</a></li>
                <li class="toc-section"><a href="#ch7-1">7.1. Editing Configuration File</a></li>
                <li class="toc-section"><a href="#ch7-2">7.2. Mechanisms</a></li>
                <li class="toc-section"><a href="#ch7-3">7.3. OpMode</a></li>
                <li class="toc-section"><a href="#ch7-4">7.4. Motor as Sensor</a></li>
                <li class="toc-section"><a href="#ch7-5">7.5. Motors and Sensors together</a></li>
                <li class="toc-section"><a href="#ch7-6">7.6. Motors and Gamepads</a></li>
                <li class="toc-section"><a href="#ch7-7">7.7. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch8">8. Servos</a></li>
                <li class="toc-section"><a href="#ch8-1">8.1. Configuration File</a></li>
                <li class="toc-section"><a href="#ch8-2">8.2. Mechanisms</a></li>
                <li class="toc-section"><a href="#ch8-3">8.3. OpMode</a></li>
                <li class="toc-section"><a href="#ch8-4">8.4. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch9">9. Analog Sensors</a></li>
                <li class="toc-section"><a href="#ch9-1">9.1. Configuration File</a></li>
                <li class="toc-section"><a href="#ch9-2">9.2. Mechanisms</a></li>
                <li class="toc-section"><a href="#ch9-3">9.3. OpMode</a></li>
                <li class="toc-section"><a href="#ch9-4">9.4. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch10">10. Color and Distance Sensors</a></li>
                <li class="toc-section"><a href="#ch10-1">10.1. Configuration File</a></li>
                <li class="toc-section"><a href="#ch10-2">10.2. Mechanisms</a></li>
                <li class="toc-section"><a href="#ch10-3">10.3. OpMode</a></li>
                <li class="toc-section"><a href="#ch10-4">10.4. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch11">11. Gyro (IMU)</a></li>
                <li class="toc-section"><a href="#ch11-1">11.1. Configuration File</a></li>
                <li class="toc-section"><a href="#ch11-2">11.2. Mechanisms</a></li>
                <li class="toc-section"><a href="#ch11-3">11.3. OpMode</a></li>
                <li class="toc-section"><a href="#ch11-4">11.4. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch12">12. Dealing with State</a></li>
                <li class="toc-section"><a href="#ch12-1">12.1. Example of a toggle</a></li>
                <li class="toc-section"><a href="#ch12-2">12.2. Autonomous state - Example</a></li>
                <li class="toc-subsection"><a href="#ch12-2-1">12.2.1. Using the switch statement</a></li>
                <li class="toc-subsection"><a href="#ch12-2-2">12.2.2. Switch with strings</a></li>
                <li class="toc-subsection"><a href="#ch12-2-3">12.2.3. Enumerated types</a></li>
                <li class="toc-section"><a href="#ch12-3">12.3. It's all relative</a></li>
                <li class="toc-section"><a href="#ch12-4">12.4. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch13">13. Arrays</a></li>
                <li class="toc-section"><a href="#ch13-1">13.1. ArrayList</a></li>
                <li class="toc-subsection"><a href="#ch13-1-1">13.1.1. Making your own generic class</a></li>
                <li class="toc-section"><a href="#ch13-2">13.2. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch14">14. Inheritance</a></li>
                <li class="toc-section"><a href="#ch14-1">14.1. Isa vs. hasa</a></li>
                <li class="toc-section"><a href="#ch14-2">14.2. So why in the world would you use this?</a></li>
                <li class="toc-section"><a href="#ch14-3">14.3. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch15">15. Rumble with Gamepad</a></li>
                <li class="toc-section"><a href="#ch15-1">15.1. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch16">16. Computer Vision</a></li>
                <li class="toc-section"><a href="#ch16-1">16.1. Using AprilTags</a></li>
                <li class="toc-section"><a href="#ch16-2">16.2. Vision Processors</a></li>
                <li class="toc-section"><a href="#ch16-3">16.3. EOCVSim</a></li>
                <li class="toc-section"><a href="#ch16-4">16.4. Expanding to 3 rectangles</a></li>
                <li class="toc-section"><a href="#ch16-5">16.5. Actual computer vision</a></li>
                <li class="toc-subsection"><a href="#ch16-5-1">16.5.1. The opmode</a></li>
                <li class="toc-section"><a href="#ch16-6">16.6. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch17">17. Javadoc</a></li>
                <li class="toc-section"><a href="#ch17-1">17.1. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch18">18. Finding things in FTC SDK</a></li>
                <li class="toc-section"><a href="#ch18-1">18.1. Exercise</a></li>
                
                <li class="toc-chapter"><a href="#ch19">19. A few other topics</a></li>
                <li class="toc-section"><a href="#ch19-1">19.1. Math class</a></li>
                <li class="toc-section"><a href="#ch19-2">19.2. final</a></li>
                <li class="toc-section"><a href="#ch19-3">19.3. Make telemetry prettier</a></li>
                <li class="toc-section"><a href="#ch19-4">19.4. Interfaces (implements)</a></li>
                <li class="toc-subsection"><a href="#ch19-4-1">19.4.1. When to use an interface instead of an abstract class?</a></li>
                <li class="toc-section"><a href="#ch19-5">19.5. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch20">20. Making Robots Drive</a></li>
                <li class="toc-section"><a href="#ch20-1">20.1. 2 motor drive</a></li>
                <li class="toc-subsection"><a href="#ch20-1-1">20.1.1. Two Motor Drive Mechanism</a></li>
                <li class="toc-subsection"><a href="#ch20-1-2">20.1.2. OpMode</a></li>
                <li class="toc-section"><a href="#ch20-2">20.2. 4 motor mecanum drive</a></li>
                <li class="toc-subsection"><a href="#ch20-2-1">20.2.1. Mecanum Mechanism</a></li>
                <li class="toc-subsection"><a href="#ch20-2-2">20.2.2. Robot oriented driving</a></li>
                <li class="toc-subsection"><a href="#ch20-2-3">20.2.3. Field oriented driving</a></li>
                <li class="toc-section"><a href="#ch20-3">20.3. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch21">21. Some hardware to help with Odometry</a></li>
                <li class="toc-section"><a href="#ch21-1">21.1. OctoQuad</a></li>
                <li class="toc-subsection"><a href="#ch21-1-1">21.1.1. What is it?</a></li>
                <li class="toc-subsection"><a href="#ch21-1-2">21.1.2. Using it simply</a></li>
                <li class="toc-subsection"><a href="#ch21-1-3">21.1.3. Using it to get multiple encoders</a></li>
                <li class="toc-subsection"><a href="#ch21-1-4">21.1.4. Using the cached attribute</a></li>
                <li class="toc-subsection"><a href="#ch21-1-5">21.1.5. Other features</a></li>
                <li class="toc-section"><a href="#ch21-2">21.2. Sparkfun Optical Tracking Odometry Sensor</a></li>
                <li class="toc-subsection"><a href="#ch21-2-1">21.2.1. What is it?</a></li>
                <li class="toc-subsection"><a href="#ch21-2-2">21.2.2. Using it</a></li>
                <li class="toc-section"><a href="#ch21-3">21.3. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch22">22. LEDs - Adding some bling feedback</a></li>
                <li class="toc-section"><a href="#ch22-1">22.1. REV Digital LED Indicator</a></li>
                <li class="toc-section"><a href="#ch22-2">22.2. Sparkfun QWIIC LED Stick</a></li>
                <li class="toc-section"><a href="#ch22-3">22.3. REV Blinkin</a></li>
                <li class="toc-section"><a href="#ch22-4">22.4. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#ch23">23. Limelight 3A</a></li>
                <li class="toc-section"><a href="#ch23-1">23.1. Simple color example</a></li>
                <li class="toc-subsection"><a href="#ch23-1-1">23.1.1. On the Limelight</a></li>
                <li class="toc-subsection"><a href="#ch23-1-2">23.1.2. Your Java Code</a></li>
                <li class="toc-subsection"><a href="#ch23-1-3">23.1.3. Changing Limelight Pipeline</a></li>
                <li class="toc-subsection"><a href="#ch23-1-4">23.1.4. Swapping between pipelines</a></li>
                <li class="toc-section"><a href="#ch23-2">23.2. Localization with AprilTags</a></li>
                <li class="toc-subsection"><a href="#ch23-2-1">23.2.1. On the Limelight</a></li>
                <li class="toc-subsection"><a href="#ch23-2-2">23.2.2. Your Java Code</a></li>
                <li class="toc-section"><a href="#ch23-3">23.3. Exercises</a></li>
                
                <li class="toc-chapter"><a href="#appendix-a">Appendix A. Making your own Programming Board</a></li>
                <li class="toc-chapter"><a href="#appendix-b">Appendix B. LinearOpMode</a></li>
                <li class="toc-section"><a href="#appendix-b-1">B.1. What is it?</a></li>
                <li class="toc-section"><a href="#appendix-b-2">B.2. Should you use it?</a></li>
                <li class="toc-chapter"><a href="#appendix-c">Appendix C. Sample Solutions</a></li>
                <li class="toc-chapter"><a href="#appendix-d">Appendix D. Credits</a></li>
            </ul>
        </nav>
"""

def add_anchor_ids(soup):
    """Add anchor IDs to headings based on content patterns"""
    
    # Mapping patterns to IDs - for chapter headings
    chapter_patterns = {
        r'^\s*1\.\s+Introduction': 'ch1',
        r'^\s*2\.\s+Variables and Data Types': 'ch2',
        r'^\s*3\.\s+Gamepad and basic math': 'ch3',
        r'^\s*4\.\s+Making decisions': 'ch4',
        r'^\s*5\.\s+Class Members and Methods': 'ch5',
        r'^\s*6\.\s+Our first hardware': 'ch6',
        r'^\s*7\.\s+Motors': 'ch7',
        r'^\s*8\.\s+Servos': 'ch8',
        r'^\s*9\.\s+Analog Sensors': 'ch9',
        r'^\s*10\.\s+Color and Distance Sensors': 'ch10',
        r'^\s*11\.\s+Gyro \(IMU\)': 'ch11',
        r'^\s*12\.\s+Dealing with State': 'ch12',
        r'^\s*13\.\s+Arrays': 'ch13',
        r'^\s*14\.\s+Inheritance': 'ch14',
        r'^\s*15\.\s+Rumble with Gamepad': 'ch15',
        r'^\s*16\.\s+Computer Vision': 'ch16',
        r'^\s*17\.\s+Javadoc': 'ch17',
        r'^\s*18\.\s+Finding things in FTC SDK': 'ch18',
        r'^\s*19\.\s+A few other topics': 'ch19',
        r'^\s*20\.\s+Making Robots Drive': 'ch20',
        r'^\s*21\.\s+Some hardware to help with Odometry': 'ch21',
        r'^\s*22\.\s+LEDs': 'ch22',
        r'^\s*23\.\s+Limelight 3A': 'ch23',
        r'^A\.\s+Making your own Programming Board': 'appendix-a',
        r'^B\.\s+LinearOpMode': 'appendix-b',
        r'^C\.\s+Sample Solutions': 'appendix-c',
        r'^D\.\s+Credits': 'appendix-d',
    }
    
    # Section patterns (like 1.1, 1.2, etc.)
    section_patterns = {
        r'^\s*1\.1\.\s+Hardware': 'ch1-1',
        r'^\s*1\.2\.\s+Our first OpMode': 'ch1-2',
        r'^\s*1\.3\.\s+Now you try': 'ch1-3',
        r'^\s*1\.4\.\s+Comments': 'ch1-4',
        r'^\s*1\.5\.\s+Sending to the Robot Controller': 'ch1-5',
        r'^\s*1\.6\.\s+Gotchas': 'ch1-6',
        r'^\s*1\.7\.\s+Exercises': 'ch1-7',
        r'^\s*2\.1\.\s+Primitive Data Types': 'ch2-1',
        r'^\s*2\.2\.\s+String': 'ch2-2',
        r'^\s*2\.3\.\s+Scope': 'ch2-3',
        r'^\s*2\.4\.\s+Exercises': 'ch2-4',
        r'^\s*3\.1\.\s+Basic Math': 'ch3-1',
        r'^\s*3\.2\.\s+Other assignment operators': 'ch3-2',
        r'^\s*3\.3\.\s+Exercises': 'ch3-3',
        r'^\s*4\.1\.\s+If': 'ch4-1',
        r'^\s*4\.2\.\s+Else': 'ch4-2',
        r'^\s*4\.3\.\s+Combinations': 'ch4-3',
        r'^\s*4\.4\.\s+While': 'ch4-4',
        r'^\s*4\.5\.\s+For': 'ch4-5',
        r'^\s*4\.6\.\s+Exercises': 'ch4-6',
        r'^\s*5\.1\.\s+Class Members': 'ch5-1',
        r'^\s*5\.2\.\s+Class Methods': 'ch5-2',
        r'^\s*5\.3\.\s+Controlling access': 'ch5-3',
        r'^\s*5\.4\.\s+Creating your own classes': 'ch5-4',
        r'^\s*5\.5\.\s+static': 'ch5-5',
        r'^\s*5\.6\.\s+Exercises': 'ch5-6',
        r'^\s*6\.1\.\s+Configuration file': 'ch6-1',
        r'^\s*6\.2\.\s+Mechanisms': 'ch6-2',
        r'^\s*6\.3\.\s+OpMode': 'ch6-3',
        r'^\s*6\.4\.\s+Making changes': 'ch6-4',
        r'^\s*6\.5\.\s+Exercises': 'ch6-5',
        r'^\s*7\.1\.\s+Editing Configuration File': 'ch7-1',
        r'^\s*7\.2\.\s+Mechanisms': 'ch7-2',
        r'^\s*7\.3\.\s+OpMode': 'ch7-3',
        r'^\s*7\.4\.\s+Motor as Sensor': 'ch7-4',
        r'^\s*7\.5\.\s+Motors and Sensors together': 'ch7-5',
        r'^\s*7\.6\.\s+Motors and Gamepads': 'ch7-6',
        r'^\s*7\.7\.\s+Exercises': 'ch7-7',
        r'^\s*8\.1\.\s+Configuration File': 'ch8-1',
        r'^\s*8\.2\.\s+Mechanisms': 'ch8-2',
        r'^\s*8\.3\.\s+OpMode': 'ch8-3',
        r'^\s*8\.4\.\s+Exercises': 'ch8-4',
        r'^\s*9\.1\.\s+Configuration File': 'ch9-1',
        r'^\s*9\.2\.\s+Mechanisms': 'ch9-2',
        r'^\s*9\.3\.\s+OpMode': 'ch9-3',
        r'^\s*9\.4\.\s+Exercises': 'ch9-4',
        r'^\s*10\.1\.\s+Configuration File': 'ch10-1',
        r'^\s*10\.2\.\s+Mechanisms': 'ch10-2',
        r'^\s*10\.3\.\s+OpMode': 'ch10-3',
        r'^\s*10\.4\.\s+Exercises': 'ch10-4',
        r'^\s*11\.1\.\s+Configuration File': 'ch11-1',
        r'^\s*11\.2\.\s+Mechanisms': 'ch11-2',
        r'^\s*11\.3\.\s+OpMode': 'ch11-3',
        r'^\s*11\.4\.\s+Exercises': 'ch11-4',
        r'^\s*12\.1\.\s+Example of a toggle': 'ch12-1',
        r'^\s*12\.2\.\s+Autonomous state': 'ch12-2',
        r'^\s*12\.3\.\s+It\'s all relative': 'ch12-3',
        r'^\s*12\.4\.\s+Exercises': 'ch12-4',
        r'^\s*13\.1\.\s+ArrayList': 'ch13-1',
        r'^\s*13\.2\.\s+Exercises': 'ch13-2',
        r'^\s*14\.1\.\s+Isa vs\. hasa': 'ch14-1',
        r'^\s*14\.2\.\s+So why in the world': 'ch14-2',
        r'^\s*14\.3\.\s+Exercises': 'ch14-3',
        r'^\s*15\.1\.\s+Exercises': 'ch15-1',
        r'^\s*16\.1\.\s+Using AprilTags': 'ch16-1',
        r'^\s*16\.2\.\s+Vision Processors': 'ch16-2',
        r'^\s*16\.3\.\s+EOCVSim': 'ch16-3',
        r'^\s*16\.4\.\s+Expanding to 3 rectangles': 'ch16-4',
        r'^\s*16\.5\.\s+Actual computer vision': 'ch16-5',
        r'^\s*16\.6\.\s+Exercises': 'ch16-6',
        r'^\s*17\.1\.\s+Exercises': 'ch17-1',
        r'^\s*18\.1\.\s+Exercise': 'ch18-1',
        r'^\s*19\.1\.\s+Math class': 'ch19-1',
        r'^\s*19\.2\.\s+final': 'ch19-2',
        r'^\s*19\.3\.\s+Make telemetry prettier': 'ch19-3',
        r'^\s*19\.4\.\s+Interfaces': 'ch19-4',
        r'^\s*19\.5\.\s+Exercises': 'ch19-5',
        r'^\s*20\.1\.\s+2 motor drive': 'ch20-1',
        r'^\s*20\.2\.\s+4 motor mecanum drive': 'ch20-2',
        r'^\s*20\.3\.\s+Exercises': 'ch20-3',
        r'^\s*21\.1\.\s+OctoQuad': 'ch21-1',
        r'^\s*21\.2\.\s+Sparkfun': 'ch21-2',
        r'^\s*21\.3\.\s+Exercises': 'ch21-3',
        r'^\s*22\.1\.\s+REV Digital LED': 'ch22-1',
        r'^\s*22\.2\.\s+Sparkfun QWIIC LED': 'ch22-2',
        r'^\s*22\.3\.\s+REV Blinkin': 'ch22-3',
        r'^\s*22\.4\.\s+Exercises': 'ch22-4',
        r'^\s*23\.1\.\s+Simple color example': 'ch23-1',
        r'^\s*23\.2\.\s+Localization with AprilTags': 'ch23-2',
        r'^\s*23\.3\.\s+Exercises': 'ch23-3',
    }
    
    # Subsection patterns (like 1.1.1, 5.2.1, etc.)
    subsection_patterns = {
        r'^\s*1\.1\.1\.\s+Robot Controller': 'ch1-1-1',
        r'^\s*1\.1\.2\.\s+Programming Board': 'ch1-1-2',
        r'^\s*1\.1\.3\.\s+Driver Station': 'ch1-1-3',
        r'^\s*1\.2\.1\.\s+Some terminology': 'ch1-2-1',
        r'^\s*1\.2\.2\.\s+What is an OpMode': 'ch1-2-2',
        r'^\s*1\.2\.3\.\s+Parts of an OpMode': 'ch1-2-3',
        r'^\s*1\.2\.4\.\s+Hello, World': 'ch1-2-4',
        r'^\s*4\.2\.1\.\s+Else if': 'ch4-2-1',
        r'^\s*5\.2\.1\.\s+Return Types': 'ch5-2-1',
        r'^\s*5\.2\.2\.\s+Parameters': 'ch5-2-2',
        r'^\s*5\.2\.3\.\s+Special Methods: Constructors': 'ch5-2-3',
        r'^\s*5\.2\.4\.\s+Another special method': 'ch5-2-4',
        r'^\s*12\.2\.1\.\s+Using the switch statement': 'ch12-2-1',
        r'^\s*12\.2\.2\.\s+Switch with strings': 'ch12-2-2',
        r'^\s*12\.2\.3\.\s+Enumerated types': 'ch12-2-3',
        r'^\s*13\.1\.1\.\s+Making your own generic class': 'ch13-1-1',
        r'^\s*16\.5\.1\.\s+The opmode': 'ch16-5-1',
        r'^\s*19\.4\.1\.\s+When to use an interface': 'ch19-4-1',
        r'^\s*20\.1\.1\.\s+Two Motor Drive Mechanism': 'ch20-1-1',
        r'^\s*20\.1\.2\.\s+OpMode': 'ch20-1-2',
        r'^\s*20\.2\.1\.\s+Mecanum Mechanism': 'ch20-2-1',
        r'^\s*20\.2\.2\.\s+Robot oriented driving': 'ch20-2-2',
        r'^\s*20\.2\.3\.\s+Field oriented driving': 'ch20-2-3',
        r'^\s*21\.1\.1\.\s+What is it': 'ch21-1-1',
        r'^\s*21\.1\.2\.\s+Using it simply': 'ch21-1-2',
        r'^\s*21\.1\.3\.\s+Using it to get multiple': 'ch21-1-3',
        r'^\s*21\.1\.4\.\s+Using the cached attribute': 'ch21-1-4',
        r'^\s*21\.1\.5\.\s+Other features': 'ch21-1-5',
        r'^\s*21\.2\.1\.\s+What is it': 'ch21-2-1',
        r'^\s*21\.2\.2\.\s+Using it': 'ch21-2-2',
        r'^\s*23\.1\.1\.\s+On the Limelight': 'ch23-1-1',
        r'^\s*23\.1\.2\.\s+Your Java Code': 'ch23-1-2',
        r'^\s*23\.1\.3\.\s+Changing Limelight Pipeline': 'ch23-1-3',
        r'^\s*23\.1\.4\.\s+Swapping between pipelines': 'ch23-1-4',
        r'^\s*23\.2\.1\.\s+On the Limelight': 'ch23-2-1',
        r'^\s*23\.2\.2\.\s+Your Java Code': 'ch23-2-2',
    }
    
    # Appendix sections
    appendix_patterns = {
        r'B\.1\.\s+What is it': 'appendix-b-1',
        r'B\.2\.\s+Should you use it': 'appendix-b-2',
    }
    
    # Track which IDs have been used to avoid duplicates
    used_ids = set()
    
    # Find all headings and add IDs
    for heading in soup.find_all(['h1', 'h2', 'h3', 'h4']):
        text = heading.get_text().strip()
        
        # Skip if already has an ID
        if heading.get('id'):
            used_ids.add(heading.get('id'))
            continue
        
        # Try to match against our patterns
        matched_id = None
        
        # Check subsection patterns first (most specific)
        for pattern, anchor_id in subsection_patterns.items():
            if re.search(pattern, text, re.IGNORECASE):
                matched_id = anchor_id
                break
        
        if not matched_id:
            # Check section patterns
            for pattern, anchor_id in section_patterns.items():
                if re.search(pattern, text, re.IGNORECASE):
                    matched_id = anchor_id
                    break
        
        if not matched_id:
            # Check chapter patterns
            for pattern, anchor_id in chapter_patterns.items():
                if re.search(pattern, text, re.IGNORECASE):
                    matched_id = anchor_id
                    break
        
        if not matched_id:
            # Check appendix patterns
            for pattern, anchor_id in appendix_patterns.items():
                if re.search(pattern, text, re.IGNORECASE):
                    matched_id = anchor_id
                    break
        
        # Only assign if this ID hasn't been used yet
        if matched_id and matched_id not in used_ids:
            heading['id'] = matched_id
            used_ids.add(matched_id)
    
    # Add "intro" ID to the first h1 (title page)
    first_h1 = soup.find('h1')
    if first_h1 and not first_h1.get('id'):
        first_h1['id'] = 'intro'

def get_css():
    """Return the beautiful CSS styling"""
    return """
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #3498db;
            --accent-color: #e74c3c;
            --bg-color: #ffffff;
            --text-color: #333333;
            --code-bg: #f4f4f4;
            --border-color: #dddddd;
            --nav-bg: #f8f9fa;
            --hover-bg: #ecf0f1;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
            line-height: 1.6;
            color: var(--text-color);
            background: var(--bg-color);
        }

        .container {
            display: grid;
            grid-template-columns: 280px 1fr;
            min-height: 100vh;
            max-width: 1600px;
            margin: 0 auto;
        }

        @media (max-width: 968px) {
            .container {
                grid-template-columns: 1fr;
            }
            nav {
                position: static !important;
                height: auto !important;
                width: 100% !important;
            }
        }

        /* Navigation Sidebar */
        nav {
            position: sticky;
            top: 0;
            height: 100vh;
            overflow-y: auto;
            background: var(--nav-bg);
            border-right: 1px solid var(--border-color);
            padding: 2rem 1.5rem;
            width: 280px;
            flex-shrink: 0;
        }

        nav::-webkit-scrollbar {
            width: 8px;
        }

        nav::-webkit-scrollbar-track {
            background: transparent;
        }

        nav::-webkit-scrollbar-thumb {
            background: var(--border-color);
            border-radius: 4px;
        }

        nav::-webkit-scrollbar-thumb:hover {
            background: var(--secondary-color);
        }

        .toc-title {
            font-size: 1.5rem;
            font-weight: 700;
            color: var(--primary-color);
            margin-bottom: 1.5rem;
            padding-bottom: 0.75rem;
            border-bottom: 2px solid var(--secondary-color);
        }

        .toc {
            list-style: none;
        }

        .toc li {
            margin: 0.25rem 0;
        }

        .toc a {
            text-decoration: none;
            color: var(--text-color);
            display: block;
            padding: 0.4rem 0.75rem;
            border-radius: 4px;
            transition: all 0.2s;
        }

        .toc a:hover {
            background: var(--hover-bg);
            color: var(--secondary-color);
            transform: translateX(4px);
        }

        .toc-chapter {
            font-weight: 600;
            margin-top: 1rem;
        }

        .toc-section {
            font-size: 0.95rem;
            padding-left: 0.75rem;
        }

        .toc-subsection {
            font-size: 0.9rem;
            padding-left: 1.5rem;
            color: #666;
        }

        /* Main Content */
        main {
            padding: 3rem;
            max-width: 1200px;
        }

        header {
            text-align: center;
            margin-bottom: 3rem;
            padding-bottom: 2rem;
            border-bottom: 3px solid var(--primary-color);
        }

        header h1 {
            font-size: 3rem;
            color: var(--primary-color);
            margin-bottom: 0.5rem;
        }

        header .author {
            font-size: 1.5rem;
            color: var(--secondary-color);
            margin-bottom: 0.25rem;
        }

        header .date {
            color: #666;
        }

        .dedication {
            font-style: italic;
            background: var(--code-bg);
            padding: 2rem;
            border-left: 4px solid var(--secondary-color);
            margin: 2rem 0;
        }

        h1, h2, h3, h4, h5, h6 {
            color: var(--primary-color);
            margin-top: 2rem;
            margin-bottom: 1rem;
            line-height: 1.3;
        }

        h1 {
            font-size: 2.5rem;
            border-bottom: 3px solid var(--secondary-color);
            padding-bottom: 0.5rem;
        }

        h2 {
            font-size: 2rem;
            border-bottom: 2px solid var(--border-color);
            padding-bottom: 0.5rem;
        }

        h3 {
            font-size: 1.5rem;
            color: var(--secondary-color);
        }

        h4 {
            font-size: 1.25rem;
        }

        p {
            margin: 1rem 0;
            text-align: justify;
        }

        a {
            color: var(--secondary-color);
            text-decoration: none;
            transition: color 0.2s;
        }

        a:hover {
            color: var(--accent-color);
            text-decoration: underline;
        }

        code {
            font-family: 'Monaco', 'Menlo', 'Ubuntu Mono', 'Consolas', monospace;
            background: var(--code-bg);
            padding: 0.2rem 0.4rem;
            border-radius: 3px;
            font-size: 0.9em;
            color: var(--accent-color);
        }

        pre {
            background: var(--code-bg);
            border: 1px solid var(--border-color);
            border-left: 4px solid var(--secondary-color);
            padding: 1.5rem;
            overflow-x: auto;
            border-radius: 4px;
            margin: 1.5rem 0;
        }

        pre code {
            background: none;
            padding: 0;
            color: var(--text-color);
        }

        img {
            max-width: 100%;
            height: auto;
            display: block;
            margin: 2rem auto;
            border-radius: 4px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 1.5rem 0;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        table thead {
            background: var(--primary-color);
            color: white;
        }

        table th,
        table td {
            padding: 0.75rem;
            text-align: left;
            border: 1px solid var(--border-color);
        }

        table tbody tr:nth-child(even) {
            background: var(--code-bg);
        }

        table tbody tr:hover {
            background: var(--hover-bg);
        }

        ul, ol {
            margin: 1rem 0 1rem 2rem;
        }

        li {
            margin: 0.5rem 0;
        }

        blockquote {
            border-left: 4px solid var(--secondary-color);
            padding-left: 1rem;
            margin: 1.5rem 0;
            color: #666;
            font-style: italic;
        }

        .footnote {
            font-size: 0.9rem;
            color: #666;
            margin-top: 2rem;
            padding-top: 1rem;
            border-top: 1px solid var(--border-color);
        }

        sup {
            font-size: 0.75em;
            vertical-align: super;
        }

        .info-box {
            background: #e8f4f8;
            border-left: 4px solid var(--secondary-color);
            padding: 1rem;
            margin: 1.5rem 0;
        }

        .warning-box {
            background: #fef5e7;
            border-left: 4px solid #f39c12;
            padding: 1rem;
            margin: 1.5rem 0;
        }

        .note-box {
            background: #f0f0f0;
            border-left: 4px solid #95a5a6;
            padding: 1rem;
            margin: 1.5rem 0;
        }

        .section-anchor {
            display: block;
            position: relative;
            top: -80px;
            visibility: hidden;
        }

        section {
            margin-bottom: 4rem;
        }

        footer {
            text-align: center;
            padding: 2rem;
            margin-top: 4rem;
            border-top: 2px solid var(--border-color);
            color: #666;
        }

        .back-to-top {
            position: fixed;
            bottom: 2rem;
            right: 2rem;
            background: var(--secondary-color);
            color: white;
            width: 50px;
            height: 50px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            text-decoration: none;
            font-size: 1.5rem;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            opacity: 0;
            transition: opacity 0.3s, background 0.3s;
        }

        .back-to-top.visible {
            opacity: 1;
        }

        .back-to-top:hover {
            background: var(--accent-color);
        }
"""

def process_html(input_file, output_file):
    """Process the HTML file"""
    print(f"Reading {input_file}...")
    
    with open(input_file, 'r', encoding='utf-8') as f:
        soup = BeautifulSoup(f.read(), 'html.parser')
    
    print("Processing content...")
    
    # Remove all <div class="page"> wrappers while keeping content
    for page_div in soup.find_all('div', class_='page'):
        page_div.unwrap()
    
    # Remove all alt attributes from images
    for img in soup.find_all('img'):
        if img.has_attr('alt'):
            del img['alt']
    
    # Add anchor IDs to headings for TOC navigation
    add_anchor_ids(soup)
    
    # Get all content from body
    body = soup.find('body')
    if not body:
        print("ERROR: No body tag found")
        return
    
    content_html = ''.join(str(tag) for tag in body.children)
    
    # Build the new HTML
    print("Building new HTML...")
    
    html_output = f"""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Learn Java for FTC</title>
    <style>
{get_css()}
    </style>
</head>
<body>
    <div class="container">
{create_toc_structure()}
        
        <main>
{content_html}
            
            <footer>
                <p>&copy; 2020-2024 Alan G. Smith. All Rights Reserved.</p>
                <p>Source: <a href="https://github.com/alan412/LearnJavaForFTC">github.com/alan412/LearnJavaForFTC</a></p>
            </footer>
        </main>
    </div>
    
    <a href="#intro" class="back-to-top" id="backToTop">↑</a>
    
    <script>
        // Back to top button
        const backToTop = document.getElementById('backToTop');
        
        window.addEventListener('scroll', () => {{
            if (window.pageYOffset > 300) {{
                backToTop.classList.add('visible');
            }} else {{
                backToTop.classList.remove('visible');
            }}
        }});
        
        // Smooth scrolling for anchor links - only for links in nav and back-to-top
        document.querySelectorAll('nav a[href^="#"], .back-to-top[href^="#"]').forEach(anchor => {{
            anchor.addEventListener('click', function (e) {{
                e.preventDefault();
                const targetId = this.getAttribute('href').substring(1);
                const target = document.getElementById(targetId);
                if (target) {{
                    // Calculate offset to account for sticky positioning
                    const offset = 20;
                    const targetPosition = target.getBoundingClientRect().top + window.pageYOffset - offset;
                    
                    window.scrollTo({{
                        top: targetPosition,
                        behavior: 'smooth'
                    }});
                    
                    // Update URL without triggering scroll
                    if (history.pushState) {{
                        history.pushState(null, null, '#' + targetId);
                    }}
                }}
            }});
        }});
    </script>
</body>
</html>"""
    
    print(f"Writing to {output_file}...")
    with open(output_file, 'w', encoding='utf-8') as f:
        f.write(html_output)
    
    print(f"✓ Successfully created {output_file}")
    print(f"  - Removed all page divs")
    print(f"  - Removed all image alt attributes")
    print(f"  - Added beautiful styling")
    print(f"  - Added table of contents with navigation")

if __name__ == '__main__':
    input_file = 'datalab-output-LearnJavaForFTC.pdf.html'
    output_file = 'LearnJavaForFTC.html'
    
    process_html(input_file, output_file)
