# AI Research Assistant - Backend

This repository contains the backend service for the **AI Research Assistant Chrome Extension**.

## Prerequisites

Before running the backend, download and install the Chrome extension locally.

### Chrome Extension Repository

https://github.com/abdulaziz0000/Ai-Research-Assistant-Chrome-Extension

Follow the instructions in the extension repository to load the extension into Chrome using **Developer Mode**.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/abdulaziz0000/Ai-Research-Assistant.git
```

### 2. Open the Project

Import the project into your preferred Java IDE (IntelliJ IDEA, Eclipse, or VS Code).

### 3. Configure Your API Key

Create an environment variable for your AI API key.

Example:

```text
GEMINI_API_KEY=YOUR_API_KEY
```

> **Note:** Never commit your API keys or other secrets to the repository.

### 4. Run the Application

Run the application using Maven:

```bash
./mvnw spring-boot:run
```

Or start it directly from your IDE.

## Using the Application

1. Start the backend application.
2. Load the Chrome Extension locally.
3. Open the extension from the Chrome toolbar.
4. Enter or paste the text you want to analyze.
5. Generate AI-powered summaries and suggestions.

## Tech Stack

- Java
- Spring Boot
- Maven
- REST API

## License

This project is intended for educational and personal learning purposes.
