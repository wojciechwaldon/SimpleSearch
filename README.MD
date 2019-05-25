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
        <groupId>com.wojciechwaldon</groupId>
        <artifactId>cqrs</artifactId>
    </dependency>
```

* Necessary beans located in **cqrs-infrastructure** configuration:

```
    @Configuration
    @Import(CqrsConfiguration.class)
    public class TargetConfiguration {
      ...
    }
```

## Testing

**infrastructure** module contains set of functionality tests

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

**Wojciech Waldon** - check me on [github](https://github.com/wojciechwaldon/).
