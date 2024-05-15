# Sample Springboot Backend API

This is a sample Springboot backend API that provides a RESTful API for users to manage their pokedex.

## Requirements
- Java 22
- Maven 3.8.4

## Installation
1. Clone the repository
2. Run the following command to build the project
```bash
mvn clean install
```
3. Run the following command to start the application
```bash
mvn spring-boot:run
```

## Deployment to Cloud (using Google Cloud Services)
1. Create a new project in Google Cloud Console
2. Install Google Cloud SDK on your local machine
3. Authenticate your account using the following command
```bash
gcloud auth login
```
4. Set the project ID using the following command
```bash
gcloud config set project PROJECT_ID
```
5. Build the project using the following command
```bash
mvn clean install
```
6. Change directory to the target folder
```bash
cd target
```
6. Deploy the application using the following command
```bash
gcloud app deploy backendapi-0.0.1-SNAPSHOT.jar
```
7. Access the application using the following command
```bash
gcloud app browse
```

## API Documentation

### Get all Pokemons
```http
GET /api/pokemons/all
```

### Get Pokemon by ID
```http
GET /api/pokemons/{id}
```

### Add Pokemon
```http
POST /api/pokemons/add
```
```json
{
  "id": 1,
  "name": {
    "english": "Bulbasaur",
    "japanese": "フシギダネ",
    "chinese": "妙蛙种子",
    "french": "Bulbizarre"
  },
  "type": [
    "Grass",
    "Poison"
  ],
  "base": {
    "HP": 45,
    "Attack": 49,
    "Defense": 49,
    "Sp. Attack": 65,
    "Sp. Defense": 65,
    "Speed": 45
  }
}
```