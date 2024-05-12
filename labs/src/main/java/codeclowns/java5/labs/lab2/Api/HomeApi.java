package codeclowns.java5.labs.lab2.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lab2")
public class HomeApi {
    @GetMapping("/getInformation")
    public ResponseEntity<?> doGetInformation(@RequestParam("ten") String name,
                                              @RequestParam("tuoi") String age) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("ten", name);
        result.put("tuoi", age);
        result.put("lop", "Java5");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/archiveLoginCredentials")
    public ResponseEntity<?> doPostArchiveLoginCredentials(
            @RequestBody String secretKey
    ) {
        var result = new HashMap<String, Object>();
        if (secretKey.equals("Khoadz")) {
            result.put("username", "codeclowns");
            result.put("password", "123");
        } else {
            result = new HashMap<String, Object>();
            result.put("message", "invalid token");
        }
        return ResponseEntity.ok(result);
    }
}
