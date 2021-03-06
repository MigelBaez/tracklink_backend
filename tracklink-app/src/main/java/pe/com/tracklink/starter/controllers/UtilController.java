package pe.com.tracklink.starter.controllers;

import org.springframework.data.mongodb.core.aggregation.ConvertOperators.Convert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api")
public class UtilController {

    @GetMapping("info")
    public ResponseEntity<String> getInfo() throws UnknownHostException {
    	 InetAddress inetAddress = InetAddress.getLocalHost();    
        return ResponseEntity.ok("Ip: "+ inetAddress.getHostAddress() + " Hostname: " +inetAddress.getHostName());
    }

    @GetMapping("multiplications/{x}/{y}")
    public ResponseEntity<String> getOperations(@PathVariable String x,@PathVariable String y) {
      
    	Integer ix = Integer.parseInt(x);
    	Integer iy = Integer.parseInt(y);
    	Integer result = ix * iy;
        return ResponseEntity.ok(result.toString());
    }

}
