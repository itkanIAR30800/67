package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

/**
 * A simple pose container that works in both MeepMeep and Robot code.
 * This enables copying coordinates directly between simulation and reality.
 */
public class EditablePose {
    public double x, y, heading;

    public EditablePose(double x, double y, double heading) {
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    public Pose2d toPose2d() {
        return new Pose2d(x, y, heading);
    }

    public Vector2d toVector2d() {
        return new Vector2d(x, y);
    }

    public double distTo(EditablePose other) {
        double dx = other.x - x;
        double dy = other.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double angleTo(EditablePose other) {
        return Math.atan2(other.y - y, other.x - x);
    }
}

