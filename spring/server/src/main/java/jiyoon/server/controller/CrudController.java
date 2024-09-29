package jiyoon.server.controller;

import jiyoon.server.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/crud-api")
public class CrudController {

    @GetMapping
    public String getName() {
        return "Judy";
    }

    // GET '/api/v1/crud-api/{variable}'
    @GetMapping("/{variable}")
    public String getVariable(@PathVariable String variable) {
        return variable;
    }

    // GET '/api/v1/crud-api/param'
    @GetMapping("/param")
    public String getNameWithParam(@RequestParam String name) {
        return "Hello. " + name + "!";
    }

    // POST 'api/v1/crud-api'
    // 요청 본문에서 MemberDto 데이터를 받고, 쿼리 파라미터로 전달된
    // name, email, organization을 새 MemberDto 객체에 설정한 후 반환
    @PostMapping
    public ResponseEntity<MemberDto> getMember(
            @RequestBody MemberDto request,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization
    ){
        MemberDto memberDto = new MemberDto();
        memberDto.setName(name);
        memberDto.setEmail(email);
        memberDto.setOrganization(organization);
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    // POST '/api/v1/crud-api/add-header
    // 요청 헤더 my-header와 본문 MemberDto를 받아서 MemberDto 객체 반환
    @PostMapping("/add-header")
    public ResponseEntity<MemberDto> addHeader(@RequestHeader("my-header") String header,
                                               @RequestBody MemberDto memberDto) {
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

}
