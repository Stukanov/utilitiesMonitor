#Gas & Water Usage Monitoring Application

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

If one or several restrictions are violated, you'll get an error message with description - in what field and what mistake you've done.

**Second end-point allows you to get particular user's measurement history:**

```GET /users/{userId}/measurements```

To get some history, you should save measurements for any ```userId``` (by POST request) and use the same ```userId``` in GET request. Results will be in descending order by measurement save date and time.

***How to start***

You need to: 
- download or checkout the source code
- go to source code root directory in your terminal
- run command ```mvn spring-boot:run``` (or, if you want to run tests, use: ```mvn verify spring-boot:run```)

After that you can hit one of the end-points with your requests. The app doesn't persist data across 
application launches.

#Performance test

The performance testing profile was composed in JMeter. For convenience of the reviewer, it was designed to run as part of the existing maven project.

The load profile consists of 2 thread groups:
- Writer - for requesting `POST /measurements`
- Reader - for requesting `GET /users/{userId}/measurements`

Both thread groups implement stair-like ramp-up scenario. Since no details were specified in the test task, the load profile was defined as:
- Peak load - 80 RPS total
- 3 steps of load increase by 5 minutes (30 RPS,50 RPS,80 RPS)
- Total duration of 15 minutes

##How to run

- download or checkout the source code
- go to source code root directory in your terminal
- run command `mvn clean integration-tests` (or, if you want to open the Jmeter GUI and inspect the profile, use: `mvn configure:jmeter-gui`)

After GUI started, to view/edit test profile, you need to open the test profile file [test profile file](src/test/jmeter/Test.jmx) right in the JMeter GUI

##Viewing the results
In the end of the test JMeter will generate an interactive dashboard with the results of the test.
By default, it is located at `target/jmeter/reports/Test/index.html`