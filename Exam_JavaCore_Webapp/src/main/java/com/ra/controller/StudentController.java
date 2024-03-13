package com.ra.controller;

import com.ra.model.entity.Student;
import com.ra.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Value("${path}")
    private String path;
    @GetMapping("/")
    public String index(Model model){
        List<Student> students = studentService.getAll();
        model.addAttribute("students",students);
        return "home";
    }
    @GetMapping("/add-student")
    public String add(Model model,Student student){
        model.addAttribute("product",student);
        return "add";
    }
    @PostMapping("/add-student")
    public String create(@ModelAttribute("student") Student student){
        // upload file

        // thêm sản phẩm
        studentService.saveOrUpdate(student);
        return "redirect:/home";
    }
    @GetMapping("/edit-product/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        Student product = studentService.findById(id);
        model.addAttribute("product",product);
        return "edit";
    }
    @PostMapping("/edit-student/{id}")
    public String update(
            @PathVariable("id") Integer id,
            @ModelAttribute("student") Student student
            ){
        if(studentService.saveOrUpdate(student)){
            return "redirect:/home";
        }
        return "redirect:/edit-student/"+id;
    }

    @GetMapping("/delete-student/{id}")
    public String delete(@PathVariable Integer id){
        studentService.delete(id);
        return "redirect:/home";
    }
}
