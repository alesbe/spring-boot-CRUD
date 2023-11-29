# Spring Boot - Movies CRUD
A CRUD made for a movies database made with Spring Boot

## 🏗️ Arquitecture
This CRUD uses a [Controller - Service - Repository](https://tom-collings.medium.com/controller-service-repository-16e29a4684e5) pattern.

![layers](https://i.imgur.com/4p4h1ff.png)

This designs allows the domain layer to be completly isolated from the other layers. We achieve this using a variety of techniques:
- [**Dependecy inversion principle**](https://en.wikipedia.org/wiki/Dependency_inversion_principle): This way, the persistance layer implements the interfaces defined in the domain layer. The main idea is that the domain should contain all the application logic and shouldn't be dependent or rely on any other layer.
  
- **Creating entities for each layer**: Each layer uses its own entities, adapted for its own use case. The web entities are adapted to recieve data, the domain entites are the main entities of the application, and the persistance entities are adapted to the database tables, but not mirroring the columns.

**🚧 README WIP 🚧**
