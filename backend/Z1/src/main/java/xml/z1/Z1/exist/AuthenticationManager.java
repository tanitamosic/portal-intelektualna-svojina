package xml.z1.Z1.exist;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.exist.xmldb.DatabaseImpl;

@Getter
@Setter
@Component
public class AuthenticationManager {

    @Value("${conn.username}")
    private String username;
    @Value("${conn.password}")
    private String password;
    @Value("${conn.host}")
    private String host;
    @Value("${conn.port}")
    private String port;
    @Value("${conn.driver}")
    private String driver;
    @Value("${conn.uri}")
    private String uri;

    public String getUser() {
        return "admin";
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = "localhost";
    }

    public String getPort() {
        return "8088";
    }

    public void setPort(String port) {
        this.port = "8088";
    }

    public String getDriver() {
        return "org.exist.xmldb.DatabaseImpl";
    }

    public void setDriver(String driver) {
        this.driver = "org.exist.xmldb.DatabaseImpl";
    }

    public String getUri() {
        return "xmldb:exist://localhost/8088/exist/xmlrpc";
    }

    public void setUri(String uri) {
        this.uri = "xmldb:exist://localhost/8088/exist/xmlrpc";
    }
//    public AuthenticationManager() {
//        this.user = "admin";
//        this.password = "";
//        this.host = "localhost";
//        this.port = "8088";
//        this.driver = "org.exist.xmldb.DatabaseImpl";
//        this.uri = "xmldb:exist://localhost/8088/exist/xmlrpc";
//    }
}
