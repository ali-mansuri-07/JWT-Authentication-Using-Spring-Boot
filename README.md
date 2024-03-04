# Spring Boot JWT Authentication Example

In this project, we'll demonstrate how to configure InMemory user and JWT authentication using the latest Spring Boot 3.0.

## Setup

1. Create a new Spring Boot project using [Spring Initializer](https://start.spring.io/) with the following dependencies:

    - **Web**:
        ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        ```
    
    - **Security**:
        ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        ```
    
    - **Lombok**:
        ```xml
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        ```
    
    - **JWT**:
        ```xml
        <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.11.5</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
            <version>0.11.5</version>
            <scope>runtime</scope>
        </dependency>
        ```

2. Create the endpoints:

    - **Test Endpoint**:
    
        ```java
        @RestController
        public class HomeController {

            Logger logger = LoggerFactory.getLogger(HomeController.class);

            @RequestMapping("/test")
            public String test() {
                this.logger.warn("This is a working message");
                return "Testing message";
            }
        }
        ```
    
    - **User Creation Endpoint**:
    
        *Add a new controller method to handle user creation.*
        
    - **Login Endpoint**:
    
        ```java
        @RestController
        @RequestMapping("/auth")
        public class AuthController {

            // Other methods...

            @PostMapping("/login")
            public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

                // Authentication logic...

                UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
                String token = this.helper.generateToken(userDetails);

                JwtResponse response = JwtResponse.builder()
                        .jwtToken(token)
                        .username(userDetails.getUsername()).build();
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        ```
    
    - **Logout Endpoint**:
    
        *Add a new controller method to handle user logout.*

3. Configure InMemory user with `UserDetailService` Bean:

    ```java
    @Configuration
    class MyConfig {
        @Bean
        public UserDetailsService userDetailsService() {
            UserDetails userDetails = User.builder()
                    .username("DURGESH")
                    .password(passwordEncoder().encode("DURGESH"))
                    .roles("ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(userDetails);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
            return builder.getAuthenticationManager();
        }
    }
    ```

4. Implement JWT Authentication Flow:

    - Create `JWTAthenticationEntryPoint` class.
    - Create `JwtHelper` class.
    - Create `JwtAuthenticationFilter` class.
    - Configure Spring Security in `SecurityConfig`.
    - Create `JWTRequest` and `JWTResponse` classes.
    - Create `AuthController` with login API.

## Testing

1. Test the application by accessing:

    ```
    http://localhost:8080/test
    ```

2. Log in using the provided username and password when prompted.

3. Create users using the appropriate endpoint.

4. Log out using the logout endpoint.

