package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import process.Processor;

@RestController
public class Controller {

    @Autowired
    private Processor processor;

    @RequestMapping(value="/find-template", params = "phrase", produces="application/json", method=RequestMethod.GET)
    @ResponseBody
    public Response getTemplateByPhrase(@RequestParam("phrase") String phrase) throws Exception {
        String tmpl = processor.getTemplateByPhrase(phrase);

        if (tmpl != null) {
            return new Response(1, tmpl);
        }
        return new Response(0, null);
    }
}
