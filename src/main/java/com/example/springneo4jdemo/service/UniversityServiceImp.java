package com.example.springneo4jdemo.service;

import com.example.springneo4jdemo.dto.UniversityPayLoadDTO;
import com.example.springneo4jdemo.entity.College;
import com.example.springneo4jdemo.entity.Department;
import com.example.springneo4jdemo.entity.University;
import com.example.springneo4jdemo.repository.CollegeRepository;
import com.example.springneo4jdemo.repository.DepartmentRepository;
import com.example.springneo4jdemo.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UniversityServiceImp implements IUniversityService {

    private UniversityRepository universityRepository;

    private CollegeRepository collegeRepository;

    private DepartmentRepository departmentRepository;

    public UniversityServiceImp(UniversityRepository universityRepository, CollegeRepository collegeRepository, DepartmentRepository departmentRepository) {
        this.universityRepository = universityRepository;
        this.collegeRepository = collegeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public University saveUniversity(UniversityPayLoadDTO universityPayLoadDTO) throws Exception {
        List<College> collegeList = new ArrayList<>();
        List<Department> departmentList = new ArrayList<>();
        //save department
        universityPayLoadDTO.getDepartmentList().forEach(department -> {
            Department existingDepartment = departmentRepository.findByDepartmentName(department.getDepartmentName());
            if (existingDepartment == null) {
                Department newDepartment = new Department();
                newDepartment.setDepartmentName(department.getDepartmentName());
                departmentList.add(newDepartment);
            } else {
                departmentList.add(existingDepartment);
            }
        });

        //save college
        universityPayLoadDTO.getCollegeList().forEach(college -> {
            College existingCollege = collegeRepository.findByCollegeName(college.getCollegeName());
            if (existingCollege == null) {
                College newCollege = new College();
                newCollege.setCollegeName(college.getCollegeName());
                collegeList.add(newCollege);
            } else {
                collegeList.add(existingCollege);
            }
        });
        // save university
        University university = new University();
        university.setUniversityName(universityPayLoadDTO.getUniversityName());
        university.setDepartmentList(departmentList);
        university.setCollegeList(collegeList);
        return universityRepository.save(university);

    }
}
