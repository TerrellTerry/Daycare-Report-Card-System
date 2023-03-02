package dev.titans.entities;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private int studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "guardian_username")
    private String guardianUsername;

    public Student() {
    }

    public Student(int studentId, String firstName, String lastName, String guardianUsername) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.guardianUsername = guardianUsername;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGuardianUsername() {
        return guardianUsername;
    }

    public void setGuardianUsername(String guardianUsername) {
        this.guardianUsername = guardianUsername;
    }

    @Override
    public String toString() {
        return "Student{" +
                "s_id=" + studentId +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", guardian_username='" + guardianUsername + '\'' +
                '}';
    }
}
