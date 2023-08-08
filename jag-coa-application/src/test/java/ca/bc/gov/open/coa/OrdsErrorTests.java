package ca.bc.gov.open.coa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ca.bc.gov.open.coa.configuration.CoaConfig;
import ca.bc.gov.open.coa.controllers.FileController;
import ca.bc.gov.open.coa.controllers.HealthController;
import ca.bc.gov.open.coa.controllers.StorageController;
import ca.bc.gov.open.coa.controllers.TicketController;
import ca.bc.gov.open.coa.exceptions.ORDSException;
import ca.bc.gov.open.coa.one.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrdsErrorTests {
    @Autowired private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private CoaConfig coaConfig;

    @Autowired private MockMvc mockMvc;

    @Mock private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getHealthOrdsFailTest() {
        HealthController healthController = new HealthController(restTemplate, objectMapper);

        Assertions.assertThrows(
                ORDSException.class, () -> healthController.getHealth(new GetHealth()));
    }

    @Test
    public void getPingOrdsFailTest() {
        HealthController healthController = new HealthController(restTemplate, objectMapper);

        Assertions.assertThrows(ORDSException.class, () -> healthController.getPing(new GetPing()));
    }

    @Test
    public void getFileSizeOrdsFailTest() {
        FileController fileController = new FileController(restTemplate, objectMapper, coaConfig);
        GetFileSizeRequest req = new GetFileSizeRequest();
        req.setDocumentGUID("A");
        Assertions.assertThrows(ORDSException.class, () -> fileController.getFileSize(req));
    }

    @Test
    public void getFileMimeOrdsFailTest() {
        FileController fileController = new FileController(restTemplate, objectMapper, coaConfig);
        GetFileMimeRequest req = new GetFileMimeRequest();
        req.setDocumentGUID("A");
        Assertions.assertThrows(ORDSException.class, () -> fileController.getFileMime(req));
    }

    @Test
    public void getTicketedUrlOrdsFailTest() {
        TicketController ticketController =
                new TicketController(restTemplate, objectMapper, coaConfig);
        GetTicketedUrlRequest req = new GetTicketedUrlRequest();
        req.setDocumentGUID("A");
        Assertions.assertThrows(ORDSException.class, () -> ticketController.getTicketedUrl(req));
    }

    @Test
    public void getTicketOrdsFailTest() {
        TicketController ticketController =
                new TicketController(restTemplate, objectMapper, coaConfig);
        GetTicketRequest req = new GetTicketRequest();
        req.setDocumentGUID("A");
        Assertions.assertThrows(ORDSException.class, () -> ticketController.getTicket(req));
    }

    @Test
    public void getDocumentUploadStateOrdsFailTest() {
        StorageController storageController =
                new StorageController(restTemplate, objectMapper, coaConfig);
        GetDocumentUploadStateRequest req = new GetDocumentUploadStateRequest();
        req.setDocumentGUID("A");
        Assertions.assertThrows(
                ORDSException.class, () -> storageController.getDocumentUploadState(req));
    }

    @Test
    public void storeDocumentOrdsFailTest() {
        StorageController storageController =
                new StorageController(restTemplate, objectMapper, coaConfig);

        Assertions.assertThrows(
                ORDSException.class,
                () -> storageController.storeDocument(new StoreDocumentRequest()));
    }

    @Test
    public void storeDocumentAsyncOrdsFailTest() {
        StorageController storageController =
                new StorageController(restTemplate, objectMapper, coaConfig);

        Assertions.assertThrows(
                ORDSException.class,
                () -> storageController.storeDocumentAsync(new StoreDocumentAsyncRequest()));
    }

//    @Test
//    public void securityTestFail_Then401() throws Exception {
//        var response =
//                mockMvc.perform(post("/ws").contentType(MediaType.TEXT_XML))
//                        .andExpect(status().is4xxClientError())
//                        .andReturn();
//        Assertions.assertEquals(
//                HttpStatus.UNAUTHORIZED.value(), response.getResponse().getStatus());
//    }
}
