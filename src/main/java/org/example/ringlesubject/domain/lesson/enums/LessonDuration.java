package org.example.ringlesubject.domain.lesson.enums;

public enum LessonDuration {
    MINUTES_30,
    MINUTES_60;

    public boolean is30Min() {
        return this == MINUTES_30;
    }

    public boolean is60Min() {
        return this == MINUTES_60;
    }
}
