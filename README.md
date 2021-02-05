# SDN-Vodafone
Network Management Web Application 

The chosen approach of implementing the network management application is to store the objects in a H2(in-memory) database and access them via JPA.

Package Entities contains the definition of each entity used to establish the relational database, following a structure close to the JSON example. In our case,
there exists a one to many relation between the foreign keys, with usage of CascadeType.ALL to propagate persistance events from parent entity to child.
(and orphanRemoval = true to remove dependent entities when the parent is no longer available)

Package Repositories contains the interfaces that allow easy access to the data. I have created the second repository as a way to avoid the iteration from City
entity to the lowest NetworkInfo entity. For the /getNetworks endpoint it did allow for an easier access of the data, although there was no way around for the 
/insertNetwork one. Thus, the implementation can do without the Network repository and still work.

The Configuration Package contains the LoadDatabaseConfiguration where the database is initiated through the CommandLineRunner method marked as a Bean, which runs on 
start up. In here I have also included the Swagger-UI Bean for ease of interaction with the API. Within the same package, the controller contains our endpoints receiving
HTTP requests. The implementation logic is stored in the Service package, service that will be injected via the constructor in the controller.

Finally, for testing the web layer, a smoke test verifies that the application context can start and that the context creates the controller. Besides the sanity check,
the following tests assert the behaviour of the application. This is done using Spring's MockMvc, which tests the layer were Spring handles the HTTP requests and hands it
off to the controller, without starting the server. The third and final test asserts the former case only on the web layer.


