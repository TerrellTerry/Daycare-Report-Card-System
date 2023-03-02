package dev.titans.entities;

import javax.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g_id")
    private int gradeId;

    @Column(name = "s_id")
    private int studentId;

    @Column(name = "time_reported")
    private long timeReported;

    @Column(name = "note")
    private String note;

    @Column(name = "behavior")
    private Behavior behavior;

    public Grade() {
    }

    public Grade(int gradeId, int studentId, long timeReported, String note, Behavior behavior) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.timeReported = timeReported;
        this.note = note;
        this.behavior = behavior;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public long getTimeReported() {
        return timeReported;
    }

    public void setTimeReported(long timeReported) {
        this.timeReported = timeReported;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "g_id=" + gradeId +
                ", s_id=" + studentId +
                ", time_reported=" + timeReported +
                ", note='" + note + '\'' +
                ", behavior=" + behavior +
                '}';
    }
}
