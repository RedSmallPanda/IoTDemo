package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
public class controller {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String  testget(@RequestParam(value = "id") String id){
        return "test"+id;
    }

}
