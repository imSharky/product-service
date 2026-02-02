Prerequisites
Before running this microservice, ensure the following dependencies are available and properly configured:
Redis is up and running
User Service is running and accessible
Postman (or any API client) for testing APIs

Service Startup Order
Start Redis: Make sure Redis is running on the configured host and port.
Start User Service: This service is required for authentication and JWT generation.
Start Payment Service: Run the Payment Service after Redis and User Service are up.

Authentication (JWT Token)
This microservice is secured using JWT-based authentication.
Use Postman to log in via the User Service login API.
Copy the generated JWT token from the response.
Add the token to the request headers when calling Payment Service APIs:
Key: authorization
Value: <JWT_TOKEN>
