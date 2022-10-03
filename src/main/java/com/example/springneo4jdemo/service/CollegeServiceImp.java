package com.example.springneo4jdemo.service;

import com.example.springneo4jdemo.dto.CollegePayLoadDTO;
import com.example.springneo4jdemo.entity.College;
import com.example.springneo4jdemo.entity.Department;
import com.example.springneo4jdemo.exception.DataNotFoundExeption;
import com.example.springneo4jdemo.repository.CollegeRepository;
import com.example.springneo4jdemo.repository.DepartmentRepository;
import org.neo4j.driver.Driver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CollegeServiceImp implements ICollegeService {

    private CollegeRepository collegeRepository;
    private DepartmentRepository departmentRepository;

    private final Driver driver;

    public CollegeServiceImp(CollegeRepository collegeRepository, DepartmentRepository departmentRepository, Driver driver) {
        this.collegeRepository = collegeRepository;
        this.departmentRepository = departmentRepository;
        this.driver = driver;
    }

    @Override
    public College saveCollege(CollegePayLoadDTO collegePayLoadDTO) throws Exception {
        List<Department> departmentList = new ArrayList<Department>();
        collegePayLoadDTO.getDepartmentList().forEach(department -> {
            Department existingDepartment = departmentRepository.findByDepartmentName(department.getDepartmentName());
            if (existingDepartment == null) {
                Department newDepartment = new Department();
                newDepartment.setDepartmentName(department.getDepartmentName());
                departmentList.add(newDepartment);
            } else {
                departmentList.add(existingDepartment);
            }
        });
        College college = new College();
        college.setCollegeName(collegePayLoadDTO.getCollegeName());
        college.setDepartmentList(departmentList);

        return collegeRepository.save(college);
    }

    @Override
    public int updateCollegeWithDEpartment(String collegename, String departmentname, String relationType) throws Exception {
        return collegeRepository.updateCollegeWithSingleDepartment(collegename, departmentname, relationType);
    }

    public void deleteCollegeById(UUID id)throws Exception {
        collegeRepository.deleteById(id);
    }
    @Transactional
    public String addDepartment(CollegePayLoadDTO collegePayLoadDTO) {

        //check college is present or not
        College college = collegeRepository.findByCollegeName(collegePayLoadDTO.getCollegeName());
        if (college == null) {
            throw new DataNotFoundExeption("given a wrong college name");
        }
        List<String> departmentList = new ArrayList<>();
        collegePayLoadDTO.getDepartmentList().forEach(departmentPayLoadDTO -> {
            departmentList.add(departmentPayLoadDTO.getDepartmentName());
        });

        return collegeRepository.addDepartment(collegePayLoadDTO.getCollegeName(), departmentList);
    }
}
