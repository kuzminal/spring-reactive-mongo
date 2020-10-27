package com.kuzmin.studentsapp.finctional;

import com.kuzmin.studentsapp.model.Student;
import com.kuzmin.studentsapp.repository.StudentMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class StudentRouterHandlerCombined {
    @Autowired
    private StudentMongoRepository studentMongoRepository;
    @Bean
    RouterFunction<ServerResponse> returnStudentWithCombineFun(){
        HandlerFunction<ServerResponse> studentHandler =
                serverRequest -> {
                    int rollNo = getInt(serverRequest.pathVariable("rollNo"));
                    return ServerResponse.ok().
                            body(studentMongoRepository.findByRollNo(rollNo)
                                    , Student.class);
                };
        RouterFunction<ServerResponse> studentResponse =
                RouterFunctions.route(
                        RequestPredicates.GET("/api/f/combine/getStudent/{rollNo}"),
                        studentHandler);
        return studentResponse;
    }
    @Bean
    RouterFunction<ServerResponse> returnAllStudentWithCombineFun(){
        HandlerFunction<ServerResponse> studentHandler =
                serverRequest ->
                        ServerResponse.ok().
                                body(studentMongoRepository.findAll(), Student.class);
        RouterFunction<ServerResponse> studentResponse =
                RouterFunctions.route(
                        RequestPredicates.GET("/api/f/combine/getAllStudent"),
                        studentHandler);
        return studentResponse;
    }
    private int getInt(String intStr) {
        int returnVal=0;
        if(intStr !=null && !intStr.isEmpty()) {
            try {
                returnVal = Integer.parseInt(intStr);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return returnVal;
    }
}
