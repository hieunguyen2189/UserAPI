package com.rest.userapi.Models;
import javax.persistence.*;

@Entity
@Table(name = "user")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "dept")
    private String dept;
    @Column(name = "age")
    private int age;
    @Column(name = "detail")
    private String detail;
    @Column(name = "img")
    private String img;
    public user() {

    }

    public user(String name, String dept, int age,String detail,String img) {
        this.name = name;
        this.dept = dept;
        this.age = age;
        this.detail = detail;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public int getAge() {
        return age;
    }

    public String getDetail() {
        return detail;
    }

    public String getImg() {
        return img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", age=" + age +
                ", detail='" + detail + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
