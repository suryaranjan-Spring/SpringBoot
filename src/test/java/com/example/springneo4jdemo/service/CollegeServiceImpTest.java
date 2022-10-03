package com.example.springneo4jdemo.service;

import com.example.springneo4jdemo.dto.CollegePayLoadDTO;
import com.example.springneo4jdemo.dto.DepartmentPayLoadDTO;
import com.example.springneo4jdemo.entity.College;
import com.example.springneo4jdemo.exception.DataNotFoundExeption;
import com.example.springneo4jdemo.repository.CollegeRepository;
import com.example.springneo4jdemo.service.CollegeServiceImp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CollegeServiceImpTest {
    @Mock
    private CollegeRepository collegeRepository;
    @InjectMocks
    private CollegeServiceImp collegeService;

    @Test
    void saveCollege() {
    }

    @Test
    void updateCollegeWithDEpartment() {
    }

    @Test
    void deleteCollegeById() {
    }

    @Test
    @DisplayName("Test for invalid college  data")
    public void invalidCollegeaddDepartmentTest() throws Exception {
        //given
        CollegePayLoadDTO collegePayLoadDTO = new CollegePayLoadDTO();
        collegePayLoadDTO.setCollegeName("KIIT");
        // when
        Mockito.when(collegeRepository.findByCollegeName(collegePayLoadDTO.getCollegeName())).thenReturn(null);
        assertThatThrownBy(()->collegeService.addDepartment(collegePayLoadDTO)).isInstanceOf(DataNotFoundExeption.class).hasMessage("given a wrong college name");
    }
    @Test
    @DisplayName("Test for Successfully add Department")
    public void successCollegeaddDepartmentTest() throws Exception {
        //given
        CollegePayLoadDTO collegePayLoadDTO = new CollegePayLoadDTO();
        collegePayLoadDTO.setCollegeName("KIIT");
        List<DepartmentPayLoadDTO> departmentPayLoadDTOList=new ArrayList<>();
        DepartmentPayLoadDTO departmentPayLoadDTO1=new DepartmentPayLoadDTO();
        departmentPayLoadDTO1.setDepartmentName("CSE");
        departmentPayLoadDTOList.add(departmentPayLoadDTO1);
        DepartmentPayLoadDTO departmentPayLoadDTO2=new DepartmentPayLoadDTO();
        departmentPayLoadDTO1.setDepartmentName("ECE");
        departmentPayLoadDTOList.add(departmentPayLoadDTO2);
        DepartmentPayLoadDTO departmentPayLoadDTO3=new DepartmentPayLoadDTO();
        departmentPayLoadDTO1.setDepartmentName("MECHANICAL");
        departmentPayLoadDTOList.add(departmentPayLoadDTO3);
        collegePayLoadDTO.setDepartmentList(departmentPayLoadDTOList);

        List<String> departmentList = new ArrayList<>();
        collegePayLoadDTO.getDepartmentList().forEach(departmentPayLoadDTO -> {
            departmentList.add(departmentPayLoadDTO.getDepartmentName());
        });
        College college = new College();


        // when
        Mockito.when(collegeRepository.findByCollegeName(collegePayLoadDTO.getCollegeName())).thenReturn(college);
        Mockito.when(collegeRepository.addDepartment(collegePayLoadDTO.getCollegeName(), departmentList)).thenReturn("department is added to college");
        assertEquals("department is added to college",collegeService.addDepartment(collegePayLoadDTO));
    }
}