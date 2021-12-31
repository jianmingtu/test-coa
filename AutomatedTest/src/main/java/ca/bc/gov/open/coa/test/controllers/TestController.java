package ca.bc.gov.open.coa.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestController {
//    private TestService testService;
    @Autowired
    public TestController(){

        //this.testService = testService;
    }
//    @GetMapping("/all")
//
//    public String runAllTests() throws Exception {
//        testService.runAllTests();
//        return"TestComplete";
//
//    }

    @GetMapping("/ping")
    public String ping() {
        return "ping";
    }
}
