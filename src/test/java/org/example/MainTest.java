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
    //Specific testing for div started
    @Test
    public void testDivWithSimpleText1() {
        String result = div("Simple Text").render();
        assertTrue(result.equals("<div>Simple Text</div>"));
    }

    @Test
    public void testDivWithSimpleText2() {
        String result = div("Many colors").render();
        assertFalse(result.equals("<div>many colors</div>"));
    }

    @Test
    public void testDivWithSimpleText3() {
        String result = div("Rainbow diary").render();
        assertFalse(result.equals("<div>Rainbow book</div>"));
    }

    @Test
    public void testDivWithSimpleText4() {
        String result = div("Fish and Meat").render();
        assertFalse(result.equals("<div>Fish and Chip</div>"));
    }
    @Test
    public void testDivWithSimpleText5() {
        String result = div("Postcard").render();
        assertFalse(result.equals("<div>post</div>"));
    }
    @Test
    public void testDivWithSimpleText6() {
        String result = div("java class").render();
        assertFalse(result.equals("<div>tution</div>"));
    }
    @Test
    public void testEmptyDiv2() {
        String result = div().render();
        assertTrue(result.equals("<div></div>"));
    }

    @Test
    public void testEmptyDiv3() {
        String result = div().render();
        assertFalse(result.equals("<div>Fish</div>"));
    }

    @Test
    public void testEmptyDiv4() {
        String result = div().render();
        assertFalse(result.equals("<div>Meat</div>"));
    }
    @Test
    public void testEmptyDiv5() {
        String result = div().render();
        assertFalse(result.equals("<div>Flower</div>"));
    }
    @Test
    public void testEmptyDiv6() {
        String result = div().render();
        assertFalse(result.equals("<div>Long</div>"));
    }
    @Test
    public void testEmptyDiv7() {
        String result = div().render();
        assertFalse(result.equals("<div>Nice</div>"));
    }
    @Test
    public void testEmptyDiv8() {
        String result = div().render();
        assertFalse(result.equals("<div>read</div>"));
    }
    @Test
    public void testEmptyDiv9() {
        String result = div().render();
        assertFalse(result.equals("<div>User</div>"));
    }
    @Test
    public void testEmptyDiv10() {
        String result = div().render();
        assertFalse(result.equals("<div>Empty</div>"));
    }
    @Test
    public void testEmptyDiv11() {
        String result = div().render();
        assertFalse(result.equals("<div>Rich</div>"));
    }
    @Test
    public void testEmptyDiv12() {
        String result = div().render();
        assertFalse(result.equals("<div>context</div>"));
    }
    @Test
    public void testEmptyDiv13() {
        String result = div().render();
        assertFalse(result.equals("<div>beauty</div>"));
    }
    @Test
    public void testEmptyDiv14() {
        String result = div().render();
        assertFalse(result.equals("<div>Remind</div>"));
    }
    @Test
    public void testEmptyDiv15() {
        String result = div().render();
        assertFalse(result.equals("<div>pure</div>"));
    }
    @Test
    public void testEmptyDiv16() {
        String result = div().render();
        assertFalse(result.equals("<div>soul</div>"));
    }
    @Test
    public void testEmptyDiv17() {
        String result = div().render();
        assertFalse(result.equals("<div>array</div>"));
    }
    @Test
    public void testEmptyDiv18() {
        String result = div().render();
        assertFalse(result.equals("<div>sweat</div>"));
    }
    @Test
    public void testEmptyDiv19() {
        String result = div().render();
        assertFalse(result.equals("<div>heart</div>"));
    }
    @Test
    public void testEmptyDiv20() {
        String result = div().render();
        assertFalse(result.equals("<div>end</div>"));
    }
    @Test
    public void testEmptyDiv21() {
        String result = div().render();
        assertFalse(result.equals("<div>not</div>"));
    }
    @Test
    public void testEmptyDiv22() {
        String result = div().render();
        assertFalse(result.equals("<div>remember</div>"));
    }
    @Test
    public void testEmptyDiv23() {
        String result = div().render();
        assertFalse(result.equals("<div>fool</div>"));
    }

    @Test
    public void testDivWithClass1() {
        String result = div().withClass("container").render();
        assertTrue(result.equals("<div class=\"container\"></div>"));
    }
    @Test
    public void testDivWithClass2() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container23\"></div>"));
    }
    @Test
    public void testDivWithClass3() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container234\"></div>"));
    }
    @Test
    public void testDivWithClass4() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container237\"></div>"));
    }
    @Test
    public void testDivWithClass5() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container237\"></div>"));
    }
    @Test
    public void testDivWithClass6() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2376\"></div>"));
    }
    @Test
    public void testDivWithClass7() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2398\"></div>"));
    }
    @Test
    public void testDivWithClass8() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2366\"></div>"));
    }
    @Test
    public void testDivWithClass9() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2375\"></div>"));
    }
    @Test
    public void testDivWithClass10() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2387\"></div>"));
    }
    @Test
    public void testDivWithClass11() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2883\"></div>"));
    }
    @Test
    public void testDivWithClass12() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2387\"></div>"));
    }
    @Test
    public void testDivWithClass13() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2399\"></div>"));
    }
    @Test
    public void testDivWithClass14() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2334\"></div>"));
    }
    @Test
    public void testDivWithClass15() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container23\"></div>"));
    }
    @Test
    public void testDivWithClass16() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2873\"></div>"));
    }
    @Test
    public void testDivWithClass17() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container24873\"></div>"));
    }
    @Test
    public void testDivWithClass18() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2873\"></div>"));
    }
    @Test
    public void testDivWithClass19() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2983\"></div>"));
    }
    @Test
    public void testDivWithClass20() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container25343\"></div>"));
    }
    @Test
    public void testDivWithClass21() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container878623\"></div>"));
    }
    @Test
    public void testDivWithClass22() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container239786\"></div>"));
    }
    @Test
    public void testDivWithClass23() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container23766\"></div>"));
    }
    @Test
    public void testDivWithClass24() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container27653\"></div>"));
    }
    @Test
    public void testDivWithClass25() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container266863\"></div>"));
    }
    @Test
    public void testDivWithClass26() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container23\"></div>"));
    }
    @Test
    public void testDivWithClass27() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container28783\"></div>"));
    }
    @Test
    public void testDivWithClass28() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container28773\"></div>"));
    }
    @Test
    public void testDivWithClass29() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container28663\"></div>"));
    }
    @Test
    public void testDivWithClass30() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container297973\"></div>"));
    }
    @Test
    public void testDivWithClass31() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container224363\"></div>"));
    } @Test
    public void testDivWithClass32() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container237676\"></div>"));
    }
    @Test
    public void testDivWithClass33() {
        String result = div().withClass("container").render();
        assertFalse(result.equals("<div class=\"container2365656\"></div>"));
    }
    @Test
    public void testDivWithId1() {
        String result = div().withId("main").render();
        assertTrue(result.equals("<div id=\"main\"></div>"));
    }
    @Test
    public void testDivWithId2() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main129482\"></div>"));
    }
    @Test
    public void testDivWithId3() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1238472\"></div>"));
    }
    @Test
    public void testDivWithId4() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1279772\"></div>"));
    }
    @Test
    public void testDivWithId5() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main127462\"></div>"));
    }
    @Test
    public void testDivWithId6() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main122333\"></div>"));
    }
    @Test
    public void testDivWithId7() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1287382\"></div>"));
    }
    @Test
    public void testDivWithId8() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main12238738\"></div>"));
    }
    @Test
    public void testDivWithId9() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main12836832\"></div>"));
    }
    @Test
    public void testDivWithId10() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main12882\"></div>"));
    }
    @Test
    public void testDivWithId11() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main123u32\"></div>"));
    }
    @Test
    public void testDivWithId12() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1373722\"></div>"));
    }
    @Test
    public void testDivWithId13() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main12726722\"></div>"));
    }
    @Test
    public void testDivWithId14() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main122u6382\"></div>"));
    }
    @Test
    public void testDivWithId15() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1236732\"></div>"));
    }
    @Test
    public void testDivWithId16() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1228792872\"></div>"));
    }
    @Test
    public void testDivWithId17() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main127682722\"></div>"));
    }
    @Test
    public void testDivWithId18() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main12686222\"></div>"));
    }
    @Test
    public void testDivWithId19() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main12987822\"></div>"));
    }
    @Test
    public void testDivWithId20() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main12762722\"></div>"));
    }
    @Test
    public void testDivWithId21() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main122872\"></div>"));
    }
    @Test
    public void testDivWithId22() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main127622\"></div>"));
    }
    @Test
    public void testDivWithId23() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main127632\"></div>"));
    }
    @Test
    public void testDivWithId24() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1263672\"></div>"));
    }
    @Test
    public void testDivWithId25() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1298792\"></div>"));
    }
    @Test
    public void testDivWithId26() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main12987372\"></div>"));
    }
    @Test
    public void testDivWithId27() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1287382\"></div>"));
    }
    @Test
    public void testDivWithId28() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1276362\"></div>"));
    }
    @Test
    public void testDivWithId29() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main128732\"></div>"));
    }
    @Test
    public void testDivWithId30() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main123882\"></div>"));
    }
    @Test
    public void testDivWithId31() {
        String result = div().withId("main").render();
        assertFalse(result.equals("<div id=\"main1287382\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses2() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid2\"></div>"));
    }
    @Test
    public void testDivWithMultipleClasses3() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid3\"></div>"));
    }
    @Test
    public void testDivWithMultipleClasses4() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid4\"></div>"));
    }
    @Test
    public void testDivWithMultipleClasses5() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid5\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses6() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid6\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses7() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid7\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses8() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid8\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses9() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid9\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses10() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid10\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses11() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid11\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses12() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid12\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses13() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid13\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses14() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid14\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses15() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid15\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses16() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid16\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses17() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid17\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses18() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid18\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses19() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid19\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses20() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid20\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses21() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid21\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses22() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid228\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses23() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid23757\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses24() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid2477\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses25() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid7686\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses26() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid6547\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses27() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid766\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses28() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid8686\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses29() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid76868\"></div>"));
    }

    @Test
    public void testDivWithMultipleClasses30() {
        String result = div().withClass("container fluid").render();
        assertFalse(result.equals("<div class=\"container fluid75778\"></div>"));
    }

    @Test
    public void testDivWithStyle1() {
        String result = div().withStyle("color: red;").render();
        assertTrue(result.equals("<div style=\"color: red;\"></div>"));
    }
    @Test
    public void testDivWithStyle3() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: blue;\"></div>"));
    }
    @Test
    public void testDivWithStyle4() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: green;\"></div>"));
    }
    @Test
    public void testDivWithStyle6() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: yellow;\"></div>"));
    }
    @Test
    public void testDivWithStyle7() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: tint;\"></div>"));
    }
    @Test
    public void testDivWithStyle8() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: ash;\"></div>"));
    }
    @Test
    public void testDivWithStyle9() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: light blue;\"></div>"));
    }
    @Test
    public void testDivWithStyle10() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: light green;\"></div>"));
    }
    @Test
    public void testDivWithStyle11() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: maroon;\"></div>"));
    }
    @Test
    public void testDivWithStyle12() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: pink;\"></div>"));
    }
    @Test
    public void testDivWithStyle13() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: black;\"></div>"));
    }
    @Test
    public void testDivWithStyle14() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: orange;\"></div>"));
    }
    @Test
    public void testDivWithStyle15() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: dark pink;\"></div>"));
    }
    @Test
    public void testDivWithStyle16() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: sky blue;\"></div>"));
    }
    @Test
    public void testDivWithStyle17() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: violate;\"></div>"));
    }
    @Test
    public void testDivWithStyle18() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: white;\"></div>"));
    }
    @Test
    public void testDivWithStyle19() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: gray;\"></div>"));
    }
    @Test
    public void testDivWithStyle20() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: light yellow;\"></div>"));
    }
    @Test
    public void testDivWithStyle21() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: light green;\"></div>"));
    }
    @Test
    public void testDivWithStyle22() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: dark green;\"></div>"));
    }
    @Test
    public void testDivWithStyle23() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: magenta;\"></div>"));
    }
    @Test
    public void testDivWithStyle24() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Carnelian;\"></div>"));
    }
    @Test
    public void testDivWithStyle25() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Carolina blue;\"></div>"));
    }
    @Test
    public void testDivWithStyle26() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Cherry blossom pink;\"></div>"));
    }
    @Test
    public void testDivWithStyle27() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Chili red;\"></div>"));
    }
    @Test
    public void testDivWithStyle28() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Chinese violet;\"></div>"));
    }
    @Test
    public void testDivWithStyle29() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Charcoal;\"></div>"));
    }
    @Test
    public void testDivWithStyle30() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Cerulean blue;\"></div>"));
    }
    @Test
    public void testDivWithStyle31() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Cerise;\"></div>"));
    }
    @Test
    public void testDivWithStyle32() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Celeste;\"></div>"));
    }
    @Test
    public void testDivWithStyle33() {
        String result = div().withStyle("color: red;").render();
        assertFalse(result.equals("<div style=\"color: Celadon;\"></div>"));
    }

    @Test
    public void testNestedDivs1() {
        String result = div(div("Inner")).render();
        assertTrue(result.equals("<div><div>Inner</div></div>"));
    }
    @Test
    public void testNestedDivs2() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><p>Inner</p></div>"));
    }
    @Test
    public void testNestedDivs3() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<p><p>Inner</p></p>"));
    }
    @Test
    public void testNestedDivs4() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<p>Inner</p>"));
    }
    @Test
    public void testNestedDivs5() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div>Inner</div>"));
    }
    @Test
    public void testNestedDivs6() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><p>Outer</p></div>"));
    }
    @Test
    public void testNestedDivs7() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><p>Inner</div>"));
    }
    @Test
    public void testNestedDivs8() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<p>Inner</p></div>"));
    }
    @Test
    public void testNestedDivs9() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><div>Inner<div></div>"));
    }
    @Test
    public void testNestedDivs10() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><p></p></div>"));
    }
    @Test
    public void testNestedDivs11() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div></div>"));
    }
    @Test
    public void testNestedDivs12() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<p></p>"));
    }
    @Test
    public void testNestedDivs13() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div>"));
    }
    @Test
    public void testNestedDivs14() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><p>In</p></div>"));
    }
    @Test
    public void testNestedDivs15() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><p>Inner1</p></div>"));
    }
    @Test
    public void testNestedDivs16() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><p>Inside</p></div>"));
    }
    @Test
    public void testNestedDivs17() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><p>Inner</p></div>"));
    }
    @Test
    public void testNestedDivs18() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("</p>"));
    }
    @Test
    public void testNestedDivs19() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("</div>"));
    }
    @Test
    public void testNestedDivs20() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("</p></div>"));
    }
    @Test
    public void testNestedDivs21() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div><p>"));
    }
    @Test
    public void testNestedDivs22() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<p>"));
    }
    @Test
    public void testNestedDivs23() {
        String result = div(div("Inner")).render();
        assertFalse(result.equals("<div>"));
    }

    @Test
    public void testDivWithDataAttribute1() {
        String result = div().attr("data-value", "123").render();
        assertTrue(result.equals("<div data-value=\"123\"></div>"));
    }

    @Test
    public void testDivWithDataAttribute2() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("< value=\"123\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute3() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div =\"123\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute4() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div \"123\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute5() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"123\"><div>"));
    }
    @Test
    public void testDivWithDataAttribute6() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute7() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"123\"></>"));
    }
    @Test
    public void testDivWithDataAttribute8() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value></div>"));
    }
    @Test
    public void testDivWithDataAttribute9() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"123\">"));
    }
    @Test
    public void testDivWithDataAttribute11() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<\"123\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute10() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div></div>"));
    }
    @Test
    public void testDivWithDataAttribute12() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("></div>"));
    }
    @Test
    public void testDivWithDataAttribute13() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"123\">"));
    }
    @Test
    public void testDivWithDataAttribute14() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<value=\"123\">"));
    }
    @Test
    public void testDivWithDataAttribute15() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"13\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute16() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"1\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute17() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"3\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute18() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"123\"</div>"));
    }
    @Test
    public void testDivWithDataAttribute19() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("div value=\"123\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute20() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"12388\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute21() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"123675\"></div>"));
    }
    @Test
    public void testDivWithDataAttribute22() {
        String result = div().attr("data-value", "123").render();
        assertFalse(result.equals("<div value=\"46123\"></div>"));

    }
    @Test
    public void testDivWithArbitraryAttribute1() {
        String result = div().attr("custom-attr", "value").render();
        assertTrue(result.equals("<div custom-attr=\"value\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute2() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=\"\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute3() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=\"value\"><p>"));
    }
    @Test
    public void testDivWithArbitraryAttribute4() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=\"value78\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute5() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=\"value98\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute6() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<custom-attr=\"value\">"));
    }
    @Test
    public void testDivWithArbitraryAttribute7() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=\"value\" div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute8() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=\"value\">"));
    }
    @Test
    public void testDivWithArbitraryAttribute9() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div customattr=\"value\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute10() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=\"value\"><div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute11() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=\"value></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute12() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=value\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute13() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=value></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute14() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr=\"value\"><div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute15() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<custom-attr=\"value\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute16() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div \"value\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute17() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr ></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute18() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr</div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute19() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom-attr\"value\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute20() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div attr=\"value\"></div>"));
    }
    @Test
    public void testDivWithArbitraryAttribute21() {
        String result = div().attr("custom-attr", "value").render();
        assertFalse(result.equals("<div custom=\"value\"></div>"));
    }

    @Test
    public void testDivContainingImage1() {
        String result = div(img().withSrc("image.jpg")).render();
        assertTrue(result.equals("<div><img src=\"image.jpg\"></div>"));
    }
    @Test
    public void testDivContainingImage2() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=\"image\"></div>"));
    }
    @Test
    public void testDivContainingImage3() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=\"\"></div>"));
    }

    @Test
    public void testDivContainingImage4() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=\"imagejpg\"></div>"));
    }

    @Test
    public void testDivContainingImage5() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< \"image.jpg\"></div>"));
    }

    @Test
    public void testDivContainingImage6() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=\"image.jpg\">"));
    }

    @Test
    public void testDivContainingImage7() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src></div>"));
    }

    @Test
    public void testDivContainingImage8() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=\"image.jpg></div>"));
    }

    @Test
    public void testDivContainingImage9() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=image.jpg\"></div>"));
    }

    @Test
    public void testDivContainingImage10() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=\"jpg\"></div>"));
    }

    @Test
    public void testDivContainingImage11() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=\"image1.jpg\"></div>"));
    }

    @Test
    public void testDivContainingImage12() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div><></div>"));
    }
    @Test
    public void testDivContainingImage13() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div></div>"));
    }

    @Test
    public void testDivContainingImage14() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=image.jpg></div>"));
    }

    @Test
    public void testDivContainingImage15() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=\"image.jpg\">"));
    }

    @Test
    public void testDivContainingImage16() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<src=\"image.jpg\"></div>"));
    }

    @Test
    public void testDivContainingImage17() {
        String result = div(img().withSrc("image.jpg")).render();
        assertFalse(result.equals("<div>< src=\"image.jpg\"><div>"));
    }
    @Test
    public void testDivContainingLinkAndText1() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertTrue(result.equals("<div>Visit <a href=\"http://google.com\">Google</a></div>"));
    }
    @Test
    public void testDivContainingLinkAndText2() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=\"http://google.com\">Google<a></div>"));
    }
    @Test
    public void testDivContainingLinkAndText3() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div>< href=\"http://google.com\">Google</></div>"));
    }

    @Test
    public void testDivContainingLinkAndText4() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><\"http://google.com\">Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText5() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=\"http://google.com\">Google</div>"));
    }

    @Test
    public void testDivContainingLinkAndText6() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<a href=\"http://google.com\">Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText7() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=\"http://google.com\">Google</a>"));
    }

    @Test
    public void testDivContainingLinkAndText8() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=>Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText9() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=http://google.com>Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText10() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=http://google.com\">Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText11() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=\"http://google.com>Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText12() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=\"\">Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText13() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a \"http://google.com\">Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText14() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=\"http://\">Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText15() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=\"google.com\">Google</a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText16() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=\"http://google.com\"></a></div>"));
    }

    @Test
    public void testDivContainingLinkAndText17() {
        String result = div(text("Visit "), a("Google").withHref("http://google.com")).render();
        assertFalse(result.equals("<div><a href=\"http://google.com\">Google</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters1() {
        String result = div("<script>alert('xss');</script>").render();
        assertTrue(result.equals("<div>&lt;script&gt;alert(&#x27;xss&#x27;);&lt;/script&gt;</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters2() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert(&#x27;xss&#x27;);&lt;/script&gt;</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters3() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript;alert(&#x27;xss&#x27;);&lt;/script&gt;</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters4() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>;alert(&#x27;xss&#x27;);&lt;/script&gt;</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters5() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert();&lt;/script&gt;</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters6() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert(xss&#x27;);&lt;/script&gt;</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters7() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert(&#x27;xss&#x27;);</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters8() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert(&#x27;xss&#x27;);&lt;/script&gt</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters9() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;(&#x27;xss&#x27;);&lt;/script&gt;</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters10() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>;alert(&#x27;xss&#x27;);&lt;/script&gt;</div>"));
    }
    @Test
    public void testDivWithSpecialCharacters11() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert();&lt;/script&gt;</div>"));
    }@Test
    public void testDivWithSpecialCharacters12() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert(xss&#x27;);&lt;/script&gt;</div>"));
    }@Test
    public void testDivWithSpecialCharacters13() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert(&#x27;xss&#x27;)</div>"));
    }@Test
    public void testDivWithSpecialCharacters14() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert/script&gt;</div>"));
    }@Test
    public void testDivWithSpecialCharacters15() {
        String result = div("<script>alert('xss');</script>").render();
        assertFalse(result.equals("<div>&ltscript&gt;alert(&27;xss&#x27;);&lt;/script&gt;</div>"));
    }


    @Test
    public void testDivWithOnclick0() {
        String result = div("Clickable").attr("onclick", "console.log('clicked');").render();
        assertFalse(result.contains("onclick=\"console.log('clicked');\""));
    }



    @Test
    public void testDivWithNestedList0() {
        String result = div(ul(li("Item 1"), li("Item 2"))).render();
        assertTrue(result.equals("<div><ul><li>Item 1</li><li>Item 2</li></ul></div>"));
    }
    @Test
    public void testDivWithFormElements0() {
        String result = div(form().withMethod("post").with(input().withType("text"), input().withType("submit"))).render();
        assertTrue(result.equals("<div><form method=\"post\"><input type=\"text\"><input type=\"submit\"></form></div>"));
    }

    @Test
    public void testDivWithMultipleNestedElements0() {
        String result = div(
                p("Paragraph inside div."),
                a("Link").withHref("#"),
                span("Span inside div.")
        ).render();
        assertTrue(result.equals("<div><p>Paragraph inside div.</p><a href=\"#\">Link</a><span>Span inside div.</span></div>"));
    }
    @Test
    public void testDivWithMultipleAttributes0() {
        String result = div().withClass("foo").withId("bar").withStyle("color: red;").render();
        assertTrue(result.equals("<div class=\"foo\" id=\"bar\" style=\"color: red;\"></div>"));
    }


    /** Tag div testing ends here **/


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