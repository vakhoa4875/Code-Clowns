package codeclowns.java5.labs.lab2.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lab2")
public class HomeApi {
    @GetMapping("/getInformation")
    public ResponseEntity<?> doGetInformation(@RequestParam("ten")String name,
                                              @RequestParam("tuoi")String age){
       Map<String,String> result = new HashMap<String,String>();
       result.put("ten",name);
       result.put("tuoi",age);
       result.put("lop","Java5");
       return ResponseEntity.ok(result);
    }
}
