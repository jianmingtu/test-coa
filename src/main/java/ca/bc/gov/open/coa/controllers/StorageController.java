package ca.bc.gov.open.coa.controllers;

import ca.bc.gov.open.coa.configuration.SoapConfig;
import ca.bc.gov.open.coa.exceptions.ORDSException;
import ca.bc.gov.open.coa.models.OrdsErrorLog;
import ca.bc.gov.open.coa.models.RequestSuccessLog;
import ca.bc.gov.open.coa.one.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

@Slf4j
@Endpoint
public class StorageController {

    @Value("${coa.host}")
    private String host = "https://127.0.0.1/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public StorageController(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "getDocumentUploadStateRequest")
    @ResponsePayload
    public GetDocumentUploadStateResponse getDocumentUploadStateRequest(
            @RequestPayload GetDocumentUploadStateRequest search) throws JsonProcessingException {
        addEndpointHeader("GetDocumentUploadStateRequest");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + "court-list");

        try {
            HttpEntity<GetDocumentUploadStateResponse> resp =
                    restTemplate.exchange(
                            builder.build().encode().toUri(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            GetDocumentUploadStateResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog(
                                    "Request Success", "getDocumentUploadStateRequest")));
            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "GetDocumentUploadStateRequest",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "storeDocumentAsyncRequest")
    @ResponsePayload
    public StoreDocumentAsyncResponse storeDocumentAsyncRequest(
            @RequestPayload StoreDocumentAsyncRequest search) throws JsonProcessingException {
        addEndpointHeader("StoreDocumentAsyncRequest");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + "court-list");

        try {
            HttpEntity<StoreDocumentAsyncResponse> resp =
                    restTemplate.exchange(
                            builder.build().encode().toUri(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            StoreDocumentAsyncResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "StoreDocumentAsyncRequest")));
            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "StoreDocumentAsyncRequest",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    @PayloadRoot(namespace = SoapConfig.SOAP_NAMESPACE, localPart = "storeDocumentRequest")
    @ResponsePayload
    public StoreDocumentResponse storeDocumentRequest(@RequestPayload StoreDocumentRequest search)
            throws JsonProcessingException {
        addEndpointHeader("StoreDocumentResponse");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host + "court-list");

        try {
            HttpEntity<StoreDocumentResponse> resp =
                    restTemplate.exchange(
                            builder.build().encode().toUri(),
                            HttpMethod.GET,
                            new HttpEntity<>(new HttpHeaders()),
                            StoreDocumentResponse.class);

            log.info(
                    objectMapper.writeValueAsString(
                            new RequestSuccessLog("Request Success", "StoreDocumentRequest")));
            return resp.getBody();
        } catch (Exception ex) {
            log.error(
                    objectMapper.writeValueAsString(
                            new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "StoreDocumentRequest",
                                    ex.getMessage(),
                                    null)));
            throw new ORDSException();
        }
    }

    private void addEndpointHeader(String endpoint) {
        try {
            TransportContext context = TransportContextHolder.getTransportContext();
            HttpServletConnection connection = (HttpServletConnection) context.getConnection();
            connection.addResponseHeader("Endpoint", endpoint);
        } catch (Exception ex) {
            log.warn("Failed to add endpoint response header");
        }
    }
}
