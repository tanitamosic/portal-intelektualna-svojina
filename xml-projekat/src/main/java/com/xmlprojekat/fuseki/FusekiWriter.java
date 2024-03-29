package com.xmlprojekat.fuseki;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FusekiWriter {

    private static final String RDF_FILE = "src/main/rdf/metadata.rdf";

    private static final String GRAPH_URI="Metadata";

    public static void saveRDF() throws IOException {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        Model model = ModelFactory.createDefaultModel();
        model.read(RDF_FILE);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        model.write(out, SparqlUtil.NTRIPLES);
        model.write(System.out, SparqlUtil.RDF_XML);

        UpdateRequest request = UpdateFactory.create();
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(request, conn.updateEndpoint);
        processor.execute();
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint+"/"+GRAPH_URI, out.toString());
        System.out.println(sparqlUpdate);

        UpdateRequest update = UpdateFactory.create(sparqlUpdate);
        processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();


    }
}
