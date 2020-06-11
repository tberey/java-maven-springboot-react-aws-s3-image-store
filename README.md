# AWS S3 Java Api & React Client

## A Java Spring application, using Spring-Boot to create a REST Web API, using Maven bootstrap. Perform HTTP Requests, over a TCP connection via a React front-end client, to perform operations against a AWS S3 Client and accompanying database, via the Java api.

### Create a React client server for HTTP Requests to a Java API web server, in order to upload and download image files from a AWS S3 Bucket, corresponding specific user profiles. Update a in-memory database to collect all the files and profiles. 

<br>

***

###### Client Page (Front-End) Homepage (usually Port 3000): <br>
#### <b>http://localhost:<Port\></b>

***

<br><i>

#### List of URLs (http://localhost:<Port\>/api/v1/ + End-points), for Requests against the Java backend API server, that are currently available:
| Endpoint | Method/Action on DB | Full URI (Using some port, e.g. "8080") |
|:---|:---|:---|
| <ul><li>"/api/v1/user-profile/<UUID\>/image/upload"</li></ul> | <b><u>POST/Create</u></b> | <ul><li>"http://localhost:8080/api/v1/user-profile/<UUID\>/image/upload"</li></ul> |
| <ul><li>"/api/v1/user-profile/<UUID\>/image/download"</li><li>"/api/v1/user-profile/"</li></ul> | <b><u>GET/Read</u></b> | <ul><li>"http://localhost:8080/api/v1/user-profile/<UUID\>/image/download"</li><li>"http://localhost:8080/api/v1/user-profile/"</li></ul> |

***

***

<br>

|Version| Changes|
|:---|:---|
|Version 1.0.0 [2020-05-11]|<ul><li>Initial Commit.</li><li>Implement full stack/fully functional application.</li><li>Setup backend Data Access Layer, using in-memory DB, with a Data Access Service.</li><li>Setup complete backend Service Layer.</li><li>Setup API/Controller Layer, for interaction between frontend, backend and the data-store/service.</li><li>Setup client-side/frontend, to request against backend data-store and api.</li><li>Create README.md</li></ul>|