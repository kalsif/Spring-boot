package com.example.esercizio02;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CiaoController {
    @GetMapping(path = "/ciao/{provincia}")
    public User ciao(
            @PathVariable String provincia,
            @RequestParam(required = false) String nome

    ){
        String saluto = "Ciao " + nome + ", com'è il tempo in " + provincia + " ?";
        return new User(nome,provincia,saluto);
    }


}
