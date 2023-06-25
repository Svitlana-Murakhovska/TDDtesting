package org.example;

import org.testng.annotations.Test;

import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import java.util.Map;


import static org.testng.AssertJUnit.assertEquals;

public class TemplateGenerator {
    String subject = "TestSubject";
    public void setSubject(String s){
        subject = s;
    }

    public String getSubject(){
        return subject;
    }

    public String generate(String template, Map<String, String> runtimeValues) {
        for (Map.Entry<String, String> entry : runtimeValues.entrySet()) {
            String placeholder = "#{".concat(entry.getKey()).concat("}");
            String value = entry.getValue();
            template = template.replace(placeholder, value);
        }
        return template;
    }


    @Test
    public void testSupportValuesWithDynamicPlaceholders() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "Hello #{name}! How are you #{time}?";
        Map<String, String> variables = new HashMap<>();
        variables.put("#{name}", "John");
        variables.put("#{time}", "today");
        String expected = "Hello #{name}! How are you #{time}?";
        String actual = generator.generate(template, variables);
        assertEquals(expected, actual);
    }
//    @Test(expected = MissingPlaceholderValueException.class)
//    public void testMissingPlaceholderValueException() {
//        TemplateGenerator generator = new TemplateGenerator();
//        String template = "Hello, #{name}! Welcome to our website.";
//        Map<String, String> runtimeValues = new HashMap<>();
//
//        generator.generate(template, runtimeValues);
//    }

    @Test
    public void testIgnoreValuesNotFoundInTemplate() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "Hello, #{name}! Welcome to our website.";
        Map<String, String> runtimeValues = new HashMap<>();
        runtimeValues.put("name", "John");
        runtimeValues.put("age", "25");

        String result = generator.generate(template, runtimeValues);

        assertEquals("Hello, John! Welcome to our website.", result);
    }

    @Test
    public void testSupportValuesWithHashTagInRuntime() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "Please click the link: #{link}";
        Map<String, String> runtimeValues = new HashMap<>();
        runtimeValues.put("link", "#{tag}");

        String result = generator.generate(template, runtimeValues);

        assertEquals("Please click the link: #{tag}", result);
    }
    @Test
    public void testSupportFullLatin1CharacterSet() {
        TemplateGenerator generator = new TemplateGenerator();
        String template = "This is a special character: #{character}";
        Map<String, String> runtimeValues = new HashMap<>();
        runtimeValues.put("character", "é");

        String result = generator.generate(template, runtimeValues);

        assertEquals("This is a special character: é", result);
    }




}


