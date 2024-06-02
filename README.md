# BookVenture REST API

This is a Java Spring Boot project for a REST API called BookVenture. It provides endpoints for managing authors, books, and reviews.

## Controllers

### AuthorController
- Manages CRUD operations for authors.

### BookController
- Manages CRUD operations for books.

### ReviewController
- Manages CRUD operations for reviews.

## Models

### Author
- Represents an author with properties: id, firstName, lastName, nationality, and birthDate.

### Book
- Represents a book with properties: id, title, type, and an association to an author.

### Review
- Represents a review with properties: id, title, content, stars, and an association to a book.

## DTOs (Data Transfer Objects)

### AuthorDto
- DTO for transferring Author data between layers.

### BookDto
- DTO for transferring Book data between layers.

### BookResponse
- DTO for representing paginated lists of books.

### ReviewDto
- DTO for transferring Review data between layers.

## Services

### AuthorService
- Provides service methods for managing authors.

### BookService
- Provides service methods for managing books.

### ReviewService
- Provides service methods for managing reviews.

## Repositories

### AuthorRepository
- Repository interface for managing Author entities.

### BookRepository
- Repository interface for managing Book entities.

### ReviewRepository
- Repository interface for managing Review entities.

## Exceptions

### AuthorNotFoundException
- Thrown when an author is not found.

### BookNotFoundException
- Thrown when a book is not found.

### ReviewNotFoundException
- Thrown when a review is not found.

### GlobalExceptionHandler
- Global exception handler for handling custom exceptions.

## Dependencies

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- PostgreSQL Driver
- Lombok (optional)
- Spring Boot Starter Test (for testing)

## How to Use

1. Clone the repository:
   ```bash
   git clone <repository-url>
