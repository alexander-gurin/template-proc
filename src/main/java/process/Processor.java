package process;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class Processor {

    @Value("${file-name}")
    private String fileName;

    @Value("${operand-pattern}")
    private String operandPattern;

    @Value("${finish-pattern}")
    private String finishPattern;

    private Map<String, Pattern> templates = new HashMap<>();

    public String getTemplateByPhrase(String phrase) throws Exception {
        if (!templates.isEmpty()){
            return templates.entrySet()
                              .stream()
                              .filter(e -> e.getValue().matcher(phrase).matches())
                              .findAny().orElse(null).getKey(); //Спасибо за напоминание Сергею из WhiteSky
        } else {
            throw new Exception("Template list is empty");
        }
    }

    @PostConstruct
    private void readTemplateList() {
        try {
            Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).toURI());

            Files.lines(path).forEach(l -> templates.put(l, Pattern.compile(l.replaceAll(operandPattern, finishPattern))));

            System.out.println("Templates was loaded with success!!!");
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
