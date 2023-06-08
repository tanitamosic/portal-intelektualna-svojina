package xml.z1.Z1.fuseki;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Component
public class FusekiReader {

    private FusekiReader() {
    }

    //                                              "<%hobby>", "chess"
    public static ArrayList<String> executeQuery(Map<String,String> params, String rq_path) throws IOException {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        String sparqlQueryTemplate = readFile(rq_path, StandardCharsets.UTF_8);
        String sparqlQuery = SparqlUtil.replaceParameters(sparqlQueryTemplate, params);
        System.out.println("Query: "+sparqlQuery);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        String varName;
        RDFNode varValue;
        ArrayList<String> result = new ArrayList<>();
        while (results.hasNext()) {
            QuerySolution solution = results.next();
            Iterator<String> variableBindings = solution.varNames();
            while (variableBindings.hasNext()) {
                varName = variableBindings.next();
                varValue = solution.get(varName);
                System.out.println(varName + ": " + varValue);
                result.add(varName + ": " + varValue);
            }
        }
        ResultSetFormatter.outputAsXML(System.out, results);
        query.close();
        return result;

    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
