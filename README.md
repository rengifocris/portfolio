# portfolio 🧑🏽‍💻
 Portfolio project with updating and filtering data 

### Run this locally 🧑🏽‍💻
```
 git clone https://github.com/rengifocris/portfolio.git
```

```
 cd into portfolio
```

```
./mvnw spring-boot:run
```

```
open a browser on http://localhost:8080
```

### Api Endpoints 
#### Query Portfolio Information

Going to this URL you're gonig to be able to see the swagger docs  ```http://localhost:8080/swagger-ui.html```

https://user-images.githubusercontent.com/24859230/161893898-861913bf-b425-419d-af68-d9c2cf00268b.mov

Sample Request for fetching data👇🏽

```
curl -i -X GET 'http://localhost:8080/portfolio/{IdNumber}'
```


#### Update Portfolio Information

Going to this URL you're gonig to be able to see the swagger docs  ```http://localhost:8080/swagger-ui.html```

https://user-images.githubusercontent.com/24859230/161894852-c4a91ecf-4376-4039-a873-48e026f712ea.mov

Sample request for update data👇🏽

```
curl -i -X PUT \
   -H "Content-Type:application/json" \
   -d \
'{
 "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Marvel_Logo.svg/2560px-Marvel_Logo.svg.png",
 "title": "Marvel",
 "description": "Marvel comics and marvel studios are rocking!!",
 "twitterUsername": "Marvel"
}' \
 'http://localhost:8080/portfolio/{IdNumber}'
```

## Technologies
* [Swagger](https://swagger.io/)
* [Bootstrap](https://getbootstrap.com/)
* [Vue.js](https://vuejs.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Twitter4J](http://twitter4j.org/en/)
* [JUnit](https://junit.org/)
* [Mockito](https://site.mockito.org/)
* [git](https://git-scm.com/)

# Timer
15 hours nearly.
