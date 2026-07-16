package org.meeuw.svg.springboot;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class SvgObjectsController {

    private final ITemplateEngine templateEngine;

    SvgObjectsController(ITemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping("/meeuw/circle")
    public void circle(
        @RequestParam(name = "size", defaultValue = "16") int size,
        @RequestParam(name = "color", defaultValue = "#000") String color,
        HttpServletResponse response) throws IOException {
        Context context = new Context();
        context.setVariable("size", size);
        context.setVariable("color", color);
        render("circle", context, response);
    }

    @GetMapping("/meeuw/polygon")
    public void polygon(
        @RequestParam(name = "size", defaultValue = "16") int size,
        @RequestParam(name = "color", defaultValue = "#000") String color,
        @RequestParam(name = "sides", defaultValue = "3") int sides,
        HttpServletResponse response) throws IOException {
        Context context = new Context();
        context.setVariable("size", size);
        context.setVariable("color", color);
        context.setVariable("points", polygonPoints(size, sides));
        render("polygon", context, response);
    }

    @GetMapping("/meeuw/spinner")
    public void spinner(
        @RequestParam(name = "size", defaultValue = "64") int size,
        @RequestParam(name = "color", defaultValue = "#000") String color,
        @RequestParam(name = "circles", defaultValue = "7") int circles,
        @RequestParam(name = "steps", defaultValue = "50") int steps,
        @RequestParam(name = "dur", defaultValue = "1500ms") String dur,
        HttpServletResponse response) throws IOException {
        Context context = new Context();
        context.setVariable("size", size);
        context.setVariable("color", color);
        context.setVariable("circles", circles);
        context.setVariable("dur", dur);
        context.setVariable("transforms", spinnerTransforms(size, steps));
        render("spinner", context, response);
    }

    private void render(String template, Context context, HttpServletResponse response) throws IOException {
        response.setContentType("image/svg+xml;charset=UTF-8");
        response.setHeader("Cache-Control", "max-age=3600");
        templateEngine.process(template, context, response.getWriter());
    }

    private static String polygonPoints(int size, int sides) {
        StringBuilder points = new StringBuilder();
        for (int i = 0; i < sides; i++) {
            double phi = Math.PI * 2 * (i + 0.5) / sides;
            points.append(size * (1 + Math.sin(phi)) / 4).append(",");
            points.append(size * (1 + Math.cos(phi)) / 4).append(" ");
        }
        return points.toString();
    }

    private static String spinnerTransforms(int size, int steps) {
        StringBuilder transforms = new StringBuilder();
        for (int i = 0; i < steps; i++) {
            if (i > 0) {
                transforms.append(";");
            }
            transforms.append(i * 360 / steps).append(" ").append(size).append(" ").append(size);
        }
        return transforms.toString();
    }
}
