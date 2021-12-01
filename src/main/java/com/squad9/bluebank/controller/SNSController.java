package com.squad9.bluebank.controller;

import com.squad9.bluebank.service.SNSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.squad9.bluebank.utils.formatadorStringJson.formataUmRetornoGenerico;

@RequestMapping(value = "api/sns")
@RestController
public class SNSController {

    private SNSService snsService;

    @Autowired
    public SNSController(SNSService snsService) {
        this.snsService = snsService;
    }

    @PostMapping(value = "/subscricao/{email}")
    public ResponseEntity<?> addSubscription(@PathVariable String email) {
        try {
            String msg = "Subscription request está pendente. Para confirmar, verifique seu email: " + email;
            snsService.addSubscricao(email);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(formataUmRetornoGenerico("msg", msg));
        } catch (Exception error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(formataUmRetornoGenerico("error", error.getMessage()));
        }
    }
}
