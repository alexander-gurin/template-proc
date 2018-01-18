import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import process.Processor;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class)
public class AppTests {

    @Autowired
    private Processor processor;

    @Test
    public void testProcessor() throws Exception {
        String tmpl = processor.getTemplateByPhrase("sll вд  !No#%.,:;?\\/()+-“”―_'\"`&^?{}[]<>/\\|!@#$%^()+=~* gd Ne oplachivat");

        assertNotNull(tmpl);
    }
}
