package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import process.Processor;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private Processor processor;

    @RequestMapping(value="/find/template", params = "phrase", produces="application/json", method=RequestMethod.GET)
    @ResponseBody
    public List<String> getTemplateByPhrase(@RequestParam("phrase") String phrase) throws Exception {
         return processor.getTemplateByPhrase(phrase);
    }
}
