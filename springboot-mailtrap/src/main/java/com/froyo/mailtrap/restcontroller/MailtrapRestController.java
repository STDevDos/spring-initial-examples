package com.froyo.mailtrap.restcontroller;

import com.froyo.mailtrap.dto.FeedbackDTO;
import com.froyo.mailtrap.dto.inout.SendFeedbackResponse;
import com.froyo.mailtrap.service.MailtrapService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class MailtrapRestController {

    private final MailtrapService mailtrapService;

    @PostMapping("/sendFeedback")
    public SendFeedbackResponse sendFeedback(@RequestBody @Valid FeedbackDTO feedbackDTO) {
        return mailtrapService.sendFeedback(feedbackDTO);
    }

    @GetMapping("/devtools")
    public String devtools() {
        return "success mil mascaras";
    }

    @GetMapping("/devtools2")
    public String devtools2() {
        return "success seis";
    }


}
