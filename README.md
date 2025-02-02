# Queriously

**A Spring Boot-based Q&A platform for curious minds**

Queriously is a minimalist Q&A application where users can ask questions, vote on content, and engage in meaningful discussions. Whether you're looking to satisfy your curiosity or share your expertise, Queriously offers a simple yet robust environment for knowledge exchange.

---

## Features

Queriously lets you post questions with a title and details. Upvote, downvote, and comment to drive engaging debates 💬, with secure JWT authentication for sign-up and login 🚀.

### API Documentation
<code>➜</code> **Self-Documenting APIs:**  
  Leverage SpringDoc OpenAPI for easy exploration and testing of all endpoints.

---

## Tech Stack

<code>➜ **Backend:** Spring Boot (Java) </code>  
<code>➜ **Database:** MySQL </code>     
<code>➜ **Security:** JWT-based authentication </code>    
<code>➜ **API Documentation:** SpringDoc OpenAPI </code>    
<code>➜ **Frontend:** Next.js & shadcn/ui with Bun</code>    
<code>➜ **Build Tool:** Maven </code>  

---

## Why Queriously?

Queriously was born out of a desire to dive back into Spring Boot and level up my Next.js knowledge. This project is a fun experiment where I mix backend and frontend magic into one neat package. It’s a playground for trying out cool coding tricks, handling errors smartly, and building APIs that just work without all the fuss.

---

## Getting Started

### Prerequisites
- Java 17
- Maven
- MySQL
- Bun

---

### Running the Backend
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/maskedsynatax/queriously.git
   ```
2. **Configure the database:**  
   Update your database connection details in:
   ```
   src/main/resources/application.properties
   ```
3. **Build and Run:**  
   ```bash
   mvn spring-boot:run
   ```
4. **Explore the API:**  
   Access the API documentation at <code> http://localhost:8080/swagger-ui.html </code>

### Running the Frontend
1. **Navigate to the Frontend Directory:**  
    ```
    cd frontend
    ```
2. **Install Dependencies with Bun:** 
   ```bash
   bun install
   ```
3. **Start the Dev Server:**
   ```bash
   bun dev
   ```
4. **Open the App:**  
   Head over to <code> http://localhost:3000 </code> in your browser


---

## Current Status

Just starting out and will be updating details as I keep working on the project. Feedback or ideas are always welcome.

---

## License
This project is licensed under the MIT License.

---

## Let's Connect!
Have suggestions or spot something that could be better? Open an issue or reach out directly.
