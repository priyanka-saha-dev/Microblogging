MVP key features :

User registration and login.
Posting tweets.
Following/unfollowing users.
Displaying a timeline of tweets from followed users.
We'll use Spring Boot for the backend, Kafka (or another messaging system like Redis) for real-time tweet streaming, and H2 (or any relational database like MySQL) for persistence.

High-Level Architecture
Frontend: Can be a web or mobile application.
Backend: Built using Spring Boot with REST APIs.
Database: H2 for development, MySQL/PostgreSQL for production.
Messaging: Kafka for real-time feeds (optional in this version).
Security: Basic authentication (for MVP).
