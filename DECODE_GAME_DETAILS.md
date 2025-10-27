# FTC DECODE Game Programming Reference Guide

## Game Overview
- **Match Duration**: 30-second AUTO period + 8-second transition + 2-minute TELEOP period
- **Alliance Structure**: 2 teams per alliance (Red vs Blue)
- **Objective**: Score ARTIFACTS in GOAL, build PATTERNS on RAMP, return to BASE

---

## Field Specifications

### Field Dimensions
- **Total Size**: 144" × 144" (365.75 cm × 365.75 cm)
- **Surface**: 36 interlocking foam tiles (24" × 24" × 0.59" each)
- **Coordinate System**: 6×6 tile grid (Columns A-F, Rows 1-6)
    - Blue alliance: Columns A, B, C
    - Red alliance: Columns D, E, F

### Key Field Elements

#### GOAL
- **Location**: Corner of each alliance's side
- **Opening**: 26.5" wide × 18.3" deep
- **Top Lip Height**: 38.75" from tile surface
- **Entry Method**: ARTIFACTS must enter through open top

#### OBELISK
- **Location**: Outside field perimeter on GOAL side (center)
- **Purpose**: Displays randomized MOTIF for the match
- **AprilTags**: 3 faces with IDs 21, 22, 23 (one per MOTIF)
- **Height**: 23" tall
- **IMPORTANT**: Location is NOT deterministic - do not use for navigation

#### CLASSIFIER (attached to GOAL)
- **SQUARE**: Top entry point where scoring is assessed
- **RAMP**: Holds up to 9 CLASSIFIED ARTIFACTS
- **GATE**: Push-to-open mechanism
    - Opens when ROBOT pushes down
    - May not stay open automatically
    - Releases CLASSIFIED ARTIFACTS into opponent's SECRET TUNNEL

---

## Scoring Elements

### ARTIFACTS
- **Type**: 5" diameter polypropylene balls (Gopher ResisDent™)
- **Colors**:
    - 24 Purple (P)
    - 12 Green (G)
- **Alliance Neutral**: Either alliance can score any color

### Pre-Match ARTIFACT Placement
**Starting on Field:**
1. **SPIKE MARKS** (6 marks, 3 ARTIFACTS each = 18 total):
    - Near (audience side): G-P-P
    - Middle: P-G-P
    - Far (GOAL side): P-P-G

2. **LOADING ZONES** (2 zones, 3 ARTIFACTS each = 6 total):
    - Each zone: P-G-P (biased to corner near ALLIANCE AREA)

3. **ALLIANCE AREAS** (2 areas, 6 ARTIFACTS each = 12 total):
    - 4 Purple, 2 Green per alliance (no set order)

**Pre-loading**: Each ROBOT may start with up to 3 ARTIFACTS from their ALLIANCE AREA

---

## Zones and Areas

### LAUNCH ZONES (2 per alliance)
- **Small Triangle**: 2 tiles wide × 1 tile deep (audience side)
- **Large Triangle**: 6 tiles wide × 3 tiles deep (GOAL side)
- **Rule**: ROBOTS may only LAUNCH when inside or overlapping LAUNCH LINE

### LAUNCH LINE
- White tape boundary defining LAUNCH ZONES
- Includes DEPOT tape at GOAL base

### BASE ZONE
- **Size**: 18" × 18" square
- **Location**: Alliance-specific corner
- **Marked**: Alliance-colored tape

### LOADING ZONE
- **Size**: ~23" × 23" square
- **Location**: Alliance-specific, bounded by white tape and field perimeter
- **Purpose**: Human players retrieve/place ARTIFACTS here during TELEOP

### SECRET TUNNEL ZONE
- **Size**: ~46.5" long × ~6.125" wide
- **Location**: Between GOAL and LOADING ZONE
- **Marked**: Alliance-colored tape
- **Purpose**: Catches ARTIFACTS released from opponent's GATE

### GATE ZONE
- **Size**: 2.75" wide × 10" long
- **Location**: Adjacent to each GATE
- **Marked**: Alliance-colored tape parallel strips

### DEPOT
- **Size**: ~30" long white tape
- **Location**: Base of GOAL front face
- **Scoring**: ARTIFACTS over DEPOT score points (assessed at match end)

---

## AprilTags

### GOAL Tags (for targeting/navigation)
- **Blue GOAL**: Tag ID 20
- **Red GOAL**: Tag ID 24
- **Size**: 8.125" square (36h11 family)

### OBELISK Tags (for MOTIF detection)
- **Tag ID 21**: Corresponds to G-P-P motif
- **Tag ID 22**: Corresponds to P-G-P motif
- **Tag ID 23**: Corresponds to P-P-G motif

---

## MOTIF System

### Definition
A MOTIF is a sequence of 3 ARTIFACT colors (2 Purple, 1 Green) repeated 3 times to define the 9-position PATTERN on the RAMP.

### Three MOTIFS
1. **G-P-P** (AprilTag 21): Pattern = G-P-P-G-P-P-G-P-P
2. **P-G-P** (AprilTag 22): Pattern = P-G-P-P-G-P-P-G-P
3. **P-P-G** (AprilTag 23): Pattern = P-P-G-P-P-G-P-P-G

### Randomization
- OBELISK orientation randomized by FIELD STAFF after DRIVE TEAMS setup
- Happens using event management software
- One AprilTag faces the field
- ROBOTS use sensors/vision to detect which MOTIF is active

---

## Scoring Mechanics

### ARTIFACT Scoring Types

#### 1. CLASSIFIED
- ARTIFACT enters GOAL through open top
- Exits under archway
- Passes through SQUARE
- **Transitions directly to RAMP** (does not roll over existing ARTIFACTS)
- Worth **3 points** (AUTO or TELEOP)

#### 2. OVERFLOW
- ARTIFACT enters GOAL through open top
- Exits under archway
- Passes through SQUARE
- **Does NOT transition directly** (may roll over ARTIFACTS on RAMP)
- Worth **1 point** (AUTO or TELEOP)

#### 3. DEPOT
- ARTIFACTS over the DEPOT at match end
- Worth **1 point each**
- Assessed after match ends
- Either alliance can score in either DEPOT

### PATTERN Scoring
- Assessed at **end of AUTO** and **end of TELEOP**
- ARTIFACTS directly on RAMP score if:
    1. Color matches MOTIF index position
    2. ARTIFACTS retained by GATE (not released)
- Worth **2 points per matching ARTIFACT**
- Example: If MOTIF is G-P-P and RAMP has G-P-P at positions 1-2-3, score 6 points

### ROBOT Scoring

#### LEAVE (AUTO only)
- ROBOT no longer over any LAUNCH LINE at end of AUTO
- Worth **3 points**

#### BASE (TELEOP end)
- **Partially returned**: Some support from BASE ZONE tile, some from outside = **5 points**
- **Fully returned**: All support from BASE ZONE tile only = **10 points**
- **Both ROBOTS fully returned**: Additional **10 bonus points** for alliance

---

## Point Values Summary

| Action | Period | Points |
|--------|--------|--------|
| CLASSIFIED | AUTO | 3 |
| CLASSIFIED | TELEOP | 3 |
| OVERFLOW | AUTO | 1 |
| OVERFLOW | TELEOP | 1 |
| DEPOT | End | 1 |
| PATTERN match | AUTO | 2 each |
| PATTERN match | TELEOP | 2 each |
| LEAVE | AUTO | 3 |
| Partially returned to BASE | TELEOP | 5 |
| Fully returned to BASE | TELEOP | 10 |
| Both ROBOTS fully in BASE | TELEOP | 10 bonus |
| WIN match | N/A | 3 RP |
| TIE match | N/A | 1 RP |

---

## Ranking Points (RPs)

### Standard Events Thresholds
- **MOVEMENT RP**: LEAVE + BASE points ≥ 16
- **GOAL RP**: Total ARTIFACTS scored through SQUARE ≥ 36
- **PATTERN RP**: PATTERN points ≥ 18

### Championship/Regional Championship
- Thresholds TBA (will be announced in Team Updates)

---

## Match Periods & Timing

### Setup Phase
- ROBOTS placed over LAUNCH LINE
- Must touch GOAL or field perimeter
- Must be in STARTING CONFIGURATION (18" cube)
- Pre-load up to 3 ARTIFACTS

### AUTO (0:30)
- ROBOTS operate autonomously
- No driver input allowed (except emergency stop)
- OpMode must be initialized with 30-second timer enabled
- OBELISK detection window

### Transition (0:08)
- Between AUTO and TELEOP
- No powered ROBOT movement allowed
- Time to switch OpModes

### TELEOP (2:00)
- Drivers control ROBOTS
- Human players can place ARTIFACTS in LOADING ZONE
- Last 20 seconds: Vertical expansion up to 38" allowed

---

## Robot Rules for Programming

### Starting Configuration
- **Max Size**: 18" × 18" × 18" cube
- Pre-loaded ARTIFACTS may extend outside
- Must be self-supported (can use OpMode to hold servos/motors in position)

### Expansion Limits
- **Horizontal**: Always within 18" × 18" footprint (relative to starting orientation)
- **Vertical**:
    - Default: Up to 18"
    - Last 20 seconds + not in LAUNCH ZONE: Up to 38"

### CONTROL Limits
- **Max ARTIFACTS**: No more than 3 simultaneously CONTROLLED
- CONTROL = fully supported, stuck in/on, or intentionally pushing/herding

### LAUNCHING Rules
- May only LAUNCH when:
    - Inside LAUNCH ZONE, OR
    - Overlapping LAUNCH LINE
- LAUNCHING outside = penalties

### GATE Operation
- Only push down to open (no pulling/closing force)
- May need to hold open to fully clear RAMP
- Cannot contact opponent's GATE

### RAMP Interaction
- **Cannot contact ARTIFACTS on RAMP** (own or opponent)
- Exception: By operating own GATE
- Cannot remove ARTIFACTS from own RAMP except via GATE

### Zone Restrictions (for penalty avoidance)
- **GATE ZONE**: No opponent contact if either ROBOT in opponent's GATE ZONE
- **SECRET TUNNEL**: No opponent contact if in opponent's SECRET TUNNEL
- **LOADING ZONE**: No opponent contact if either ROBOT in opponent's LOADING ZONE
- **BASE ZONE** (last 20 sec): No opponent contact if either ROBOT in opponent's BASE ZONE

---

## Sensor/Vision Considerations

### AprilTag Detection
- Primary method for MOTIF identification
- OBELISK location approximate (not for navigation)
- GOAL tags (20, 24) reliable for targeting

### Vision Systems
- USB webcams allowed (UVC compatible)
- Supported coprocessors: Limelight 3A only (programmable)
- Other vision devices: configurable but not programmable

### Distance/Positioning
- Consider using encoders, odometry, IMU
- Dead-wheel odometry kits allowed

---

## Autonomous Strategy Considerations

### MOTIF Detection
- Read OBELISK AprilTag (21, 22, or 23)
- Determine target PATTERN for RAMP
- Adapt scoring strategy based on MOTIF

### Scoring Priorities
1. **LEAVE**: Move off LAUNCH LINE (3 points)
2. **CLASSIFIED**: Score matching MOTIF colors first (3 pts + 2 pattern pts = 5 each)
3. **PATTERN**: Build correct sequence on RAMP

### Field Division
- Stay on own alliance side (Columns A-B-C for blue, D-E-F for red)
- Crossing = risk of penalties for opponent contact

### Pre-loaded ARTIFACTS
- Start with up to 3 from ALLIANCE AREA
- Strategic: Pre-load colors needed for MOTIF pattern

---

## TELEOP Strategy Considerations

### Human Player Rules
- May only interact with ARTIFACTS in LOADING ZONE
- Cannot LAUNCH, bounce, or roll ARTIFACTS into field
- Must place directly in LOADING ZONE or into ROBOT in LOADING ZONE
- Max 6 ARTIFACTS stored out of play at once

### DEPOT Scoring
- Easy points at match end
- Can score in opponent's DEPOT too
- Not protected zone

### End-Game (Last 20 seconds)
- Audio cue: "Train Whistle" at 0:20
- Vertical expansion to 38" allowed (if not in LAUNCH ZONE)
- Focus on returning to BASE
- Both ROBOTS fully in BASE = 30 total points (10+10+10 bonus)

---

## Control System Requirements

### Motors
- Max 8 motors total (all configurations combined)
- Allowed motors: AndyMark NeveRest, goBILDA, REV HD/Core Hex, etc. (see R501)

### Servos
- Max 10 servos total
- Must meet power specs: ≤8W mechanical output, ≤4A stall at 6V
- Linear servos: ≤1A stall at 6V

### Power
- One 12V NiMH battery (approved types only)
- 20A fuse required
- Main power switch required

### Control Hub/Expansion Hub
- REV Control Hub or Android phone + REV Expansion Hub
- Up to 1 additional Expansion Hub allowed
- 5V power shared across servo ports (5A total, 2A per port pair)

### Software
- Minimum recommended: ROBOT CONTROLLER App 11.0
- Hub Firmware 1.8.2
- Control Hub OS 1.1.2 / Driver Hub OS 1.2.0

### Wireless
- No other wireless allowed except ROBOT CONTROLLER ↔ DRIVER STATION
- Configure device names: [team#]-RC and [team#]-DS
- May be asked to use specific WiFi band/channel

---

## Common Penalties to Avoid in Code

### Movement Violations
- **G403**: Powered movement during AUTO→TELEOP transition = MAJOR FOUL
- **G404**: Movement after match end = MINOR/MAJOR FOUL
- **G414/G415**: Exceeding expansion limits = MINOR/MAJOR FOUL
- **G416**: LAUNCHING outside LAUNCH ZONE = MINOR/MAJOR FOUL

### ARTIFACT Violations
- **G408**: CONTROL more than 3 = MINOR FOUL per extra (can escalate to YELLOW CARD)
- **G417**: Contact opponent's GATE = MAJOR FOUL + opponent gets PATTERN RP
- **G418**: Contact ARTIFACTS on RAMP = MAJOR FOUL + lose PATTERN RP eligibility
- **G419**: LAUNCH into opponent's GOAL = MAJOR FOUL + opponent gets PATTERN RP

### Opponent Contact
- **G420**: Deliberately damage opponent = MAJOR FOUL + YELLOW/RED CARD
- **G421**: Tip or entangle opponent = MAJOR FOUL + YELLOW/RED CARD
- **G422**: PIN opponent >3 seconds = MINOR FOUL (every 3 sec)
- **G424-G427**: Contact in protected zones = MINOR/MAJOR FOUL

---

## Key Programming Tasks

### Autonomous Mode
1. Initialize OpMode with 30-sec timer
2. Detect OBELISK AprilTag → determine MOTIF
3. Navigate from starting position
4. Score pre-loaded ARTIFACTS (matching MOTIF colors prioritized)
5. Collect additional ARTIFACTS from field
6. Score to build PATTERN on RAMP
7. Move off LAUNCH LINE before AUTO ends

### TeleOp Mode
1. Driver control for movement
2. Intake system for ARTIFACT collection
3. Scoring mechanism (LAUNCHING into GOAL)
4. GATE operation (push down to release RAMP)
5. End-game: Navigate to BASE ZONE
6. Sensor feedback (e.g., ARTIFACT detected, position tracking)

### Vision Processing
1. AprilTag detection (OBELISK: IDs 21-23, GOALs: IDs 20, 24)
2. Color detection (purple vs green ARTIFACTS)
3. Distance estimation for targeting
4. FIELD position localization

### State Management
1. Current MOTIF (detected in AUTO)
2. ARTIFACT count in CONTROL
3. Current ROBOT position/orientation
4. Match period (AUTO vs TELEOP)
5. Time remaining (for end-game)

---

## Testing & Calibration Considerations

### Pre-Match FIELD Access
- 30+ minutes before Qualification Matches (at discretion)
- Can measure FIELD, calibrate sensors
- CANNOT drive ROBOT around or practice

### During Match
- Practice MATCHES allowed (if scheduled)
- Must pass inspection before Qualification MATCHES
- No timeout system (but min 5 min between Qualification, 8 min between Playoff)

---

## Important Notes

### FIELD Variability
- Tolerance: ±1" on most dimensions
- FIELD is assembled/disassembled frequently
- Design for robustness, not precision

### GATE Behavior
- May not stay open without ROBOT holding it
- Variable closing time (not an ARENA FAULT)
- Plan for both scenarios

### ARTIFACT Physics
- Sometimes skip 9th slot on RAMP = OVERFLOW (normal behavior)
- Deflections from GOAL structure don't count as violations

### Match Replay Criteria
- Only for ARENA FAULTs affecting outcome
- Team must request replay
- NOT for ROBOT failures (low battery, code issues, mechanical problems)

---

This guide covers all essential game mechanics, rules, and specifications needed for programming your FTC ROBOT for DECODE. Use this as context when developing autonomous routines, TeleOp controls, and vision systems.