# SpringBoot OpenSearch Full Text Search Example with Spring Boot 3 and OpenSearch 1.2

## Introduction

This example demonstrates how to use Spring Data OpenSearch to do simple CRUD operations.

This example inspired be
the [SpringBoot OpenSearch Full Text Search Example](https://github.com/viges2201/SpringBoot-OpenSearch-FullTextSearch)


For this example, we created db H2 with Flyway and create Index for films and search by Index Opensearch:

- Reindex films
- Search the list of films by query

For this example, we create Index Book controller that allows doing the following operations with OpenSearch:

- Get the list of all books
- Create a book
- Update a book by Id
- Delete a book by Id
- Search for a book by ISBN
- Fuzzy search for books by author and title

## How to run

The first thing to do is to start OpenSearch. For that, you can use the `docker-compose` file in the develop-scripts
directory and run it like this:

```bash
$ cd develop-scripts && docker-compose -f docker-compose up -d
``` 

It brings OpenSearch up with its dashboard.

Then you can run the application like below:

```bash
$ ./mvnw spring-boot:run
```

Once everything is up and running open the browser and go to [http://localhost:8080](http://localhost:8080). You should
see Swagger to interact with.

## Run Testcontainers tests

The integration tests are written relying on [Testcontainers](https://www.testcontainers.org/) to spin up OpenSearch on
the spot and run tests against it.

To run the integration test (using Testcontainers) just run the below command:

```bash
$ mvn clean verify
```

Make sure you have your docker running.