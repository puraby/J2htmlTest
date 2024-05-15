package org.example;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import static j2html.TagCreator.*;
import static java.util.Optional.empty;
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
    /********************************************************************************
     * *****************************************************************************
     */
    /**Testing  Tag div Starts here **/

    @Test
    public void testEmptyDiv() {
        assertEquals("<div></div>", div().render());
    }

    @Test
    public void testDivWithClass() {
        assertEquals("<div class=\"container\"></div>", div().withClass("container").render());
    }

    @Test
    public void testDivWithId() {
        assertEquals("<div id=\"uniqueId\"></div>", div().withId("uniqueId").render());
    }

    @Test
    public void testDivWithTextContent() {
        assertEquals("<div>Hello, world!</div>", div("Hello, world!").render());
    }

    @Test
    public void testDivWithChildDiv() {
        assertEquals("<div><div>Nested</div></div>", div(div("Nested")).render());
    }

    @Test
    public void testDivWithMultipleClasses() {
        assertEquals("<div class=\"container main\"></div>", div().withClass("container main").render());
    }

    @Test
    public void testDivWithStyle() {
        assertEquals("<div style=\"color:red;\"></div>", div().withStyle("color:red;").render());
    }

    @Test
    public void testDivWithMultipleChildren() {
        assertEquals("<div><p>Paragraph</p><span>Span</span></div>", div(p("Paragraph"), span("Span")).render());
    }

    @Test
    public void testDivWithAttributes() {
        assertEquals("<div class=\"container\" id=\"main\" style=\"color:red;\"></div>", div().withClass("container").withId("main").withStyle("color:red;").render());
    }

    @Test
    public void testDivWithNestedStructure() {
        assertEquals("<div><div><span>Nested</span></div></div>", div(div(span("Nested"))).render());
    }

    @Test
    public void testDivWithComplexContent() {
        assertEquals("<div><h1>Title</h1><p>Text</p></div>", div(h1("Title"), p("Text")).render());
    }

    @Test
    public void testDivWithRoleAttribute() {
        assertEquals("<div role=\"main\"></div>", div().attr("role", "main").render());
    }

    @Test
    public void testDivWithAriaLabel() {
        assertEquals("<div aria-label=\"Description\"></div>", div().attr("aria-label", "Description").render());
    }

    @Test
    public void testDivWithOnClick() {
        assertEquals("<div onclick=\"alert(&#x27;Clicked&#x27;);\"></div>", div().attr("onclick", "alert('Clicked');").render());
    }

    @Test
    public void testDivWithDataAttribute() {
        assertEquals("<div data-type=\"info\"></div>", div().attr("data-type", "info").render());
    }

    @Test
    public void testDivWithAccessibilityFeatures() {
        assertEquals("<div aria-hidden=\"true\"></div>", div().attr("aria-hidden", "true").render());
    }

    @Test
    public void testDivWithComplexStyles() {
        assertEquals("<div style=\"color:red; margin-top:10px;\"></div>", div().withStyle("color:red; margin-top:10px;").render());
    }

    @Test
    public void testDivWithLangAttribute() {
        assertEquals("<div lang=\"en\"></div>", div().attr("lang", "en").render());
    }

    @Test
    public void testDivWithTabIndex() {
        assertEquals("<div tabindex=\"-1\"></div>", div().attr("tabindex", "-1").render());
    }

    @Test
    public void testDivWithLongTextContent() {
        assertEquals("<div>This is a long text content that goes on and on and should be wrapped correctly inside the div.</div>",
                div("This is a long text content that goes on and on and should be wrapped correctly inside the div.").render());
    }

    @Test
    public void testDivWithHtmlEntities() {
        assertEquals("<div>&lt;script&gt;alert(&#x27;Hack&#x27;);&lt;/script&gt;</div>",
                div("<script>alert('Hack');</script>").render());
    }

    @Test
    public void testDivWithMultipleNestedDivs() {
        assertEquals("<div><div><div>Deeply Nested</div></div></div>",
                div(div(div("Deeply Nested"))).render());
    }
    @Test
    public void testDivWithMixedContent() {
        assertEquals("<div><h1>Header</h1><p>Paragraph</p><ul><li>List Item</li></ul></div>",
                div(h1("Header"), p("Paragraph"), ul(li("List Item"))).render());
    }

    @Test
    public void testDivWithImage() {
        assertEquals("<div><img src=\"image.jpg\" alt=\"An image\"></div>",
                div(img().withSrc("image.jpg").withAlt("An image")).render());
    }

    @Test
    public void testDivWithLink() {
        assertEquals("<div><a href=\"https://example.com\">Visit Example</a></div>",
                div(a("Visit Example").withHref("https://example.com")).render());
    }

    @Test
    public void testDivWithEmbeddedVideo() {
        assertEquals("<div><video src=\"video.mp4\"></video></div>",
                div(video().withSrc("video.mp4")).render());
    }

    @Test
    public void testDivWithForm() {
        assertEquals("<div><form action=\"#\" method=\"post\"><input type=\"text\" name=\"name\"></form></div>",
                div(form().withAction("#").withMethod("post").with(input().withType("text").withName("name"))).render());
    }

    @Test
    public void testDivWithTable() {
        assertEquals("<div><table><tr><td>Cell</td></tr></table></div>",
                div(table(tr(td("Cell")))).render());
    }



    @Test
    public void testDivWithSection() {
        assertEquals("<div><section><h2>Section Title</h2><p>Content here</p></section></div>",
                div(section(h2("Section Title"), p("Content here"))).render());
    }

    @Test
    public void testDivWithAudio() {
        assertEquals("<div><audio controls><source src=\"audio.mp3\" type=\"audio/mpeg\"></audio></div>",
                div(audio().attr("controls").with(source().withSrc("audio.mp3").withType("audio/mpeg"))).render());
    }
    @Test
    public void testEmptyDiv1() {
        assertTrue(div().render().equals("<div></div>"));
    }

    @Test
    public void testDivWithClass1() {
        assertTrue(div().withClass("container").render().equals("<div class=\"container\"></div>"));
    }

    @Test
    public void testDivWithId1() {
        assertTrue(div().withId("uniqueId").render().equals("<div id=\"uniqueId\"></div>"));
    }

    @Test
    public void testDivWithTextContent1() {
        assertTrue(div("Hello, world!").render().equals("<div>Hello, world!</div>"));
    }

    @Test
    public void testDivWithChildDiv1() {
        assertTrue(div(div("Nested")).render().equals("<div><div>Nested</div></div>"));
    }

    @Test
    public void testDivWithMultipleClasses1() {
        assertTrue(div().withClass("container main").render().equals("<div class=\"container main\"></div>"));
    }

    @Test
    public void testDivWithStyle1() {
        assertTrue(div().withStyle("color:red;").render().equals("<div style=\"color:red;\"></div>"));
    }

    @Test
    public void testDivWithMultipleChildren1() {
        assertTrue(div(p("Paragraph"), span("Span")).render().equals("<div><p>Paragraph</p><span>Span</span></div>"));
    }

    @Test
    public void testDivWithAttributes1() {
        assertTrue(div().withClass("container").withId("main").withStyle("color:red;").render().equals(
                "<div class=\"container\" id=\"main\" style=\"color:red;\"></div>"));
    }

    @Test
    public void testDivWithNestedStructure1() {
        assertTrue(div(div(span("Nested"))).render().equals("<div><div><span>Nested</span></div></div>"));
    }

    @Test
    public void testDivWithComplexContent1() {
        assertTrue(div(h1("Title"), p("Text")).render().equals("<div><h1>Title</h1><p>Text</p></div>"));
    }

    @Test
    public void testDivWithRoleAttribute1() {
        assertTrue(div().attr("role", "main").render().equals("<div role=\"main\"></div>"));
    }

    @Test
    public void testDivWithAriaLabel1() {
        assertTrue(div().attr("aria-label", "Description").render().equals("<div aria-label=\"Description\"></div>"));
    }

    @Test
    public void testDivWithOnClick1() {
        assertFalse(div().attr("onclick", "alert('Clicked');").render().equals("<div onclick=\"alert('Clicked');\"></div>"));
    }

    @Test
    public void testDivWithDataAttribute1() {
        assertTrue(div().attr("data-type", "info").render().equals("<div data-type=\"info\"></div>"));
    }

    @Test
    public void testDivWithAccessibilityFeatures1() {
        assertTrue(div().attr("aria-hidden", "true").render().equals("<div aria-hidden=\"true\"></div>"));
    }

    @Test
    public void testDivWithComplexStyles1() {
        assertTrue(div().withStyle("color:red; margin-top:10px;").render().equals("<div style=\"color:red; margin-top:10px;\"></div>"));
    }

    @Test
    public void testDivWithLangAttribute1() {
        assertTrue(div().attr("lang", "en").render().equals("<div lang=\"en\"></div>"));
    }

    @Test
    public void testDivWithTabIndex1() {
        assertTrue(div().attr("tabindex", "-1").render().equals("<div tabindex=\"-1\"></div>"));
    }

    @Test
    public void testDivWithLongTextContent1() {
        assertTrue(div("This is a long text content that goes on and on and should be wrapped correctly inside the div.").render().equals(
                "<div>This is a long text content that goes on and on and should be wrapped correctly inside the div.</div>"));
    }

    @Test
    public void testDivWithHtmlEntities1() {
        assertFalse(div("<script>alert('Hack');</script>").render().equals("<div>&lt;script&gt;alert('Hack');&lt;/script&gt;</div>"));
    }



    @Test
    public void testDivWithMixedContent1() {
        assertTrue(div(h1("Header"), p("Paragraph"), ul(li("List Item"))).render().equals("<div><h1>Header</h1><p>Paragraph</p><ul><li>List Item</li></ul></div>"));
    }

    @Test
    public void testDivWithImage1() {
        assertTrue(div(img().withSrc("image.jpg").withAlt("An image")).render().equals("<div><img src=\"image.jpg\" alt=\"An image\"></div>"));
    }

    @Test
    public void testDivWithLink1() {
        assertTrue(div(a("Visit Example").withHref("https://example.com")).render().equals("<div><a href=\"https://example.com\">Visit Example</a></div>"));
    }

    @Test
    public void testDivWithEmbeddedVideo1() {
        assertTrue(div(video().withSrc("video.mp4")).render().equals("<div><video src=\"video.mp4\"></video></div>"));
    }

    @Test
    public void testDivWithForm1() {
        assertTrue(div(form().withAction("#").withMethod("post").with(input().withType("text").withName("name"))).render().equals("<div><form action=\"#\" method=\"post\"><input type=\"text\" name=\"name\"></form></div>"));
    }

    @Test
    public void testDivWithTable1() {
        assertTrue(div(table(tr(td("Cell")))).render().equals("<div><table><tr><td>Cell</td></tr></table></div>"));
    }



    @Test
    public void testDivWithSection1() {
        assertTrue(div(section(h2("Section Title"), p("Content here"))).render().equals("<div><section><h2>Section Title</h2><p>Content here</p></section></div>"));
    }

    @Test
    public void testDivWithAudio1() {
        assertTrue(div(audio().attr("controls").with(source().withSrc("audio.mp3").withType("audio/mpeg"))).render().equals("<div><audio controls><source src=\"audio.mp3\" type=\"audio/mpeg\"></audio></div>"));
    }

    @Test
    public void testDivDoesNotContainUnsetClass() {
        assertFalse(div().render().contains("class="));
    }

    @Test
    public void testDivDoesNotIncludeIncorrectId() {
        assertFalse(div().withId("uniqueId").render().contains("id=\"wrongId\""));
    }

    @Test
    public void testDivDoesNotRenderTextContentIncorrectly() {
        assertFalse(div("Hello, world!").render().contains("Goodbye, world!"));
    }

    @Test
    public void testDivDoesNotContainWrongChildDiv() {
        assertFalse(div(div("Nested")).render().contains("<div><div>Not Nested</div></div>"));
    }

    @Test
    public void testDivDoesNotIncludeMultipleIncorrectClasses() {
        assertFalse(div().withClass("container main").render().contains("class=\"wrongClass\""));
    }

    @Test
    public void testDivDoesNotIncludeStyleWhenNotSet() {
        assertFalse(div().render().contains("style="));
    }

    @Test
    public void testDivDoesNotRenderWrongChildren() {
        assertFalse(div(p("Paragraph"), span("Span")).render().contains("<div><p>Not a Paragraph</p><span>Not a Span</span></div>"));
    }

    @Test
    public void testDivDoesNotIncludeWrongAttributes() {
        assertFalse(div().withClass("container").withId("main").withStyle("color:red;").render().contains("id=\"secondary\""));
    }

    @Test
    public void testDivDoesNotIncludeIncorrectNestedStructure() {
        assertFalse(div(div(span("Nested"))).render().contains("<div><div><span>Incorrectly Nested</span></div></div>"));
    }

    @Test
    public void testDivDoesNotContainIncorrectComplexContent() {
        assertFalse(div(h1("Title"), p("Text")).render().contains("<div><h1>Wrong Title</h1><p>Wrong Text</p></div>"));
    }

    @Test
    public void testDivDoesNotMisrepresentRoleAttribute() {
        assertFalse(div().attr("role", "main").render().contains("role=\"secondary\""));
    }

    @Test
    public void testDivDoesNotIncludeIncorrectAriaLabel() {
        assertFalse(div().attr("aria-label", "Description").render().contains("aria-label=\"Wrong Description\""));
    }

    @Test
    public void testDivDoesNotIncludeWrongOnClick() {
        assertFalse(div().attr("onclick", "alert('Clicked');").render().contains("onclick=\"alert('Not Clicked');\""));
    }

    @Test
    public void testDivDoesNotContainIncorrectDataAttribute() {
        assertFalse(div().attr("data-type", "info").render().contains("data-type=\"wrongInfo\""));
    }

    @Test
    public void testDivDoesNotIncludeIncorrectAccessibilityFeatures() {
        assertFalse(div().attr("aria-hidden", "true").render().contains("aria-hidden=\"false\""));
    }

    @Test
    public void testDivDoesNotIncludeIncorrectComplexStyles() {
        assertFalse(div().withStyle("color:red; margin-top:10px;").render().contains("style=\"color:blue; margin-top:20px;\""));
    }

    @Test
    public void testDivDoesNotMisstateLangAttribute() {
        assertFalse(div().attr("lang", "en").render().contains("lang=\"fr\""));
    }

    @Test
    public void testDivDoesNotIncludeIncorrectTabIndex() {
        assertFalse(div().attr("tabindex", "-1").render().contains("tabindex=\"1\""));
    }

    @Test
    public void testDivDoesNotRenderLongIncorrectTextContent() {
        assertFalse(div("This is a long text content that goes on and on and should be wrapped correctly inside the div.").render().contains(
                "This is a short text content."));
    }

    @Test
    public void testDivDoesNotIncludeHtmlEntitiesIncorrectly() {
        assertFalse(div("<script>alert('Hack');</script>").render().contains("<script>"));
    }

    @Test
    public void testDivDoesNotRenderMultipleNestedDivsIncorrectly() {
        assertFalse(div(div(div("Deeply Nested"))).render().contains("<div><div><div>Shallowly Nested</div></div></div>"));
    }


@Test
public void testDivWithImage2() {
    assertFalse(div(img().withSrc("image.jpg").withAlt("An image")).render().contains("<img src=\"wrongImage.jpg\" alt=\"Not an image\">"));
}

@Test
public void testDivWithLink2() {
    assertFalse(div(a("Visit Example").withHref("https://example.com")).render().contains("<a href=\"https://wrong.com\">Visit Wrong</a>"));
}

@Test
public void testDivWithEmbeddedVideo2() {
    assertFalse(div(video().withSrc("video.mp4")).render().contains("<video src=\"wrongVideo.mp4\"></video>"));
}

@Test
public void testDivWithForm2() {
    assertFalse(div(form().withAction("#").withMethod("post").with(input().withType("text").withName("name"))).render().contains("<form action=\"wrongAction\" method=\"get\"><input type=\"number\" name=\"wrongName\"></form>"));
}

@Test
public void testDivWithTable2() {
    assertFalse(div(table(tr(td("Cell")))).render().contains("<table><tr><td>Not a Cell</td></tr></table>"));
}



@Test
public void testDivWithSection2() {
    assertFalse(div(section(h2("Section Title"), p("Content here"))).render().contains("<section><h2>Wrong Title</h2><p>Wrong Content</p></section>"));
}

@Test
public void testDivWithAudio2() {
    assertFalse(div(audio().attr("controls").with(source().withSrc("audio.mp3").withType("audio/mpeg"))).render().contains("<audio controls><source src=\"wrongAudio.mp3\" type=\"audio/wav\"></audio>"));
}











    /** Testing  Tag div ends here **/


