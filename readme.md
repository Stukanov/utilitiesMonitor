***Gas & Water Usage Monitoring Application***

Simple Spring-Boot app for monitoring utilities measurement.
App has two REST end-points. 

**First, to save new measurements for particular user:**

```
POST /measurements

{
        "userId": 1,
	"gas": 45.4,
	"coldWater": 45.2,
	"hotWater":557
}
```
Fields restrictions:
- not null
- gas, cold and hot water values should be in 0...1000 range

If one or several restrictions are broken, you'll get an error message with description - in what field and what mistake you've done.

**Second end-point allows you to get particual user's measurement history:**

```GET /users/{userId}/measurements```

To get some history, you should save measurements for any ```userId``` (by POST request) and use the same ```userId``` in GET request. Results will be in descending order by measurement save date and time.

***How to start***

You need to: 
- download or checkout the source code
- go to source code root directory in your terminal
- run command ```mvn spring-boot:run``` (or, if you want to run tests, use: ```mvn verify spring-boot:run```)

After that you can hit one of the end-points with your requests. The app doesn't persist data across 
application launches.
