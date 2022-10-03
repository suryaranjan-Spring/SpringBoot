/*
package com.example.springneo4jdemo.repository;

import com.example.springneo4jdemo.dto.CollegePayLoadDTO;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.neo4j.driver.Values.parameters;

@Repository
public class CollegeDAO {
    private final Driver driver;

    public CollegeDAO(Driver driver) {
        this.driver = driver;
    }
    public String addDepartmentListIntoCollege(String collegeName, List<String> departmentList){
        StringBuilder query= new StringBuilder("match(college:College{collegeName:\"KIIT\"})\n" +
                "match(department:Department) where department.department_name  IN [");
        for(int i=0;i<departmentList.size();i++){
            query.append("$department"+i);
        }
        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(tx -> {
                Result result = tx.run(
                        "CREATE (a:Greeting) " + "SET a.message = $message "
                                + "RETURN a.message + ', from node ' + id(a)",
                        parameters("message", collegeName,"message", collegeName));
                return result.single().get(0).asString();
            });
            System.out.println(greeting);
        }
    }

}
*/
