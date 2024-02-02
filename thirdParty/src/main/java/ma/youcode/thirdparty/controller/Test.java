package ma.youcode.thirdparty.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/test")
public class Test {
    @GetMapping
    public ResponseEntity<String> testApi(){
        return ResponseEntity.ok("secured end point");
    }
}