/***************************************************************************
 * ********************************************************************************
 */




    /** Testing tag span starts here **/


    @Test
    public void testSpanTag() {
        String expectedHtml = "<span>This is a span.</span>";
        String actualHtml = span("This is a span.").render();
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testSpanWithSimpleText0() {
        String actual = span("Simple Text").render();
        assertEquals("<span>Simple Text</span>", actual);
    }
    @Test
    public void testEmptySpan0() {
        String actual = span().render();
        assertEquals("<span></span>", actual);
    }
    @Test
    public void testSpanWithClass0() {
        String actual = span().withClass("highlight").render();
        assertEquals("<span class=\"highlight\"></span>", actual);
    }
    @Test
    public void testSpanWithId0() {
        String actual = span().withId("identifier").render();
        assertEquals("<span id=\"identifier\"></span>", actual);
    }
    @Test
    public void testSpanWithMultipleClasses0() {
        String actual = span().withClass("highlight bold").render();
        assertEquals("<span class=\"highlight bold\"></span>", actual);
    }
    @Test
    public void testSpanWithStyle0() {
        String actual = span().withStyle("color: red;").render();
        assertEquals("<span style=\"color: red;\"></span>", actual);
    }
    @Test
    public void testNestedSpans0() {
        String actual = span(span("Inner")).render();
        assertEquals("<span><span>Inner</span></span>", actual);
    }
    @Test
    public void testSpanWithDataAttribute0() {
        String actual = span().attr("data-value", "123").render();
        assertEquals("<span data-value=\"123\"></span>", actual);
    }
    @Test
    public void testSpanWithArbitraryAttribute0() {
        String actual = span().attr("custom-attr", "value").render();
        assertEquals("<span custom-attr=\"value\"></span>", actual);
    }
    @Test
    public void testSpanContainingImage0() {
        String actual = span(img().withSrc("image.jpg")).render();
        assertEquals("<span><img src=\"image.jpg\"></span>", actual);
    }
    @Test
    public void testSpanContainingLinkAndText0() {
        String actual = span(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertEquals("<span>Visit <a href=\"http://google.com\">Google</a></span>", actual);
    }
    @Test
    public void testSpanWithSpecialCharacters0() {
        String actual = span("<script>alert('xss');</script>").render();
        assertEquals("<span>&lt;script&gt;alert(&#x27;xss&#x27;);&lt;/script&gt;</span>", actual);
    }
    @Test
    public void testSpanWithOnclick() {
        String actual = span("Clickable").attr("onclick", "console.log('clicked');").render();
        assertEquals("<span onclick=\"console.log(&#x27;clicked&#x27;);\">Clickable</span>", actual);
    }
    @Test
    public void testSpanWithNestedList0() {
        String actual = span(ul(li("Item 1"), li("Item 2"))).render();
        assertEquals("<span><ul><li>Item 1</li><li>Item 2</li></ul></span>", actual);
    }
    @Test
    public void testSpanWithFormElements0() {
        String actual = span(form().withMethod("post").with(input().withType("text"), input().withType("submit"))).render();
        assertEquals("<span><form method=\"post\"><input type=\"text\"><input type=\"submit\"></form></span>", actual);
    }
    @Test
    public void testSpanWithRole0() {
        String actual = span().attr("role", "alert").render();
        assertEquals("<span role=\"alert\"></span>", actual);
    }


    @Test
    public void testSpanWithSimpleText() {
        String result = span("Simple Text").render();
        assertTrue(result.equals("<span>Simple Text</span>"));
    }
    @Test
    public void testEmptySpan() {
        String result = span().render();
        assertTrue(result.equals("<span></span>"));
    }
    @Test
    public void testSpanWithClass() {
        String result = span().withClass("highlight").render();
        assertTrue(result.equals("<span class=\"highlight\"></span>"));
    }
    @Test
    public void testSpanWithId() {
        String result = span().withId("identifier").render();
        assertTrue(result.equals("<span id=\"identifier\"></span>"));
    }
    @Test
    public void testSpanWithMultipleClasses() {
        String result = span().withClass("highlight bold").render();
        assertTrue(result.equals("<span class=\"highlight bold\"></span>"));
    }
    @Test
    public void testSpanWithStyle() {
        String result = span().withStyle("color: red;").render();
        assertTrue(result.equals("<span style=\"color: red;\"></span>"));
    }
    @Test
    public void testNestedSpans() {
        String result = span(span("Inner")).render();
        assertTrue(result.equals("<span><span>Inner</span></span>"));
    }
    @Test
    public void testSpanWithDataAttribute() {
        String result = span().attr("data-value", "123").render();
        assertTrue(result.equals("<span data-value=\"123\"></span>"));
    }
    @Test
    public void testSpanWithArbitraryAttribute() {
        String result = span().attr("custom-attr", "value").render();
        assertTrue(result.equals("<span custom-attr=\"value\"></span>"));
    }
    @Test
    public void testSpanContainingImage() {
        String result = span(img().withSrc("image.jpg")).render();
        assertTrue(result.equals("<span><img src=\"image.jpg\"></span>"));
    }
    @Test
    public void testSpanWithSpecialCharacters() {
        String result = span("<script>alert('xss');</script>").render();
        assertFalse(result.contains("<script>"));
    }
    @Test
    public void testSpanWithNestedList() {
        String result = span(ul(li("Item 1"), li("Item 2"))).render();
        assertTrue(result.equals("<span><ul><li>Item 1</li><li>Item 2</li></ul></span>"));
    }
    @Test
    public void testSpanWithFormElements() {
        String result = span(form().withMethod("post").with(input().withType("text"), input().withType("submit"))).render();
        assertTrue(result.equals("<span><form method=\"post\"><input type=\"text\"><input type=\"submit\"></form></span>"));
    }
    @Test
    public void testSpanWithRole() {
        String result = span().attr("role", "alert").render();
        assertTrue(result.equals("<span role=\"alert\"></span>"));
    }
    @Test
    public void testSpanWithMultipleNestedElements() {
        String result = span(
                p("Paragraph inside span."),
                a("Link").withHref("#"),
                span("Nested span.")
        ).render();
        assertTrue(result.equals("<span><p>Paragraph inside span.</p><a href=\"#\">Link</a><span>Nested span.</span></span>"));
    }
    @Test
    public void testSpanWithMultipleAttributes() {
        String result = span().withClass("foo").withId("bar").withStyle("color: red;").render();
        assertTrue(result.equals("<span class=\"foo\" id=\"bar\" style=\"color: red;\"></span>"));
    }


    @Test
    public void testSpanWithSimpleText1() {
        String result = span("Simple Text").render();
        assertFalse(result.equals("<span>Simple</span>"));
    }
    @Test
    public void testEmptySpan1() {
        String result = span().render();
        assertFalse(result.equals("<span>777</span>"));
    }
    @Test
    public void testSpanWithClass1() {
        String result = span().withClass("highlight").render();
        assertFalse(result.equals("<span \"highlight\"></span>"));
    }
    @Test
    public void testSpanWithId1() {
        String result = span().withId("identifier").render();
        assertFalse(result.equals("<span id=\"\"></span>"));
    }
    @Test
    public void testSpanWithMultipleClasses1() {
        String result = span().withClass("highlight bold").render();
        assertFalse(result.equals("<span class=\"\"></span>"));
    }
    @Test
    public void testSpanWithStyle1() {
        String result = span().withStyle("color: red;").render();
        assertFalse(result.equals("<span\"color: red;\"></span>"));
    }
    @Test
    public void testNestedSpans1() {
        String result = span(span("Inner")).render();
        assertFalse(result.equals("<span><span></span></span>"));
    }
    @Test
    public void testSpanWithDataAttribute1() {
        String result = span().attr("data-value", "123").render();
        assertFalse(result.equals("<span \"123\"></span>"));
    }
    @Test
    public void testSpanWithArbitraryAttribute1() {
        String result = span().attr("custom-attr", "value").render();
        assertFalse(result.equals("<span custom-attr=\"value\">"));
    }
    @Test
    public void testSpanContainingImage1() {
        String result = span(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<span><img=\"image.jpg\"></span>"));
    }
    @Test
    public void testSpanWithSpecialCharacters1() {
        String result = span("<script>alert('xss');</script>").render();
        assertFalse(result.contains("<script1>"));
    }
    @Test
    public void testSpanWithNestedList1() {
        String result = span(ul(li("Item 1"), li("Item 2"))).render();
        assertFalse(result.equals("<span><li>Item 1</li><li>Item 2</li></ul></span>"));
    }
    @Test
    public void testSpanWithFormElements1() {
        String result = span(form().withMethod("post").with(input().withType("text"), input().withType("submit"))).render();
        assertFalse(result.equals("<span><form =\"post\"><input type=\"text\"><input type=\"submit\"></form></span>"));
    }
    @Test
    public void testSpanWithRole1() {
        String result = span().attr("role", "alert").render();
        assertFalse(result.equals("<span role=\"\"></span>"));
    }
    @Test
    public void testSpanWithMultipleNestedElements1() {
        String result = span(
                p("Paragraph inside span."),
                a("Link").withHref("#"),
                span("Nested span.")
        ).render();
        assertFalse(result.equals("<span><p>Paragraph inside span.</p><a href=\"#\">Link</a><span>Nested span</span>"));
    }
    @Test
    public void testSpanWithMultipleAttributes1() {
        String result = span().withClass("foo").withId("bar").withStyle("color: red;").render();
        assertFalse(result.equals("<span class=\"foo\"  style=\"color: red;\"></span>"));
    }
    /** Testing span ends here **/

    /***************************************************************************
     * ********************************************************************************
     */



/**Testing paragraph tag starts here**/





    @Test
    void createParagraphTag() {
        String expectedHtml = "<p>This is a paragraph.</p>";
        String actualHtml = p("This is a paragraph.").render();
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testParagraphWithSimpleText() {
        String actual = p("Hello World").render();
        assertEquals("<p>Hello World</p>", actual);
    }
    @Test
    public void testEmptyParagraph() {
        String actual = p().render();
        assertEquals("<p></p>", actual);
    }
    @Test
    public void testParagraphWithClass() {
        String actual = p("Content").withClass("text-class").render();
        assertEquals("<p class=\"text-class\">Content</p>", actual);
    }
    @Test
    public void testParagraphWithId() {
        String actual = p("Identified").withId("para1").render();
        assertEquals("<p id=\"para1\">Identified</p>", actual);
    }
    @Test
    public void testParagraphWithMultipleClasses() {
        String actual = p("More styles").withClass("class1 class2").render();
        assertEquals("<p class=\"class1 class2\">More styles</p>", actual);
    }
    @Test
    public void testParagraphWithStyle() {
        String actual = p("Stylish").withStyle("font-size: 20px;").render();
        assertEquals("<p style=\"font-size: 20px;\">Stylish</p>", actual);
    }
    @Test
    public void testNestedParagraphs() {
        String actual = p(p("Inner")).render();
        assertEquals("<p><p>Inner</p></p>", actual);  // Note: Nested paragraphs are not valid HTML5
    }
    @Test
    public void testParagraphWithDataAttribute() {
        String actual = p("Data driven").attr("data-info", "1234").render();
        assertEquals("<p data-info=\"1234\">Data driven</p>", actual);
    }
    @Test
    public void testParagraphWithCustomAttribute() {
        String actual = p("Custom").attr("custom-attr", "value").render();
        assertEquals("<p custom-attr=\"value\">Custom</p>", actual);
    }
    @Test
    public void testParagraphContainingImage() {
        String actual = p(img().withSrc("logo.png")).render();
        assertEquals("<p><img src=\"logo.png\"></p>", actual);
    }
    @Test
    public void testParagraphWithEscapedCharacters() {
        String actual = p("<script>alert('hack');</script>").render();
        assertEquals("<p>&lt;script&gt;alert(&#x27;hack&#x27;);&lt;/script&gt;</p>", actual);
    }
    @Test
    public void testParagraphWithOnclick() {
        String actual = p("Click me").attr("onclick", "alert('Hello!');").render();
        assertEquals("<p onclick=\"alert(&#x27;Hello!&#x27;);\">Click me</p>", actual);
    }
    @Test
    public void testParagraphWithNestedList() {
        String actual = p(ul(li("First"), li("Second"))).render();
        assertEquals("<p><ul><li>First</li><li>Second</li></ul></p>", actual);
    }
    @Test
    public void testParagraphWithRole() {
        String actual = p("Accessible paragraph").attr("role", "document").render();
        assertEquals("<p role=\"document\">Accessible paragraph</p>", actual);
    }
    @Test
    public void testParagraphWithConditionalContent() {
        boolean condition = true;
        String actual = p(condition ? "Content shown" : "Content hidden").render();
        assertEquals("<p>Content shown</p>", actual);
    }
    @Test
    public void testParagraphWithHTMLEntityEncoding() {
        String actual = p("Café & Restaurant").render();
        assertEquals("<p>Café &amp; Restaurant</p>", actual);
    }
    @Test
    public void testParagraphWithSimpleText1() {
        assertTrue(p("Hello World").render().equals("<p>Hello World</p>"));
    }

    @Test
    public void testEmptyParagraph1() {
        assertTrue(p().render().equals("<p></p>"));
    }

    @Test
    public void testParagraphWithClass1() {
        assertTrue(p("Content").withClass("text-class").render().equals("<p class=\"text-class\">Content</p>"));
    }

    @Test
    public void testParagraphWithId1() {
        assertTrue(p("Identified").withId("para1").render().equals("<p id=\"para1\">Identified</p>"));
    }

    @Test
    public void testParagraphWithMultipleClasses1() {
        assertTrue(p("More styles").withClass("class1 class2").render().equals("<p class=\"class1 class2\">More styles</p>"));
    }

    @Test
    public void testParagraphWithStyle1() {
        assertTrue(p("Stylish").withStyle("font-size: 20px;").render().equals("<p style=\"font-size: 20px;\">Stylish</p>"));
    }

    @Test
    public void testNestedParagraphs1() {
        assertTrue(p(p("Inner")).render().equals("<p><p>Inner</p></p>"));  // Note: Nested paragraphs are not valid HTML5
    }

    @Test
    public void testParagraphWithDataAttribute1() {
        assertTrue(p("Data driven").attr("data-info", "1234").render().equals("<p data-info=\"1234\">Data driven</p>"));
    }

    @Test
    public void testParagraphWithCustomAttribute1() {
        assertTrue(p("Custom").attr("custom-attr", "value").render().equals("<p custom-attr=\"value\">Custom</p>"));
    }

    @Test
    public void testParagraphContainingImage1() {
        assertTrue(p(img().withSrc("logo.png")).render().equals("<p><img src=\"logo.png\"></p>"));
    }


    @Test
    public void testParagraphWithEscapedCharacters1() {
        assertFalse(p("<script>alert('hack');</script>").render().equals("<p>&lt;script&gt;alert('hack');&lt;/script&gt;</p>"));
    }

    @Test
    public void testParagraphWithOnclick1() {
        assertFalse(p("Click me").attr("onclick", "alert('Hello!');").render().equals("<p onclick=\"alert('Hello!');\">Click me</p>"));
    }

    @Test
    public void testParagraphWithNestedList1() {
        assertTrue(p(ul(li("First"), li("Second"))).render().equals("<p><ul><li>First</li><li>Second</li></ul></p>"));
    }



    @Test
    public void testParagraphWithRole1() {
        assertTrue(p("Accessible paragraph").attr("role", "document").render().equals("<p role=\"document\">Accessible paragraph</p>"));
    }

    @Test
    public void testParagraphWithConditionalContent11() {
        boolean condition = true;
        assertTrue(p(condition ? "Content shown" : "Content hidden").render().equals("<p>Content shown</p>"));
    }

    @Test
    public void testParagraphDoesNotContainScript() {
        assertFalse(p("<script>alert('hack');</script>").render().contains("<script>"));
    }

    @Test
    public void testParagraphDoesNotHaveClassWhenNotSet() {
        assertFalse(p("No class here").render().contains("class=\""));
    }

    @Test
    public void testParagraphDoesNotContainOnClickWhenNotSet() {
        assertFalse(p("Should not be clickable").render().contains("onclick"));
    }

    @Test
    public void testParagraphNotContainingImageWhenNotIncluded() {
        assertFalse(p("No images here").render().contains("<img"));
    }

    @Test
    public void testParagraphDoesNotContainIdWhenNotSet() {
        assertFalse(p("No ID").render().contains("id=\""));
    }

    @Test
    public void testParagraphWithIncorrectNestedTag() {
        assertFalse(p(form("This should not validate")).render().equals("<p><form>"));
    }

    @Test
    public void testParagraphDoesNotIncludeStyleWhenNotSet() {
        assertFalse(p("No style").render().contains("style=\""));
    }

    @Test
    public void testParagraphDoesNotContainIncorrectText() {
        assertFalse(p("Hello World").render().contains("Goodbye World"));
    }

    @Test
    public void testParagraphDoesNotContainUnsetCustomAttribute() {
        assertFalse(p("No custom attributes").render().contains("custom-attr=\""));
    }

    @Test
    public void testEmptyParagraphDoesNotContainText() {
        assertFalse(p().render().contains("Some text"));
    }

    @Test
    public void testParagraphDoesNotContainDataAttributeWhenNotSet() {
        assertFalse(p("No data attributes here").render().contains("data-"));
    }

    @Test
    public void testParagraphDoesNotRenderNestedInvalidHtml() {
        assertFalse(p("<div>Invalid</div>").render().contains("<p><div>"));
    }

    @Test
    public void testParagraphNotSupportingNestedParagraphs() {
        assertFalse(p(p("Nested")).render().equals("<p><p>")); // Note: Nested paragraphs are not valid HTML5
    }

    @Test
    public void testParagraphDoesNotRenderAsSelfClosing() {
        assertFalse(p("Not self-closing").render().contains("<p/>"));
    }

    @Test
    public void testParagraphDoesNotContainRoleWhenNotSet() {
        assertFalse(p("Role not set").render().contains("role=\""));
    }

    @Test
    public void testParagraphWithNonExistentConditionalContent() {
        boolean condition = false;
        assertFalse(p(condition ? "Content shown" : "Content hidden").render().contains("Content shown"));
    }

    @Test
    public void testParagraphDoesNotContainIncorrectRoleAttribute() {
        assertFalse(p("Wrong role").attr("role", "navigation").render().contains("role=\"document\""));
    }

    @Test
    public void testParagraphWithIncorrectAttributeValues() {
        assertFalse(p("Check attribute").withClass("text-class").render().contains("class=\"wrong-class\""));
    }


    /***************************************************************************
     * ********************************************************************************
     */
    /** testing paragraph tag ends here. **/





    /** testing anchor tag starts here. **/



    @Test
    public void testAnchorTag() {
        String expectedHtml = "<a href=\"https://example.com\">Visit Example</a>";
        String actualHtml = a("Visit Example").withHref("https://example.com").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testAnchorWithHref() {
        assertTrue(a("Google").withHref("http://google.com").render().equals("<a href=\"http://google.com\">Google</a>"));
    }

    @Test
    public void testAnchorWithTitle() {
        assertFalse(a("Visit").withTitle("Go to Google").withHref("#").render().equals("<a href=\"#\" title=\"Go to Google\">Visit</a>"));
    }

    @Test
    public void testAnchorWithTarget() {
        assertFalse(a("Open").withTarget("_blank").withHref("#").render().equals("<a href=\"#\" target=\"_blank\">Open</a>"));
    }

    @Test
    public void testAnchorWithRel() {
        assertFalse(a("nofollow Link").withRel("nofollow").withHref("#").render().equals("<a href=\"#\" rel=\"nofollow\">nofollow Link</a>"));
    }

    @Test
    public void testAnchorWithMultipleAttributes() {
        assertTrue(a("Link").withHref("http://example.com").withTitle("Example").withTarget("_blank").render().equals("<a href=\"http://example.com\" title=\"Example\" target=\"_blank\">Link</a>"));
    }

    @Test
    public void testAnchorWithClass() {
        assertFalse(a("Styled Link").withClass("btn btn-primary").withHref("#").render().equals("<a href=\"#\" class=\"btn btn-primary\">Styled Link</a>"));
    }

    @Test
    public void testAnchorWithId() {
        assertFalse(a("ID Link").withId("link1").withHref("#").render().equals("<a href=\"#\" id=\"link1\">ID Link</a>"));
    }

    @Test
    public void testAnchorWithStyle() {
        assertFalse(a("Styled Link").withStyle("color: red;").withHref("#").render().equals("<a href=\"#\" style=\"color: red;\">Styled Link</a>"));
    }

    @Test
    public void testEmptyAnchor() {
        assertTrue(a().withHref("#").render().equals("<a href=\"#\"></a>"));
    }

    @Test
    public void testAnchorWithCustomAttribute() {
        assertFalse(a("Custom").attr("data-custom", "123").withHref("#").render().equals("<a href=\"#\" data-custom=\"123\">Custom</a>"));
    }

    @Test
    public void testAnchorWithEscapedCharacters() {
        assertTrue(a("<script>").withHref("#").render().equals("<a href=\"#\">&lt;script&gt;</a>"));
    }

    @Test
    public void testAnchorWithNestedElements() {
        assertTrue(a(span("Text")).withHref("#").render().equals("<a href=\"#\"><span>Text</span></a>"));
    }

    @Test
    public void testAnchorWithOnClick() {
        assertFalse(a("Click me").attr("onclick", "alert('clicked');").withHref("#").render().equals("<a href=\"#\" onclick=\"alert('clicked');\">Click me</a>"));
    }



    @Test
    public void testAnchorWithNoHref() {
        assertTrue(a("No href").render().equals("<a>No href</a>"));
    }

    @Test
    public void testAnchorWithMailto() {
        assertTrue(a("Send Email").withHref("mailto:example@example.com").render().equals("<a href=\"mailto:example@example.com\">Send Email</a>"));
    }

    @Test
    public void testAnchorWithTel() {
        assertTrue(a("Call Us").withHref("tel:+1234567890").render().equals("<a href=\"tel:+1234567890\">Call Us</a>"));
    }




    @Test
    public void testAnchorWithCompleteFunctionality() {
        String actual = a("Complete Link").withHref("http://complete.com").withTarget("_blank").withRel("nofollow").withClass("complete-class").render();
        assertTrue(actual.equals("<a href=\"http://complete.com\" target=\"_blank\" rel=\"nofollow\" class=\"complete-class\">Complete Link</a>"));
    }

    @Test
    public void testAnchorWithHref1() {
        assertEquals("<a href=\"http://google.com\">Google</a>", a("Google").withHref("http://google.com").render());
    }

    @Test
    public void testAnchorWithTitle1() {
        assertEquals("<a title=\"Go to Google\" href=\"#\">Visit</a>", a("Visit").withTitle("Go to Google").withHref("#").render());
    }

    @Test
    public void testAnchorWithTarget1() {
        assertEquals("<a target=\"_blank\" href=\"#\">Open</a>", a("Open").withTarget("_blank").withHref("#").render());
    }

    @Test
    public void testAnchorWithRel1() {
        assertEquals("<a rel=\"nofollow\" href=\"#\">nofollow Link</a>", a("nofollow Link").withRel("nofollow").withHref("#").render());
    }

    @Test
    public void testAnchorWithMultipleAttributes1() {
        assertEquals("<a href=\"http://example.com\" title=\"Example\" target=\"_blank\">Link</a>", a("Link").withHref("http://example.com").withTitle("Example").withTarget("_blank").render());
    }

    @Test
    public void testAnchorWithClass1() {
        assertEquals("<a class=\"btn btn-primary\" href=\"#\">Styled Link</a>", a("Styled Link").withClass("btn btn-primary").withHref("#").render());
    }

    @Test
    public void testAnchorWithId1() {
        assertEquals("<a id=\"link1\" href=\"#\">ID Link</a>", a("ID Link").withId("link1").withHref("#").render());
    }

    @Test
    public void testAnchorWithStyle1() {
        assertEquals("<a style=\"color: red;\" href=\"#\">Styled Link</a>", a("Styled Link").withStyle("color: red;").withHref("#").render());
    }

    @Test
    public void testEmptyAnchor1() {
        assertEquals("<a href=\"#\"></a>", a().withHref("#").render());
    }

    @Test
    public void testAnchorWithCustomAttribute1() {
        assertEquals("<a data-custom=\"123\" href=\"#\">Custom</a>", a("Custom").attr("data-custom", "123").withHref("#").render());
    }

    @Test
    public void testAnchorWithEscapedCharacters1() {
        assertEquals("<a href=\"#\">&lt;script&gt;</a>", a("<script>").withHref("#").render());
    }

    @Test
    public void testAnchorWithNestedElements1() {
        assertEquals("<a href=\"#\"><span>Text</span></a>", a(span("Text")).withHref("#").render());
    }

    @Test
    public void testAnchorWithOnClick1() {
        assertEquals("<a onclick=\"alert(&#x27;clicked&#x27;);\" href=\"#\">Click me</a>", a("Click me").attr("onclick", "alert('clicked');").withHref("#").render());
    }



    @Test
    public void testAnchorWithNoHref1() {
        assertEquals("<a>No href</a>", a("No href").render());
    }

    @Test
    public void testAnchorWithMailto1() {
        assertEquals("<a href=\"mailto:example@example.com\">Send Email</a>", a("Send Email").withHref("mailto:example@example.com").render());
    }

    @Test
    public void testAnchorWithTel1() {
        assertEquals("<a href=\"tel:+1234567890\">Call Us</a>", a("Call Us").withHref("tel:+1234567890").render());
    }



    @Test
    public void testAnchorWithRoleAttribute1() {
        assertEquals("<a href=\"#\" role=\"button\">Accessibility</a>", a("Accessibility").withHref("#").attr("role", "button").render());
    }

    @Test
    public void testAnchorWithTitle2() {
        assertEquals("<a title=\"Go to Google\" href=\"#\">Visit</a>", a("Visit").withTitle("Go to Google").withHref("#").render());
    }

    @Test
    public void testAnchorWithTarget2() {
        assertEquals("<a target=\"_blank\" href=\"#\">Open</a>", a("Open").withTarget("_blank").withHref("#").render());
    }

    @Test
    public void testAnchorWithRe2() {
        assertEquals("<a rel=\"nofollow\" href=\"#\">nofollow Link</a>", a("nofollow Link").withRel("nofollow").withHref("#").render());
    }

    @Test
    public void testAnchorWithMultipleAttributes2() {
        assertEquals("<a href=\"http://example.com\" title=\"Example\" target=\"_blank\">Link</a>", a("Link").withHref("http://example.com").withTitle("Example").withTarget("_blank").render());
    }

    @Test
    public void testAnchorWithClass2() {
        assertEquals("<a class=\"btn btn-primary\" href=\"#\">Styled Link</a>", a("Styled Link").withClass("btn btn-primary").withHref("#").render());
    }

    @Test
    public void testAnchorWithId2() {
        assertEquals("<a id=\"link1\" href=\"#\">ID Link</a>", a("ID Link").withId("link1").withHref("#").render());
    }

    @Test
    public void testAnchorWithStyle3() {
        assertEquals("<a style=\"color: red;\" href=\"#\">Styled Link</a>", a("Styled Link").withStyle("color: red;").withHref("#").render());
    }

    @Test
    public void testEmptyAnchor3() {
        assertEquals("<a href=\"#\"></a>", a().withHref("#").render());
    }

    @Test
    public void testAnchorWithCustomAttribute3() {
        assertEquals("<a data-custom=\"123\" href=\"#\">Custom</a>", a("Custom").attr("data-custom", "123").withHref("#").render());
    }

    @Test
    public void testAnchorWithEscapedCharacters2() {
        assertEquals("<a href=\"#\">&lt;script&gt;</a>", a("<script>").withHref("#").render());
    }

    @Test
    public void testAnchorWithNestedElements2() {
        assertEquals("<a href=\"#\"><span>Text</span></a>", a(span("Text")).withHref("#").render());
    }

    @Test
    public void testAnchorWithOnClick2() {
        assertEquals("<a onclick=\"alert(&#x27;clicked&#x27;);\" href=\"#\">Click me</a>", a("Click me").attr("onclick", "alert('clicked');").withHref("#").render());
    }



    @Test
    public void testAnchorWithNoHref2() {
        assertEquals("<a>No href</a>", a("No href").render());
    }

    @Test
    public void testAnchorWithMailto2() {
        assertEquals("<a href=\"mailto:example@example.com\">Send Email</a>", a("Send Email").withHref("mailto:example@example.com").render());
    }

    @Test
    public void testAnchorWithTel2() {
        assertEquals("<a href=\"tel:+1234567890\">Call Us</a>", a("Call Us").withHref("tel:+1234567890").render());
    }



    @Test
    public void testAnchorWithRoleAttribute() {
        assertEquals("<a href=\"#\" role=\"button\">Accessibility</a>", a("Accessibility").withHref("#").attr("role", "button").render());
    }


    @Test
    public void testAnchorDoesNotIncludeDefaultHref() {
        assertFalse(a("No default href").render().contains("href=\"\""));
    }

    @Test
    public void testAnchorDoesNotHaveUnspecifiedClass() {
        assertFalse(a("No class").withHref("#").render().contains("class="));
    }

    @Test
    public void testAnchorDoesNotHaveUnspecifiedId() {
        assertFalse(a("No ID").withHref("#").render().contains("id="));
    }

    @Test
    public void testAnchorDoesNotContainJavaScript() {
        assertFalse(a("No script").withHref("#").render().contains("<script>"));
    }

    @Test
    public void testAnchorDoesNotContainTargetBlankByDefault() {
        assertFalse(a("No target").withHref("#").render().contains("target=\"_blank\""));
    }

    @Test
    public void testAnchorDoesNotContainDownloadAttributeUnnecessarily() {
        assertTrue(a("Should not download").withHref("#").render().contains("download"));
    }

    @Test
    public void testAnchorDoesNotHaveOnClickByDefault() {
        assertFalse(a("Click me not").withHref("#").render().contains("onclick="));
    }

    @Test
    public void testAnchorDoesNotContainRelNoFollowByDefault() {
        assertFalse(a("No rel").withHref("#").render().contains("rel=\"nofollow\""));
    }

    @Test
    public void testAnchorDoesNotIncludeStyleByDefault() {
        assertFalse(a("Plain link").withHref("#").render().contains("style="));
    }

    @Test
    public void testAnchorDoesNotContainCustomDataAttribute() {
        assertFalse(a("No data").withHref("#").render().contains("data-"));
    }

    @Test
    public void testAnchorDoesNotIncludeInvalidAttribute() {
        assertTrue(a("Invalid attribute").withHref("#").attr("nonexistent", "false").render().contains("nonexistent"));
    }

    @Test
    public void testAnchorDoesNotRenderAsSelfClosingTag() {
        assertFalse(a("Not self-closing").withHref("#").render().contains("<a href=\"#\"/>"));
    }

    @Test
    public void testAnchorDoesNotContainUnexpectedText() {
        assertFalse(a("Hello").withHref("#").render().contains("Goodbye"));
    }

    @Test
    public void testAnchorDoesNotContainEmbeddedHtml() {
        assertFalse(a("No HTML").withHref("#").render().contains("<div>"));
    }

    @Test
    public void testAnchorWithEmptyHrefDoesNotHaveTrailingSlash() {
        assertFalse(a().withHref("").render().contains("/>"));
    }

    @Test
    public void testAnchorWithNoHrefAttributeDoesNotAppear() {
        assertFalse(a("No href here").render().contains("href="));
    }

    @Test
    public void testAnchorDoesNotAlterText() {
        assertFalse(a("text").withHref("#").render().contains("Text"));
    }

    @Test
    public void testAnchorDoesNotMisrepresentTarget() {
        assertFalse(a("Wrong target").withHref("#").withTarget("_self").render().contains("target=\"_blank\""));
    }

    @Test
    public void testAnchorDoesNotIncludeRoleWhenNotSpecified() {
        assertFalse(a("No role").withHref("#").render().contains("role="));
    }

    @Test
    public void testAnchorDoesNotContainIncorrectRel() {
        assertFalse(a("Incorrect Rel").withHref("#").withRel("noopener").render().contains("rel=\"nofollow\""));
    }

    /** Testing Anchor tag ends here.
     ********************************************************************************************************
     * **********************************************************************************************
     */








    /** Testing unordered list starts here**
     * **************************************
     */


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
    public void testEmptyUnorderedList() {
        assertEquals("<ul></ul>", ul().render());
    }

    @Test
    public void testUnorderedListWithOneItem() {
        assertEquals("<ul><li>Item 1</li></ul>", ul(li("Item 1")).render());
    }

    @Test
    public void testUnorderedListWithMultipleItems() {
        assertEquals("<ul><li>Item 1</li><li>Item 2</li><li>Item 3</li></ul>",
                ul(
                        li("Item 1"),
                        li("Item 2"),
                        li("Item 3")
                ).render());
    }

    @Test
    public void testUnorderedListWithNestedLists() {
        assertEquals("<ul><li>Item 1</li><li>Item 2<ul><li>Subitem 1</li><li>Subitem 2</li></ul></li><li>Item 3</li></ul>",
                ul(
                        li("Item 1"),
                        li("Item 2").with(
                                ul(
                                        li("Subitem 1"),
                                        li("Subitem 2")
                                )
                        ),
                        li("Item 3")
                ).render());
    }

    @Test
    public void testUnorderedListWithClass() {
        assertEquals("<ul class=\"my-list\"></ul>", ul().withClass("my-list").render());
    }

    @Test
    public void testUnorderedListWithId() {
        assertEquals("<ul id=\"list-id\"></ul>", ul().withId("list-id").render());
    }

    @Test
    public void testUnorderedListWithStyle() {
        assertEquals("<ul style=\"color: red;\"></ul>", ul().withStyle("color: red;").render());
    }

    @Test
    public void testUnorderedListWithMultipleAttributes() {
        assertEquals("<ul id=\"list-id\" class=\"my-list\" style=\"color: red;\"></ul>",
                ul().withId("list-id").withClass("my-list").withStyle("color: red;").render());
    }

    @Test
    public void testUnorderedListWithAriaRole() {
        assertEquals("<ul role=\"navigation\"></ul>", ul().attr("role", "navigation").render());
    }

    @Test
    public void testUnorderedListWithCustomAttribute() {
        assertEquals("<ul custom=\"value\"></ul>", ul().attr("custom", "value").render());
    }

    @Test
    public void testUnorderedListWithItemsHavingClasses() {
        assertEquals("<ul><li class=\"item-class\">Item 1</li><li class=\"item-class\">Item 2</li></ul>",
                ul(
                        li("Item 1").withClass("item-class"),
                        li("Item 2").withClass("item-class")
                ).render());
    }

    @Test
    public void testUnorderedListWithEscapedText() {
        assertEquals("<ul><li>&lt;script&gt;alert(&#x27;xss&#x27;);&lt;/script&gt;</li></ul>",
                ul(li("<script>alert('xss');</script>")).render());
    }

    @Test
    public void testUnorderedListWithLinkItems() {
        assertEquals("<ul><li><a href=\"https://example.com\">Link 1</a></li><li><a href=\"https://example.com\">Link 2</a></li></ul>",
                ul(
                        li(a("Link 1").withHref("https://example.com")),
                        li(a("Link 2").withHref("https://example.com"))
                ).render());
    }

    @Test
    public void testUnorderedListWithVariousTypesOfChildren() {
        assertEquals("<ul><li>Text</li><li><img src=\"image.jpg\"></li></ul>",
                ul(
                        li("Text"),
                        li(img().withSrc("image.jpg"))
                ).render());
    }



    @Test
    public void testUnorderedListWithManyLevelsOfNesting() {
        assertEquals("<ul><li>Level 1<ul><li>Level 2<ul><li>Level 3</li></ul></li></ul></li></ul>",
                ul(
                        li("Level 1").with(
                                ul(
                                        li("Level 2").with(
                                                ul(
                                                        li("Level 3")
                                                )
                                        )
                                )
                        )
                ).render());
    }

    @Test
    public void testUnorderedListWithItemAttributes() {
        assertEquals("<ul><li id=\"first-item\" class=\"highlight\">First Item</li></ul>",
                ul(
                        li("First Item").withId("first-item").withClass("highlight")
                ).render());
    }

    @Test
    public void testUnorderedListWithMixedContentItems() {
        assertEquals("<ul><li>Text Item</li><li><b>Bold Text</b></li><li><i>Italic Text</i></li></ul>",
                ul(
                        li("Text Item"),
                        li(b("Bold Text")),
                        li(i("Italic Text"))
                ).render());
    }

    @Test
    public void testUnorderedListWithLinkAndImage() {
        assertEquals("<ul><li><a href=\"http://example.com\"><img src=\"image.png\" alt=\"Example\"></a></li></ul>",
                ul(
                        li(a(img().withSrc("image.png").withAlt("Example")).withHref("http://example.com"))
                ).render());
    }

    @Test
    public void testUnorderedListWithFormElements() {
        assertEquals("<ul><li><form action=\"#\"><input type=\"text\" placeholder=\"Enter text here\"></form></li></ul>",
                ul(
                        li(form().withAction("#").with(
                                input().withType("text").withPlaceholder("Enter text here")
                        ))
                ).render());
    }

    @Test
    public void testUnorderedListWithDifferentListItemTypes() {
        assertEquals("<ul><li class=\"type1\">Item 1</li><li class=\"type2\">Item 2</li><li class=\"type3\">Item 3</li></ul>",
                ul(
                        li("Item 1").withClass("type1"),
                        li("Item 2").withClass("type2"),
                        li("Item 3").withClass("type3")
                ).render());
    }

    @Test
    public void testUnorderedListWithNestedDiv() {
        assertEquals("<ul><li><div>Content inside a div</div></li></ul>",
                ul(
                        li(div("Content inside a div"))
                ).render());
    }

    @Test
    public void testUnorderedListWithInlineStyles() {
        assertEquals("<ul><li style=\"color: blue;\">Blue Item</li><li style=\"color: red;\">Red Item</li></ul>",
                ul(
                        li("Blue Item").withStyle("color: blue;"),
                        li("Red Item").withStyle("color: red;")
                ).render());
    }

    @Test
    public void testUnorderedListWithRoleAndAriaAttributes() {
        assertEquals("<ul role=\"navigation\" aria-label=\"Main Navigation\"><li>Home</li><li>About</li></ul>",
                ul(
                        li("Home"),
                        li("About")
                ).attr("role", "navigation").attr("aria-label", "Main Navigation").render());
    }

    @Test
    public void testUnorderedListWithSpecifiedTabindex() {
        assertEquals("<ul><li tabindex=\"1\">First</li><li tabindex=\"2\">Second</li></ul>",
                ul(
                        li("First").attr("tabindex", "1"),
                        li("Second").attr("tabindex", "2")
                ).render());
    }

    @Test
    public void testUnorderedListWithDynamicContentViaMethod() {
        assertEquals("<ul><li>Dynamic</li></ul>", ul(li(generateDynamicContent())).render());
    }

    @Test
    public void testUnorderedListWithConditionalRendering() {
        boolean condition = true;
        assertEquals("<ul>" + (condition ? "<li>Visible Item</li>" : "") + "</ul>",
                ul(
                        condition ? li("Visible Item") : text("")
                ).render());
    }


    @Test
    public void testEmptyUnorderedList1() {
        assertTrue(ul().render().equals("<ul></ul>"));
    }

    @Test
    public void testUnorderedListWithOneItem1() {
        assertTrue(ul(li("Item 1")).render().equals("<ul><li>Item 1</li></ul>"));
    }

    @Test
    public void testUnorderedListWithMultipleItems1() {
        assertTrue(ul(li("Item 1"), li("Item 2"), li("Item 3")).render().equals("<ul><li>Item 1</li><li>Item 2</li><li>Item 3</li></ul>"));
    }

    @Test
    public void testUnorderedListWithNestedLists1() {
        assertTrue(ul(li("Item 1"), li("Item 2").with(ul(li("Subitem 1"), li("Subitem 2"))), li("Item 3")).render().equals(
                "<ul><li>Item 1</li><li>Item 2<ul><li>Subitem 1</li><li>Subitem 2</li></ul></li><li>Item 3</li></ul>"));
    }

    @Test
    public void testUnorderedListWithClass1() {
        assertTrue(ul().withClass("my-list").render().equals("<ul class=\"my-list\"></ul>"));
    }

    @Test
    public void testUnorderedListWithId1() {
        assertTrue(ul().withId("list-id").render().equals("<ul id=\"list-id\"></ul>"));
    }

    @Test
    public void testUnorderedListWithStyle1() {
        assertTrue(ul().withStyle("color: red;").render().equals("<ul style=\"color: red;\"></ul>"));
    }

    @Test
    public void testUnorderedListWithMultipleAttributes1() {
        assertTrue(ul().withId("list-id").withClass("my-list").withStyle("color: red;").render().equals(
                "<ul id=\"list-id\" class=\"my-list\" style=\"color: red;\"></ul>"));
    }

    @Test
    public void testUnorderedListWithAriaRole1() {
        assertTrue(ul().attr("role", "navigation").render().equals("<ul role=\"navigation\"></ul>"));
    }

    @Test
    public void testUnorderedListWithCustomAttribute1() {
        assertTrue(ul().attr("custom", "value").render().equals("<ul custom=\"value\"></ul>"));
    }

    @Test
    public void testUnorderedListWithItemsHavingClasses1() {
        assertTrue(ul(li("Item 1").withClass("item-class"), li("Item 2").withClass("item-class")).render().equals(
                "<ul><li class=\"item-class\">Item 1</li><li class=\"item-class\">Item 2</li></ul>"));
    }

    @Test
    public void testUnorderedListWithEscapedText1() {
        assertFalse(ul(li("<script>alert('xss');</script>")).render().equals(
                "<ul><li>&lt;script&gt;alert('xss');&lt;/script&gt;</li></ul>"));
    }

    @Test
    public void testUnorderedListWithLinkItems1() {
        assertTrue(ul(li(a("Link 1").withHref("https://example.com")), li(a("Link 2").withHref("https://example.com"))).render().equals(
                "<ul><li><a href=\"https://example.com\">Link 1</a></li><li><a href=\"https://example.com\">Link 2</a></li></ul>"));
    }

    @Test
    public void testUnorderedListWithVariousTypesOfChildren1() {
        assertTrue(ul(li("Text"), li(img().withSrc("image.jpg"))).render().equals("<ul><li>Text</li><li><img src=\"image.jpg\"></li></ul>"));
    }

    @Test
    public void testUnorderedListWithNestedComplexStructure1() {
        assertFalse(ul(li("Item 1"), li("Item 2").with(ul(li("Subitem 1"), li(a("Link").withHref("#"))))).render().equals(
                "<ul><li>Item 1</li><li>Item 2<ul><li>Subitem 1</li><li><a href=\"#\">Link</a></li</li></ul></li><li>Item 3</li></ul>"));
    }

    @Test
    public void testUnorderedListWithManyLevelsOfNesting1() {
        assertTrue(ul(
                li("Level 1").with(
                        ul(
                                li("Level 2").with(
                                        ul(
                                                li("Level 3")
                                        )
                                )
                        )
                )
        ).render().equals("<ul><li>Level 1<ul><li>Level 2<ul><li>Level 3</li></ul></li></ul></li></ul>"));
    }

    @Test
    public void testUnorderedListWithItemAttributes1() {
        assertTrue(ul(
                li("First Item").withId("first-item").withClass("highlight")
        ).render().equals("<ul><li id=\"first-item\" class=\"highlight\">First Item</li></ul>"));
    }

    @Test
    public void testUnorderedListWithConditionalRendering1() {
        boolean condition = true;
        assertTrue(ul(
                condition ? li("Visible Item") : null
        ).render().equals(condition ? "<ul><li>Visible Item</li></ul>" : "<ul></ul>"));
    }

    @Test
    public void testUnorderedListWithDynamicContentViaMethod1() {
        assertTrue(ul(
                li(generateDynamicContent())
        ).render().equals("<ul><li>Dynamic</li></ul>"));
    }

    private String generateDynamicContent() {
        return "Dynamic";
    }

    @Test
    public void testUnorderedListDoesNotContainClassesWhenNotSet() {
        assertFalse(ul(li("Item")).render().contains("class="));
    }

    @Test
    public void testUnorderedListDoesNotContainIdWhenNotSet() {
        assertFalse(ul(li("Item")).render().contains("id="));
    }

    @Test
    public void testUnorderedListDoesNotIncludeStyleWhenNotSet() {
        assertFalse(ul(li("Item")).render().contains("style="));
    }

    @Test
    public void testUnorderedListDoesNotContainScriptTag() {
        assertFalse(ul(li("<script>alert('hack');</script>")).render().contains("<script>"));
    }

    @Test
    public void testUnorderedListDoesNotIncludeLinkTag() {
        assertFalse(ul(li(a("Click here").withHref("#"))).render().contains("<link>"));
    }

    @Test
    public void testUnorderedListNotSelfClosing() {
        assertFalse(ul().render().equals("<ul/>"));
    }

    @Test
    public void testUnorderedListDoesNotContainAdditionalText() {
        assertFalse(ul(li("Item")).render().contains("Extra Text"));
    }


    @Test
    public void testUnorderedListDoesNotIncludeIncorrectListItem() {
        assertFalse(ul(li("Item")).render().contains("<li>Incorrect Item</li>"));
    }

    @Test
    public void testUnorderedListWithEmptyItemsDoesNotRenderLiTags() {
        assertFalse(ul().render().contains("<li>"));
    }

    @Test
    public void testUnorderedListDoesNotRenderNestedUlWithoutLi() {
        assertTrue(ul(ul()).render().contains("<ul><ul>"));
    }

    @Test
    public void testUnorderedListDoesNotRenderLiTagsOutsideOfUl() {
        assertFalse(ul(li("Item")).render().contains("</ul><li>"));
    }

    @Test
    public void testUnorderedListDoesNotContainMisplacedDivTag() {
        assertFalse(ul(li("Item")).render().contains("<div>"));
    }

    @Test
    public void testUnorderedListDoesNotIncludeDisabledAttribute() {
        assertTrue(ul(li("Item")).attr("disabled", "disabled").render().contains("disabled="));
    }

    @Test
    public void testUnorderedListDoesNotHaveFalseAttributes() {
        assertTrue(ul(li("Item")).attr("false", "false").render().contains("false="));
    }

    @Test
    public void testUnorderedListDoesNotHaveUnsetRole() {
        assertFalse(ul(li("Item")).render().contains("role="));
    }

    @Test
    public void testUnorderedListDoesNotContainMisleadingHref() {
        assertFalse(ul(li(a("Link").withHref("http://example.com"))).render().contains("href=\"http://false.com\""));
    }

    @Test
    public void testUnorderedListDoesNotContainIncorrectAriaLabel() {
        assertFalse(ul().attr("aria-label", "List").render().contains("aria-label=\"Incorrect\""));
    }

    /** Testing Unordered list end here.
     * *************************************************************************************
     * ******************************************************************************************
     */


    /**
     * Testing header tag starts here.
     * *******************************************************************************************
     * *****************************************************************************************
     */




    @Test
    public void testHeaderTags() {
        assertEquals("<h1>Header 1</h1>", h1("Header 1").render());
        assertEquals("<h2>Header 2</h2>", h2("Header 2").render());
        assertEquals("<h3>Header 3</h3>", h3("Header 3").render());
        // Continue for h4, h5, h6
    }
    @Test
    public void testEmptyHeader() {
        assertEquals("<header></header>", header().render());
    }

    @Test
    public void testHeaderWithText() {
        assertEquals("<header>Hello World</header>", header("Hello World").render());
    }

    @Test
    public void testHeaderWithMultipleTextElements() {
        assertEquals("<header>Hello World, Welcome</header>", header(text("Hello World"), text(", Welcome")).render());
    }

    @Test
    public void testHeaderWithNestedElements() {
        assertEquals("<header><h1>Title</h1><p>Description</p></header>",
                header(
                        h1("Title"),
                        p("Description")
                ).render());
    }

    @Test
    public void testHeaderWithId() {
        assertEquals("<header id=\"main-header\"></header>", header().withId("main-header").render());
    }

    @Test
    public void testHeaderWithClass() {
        assertEquals("<header class=\"header-class\"></header>", header().withClass("header-class").render());
    }

    @Test
    public void testHeaderWithStyle() {
        assertEquals("<header style=\"color: red;\"></header>", header().withStyle("color: red;").render());
    }

    @Test
    public void testHeaderWithCustomAttributes() {
        assertEquals("<header custom=\"value\"></header>", header().attr("custom", "value").render());
    }

    @Test
    public void testHeaderWithLink() {
        assertEquals("<header><a href=\"https://example.com\">Visit</a></header>",
                header(
                        a("Visit").withHref("https://example.com")
                ).render());
    }

    @Test
    public void testHeaderWithNavigation() {
        assertEquals("<header><nav><a href=\"#home\">Home</a><a href=\"#about\">About</a></nav></header>",
                header(
                        nav(
                                a("Home").withHref("#home"),
                                a("About").withHref("#about")
                        )
                ).render());
    }

    @Test
    public void testHeaderWithImage() {
        assertEquals("<header><img src=\"logo.png\" alt=\"Logo\"></header>",
                header(
                        img().withSrc("logo.png").withAlt("Logo")
                ).render());
    }

    @Test
    public void testHeaderWithComplexStructure() {
        assertEquals("<header><div><h1>Title</h1><p>Description under title</p></div></header>",
                header(
                        div(
                                h1("Title"),
                                p("Description under title")
                        )
                ).render());
    }

    @Test
    public void testHeaderWithRoleAttribute() {
        assertEquals("<header role=\"banner\"></header>", header().attr("role", "banner").render());
    }

    @Test
    public void testHeaderWithAriaLabel() {
        assertEquals("<header aria-label=\"Main Header\"></header>", header().attr("aria-label", "Main Header").render());
    }

    @Test
    public void testHeaderWithMixedContent() {
        assertEquals("<header><h1>Main</h1><nav><ul><li><a href=\"#home\">Home</a></li><li><a href=\"#services\">Services</a></li></ul></nav></header>",
                header(
                        h1("Main"),
                        nav(
                                ul(
                                        li(a("Home").withHref("#home")),
                                        li(a("Services").withHref("#services"))
                                )
                        )
                ).render());
    }

    @Test
    public void testHeaderWithEventAttribute() {
        assertEquals("<header onclick=\"alert(&#x27;Clicked!&#x27;);\"></header>", header().attr("onclick", "alert('Clicked!');").render());
    }

    @Test
    public void testHeaderWithMultipleClasses() {
        assertEquals("<header class=\"class1 class2\"></header>", header().withClass("class1 class2").render());
    }

    @Test
    public void testHeaderWithEscapedText() {
        assertEquals("<header>&lt;script&gt;alert(&#x27;XSS&#x27;);&lt;/script&gt;</header>",
                header(text("<script>alert('XSS');</script>")).render());
    }

    @Test
    public void testHeaderWithDynamicContent() {
        assertEquals("<header>Dynamic</header>", header(text(generateDynamicContent())).render());
    }
    @Test
    public void testEmptyHeader1() {
        assertTrue(header().render().equals("<header></header>"));
    }

    @Test
    public void testHeaderWithText1() {
        assertTrue(header("Hello World").render().equals("<header>Hello World</header>"));
    }

    @Test
    public void testHeaderWithMultipleTextElements1() {
        assertTrue(header(text("Hello World"), text(", Welcome")).render().equals("<header>Hello World, Welcome</header>"));
    }

    @Test
    public void testHeaderWithNestedElements1() {
        assertTrue(header(
                h1("Title"),
                p("Description")
        ).render().equals("<header><h1>Title</h1><p>Description</p></header>"));
    }

    @Test
    public void testHeaderWithId1() {
        assertTrue(header().withId("main-header").render().equals("<header id=\"main-header\"></header>"));
    }

    @Test
    public void testHeaderWithClass1() {
        assertTrue(header().withClass("header-class").render().equals("<header class=\"header-class\"></header>"));
    }

    @Test
    public void testHeaderWithStyle1() {
        assertTrue(header().withStyle("color: red;").render().equals("<header style=\"color: red;\"></header>"));
    }

    @Test
    public void testHeaderWithCustomAttributes1() {
        assertTrue(header().attr("custom", "value").render().equals("<header custom=\"value\"></header>"));
    }

    @Test
    public void testHeaderWithLink1() {
        assertTrue(header(
                a("Visit").withHref("https://example.com")
        ).render().equals("<header><a href=\"https://example.com\">Visit</a></header>"));
    }

    @Test
    public void testHeaderWithNavigation1() {
        assertTrue(header(
                nav(
                        a("Home").withHref("#home"),
                        a("About").withHref("#about")
                )
        ).render().equals("<header><nav><a href=\"#home\">Home</a><a href=\"#about\">About</a></nav></header>"));
    }

    @Test
    public void testHeaderWithImage1() {
        assertTrue(header(
                img().withSrc("logo.png").withAlt("Logo")
        ).render().equals("<header><img src=\"logo.png\" alt=\"Logo\"></header>"));
    }

    @Test
    public void testHeaderWithComplexStructure1() {
        assertTrue(header(
                div(
                        h1("Title"),
                        p("Description under title")
                )
        ).render().equals("<header><div><h1>Title</h1><p>Description under title</p></div></header>"));
    }

    @Test
    public void testHeaderWithRoleAttribute1() {
        assertTrue(header().attr("role", "banner").render().equals("<header role=\"banner\"></header>"));
    }

    @Test
    public void testHeaderWithAriaLabel1() {
        assertTrue(header().attr("aria-label", "Main Header").render().equals("<header aria-label=\"Main Header\"></header>"));
    }

    @Test
    public void testHeaderWithMixedContent1() {
        assertTrue(header(
                h1("Main"),
                nav(
                        ul(
                                li(a("Home").withHref("#home")),
                                li(a("Services").withHref("#services"))
                        )
                )
        ).render().equals("<header><h1>Main</h1><nav><ul><li><a href=\"#home\">Home</a></li><li><a href=\"#services\">Services</a></li></ul></nav></header>"));
    }

    @Test
    public void testHeaderWithEventAttribute1() {
        assertFalse(header().attr("onclick", "alert('Clicked!');").render().equals("<header onclick=\"alert('Clicked!');\"></header>"));
    }

    @Test
    public void testHeaderWithMultipleClasses1() {
        assertTrue(header().withClass("class1 class2").render().equals("<header class=\"class1 class2\"></header>"));
    }

    @Test
    public void testHeaderWithEscapedText1() {
        assertFalse(header(text("<script>alert('XSS');</script>")).render().equals("<header>&lt;script&gt;alert('XSS');&lt;/script&gt;</header>"));
    }

    @Test
    public void testHeaderWithDynamicContent1() {
        String dynamicContent = "Dynamic Content";
        assertTrue(header(text(dynamicContent)).render().equals("<header>Dynamic Content</header>"));
    }

    /**Testing Header tag ends here
     * ********************************************************************************
     * *************************************************************************************
     */


    /** Testing Image tag starts here*
     * ************************************************************************************
     *
     */


    @Test
    public void testImageTag() {
        String expectedHtml = "<img src=\"image.jpg\" alt=\"An image\" width=\"500\" height=\"300\">";
        String actualHtml = img().withSrc("image.jpg").withAlt("An image").withWidth("500").withHeight("300").render();
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testImageWithSource() {
        assertEquals("<img src=\"image.jpg\">", img().withSrc("image.jpg").render());
    }

    @Test
    public void testImageWithAltText() {
        assertEquals("<img src=\"image.jpg\" alt=\"Description\">", img().withSrc("image.jpg").withAlt("Description").render());
    }

    @Test
    public void testImageWithDimensions() {
        assertEquals("<img src=\"image.jpg\" width=\"200\" height=\"100\">",
                img().withSrc("image.jpg").withWidth("200").withHeight("100").render());
    }

    @Test
    public void testImageWithAllAttributes() {
        assertEquals("<img src=\"image.jpg\" alt=\"Description\" width=\"200\" height=\"100\" class=\"responsive\">",
                img().withSrc("image.jpg").withAlt("Description").withWidth("200").withHeight("100").withClass("responsive").render());
    }

    @Test
    public void testImageWithoutSource() {
        assertEquals("<img>", img().render());
    }

    @Test
    public void testImageWithCustomAttribute() {
        assertEquals("<img src=\"image.jpg\" custom=\"value\">",
                img().withSrc("image.jpg").attr("custom", "value").render());
    }

    @Test
    public void testImageWithRoleAttribute() {
        assertEquals("<img src=\"image.jpg\" role=\"presentation\">",
                img().withSrc("image.jpg").attr("role", "presentation").render());
    }

    @Test
    public void testImageWithStyle() {
        assertEquals("<img src=\"image.jpg\" style=\"display:block;\">",
                img().withSrc("image.jpg").withStyle("display:block;").render());
    }

    @Test
    public void testImageWithMultipleClasses() {
        assertEquals("<img src=\"image.jpg\" class=\"class1 class2\">",
                img().withSrc("image.jpg").withClass("class1 class2").render());
    }

    @Test
    public void testImageWithEscapedTextInAlt() {
        assertEquals("<img src=\"image.jpg\" alt=\"&lt;script&gt;alert(&#x27;XSS&#x27;);&lt;/script&gt;\">",
                img().withSrc("image.jpg").withAlt("<script>alert('XSS');</script>").render());
    }

    @Test
    public void testImageWithLinkWrapper() {
        assertEquals("<a href=\"http://example.com\"><img src=\"image.jpg\" alt=\"Navigate\"></a>",
                a(img().withSrc("image.jpg").withAlt("Navigate")).withHref("http://example.com").render());
    }

    @Test
    public void testImageWithAriaLabel() {
        assertEquals("<img src=\"image.jpg\" aria-label=\"Descriptive label\">",
                img().withSrc("image.jpg").attr("aria-label", "Descriptive label").render());
    }

    @Test
    public void testImageWithLazyLoading() {
        assertEquals("<img src=\"image.jpg\" loading=\"lazy\">",
                img().withSrc("image.jpg").attr("loading", "lazy").render());
    }

    @Test
    public void testImageWithCrossOrigin() {
        assertEquals("<img src=\"image.jpg\" crossorigin=\"anonymous\">",
                img().withSrc("image.jpg").attr("crossorigin", "anonymous").render());
    }

    @Test
    public void testImageWithMap() {
        assertEquals("<img src=\"image.jpg\" usemap=\"#map\">",
                img().withSrc("image.jpg").attr("usemap", "#map").render());
    }

    @Test
    public void testImageWithDataAttribute() {
        assertEquals("<img src=\"image.jpg\" data-name=\"value\">",
                img().withSrc("image.jpg").attr("data-name", "value").render());
    }

    @Test
    public void testImageWithSrcset() {
        assertEquals("<img srcset=\"image.jpg 1x, image2x.jpg 2x\">",
                img().attr("srcset", "image.jpg 1x, image2x.jpg 2x").render());
    }

    @Test
    public void testImageWithSource1() {
        assertTrue(img().withSrc("image.jpg").render().equals("<img src=\"image.jpg\">"));
    }

    @Test
    public void testImageWithAltText1() {
        assertTrue(img().withSrc("image.jpg").withAlt("Description").render().equals("<img src=\"image.jpg\" alt=\"Description\">"));
    }

    @Test
    public void testImageWithDimensions1() {
        assertTrue(img().withSrc("image.jpg").withWidth("200").withHeight("100").render().equals("<img src=\"image.jpg\" width=\"200\" height=\"100\">"));
    }

    @Test
    public void testImageWithAllAttributes1() {
        assertTrue(img().withSrc("image.jpg").withAlt("Description").withWidth("200").withHeight("100").withClass("responsive").render().equals("<img src=\"image.jpg\" alt=\"Description\" width=\"200\" height=\"100\" class=\"responsive\">"));
    }

    @Test
    public void testImageWithoutSource1() {
        assertTrue(img().render().equals("<img>"));
    }

    @Test
    public void testImageWithCustomAttribute1() {
        assertTrue(img().withSrc("image.jpg").attr("custom", "value").render().equals("<img src=\"image.jpg\" custom=\"value\">"));
    }

    @Test
    public void testImageWithRoleAttribute1() {
        assertTrue(img().withSrc("image.jpg").attr("role", "presentation").render().equals("<img src=\"image.jpg\" role=\"presentation\">"));
    }

    @Test
    public void testImageWithStyle1() {
        assertTrue(img().withSrc("image.jpg").withStyle("display:block;").render().equals("<img src=\"image.jpg\" style=\"display:block;\">"));
    }

    @Test
    public void testImageWithMultipleClasses1() {
        assertTrue(img().withSrc("image.jpg").withClass("class1 class2").render().equals("<img src=\"image.jpg\" class=\"class1 class2\">"));
    }

    @Test
    public void testImageWithEscapedTextInAlt1() {
        assertFalse(img().withSrc("image.jpg").withAlt("<script>alert('XSS');</script>").render().equals("<img src=\"image.jpg\" alt=\"&lt;script&gt;alert('XSS');&lt;/script&gt;\">"));
    }

    @Test
    public void testImageWithLinkWrapper1() {
        assertTrue(a(img().withSrc("image.jpg").withAlt("Navigate")).withHref("http://example.com").render().equals("<a href=\"http://example.com\"><img src=\"image.jpg\" alt=\"Navigate\"></a>"));
    }

    @Test
    public void testImageWithAriaLabel1() {
        assertTrue(img().withSrc("image.jpg").attr("aria-label", "Descriptive label").render().equals("<img src=\"image.jpg\" aria-label=\"Descriptive label\">"));
    }

    @Test
    public void testImageWithLazyLoading1() {
        assertTrue(img().withSrc("image.jpg").attr("loading", "lazy").render().equals("<img src=\"image.jpg\" loading=\"lazy\">"));
    }

    @Test
    public void testImageWithCrossOrigin1() {
        assertTrue(img().withSrc("image.jpg").attr("crossorigin", "anonymous").render().equals("<img src=\"image.jpg\" crossorigin=\"anonymous\">"));
    }

    @Test
    public void testImageWithMap1() {
        assertTrue(img().withSrc("image.jpg").attr("usemap", "#map").render().equals("<img src=\"image.jpg\" usemap=\"#map\">"));
    }

    @Test
    public void testImageWithDataAttribute1() {
        assertTrue(img().withSrc("image.jpg").attr("data-name", "value").render().equals("<img src=\"image.jpg\" data-name=\"value\">"));
    }

    @Test
    public void testImageWithSrcset1() {
        assertTrue(img().attr("srcset", "image.jpg 1x, image2x.jpg 2x").render().equals("<img srcset=\"image.jpg 1x, image2x.jpg 2x\">"));
    }

    @Test
    public void testImageWithRolePresentation() {
        assertTrue(img().withSrc("image.jpg").attr("role", "presentation").render().equals("<img src=\"image.jpg\" role=\"presentation\">"));
    }

    @Test
    public void testImageWithNoSourceAndAlt() {
        assertTrue(img().withAlt("No source").render().equals("<img alt=\"No source\">"));
    }

    @Test
    public void testImageWithCustomDataAttribute() {
        assertTrue(img().withSrc("image.jpg").attr("data-type", "profile").render().equals("<img src=\"image.jpg\" data-type=\"profile\">"));
    }

    @Test
    public void testImageWithMultipleStyles() {
        assertTrue(img().withSrc("image.jpg").withStyle("border: 1px solid #ccc; display: block;").render().equals("<img src=\"image.jpg\" style=\"border: 1px solid #ccc; display: block;\">"));
    }

    @Test
    public void testImageWithAccessibilityFeatures() {
        assertTrue(img().withSrc("image.jpg").attr("aria-hidden", "true").render().equals("<img src=\"image.jpg\" aria-hidden=\"true\">"));
    }

    @Test
    public void testImageWithEmptyAltForDecorative() {
        assertTrue(img().withSrc("decorative.jpg").withAlt("").render().equals("<img src=\"decorative.jpg\" alt=\"\">"));
    }

    @Test
    public void testImageAsBlockElement() {
        assertTrue(img().withSrc("block.jpg").withStyle("display: block;").render().equals("<img src=\"block.jpg\" style=\"display: block;\">"));
    }

    @Test
    public void testImageWithLazyLoadingAndAspectRatio() {
        assertTrue(img().withSrc("lazy.jpg").attr("loading", "lazy").attr("aspect-ratio", "16/9").render().equals("<img src=\"lazy.jpg\" loading=\"lazy\" aspect-ratio=\"16/9\">"));
    }

    @Test
    public void testImageWithCrossoriginAnonymous() {
        assertTrue(img().withSrc("crossorigin.jpg").attr("crossorigin", "anonymous").render().equals("<img src=\"crossorigin.jpg\" crossorigin=\"anonymous\">"));
    }

    @Test
    public void testImageDoesNotIncludeUnsetSrc() {
        assertFalse(img().render().contains("src="));
    }

    @Test
    public void testImageDoesNotIncludeUnspecifiedAlt() {
        assertFalse(img().withSrc("image.jpg").render().contains("alt="));
    }

    @Test
    public void testImageDoesNotContainStyleWhenNotSet() {
        assertFalse(img().withSrc("image.jpg").render().contains("style="));
    }

    @Test
    public void testImageDoesNotMisuseClassAttribute() {
        assertFalse(img().withSrc("image.jpg").withClass("img-responsive").render().contains("class=\"img-wrong\""));
    }

    @Test
    public void testImageDoesNotContainIncorrectHeight() {
        assertFalse(img().withSrc("image.jpg").withHeight("100").render().contains("height=\"200\""));
    }

    @Test
    public void testImageDoesNotContainIncorrectWidth() {
        assertFalse(img().withSrc("image.jpg").withWidth("100").render().contains("width=\"200\""));
    }

    @Test
    public void testImageDoesNotHaveCrossOriginByDefault() {
        assertFalse(img().withSrc("image.jpg").render().contains("crossorigin="));
    }

    @Test
    public void testImageDoesNotIncludeIncorrectSrcset() {
        assertFalse(img().withSrc("image.jpg").attr("srcset", "image.jpg 1x, image2x.jpg 2x").render().contains("srcset=\"wrong.jpg 1x, wrong2x.jpg 2x\""));
    }

    @Test
    public void testImageDoesNotRenderAsSelfClosingTagIncorrectly() {
        assertFalse(img().withSrc("image.jpg").render().equals("<img src=\"image.jpg\"/>"));
    }

    @Test
    public void testImageWithNoHrefDoesNotAppearInLink() {
        String imgRendered = img().withSrc("image.jpg").render();
        String anchorRendered = a(imgRendered).withHref("http://example.com").render();
        assertFalse(anchorRendered.equals("<a href=\"http://example.com\"><img src=\"image.jpg\"></a>"));
    }

    @Test
    public void testImageDoesNotContainRandomAttributes() {
        assertTrue(img().withSrc("image.jpg").attr("random", "randomValue").render().contains("random=\"randomValue\""));
    }

    @Test
    public void testImageDoesNotIncludeRoleWhenNotSet() {
        assertFalse(img().withSrc("image.jpg").render().contains("role="));
    }

    /** Testing Image tag ends here*
     * ************************************************************************************
     *
     */








    @Test
    public void testFormElements() {
        String expectedForm = "<form method=\"post\" action=\"submit.php\"><input type=\"text\" name=\"username\" required></form>";
        String actualForm = form().withMethod("post").withAction("submit.php")
                .with(input().withType("text").withName("username").isRequired()).render();
        assertEquals(expectedForm, actualForm);
    }

    /** Testing Table tag starts here*
     * ************************************************************************************
     *
     */








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
    public void testEmptyTable() {
        assertEquals("<table></table>", table().render());
    }

    @Test
    public void testTableWithSingleRowAndCell() {
        assertEquals("<table><tr><td>Content</td></tr></table>", table(tr(td("Content"))).render());
    }

    @Test
    public void testTableWithMultipleRows() {
        assertEquals("<table><tr><td>Row 1</td></tr><tr><td>Row 2</td></tr></table>",
                table(
                        tr(td("Row 1")),
                        tr(td("Row 2"))
                ).render());
    }

    @Test
    public void testTableWithHeaderAndRows() {
        assertEquals("<table><thead><tr><th>Header</th></tr></thead><tbody><tr><td>Data</td></tr></tbody></table>",
                table(
                        thead(tr(th("Header"))),
                        tbody(tr(td("Data")))
                ).render());
    }

    @Test
    public void testTableWithMultipleColumns() {
        assertEquals("<table><tr><td>Column 1</td><td>Column 2</td></tr></table>",
                table(
                        tr(td("Column 1"), td("Column 2"))
                ).render());
    }

    @Test
    public void testTableWithAttributes() {
        assertEquals("<table class=\"data-table\" id=\"table1\"></table>",
                table().withClass("data-table").withId("table1").render());
    }

    @Test
    public void testComplexTable1() {
        assertEquals("<table><tr><th>Name</th><th>Age</th></tr><tr><td>John</td><td>30</td></tr></table>",
                table(
                        tr(th("Name"), th("Age")),
                        tr(td("John"), td("30"))

                ).render());
    }

    @Test
    public void testTableWithCaption() {
        assertEquals("<table><caption>Table Caption</caption><tr><td>Data</td></tr></table>",
                table(
                        caption("Table Caption"),
                        tr(td("Data"))
                ).render());
    }

    @Test
    public void testTableWithFooter() {
        assertEquals("<table><tfoot><tr><td>Footer</td></tr></tfoot></table>",
                table(
                        tfoot(tr(td("Footer")))
                ).render());
    }

    @Test
    public void testTableWithScopedHeader() {
        assertEquals("<table><tr><th scope=\"col\">Column Header</th></tr><tr><td>Data</td></tr></table>",
                table(
                        tr(th("Column Header").attr("scope", "col")),
                        tr(td("Data"))
                ).render());
    }

    @Test
    public void testTableWithColgroup() {
        assertEquals("<table><colgroup><col span=\"2\" style=\"background-color:red\"></colgroup><tr><td>1</td><td>2</td></tr></table>",
                table(
                        colgroup(col().attr("span", "2").withStyle("background-color:red")),
                        tr(td("1"), td("2"))
                ).render());
    }

    @Test
    public void testEmptyTable1() {
        assertTrue(table().render().equals("<table></table>"));
    }

    @Test
    public void testTableWithSingleRowAndCell1() {
        assertTrue(table(tr(td("Content"))).render().equals("<table><tr><td>Content</td></tr></table>"));
    }

    @Test
    public void testTableWithMultipleRows1() {
        assertTrue(table(
                tr(td("Row 1")),
                tr(td("Row 2"))
        ).render().equals("<table><tr><td>Row 1</td></tr><tr><td>Row 2</td></tr></table>"));
    }

    @Test
    public void testTableWithHeaderAndRows1() {
        assertTrue(table(
                thead(tr(th("Header"))),
                tbody(tr(td("Data")))
        ).render().equals("<table><thead><tr><th>Header</th></tr></thead><tbody><tr><td>Data</td></tr></tbody></table>"));
    }

    @Test
    public void testTableWithMultipleColumns1() {
        assertTrue(table(
                tr(td("Column 1"), td("Column 2"))
        ).render().equals("<table><tr><td>Column 1</td><td>Column 2</td></tr></table>"));
    }

    @Test
    public void testTableWithAttributes1() {
        assertTrue(table().withClass("data-table").withId("table1").render().equals("<table class=\"data-table\" id=\"table1\"></table>"));
    }

    @Test
    public void testComplexTable11() {
        assertTrue(table(
                tr(th("Name"), th("Age")),
                tr(td("John"), td("30")),
                tr(td("Jane"), td("25"))
        ).render().equals("<table><tr><th>Name</th><th>Age</th></tr><tr><td>John</td><td>30</td></tr><tr><td>Jane</td><td>25</td></tr></table>"));
    }

    @Test
    public void testTableWithCaption1() {
        assertTrue(table(
                caption("Table Caption"),
                tr(td("Data"))
        ).render().equals("<table><caption>Table Caption</caption><tr><td>Data</td></tr></table>"));
    }

    @Test
    public void testTableWithFooter1() {
        assertTrue(table(
                tfoot(tr(td("Footer")))
        ).render().equals("<table><tfoot><tr><td>Footer</td></tr></tfoot></table>"));
    }

    @Test
    public void testTableWithScopedHeader1() {
        assertTrue(table(
                tr(th("Column Header").attr("scope", "col")),
                tr(td("Data"))
        ).render().equals("<table><tr><th scope=\"col\">Column Header</th></tr><tr><td>Data</td></tr></table>"));
    }

    @Test
    public void testTableWithColgroup1() {
        assertTrue(table(
                colgroup(col().attr("span", "2").withStyle("background-color:red")),
                tr(td("1"), td("2"))
        ).render().equals("<table><colgroup><col span=\"2\" style=\"background-color:red\"></colgroup><tr><td>1</td><td>2</td></tr></table>"));
    }

    @Test
    public void testTableIncludesProperAttributes() {
        String htmlOutput = table(tr(td("Data").withClass("data"))).withClass("table-class").render();
        assertTrue(htmlOutput.contains("class=\"table-class\"") && htmlOutput.contains("class=\"data\""));
    }

    @Test
    public void testTableRenderProperStructure() {
        String expected = "<table><tr><td>Info</td></tr></table>";
        assertTrue(table(tr(td("Info"))).render().equals(expected));
    }

    @Test
    public void testTableDoesNotIncludeRandomAttributes() {
        String render = table().attr("random", "value").render();
        assertTrue(render.contains("random=\"value\""));
    }
    @Test
    public void testTableDoesNotContainUnsetClass() {
        assertFalse(table(tr(td("Content"))).render().contains("class="));
    }

    @Test
    public void testTableDoesNotContainIdWhenNotSet() {
        assertFalse(table(tr(td("Content"))).render().contains("id="));
    }

    @Test
    public void testTableDoesNotIncludeStyleWhenNotSet() {
        assertFalse(table(tr(td("Content"))).render().contains("style="));
    }

    @Test
    public void testTableDoesNotIncludeScriptTag() {
        assertFalse(table(tr(td("<script>alert('hack');</script>"))).render().contains("<script>"));
    }

    @Test
    public void testTableDoesNotRenderExtraRows() {
        assertFalse(table(tr(td("Row 1")), tr(td("Row 2"))).render().contains("<tr><td>Row 3</td></tr>"));
    }

    @Test
    public void testTableDoesNotMisuseAttributes() {
        assertFalse(table().withClass("my-class").render().contains("class=\"another-class\""));
    }

    @Test
    public void testTableDoesNotHaveFooterWhenNotAdded() {
        assertFalse(table(tr(td("Data"))).render().contains("<tfoot>"));
    }

    @Test
    public void testTableWithIncorrectAttribute() {
        assertFalse(table(tr(td("Data")).withClass("correct")).render().contains("class=\"incorrect\""));
    }

    @Test
    public void testTableDoesNotIncludeHeaderInBody() {
        assertFalse(table(tbody(tr(th("Header")))).render().contains("<thead>"));
    }

    @Test
    public void testTableDoesNotContainMismatchedTags() {
        assertFalse(table(tr(td("Data"))).render().contains("</th>"));
    }

    @Test
    public void testTableDoesNotRenderAsSelfClosing() {
        assertFalse(table().render().equals("<table/>"));
    }

    @Test
    public void testTableDoesNotRenderNestedTablesIncorrectly() {
        assertFalse(table(tr(td(table(tr(td("Nested")))))).render().contains("<table><table>"));
    }

    @Test
    public void testTableDoesNotContainEscapedTextInCells() {
        assertTrue(table(tr(td("<b>Bold</b>"))).render().contains("&lt;b&gt;Bold&lt;/b&gt;"));
    }

    @Test
    public void testTableDoesNotIncludeRandomAttributes3() {
        assertTrue(table().attr("random", "value").render().contains("random=\"value\""));
    }

    @Test
    public void testTableWithSpecificClass() {
        assertTrue(table().withClass("specific-class").render().contains("class=\"specific-class\""));
    }

    @Test
    public void testTableWithMultipleHeaders() {
        String rendered = table(thead(tr(th("Header1")), tr(th("Header2")))).render();
        assertTrue(rendered.contains("<th>Header1</th>") && rendered.contains("<th>Header2</th>"));
    }

    @Test
    public void testTableWithMultipleTbodySections() {
        String rendered = table(
                tbody(tr(td("Row1"))),
                tbody(tr(td("Row2")))
        ).render();
        assertTrue(rendered.contains("<tbody><tr><td>Row1</td></tr></tbody><tbody><tr><td>Row2</td></tr></tbody>"));
    }

    @Test
    public void testTableWithColspanInHeader() {
        assertTrue(table(thead(tr(th("Spanned Header").attr("colspan", "2")))).render().contains("colspan=\"2\""));
    }

    @Test
    public void testTableWithRowspanInDataCell() {
        assertTrue(table(tr(td("Spanned Data").attr("rowspan", "3"))).render().contains("rowspan=\"3\""));
    }

    @Test
    public void testTableWithInlineStyle() {
        assertTrue(table().withStyle("margin-top: 10px;").render().contains("style=\"margin-top: 10px;\""));
    }

    @Test
    public void testTableWithLinkedCSS() {
        assertFalse(table().withClass("linked-class").render().contains("href=\"styles.css\""));
    }

    @Test
    public void testComplexTableWithNestedElements() {
        String rendered = table(tr(td(div(p("Paragraph inside cell"))))).render();
        assertTrue(rendered.contains("<p>Paragraph inside cell</p>"));
    }

    @Test
    public void testTableWithCaptionAndSummary() {
        String rendered = table(caption("Overview"), tr(td("Data"))).attr("summary", "Table Summary").render();
        assertTrue(rendered.contains("<caption>Overview</caption>") && rendered.contains("summary=\"Table Summary\""));
    }

    @Test
    public void testTableDoesNotRenderScript() {
        assertFalse(table(tr(td("<script>alert('test');</script>"))).render().contains("<script>"));
    }

    @Test
    public void testEmptyTableWithAttributes() {
        assertTrue(table().withId("empty-table").withClass("no-rows").render().equals("<table id=\"empty-table\" class=\"no-rows\"></table>"));
    }

    @Test
    public void testTableWithSortedColumnIndicator() {
        assertTrue(table(tr(th("Name"), th("Age").withClass("sorted"))).render().contains("class=\"sorted\""));
    }

    @Test
    public void testTableWithAccessibleAriaLabels() {
        assertTrue(table(tr(th("Name"), th("Age"))).attr("aria-label", "Person Details").render().contains("aria-label=\"Person Details\""));
    }

    @Test
    public void testTableWithNoBorderSpecified() {
        assertFalse(table().render().contains("border="));
    }

    @Test
    public void testTableDoesNotIncludeInvalidHTML() {
        assertFalse(table(tr(td("Valid Content"))).render().contains("<div>Invalid Placement</div>"));
    }

    @Test
    public void testTableDoesNotDuplicateRows() {
        String output = table(tr(td("Row")), tr(td("Row"))).render();
        assertFalse(output.equals("<table><tr><td>Row</td></tr><tr><td>Row</td></tr><tr><td>Row</td></tr></table>"));
    }

    @Test
    public void testTableWithThematicBreakWithinCell() {
        assertTrue(table(tr(td(hr()))).render().contains("<hr>"));
    }

    @Test
    public void testTableWithoutExplicitTbodyDoesNotIncludeTbodyTag() {
        assertFalse(table(tr(td("Data"))).render().contains("<tbody>"));
    }

    @Test
    public void testTableWithExplicitValignAttributeOnTd() {
        assertTrue(table(tr(td("Valigned Data").attr("valign", "top"))).render().contains("valign=\"top\""));
    }

    @Test
    public void testTableDoesNotContainDeprecatedCellpaddingAttribute() {
        assertTrue(table().attr("cellpadding", "5").render().contains("cellpadding="));
    }

    /** Testing table ends here**
     * ***************************************************************************************
     * ***************************************************************************************
     */







    /** Testing LinksWithAttributes starts here**
     * ***************************************************************************************
     * ***************************************************************************************
     */





    @Test
    public void testLinksWithAttributes() {
        String expectedHtml = "<a href=\"https://example.com\" target=\"_blank\">Visit Example</a>";
        String actualHtml = a("Visit Example").withHref("https://example.com").withTarget("_blank").render();
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testLinkWithHref() {
        assertEquals("<a href=\"https://example.com\">Visit Example</a>", a("Visit Example").withHref("https://example.com").render());
    }

    @Test
    public void testLinkWithTitle() {
        assertEquals("<a href=\"#\" title=\"Go to Top\">Top</a>", a("Top").withHref("#").withTitle("Go to Top").render());
    }

    @Test
    public void testLinkWithTarget() {
        assertEquals("<a href=\"https://example.com\" target=\"_blank\">Open in New Tab</a>", a("Open in New Tab").withHref("https://example.com").withTarget("_blank").render());
    }

    @Test
    public void testLinkWithRel() {
        assertEquals("<a href=\"https://example.com\" rel=\"noopener noreferrer\">Secure Link</a>", a("Secure Link").withHref("https://example.com").withRel("noopener noreferrer").render());
    }

    @Test
    public void testLinkWithMultipleAttributes() {
        assertEquals("<a href=\"https://example.com\" title=\"Visit Example\" target=\"_blank\">Example</a>", a("Example").withHref("https://example.com").withTitle("Visit Example").withTarget("_blank").render());
    }

    @Test
    public void testLinkWithClass() {
        assertEquals("<a href=\"#\" class=\"button\">Button</a>", a("Button").withHref("#").withClass("button").render());
    }

    @Test
    public void testLinkWithId() {
        assertEquals("<a href=\"#\" id=\"top-link\">Go to Top</a>", a("Go to Top").withHref("#").withId("top-link").render());
    }

    @Test
    public void testLinkWithStyle() {
        assertEquals("<a href=\"#\" style=\"color: red;\">Red Link</a>", a("Red Link").withHref("#").withStyle("color: red;").render());
    }



    @Test
    public void testLinkWithRoleAttribute() {
        assertEquals("<a href=\"#\" role=\"button\">Button Role</a>", a("Button Role").withHref("#").attr("role", "button").render());
    }

    @Test
    public void testLinkWithAriaLabel() {
        assertEquals("<a href=\"#\" aria-label=\"More Information\">Info</a>", a("Info").withHref("#").attr("aria-label", "More Information").render());
    }

    @Test
    public void testLinkWithCustomDataAttribute() {
        assertEquals("<a href=\"#\" data-info=\"custom\">Custom Data</a>", a("Custom Data").withHref("#").attr("data-info", "custom").render());
    }

    @Test
    public void testLinkWithOnClickJavaScript() {
        assertEquals("<a href=\"#\" onclick=\"alert(&#x27;Clicked!&#x27;);\">Click Me</a>", a("Click Me").withHref("#").attr("onclick", "alert('Clicked!');").render());
    }

    @Test
    public void testLinkWithNoHref() {
        assertEquals("<a>No href</a>", a("No href").render());
    }

    @Test
    public void testLinkWithEmptyHref() {
        assertEquals("<a href=\"\">Empty href</a>", a("Empty href").withHref("").render());
    }

    @Test
    public void testLinkWithMailto() {
        assertEquals("<a href=\"mailto:someone@example.com\">Send Email</a>", a("Send Email").withHref("mailto:someone@example.com").render());
    }

    @Test
    public void testLinkWithTel() {
        assertEquals("<a href=\"tel:+1234567890\">Call Us</a>", a("Call Us").withHref("tel:+1234567890").render());
    }

    @Test
    public void testLinkWithHashTag() {
        assertEquals("<a href=\"#section\">Jump to Section</a>", a("Jump to Section").withHref("#section").render());
    }

    @Test
    public void testLinkWithExternalRel() {
        assertEquals("<a href=\"https://external.com\" rel=\"external\">Visit External</a>", a("Visit External").withHref("https://external.com").withRel("external").render());
    }



    @Test
    public void testLinkWithSpecialCharactersInText() {
        assertEquals("<a href=\"#\">Café &amp; Bar</a>", a("Café & Bar").withHref("#").render());
    }

    @Test
    public void testLinkWithEncodedURL() {
        assertEquals("<a href=\"https://example.com?q=hello%20world\">Search</a>", a("Search").withHref("https://example.com?q=hello%20world").render());
    }

    @Test
    public void testLinkWithEscapedCharacters() {
        assertEquals("<a href=\"#\">&lt;script&gt;alert(&#x27;XSS&#x27;);&lt;/script&gt;</a>", a("<script>alert('XSS');</script>").withHref("#").render());
    }

    @Test
    public void testLinkWithNonStandardAttributes() {
        assertEquals("<a href=\"#\" nonstandard=\"true\">Non-standard</a>", a("Non-standard").withHref("#").attr("nonstandard", "true").render());
    }

    @Test
    public void testLinkWithMultipleNestedElementsAndClasses() {
        assertEquals("<a href=\"#\"><div class=\"container\"><p class=\"text\">Nested Content</p></div></a>", a(div(p("Nested Content").withClass("text")).withClass("container")).withHref("#").render());
    }

    @Test
    public void testLinkWithTabindex() {
        assertEquals("<a href=\"#\" tabindex=\"1\">Accessible Link</a>", a("Accessible Link").withHref("#").attr("tabindex", "1").render());
    }

    @Test
    public void testLinkWithNoFollowRel() {
        assertEquals("<a href=\"https://external.com\" rel=\"nofollow\">No Follow Link</a>", a("No Follow Link").withHref("https://external.com").withRel("nofollow").render());
    }

    @Test
    public void testLinkWithMultipleRelValues() {
        assertEquals("<a href=\"#\" rel=\"nofollow noopener\">Secure Link</a>", a("Secure Link").withHref("#").withRel("nofollow noopener").render());
    }

    @Test
    public void testLinkWithLangAttribute() {
        assertEquals("<a href=\"#\" lang=\"en\">English Link</a>", a("English Link").withHref("#").attr("lang", "en").render());
    }

    @Test
    public void testLinkWithAccessKey() {
        assertEquals("<a href=\"#\" accesskey=\"g\">Quick Access</a>", a("Quick Access").withHref("#").attr("accesskey", "g").render());
    }
    @Test
    public void testLinkWithHref1() {
        assertTrue(a("Visit Example").withHref("https://example.com").render().equals("<a href=\"https://example.com\">Visit Example</a>"));
    }

    @Test
    public void testLinkWithTitle1() {
        assertTrue(a("Top").withHref("#").withTitle("Go to Top").render().equals("<a href=\"#\" title=\"Go to Top\">Top</a>"));
    }

    @Test
    public void testLinkWithTarget1() {
        assertTrue(a("Open in New Tab").withHref("https://example.com").withTarget("_blank").render().equals("<a href=\"https://example.com\" target=\"_blank\">Open in New Tab</a>"));
    }

    @Test
    public void testLinkWithRel1() {
        assertTrue(a("Secure Link").withHref("https://example.com").withRel("noopener noreferrer").render().equals("<a href=\"https://example.com\" rel=\"noopener noreferrer\">Secure Link</a>"));
    }

    @Test
    public void testLinkWithMultipleAttributes1() {
        assertTrue(a("Example").withHref("https://example.com").withTitle("Visit Example").withTarget("_blank").render().equals("<a href=\"https://example.com\" title=\"Visit Example\" target=\"_blank\">Example</a>"));
    }

    @Test
    public void testLinkWithClass1() {
        assertTrue(a("Button").withHref("#").withClass("button").render().equals("<a href=\"#\" class=\"button\">Button</a>"));
    }

    @Test
    public void testLinkWithId1() {
        assertTrue(a("Go to Top").withHref("#").withId("top-link").render().equals("<a href=\"#\" id=\"top-link\">Go to Top</a>"));
    }

    @Test
    public void testLinkWithStyle1() {
        assertTrue(a("Red Link").withHref("#").withStyle("color: red;").render().equals("<a href=\"#\" style=\"color: red;\">Red Link</a>"));
    }



    @Test
    public void testLinkWithRoleAttribute1() {
        assertTrue(a("Button Role").withHref("#").attr("role", "button").render().equals("<a href=\"#\" role=\"button\">Button Role</a>"));
    }

    @Test
    public void testLinkWithAriaLabel1() {
        assertTrue(a("Info").withHref("#").attr("aria-label", "More Information").render().equals("<a href=\"#\" aria-label=\"More Information\">Info</a>"));
    }

    @Test
    public void testLinkWithCustomDataAttribute1() {
        assertTrue(a("Custom Data").withHref("#").attr("data-info", "custom").render().equals("<a href=\"#\" data-info=\"custom\">Custom Data</a>"));
    }

    @Test
    public void testLinkWithOnClickJavaScript1() {
        assertFalse(a("Click Me").withHref("#").attr("onclick", "alert('Clicked!');").render().equals("<a href=\"#\" onclick=\"alert('Clicked!');\">Click Me</a>"));
    }

    @Test
    public void testLinkWithNoHref1() {
        assertTrue(a("No href").render().equals("<a>No href</a>"));
    }

    @Test
    public void testLinkWithEmptyHref1() {
        assertTrue(a("Empty href").withHref("").render().equals("<a href=\"\">Empty href</a>"));
    }

    @Test
    public void testLinkWithMailto1() {
        assertTrue(a("Send Email").withHref("mailto:someone@example.com").render().equals("<a href=\"mailto:someone@example.com\">Send Email</a>"));
    }

    @Test
    public void testLinkWithTel1() {
        assertTrue(a("Call Us").withHref("tel:+1234567890").render().equals("<a href=\"tel:+1234567890\">Call Us</a>"));
    }

    @Test
    public void testLinkWithHashTag1() {
        assertTrue(a("Jump to Section").withHref("#section").render().equals("<a href=\"#section\">Jump to Section</a>"));
    }

    @Test
    public void testLinkWithExternalRel1() {
        assertFalse(a("VisitExternal").withHref("https://external.com").withRel("external").render().equals("<a href=\"https://external.com\" rel=\"external\">Visit External</a>"));
    }


    @Test
    public void testLinkWithSpecialCharactersInText1() {
        assertFalse(a("Café & Bar").withHref("#").render().equals("<a href=\"#\">Café & Bar</a>"));
    }

    @Test
    public void testLinkWithEncodedURL1() {
        assertTrue(a("Search").withHref("https://example.com?q=hello%20world").render().equals("<a href=\"https://example.com?q=hello%20world\">Search</a>"));
    }

    @Test
    public void testLinkWithEscapedCharacters1() {
        assertFalse(a("<script>alert('XSS');</script>").withHref("#").render().equals("<a href=\"#\">&lt;script&gt;alert('XSS');&lt;/script&gt;</a>"));
    }

    @Test
    public void testLinkWithNonStandardAttributes1() {
        assertTrue(a("Non-standard").withHref("#").attr("nonstandard", "true").render().equals("<a href=\"#\" nonstandard=\"true\">Non-standard</a>"));
    }

    @Test
    public void testLinkWithMultipleNestedElementsAndClasses1() {
        assertTrue(a(div(p("Nested Content").withClass("text")).withClass("container")).withHref("#").render().equals("<a href=\"#\"><div class=\"container\"><p class=\"text\">Nested Content</p></div></a>"));
    }

    @Test
    public void testLinkWithTabindex1() {
        assertTrue(a("Accessible Link").withHref("#").attr("tabindex", "1").render().equals("<a href=\"#\" tabindex=\"1\">Accessible Link</a>"));
    }

    @Test
    public void testLinkWithNoFollowRel1() {
        assertTrue(a("No Follow Link").withHref("https://external.com").withRel("nofollow").render().equals("<a href=\"https://external.com\" rel=\"nofollow\">No Follow Link</a>"));
    }

    @Test
    public void testLinkWithMultipleRelValues1() {
        assertTrue(a("Secure Link").withHref("#").withRel("nofollow noopener").render().equals("<a href=\"#\" rel=\"nofollow noopener\">Secure Link</a>"));
    }

    @Test
    public void testLinkWithLangAttribute1() {
        assertTrue(a("English Link").withHref("#").attr("lang", "en").render().equals("<a href=\"#\" lang=\"en\">English Link</a>"));
    }

    @Test
    public void testLinkWithAccessKey1() {
        assertTrue(a("Quick Access").withHref("#").attr("accesskey", "g").render().equals("<a href=\"#\" accesskey=\"g\">Quick Access</a>"));
    }
    @Test
    public void testLinkDoesNotContainUnsetHref() {
        assertFalse(a("No Href").render().contains("href="));
    }

    @Test
    public void testLinkDoesNotContainIncorrectTitle() {
        assertFalse(a("Link").withTitle("Correct Title").render().contains("title=\"Incorrect Title\""));
    }

    @Test
    public void testLinkDoesNotIncludeIncorrectTarget() {
        assertFalse(a("Link").withTarget("_blank").render().contains("target=\"_self\""));
    }

    @Test
    public void testLinkDoesNotUseRelWhenNotSet() {
        assertFalse(a("Link").withHref("https://example.com").render().contains("rel="));
    }

    @Test
    public void testLinkWithMultipleAttributesDoesNotIncludeUnsetClass() {
        assertFalse(a("Link").withHref("https://example.com").withTitle("Title").render().contains("class="));
    }

    @Test
    public void testLinkDoesNotHaveIdWhenNotSet() {
        assertFalse(a("Link").render().contains("id="));
    }

    @Test
    public void testLinkDoesNotIncludeStyleWhenNotSet() {
        assertFalse(a("Link").render().contains("style="));
    }

    @Test
    public void testLinkDoesNotContainDownloadWhenNotSet() {
        assertFalse(a("Link").render().contains("download"));
    }

    @Test
    public void testLinkDoesNotMisuseRoleAttribute() {
        assertFalse(a("Link").withHref("#").render().contains("role="));
    }

    @Test
    public void testLinkWithoutAriaLabel() {
        assertFalse(a("Link").render().contains("aria-label="));
    }

    @Test
    public void testLinkWithoutCustomDataAttribute() {
        assertFalse(a("Link").render().contains("data-info="));
    }

    @Test
    public void testLinkDoesNotHaveOnClickIfNotSet() {
        assertFalse(a("Link").render().contains("onclick="));
    }

    @Test
    public void testLinkWithNoHrefAttribute() {
        assertFalse(a("Link").withHref("").render().contains("href=\"https://example.com\""));
    }

    @Test
    public void testLinkDoesNotContainInvalidMailto() {
        assertFalse(a("Link").withHref("mailto:someone@example.com").render().contains("href=\"mailto:wrong@example.com\""));
    }

    @Test
    public void testLinkDoesNotMisuseTelProtocol() {
        assertFalse(a("Call Us").withHref("tel:+1234567890").render().contains("href=\"tel:+0000000000\""));
    }

    @Test
    public void testLinkDoesNotIncludeWrongHashTag() {
        assertFalse(a("Jump to Section").withHref("#section").render().contains("href=\"#wrongsection\""));
    }

    @Test
    public void testLinkDoesNotIncludeIncorrectRelValue() {
        assertFalse(a("Visit External").withHref("https://external.com").withRel("external").render().contains("rel=\"internal\""));
    }

    @Test
    public void testLinkDoesNotIncludeNestedContentWhenNotProvided() {
        assertFalse(a("Simple Link").withHref("#").render().contains("<span>"));
    }

    @Test
    public void testLinkDoesNotEscapeNecessaryCharacters() {
        assertFalse(a("<script>alert('XSS');</script>").withHref("#").render().contains("<script>"));
    }

    @Test
    public void testLinkDoesNotIncludeNonStandardAttributesIncorrectly() {
        assertFalse(a("Link").withHref("#").attr("nonstandard", "true").render().contains("nonstandard=\"false\""));
    }

    @Test
    public void testLinkDoesNotIncorrectlyAssignTabindex() {
        assertFalse(a("Accessible Link").withHref("#").attr("tabindex", "1").render().contains("tabindex=\"2\""));
    }

    @Test
    public void testLinkDoesNotIncludeNoFollowRelIncorrectly() {
        assertFalse(a("No Follow Link").withHref("https://external.com").withRel("nofollow").render().contains("rel=\"follow\""));
    }

    @Test
    public void testLinkDoesNotMisrepresentRelValues() {
        assertFalse(a("Secure Link").withHref("#").withRel("nofollow noopener").render().contains("rel=\"noopener\""));
    }

    @Test
    public void testLinkDoesNotMisassignLangAttribute() {
        assertFalse(a("English Link").withHref("#").attr("lang", "en").render().contains("lang=\"fr\""));
    }

    @Test
    public void testLinkDoesNotIncludeAccessKeyWhenNotSet() {
        assertFalse(a("Quick Access").withHref("#").render().contains("accesskey="));
    }

    /****************************************************
     * *****************************************************************
     * Testing Link with attribute ends here
     */

























    /****************************************************
     * *****************************************************************
     * Testing Ordered list Starts here
     */








    @Test
    public void testOrderedList() {
        String expectedHtml = "<ol><li>First item</li><li>Second item</li><li>Third item</li></ol>";
        String actualHtml = ol(li("First item"), li("Second item"), li("Third item")).render();
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testEmptyOrderedList() {
        assertEquals("<ol></ol>", ol().render());
    }

    @Test
    public void testOrderedListWithOneItem() {
        assertEquals("<ol><li>Item 1</li></ol>", ol(li("Item 1")).render());
    }

    @Test
    public void testOrderedListWithMultipleItems() {
        assertEquals("<ol><li>Item 1</li><li>Item 2</li><li>Item 3</li></ol>", ol(li("Item 1"), li("Item 2"), li("Item 3")).render());
    }

    @Test
    public void testOrderedListWithNestedLists() {
        assertEquals("<ol><li>Item 1</li><li>Item 2<ol><li>Subitem 1</li><li>Subitem 2</li></ol></li><li>Item 3</li></ol>",
                ol(
                        li("Item 1"),
                        li("Item 2").with(
                                ol(
                                        li("Subitem 1"),
                                        li("Subitem 2")
                                )
                        ),
                        li("Item 3")
                ).render());
    }

    @Test
    public void testOrderedListWithClass() {
        assertEquals("<ol class=\"list-class\"></ol>", ol().withClass("list-class").render());
    }

    @Test
    public void testOrderedListWithId() {
        assertEquals("<ol id=\"list-id\"></ol>", ol().withId("list-id").render());
    }

    @Test
    public void testOrderedListWithStyle() {
        assertEquals("<ol style=\"color: red;\"></ol>", ol().withStyle("color: red;").render());
    }

    @Test
    public void testOrderedListWithMultipleAttributes() {
        assertEquals("<ol id=\"list-id\" class=\"list-class\" style=\"color: red;\"></ol>",
                ol().withId("list-id").withClass("list-class").withStyle("color: red;").render());
    }

    @Test
    public void testOrderedListWithAriaRole() {
        assertEquals("<ol role=\"navigation\"></ol>", ol().attr("role", "navigation").render());
    }

    @Test
    public void testOrderedListWithCustomAttribute() {
        assertEquals("<ol custom=\"value\"></ol>", ol().attr("custom", "value").render());
    }

    @Test
    public void testOrderedListWithItemsHavingClasses() {
        assertEquals("<ol><li class=\"item-class\">Item 1</li><li class=\"item-class\">Item 2</li></ol>",
                ol(
                        li("Item 1").withClass("item-class"),
                        li("Item 2").withClass("item-class")
                ).render());
    }

    @Test
    public void testOrderedListWithEscapedText() {
        assertEquals("<ol><li>&lt;script&gt;alert(&#x27;xss&#x27;);&lt;/script&gt;</li></ol>",
                ol(li("<script>alert('xss');</script>")).render());
    }

    @Test
    public void testOrderedListWithLinkItems() {
        assertEquals("<ol><li><a href=\"https://example.com\">Link 1</a></li><li><a href=\"https://example.com\">Link 2</a></li></ol>",
                ol(
                        li(a("Link 1").withHref("https://example.com")),
                        li(a("Link 2").withHref("https://example.com"))
                ).render());
    }

    @Test
    public void testOrderedListWithVariousTypesOfChildren() {
        assertEquals("<ol><li>Text</li><li><img src=\"image.jpg\"></li></ol>",
                ol(
                        li("Text"),
                        li(img().withSrc("image.jpg"))
                ).render());
    }

    @Test
    public void testOrderedListWithNestedComplexStructure() {
        assertEquals("<ol><li>Item 1</li><li>Item 2<ol><li>Subitem 1</li><li><a href=\"#\">Link</a></li></ol></li></ol>",
                ol(
                        li("Item 1"),
                        li("Item 2").with(
                                ol(
                                        li("Subitem 1"),
                                        li(a("Link").withHref("#"))
                                )
                        )
                ).render());
    }

    @Test
    public void testOrderedListWithManyLevelsOfNesting() {
        assertEquals("<ol><li>Level 1<ol><li>Level 2<ol><li>Level 3</li></ol></li></ol></li></ol>",
                ol(
                        li("Level 1").with(
                                ol(
                                        li("Level 2").with(
                                                ol(
                                                        li("Level 3")
                                                )
                                        )
                                )
                        )
                ).render());
    }

    @Test
    public void testOrderedListWithItemAttributes() {
        assertEquals("<ol><li id=\"first-item\" class=\"highlight\">First Item</li></ol>",
                ol(
                        li("First Item").withId("first-item").withClass("highlight")
                ).render());
    }





    @Test
    public void testOrderedListWithLangAttribute() {
        assertEquals("<ol lang=\"en\"><li>English Item</li></ol>", ol().attr("lang", "en").with(li("English Item")).render());
    }

    @Test
    public void testOrderedListWithNestedDivs() {
        assertEquals("<ol><li><div>Content in Div</div></li></ol>", ol(li(div("Content in Div"))).render());
    }

    @Test
    public void testOrderedListWithComplexContent() {
        assertEquals("<ol><li>Item &lt;strong&gt;Bold&lt;/strong&gt; and &lt;em&gt;Italic&lt;/em&gt;</li></ol>",
                ol(li("Item " + strong("Bold") + " and " + em("Italic"))).render());
    }

    @Test
    public void testOrderedListWithEscapedCharacterAttributes() {
        assertEquals("<ol><li title=\"&lt;None&gt;\">None</li></ol>",
                ol(li("None").withTitle("<None>")).render());
    }

    @Test
    public void testOrderedListWithMultipleStyles() {
        assertEquals("<ol style=\"color: blue; font-size: 14px;\"><li>Styled List</li></ol>",
                ol(li("Styled List")).withStyle("color: blue; font-size: 14px;").render());
    }

    @Test
    public void testOrderedListWithAccessibilityFeatures() {
        assertEquals("<ol aria-describedby=\"list-desc\"><li>Accessible Item</li></ol>",
                ol(li("Accessible Item")).attr("aria-describedby", "list-desc").render());
    }

    @Test
    public void testOrderedListWithImplicitEndTag() {
        assertEquals("<ol><li>Open Tag Only</li></ol>", ol(li("Open Tag Only")).render());
    }

    @Test
    public void testOrderedListWithIncorrectNestedList() {
        assertFalse(ol(li("Item 1"), ol(li("Nested Item"))).render().equals("<ol><li>Item 1<ol><li>Nested Item</ol></li></ol>"));
    }

    @Test
    public void testOrderedListWithoutClosingTag() {
        assertFalse(ol(li("Missing Close")).render().equals("<ol><li>Missing Close</li>"));
    }
    @Test
    public void testEmptyOrderedList1() {
        assertTrue(ol().render().equals("<ol></ol>"));
    }

    @Test
    public void testOrderedListWithOneItem1() {
        assertTrue(ol(li("Item 1")).render().equals("<ol><li>Item 1</li></ol>"));
    }

    @Test
    public void testOrderedListWithMultipleItems1() {
        assertTrue(ol(li("Item 1"), li("Item 2"), li("Item 3")).render().equals("<ol><li>Item 1</li><li>Item 2</li><li>Item 3</li></ol>"));
    }

    @Test
    public void testOrderedListWithNestedLists1() {
        assertTrue(ol(
                li("Item 1"),
                li("Item 2").with(ol(li("Subitem 1"), li("Subitem 2"))),
                li("Item 3")
        ).render().equals("<ol><li>Item 1</li><li>Item 2<ol><li>Subitem 1</li><li>Subitem 2</li></ol></li><li>Item 3</li></ol>"));
    }

    @Test
    public void testOrderedListWithClass1() {
        assertTrue(ol().withClass("list-class").render().equals("<ol class=\"list-class\"></ol>"));
    }

    @Test
    public void testOrderedListWithId1() {
        assertTrue(ol().withId("list-id").render().equals("<ol id=\"list-id\"></ol>"));
    }

    @Test
    public void testOrderedListWithStyle1() {
        assertTrue(ol().withStyle("color: red;").render().equals("<ol style=\"color: red;\"></ol>"));
    }

    @Test
    public void testOrderedListWithMultipleAttributes1() {
        assertTrue(ol().withId("list-id").withClass("list-class").withStyle("color: red;").render().equals("<ol id=\"list-id\" class=\"list-class\" style=\"color: red;\"></ol>"));
    }

    @Test
    public void testOrderedListWithAriaRole1() {
        assertTrue(ol().attr("role", "navigation").render().equals("<ol role=\"navigation\"></ol>"));
    }

    @Test
    public void testOrderedListWithCustomAttribute1() {
        assertTrue(ol().attr("custom", "value").render().equals("<ol custom=\"value\"></ol>"));
    }

    @Test
    public void testOrderedListWithItemsHavingClasses1() {
        assertTrue(ol(
                li("Item 1").withClass("item-class"),
                li("Item 2").withClass("item-class")
        ).render().equals("<ol><li class=\"item-class\">Item 1</li><li class=\"item-class\">Item 2</li></ol>"));
    }

    @Test
    public void testOrderedListWithEscapedText1() {
        assertFalse(ol(li("<script>alert('xss');</script>")).render().equals("<ol><li>&lt;script&gt;alert('xss');&lt;/script&gt;</li></ol>"));
    }

    @Test
    public void testOrderedListWithLinkItems1() {
        assertTrue(ol(
                li(a("Link 1").withHref("https://example.com")),
                li(a("Link 2").withHref("https://example.com"))
        ).render().equals("<ol><li><a href=\"https://example.com\">Link 1</a></li><li><a href=\"https://example.com\">Link 2</a></li></ol>"));
    }

    @Test
    public void testOrderedListWithVariousTypesOfChildren1() {
        assertTrue(ol(
                li("Text"),
                li(img().withSrc("image.jpg"))
        ).render().equals("<ol><li>Text</li><li><img src=\"image.jpg\"></li></ol>"));
    }

    @Test
    public void testOrderedListWithNestedComplexStructure1() {
        assertFalse(ol(
                li("Item 1"),
                li("Item 2").with(
                        ol(
                                li("Subitem 1"),
                                li(a("Link").withHref("#"))
                        )
                )
        ).render().equals("<ol><li>Item 1</li><li>Item 2<ol><li>Subitem 1</li><li><a href=\"#\">Link</ali></ol></li></ol>"));
    }

    @Test
    public void testOrderedListWithManyLevelsOfNesting1() {
        assertTrue(ol(
                li("Level 1").with(
                        ol(
                                li("Level 2").with(
                                        ol(
                                                li("Level 3")
                                        )
                                )
                        )
                )
        ).render().equals("<ol><li>Level 1<ol><li>Level 2<ol><li>Level 3</li></ol></li></ol></li></ol>"));
    }

    @Test
    public void testOrderedListWithItemAttributes1() {
        assertTrue(ol(
                li("First Item").withId("first-item").withClass("highlight")
        ).render().equals("<ol><li id=\"first-item\" class=\"highlight\">First Item</li></ol>"));
    }




    @Test
    public void testOrderedListWithLangAttribute1() {
        assertTrue(ol().attr("lang", "en").with(li("English Item")).render().equals("<ol lang=\"en\"><li>English Item</li></ol>"));
    }

    @Test
    public void testOrderedListWithNestedDivs1() {
        assertTrue(ol(li(div("Content in Div"))).render().equals("<ol><li><div>Content in Div</div></li></ol>"));
    }

    @Test
    public void testOrderedListWithComplexContent1() {
        assertFalse(ol(li("Item " + strong("Bold") + " and " + em("Italic"))).render().equals("<ol><li>Item <strong>Bold</strong> and <em>Italic</em></li></ol>"));
    }

    @Test
    public void testOrderedListWithEscapedCharacterAttributes1() {
        assertTrue(ol(li("None").withTitle("<None>")).render().equals("<ol><li title=\"&lt;None&gt;\">None</li></ol>"));
    }

    @Test
    public void testOrderedListWithMultipleStyles1() {
        assertTrue(ol(li("Styled List")).withStyle("color: blue; font-size: 14px;").render().equals("<ol style=\"color: blue; font-size: 14px;\"><li>Styled List</li></ol>"));
    }

    @Test
    public void testOrderedListWithAccessibilityFeatures1() {
        assertTrue(ol(li("Accessible Item")).attr("aria-describedby", "list-desc").render().equals("<ol aria-describedby=\"list-desc\"><li>Accessible Item</li></ol>"));
    }

    @Test
    public void testOrderedListWithImplicitEndTag1() {
        assertTrue(ol(li("Open Tag Only")).render().contains("<li>Open Tag Only</li>"));
    }

    @Test
    public void testOrderedListWithIncorrectNestedList1() {
        assertFalse(ol(li("Item 1"), ol(li("Nested Item"))).render().equals("<ol><li>Item 1<ol><li>Nested Item</ol></li></ol>"));
    }

    @Test
    public void testOrderedListWithoutClosingTag1() {
        assertFalse(ol(li("Missing Close")).render().equals("<ol><li>Missing Close</li>"));
    }

    @Test
    public void testOrderedListDoesNotContainUnsetClass() {
        assertFalse(ol().render().contains("class="));
    }

    @Test
    public void testOrderedListDoesNotIncludeStyleWhenNotSet() {
        assertFalse(ol().render().contains("style="));
    }

    @Test
    public void testOrderedListDoesNotContainIdWhenNotSet() {
        assertFalse(ol().render().contains("id="));
    }

    @Test
    public void testOrderedListDoesNotContainIncorrectItem() {
        assertFalse(ol(li("Item 1")).render().contains("<li>Item 2</li>"));
    }

    @Test
    public void testOrderedListDoesNotRenderScriptInsideItem() {
        assertFalse(ol(li("<script>alert('hack');</script>")).render().contains("<script>"));
    }

    @Test
    public void testOrderedListWithLinkDoesNotContainExternalHref() {
        assertFalse(ol(li(a("Link").withHref("internal.html"))).render().contains("href=\"http://external.com\""));
    }

    @Test
    public void testOrderedListDoesNotMisuseAttributes() {
        assertFalse(ol().withClass("ordered-list").render().contains("class=\"unordered-list\""));
    }

    @Test
    public void testOrderedListDoesNotHaveAriaLabelIfNotSet() {
        assertFalse(ol().render().contains("aria-label="));
    }

    @Test
    public void testOrderedListDoesNotIncludeRelAttribute() {
        assertFalse(ol().render().contains("rel="));
    }

    @Test
    public void testOrderedListDoesNotIncludeTitleIfNotSet() {
        assertFalse(ol().render().contains("title="));
    }

    @Test
    public void testOrderedListWithItemHavingIdDoesNotIncludeIncorrectId() {
        assertFalse(ol(li("Item").withId("correct-id")).render().contains("id=\"wrong-id\""));
    }

    @Test
    public void testOrderedListDoesNotContainNonExistingDataAttribute() {
        assertFalse(ol().render().contains("data-info="));
    }

    @Test
    public void testOrderedListDoesNotContainDownloadAttribute() {
        assertFalse(ol().render().contains("download"));
    }

    @Test
    public void testOrderedListDoesNotIncludeLangAttributeWhenNotSet() {
        assertFalse(ol().render().contains("lang="));
    }

    @Test
    public void testOrderedListDoesNotMisplaceNestedList() {
        assertFalse(ol(li("Item 1"), ol(li("Nested Item 1"))).render().equals("<ol><li>Item 1<li><ol><li>Nested Item 1</li></ol></li></ol>"));
    }

    @Test
    public void testOrderedListDoesNotIncludeSpanTag() {
        assertFalse(ol(li("Item")).render().contains("<span>"));
    }

    @Test
    public void testOrderedListDoesNotIncludeIncorrectHrefInLink() {
        assertFalse(ol(li(a("Link").withHref("https://correct.com"))).render().contains("href=\"https://incorrect.com\""));
    }

    @Test
    public void testOrderedListDoesNotIncludeStyleIfNotAppliedToLi() {
        assertTrue(ol(li("Item").withStyle("")).render().contains("style="));
    }

    @Test
    public void testOrderedListDoesNotRenderReversedWhenNotSet() {
        assertFalse(ol(li("Item")).render().contains("reversed"));
    }

    @Test
    public void testOrderedListDoesNotIncorrectlyNestOtherElements() {
        assertTrue(ol(div("This should not be inside ol")).render().contains("<ol><div>"));
    }

    @Test
    public void testOrderedListDoesNotIncludeLinkIfNotProvided() {
        assertFalse(ol(li("Item")).render().contains("<a href=\""));
    }

    @Test
    public void testOrderedListDoesNotIncludeDisabledAttribute() {
        assertTrue(ol().attr("disabled", "disabled").render().contains("disabled="));
    }


        @Test
        public void testOrderedListDoesNotContainJavascript() {
            assertFalse(ol(li("Item <script>alert('XSS');</script>")).render().contains("<script>"));
        }

        @Test
        public void testOrderedListDoesNotIncludeIncorrectClass() {
            assertFalse(ol().withClass("correct-class").render().contains("class=\"incorrect-class\""));
        }

        @Test
        public void testOrderedListDoesNotMisplaceImgTag() {
            assertFalse(ol(li("Item"), img().withSrc("image.jpg")).render().contains("<ol><img src=\"image.jpg\"></ol>"));
        }

        @Test
        public void testOrderedListDoesNotContainReversedAttributeIfNotSpecified() {
            assertFalse(ol().with(li("Item")).render().contains("reversed"));
        }


    /**Testing Ordered list ends here
     * *************************************************************
     * ***************************************************************
     */



    @Test
    public void testUnorderedList() {
        String expectedHtml = "<ul><li>Item one</li><li>Item two</li></ul>";
        String actualHtml = ul(li("Item one"), li("Item two")).render();
        assertEquals(expectedHtml, actualHtml);
    }







    /**Testing Complex table starts  here
     * *************************************************************
     * ***************************************************************
     */






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
    public void testEmptyTable2() {
        assertEquals("<table></table>", table().render());
    }

    @Test
    public void testTableWithSingleCell() {
        assertEquals("<table><tr><td>Cell</td></tr></table>", table(tr(td("Cell"))).render());
    }

    @Test
    public void testTableWithHeaderAndFooter() {
        assertEquals("<table><thead><tr><th>Header</th></tr></thead><tfoot><tr><td>Footer</td></tr></tfoot><tbody><tr><td>Content</td></tr></tbody></table>",
                table(
                        thead(tr(th("Header"))),
                        tfoot(tr(td("Footer"))),
                        tbody(tr(td("Content")))
                ).render());
    }

    @Test
    public void testTableWithMultipleRowsAndColumns() {
        assertEquals("<table><tr><td>Row1Col1</td><td>Row1Col2</td></tr><tr><td>Row2Col1</td><td>Row2Col2</td></tr></table>",
                table(
                        tr(td("Row1Col1"), td("Row1Col2")),
                        tr(td("Row2Col1"), td("Row2Col2"))
                ).render());
    }

    @Test
    public void testTableWithColspan() {
        assertEquals("<table><tr><td colspan=\"2\">Spanned Cell</td></tr></table>", table(tr(td("Spanned Cell").attr("colspan", "2"))).render());
    }

    @Test
    public void testTableWithRowspan() {
        assertEquals("<table><tr><td rowspan=\"2\">Spanned Row</td><td>Row1Col2</td></tr><tr><td>Row2Col2</td></tr></table>",
                table(
                        tr(td("Spanned Row").attr("rowspan", "2"), td("Row1Col2")),
                        tr(td("Row2Col2"))
                ).render());
    }

    @Test
    public void testTableWithNestedTables() {
        assertEquals("<table><tr><td><table><tr><td>Nested Cell</td></tr></table></td></tr></table>",
                table(tr(td(table(tr(td("Nested Cell")))))).render());
    }

    @Test
    public void testTableWithMixedContent() {
        assertEquals("<table><tr><td>Text</td><td><img src=\"image.jpg\"></td></tr></table>",
                table(tr(td("Text"), td(img().withSrc("image.jpg")))).render());
    }

    @Test
    public void testTableWithStyles() {
        assertEquals("<table style=\"width: 100%;\"><tr><td style=\"text-align: center;\">Centered Text</td></tr></table>",
                table(tr(td("Centered Text").withStyle("text-align: center;"))).withStyle("width: 100%;").render());
    }

    @Test
    public void testTableWithClasses() {
        assertEquals("<table class=\"table\"><tr><td class=\"cell\">Cell Content</td></tr></table>",
                table(tr(td("Cell Content").withClass("cell"))).withClass("table").render());
    }

    @Test
    public void testTableWithId() {
        assertEquals("<table id=\"uniqueId\"><tr><td>Cell</td></tr></table>", table(tr(td("Cell"))).withId("uniqueId").render());
    }

    @Test
    public void testTableWithMultipleHeadRows() {
        assertEquals("<table><thead><tr><th>Header 1</th></tr><tr><th>Header 2</th></tr></thead><tbody><tr><td>Data</td></tr></tbody></table>",
                table(
                        thead(tr(th("Header 1")), tr(th("Header 2"))),
                        tbody(tr(td("Data")))
                ).render());
    }



    @Test
    public void testTableWithTheadTfootTbodyOrder() {
        assertEquals("<table><thead><tr><th>Header</th></tr></thead><tfoot><tr><td>Footer</td></tr></tfoot><tbody><tr><td>Body</td></tr></tbody></table>",
                table(
                        thead(tr(th("Header"))),
                        tfoot(tr(td("Footer"))),
                        tbody(tr(td("Body")))
                ).render());
    }



    @Test
    public void testTableWithEscapedCharacters() {
        assertEquals("<table><tr><td>&lt;div&gt;Escaped HTML&lt;/div&gt;</td></tr></table>",
                table(tr(td("<div>Escaped HTML</div>"))).render());
    }

    @Test
    public void testTableWithLangAttribute() {
        assertEquals("<table lang=\"en\"><tr><td>English Content</td></tr></table>",
                table(tr(td("English Content"))).attr("lang", "en").render());
    }

    @Test
    public void testTableWithDirectionAttribute() {
        assertEquals("<table dir=\"rtl\"><tr><td>Right to Left</td></tr></table>",
                table(tr(td("Right to Left"))).attr("dir", "rtl").render());
    }

    @Test
    public void testTableWithAccessibilityFeatures() {
        assertEquals("<table aria-label=\"Descriptive Table\"><tr><td>Accessible Cell</td></tr></table>",
                table(tr(td("Accessible Cell"))).attr("aria-label", "Descriptive Table").render());
    }

    @Test
    public void testTableWithColgroupAndCol() {
        assertEquals("<table><colgroup><col span=\"2\" style=\"background-color:yellow\"></colgroup><tr><td>Col 1</td><td>Col 2</td></tr></table>",
                table(
                        colgroup(col().attr("span", "2").withStyle("background-color:yellow")),
                        tr(td("Col 1"), td("Col 2"))
                ).render());
    }

    @Test
    public void testTableWithComplexHeader() {
        assertEquals("<table><thead><tr><th colspan=\"2\">Complex Header</th></tr></thead><tbody><tr><td>Data 1</td><td>Data 2</td></tr></tbody></table>",
                table(
                        thead(tr(th("Complex Header").attr("colspan", "2"))),
                        tbody(tr(td("Data 1"), td("Data 2")))
                ).render());
    }

    @Test
    public void testTableWithInteractiveContent() {
        assertEquals("<table><tr><td><button type=\"button\">Click Me</button></td></tr></table>",
                table(tr(td(button("Click Me").withType("button")))).render());
    }

    @Test
    public void testTableWithAlignAttribute() {
        assertEquals("<table><tr><td align=\"center\">Centered Content</td></tr></table>",
                table(tr(td("Centered Content").attr("align", "center"))).render());
    }

    @Test
    public void testTableWithMultipleTbodySections1() {
        assertEquals("<table><tbody><tr><td>First Section</td></tr></tbody><tbody><tr><td>Second Section</td></tr></tbody></table>",
                table(
                        tbody(tr(td("First Section"))),
                        tbody(tr(td("Second Section")))
                ).render());
    }

    @Test
    public void testTableWithComplexNestedStructures() {
        assertEquals("<table><tr><td><div><ul><li>List in Table</li></ul></div></td></tr></table>",
                table(tr(td(div(ul(li("List in Table")))))).render());
    }

    @Test
    public void testTableWithCaptionAndMultipleHeadRows() {
        assertEquals("<table><caption>Table Caption</caption><thead><tr><th>Header 1</th></tr><tr><th>Header 2</th></tr></thead><tbody><tr><td>Data</td></tr></tbody></table>",
                table(
                        caption("Table Caption"),
                        thead(tr(th("Header 1")), tr(th("Header 2"))),
                        tbody(tr(td("Data")))
                ).render());
    }

    @Test
    public void testEmptyTableRendersCorrectly() {
        assertTrue(table().render().equals("<table></table>"));
    }

    @Test
    public void testTableWithSingleCellRendersCorrectly() {
        assertTrue(table(tr(td("Cell"))).render().equals("<table><tr><td>Cell</td></tr></table>"));
    }

    @Test
    public void testTableWithHeaderAndFooterRendersCorrectly() {
        assertTrue(table(
                thead(tr(th("Header"))),
                tfoot(tr(td("Footer"))),
                tbody(tr(td("Content")))
        ).render().equals("<table><thead><tr><th>Header</th></tr></thead><tfoot><tr><td>Footer</td></tr></tfoot><tbody><tr><td>Content</td></tr></tbody></table>"));
    }

    @Test
    public void testTableWithMultipleRowsAndColumnsRendersCorrectly() {
        assertTrue(table(
                tr(td("Row1Col1"), td("Row1Col2")),
                tr(td("Row2Col1"), td("Row2Col2"))
        ).render().equals("<table><tr><td>Row1Col1</td><td>Row1Col2</td></tr><tr><td>Row2Col1</td><td>Row2Col2</td></tr></table>"));
    }

    @Test
    public void testTableWithColspanRendersCorrectly() {
        assertTrue(table(tr(td("Spanned Cell").attr("colspan", "2"))).render().equals("<table><tr><td colspan=\"2\">Spanned Cell</td></tr></table>"));
    }

    @Test
    public void testTableWithRowspanRendersCorrectly() {
        assertTrue(table(
                tr(td("Spanned Row").attr("rowspan", "2"), td("Row1Col2")),
                tr(td("Row2Col2"))
        ).render().equals("<table><tr><td rowspan=\"2\">Spanned Row</td><td>Row1Col2</td></tr><tr><td>Row2Col2</td></tr></table>"));
    }

    @Test
    public void testTableWithNestedTablesRendersCorrectly() {
        assertTrue(table(tr(td(table(tr(td("Nested Cell")))))).render().equals("<table><tr><td><table><tr><td>Nested Cell</td></tr></table></td></tr></table>"));
    }

    @Test
    public void testTableWithMixedContentRendersCorrectly() {
        assertTrue(table(tr(td("Text"), td(img().withSrc("image.jpg")))).render().equals("<table><tr><td>Text</td><td><img src=\"image.jpg\"></td></tr></table>"));
    }

    @Test
    public void testTableWithStylesRendersCorrectly() {
        assertTrue(table(tr(td("Centered Text").withStyle("text-align: center;"))).withStyle("width: 100%;").render().equals("<table style=\"width: 100%;\"><tr><td style=\"text-align: center;\">Centered Text</td></tr></table>"));
    }

    @Test
    public void testTableWithClassesRendersCorrectly() {
        assertTrue(table(tr(td("Cell Content").withClass("cell"))).withClass("table").render().equals("<table class=\"table\"><tr><td class=\"cell\">Cell Content</td></tr></table>"));
    }

    @Test
    public void testTableWithIdRendersCorrectly() {
        assertTrue(table(tr(td("Cell"))).withId("uniqueId").render().equals("<table id=\"uniqueId\"><tr><td>Cell</td></tr></table>"));
    }

    @Test
    public void testTableWithMultipleHeadRowsRendersCorrectly() {
        assertTrue(table(
                thead(tr(th("Header 1")), tr(th("Header 2"))),
                tbody(tr(td("Data")))
        ).render().equals("<table><thead><tr><th>Header 1</th></tr><tr><th>Header 2</th></tr></thead><tbody><tr><td>Data</td></tr></tbody></table>"));
    }

    @Test
    public void testTableWithMultipleFootRowsRendersCorrectly() {
        assertFalse(table(
                tfoot(tr(td("Footer 1")), tr(td("Footer 2"))),
                tbody(tr(td("Data")))
        ).render().equals("<table><tfoot<tr><td>Footer 1</td></tr><tr><td>Footer 2</td></tr></tfoot><tbody><tr><td>Data</td></tr></tbody></table>"));
    }

    @Test
    public void testTableWithTheadTfootTbodyOrderRendersCorrectly() {
        assertTrue(table(
                thead(tr(th("Header"))),
                tfoot(tr(td("Footer"))),
                tbody(tr(td("Body")))
        ).render().equals("<table><thead><tr><th>Header</th></tr></thead><tfoot><tr><td>Footer</td></tr></tfoot><tbody><tr><td>Body</td></tr></tbody></table>"));
    }



    @Test
    public void testTableWithEscapedCharactersRendersCorrectly() {
        assertTrue(table(tr(td("<div>Escaped HTML</div>"))).render().equals("<table><tr><td>&lt;div&gt;Escaped HTML&lt;/div&gt;</td></tr></table>"));
    }

    @Test
    public void testTableWithLangAttributeRendersCorrectly() {
        assertTrue(table(tr(td("English Content"))).attr("lang", "en").render().equals("<table lang=\"en\"><tr><td>English Content</td></tr></table>"));
    }

    @Test
    public void testTableWithDirectionAttributeRendersCorrectly() {
        assertTrue(table(tr(td("Right to Left"))).attr("dir", "rtl").render().equals("<table dir=\"rtl\"><tr><td>Right to Left</td></tr></table>"));
    }

    @Test
    public void testTableWithAccessibilityFeaturesRendersCorrectly() {
        assertTrue(table(tr(td("Accessible Cell"))).attr("aria-label", "Descriptive Table").render().equals("<table aria-label=\"Descriptive Table\"><tr><td>Accessible Cell</td></tr></table>"));
    }

    @Test
    public void testTableWithColgroupAndColRendersCorrectly() {
        assertTrue(table(
                colgroup(col().attr("span", "2").withStyle("background-color:yellow")),
                tr(td("Col 1"), td("Col 2"))
        ).render().equals("<table><colgroup><col span=\"2\" style=\"background-color:yellow\"></colgroup><tr><td>Col 1</td><td>Col 2</td></tr></table>"));
    }

    @Test
    public void testTableWithComplexHeaderRendersCorrectly() {
        assertTrue(table(
                thead(tr(th("Complex Header").attr("colspan", "2"))),
                tbody(tr(td("Data 1"), td("Data 2")))
        ).render().equals("<table><thead><tr><th colspan=\"2\">Complex Header</th></tr></thead><tbody><tr><td>Data 1</td><td>Data 2</td></tr></tbody></table>"));
    }

    @Test
    public void testTableWithInteractiveContentRendersCorrectly() {
        assertTrue(table(tr(td(button("Click Me").withType("button")))).render().equals("<table><tr><td><button type=\"button\">Click Me</button></td></tr></table>"));
    }

    @Test
    public void testTableWithAlignAttributeRendersCorrectly() {
        assertTrue(table(tr(td("Centered Content").attr("align", "center"))).render().equals("<table><tr><td align=\"center\">Centered Content</td></tr></table>"));
    }

    @Test
    public void testTableWithMultipleTbodySectionsRendersCorrectly() {
        assertTrue(table(
                tbody(tr(td("First Section"))),
                tbody(tr(td("Second Section")))
        ).render().equals("<table><tbody><tr><td>First Section</td></tr></tbody><tbody><tr><td>Second Section</td></tr></tbody></table>"));
    }

    @Test
    public void testTableWithComplexNestedStructuresRendersCorrectly() {
        assertTrue(table(tr(td(div(ul(li("List in Table")))))).render().equals("<table><tr><td><div><ul><li>List in Table</li></ul></div></td></tr></table>"));
    }

    @Test
    public void testTableDoesNotIncludeUnsetClass() {
        assertFalse(table().render().contains("class="));
    }

    @Test
    public void testTableDoesNotRenderIncorrectId() {
        assertFalse(table().withId("correctId").render().contains("id=\"incorrectId\""));
    }

    @Test
    public void testTableDoesNotContainStyleWhenNotSet() {
        assertFalse(table().render().contains("style="));
    }

    @Test
    public void testTableDoesNotHaveIncorrectHeaders() {
        assertFalse(table(thead(tr(th("Header")))).render().contains("<th>Wrong Header</th>"));
    }

    @Test
    public void testTableDoesNotContainColspanIfNotSpecified() {
        assertFalse(table(tr(td("Data"))).render().contains("colspan="));
    }

    @Test
    public void testTableWithRowspanDoesNotMisplaceRowspanAttribute() {
        assertFalse(table(tr(td("Row").attr("rowspan", "2"))).render().contains("<tr><td rowspan=\"3\">Row</td></tr>"));
    }

    @Test
    public void testTableDoesNotRenderNestedTablesIncorrectly22() {
        assertFalse(table(tr(td(table(tr(td("Nested")))))).render().contains("<table><table>"));
    }

    @Test
    public void testTableDoesNotMixContentIncorrectly() {
        assertFalse(table(tr(td("Text"), td("Wrong Text"))).render().contains("<td><img src=\"image.jpg\"></td>"));
    }

    @Test
    public void testTableDoesNotIncludeScripts() {
        assertFalse(table(tr(td("<script>alert('XSS');</script>"))).render().contains("<script>"));
    }

    @Test
    public void testTableDoesNotUseIncorrectAttribute() {
        assertFalse(table(tr(td("Data"))).attr("wrong", "true").render().contains("wrong=\"false\""));
    }

    @Test
    public void testTableDoesNotIncludeLangWhenNotSet() {
        assertFalse(table(tr(td("Content"))).render().contains("lang="));
    }

    @Test
    public void testTableDoesNotRenderWrongDirectionAttribute() {
        assertFalse(table(tr(td("Content")).attr("dir", "ltr")).render().contains("dir=\"rtl\""));
    }

    @Test
    public void testTableDoesNotIncludeEscapedHTMLIncorrectly() {
        assertFalse(table(tr(td("<div>Good</div>"))).render().contains("&lt;div&gt;Bad&lt;/div&gt;"));
    }

    @Test
    public void testTableDoesNotMislabelAriaAttributes() {
        assertFalse(table().attr("aria-label", "Table").render().contains("aria-label=\"Incorrect Label\""));
    }

    @Test
    public void testTableDoesNotContainNonexistentAttributes() {
        assertFalse(table().render().contains("nonexistent=\"true\""));
    }

    @Test
    public void testTableDoesNotRenderIncorrectColgroup() {
        assertFalse(table(colgroup(col().withStyle("background-color:red"))).render().contains("<col style=\"background-color:blue\">"));
    }



    @Test
    public void testTableDoesNotIncludeInvalidRow() {
        assertFalse(table(tr(td("Valid Row")), tr(td("Invalid Row"))).render().contains("<tr><td>Nonexistent Row</td></tr>"));
    }

    @Test
    public void testTableDoesNotMisplaceCaption() {
        assertFalse(table(caption("Correct Caption")).render().contains("<caption>Wrong Caption</caption>"));
    }

    @Test
    public void testTableDoesNotIncludeMisplacedDiv() {
        assertFalse(table(tr(td(div("Correct Div")))).render().contains("<div>Wrong Place</div>"));
    }

    @Test
    public void testTableDoesNotContainAdditionalFooters() {
        assertFalse(table(tfoot(tr(td("Footer")))).render().contains("<tfoot><tr><td>Extra Footer</td></tr></tfoot>"));
    }

    @Test
    public void testTableDoesNotRenderIncorrectColspan() {
        assertFalse(table(tr(td("Cell").attr("colspan", "1"))).render().contains("colspan=\"2\""));
    }

    @Test
    public void testTableDoesNotUseIncorrectAttributesForAccessibility() {
        assertFalse(table(tr(td("Accessible Cell"))).attr("aria-describer", "desc").render().contains("aria-describedby=\"desc\""));
    }

    @Test
    public void testTableDoesNotMisrenderMultipleTbodySections() {
        assertFalse(table(
                tbody(tr(td("First Section"))),
                tbody(tr(td("Second Section")))
        ).render().equals("<table><tbody><tr><td>First Section</td></tr></tbody><tbody><tr><td>Third Section</td></tr></tbody></table>"));
    }

    @Test
    public void testTableDoesNotIncludeIncorrectNestedStructures() {
        assertFalse(table(tr(td(div(ul(li("Correct List")))))).render().contains("<ul><li>Wrong List</li></ul>"));
    }

    @Test
    public void testTableWithCaptionAndMultipleHeadRowsDoesNotIncludeIncorrectCaption() {
        assertFalse(table(
                caption("Correct Caption"),
                thead(tr(th("Header 1")), tr(th("Header 2"))),
                tbody(tr(td("Data")))
        ).render().contains("<caption>Incorrect Caption</caption>"));
    }






    /**Testing Complex table ends here
     * *************************************************************
     * ***************************************************************
     */


    /**Testing Image With Attributes starts here
     * *************************************************************
     * ***************************************************************
     */

    @Test
    public void testImageWithAttributes() {
        String expectedHtml = "<img src=\"logo.png\" alt=\"Company Logo\" width=\"200\" height=\"100\">";
        String actualHtml = img().withSrc("logo.png").withAlt("Company Logo").withWidth("200").withHeight("100").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testEmptyImageTag() {
        assertEquals("<img>", img().render());
    }

    @Test
    public void testImageWithSource2() {
        assertEquals("<img src=\"image.jpg\">", img().withSrc("image.jpg").render());
    }

    @Test
    public void testImageWithAltText2() {
        assertEquals("<img alt=\"Description\">", img().withAlt("Description").render());
    }

    @Test
    public void testImageWithWidthAndHeight() {
        assertEquals("<img width=\"100\" height=\"100\">", img().withWidth("100").withHeight("100").render());
    }

    @Test
    public void testImageWithAllAttributes2() {
        assertEquals("<img src=\"image.jpg\" alt=\"Description\" width=\"100\" height=\"100\">", img().withSrc("image.jpg").withAlt("Description").withWidth("100").withHeight("100").render());
    }

    @Test
    public void testImageWithStyle2() {
        assertEquals("<img style=\"display:block;\">", img().withStyle("display:block;").render());
    }

    @Test
    public void testImageWithClass() {
        assertEquals("<img class=\"responsive\">", img().withClass("responsive").render());
    }

    @Test
    public void testImageWithId() {
        assertEquals("<img id=\"logo\">", img().withId("logo").render());
    }

    @Test
    public void testImageWithRoleAttribute2() {
        assertEquals("<img role=\"presentation\">", img().attr("role", "presentation").render());
    }

    @Test
    public void testImageWithCustomDataAttribute2() {
        assertEquals("<img data-custom=\"value\">", img().attr("data-custom", "value").render());
    }

    @Test
    public void testImageWithSrcset2() {
        assertEquals("<img srcset=\"image.jpg 1x, image2x.jpg 2x\">", img().attr("srcset", "image.jpg 1x, image2x.jpg 2x").render());
    }

    @Test
    public void testImageWithSizesAttribute() {
        assertEquals("<img sizes=\"(max-width: 600px) 200px, 50vw\">", img().attr("sizes", "(max-width: 600px) 200px, 50vw").render());
    }

    @Test
    public void testImageWithCrossOriginAnonymous() {
        assertEquals("<img crossorigin=\"anonymous\">", img().attr("crossorigin", "anonymous").render());
    }

    @Test
    public void testImageWithLoadingLazy() {
        assertEquals("<img loading=\"lazy\">", img().attr("loading", "lazy").render());
    }

    @Test
    public void testImageWithAltAndRole() {
        assertEquals("<img alt=\"Description\" role=\"presentation\">", img().withAlt("Description").attr("role", "presentation").render());
    }

    @Test
    public void testImageWithAriaLabel2() {
        assertEquals("<img aria-label=\"Logo\">", img().attr("aria-label", "Logo").render());
    }

    @Test
    public void testImageWithAriaHiddenTrue() {
        assertEquals("<img aria-hidden=\"true\">", img().attr("aria-hidden", "true").render());
    }

    @Test
    public void testImageWithAccessibilityFeatures2() {
        assertEquals("<img alt=\"Accessible image\" role=\"img\">", img().withAlt("Accessible image").attr("role", "img").render());
    }

    @Test
    public void testImageWithOnClick() {
        assertEquals("<img onclick=\"handleClick()\">", img().attr("onclick", "handleClick()").render());
    }

    @Test
    public void testImageWithCompleteAccessibility() {
        assertEquals("<img alt=\"Complete\" role=\"button\" tabindex=\"0\">", img().withAlt("Complete").attr("role", "button").attr("tabindex", "0").render());
    }

    @Test
    public void testImageWithMultipleClasses2() {
        assertEquals("<img class=\"class1 class2\">", img().withClass("class1 class2").render());
    }


    @Test
    public void testImageWithCompleteStyle() {
        assertEquals("<img style=\"width:100%; height:auto;\">", img().withStyle("width:100%; height:auto;").render());
    }

    @Test
    public void testImageWithSrcAndAltTogether() {
        assertEquals("<img src=\"image.jpg\" alt=\"A scenic view\">", img().withSrc("image.jpg").withAlt("A scenic view").render());
    }

    @Test
    public void testImageWithSrcAndOnError() {
        assertEquals("<img src=\"image.jpg\" onerror=\"alert(&#x27;Error loading image&#x27;);\">", img().withSrc("image.jpg").attr("onerror", "alert('Error loading image');").render());
    }

    @Test
    public void testImageWithNoSrcAttribute() {
        assertEquals("<img alt=\"No Source\">", img().withAlt("No Source").render());
    }

    @Test
    public void testImageWithMultipleDataAttributes() {
        assertEquals("<img data-id=\"123\" data-role=\"icon\">", img().attr("data-id", "123").attr("data-role", "icon").render());
    }

    @Test
    public void testImageWithMultipleAccessibilityAttributes() {
        assertEquals("<img alt=\"Logo\" aria-label=\"Company Logo\" role=\"img\">", img().withAlt("Logo").attr("aria-label", "Company Logo").attr("role", "img").render());
    }

    @Test
    public void testImageWithPlaceholderImageOnSrcError() {
        assertEquals("<img src=\"image.jpg\" onerror=\"this.src=&#x27;placeholder.png&#x27;\">", img().withSrc("image.jpg").attr("onerror", "this.src='placeholder.png'").render());
    }
    @Test
    public void testEmptyImageTag1() {
        assertTrue(img().render().equals("<img>"));
    }

    @Test
    public void testImageWithSource3() {
        assertTrue(img().withSrc("image.jpg").render().equals("<img src=\"image.jpg\">"));
    }

    @Test
    public void testImageWithAltText3() {
        assertTrue(img().withAlt("Description").render().equals("<img alt=\"Description\">"));
    }

    @Test
    public void testImageWithWidthAndHeight3() {
        assertTrue(img().withWidth("100").withHeight("100").render().equals("<img width=\"100\" height=\"100\">"));
    }

    @Test
    public void testImageWithAllAttributes3() {
        assertTrue(img().withSrc("image.jpg").withAlt("Description").withWidth("100").withHeight("100").render().equals("<img src=\"image.jpg\" alt=\"Description\" width=\"100\" height=\"100\">"));
    }

    @Test
    public void testImageWithStyle3() {
        assertTrue(img().withStyle("display:block;").render().equals("<img style=\"display:block;\">"));
    }

    @Test
    public void testImageWithClass3() {
        assertTrue(img().withClass("responsive").render().equals("<img class=\"responsive\">"));
    }

    @Test
    public void testImageWithId3() {
        assertTrue(img().withId("logo").render().equals("<img id=\"logo\">"));
    }

    @Test
    public void testImageWithRoleAttribute3() {
        assertTrue(img().attr("role", "presentation").render().equals("<img role=\"presentation\">"));
    }

    @Test
    public void testImageWithCustomDataAttribute3() {
        assertTrue(img().attr("data-custom", "value").render().equals("<img data-custom=\"value\">"));
    }

    @Test
    public void testImageWithSrcset3() {
        assertTrue(img().attr("srcset", "image.jpg 1x, image2x.jpg 2x").render().equals("<img srcset=\"image.jpg 1x, image2x.jpg 2x\">"));
    }

    @Test
    public void testImageWithSizesAttribute3() {
        assertTrue(img().attr("sizes", "(max-width: 600px) 200px, 50vw").render().equals("<img sizes=\"(max-width: 600px) 200px, 50vw\">"));
    }

    @Test
    public void testImageWithCrossOriginAnonymous3() {
        assertTrue(img().attr("crossorigin", "anonymous").render().equals("<img crossorigin=\"anonymous\">"));
    }

    @Test
    public void testImageWithLoadingLazy3() {
        assertTrue(img().attr("loading", "lazy").render().equals("<img loading=\"lazy\">"));
    }

    @Test
    public void testImageWithAltAndRole3() {
        assertTrue(img().withAlt("Description").attr("role", "presentation").render().equals("<img alt=\"Description\" role=\"presentation\">"));
    }

    @Test
    public void testImageWithAriaLabel3() {
        assertTrue(img().attr("aria-label", "Logo").render().equals("<img aria-label=\"Logo\">"));
    }

    @Test
    public void testImageWithAriaHiddenTrue3() {
        assertTrue(img().attr("aria-hidden", "true").render().equals("<img aria-hidden=\"true\">"));
    }

    @Test
    public void testImageWithAccessibilityFeatures3() {
        assertTrue(img().withAlt("Accessible image").attr("role", "img").render().equals("<img alt=\"Accessible image\" role=\"img\">"));
    }

    @Test
    public void testImageWithOnClick3() {
        assertTrue(img().attr("onclick", "handleClick()").render().equals("<img onclick=\"handleClick()\">"));
    }

    @Test
    public void testImageWithCompleteAccessibility3() {
        assertTrue(img().withAlt("Complete").attr("role", "button").attr("tabindex", "0").render().equals("<img alt=\"Complete\" role=\"button\" tabindex=\"0\">"));
    }

    @Test
    public void testImageWithMultipleClasses3() {
        assertTrue(img().withClass("class1 class2").render().equals("<img class=\"class1 class2\">"));
    }

    @Test
    public void testImageWithTabIndex() {
        assertTrue(img().attr("tabindex", "1").render().equals("<img tabindex=\"1\">"));
    }


    @Test
    public void testImageWithSrcAndAltTogether3() {
        assertTrue(img().withSrc("image.jpg").withAlt("A scenic view").render().equals("<img src=\"image.jpg\" alt=\"A scenic view\">"));
    }

    @Test
    public void testImageWithSrcAndOnError3() {
        assertFalse(img().withSrc("image.jpg").attr("onerror", "alert('Error loading image');").render().equals("<img src=\"image.jpg\" onerror=\"alert('Error loading image');\">"));
    }

    @Test
    public void testImageWithNoSrcAttribute3() {
        assertTrue(img().withAlt("No Source").render().equals("<img alt=\"No Source\">"));
    }

    @Test
    public void testImageWithMultipleDataAttributes3() {
        assertTrue(img().attr("data-id", "123").attr("data-role", "icon").render().equals("<img data-id=\"123\" data-role=\"icon\">"));
    }

    @Test
    public void testImageWithMultipleAccessibilityAttributes3() {
        assertTrue(img().withAlt("Logo").attr("aria-label", "Company Logo").attr("role", "img").render().equals("<img alt=\"Logo\" aria-label=\"Company Logo\" role=\"img\">"));
    }

    @Test
    public void testImageWithPlaceholderImageOnSrcError3() {
        assertFalse(img().withSrc("image.jpg").attr("onerror", "this.src='placeholder.png'").render().equals("<img src=\"image.jpg\" onerror=\"this.src='placeholder.png'\">"));
    }

    @Test
    public void testImageDoesNotContainUnsetSource() {
        assertFalse(img().render().contains("src="));
    }

    @Test
    public void testImageDoesNotRenderIncorrectAltText() {
        assertFalse(img().withAlt("Correct Alt").render().contains("alt=\"Incorrect Alt\""));
    }

    @Test
    public void testImageDoesNotIncludeHeightWhenNotSet() {
        assertFalse(img().render().contains("height="));
    }

    @Test
    public void testImageDoesNotIncludeWidthWhenNotSet() {
        assertFalse(img().render().contains("width="));
    }

    @Test
    public void testImageDoesNotIncludeStyleWhenNotSet() {
        assertFalse(img().render().contains("style="));
    }

    @Test
    public void testImageDoesNotContainClassWhenNotSet() {
        assertFalse(img().render().contains("class="));
    }

    @Test
    public void testImageDoesNotRenderIdIfNotProvided() {
        assertFalse(img().render().contains("id="));
    }

    @Test
    public void testImageDoesNotContainIncorrectRole() {
        assertFalse(img().attr("role", "presentation").render().contains("role=\"button\""));
    }

    @Test
    public void testImageDoesNotContainCustomDataAttributeIfNotSet() {
        assertFalse(img().render().contains("data-custom="));
    }

    @Test
    public void testImageDoesNotRenderSrcsetIfNotProvided() {
        assertFalse(img().render().contains("srcset="));
    }

    @Test
    public void testImageDoesNotMisuseSizesAttribute() {
        assertFalse(img().render().contains("sizes="));
    }

    @Test
    public void testImageDoesNotIncludeCrossOriginIfNotSet() {
        assertFalse(img().render().contains("crossorigin="));
    }

    @Test
    public void testImageDoesNotRenderLoadingAttributeIfNotProvided() {
        assertFalse(img().render().contains("loading="));
    }

    @Test
    public void testImageWithIncorrectAriaLabel() {
        assertFalse(img().attr("aria-label", "Logo").render().contains("aria-label=\"Icon\""));
    }

    @Test
    public void testImageDoesNotHaveAriaHiddenAttributeWhenNotSet() {
        assertFalse(img().render().contains("aria-hidden="));
    }

    @Test
    public void testImageDoesNotMisrepresentAccessibilityFeatures() {
        assertFalse(img().attr("role", "img").render().contains("role=\"none\""));
    }

    @Test
    public void testImageDoesNotIncludeOnClickIfNotProvided() {
        assertFalse(img().render().contains("onclick="));
    }

    @Test
    public void testImageDoesNotRenderMultipleClassesIncorrectly() {
        assertFalse(img().withClass("class1 class2").render().contains("class=\"class3 class4\""));
    }

    @Test
    public void testImageDoesNotContainTabIndexWhenNotSet() {
        assertFalse(img().render().contains("tabindex="));
    }

    @Test
    public void testImageDoesNotIncludeStyleIncorrectly() {
        assertFalse(img().withStyle("display:block;").render().contains("style=\"display:none;\""));
    }

    @Test
    public void testImageDoesNotRenderSrcAndAltTogetherIncorrectly() {
        assertFalse(img().withSrc("image.jpg").withAlt("A scenic view").render().contains("src=\"video.mp4\""));
    }

    @Test
    public void testImageDoesNotIncludeOnErrorIfNotProvided() {
        assertFalse(img().withSrc("image.jpg").render().contains("onerror="));
    }

    @Test
    public void testImageDoesNotRenderNoSrcAttributeIncorrectly() {
        assertFalse(img().withAlt("No Source").render().contains("src="));
    }

    @Test
    public void testImageDoesNotIncludeMultipleDataAttributesIncorrectly() {
        assertFalse(img().attr("data-id", "123").attr("data-role", "icon").render().contains("data-id=\"456\""));
    }

    @Test
    public void testImageDoesNotMisuseAccessibilityAttributes() {
        assertFalse(img().withAlt("Logo").attr("aria-label", "Company Logo").attr("role", "img").render().contains("role=\"button\""));
    }



    /**Testing Image With Attributes ends here
     * *************************************************************
     * ***************************************************************
     */

    /**@Test
    public void testVideoWithAttributes() {
        String expectedHtml = "<video src=\"movie.mp4\" controls></video>";
        String actualHtml = video().withSrc("movie.mp4").withControls().render();
        assertEquals(expectedHtml, actualHtml);
    }**/








    /**Testing Iframe starts here
     * *************************************************************
     * ***************************************************************
     */
    @Test
    public void testIframe() {
        String expectedHtml = "<iframe src=\"https://example.com\" width=\"300\" height=\"200\"></iframe>";
        String actualHtml = iframe().withSrc("https://example.com").withWidth("300").withHeight("200").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testEmptyIframe() {
        assertEquals("<iframe></iframe>", iframe().render());
    }

    @Test
    public void testIframeWithSource() {
        assertEquals("<iframe src=\"example.html\"></iframe>", iframe().withSrc("example.html").render());
    }

    @Test
    public void testIframeWithWidthAndHeight() {
        assertEquals("<iframe width=\"300\" height=\"200\"></iframe>", iframe().withWidth("300").withHeight("200").render());
    }



    @Test
    public void testIframeWithStyle() {
        assertEquals("<iframe style=\"border:none;\"></iframe>", iframe().withStyle("border:none;").render());
    }

    @Test
    public void testIframeWithSandbox6() {
        assertEquals("<iframe sandbox=\"allow-scripts\"></iframe>", iframe().attr("sandbox", "allow-scripts").render());
    }

    @Test
    public void testIframeWithMultipleSandboxValues() {
        assertEquals("<iframe sandbox=\"allow-scripts allow-same-origin\"></iframe>", iframe().attr("sandbox", "allow-scripts allow-same-origin").render());
    }

    @Test
    public void testIframeWithAllowFullScreen() {
        assertEquals("<iframe allowfullscreen></iframe>", iframe().attr("allowfullscreen").render());
    }

    @Test
    public void testIframeWithRoleAttribute() {
        assertEquals("<iframe role=\"presentation\"></iframe>", iframe().attr("role", "presentation").render());
    }

    @Test
    public void testIframeWithAriaHidden() {
        assertEquals("<iframe aria-hidden=\"true\"></iframe>", iframe().attr("aria-hidden", "true").render());
    }

    @Test
    public void testIframeWithLoadingLazy() {
        assertEquals("<iframe loading=\"lazy\"></iframe>", iframe().attr("loading", "lazy").render());
    }

    @Test
    public void testIframeWithID() {
        assertEquals("<iframe id=\"myFrame\"></iframe>", iframe().withId("myFrame").render());
    }

    @Test
    public void testIframeWithName() {
        assertEquals("<iframe name=\"frameName\"></iframe>", iframe().withName("frameName").render());
    }

    @Test
    public void testIframeWithCustomDataAttribute() {
        assertEquals("<iframe data-custom=\"value\"></iframe>", iframe().attr("data-custom", "value").render());
    }

    @Test
    public void testIframeWithSrcDoc() {
        assertEquals("<iframe srcdoc=\"&lt;p&gt;Hello, world!&lt;/p&gt;\"></iframe>", iframe().attr("srcdoc", "<p>Hello, world!</p>").render());
    }



    @Test
    public void testIframeWithTitle() {
        assertEquals("<iframe title=\"Frame Title\"></iframe>", iframe().withTitle("Frame Title").render());
    }

    @Test
    public void testIframeWithLangAttribute() {
        assertEquals("<iframe lang=\"en\"></iframe>", iframe().attr("lang", "en").render());
    }

    @Test
    public void testIframeWithTabIndex() {
        assertEquals("<iframe tabindex=\"-1\"></iframe>", iframe().attr("tabindex", "-1").render());
    }

    @Test
    public void testIframeWithAlignment() {
        assertEquals("<iframe align=\"center\"></iframe>", iframe().attr("align", "center").render());
    }

    @Test
    public void testIframeWithAccessKey() {
        assertEquals("<iframe accesskey=\"k\"></iframe>", iframe().attr("accesskey", "k").render());
    }

    @Test
    public void testEmptyIframe1() {
        assertTrue(iframe().render().equals("<iframe></iframe>"));
    }

    @Test
    public void testIframeWithSource1() {
        assertTrue(iframe().withSrc("example.html").render().equals("<iframe src=\"example.html\"></iframe>"));
    }

    @Test
    public void testIframeWithWidthAndHeight1() {
        assertTrue(iframe().withWidth("300").withHeight("200").render().equals("<iframe width=\"300\" height=\"200\"></iframe>"));
    }



    @Test
    public void testIframeWithStyle1() {
        assertTrue(iframe().withStyle("border:none;").render().equals("<iframe style=\"border:none;\"></iframe>"));
    }

    @Test
    public void testIframeWithSandbox1() {
        assertTrue(iframe().attr("sandbox", "allow-scripts").render().equals("<iframe sandbox=\"allow-scripts\"></iframe>"));
    }

    @Test
    public void testIframeWithMultipleSandboxValues1() {
        assertTrue(iframe().attr("sandbox", "allow-scripts allow-same-origin").render().equals("<iframe sandbox=\"allow-scripts allow-same-origin\"></iframe>"));
    }

    @Test
    public void testIframeWithAllowFullScreen1() {
        assertTrue(iframe().attr("allowfullscreen").render().equals("<iframe allowfullscreen></iframe>"));
    }

    @Test
    public void testIframeWithRoleAttribute1() {
        assertTrue(iframe().attr("role", "presentation").render().equals("<iframe role=\"presentation\"></iframe>"));
    }

    @Test
    public void testIframeWithAriaHidden1() {
        assertTrue(iframe().attr("aria-hidden", "true").render().equals("<iframe aria-hidden=\"true\"></iframe>"));
    }

    @Test
    public void testIframeWithLoadingLazy1() {
        assertTrue(iframe().attr("loading", "lazy").render().equals("<iframe loading=\"lazy\"></iframe>"));
    }

    @Test
    public void testIframeWithID1() {
        assertTrue(iframe().withId("myFrame").render().equals("<iframe id=\"myFrame\"></iframe>"));
    }

    @Test
    public void testIframeWithName1() {
        assertTrue(iframe().withName("frameName").render().equals("<iframe name=\"frameName\"></iframe>"));
    }

    @Test
    public void testIframeWithCustomDataAttribute1() {
        assertTrue(iframe().attr("data-custom", "value").render().equals("<iframe data-custom=\"value\"></iframe>"));
    }

    @Test
    public void testIframeWithSrcDoc1() {
        assertFalse(iframe().attr("srcdoc", "<p>Hello, world!</p>").render().equals("<iframe srcdoc=\"<p>Hello, world!</p>\"></iframe>"));
    }



    @Test
    public void testIframeWithTitle1() {
        assertTrue(iframe().withTitle("Frame Title").render().equals("<iframe title=\"Frame Title\"></iframe>"));
    }

    @Test
    public void testIframeWithLangAttribute1() {
        assertTrue(iframe().attr("lang", "en").render().equals("<iframe lang=\"en\"></iframe>"));
    }

    @Test
    public void testIframeWithTabIndex1() {
        assertTrue(iframe().attr("tabindex", "-1").render().equals("<iframe tabindex=\"-1\"></iframe>"));
    }

    @Test
    public void testIframeWithAlignment1() {
        assertTrue(iframe().attr("align", "center").render().equals("<iframe align=\"center\"></iframe>"));
    }



    @Test
    public void testIframeWithLongDesc() {
        assertTrue(iframe().attr("longdesc", "description.html").render().equals("<iframe longdesc=\"description.html\"></iframe>"));
    }

    @Test
    public void testIframeWithSecurityPolicy() {
        assertFalse(iframe().attr("csp", "default-src 'self';").render().equals("<iframe csp=\"default-src 'self';\"></iframe>"));
    }



    @Test
    public void testIframeWithOnClick() {
        assertFalse(iframe().attr("onclick", "alert('Clicked!');").render().equals("<iframe onclick=\"alert('Clicked!');\"></iframe>"));
    }

    @Test
    public void testIframeWithCompleteStyle() {
        assertTrue(iframe().withStyle("border: none; width: 100%; height: 100%;").render().equals("<iframe style=\"border: none; width: 100%; height: 100%;\"></iframe>"));
    }
    @Test
    public void testIframeDoesNotContainUnsetSource() {
        assertFalse(iframe().render().contains("src="));
    }

    @Test
    public void testIframeDoesNotIncludeIncorrectWidth() {
        assertFalse(iframe().withWidth("100").render().contains("width=\"200\""));
    }

    @Test
    public void testIframeDoesNotIncludeIncorrectHeight() {
        assertFalse(iframe().withHeight("100").render().contains("height=\"200\""));
    }

    @Test
    public void testIframeDoesNotContainStyleWhenNotSet() {
        assertFalse(iframe().render().contains("style="));
    }

    @Test
    public void testIframeDoesNotContainClassWhenNotSet() {
        assertFalse(iframe().render().contains("class="));
    }

    @Test
    public void testIframeDoesNotRenderIdIfNotProvided() {
        assertFalse(iframe().render().contains("id="));
    }

    @Test
    public void testIframeDoesNotContainIncorrectRole() {
        assertFalse(iframe().attr("role", "presentation").render().contains("role=\"button\""));
    }

    @Test
    public void testIframeDoesNotIncludeCustomDataAttributeIfNotSet() {
        assertFalse(iframe().render().contains("data-custom="));
    }

    @Test
    public void testIframeDoesNotRenderSrcsetIfNotProvided() {
        assertFalse(iframe().render().contains("srcset="));
    }

    @Test
    public void testIframeDoesNotMisuseSandboxAttribute() {
        assertFalse(iframe().attr("sandbox", "allow-scripts").render().contains("sandbox=\"allow-top-navigation\""));
    }

    @Test
    public void testIframeDoesNotRenderAllowFullScreenIncorrectly() {
        assertFalse(iframe().attr("allowfullscreen").render().contains("allowfullscreen=\"false\""));
    }

    @Test
    public void testIframeDoesNotContainAriaHiddenWhenNotSet() {
        assertFalse(iframe().render().contains("aria-hidden=\"true\""));
    }

    @Test
    public void testIframeDoesNotIncludeIncorrectLoadingAttribute() {
        assertFalse(iframe().attr("loading", "lazy").render().contains("loading=\"eager\""));
    }

    @Test
    public void testIframeDoesNotIncludeIncorrectName() {
        assertFalse(iframe().withName("frameName").render().contains("name=\"wrongName\""));
    }

    @Test
    public void testIframeDoesNotMisrenderSrcDoc() {
        assertFalse(iframe().attr("srcdoc", "<p>Hello, world!</p>").render().contains("srcdoc=\"<p>Goodbye, world!</p>\""));
    }

    @Test
    public void testIframeDoesNotIncludeBorderWhenNotSet() {
        assertFalse(iframe().render().contains("frameborder=\"1\""));
    }


    @Test
    public void testIframeDoesNotMisplaceTitle() {
        assertFalse(iframe().withTitle("Frame Title").render().contains("title=\"Wrong Title\""));
    }

    @Test
    public void testIframeDoesNotIncludeIncorrectLangAttribute() {
        assertFalse(iframe().attr("lang", "en").render().contains("lang=\"es\""));
    }

    @Test
    public void testIframeDoesNotMisuseTabIndex() {
        assertFalse(iframe().attr("tabindex", "-1").render().contains("tabindex=\"1\""));
    }

    @Test
    public void testIframeDoesNotIncludeIncorrectAlignment() {
        assertFalse(iframe().attr("align", "center").render().contains("align=\"left\""));
    }

    @Test
    public void testIframeDoesNotIncludeAccessKeyIfNotProvided() {
        assertFalse(iframe().render().contains("accesskey=\"k\""));
    }

    @Test
    public void testIframeDoesNotIncludeLongDescWhenNotSet() {
        assertFalse(iframe().render().contains("longdesc="));
    }



    @Test
    public void testIframeDoesNotIncludeOnClickIfNotProvided() {
        assertFalse(iframe().render().contains("onclick="));
    }

    @Test
    public void testIframeDoesNotRenderCompleteStyleIncorrectly() {
        assertFalse(iframe().withStyle("border: none; width: 100%; height: 100%;").render().contains("style=\"border: solid; width: 50%; height: 50%;\""));
    }





    /**Testing  Iframe ends here
     * *************************************************************
     * ***************************************************************
     */




   /** @Test
    public void testAudioWithAttributes() {
        String expectedHtml = "<audio controls><source src=\"audio.mp3\" type=\"audio/mpeg\"></audio>";
        String actualHtml = audio(
                source().withSrc("audio.mp3").withType("audio/mpeg")
        ).attr("controls", "true").render(); // Using attr to manually add controls
        assertEquals(expectedHtml, actualHtml);
    }**/




    /**Testing  Meta Starts here
     * *************************************************************
     * ***************************************************************
     */


    @Test
    public void testMetaTags() {
        String expectedHtml = "<meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        String actualHtml = meta().withCharset("UTF-8").render() +
                meta().withName("viewport").withContent("width=device-width, initial-scale=1.0").render();
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    public void testMetaCharset() {
        assertEquals("<meta charset=\"UTF-8\">", meta().withCharset("UTF-8").render());
    }


    @Test
    public void testMetaNameDescription() {
        assertEquals("<meta name=\"description\" content=\"This is a test description.\">",
                meta().withName("description").withContent("This is a test description.").render());
    }

    @Test
    public void testMetaNameKeywords() {
        assertEquals("<meta name=\"keywords\" content=\"HTML, CSS, XML, JavaScript\">",
                meta().withName("keywords").withContent("HTML, CSS, XML, JavaScript").render());
    }

    @Test
    public void testMetaNameAuthor() {
        assertEquals("<meta name=\"author\" content=\"John Doe\">",
                meta().withName("author").withContent("John Doe").render());
    }

    @Test
    public void testMetaNameViewport() {
        assertEquals("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">",
                meta().withName("viewport").withContent("width=device-width, initial-scale=1.0").render());
    }

    @Test
    public void testMetaNameRobots() {
        assertEquals("<meta name=\"robots\" content=\"index, follow\">",
                meta().withName("robots").withContent("index, follow").render());
    }




    @Test
    public void testMetaCharsetCapitalized() {
        assertEquals("<meta charset=\"UTF-8\">", meta().withCharset("UTF-8").render());
    }

    @Test
    public void testMetaNameGenerator() {
        assertEquals("<meta name=\"generator\" content=\"J2html\">",
                meta().withName("generator").withContent("J2html").render());
    }

    @Test
    public void testMetaNameApplicationName() {
        assertEquals("<meta name=\"application-name\" content=\"My Application\">",
                meta().withName("application-name").withContent("My Application").render());
    }

    @Test
    public void testMetaNameThemeColor() {
        assertEquals("<meta name=\"theme-color\" content=\"#ffffff\">",
                meta().withName("theme-color").withContent("#ffffff").render());
    }



    @Test
    public void testMetaNameMobileOptimized() {
        assertEquals("<meta name=\"MobileOptimized\" content=\"width\">",
                meta().withName("MobileOptimized").withContent("width").render());
    }

    @Test
    public void testMetaNameHandheldFriendly() {
        assertEquals("<meta name=\"HandheldFriendly\" content=\"true\">",
                meta().withName("HandheldFriendly").withContent("true").render());
    }



    @Test
    public void testMetaNameRating() {
        assertEquals("<meta name=\"rating\" content=\"general\">",
                meta().withName("rating").withContent("general").render());
    }

    @Test
    public void testMetaNameReferrer() {
        assertEquals("<meta name=\"referrer\" content=\"no-referrer\">",
                meta().withName("referrer").withContent("no-referrer").render());
    }



    @Test
    public void testMetaNameColorScheme() {
        assertEquals("<meta name=\"color-scheme\" content=\"light dark\">",
                meta().withName("color-scheme").withContent("light dark").render());
    }

    @Test
    public void testMetaNameTwitterCard() {
        assertEquals("<meta name=\"twitter:card\" content=\"summary_large_image\">",
                meta().withName("twitter:card").withContent("summary_large_image").render());
    }

    @Test
    public void testMetaNameTwitterSite() {
        assertEquals("<meta name=\"twitter:site\" content=\"@example\">",
                meta().withName("twitter:site").withContent("@example").render());
    }

    @Test
    public void testMetaNameOgType() {
        assertEquals("<meta property=\"og:type\" content=\"website\">",
                meta().attr("property", "og:type").withContent("website").render());
    }

    @Test
    public void testMetaNameOgTitle() {
        assertEquals("<meta property=\"og:title\" content=\"Open Graph Title\">",
                meta().attr("property", "og:title").withContent("Open Graph Title").render());
    }

    @Test
    public void testMetaNameOgDescription() {
        assertEquals("<meta property=\"og:description\" content=\"Description Here\">",
                meta().attr("property", "og:description").withContent("Description Here").render());
    }
    @Test
    public void testMetaCharset1() {
        assertTrue(meta().withCharset("UTF-8").render().equals("<meta charset=\"UTF-8\">"));
    }



    @Test
    public void testMetaNameDescription1() {
        assertTrue(meta().withName("description").withContent("This is a test description.").render().equals(
                "<meta name=\"description\" content=\"This is a test description.\">"));
    }

    @Test
    public void testMetaNameKeywords1() {
        assertTrue(meta().withName("keywords").withContent("HTML, CSS, XML, JavaScript").render().equals(
                "<meta name=\"keywords\" content=\"HTML, CSS, XML, JavaScript\">"));
    }

    @Test
    public void testMetaNameAuthor1() {
        assertTrue(meta().withName("author").withContent("John Doe").render().equals(
                "<meta name=\"author\" content=\"John Doe\">"));
    }

    @Test
    public void testMetaNameViewport1() {
        assertTrue(meta().withName("viewport").withContent("width=device-width, initial-scale=1.0").render().equals(
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"));
    }

    @Test
    public void testMetaNameRobots1() {
        assertTrue(meta().withName("robots").withContent("index, follow").render().equals(
                "<meta name=\"robots\" content=\"index, follow\">"));
    }





    @Test
    public void testMetaCharsetCapitalized1() {
        assertTrue(meta().withCharset("UTF-8").render().equals("<meta charset=\"UTF-8\">"));
    }

    @Test
    public void testMetaNameGenerator1() {
        assertTrue(meta().withName("generator").withContent("J2html").render().equals(
                "<meta name=\"generator\" content=\"J2html\">"));
    }

    @Test
    public void testMetaNameApplicationName1() {
        assertTrue(meta().withName("application-name").withContent("My Application").render().equals(
                "<meta name=\"application-name\" content=\"My Application\">"));
    }

    @Test
    public void testMetaNameThemeColor1() {
        assertTrue(meta().withName("theme-color").withContent("#ffffff").render().equals(
                "<meta name=\"theme-color\" content=\"#ffffff\">"));
    }



    @Test
    public void testMetaNameMobileOptimized1() {
        assertTrue(meta().withName("MobileOptimized").withContent("width").render().equals(
                "<meta name=\"MobileOptimized\" content=\"width\">"));
    }

    @Test
    public void testMetaNameHandheldFriendly1() {
        assertTrue(meta().withName("HandheldFriendly").withContent("true").render().equals(
                "<meta name=\"HandheldFriendly\" content=\"true\">"));
    }

    @Test
    public void testMetaNameFormatDetection() {
        assertFalse(meta().withName("format-detection").withContent("telephone=no").render().equals(
                "<meta name=\"format-detection\" content=\"\"telephone=no\">"));
    }

        @Test
        public void testMetaNameRating1() {
            assertTrue(meta().withName("rating").withContent("general").render().equals(
                    "<meta name=\"rating\" content=\"general\">"));
        }

        @Test
        public void testMetaNameReferrer1() {
            assertTrue(meta().withName("referrer").withContent("no-referrer").render().equals(
                    "<meta name=\"referrer\" content=\"no-referrer\">"));
        }

        @Test
        public void testMetaNameColorScheme1() {
            assertTrue(meta().withName("color-scheme").withContent("light dark").render().equals(
                    "<meta name=\"color-scheme\" content=\"light dark\">"));
        }

        @Test
        public void testMetaNameTwitterCard1() {
            assertTrue(meta().withName("twitter:card").withContent("summary_large_image").render().equals(
                    "<meta name=\"twitter:card\" content=\"summary_large_image\">"));
        }

        @Test
        public void testMetaNameTwitterSite1() {
            assertTrue(meta().withName("twitter:site").withContent("@example").render().equals(
                    "<meta name=\"twitter:site\" content=\"@example\">"));
        }

        @Test
        public void testMetaNameOgType1() {
            assertTrue(meta().attr("property", "og:type").withContent("website").render().equals(
                    "<meta property=\"og:type\" content=\"website\">"));
        }

        @Test
        public void testMetaNameOgTitle1() {
            assertTrue(meta().attr("property", "og:title").withContent("Open Graph Title").render().equals(
                    "<meta property=\"og:title\" content=\"Open Graph Title\">"));
        }

        @Test
        public void testMetaNameOgDescription1() {
            assertTrue(meta().attr("property", "og:description").withContent("Description Here").render().equals(
                    "<meta property=\"og:description\" content=\"Description Here\">"));
        }
    @Test
    public void testMetaDoesNotContainUnsetCharset() {
        assertFalse(meta().render().contains("charset="));
    }

    @Test
    public void testMetaDoesNotRenderWrongDescription() {
        assertFalse(meta().withName("description").withContent("Valid description.").render().contains("Invalid description"));
    }

    @Test
    public void testMetaDoesNotIncludeWrongKeywords() {
        assertFalse(meta().withName("keywords").withContent("HTML, CSS").render().contains("PHP, Perl"));
    }

    @Test
    public void testMetaDoesNotMisuseAuthorContent() {
        assertFalse(meta().withName("author").withContent("John Doe").render().contains("Jane Doe"));
    }

    @Test
    public void testMetaDoesNotMisstateViewportSettings() {
        assertFalse(meta().withName("viewport").withContent("width=device-width, initial-scale=1.0").render().contains("initial-scale=2.0"));
    }

    @Test
    public void testMetaDoesNotMistakeRobotsContent() {
        assertFalse(meta().withName("robots").withContent("index, follow").render().contains("noindex, nofollow"));
    }


    @Test
    public void testMetaDoesNotIncludeIncorrectGenerator() {
        assertFalse(meta().withName("generator").withContent("J2html").render().contains("WordPress"));
    }

    @Test
    public void testMetaDoesNotMisstateApplicationName() {
        assertFalse(meta().withName("application-name").withContent("My Application").render().contains("Your Application"));
    }

    @Test
    public void testMetaDoesNotIncludeIncorrectThemeColor() {
        assertFalse(meta().withName("theme-color").withContent("#ffffff").render().contains("#000000"));
    }



    @Test
    public void testMetaDoesNotIncludeWrongMobileOptimized() {
        assertFalse(meta().withName("MobileOptimized").withContent("width").render().contains("height"));
    }

    @Test
    public void testMetaDoesNotIncludeIncorrectFormatDetection() {
        assertFalse(meta().withName("format-detection").withContent("telephone=no").render().contains("telephone=yes"));
    }

    @Test
    public void testMetaDoesNotIncludeWrongRating() {
        assertFalse(meta().withName("rating").withContent("general").render().contains("mature"));
    }

    @Test
    public void testMetaDoesNotMistakeReferrerPolicy() {
        assertFalse(meta().withName("referrer").withContent("no-referrer").render().contains("origin"));
    }


    @Test
    public void testMetaDoesNotMisstateColorScheme() {
        assertFalse(meta().withName("color-scheme").withContent("light dark").render().contains("dark only"));
    }

    @Test
    public void testMetaDoesNotMisrepresentTwitterCard() {
        assertTrue(meta().withName("twitter:card").withContent("summary_large_image").render().contains("summary"));
    }




        /**Testing  Meta ends here
         * *************************************************************
         * ***************************************************************
         */


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

    /** Testing script tag starts here
     * *************************************************************************************
     * **************************************************************************************
     */

    @Test
    public void testScriptTag() {
        String expectedHtml = "<script src=\"app.js\"></script>";
        String actualHtml = script().withSrc("app.js").render();
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    public void testEmptyScriptTag() {
        assertEquals("<script></script>", script().render());
    }

    @Test
    public void testScriptTagWithSrc() {
        assertEquals("<script src=\"script.js\"></script>", script().withSrc("script.js").render());
    }

    @Test
    public void testScriptTagWithType() {
        assertEquals("<script type=\"text/javascript\"></script>", script().withType("text/javascript").render());
    }

    @Test
    public void testScriptTagWithAsync() {
        assertEquals("<script src=\"script.js\" async></script>", script().withSrc("script.js").attr("async").render());
    }

    @Test
    public void testScriptTagWithDefer() {
        assertEquals("<script src=\"script.js\" defer></script>", script().withSrc("script.js").attr("defer").render());
    }

    @Test
    public void testScriptTagWithCharset() {
        assertEquals("<script charset=\"utf-8\"></script>", script().withCharset("utf-8").render());
    }



    @Test
    public void testScriptTagWithTextContent() {
        assertEquals("<script>alert('Hello, world!');</script>", script("alert('Hello, world!');").render());
    }

    @Test
    public void testScriptTagWithComplexJavaScript() {
        String jsCode = "if (window.confirm('Are you sure?')) { console.log('Confirmed'); } else { console.log('Cancelled'); }";
        assertEquals("<script>" + jsCode + "</script>", script(jsCode).render());
    }

    @Test
    public void testScriptTagWithModuleType() {
        assertEquals("<script type=\"module\"></script>", script().withType("module").render());
    }



    @Test
    public void testScriptTagWithNoModule() {
        assertEquals("<script nomodule></script>", script().attr("nomodule").render());
    }



    @Test
    public void testScriptTagWithCustomAttribute() {
        assertEquals("<script data-custom=\"value\"></script>", script().attr("data-custom", "value").render());
    }

    @Test
    public void testScriptTagWithMultipleAttributes() {
        assertEquals("<script src=\"script.js\" type=\"text/javascript\" async defer></script>", script().withSrc("script.js").withType("text/javascript").attr("async").attr("defer").render());
    }

    @Test
    public void testScriptTagWithInnerHtmlContent() {
        assertEquals("<script>document.getElementById('demo').innerHTML = 'Hello JavaScript!';</script>", script("document.getElementById('demo').innerHTML = 'Hello JavaScript!';").render());
    }

    @Test
    public void testScriptTagWithSrcAndType() {
        assertEquals("<script src=\"script.js\" type=\"text/javascript\"></script>", script().withSrc("script.js").withType("text/javascript").render());
    }

    @Test
    public void testScriptTagWithMultipleLinesOfCode() {
        String jsCode = "function myFunction() {\n" +
                "  document.getElementById('demo').innerHTML = 'Paragraph changed.';\n" +
                "}";
        assertEquals("<script>" + jsCode + "</script>", script(jsCode).render());
    }

    @Test
    public void testScriptTagWithCommentedJsCode() {
        String jsCode = "/* JavaScript code */\n" +
                "console.log('This is a comment.');";
        assertEquals("<script>" + jsCode + "</script>", script(jsCode).render());
    }

    @Test
    public void testScriptTagWithLinkToExternalJs1() {
        assertEquals("<script src=\"https://cdn.example.com/example.js\"></script>", script().withSrc("https://cdn.example.com/example.js").render());
    }

    @Test
    public void testScriptTagWithDeprecatedType1() {
        assertEquals("<script type=\"text/javascript\"></script>", script().withType("text/javascript").render());
    }


    @Test
    public void testScriptTagWithSelfExecutingFunction1() {
        String jsCode = "(function(){ console.log('Self-executing function'); })();";
        assertEquals("<script>" + jsCode + "</script>", script(jsCode).render());
    }

    @Test
    public void testScriptTagWithAsyncAndDefer1() {
        assertEquals("<script src=\"async-defer.js\" async defer></script>", script().withSrc("async-defer.js").attr("async").attr("defer").render());
    }

    @Test
    public void testScriptTagWithEmbeddedObject1() {
        String jsCode = "var obj = {key: 'value'};";
        assertEquals("<script>" + jsCode + "</script>", script(jsCode).render());
    }

    @Test
    public void testScriptTagWithFunctionDeclaration1() {
        String jsCode = "function greet() { alert('Hello!'); }";
        assertEquals("<script>" + jsCode + "</script>", script(jsCode).render());
    }

    @Test
    public void testScriptTagWithEventListeners1() {
        String jsCode = "document.addEventListener('DOMContentLoaded', function() { console.log('Document loaded'); });";
        assertEquals("<script>" + jsCode + "</script>", script(jsCode).render());
    }
    @Test
    public void testEmptyScriptTag1() {
        assertTrue(script().render().equals("<script></script>"));
    }

    @Test
    public void testScriptTagWithSrc1() {
        assertTrue(script().withSrc("script.js").render().equals("<script src=\"script.js\"></script>"));
    }

    @Test
    public void testScriptTagWithType1() {
        assertTrue(script().withType("text/javascript").render().equals("<script type=\"text/javascript\"></script>"));
    }

    @Test
    public void testScriptTagWithAsync1() {
        assertTrue(script().withSrc("script.js").attr("async").render().equals("<script src=\"script.js\" async></script>"));
    }

    @Test
    public void testScriptTagWithDefer1() {
        assertTrue(script().withSrc("script.js").attr("defer").render().equals("<script src=\"script.js\" defer></script>"));
    }

    @Test
    public void testScriptTagWithCharset1() {
        assertTrue(script().withCharset("utf-8").render().equals("<script charset=\"utf-8\"></script>"));
    }


    @Test
    public void testScriptTagWithTextContent1() {
        assertTrue(script("alert('Hello, world!');").render().equals("<script>alert('Hello, world!');</script>"));
    }

    @Test
    public void testScriptTagWithComplexJavaScript1() {
        String jsCode = "if (window.confirm('Are you sure?')) { console.log('Confirmed'); } else { console.log('Cancelled'); }";
        assertTrue(script(jsCode).render().equals("<script>" + jsCode + "</script>"));
    }

    @Test
    public void testScriptTagWithModuleType1() {
        assertTrue(script().withType("module").render().equals("<script type=\"module\"></script>"));
    }



    @Test
    public void testScriptTagWithNoModule1() {
        assertTrue(script().attr("nomodule").render().equals("<script nomodule></script>"));
    }



    @Test
    public void testScriptTagWithCustomAttribute1() {
        assertTrue(script().attr("data-custom", "value").render().equals("<script data-custom=\"value\"></script>"));
    }

    @Test
    public void testScriptTagWithMultipleAttributes1() {
        assertTrue(script().withSrc("script.js").withType("text/javascript").attr("async").attr("defer").render().equals("<script src=\"script.js\" type=\"text/javascript\" async defer></script>"));
    }

    @Test
    public void testScriptTagWithInnerHtmlContent1() {
        assertTrue(script("document.getElementById('demo').innerHTML = 'Hello JavaScript!';").render().equals("<script>document.getElementById('demo').innerHTML = 'Hello JavaScript!';</script>"));
    }

    @Test
    public void testScriptTagWithSrcAndType1() {
        assertTrue(script().withSrc("script.js").withType("text/javascript").render().equals("<script src=\"script.js\" type=\"text/javascript\"></script>"));
    }

    @Test
    public void testScriptTagWithMultipleLinesOfCode1() {
        String jsCode = "function myFunction() {\n" +
                "  document.getElementById('demo').innerHTML = 'Paragraph changed.';\n" +
                "}";
        assertTrue(script(jsCode).render().equals("<script>" + jsCode + "</script>"));
    }



    @Test
    public void testScriptTagWithLinkToExternalJs() {
        assertTrue(script().withSrc("https://cdn.example.com/example.js").render().equals("<script src=\"https://cdn.example.com/example.js\"></script>"));
    }

    @Test
    public void testScriptTagWithDeprecatedType() {
        assertTrue(script().withType("text/javascript").render().equals("<script type=\"text/javascript\"></script>"));
    }




    @Test
    public void testScriptTagWithSelfExecutingFunction() {
        String jsCode = "(function(){ console.log('Self-executing function'); })();";
        assertTrue(script(jsCode).render().equals("<script>" + jsCode + "</script>"));
    }

    @Test
    public void testScriptTagWithAsyncAndDefer() {
        assertTrue(script().withSrc("async-defer.js").attr("async").attr("defer").render().equals("<script src=\"async-defer.js\" async defer></script>"));
    }

    @Test
    public void testScriptTagWithEmbeddedObject() {
        String jsCode = "var obj = {key: 'value'};";
        assertTrue(script(jsCode).render().equals("<script>" + jsCode + "</script>"));
    }

    @Test
    public void testScriptTagWithFunctionDeclaration() {
        String jsCode = "function greet() { alert('Hello!'); }";
        assertTrue(script(jsCode).render().equals("<script>" + jsCode + "</script>"));
    }

    @Test
    public void testScriptTagWithEventListeners() {
        String jsCode = "document.addEventListener('DOMContentLoaded', function() { console.log('Document loaded'); });";
        assertTrue(script(jsCode).render().equals("<script>" + jsCode + "</script>"));
    }


    @Test
    public void testScriptTagDoesNotContainUnsetSrc() {
        assertFalse(script().render().contains("src="));
    }

    @Test
    public void testScriptTagDoesNotIncludeIncorrectType() {
        assertFalse(script().withType("text/javascript").render().contains("type=\"application/json\""));
    }

    @Test
    public void testScriptTagDoesNotRenderAsyncIncorrectly() {
        assertFalse(script().withSrc("script.js").render().contains("async"));
    }

    @Test
    public void testScriptTagDoesNotRenderDeferIncorrectly() {
        assertFalse(script().withSrc("script.js").render().contains("defer"));
    }

    @Test
    public void testScriptTagDoesNotIncludeWrongCharset() {
        assertFalse(script().withCharset("utf-8").render().contains("charset=\"iso-8859-1\""));
    }





    @Test
    public void testScriptTagDoesNotRenderTextContentIncorrectly() {
        assertFalse(script("alert('Hello, world!');").render().contains("alert('Goodbye, world!');"));
    }

    @Test
    public void testScriptTagDoesNotMisrenderComplexJavaScript() {
        String jsCode = "if (window.confirm('Are you sure?')) { console.log('Confirmed'); } else { console.log('Cancelled'); }";
        assertFalse(script(jsCode).render().contains("if (window.confirm('Are you sure?')) { console.log('Not Confirmed'); }"));
    }

    @Test
    public void testScriptTagDoesNotIncludeWrongModuleType() {
        assertFalse(script().withType("module").render().contains("type=\"text/javascript\""));
    }



    @Test
    public void testScriptTagDoesNotRenderNoModuleIncorrectly() {
        assertTrue(script().attr("nomodule").render().contains("module"));
    }



    @Test
    public void testScriptTagDoesNotIncludeWrongCustomAttributes() {
        assertFalse(script().attr("data-custom", "value").render().contains("data-custom=\"wrongValue\""));
    }

    @Test
    public void testScriptTagDoesNotIncludeMultipleWrongAttributes() {
        assertFalse(script().withSrc("script.js").withType("text/javascript").attr("async").attr("defer").render().contains("src=\"wrongScript.js\" type=\"module\""));
    }

    @Test
    public void testScriptTagDoesNotIncludeWrongInnerHtmlContent() {
        assertFalse(script("document.getElementById('demo').innerHTML = 'Hello JavaScript!';").render().contains("document.getElementById('demo').innerHTML = 'Wrong JavaScript!';"));
    }

    @Test
    public void testScriptTagDoesNotIncludeSrcAndTypeIncorrectly() {
        assertFalse(script().withSrc("script.js").withType("text/javascript").render().contains("src=\"wrongScript.js\" type=\"application/json\""));
    }

    @Test
    public void testScriptTagDoesNotIncludeMultipleLinesOfCodeIncorrectly() {
        String jsCode = "function myFunction() {\n" +
                "  document.getElementById('demo').innerHTML = 'Paragraph changed.';\n" +
                "}";
        assertFalse(script(jsCode).render().contains("function myFunction() {\n" +
                "  document.getElementById('demo').innerHTML = 'Paragraph not changed.';\n" +
                "}"));
    }

    @Test
    public void testScriptTagDoesNotIncludeCommentedJsCodeIncorrectly() {
        String jsCode = "/* JavaScript code */\n" +
                "console.log('This is a comment.');";
        assertFalse(script(jsCode).render().contains("/* JavaScript code*/\n" +
                "console.log('This is not a comment.');"));
    }

    @Test
    public void testScriptTagDoesNotIncludeLinkToExternalJsIncorrectly() {
        assertFalse(script().withSrc("https://cdn.example.com/example.js").render().contains("src=\"https://cdn.example.com/wrongExample.js\""));
    }

    @Test
    public void testScriptTagDoesNotIncludeDeprecatedTypeIncorrectly() {
        assertFalse(script().withType("text/javascript").render().contains("type=\"application/json\""));
    }



    @Test
    public void testScriptTagDoesNotIncludeSelfExecutingFunctionIncorrectly() {
        String jsCode = "(function(){ console.log('Self-executing function'); })();";
        assertFalse(script(jsCode).render().contains("(function(){ console.log('Incorrect function'); })();"));
    }

    @Test
    public void testScriptTagDoesNotIncludeAsyncAndDeferIncorrectly() {
        assertFalse(script().withSrc("async-defer.js").attr("async").attr("defer").render().contains("<script src=\"async-defer.js\" async=\"false\" defer=\"false\"></script>"));
    }

    @Test
    public void testScriptTagDoesNotIncludeEmbeddedObjectIncorrectly() {
        String jsCode = "var obj = {key: 'value'};";
        assertFalse(script(jsCode).render().contains("var obj = {key: 'wrongValue'};"));
    }

    @Test
    public void testScriptTagDoesNotIncludeFunctionDeclarationIncorrectly() {
        String jsCode = "function greet() { alert('Hello!'); }";
        assertFalse(script(jsCode).render().contains("function greet() { alert('Goodbye!'); }"));
    }

    @Test
    public void testScriptTagDoesNotIncludeEventListenersIncorrectly() {
        String jsCode = "document.addEventListener('DOMContentLoaded', function() { console.log('Document loaded'); });";
        assertFalse(script(jsCode).render().contains("document.addEventListener('DOMContentLoaded', function() { console.log('Document not loaded'); });"));
    }


    /** Testing script tag ends here
     * *************************************************************************************
     * **************************************************************************************
     */

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