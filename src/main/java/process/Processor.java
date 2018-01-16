package process;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class Processor {

    @Value("${file-name}")
    private String fileName;

    //@Value("${operand-pattern}")
    private String operandPattern = "%w+";

    //@Value("${finish-pattern}")
    private String finishPattern = "\\w+|\\W+|\\d+";

    private List<Pattern> templateList;

    public List<String> getTemplateByPhrase(String phrase) throws Exception {
        if (!templateList.isEmpty()){
            return templateList
                         .stream()
                         .filter(tmpl -> tmpl.matcher(phrase).matches())
                         .map(Pattern::toString)
                         .map(line -> line.replaceAll(finishPattern, operandPattern))
                         .collect(Collectors.toList());
        } else {
            throw new Exception("Template list is empty");
        }
    }

    @PostConstruct
    private void readTemplateList() {
        try {
            Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());
            templateList = Files
                              .lines(path)
                              .map(line -> line.replaceAll(operandPattern, finishPattern))
                              .map(Pattern::compile)
                              .collect(Collectors.toList());

            System.out.println("Templates was loaded with success!!!");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
