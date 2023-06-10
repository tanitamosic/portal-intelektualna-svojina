package xml.z1.Z1.exist;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

}
