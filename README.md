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


User should be able to get statistics for a metric
--------------------------------------------------


How to run this app?
-------------------

