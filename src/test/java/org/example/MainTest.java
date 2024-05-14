package org.example;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static j2html.TagCreator.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    /**
     * Basic HTML Tags: Test generating each type of HTML tag (e.g., <p>, <div>, <span>, etc.).
     * Attributes: Test adding attributes to tags (e.g., id, class, data-* attributes).
     * Nesting: Test the correct nesting of tags.
     * Content Safety: Test to ensure that text content is properly escaped to prevent XSS (Cross-Site Scripting).
     * Special Cases: Test edge cases like empty elements, self-closing tags, and incorrect attribute values.
     */

    @Test
    void generateHtml() {
        String expectedHtml = "<html><head><title>J2HTML Example</title></head><body><h1>Hello, J2HTML!</h1><p>This is an example of using J2HTML in a Maven project.</p></body></html>";

        // When
        String generatedHtml = Main.generateHtml();

        // Then
        assertEquals(expectedHtml, generatedHtml);
        System.out.println("test");
    }
    @Test
    public void testDivTag() {
        String expectedHtml = "<div>This is a div.</div>";
        String actualHtml = div("This is a div.").render();

        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    //Basic Content Test
    public void testDivWithSimpleText() {
        String result = div("Simple Text").render();
        assertEquals("<div>Simple Text</div>", result);
    }
    @Test
    //Empty Div Test
    public void testEmptyDiv() {
        String result = div().render();
        assertEquals("<div></div>", result);
    }
    //Div With Class Attribute
    @Test
    public void testDivWithClass() {
        String result = div().withClass("container").render();
        assertEquals("<div class=\"container\"></div>", result);
    }
    //Div With Id Attribute
    @Test
    public void testDivWithId() {
        String result = div().withId("main").render();
        assertEquals("<div id=\"main\"></div>", result);
    }

    //Div With Multiple Classes
    @Test
    public void testDivWithMultipleClasses() {
        String result = div().withClass("container fluid").render();
        assertEquals("<div class=\"container fluid\"></div>", result);
    }

    //Div With Style
    @Test
    public void testDivWithStyle() {
        String result = div().withStyle("color: red;").render();
        assertEquals("<div style=\"color: red;\"></div>", result);
    }

    //Nested Divs
    @Test
    public void testNestedDivs() {
        String result = div(
                div("Inner")
        ).render();
        assertEquals("<div><div>Inner</div></div>", result);
    }
    //Div With Data Attribute
    @Test
    public void testDivWithDataAttribute() {
        String result = div().attr("data-value", "123").render();
        assertEquals("<div data-value=\"123\"></div>", result);
    }
   //Div With Arbitrary Attribute
   @Test
   public void testDivWithArbitraryAttribute() {
       String result = div().attr("custom-attr", "value").render();
       assertEquals("<div custom-attr=\"value\"></div>", result);
   }
    //Div Containing Image Tag
    @Test
    public void testDivContainingImage() {
        String result = div(img().withSrc("image.jpg")).render();
        assertEquals("<div><img src=\"image.jpg\"></div>", result);
    }
   // Div Containing Link and Text:
   @Test
   public void testDivContainingLinkAndText() {
       String result = div(
               text("Visit "),
               a("Google").withHref("http://google.com")
       ).render();
       assertEquals("<div>Visit <a href=\"http://google.com\">Google</a></div>", result);
   }
   //Div With Special Characters
   @Test
   public void testDivWithSpecialCharacters() {
       String result = div("<script>alert('xss');</script>").render();
       System.out.println(result);
       assertEquals("<div>&lt;script&gt;alert(&#x27;xss&#x27;);&lt;/script&gt;</div>", result);
   }
   //Div With Click Event
   @Test
   public void testDivWithOnclick() {
       String result = div("Clickable").attr("onclick", "console.log('clicked');").render();
       assertEquals("<div onclick=\"console.log(&#x27;clicked&#x27;);\">Clickable</div>", result);
   }

   //Div with Nested List
   @Test
   public void testDivWithNestedList() {
       String result = div(
               ul(
                       li("Item 1"),
                       li("Item 2")
               )
       ).render();
       assertEquals("<div><ul><li>Item 1</li><li>Item 2</li></ul></div>", result);
   }
   //Div with Form Elements
   @Test
   public void testDivWithFormElements() {
       String result = div(
               form().withMethod("post").with(
                       input().withType("text"),
                       input().withType("submit")
               )
       ).render();
       assertEquals("<div><form method=\"post\"><input type=\"text\"><input type=\"submit\"></form></div>", result);
   }
   //Div With Role Attribute
   @Test
   public void testDivWithRole() {
       String result = div().attr("role", "banner").render();
       assertEquals("<div role=\"banner\"></div>", result);
   }
   //Div with Multiple Nested Elements
   @Test
   public void testDivWithMultipleNestedElements() {
       String result = div(
               p("Paragraph inside div."),
               a("Link").withHref("#"),
               span("Span inside div.")
       ).render();
       assertEquals("<div><p>Paragraph inside div.</p><a href=\"#\">Link</a><span>Span inside div.</span></div>", result);
   }
 //Div with Multiple Attributes
    @Test
    public void testDivWithMultipleAttributes() {
        String result = div().withClass("foo").withId("bar").withStyle("color: red;").render();
        assertEquals("<div class=\"foo\" id=\"bar\" style=\"color: red;\"></div>", result);
    }


























    @Test
    public void testSpanTag() {
        String expectedHtml = "<span>This is a span.</span>";
        String actualHtml = span("This is a span.").render();
        assertEquals(expectedHtml, actualHtml);
    }


    @Test
    void createParagraphTag() {
        String expectedHtml = "<p>This is a paragraph.</p>";
        String actualHtml = p("This is a paragraph.").render();
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testAnchorTag() {
        String expectedHtml = "<a href=\"https://example.com\">Visit Example</a>";
        String actualHtml = a("Visit Example").withHref("https://example.com").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testUnorderedListTag() {
        String expectedHtml = "<ul><li>Item 1</li><li>Item 2</li><li>Item 3</li></ul>";
        String actualHtml = ul(
                li("Item 1"),
                li("Item 2"),
                li("Item 3")
        ).render();
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testHeaderTags() {
        assertEquals("<h1>Header 1</h1>", h1("Header 1").render());
        assertEquals("<h2>Header 2</h2>", h2("Header 2").render());
        assertEquals("<h3>Header 3</h3>", h3("Header 3").render());
        // Continue for h4, h5, h6
    }

    @Test
    public void testImageTag() {
        String expectedHtml = "<img src=\"image.jpg\" alt=\"An image\" width=\"500\" height=\"300\">";
        String actualHtml = img().withSrc("image.jpg").withAlt("An image").withWidth("500").withHeight("300").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testFormElements() {
        String expectedForm = "<form method=\"post\" action=\"submit.php\"><input type=\"text\" name=\"username\" required></form>";
        String actualForm = form().withMethod("post").withAction("submit.php")
                .with(input().withType("text").withName("username").isRequired()).render();
        assertEquals(expectedForm, actualForm);
    }

    @Test
    public void testTable() {
        String expectedTable = "<table><tr><th>Header</th></tr><tr><td>Data</td></tr></table>";
        String actualTable = table(
                tr(th("Header")),
                tr(td("Data"))
        ).render();
        assertEquals(expectedTable, actualTable);
    }
    @Test
    public void testLinksWithAttributes() {
        String expectedHtml = "<a href=\"https://example.com\" target=\"_blank\">Visit Example</a>";
        String actualHtml = a("Visit Example").withHref("https://example.com").withTarget("_blank").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testOrderedList() {
        String expectedHtml = "<ol><li>First item</li><li>Second item</li><li>Third item</li></ol>";
        String actualHtml = ol(li("First item"), li("Second item"), li("Third item")).render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testUnorderedList() {
        String expectedHtml = "<ul><li>Item one</li><li>Item two</li></ul>";
        String actualHtml = ul(li("Item one"), li("Item two")).render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testComplexTable() {
        String expectedHtml = "<table><thead><tr><th>Header 1</th><th>Header 2</th></tr></thead><tbody><tr><td>Data 1</td><td>Data 2</td></tr></tbody></table>";
        String actualHtml = table(
                thead(tr(th("Header 1"), th("Header 2"))),
                tbody(tr(td("Data 1"), td("Data 2")))
        ).render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testImageWithAttributes() {
        String expectedHtml = "<img src=\"logo.png\" alt=\"Company Logo\" width=\"200\" height=\"100\">";
        String actualHtml = img().withSrc("logo.png").withAlt("Company Logo").withWidth("200").withHeight("100").render();
        assertEquals(expectedHtml, actualHtml);
    }

    /**@Test
    public void testVideoWithAttributes() {
        String expectedHtml = "<video src=\"movie.mp4\" controls></video>";
        String actualHtml = video().withSrc("movie.mp4").withControls().render();
        assertEquals(expectedHtml, actualHtml);
    }**/
    @Test
    public void testIframe() {
        String expectedHtml = "<iframe src=\"https://example.com\" width=\"300\" height=\"200\"></iframe>";
        String actualHtml = iframe().withSrc("https://example.com").withWidth("300").withHeight("200").render();
        assertEquals(expectedHtml, actualHtml);
    }
   /** @Test
    public void testAudioWithAttributes() {
        String expectedHtml = "<audio controls><source src=\"audio.mp3\" type=\"audio/mpeg\"></audio>";
        String actualHtml = audio(
                source().withSrc("audio.mp3").withType("audio/mpeg")
        ).attr("controls", "true").render(); // Using attr to manually add controls
        assertEquals(expectedHtml, actualHtml);
    }**/

    @Test
    public void testMetaTags() {
        String expectedHtml = "<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        String actualHtml = meta().withCharset("UTF-8").render() +
                meta().withName("viewport").withContent("width=device-width, initial-scale=1.0").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testMultipleInputForms() {
        String expectedHtml = "<form method=\"post\" action=\"login.php\"><input type=\"text\" name=\"username\"><input type=\"password\" name=\"password\"><input type=\"submit\" value=\"Login\"></form>";
        String actualHtml = form().withMethod("post").withAction("login.php")
                .with(input().withType("text").withName("username"))
                .with(input().withType("password").withName("password"))
                .with(input().withType("submit").withValue("Login")).render();
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testComplexForm() {
        String expectedHtml = "<form method=\"post\" action=\"submit.php\"><label for=\"name\">Name:</label><input type=\"text\" id=\"name\" name=\"name\"><label for=\"age\">Age:</label><input type=\"number\" id=\"age\" name=\"age\"><button type=\"submit\">Submit</button></form>";
        String actualHtml = form().withMethod("post").withAction("submit.php")
                .with(
                        label("Name:").withFor("name"),
                        input().withType("text").withId("name").withName("name"),
                        label("Age:").withFor("age"),
                        input().withType("number").withId("age").withName("age"),
                        button("Submit").withType("submit")
                ).render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testScriptTag() {
        String expectedHtml = "<script src=\"app.js\"></script>";
        String actualHtml = script().withSrc("app.js").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testAccessibilityWithAria() {
        String expectedHtml = "<button aria-label=\"Close\" onclick=\"closeModal()\">X</button>";
        String actualHtml = button("X").attr("aria-label", "Close").attr("onclick", "closeModal()").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testIframeWithSandbox() {
        String expectedHtml = "<iframe src=\"https://example.com\" sandbox=\"allow-scripts\"></iframe>";
        String actualHtml = iframe().withSrc("https://example.com").attr("sandbox", "allow-scripts").render();
        assertEquals(expectedHtml, actualHtml);
    }



    @Test
    public void testInvalidAttribute() {
        String result = a("Click here").attr("href", "invalidurl").render();
        assertTrue(result.contains("href=\"invalidurl\""));
    }
    @Test
    public void testImproperNesting() {
        String result = p(ul(li("Item"))).render();
        assertFalse(result.equals("<p><ul><li>Item</li></ul></p>")); // Expecting it to not be a valid nesting, depends on library behavior
    }

    @Test
    public void testSpecialCharactersEscape() {
        String result = p("Hello <script>alert('hack');</script>").render();
        assertFalse(result.contains("<script>"));
    }
    @Test
    public void testEmptyElement() {
        String result = img().render();
        assertTrue(result.equals("<img>")); // Test based on expected behavior; some tags might need attributes like src
    }

    @Test
    public void performanceLargeHtmlGeneration() {
        long startTime = System.currentTimeMillis();
        StringBuilder largeHtml = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeHtml.append(p("Paragraph " + i).render());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time for generating large HTML: " + (endTime - startTime) + " ms");
        assertNotNull(largeHtml.toString());
    }

    @Test
    public void testConcurrentHtmlGeneration() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tasks.add(() -> div("Content " + Thread.currentThread().getId()).render());
        }
        try {
            List<Future<String>> results = service.invokeAll(tasks);
            for (Future<String> result : results) {
                assertNotNull(result.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }
    }

    @Test
    public void testAttributeOverriding() {
        String result = a("Link").withHref("http://example1.com").withHref("http://example2.com").render();
        assertTrue(result.contains("href=\"http://example2.com\""));
    }
    @Test
    public void testBooleanAttributes() {
        String result = input().withType("checkbox").isChecked().render();
        assertTrue(result.contains("checked"));
    }

    @Test
    public void testCssStyle() {
        String result = p("Text").withStyle("color: red;").render();
        assertTrue(result.contains("style=\"color: red;\""));
    }

    @Test
    public void testInlineJavaScript() {
        String result = button("Click me").attr("onclick", "alert('Clicked!');").render();
        assertTrue(result.contains("onclick=\"alert('Clicked!');\""));
    }

    @Test
    public void testInlineJavaScript2() {
        String result = button("Click me").attr("onclick", "alert('Clicked!');").render();
        // Adjust the assertion to check for the HTML entity encoded version of the quotes
        assertTrue(result.contains("onclick=\"alert(&#x27;Clicked!&#x27;);\""));
    }


    @Test
    public void testSelfClosingTags() {
        String result = div(img().render() + br().render()).render();
        System.out.println(result);
        assertTrue(result.contains("<img>") && result.contains("<br>"));
    }
    @Test
    public void testHtmlEscaping() {
        String result = p("<script>alert('xss');</script>").render();
        assertFalse(result.contains("<script>"));
    }

















}