# StreamRank-BE

StreamRank-BE is a backend service designed for managing movie recommendations, user preferences, and streaming rankings. The project is built using Spring Boot and follows a clean architecture with distinct domain, service, and API layers.

## Features

- **Movie Suggestions**: Retrieve personalized movie suggestions for users.
- **Movie Details**: Fetch detailed information about specific movies.
- **User API**: Manage user data and movie preferences.
- **Favorite Movies**: Allow users to mark movies as favorites and manage their lists.
- **Authentication**: Secure endpoints with authentication features.
- **CRUD Operations**: Comprehensive CRUD functionality for various domain entities.

## Project Architecture

The project is structured into multiple layers:
1. **Domain Layer**: Core business logic and entities.
2. **DTO Layer**: Data transfer objects for API communication.
3. **Service Layer**: Handles business operations and connects repositories with APIs.
4. **Repository Layer**: Interfaces with the database for CRUD operations.
5. **API Layer**: Exposes RESTful endpoints for client interaction.

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Oracle Database

### Setup Instructions

1. Clone the repository:
   ```git clone https://github.com/yassineB999/StreamRank-BE.git```

2. Navigate to the project directory:
   ```cd StreamRank-BE```

3. Configure the database in the `application.properties` file:
   ```spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE```
   ```spring.datasource.username=<your-username>'
   ```spring.datasource.password=<your-password>'
   ```spring.datasource.driver-class-name=oracle.jdbc.OracleDriver```
   
4. Build the project:
   ```mvn clean install```

5. Run the application:
   ```mvn spring-boot:run```

### Testing

To run the tests:
```mvn test```

## API Endpoints

### Movie Endpoints
- GET `/movies/suggestions`: Get movie recommendations.
- GET `/movies/{id}`: Fetch details for a specific movie.

### User Endpoints
- POST `/users`: Create a new user.
- GET `/users/{id}/favorites`: Get a user```s favorite movies.
- POST `/users/{id}/favorites`: Add a movie to a user```s favorites.

## Contributing

Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new feature branch:
   ```git checkout -b feature/your-feature-name```

3. Commit your changes:
   ```git commit -m "feat: Add your feature"'

4. Push the branch:
   ```git push origin feature/your-feature-name```

5. Open a pull request.

## Authors

- **Mohamed Hakkou**
- **Yassine Belouchi**
