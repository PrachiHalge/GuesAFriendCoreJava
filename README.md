# GuessAFriend

Welcome to the GuessAFriend application! This Java application assists in organizing events where participants can randomly select friends for various activities, such as gift exchanges or special events. Below, you'll find information on how to use the application and contribute to its development.

## Features

- **Random Friend Selection**: Randomly assigns friends for various activities.
- **Database Integration**: Uses a database to store and retrieve participant information.
- **Customizable**: Easily modify the participant list and other parameters to suit your event.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) version 8 or later.
- Database (e.g., Oracle) for storing participant information.
- Git (optional, if you want to clone the repository).

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/GuessAfriend.git

   Usage
Set up your database and configure the database connection in ConnectionJDBC.java.
Customize the participant list in SecretSanta objects within the GetDataServiceImpl class.
Run the application to get names with no "ToFriend" and "FromFriend" assignments.
Assign ToFriends using the provided methods and update the database accordingly.
Retrieve assigned ToFriend names and organize your event with GuessAFriend!

####  Table Structure

CREATE TABLE "EAT"."TESTSANTA" (
    "PERSONID" NUMBER,
    "FROMNAME" VARCHAR2(500 BYTE),
    "TONAME" VARCHAR2(500 BYTE)
) 

