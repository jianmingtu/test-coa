package ca.bc.gov.open.coa.comparison.services;

import ca.bc.gov.open.coa.comparison.config.DualProtocolSaajSoapMessageFactory;
import ca.bc.gov.open.coa.comparison.config.WebServiceSenderWithAuth;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import ca.bc.gov.open.coa.one.*;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.changetype.container.ListChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

@Service
public class TestService {
    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    private final WebServiceSenderWithAuth webServiceSenderWithAuth;

    private final Javers javers = JaversBuilder.javers().build();

    @Autowired
    public TestService(WebServiceSenderWithAuth webServiceSenderWithAuth) {
        this.webServiceSenderWithAuth = webServiceSenderWithAuth;
    }

    @Value("${host.api-host}")
    private String apiHost;

    @Value("${host.wm-host}")
    private String wmHost;

    @Value("${host.username}")
    private String username;

    @Value("${host.password}")
    private String password;

    private PrintWriter fileOutput;
    private static String outputDir = "comparison-tool/results/";

    private int overallDiff = 0;

    public void runCompares() throws IOException {
        System.out.println("INFO: COA Diff testing started");

        getFileMimeCompare();
        getFileSizeCompare();

        getTicketCompare();
        getTicketedUrlCompare();

        getDocumentUploadStateCompare();
    }

    private void getDocumentUploadStateCompare() throws FileNotFoundException, UnsupportedEncodingException {
        int diffCounter = 0;

        GetDocumentUploadStateRequest request = new GetDocumentUploadStateRequest();

        InputStream inputIds = getClass().getResourceAsStream("/getDocumentUploadStateDocumentGUID.csv");
        assert inputIds != null;
        Scanner scanner = new Scanner(inputIds);

        fileOutput = new PrintWriter(outputDir + " GetDocumentUploadState.txt", "UTF-8");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            request.setDocumentGUID(line);

            System.out.println(
                    "\nINFO: GetDocumentUploadState with DocumentGUID: "
                            + line);

            String[] contextPath = {"ca.bc.gov.open.coa.one"};

            if (!compare(new GetDocumentUploadStateResponse(), request, contextPath)) {
                fileOutput.println(
                        "INFO: GetDocumentUploadState with DocumentGUID: "
                                + line
                                + "\n\n");
                ++diffCounter;
            }
        }

        System.out.println(
                "########################################################\n"
                        + "INFO: GetDocumentUploadState Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: GetDocumentUploadState Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    private void getTicketedUrlCompare() throws FileNotFoundException, UnsupportedEncodingException {
        int diffCounter = 0;

        GetTicketedUrlRequest request = new GetTicketedUrlRequest();

        InputStream inputIds = getClass().getResourceAsStream("/getTicketedUrlDocumentGUID.csv");
        assert inputIds != null;
        Scanner scanner = new Scanner(inputIds);

        fileOutput = new PrintWriter(outputDir + "GetTicketedUrl.txt", "UTF-8");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            request.setDocumentGUID(line);

            System.out.println(
                    "\nINFO: GetTicketedUrl with DocumentGUID: "
                            + line);

            String[] contextPath = {"ca.bc.gov.open.coa.one"};

            if (!compare(new GetTicketedUrlResponse(), request, contextPath)) {
                fileOutput.println(
                        "INFO: GetTicketedUrl with DocumentGUID: "
                                + line
                                + "\n\n");
                ++diffCounter;
            }
        }

