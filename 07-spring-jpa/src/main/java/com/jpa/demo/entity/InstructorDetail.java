package com.jpa.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="youtube_channel")
    private String youtubeChannel;

    @Column(name="hobby")
    private String hobby;

    //THIS WILL LINKED INSTRUCTOR OBJECT, TO retain instructor record when deleting the detail, we can change CascadeType as follows
    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Instructor instructor;

    public InstructorDetail() {
    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return this.youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby() {
        return this.hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public InstructorDetail id(int id) {
        setId(id);
        return this;
    }

    public InstructorDetail youtubeChannel(String youtubeChannel) {
        setYoutubeChannel(youtubeChannel);
        return this;
    }

    public InstructorDetail hobby(String hobby) {
        setHobby(hobby);
        return this;
    }


    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", youtubeChannel='" + getYoutubeChannel() + "'" +
            ", hobby='" + getHobby() + "'" +
            "}";
    }
    

}
