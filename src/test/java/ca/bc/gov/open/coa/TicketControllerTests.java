package ca.bc.gov.open.coa;

import static org.mockito.Mockito.when;

import ca.bc.gov.open.coa.configuration.CoaConfig;
import ca.bc.gov.open.coa.controllers.TicketController;
import ca.bc.gov.open.coa.one.GetTicketRequest;
import ca.bc.gov.open.coa.one.GetTicketResponse;
import ca.bc.gov.open.coa.one.GetTicketedUrlRequest;
import ca.bc.gov.open.coa.one.GetTicketedUrlResponse;
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
public class TicketControllerTests {
    @Autowired private ObjectMapper objectMapper;

    @Autowired private CoaConfig coaConfig;

    @Mock private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getTicketedUrlTest() throws JsonProcessingException {
        var req = new GetTicketedUrlRequest();
        var resp = new GetTicketedUrlResponse();

        resp.setTicketedUrl("A");
        ResponseEntity<GetTicketedUrlResponse> responseEntity =
                new ResponseEntity<>(resp, HttpStatus.OK);

        // Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(URI.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetTicketedUrlResponse>>any()))
                .thenReturn(responseEntity);

        TicketController ticketController =
                new TicketController(restTemplate, objectMapper, coaConfig);
        var out = ticketController.getTicketedUrl(req);
        Assertions.assertNotNull(out);
    }

    @Test
    public void getTicketTest() throws JsonProcessingException {
        var req = new GetTicketRequest();
        var resp = new GetTicketResponse();

        resp.setTicket("A");
        ResponseEntity<GetTicketResponse> responseEntity =
                new ResponseEntity<>(resp, HttpStatus.OK);

        // Set up to mock ords response
        when(restTemplate.exchange(
                        Mockito.any(URI.class),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.<HttpEntity<String>>any(),
                        Mockito.<Class<GetTicketResponse>>any()))
                .thenReturn(responseEntity);

        TicketController ticketController =
                new TicketController(restTemplate, objectMapper, coaConfig);
        var out = ticketController.getTicket(req);
        Assertions.assertNotNull(out);
    }
}
