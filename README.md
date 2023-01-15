# Getting Started

### Lightweight Container with Cloud Native Buildpacks

If you're already familiar with Spring Boot container images support, this is the easiest way to get started. Docker
should be installed and configured on your machine prior to creating the image.

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image -Pnative
```

Then, you can run the app like any other container:

```
$ docker run --rm subscription:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools

Use this option if you want to explore more options such as running your tests in a native image. The
GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```
$ ./mvnw native:compile -Pnative
```

Then, you can run the app as follows:

```
$ target/subscription
```

You can also run your existing tests suite in a native image. This is an efficient way to validate the compatibility of
your application.

To run your existing tests in a native image, run the following goal:

```
$ ./mvnw test -PnativeTest
```


### Setup Guide

1. Start the application by default it runs on the `9090` port.
2. The application is configured to use Sqlite as DB.
3. Rest Server is currently protected using JWT Authentication. Please pass the token mentioned below as Bearer token.
4. Here is the sample curl request ``curl --location --request GET 'http://127.0.0.1:9090/api/subscription/sub_L4Ro8jv6SguG7y' \
   --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJseWJsX3VzZXIiLCJpYXQiOjE2NzM4MDQwNzEsImV4cCI6MTY3NDE2NDA3MX0.xCw420jjBZSJr-BvcYH8JwlLxkcn9BzPtppulVsiHW8h3FHxZjVUxkmNRFLKbNy29c0enNUST-k34qvblfOg9g'
   ``
#### Bearer Token
```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJseWJsX3VzZXIiLCJpYXQiOjE2NzM4MDQwNzEsImV4cCI6MTY3NDE2NDA3MX0.xCw420jjBZSJr-BvcYH8JwlLxkcn9BzPtppulVsiHW8h3FHxZjVUxkmNRFLKbNy29c0enNUST-k34qvblfOg9g
```

### Architecture and Assumptions
1. It is assumed right now subscriptions and plans are managed by Razorpay but this can be modified to create custom plans and subscriptions in DB and map it at razorpay end.
2. Models and queries are designed as per Sqlite.
3. Right now all the endpoints are behind Auth layer an exceptions and roles can be added to allow few API's accessible publicly.
4. Events processing ideally can be done by pushing events and corresponding payloads to Kafka and let them process async manner to scale this application.