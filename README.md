# Book service
- --
## How to start
- Clone this project to your IDEA
- Enter your data to connect to the database in a file:
`src/main/resources/application.properties`
```properties
datasource.url=YOUR_DB_URL
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
```
- After start, some information will be added to the database
- - To see what will be added, check the class: `src/main/java/book/manager/util/DataInject.java`
- --
## Endpoints
- GET: 
- - `http://localhost:8080/authors/find-most-successful`
- - `http://localhost:8080/books?authorName={Author name}`
- - `http://localhost:8080/books/most-selling?authorName={Author name}`
- - `http://localhost:8080/books/most-published?authorName={Author name}`
- - `http://localhost:8080/books/most-selling-by-author-part-name?authorPartName={Part of the author name}`
- - `http://localhost:8080/books/most-published-by-author-part-name?authorPartName={Part of the author name}`
- - `http://localhost:8080/books/most-successful-by-author-part-name?authorPartName={Part of the author name}`
- POST:
- - `http://localhost:8080/authors` + AuthorRequestDto
- - `http://localhost:8080/books` + BookRequestDto
- PUT:
- - `http://localhost:8080/authors/{authorId}` + AuthorRequestDto
- - `http://localhost:8080/books/{bookId}` + BookRequestDto
- DELETE:
- - `http://localhost:8080/authors/{authorId}`
- - `http://localhost:8080/books/{bookId}`
```json
AuthorRequestDto JSON example:
{
    "authorName": "Bob",
    "birthDate": "2000-11-30",
    "phone": "30886223",
    "email": "example@exmail.com"
}
        
BookRequestDto JSON example:
{
    "bookName": "New book name",
    "authorId": 1,
    "publishedAmount": 15000,
    "soldAmount": 12000
}

```
- --
## Used technologies
- Spring Boot
- PostgresSQL
- Maven
- Hibernate
