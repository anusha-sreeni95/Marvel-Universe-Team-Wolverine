# Marvel-Universe-Wolverine

![Marvels](https://images.moviepilot.com/image/upload/c_fill,h_470,q_auto:good,w_620/bdwb9g6i5jrxofbl1gs5.jpg)

Marvel-Universe-Wolverine is a secure backend RESTful API service for Avengers and XMen Mission center with Super Server serving the web pages.

# Features
  - Secured with Sessions. Each API endpoint is secured with checks for open session.
  - Single entry point (API) to open a session with password.
  - Passwords stored as hash maps with random salt to to beat rainbow table attack
  - XMen and Avenger servers can only serve requests from a super server

Also:
  - Single sign on and redirection for members of both Xmen and Avenger.
  - Squad Todo integration for members of both Xmen and Avenger.
  - Personal Todo safe with you on Xmen or Avenger mission Control.

### Infrastructure and Design

                                 User           O
                                               -|-
                                               / \
                                               
                                              |  ^
                                Response      |  |  Request
                                              v  |
                                    --------------------------
                                    |                       |
                                    |      Super Server     |
                                    |                       |
                                    --------------------------
                                    /   >                       <   \
                                /   /                               \   \
                            /   /                                       \   \
                        <     /                                             \   >
                -----------------                                       -----------------
                |Rest Web Service|                                      |Rest Web Services|
                |   Avengers     |                                      |     X Men       |
                -----------------                                       -----------------
                        <   \                                               <   /
                            \   \                                       /   /
                                \   \                               /   /
                                    \   >                       /   >
                                    ---------------------------------
                                    |   In Memory H2 Database   |
                                    ---------------------------------

### Setup and Installation

Clone the Marvels Universe Wolverine (This).
```sh
$ git clone https://github.com/anusha-sreeni95/Marvel-Universe-Wolverine
```
Import 3 different projects in Intellij Idea and run with below mentioned specifications
Marvel-Universe - port 9000 
Marvel-Universe-avengers - port 8040
Marvel-Universe-xmen - port 8050

Marvel-Universe - Serve the web pages and it sends request to Rest Servers and complete the request. Avengers and XMen can only serve request from Super Server. But we still need to integrate the filters to make it secure.

Open the browser and access the website : http://localhost:9000

End users won't know the IP addresses of REST servers.







### Tech

 - Java with SpringBoot and Maven
 - Bcrypt
 - H2 in memory database
 - Swagger UI
 - Jetty server
 - html, css (BootStrap), javascript/jquery, Thymeleaf
