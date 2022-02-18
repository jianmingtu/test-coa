package ca.bc.gov.open.coa;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.coa.configuration.CoaConfig;
import ca.bc.gov.open.coa.controllers.StorageController;
import ca.bc.gov.open.coa.one.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StorageControllerTests {
    @Autowired private ObjectMapper objectMapper;

    @Autowired private CoaConfig coaConfig;

    @Mock private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getDocumentUploadStateTest() throws JsonProcessingException {
        var req = new GetDocumentUploadStateRequest();
        var resp = new GetDocumentUploadStateResponse();

        req.setDocumentGUID("A");
        req.setTimeoutX00A0(Integer.MAX_VALUE);
        resp.setState("A");
        ResponseEntity<GetDocumentUploadStateResponse> responseEntity =
                new ResponseEntity<>(resp, HttpStatus.OK);

        // Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(URI.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetDocumentUploadStateResponse>>any()))
                .thenReturn(responseEntity);

        StorageController storageController =
                new StorageController(restTemplate, objectMapper, coaConfig);
        var out = storageController.getDocumentUploadState(req);
        Assertions.assertNotNull(out);
    }

    @Test
    public void storeDocumentTest() throws JsonProcessingException {
        var req = new StoreDocumentRequest();
        var resp = new StoreDocumentResponse();

        req.setBase64Document("A");
        req.setApplicationViewGrant("A");
        req.setFilename("A");
        resp.setDocumentGUID("A");
        ResponseEntity<StoreDocumentResponse> responseEntity =
                new ResponseEntity<>(resp, HttpStatus.OK);

        // Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(URI.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<StoreDocumentResponse>>any()))
                .thenReturn(responseEntity);

        StorageController storageController =
                new StorageController(restTemplate, objectMapper, coaConfig);
        var out = storageController.storeDocument(req);
        Assertions.assertNotNull(out);
    }

    @Test
    public void storeDocumentAsyncTest() throws JsonProcessingException {
        var req = new StoreDocumentAsyncRequest();
        var resp = new StoreDocumentAsyncResponse();

        req.setFilename("A");
        req.setApplicationViewGrant("A");
        resp.setDocumentGUID("A");
        ResponseEntity<StoreDocumentAsyncResponse> responseEntity =
                new ResponseEntity<>(resp, HttpStatus.OK);

        // Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(URI.class),
                        Mockito.eq(HttpMethod.POST),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<StoreDocumentAsyncResponse>>any()))
                .thenReturn(responseEntity);

        StorageController storageController =
                new StorageController(restTemplate, objectMapper, coaConfig);
        var out = storageController.storeDocumentAsync(req);
        Assertions.assertNotNull(out);
    }
}
