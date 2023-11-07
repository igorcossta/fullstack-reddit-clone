<a id="readme-top" name="readme-top"></a>



<!-- PROJECT LOGO -->
<img src="https://i.imgur.com/7wlnLSi.png" align="right" />

# Reddit Clone

> A simple reddit clone made with Java/Spring and ReactJS

<br>

<p align="right">
    <a href="https://example.com">View Demo</a>
    ·
    <a href="https://example.com">Report Bug</a>
    ·
    <a href="https://example.com">Request Feature</a>
</p>
<p align="right">
  <a href="#getting-started"><strong>Explore the docs »</strong></a>
</p>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#preview">Preview</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Home Page][product-screenshot]](https://github.com/igorcossta/reddit-clone/)

Reddit Clone is a web application developed using Java and Spring for the backend, and ReactJS for the frontend. It aims to provide users with a familiar and intuitive interface for creating and participating in online discussions, sharing links and media, and building communities around their interests.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

The application is meticulously crafted with an array of cutting-edge technologies to ensure a seamless and
delightful user experience. Here's a closer look at the technologies powering this clone:


- [Java 11] - The foundation of the application, ensuring robust performance and security.
- [Spring Framework] - Empowering the application with security features and dynamic web pages.
- [JWT] - Authentication and Authorization with JWT.
- [ReactJS] - The library for creating interactive web content.
- [Lombok] - Simplifying Java code with reduced boilerplate.
- [IntelliJ] - The development environment of choice for efficient coding.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

Follow the instructions to RUN `Reddit` on your machine:

### Prerequisites

To run the application, you'll need the following essential technologies and configurations:
* Ensure you have Java 11 or a newer version installed. You can install it with SDKMAN:
  ```sh
  sdk install java 11.0.1-tem
  ```

* Set up a database for Reddit using Docker and PostgreSQL. Run the following command to create a PostgreSQL container
  with specific configurations:
    ```sh
    docker run --name mypostgres \
    -e POSTGRES_USER=root \
    -e POSTGRES_PASSWORD=password \
    -e POSTGRES_DB=mydatabase \
    -p 5432:5432 \
    -d postgres
    ```
  > Note: The application currently uses an H2 database, which is ideal for development and testing.

* Download node using [nvm](https://github.com/nvm-sh/nvm)
    ```sh
    nvm install 12
    ```

### Installation

You can now download and set up the project on your machine by following these simple steps:

1. Use either of the following commands to clone the Yummy repository to your local machine:
    ```sh
    git clone git@github.com:igorcossta/reddit-clone.git
    git clone https://github.com/igorcossta/reddit-clone.git
    ```
2. Open the project in your favorite IDE.

3. In your project's `application-dev.yaml` file, locate the database configuration section and enter your docker
   container setup:
     ```yaml
      # DATABASE CONFIGURATION
      spring.datasource.url: jdbc:postgresql://${POSTGRES_HOST}:${POSTGRES_PORT}/${POSTGRES_DATABASE_NAME}
      POSTGRES_HOST: localhost
      POSTGRES_PORT: 5432
      POSTGRES_DATABASE_NAME: mydatabase
      spring.datasource.username: postgres
      spring.datasource.password: password
     ```
   > Note: This section must match with the docker or whatever database you are using.

4. Go to `frontend` folder and install NPM packages:
   ```sh
   npm install
   ```

5. Go to `backend` folder and install maven dependencies:
   ```sh
   mvn install
   ```

6. Finally run both applications and go to `localhost:3000`

With these straightforward steps, you'll have the application up and running.

<p align="right">(<a href="#readme-top">back to top</a>)</p>




<!-- PREVIEW -->
## Preview

[![Account][account]](https://github.com/igorcossta/reddit-clone)
[![Dashboard][dashboard]](https://github.com/igorcossta/reddit-clone)
[![subreddit][subreddit]](https://github.com/igorcossta/reddit-clone)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [x] Finished ?

See the [open issues](https://github.com/igorcossta/reddit-clone/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<br>

<!-- MARKDOWN LINKS & IMAGES -->
[product-screenshot]: https://user-images.githubusercontent.com/65612587/173901627-48d89f74-78cd-4d7c-900e-aa2b1b9b085f.png

[account]: https://user-images.githubusercontent.com/65612587/173901705-76ee7db8-298a-405e-b671-58613e4cf775.png
[dashboard]: https://user-images.githubusercontent.com/65612587/173901785-6b057458-4e45-4bcb-8b0d-d8db3c89471d.png
[subreddit]: https://user-images.githubusercontent.com/65612587/173901801-a10ced98-46bf-4d9e-9ef0-f82f8dc6fa59.png
