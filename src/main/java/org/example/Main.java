package org.example;
import static j2html.TagCreator.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Main {
    public static String generateHtml() {
        return html(
                head(
                        title("J2HTML Example")
                ),
                body(
                        h1("Hello, J2HTML!"),
                        p("This is an example of using J2HTML in a Maven project.")
                )
        ).render();
    }

    public static void main(String[] args) {

        String html = generateHtml();
        System.out.println(html);

    }


}