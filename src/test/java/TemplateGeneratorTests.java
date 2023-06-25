import org.example.TemplateGenerator;
import org.junit.Assert;
import org.junit.Test;


public class TemplateGeneratorTests {

    TemplateGenerator templateGenerator = new TemplateGenerator();
    @Test
    public void checkMethodSubject() {

   templateGenerator.setSubject("Tester1");
        Assert.assertSame(templateGenerator.getSubject(),"Tester1");
    }

    }


