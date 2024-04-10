# Data Visualization  From The "ThingSpeak" Service.

### This project is a full-stack web application built with React, Chakra UI, and ApexCharts on the frontend, and Java with Spring Boot on the backend. It allows users to fetch data from ThingSpeak, a platform for Internet of Things (IoT) devices, save and validate it in a PostgreSQL database using Docker containers, and visualize the data using interactive charts. ### 

## Features
###  -   Integration with ThingSpeak: Fetch data from ThingSpeak channels.
###  -   Data Validation and Storage: Validate the fetched data and store it in a PostgreSQL database for persistent storage.
###  -   Docker Containerization: Utilize Docker containers to deploy and manage the PostgreSQL database.
###  -   React Frontend: Present the data through a user-friendly interface built with React.js and Chakra UI components.
###  -   Interactive Data Visualization: Use ApexCharts to visualize the data fetched from ThingSpeak channels, providing users with insightful visualizations.

## Technologies Used
### Frontend:
    - React.js
    - Chakra UI
    - ApexCharts

    Backend:
    -Java
    -Spring Boot
    -PostgreSQL
    
    Other Tools:
        Docker
## How to Use

###   git clone  
    https://github.com/Levantosina/DataVisualization.git


### Pull a Docker Image

    docker push levantosina/datavisualization:tagname
### Set up PostgreSQL database using Docker:
    docker-compose up -d
### Navigate to the project directory:
    cd frontend/dvReact
    in terminal: npm run dev

## Contributions are welcome! If you have any ideas or improvements, feel free to open an issue or submit a pull request.