        System.out.println(
                "########################################################\n"
                        + "INFO: GetTicketedUrl Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: GetTicketedUrl Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    private void getTicketCompare() throws FileNotFoundException, UnsupportedEncodingException {
        int diffCounter = 0;

        GetTicketRequest request = new GetTicketRequest();

        InputStream inputIds = getClass().getResourceAsStream("/getTicketDocumentGUID.csv");
        assert inputIds != null;
        Scanner scanner = new Scanner(inputIds);

        fileOutput = new PrintWriter(outputDir + "GetTicket.txt", "UTF-8");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            request.setDocumentGUID(line);

            System.out.println(
                    "\nINFO: GetTicket with DocumentGUID: "
                            + line);

            String[] contextPath = {"ca.bc.gov.open.coa.one"};

            if (!compare(new GetTicketResponse(), request, contextPath)) {
                fileOutput.println(
                        "INFO: GetTicket with DocumentGUID: "
                                + line
                                + "\n\n");
                ++diffCounter;
            }
        }

        System.out.println(
                "########################################################\n"
                        + "INFO: GetTicket Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: GetTicket Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    private void getFileSizeCompare() throws FileNotFoundException, UnsupportedEncodingException {
        int diffCounter = 0;

        GetFileSizeRequest request = new GetFileSizeRequest();

        InputStream inputIds = getClass().getResourceAsStream("/getFileSizeDocumentGUID.csv");
        assert inputIds != null;
        Scanner scanner = new Scanner(inputIds);

        fileOutput = new PrintWriter(outputDir + "GetFileSize.txt", "UTF-8");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            request.setDocumentGUID(line);

            System.out.println(
                    "\nINFO: GetFileSize with DocumentGUID: "
                            + line);

            String[] contextPath = {"ca.bc.gov.open.coa.one"};

            if (!compare(new GetFileSizeResponse(), request, contextPath)) {
                fileOutput.println(
                        "INFO: GetFileSize with DocumentGUID: "
                                + line
                                + "\n\n");
                ++diffCounter;
            }
        }

        System.out.println(
                "########################################################\n"
                        + "INFO: GetFileSize Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: GetFileSize Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    private void getFileMimeCompare() throws FileNotFoundException, UnsupportedEncodingException {
        int diffCounter = 0;

        GetFileMimeRequest request = new GetFileMimeRequest();

        InputStream inputIds = getClass().getResourceAsStream("/getFileMimeDocumentGUID.csv");
        assert inputIds != null;
        Scanner scanner = new Scanner(inputIds);

        fileOutput = new PrintWriter(outputDir + "GetFileMime.txt", "UTF-8");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            request.setDocumentGUID(line);

            System.out.println(
                    "\nINFO: GetFileMime with DocumentGUID: "
                            + line);

            String[] contextPath = {"ca.bc.gov.open.coa.one"};

            if (!compare(new GetFileMimeResponse(), request, contextPath)) {
                fileOutput.println(
                        "INFO: GetFileMime with DocumentGUID: "
                                + line
                                + "\n\n");
                ++diffCounter;
            }
        }

        System.out.println(
                "########################################################\n"
                        + "INFO: GetFileMime Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        fileOutput.println(
                "########################################################\n"
                        + "INFO: GetFileMime Completed there are "
                        + diffCounter
                        + " diffs\n"
                        + "########################################################");

