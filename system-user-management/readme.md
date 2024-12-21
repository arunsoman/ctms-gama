# User Management API

This project provides RESTful APIs for managing users, roles, and permissions, along with their relationships in a system. The APIs are implemented using Spring Boot and expose endpoints for CRUD operations and mappings.

## Features

- User Management: Create, update, delete, and search users.
- Role Management: Create, update, delete, and fetch roles.
- Permission Management: Create, update, delete, and fetch permissions.
- Role-User Mapping: Assign and remove roles for users.
- Role-Permission Mapping: Assign and remove permissions for roles.

## API Endpoints

### User Management

| HTTP Method | Endpoint          | Description                      |
|-------------|-------------------|----------------------------------|
| POST        | `/users`          | Create a new user                |
| PUT         | `/users/{id}`     | Update an existing user          |
| DELETE      | `/users/{id}`     | Delete a user                    |
| GET         | `/users/search`   | Search for users by a query      |

### Role Management

| HTTP Method | Endpoint          | Description                      |
|-------------|-------------------|----------------------------------|
| POST        | `/roles`          | Create a new role                |
| PUT         | `/roles/{id}`     | Update an existing role          |
| GET         | `/roles`          | Fetch all roles                  |
| GET         | `/roles/{id}`     | Fetch a role by ID               |
| DELETE      | `/roles/{id}`     | Delete a role                    |

### Permission Management

| HTTP Method | Endpoint                  | Description                      |
|-------------|---------------------------|----------------------------------|
| POST        | `/permissions`            | Create a new permission          |
| PUT         | `/permissions/{id}`       | Update an existing permission    |
| GET         | `/permissions/{id}`       | Fetch a permission by ID         |
| DELETE      | `/permissions/{id}`       | Delete a permission              |

### Role-User Mapping

| HTTP Method | Endpoint                          | Description                      |
|-------------|-----------------------------------|----------------------------------|
| POST        | `/roles/{roleId}/mapto/{userId}`  | Map a role to a user             |
| DELETE      | `/roles/{roleId}/removeto/{userId}` | Remove a role from a user       |

### Role-Permission Mapping

| HTTP Method | Endpoint                                     | Description                      |
|-------------|----------------------------------------------|----------------------------------|
| POST        | `/roles/{roleId}/mappermission/{permissionId}` | Map a permission to a role      |
| DELETE      | `/roles/{roleId}/removepermission/{permissionId}` | Remove a permission from a role |

## Technologies Used

- Java
- Spring Boot
- REST API

## How to Use

1. Clone the repository.
2. Build the project using Maven or Gradle.
3. Start the application.
4. Use a tool like Postman or cURL to interact with the APIs.

## Example Requests

### Create a User
```http
POST /users
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
