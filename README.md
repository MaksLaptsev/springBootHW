## REST веб-сервис для работы с каналами видеохостинга на Spring Boot
## Используемые технологии:
- Java 17
- Spring Boot 3.1.5
- Gradle
- PostgreSQL 
- JPA
- Spring Data Repositories
- Lombok
- Liquibase
- MapStruct
## Для запуска необходимо:
- Выполнить docker-compose up -d (docker-compose находится в папке с ресурсами), для создания бд postgres
- После запуска приложения в Intellij IDEA Liquibase создаст таблицы и заполнит их.
## Реализованные операции:(кликабельно, ведет в файл с http запросами)
- Создание пользователя
  [POST http://localhost:8080/user ](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L1)
- Редактирование пользователя с id: 5
    [PUT http://localhost:8080/user/5](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L11)
- Создание канала
    [POST http://localhost:8080/channel?authorId=5](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L21) , где authorId - id пользователя, владельца канала
- Редактирование канала
    [PUT http://localhost:8080/channel?channelId=12](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L43) , где channelId - id канала, который редактируем
- Подписка пользователя на канал
    [PUT http://localhost:8080/channel/subscribe?channelId=12&personId=2](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L65) , где channelId - id канала на который
                                                                          подписывается пользователь, personId - id пользователя
- Отписка пользователя с канала
    [PUT http://localhost:8080/channel/unsubscribe?channelId=12&personId=2](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L68) , где channelId - id канала на с которого
                                                                            отписывается пользователь, personId - id пользователя
- 7.1 Фильтр по языку + пагинация(опционально)
        [GET http://localhost:8080/channel/filter?language=rus&page=0&size=2](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L71) где language - небходимый для поиска язык (rus)
- 7.2 Фильтр по названию + пагинация(опционально)
        [GET http://localhost:8080/channel/filter?name=jetix&page=0&size=2](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L74) где name - неоходимое для поиска название канала (tech)
- 7.3 Фильтр по категории + пагинация(опционально)
        [GET http://localhost:8080/channel/filter?category=hea&page=0&size=2](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L76) где category - необходимая для поиска категория канала (hea)
    page - номер страницы(для пагинации), size - кол-во объектов для отображения на одной странице
- Отображение списка всех подписок пользователя(без пагинации, отображаются только имена каналов)
    [GET http://localhost:8080/user/subscriptions?personId=5](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L79) где personId - id пользователя
- Получение подробной информации об канале(название, категория, ид, дата создания, кол-во подписчиков, автор, язык)
    [GET http://localhost:8080/channel/5](https://github.com/MaksLaptsev/springBootHW/blob/fd68c99fcdc274d7ada4119af4293348172989ed/src/main/resources/requests.http#L82)  где 5 - id канала

## Описание сущностей:
- Канал: имеет название, краткое описание, автора (среди зарегистрированных пользователей), неограниченное число подписчиков (тоже среди зарегистрированных пользователей), дату создания, один основной язык, аватарку(формат jpg/png, хранятся в папке logo), одну категорию
- Пользователь
Каждый пользователь имеет никнейм, имя, почту и может подписаться на неограниченное число каналов