# **Процедура запуска авто-тестов**

### Перед запуском авто-тестов необходимо установить:

* [Docker Desktop](https://www.docker.com/products/docker-desktop)

### Запуск авто-тестов:

1. Запустить контейнеры СУБД MySQl, PostgerSQL и Node.js командой в терминале:

```
docker-compose up -d
```

2. Запустить SUT, работающей на СУБД MySQl командой:

```
java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
```

на СУБД PostgreSQL командой:

```
java -Dspring.datasource.url=jdbc:postgresql://localhost:5433/app -jar artifacts/aqa-shop.jar
```

3. В новом терминале запустить авто-тесты командой для MySQL:

```
gradlew clean test -Durl=jdbc:mysql://localhost:3306/app
```

для PostgreSQL:

```
gradlew clean test -Durl=jdbc:postgresql://localhost:5433/app
```

4. Сгенерировать отчеты двумя командами:

```
gradlew clean test allureReport
```

```
gradlew allureServe
```

5. Остановить и удалить все контейнеры командой:

```
docker-compose down 
```
