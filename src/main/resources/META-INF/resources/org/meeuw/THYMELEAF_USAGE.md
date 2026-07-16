# Thymeleaf SVG Templates

This directory contains Thymeleaf versions of the SVG template files for easier reuse in Spring Boot applications.

## Files

- `circle.svg.html` - Generates a simple circle SVG
- `polygon.svg.html` - Generates a polygon SVG with configurable sides
- `spinner.svg.html` - Generates an animated spinner SVG

## Usage in Spring Boot

### Circle SVG

```java
@GetMapping("/circle.svg")
public String circle(Model model, 
                     @RequestParam(defaultValue = "16") int size,
                     @RequestParam(defaultValue = "#000") String color) {
    model.addAttribute("size", size);
    model.addAttribute("color", color);
    return "circle.svg";
}
```

### Polygon SVG

```java
@GetMapping("/polygon.svg")
public String polygon(Model model,
                      @RequestParam(defaultValue = "16") int size,
                      @RequestParam(defaultValue = "#000") String color,
                      @RequestParam(defaultValue = "3") int sides) {
    model.addAttribute("size", size);
    model.addAttribute("color", color);
    model.addAttribute("sides", sides);
    
    // Calculate polygon points
    StringBuilder points = new StringBuilder();
    for (int i = 0; i < sides; i++) {
        double phi = Math.PI * 2 * (i + 0.5) / sides;
        points.append(size * (1 + Math.sin(phi)) / 4).append(",");
        points.append(size * (1 + Math.cos(phi)) / 4).append(" ");
    }
    model.addAttribute("points", points.toString());
    
    return "polygon.svg";
}
```

### Spinner SVG

```java
@GetMapping("/spinner.svg")
public String spinner(Model model,
                      @RequestParam(defaultValue = "64") int size,
                      @RequestParam(defaultValue = "#000") String color,
                      @RequestParam(defaultValue = "7") int circles,
                      @RequestParam(defaultValue = "50") int steps,
                      @RequestParam(defaultValue = "1500ms") String dur) {
    model.addAttribute("size", size);
    model.addAttribute("color", color);
    model.addAttribute("circles", circles);
    model.addAttribute("dur", dur);
    
    // Calculate animation transforms
    StringBuilder transforms = new StringBuilder();
    for (int i = 0; i < steps; i++) {
        if (i > 0) transforms.append(";");
        transforms.append(i * 360 / steps).append(" ").append(size).append(" ").append(size);
    }
    model.addAttribute("transforms", transforms.toString());
    
    return "spinner.svg";
}
```

## Configuration

Make sure your Spring Boot application is configured to serve SVG content types:

```yaml
spring:
  mvc:
    contentnegotiation:
      media-types:
        svg: image/svg+xml
```

And configure Thymeleaf to look for these templates in the appropriate directory.

