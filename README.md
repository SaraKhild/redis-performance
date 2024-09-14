# JMeter Performance Test Results for Three Redis implementations: spring-boot-starter-data-redis, redisson-spring-boot-starter, and spring-boot-starter-data-redis-reactive.

<br>

## Overview
This project <mark>compares the performance of three different Redis implementations</mark> using JMeter: <strong>spring-boot-starter-data-redis</strong>, <strong>redisson-spring-boot-starter</strong>, and <strong>spring-boot-starter-data-redis-reactive.</strong> The tests evaluate the <mark>throughput</mark>, <mark>latency</mark>, and <mark>resource consumption of each implementation under different load conditions</mark>, providing insights into the optimal choice for high-performance, scalable applications.
  
## Usages
- Data Redis
- Redis Reactive
- Redisson

## Architecture of each the Projects

 ### 1-src folder
   - configration
   - controller
   - model
   - repository
   - service
   - util
     
 ### 2-resources folder
   - application.properties
   - schema.sql
   
### 3-Maven pom.xml of Data Redis
<br> 
    
```
	<dependencies>
	<dependency>
			<groupId>com.example</groupId>
			<artifactId>redis-reactive</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-r2dbc</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.asyncer</groupId>
			<artifactId>r2dbc-mysql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.projectreactor</groupId>
			<artifactId>reactor-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
 ```

### 4-Maven pom.xml of Redis Reactive
<br> 
    
```
	<dependencies>
	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis-reactive</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.30</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-r2dbc</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.asyncer</groupId>
			<artifactId>r2dbc-mysql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.projectreactor</groupId>
			<artifactId>reactor-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
 ```

<br>

### 5-Maven pom.xml of Redisson
<br> 
    
```
	<dependencies>
	<dependency>
			<groupId>com.example</groupId>
			<artifactId>redis-reactive</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
		</dependency>
		<dependency>
			<groupId>org.redisson</groupId>
			<artifactId>redisson-spring-boot-starter</artifactId>
			<version>3.16.6</version>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.30</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-r2dbc</artifactId>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.asyncer</groupId>
			<artifactId>r2dbc-mysql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.projectreactor</groupId>
			<artifactId>reactor-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
 ```

<br>

###### Output :star_struck: 
 The `spring-boot-starter-data-redis-reactive` implementation seems to have **the best overall performance**, as it shows **lower average response times** and **higher throughput**.`Redisson` and `spring-boot-starter-  data-redis` have **similar performance**.

- Redis:
<img width="1018" alt="data-redis" src="https://github.com/user-attachments/assets/3644280d-322e-477e-a82d-a75f1f5ffb85">

- Redis Reactive:
  
<img width="1024" alt="reactive-redis" src="https://github.com/user-attachments/assets/51c4b752-ecb4-4dfc-84ce-4768881382a9">

- Redisson:
  
<img width="1021" alt="redisson" src="https://github.com/user-attachments/assets/d3be5664-c7fe-4065-8c29-1d888e134bb6">

---

### Good Luck <img src="https://media.giphy.com/media/hvRJCLFzcasrR4ia7z/giphy.gif" width="30px"> 
