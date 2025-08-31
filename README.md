# List API

This RESTful API should return a list of countries that contain the specified substring, sorted by most occurences of the input to least.

### Data

Using [this](https://restcountries.com/#about-this-project-important-information) third-party API to get countries

### Implementation

- Using a thread pool to process each country and a ConcurrentHashMap to handle data access in a thread-safe manner.
- Implemented unit tests with JUnit.

### Future State

- Caching for the third-party API requests
- Add a openapi.yml spec file
