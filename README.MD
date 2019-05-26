# Simple search engine

The search engine is able to:
* take in a list of documents
* support searches for single terms in the document set
* return a list of matching documents sorted by TF-IDF 

## Getting Started
### Prerequisites

* Necessary dependencies:
```
    <dependency>
        <groupId>com.wojciechwaldon.cqrs</groupId>
        <artifactId>api</artifactId>
    </dependency>
```

* Necessary beans located in **cqrs-infrastructure** module configuration, example:

```
    @Configuration
    @Import(CqrsConfiguration.class)
    public class TargetConfiguration {
      ...
    }
```

```
    <dependency>
        <groupId>com.wojciechwaldon.cqrs</groupId>
        <artifactId>infrastructure</artifactId>
    </dependency>
```

## Structure

* **api module** - core functionality providing module
* **application module** - module responsible for api's logical part
* **infrastructure module** - module carrying for implementation of the infrastructure abstraction

Partition created with intention to deliver abstract components available for later modification. (DDD, Dependency Inversion)

## Testing

**Infrastructure** module contains set of functionality tests available to run manually.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

**Wojciech Waldon** - check me on [github](https://github.com/wojciechwaldon/).
