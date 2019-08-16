# svg-spinner
A parameterized SVG spinner in JSPX

If you have a servlet environment, than you can simply generate an SVG spinner using JSP. E.g. like this.

It can be parameterized and there is no need to generate these kind of things beforehand.

This version has the following parameters:
```jsp
  <c:set var="size" value="${empty param.size ? 64 : param.size}" />
  <c:set var="color" value="${empty param.color ? '#ed6d1c' : param.color}" />
  <c:set var="circles" value="${empty param.circles ? 7 : param.circles}" />
  <c:set var="steps" value="${empty param.steps ? 50 : param.steps}" />
  <c:set var="dur" value="${empty param.dur ? '1500ms' : param.dur}" />
```
And the default then renders like so:
<img src="./spinner.svg">
