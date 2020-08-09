### **Task F.c4 Quick Poll - start the app**

Task description:
  
  1) Get the book: Spring Rest 2015 (https://drive.google.com/file/d/1RsenHfvDpM0pDMRKcfaYT0pmPTnZ0jt7/view?usp=sharing).
  
  2) Give your estimations regarding time it will take for your to completion the task.
  
  3) Read 4 chapter of the book and be ready to pass a quizz, answer knowledge verification questions.
  
  4) Debugging. Записать видео-ролик, выложить на youtube с доступом unlisted, ссылку добавить в МР:
  
    - через Постман отправить несколько запросов на создания разных Поллов;
        
    - через Постман вызываем получить Полл по id 1234(любой несуществующий);
        
    - видим что такого нет (кстате, вместо статус кода 404, код 200 и null в теле);
        
    - ставим breakpoint в начале соответствующего handler method
        
    - находясь в дебаг в хандлер методе: смотрим какой id пришел
        
    - находясь в дебаг в хандлер методе: смотрим Поллы с какими id есть в БД
        
    - отпускаем дебаггер (IDEA:F9)
        
  5) Create MR ("Spring Rest 2015 book - Chapters 4" in the title) and send me the link.

*****
*****

Quiz answers:

1. Basic conventions in endpoint design:

    1. using a base URI,

    2. using plural names for resourses,

    3. URI hierarchy of endpoint show relations between resources,

    4. using a query parameters for additional information.

2. Design of RESTful application have 4 basic steps:
  
    1. resource identification - choose requirement nouns,
  
    2. resourse representation - choose representation formats,
 
    3. endpoint identification - design endpoints,
  
    4. action identification - choose Http methods for endpoints.

3. Above (2)

4. Lame REST API DESIGN might be incomprehensible for user, can provoke redundant requests, overload interface or database connection etc.

5. Next resources have been identified in QuickPoll application: poll, vote, user (and plurals).

6. REST APIs supports many formats: JSON, XML, HTML. JSON is used in QuickPoll application.

7. Noun, required for data representation and not makes API more redundant, will stay resource.

8. REST API might support only one format when it's internal to company. JSON, unlike xml, is shorter, can use arrays and simpler in parsing.

9. JSON support next types of values: null, boolean, int, float, String, array and JSON object

10. @OrderBy annotation provoke sorting when collection is retrieved.

11. DAO can be used with any type of data source, not only databases.

12. [Mouse endpoint](docs/Mouse.md) to get all for entity *Mouse {id: long, name: String}*.

13. We use ResponseEntity when we need add to response mor information - headers, HttpStatus etc.

14. @RequestBody annotation is used for map body to a domain object.

15. Location header contains URI of created object.

16. Data transfer objects are used to represent calculation, based on domain object.

17. Different between @Inject and @Autowired:

    1. Only Spring supports @Autowired, many libraries support @Inject. So @Inject is better for portability.
  
    2. @Inject can be used without any attributes.

18. Dependency for @Inject using:

    groupId: javax.inject
    
    artifactId : javax.inject

    version : 1

    scope : runtime

19. Layered architecture makes application simpler to build and separate concerns. Layers of typical REST application:

    1. WEB API layer. It constains convertation of exception to http codes, satisfaction of specific requirements of different platforms' clients, validation of request parameters.
    
    2. Domain layer. It provides processing of a user request according to business rules.
    
    3. Repository layer. It performs CRUD operations on entities and manages transactions between multiple data sources.