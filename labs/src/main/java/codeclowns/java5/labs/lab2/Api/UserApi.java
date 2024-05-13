package codeclowns.java5.labs.lab2.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/lab2")
public class UserApi {
    @GetMapping("/getUserInfo/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable("userId") String userId) {
        var result = new HashMap<String, Object>();
        if (userId.equals("PhucHau")) {
            result.put("userId", userId);
            result.put("username", "hautran");
            result.put("email", "hautran@example.com");
        } else {
            result.put("message", "User not found");
        }
        return ResponseEntity.ok(result);
    }
}
