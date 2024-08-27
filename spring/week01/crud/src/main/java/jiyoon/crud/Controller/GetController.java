package jiyoon.crud.Controller;

import jiyoon.crud.Dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    @RequestMapping(value="/hello", method= RequestMethod.GET)
    public String getHello(){
        return "Hello World";
    }

    @GetMapping(value="/name")
    public String getName(){
        return "Flature";
    }

    @GetMapping(value="/variable1/{variable}")
    public String getVariable(@PathVariable String variable){
        return variable;
    }

    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization){
        return name + " " + email + " " + organization;
    }

    @GetMapping(value="/request2")
    public String getRequestParam2(@RequestParam Map<String,String> param){
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map->{
           sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    @GetMapping(value="/request3")
    public String getRequestParam3(MemberDto memberDto){
        return memberDto.toString();
    }
}
