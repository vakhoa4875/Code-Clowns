package codeclowns.java5.labs.lab2.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lab2")
public class BookApi {
   @RequestMapping("/books")
    ResponseEntity<?> doGetInfomationBooks(@RequestParam("id") String id,
                               @RequestParam("tenSach") String tenSach,
                               @RequestParam("gia") String gia)
    {
            Map<String,String> result= new HashMap<String,String>();
            result.put("id",id);
            result.put("tenSach",tenSach);
            result.put("gia",gia);

    return ResponseEntity.ok(result);

    }

    @PostMapping("/save")
    ResponseEntity<?> doSaveBook(@RequestBody String id)
    {
        var result = new HashMap<String, Object>();

        if(id!=null) {
            result.put("message", "Object saved successfully");
            result.put("username", "codeclowns");
            result.put("password", "123");
        }else{
            result.put("message", "Object saved fail");
        }
        return ResponseEntity.ok(result);
    }


}
