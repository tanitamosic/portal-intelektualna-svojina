package xml.z1.Z1.fuseki;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FusekiWriter {

    private static final String GRAPH_URI="P1/Metadata";

    public static void saveRDF(String rdf_data) throws IOException {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        Model model = ModelFactory.createDefaultModel();
        model.read(rdf_data);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);
        model.write(System.out, SparqlUtil.RDF_XML);

        UpdateRequest request = UpdateFactory.create();
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
        processor.execute();
        String sparqlUpdate = SparqlUtil.replaceData(conn.dataEndpoint+"/"+GRAPH_URI, out.toString());
        System.out.println(sparqlUpdate);

        UpdateRequest update = UpdateFactory.create(sparqlUpdate);
        processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();
    }
}
