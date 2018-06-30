Implementation of a Web API for Metrics
---------------------------------------

The requirement is to implement a web application which allows users to create multiple metrics and post values to that metric
(e.g. stock price of a security). Users should be able to also request summary statistics for that metric.
Users will communicate with the web application via a Web API.


There are three main uses cases for this api:

User should be able to create metrics
-------------------------------------
To create a metric you can go to /metrics endpoint and send a POST request with the following payload:
```json
{"metricName": "name of the metric"}
```
You should get a 200 response with the newly created resource.

The creation of the metric has a O(1) complexity

User should be able to add values to a metric
---------------------------------------------

To add values to a metric you can go to /metrics/{id}/values and send a POST request with the following payload:
```json
{"value":4546.55"
```
You should get a 202 response.

Adding a metric has a O(log n) complexity. The reason for this is that during insertion we find the insertion point using a binary search. This helps us keep the collection of values ordered.

User should be able to get statistics for a metric
--------------------------------------------------

To get the statistics you can go to /metrics/{id}/statistics and send a GET request.

You should get a 200 response with a list of statistics.

The statistics run on a O(1) complexity thanks to the underlying structure used to store the values.

All of the endpoints exposed provide links. We take advantage of [HATEOAS](https://en.wikipedia.org/wiki/HATEOAS) in our API, specifically [HAL](https://en.wikipedia.org/wiki/Hypertext_Application_Language).

How to run this app?
-------------------
This app is a [Spring Boot](https://spring.io/projects/spring-boot) app and it is built using [Apache Maven](https://maven.apache.org/). To run just issue the following at the command propmt:
```bash
$ mvn spring-boot:run
```
You can access it on http://localhost:8080
