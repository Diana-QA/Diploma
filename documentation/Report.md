# Отчет по итогам автоматизированного тестирования

### **Краткое описание**

Автоматизация тестирования выполнялась с использованием следующих инструментов:

* **Junit 5** 

```
 testImplementation 'org.junit.jupiter:junit-jupiter:5.7.2'
```

* **Selenide** 

```
testImplementation 'com.codeborne:selenide:5.23.0'
```

* **Allure** 

```
testImplementation 'io.qameta.allure:allure-selenide:2.14.0'
```

Для работы с БД и симулятором банковских сервисов использовался [Docker](https://www.docker.com/products/docker-desktop)

### **Количество тест-кейсов**

* Всего: *24 тест-кейсов*
* Успешных: *20 (83.33%)*
* Неуспешных: *4 (16.67%)*

### **Отчет Gradle:**

![4664373](https://user-images.githubusercontent.com/79462466/128639359-19b2b84e-25e3-4707-9d63-153489689429.png)

### **Отчет Allure:**

![64577 (2)](https://user-images.githubusercontent.com/79462466/128639367-74f5c357-b297-42f4-8553-80d2f32c4c19.png)

![5767 (2)](https://user-images.githubusercontent.com/79462466/128639415-038544e3-fe14-4180-9600-d129c522122d.png)

### **Общие рекомендации**

#### **Issue заведено `10`:**

[1. Сумма оплаты тура в базе данных при оплате отличается](https://github.com/Diana-QA/Diploma/issues/10)

[2. Отображение кредитной истории в базе данных при оплате в кредит не записывается](https://github.com/Diana-QA/Diploma/issues/9)

[3. Операция проходит успешно при отправке карты со статусом DECLINED](https://github.com/Diana-QA/Diploma/issues/8)

[4. Отсутствует валидация поля "Владелец"](https://github.com/Diana-QA/Diploma/issues/7)

[5. Неполная валидация поля "CVC/CVV"](https://github.com/Diana-QA/Diploma/issues/6)

[6. Неполная валидация поля "Месяц"](https://github.com/Diana-QA/Diploma/issues/5)

[7. Падение веб-приложения при отправке некорректным значением поля "Номер карты"](https://github.com/Diana-QA/Diploma/issues/4)

[8. Неправильное поведение отображения сообщения об ошибке под полем ввода после исправления](https://github.com/Diana-QA/Diploma/issues/3)

[9. Двойное уведомление при отправке запроса с не валидным номером карты](https://github.com/Diana-QA/Diploma/issues/2)

[10. Неподходящее название в заголовке страницы](https://github.com/Diana-QA/Diploma/issues/1)

