package com.scaler.ecommerceapp;



import com.scaler.ecommerceapp.InheritanceExample.joined.Mentor;
import com.scaler.ecommerceapp.InheritanceExample.joined.MentorRespository;
import com.scaler.ecommerceapp.InheritanceExample.joined.Student;
import com.scaler.ecommerceapp.InheritanceExample.joined.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class EcommerceAppApplication implements CommandLineRunner {
    private MentorRespository mentorRepository;
    private StudentRepository studentRepository;
    public EcommerceAppApplication(MentorRespository mentorRepository, StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.mentorRepository = mentorRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(EcommerceAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        Mentor mentor = new Mentor();
//        mentor.setAvgRating(4.5f);
//        mentor.setName("Gurpreet Singh");
//        mentor.setEmail("gs@gmail.com");
//        mentorRepository.save(mentor);
//
//        Student student = new Student();
//        student.setBatch("Sept2022");
//        student.setPsp(95);
//        student.setName("Rahul");
//        student.setEmail("rahul@gmail.com");
//        studentRepository.save(student);
//
//        User user = new User();
//        user.setName("Sparsh");
//        user.setEmail("sparsh@gmail.com");
//        userRepository.save(user);

//        List<User> users = userRepository.findAll();
//        users.forEach(user2 -> System.out.println(user2.getName()));

        Mentor mentor = new Mentor();
        mentor.setAvgRating(4.9f);
        mentor.setName("Saket Ghosh");
        mentor.setEmail("sakets@gmail.com");
        mentorRepository.save(mentor);

        Student student = new Student();
        student.setName("Ankit");
        student.setEmail("ankit1@gmail.com");
        student.setBatch("Oct2022");
        student.setPsp(90);
        studentRepository.save(student);
    }
}
