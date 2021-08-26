# protobuf-object-mapper

![Java CI with Maven](https://github.com/robert2411/protobuf-object-mapper/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.robert2411/protobuf-swagger-mapper/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.robert2411/protobuf-swagger-mapper)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Frobert2411%2Fprotobuf-object-mapper.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Frobert2411%2Fprotobuf-object-mapper?ref=badge_shield)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=robert2411_protobuf-object-mapper&metric=alert_status)](https://sonarcloud.io/dashboard?id=robert2411_protobuf-object-mapper)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=robert2411_protobuf-object-mapper&metric=coverage)](https://sonarcloud.io/dashboard?id=robert2411_protobuf-object-mapper)

The goal of this project is to make it easier to map protobuf objects from/to (swagger) objects

## Usage
Add the dependency to your project
```xml
<dependency>
    <groupId>com.github.robert2411</groupId>
    <artifactId>protobuf-swagger-mapper</artifactId>
</dependency>
```
### Basic example

```java
ProtobufObjectMapper mapper = new ProtobufObjectMapper();
//to proto
ProtobufTest.Greeting greeting = mapper.map(testObject, ProtobufTest.Greeting::newBuilder);
//To object
TestObject out = mapper.map(greeting, TestObject.class);

```
## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Frobert2411%2Fprotobuf-object-mapper.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Frobert2411%2Fprotobuf-object-mapper?ref=badge_large)