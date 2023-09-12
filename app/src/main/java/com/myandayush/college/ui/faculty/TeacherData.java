package com.myandayush.college.ui.faculty;

public class   TeacherData {
     String name, email, post, image, teacherDepartment, uniqueKEy;  // key used to update the teacher data
      TeacherData(){

      }


    public TeacherData(String name, String email, String teacherDepartment,String post, String image, String uniqueKEy) {
        this.name = name;
        this.email = email;
        this.post = post;
        this.image = image;
        this.teacherDepartment = teacherDepartment;
        this.uniqueKEy = uniqueKEy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTeacherDepartment() {
        return teacherDepartment;
    }

    public void setTeacherDepartment(String teacherDepartment) {
        this.teacherDepartment = teacherDepartment;
    }

    public String getUniqueKEy() {
        return uniqueKEy;
    }

    public void setUniqueKEy(String uniqueKEy) {
        this.uniqueKEy = uniqueKEy;
    }

}
