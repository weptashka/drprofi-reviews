package com.drprofireviews.controllers;

import com.drprofireviews.models.Doctor;
import com.drprofireviews.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorController() {
    }
    @GetMapping({"/doctor/table"})
    public String getDoctorTable(Model model) {
        Iterable<Doctor> doctors = this.doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        return "doctor/doctor-table";
    }

}