        overallDiff += diffCounter;
        fileOutput.close();
    }

    public <T, G> boolean compare(T response, G request, String[] contextPath) {

        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

        DualProtocolSaajSoapMessageFactory saajSoapMessageFactory =
                new DualProtocolSaajSoapMessageFactory();
        saajSoapMessageFactory.setSoapVersion(SoapVersion.SOAP_11);
        saajSoapMessageFactory.afterPropertiesSet();

        webServiceTemplate.setMessageSender(webServiceSenderWithAuth);
        webServiceTemplate.setMessageFactory(saajSoapMessageFactory);
        jaxb2Marshaller.setContextPaths(contextPath);
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        webServiceTemplate.afterPropertiesSet();

        T resultObjectWM = null;
        T resultObjectAPI = null;

        try {
            resultObjectWM = (T) webServiceTemplate.marshalSendAndReceive(wmHost, request);
            resultObjectAPI = (T) webServiceTemplate.marshalSendAndReceive(apiHost, request);
            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("ERROR: Failed to send request... " + e);
            fileOutput.println("ERROR: Failed to send request... " + e);
        }

        Diff diff = javers.compare(resultObjectAPI, resultObjectWM);

        String responseClassName = response.getClass().getName();
        if (diff.hasChanges()) {
            printDiff(diff);
            return false;
        } else {
            if (resultObjectAPI == null && resultObjectWM == null)
                System.out.println(
                        "WARN: "
                                + responseClassName.substring(
                                        responseClassName.lastIndexOf('.') + 1)
                                + ": NULL responses");
            else
                System.out.println(
                        "INFO: "
                                + responseClassName.substring(
                                        responseClassName.lastIndexOf('.') + 1)
                                + ": No Diff Detected");
            return true;
        }
    }

    private void printDiff(Diff diff) {
        List<Change> actualChanges = new ArrayList<>(diff.getChanges());

        actualChanges.removeIf(
                c -> {
                    if (c instanceof ValueChange) {
                        return ((ValueChange) c).getLeft() == null
                                && ((ValueChange) c).getRight().toString().isBlank();
                    }

                    return false;
                });

        int diffSize = actualChanges.size();

        if (diffSize == 0) {
            System.out.println("Only null and blank changes detected");
            return;
        }

        String[] header = new String[] {"Property", "API Response", "WM Response"};
        String[][] table = new String[diffSize + 1][3];
        table[0] = header;

        for (int i = 0; i < diffSize; ++i) {
            Change ch = diff.getChanges().get(i);

            if (ch instanceof ListChange) {
                String apiVal =
                        ((ListChange) ch).getLeft() == null
                                ? "null"
                                : ((ListChange) ch).getLeft().toString();
                String wmVal =
                        ((ListChange) ch).getRight() == null
                                ? "null"
                                : ((ListChange) ch).getRight().toString();
                table[i + 1][0] = ((ListChange) ch).getPropertyNameWithPath();
                table[i + 1][1] = apiVal;
                table[i + 1][2] = wmVal;
            } else if (ch instanceof ValueChange) {
                String apiVal =
                        ((ValueChange) ch).getLeft() == null
                                ? "null"
                                : ((ValueChange) ch).getLeft().toString();
                String wmVal =
                        ((ValueChange) ch).getRight() == null
                                ? "null"
                                : ((ValueChange) ch).getRight().toString();
                table[i + 1][0] = ((ValueChange) ch).getPropertyNameWithPath();
                table[i + 1][1] = apiVal;
                table[i + 1][2] = wmVal;
            }
        }

        boolean leftJustifiedRows = false;
        int totalColumnLength = 10;
        /*
         * Calculate appropriate Length of each column by looking at width of data in
         * each column.
         *
         * Map columnLengths is <column_number, column_length>
         */
        Map<Integer, Integer> columnLengths = new HashMap<>();
        Arrays.stream(table)
                .forEach(
                        a ->
                                Stream.iterate(0, (i -> i < a.length), (i -> ++i))
                                        .forEach(
                                                i -> {
                                                    if (columnLengths.get(i) == null) {
                                                        columnLengths.put(i, 0);
                                                    }
                                                    if (columnLengths.get(i) < a[i].length()) {
                                                        columnLengths.put(i, a[i].length());
                                                    }
                                                }));

        for (Map.Entry<Integer, Integer> e : columnLengths.entrySet()) {
            totalColumnLength += e.getValue();
        }
        fileOutput.println("=".repeat(totalColumnLength));
        System.out.println("=".repeat(totalColumnLength));

        final StringBuilder formatString = new StringBuilder("");
        String flag = leftJustifiedRows ? "-" : "";
        columnLengths.entrySet().stream()
                .forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
        formatString.append("|\n");

        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(
                        a -> {
                            fileOutput.printf(formatString.toString(), table[a]);
                            System.out.printf(formatString.toString(), table[a]);
                        });

        fileOutput.println("=".repeat(totalColumnLength));
        System.out.println("=".repeat(totalColumnLength));
    }
}
