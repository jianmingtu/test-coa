package ca.bc.gov.open.coa.test.services;

import com.eviware.soapui.tools.SoapUITestCaseRunner;
import java.io.*;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Value("${test.username}")
    private String username;

    @Value("${test.password}")
    private String password;

    public TestService() {}

    public void setAuthentication() throws IOException {
        File template =
                new File(
                        "AutomatedTest/src/main/resources/SoapUiFile/coa-soapui-project-template.xml");
        Scanner scanner = new Scanner(template);
        BufferedWriter writer =
                new BufferedWriter(
                        new FileWriter(
                                "AutomatedTest/src/main/resources/SoapUiFile/coa-soapui-project.xml"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("{AUTHENTICATION_USERNAME}")) {
                line = line.replaceAll("\\{AUTHENTICATION_USERNAME}", username);
            }
            if (line.contains("{AUTHENTICATION_PASSWORD}")) {
                line = line.replaceAll("\\{AUTHENTICATION_PASSWORD}", password);
            }
            writer.append(line);
        }
        writer.flush();
        writer.close();
        scanner.close();
    }

    public void runAllTests() throws Exception {

        // locate the project
        SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
        runner.setProjectFile("AutomatedTest/src/main/resources/SoapUiFile/coa-soapui-project.xml");
        runner.run();
    }
}
