# BookVenture REST API

This is a Java Spring Boot project for a REST API called BookVenture. It provides endpoints for managing authors, books, and reviews.

## Controllers

### AuthorController

- Manages CRUD operations for authors.

#### Example Usage

**Get All Authors:**

```http
GET /api/author
```

**Get Author by ID:**

```http
GET /api/author/{id}
```

**Create Author:**

```http
POST /api/author/create
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "nationality": "American",
  "birthDate": "1990-01-01"
}
```

**Update Author:**

```http
PUT /api/author/{id}
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Doe",
  "nationality": "British",
  "birthDate": "1992-02-02"
}
```

**Delete Author:**

```http
DELETE /api/author/{id}
```

### BookController

- Manages CRUD operations for books.

#### Example Usage

**Get All Books:**

```http
GET /api/book?page=0&pageSize=10
```

**Get Book by ID:**

```http
GET /api/book/{authorId}/{id}
```

**Get Books by Author ID:**

```http
GET /api/book/{authorId}
```

**Create Book:**

```http
POST /api/book/create/{authorId}
Content-Type: application/json

{
  "title": "Book Title",
  "type": "Fiction"
}
```

**Update Book:**

```http
PUT /api/book/{id}/update/{authorId}
Content-Type: application/json

{
  "title": "Updated Book Title",
  "type": "Non-fiction"
}
```

**Delete Book:**

```http
DELETE /api/book/{id}/delete/{authorId}
```

### ReviewController

- Manages CRUD operations for reviews.

#### Example Usage

**Create Review:**

```http
POST /api/book/{bookId}/review
Content-Type: application/json

{
  "title": "Great Book!",
  "content": "This book is amazing!",
  "stars": 5
}
```

**Get Reviews by Book ID:**

```http
GET /api/book/{bookId}/reviews
```

**Get Review by ID:**

```http
GET /api/book/{bookId}/reviews/{id}
```

**Update Review:**

```http
PUT /api/book/{bookId}/reviews/{id}
Content-Type: application/json

{
  "title": "Updated Review Title",
  "content": "Updated review content.",
  "stars": 4
}
```

**Delete Review:**

```http
DELETE /api/book/{bookId}/reviews/{id}
```

## Dependencies

* Spring Boot Starter Data JPA
* Spring Boot Starter Web
* PostgreSQL Driver
* Lombok (optional)
* Spring Boot Starter Test (for testing)

## How to Use

**Clone the repository:**

```bash
git clone <repository-url>
```

**Navigate to the project directory:**

```bash
cd <project-directory>
```

**Build the project:**

```bash
mvn clean install
```

**Run the project:**

```bash
mvn spring-boot:run
```

**Access the API endpoints using a tool like Postman or cURL.**
