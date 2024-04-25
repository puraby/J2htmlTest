package org.example;

import org.junit.jupiter.api.Test;
import static j2html.TagCreator.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void generateHtml() {
        String expectedHtml = "<html><head><title>J2HTML Example</title></head><body><h1>Hello, J2HTML!</h1><p>This is an example of using J2HTML in a Maven project.</p></body></html>";

        // When
        String generatedHtml = Main.generateHtml();

        // Then
        assertEquals(expectedHtml, generatedHtml);
    }
    @Test
    public void testDivTag() {
        String expectedHtml = "<div>This is a div.</div>";
        String actualHtml = div("This is a div.").render();
        assertEquals(expectedHtml, actualHtml);
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




}