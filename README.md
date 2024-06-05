# BookVenture REST API

BookVenture REST API: Fuel Your Literary Adventure
BookVenture, a Java Spring Boot-powered REST API built as a Maven project, ignites your passion for books. Manage your collection, delve into author information, and explore the world of reviews - all through a user-friendly RESTful interface. Embark on your literary adventure today!

## Controllers


### AuthController

- The AuthController handles user authentication and token generation for the BookVenture REST API. It uses JWT (JSON Web Tokens) for securing the endpoints.
  
#### Example Usage

**Login:**

This endpoint authenticates a user and returns a JWT token.

```http
POST /api/auth/login
Content-Type: application/json

{
 "username": "user@example.com",
 "password": "password123"
}
```

**Expected Response:**

* Upon successful login, the response will contain a JSON object with a JWT token and potentially other user information (depending on the implementation).
* In case of failed login, an error response with appropriate status code and message will be provided.

**Register:**

This endpoint creates a new user.

```http
POST /api/auth/register
Content-Type: application/json

{
 "username": "user@example.com",
 "password": "password123"
}
```

**Expected Response:**

* Upon successful registration, the response might be a simple success message or a JSON object containing details of the created user.
* On registration failure, an error response with appropriate status code and message will be provided.


### AuthorController

- Manages CRUD operations for authors.

#### Example Usage

**Get All Authors:**

```http
GET /api/author
```

**Expected Response:**

* This endpoint retrieves a list of all authors. The response will likely be a JSON array containing `AuthorDto` objects, each representing an author.

**Get Author by ID:**

```http
GET /api/author/{id}
```

**Expected Response:**

* This endpoint retrieves a single author by their ID. The response will likely be a single `AuthorDto` object containing details of the requested author.

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

**Expected Response:**

* This endpoint creates a new author. The response might be a JSON object with details of the created author or a simple success message.

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

**Expected Response:**

* This endpoint updates an existing author. The response will likely be a success message or an updated `AuthorDto` object representing the modified author.

**Delete Author:**

```http
DELETE /api/author/{id}
```

**Expected Response:**

* This endpoint deletes an author. The response will likely be a simple success message or a status code indicating successful deletion.


### BookController

- Manages CRUD operations for books.

#### Example Usage

**Get All Books:**

```http
GET /api/book?page=0&pageSize=10
```

**Expected Response:**

* This endpoint retrieves a paginated list of books. The response will likely be a `BookResponse` object containing a list of `BookDto` objects representing the requested page of books, along with pagination information like total elements, total pages, etc.

**Get Book by ID:**

```http
GET /api/book/{authorId}/{id}
```

**Expected Response:**

* This endpoint retrieves a single book by its ID and author ID. The response will likely be a single `BookDto` object containing details of the requested book.

**Get Books by Author ID:**

```http
GET /api/book/{authorId}
```

**Expected Response:**

* This endpoint retrieves all books written by a specific author. The response will likely be a list of `BookDto` objects, similar to Get All Books but filtered by author.

Absolutely, continuing the breakdown of BookVenture REST API documentation:

**Create Book (continued):**

* Content-Type: application/json

```json
{
  "title": "Book Title",
  "type": "Fiction"
}
```

**Expected Response:**

* This endpoint creates a new book. The response might be a `BookDto` object representing the newly created book or a simple success message with a created status code.

**Update Book:**

```http
PUT /api/book/{id}/update/{authorId}
Content-Type: application/json

{
  "title": "Updated Book Title",
  "type": "Non-fiction"
}
```

**Expected Response:**

* This endpoint updates an existing book. The response will likely be a success message or an updated `BookDto` object representing the modified book.

**Delete Book:**

```http
DELETE /api/book/{id}/delete/{authorId}
```

**Expected Response:**

* This endpoint deletes a book. The response will likely be a simple success message or a status code indicating successful deletion.


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

**Expected Response:**

* This endpoint creates a new review for a specific book. The response will likely be a `ReviewDto` object representing the newly created review.

**Get Reviews by Book ID:**

```http
GET /api/book/{bookId}/reviews
```

**Expected Response:**

* This endpoint retrieves all reviews for a particular book. The response will likely be a list of `ReviewDto` objects.

**Get Review by ID:**

```http
GET /api/book/{bookId}/reviews/{id}
```

**Expected Response:**

* This endpoint retrieves a single review by its ID and book ID. The response will likely be a single `ReviewDto` object containing details of the requested review.

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

**Expected Response:**

* This endpoint updates an existing review. The response will likely be a success message or an updated `ReviewDto` object representing the modified review.

**Delete Review:**

```http
DELETE /api/book/{bookId}/reviews/{id}
```

**Expected Response:**

* This endpoint deletes a review. The response will likely be a simple success message or a status code indicating successful deletion.

## Dependencies

* Spring Boot Starter Data JPA
* Spring Boot Starter Web
* PostgreSQL Driver
* Lombok

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

This comprehensive breakdown, including expected responses for each endpoint, should provide a clear understanding of the BookVenture REST API functionality documented in the readme.md file.
