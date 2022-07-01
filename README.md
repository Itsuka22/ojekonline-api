# ojekonline-api

# API Specs

## Base url

```
https://ojekonline-api.herokuapp.com
```

## Table endpoints
### User Customer

| Name              | Endpoint                     | Method   | JWT token | Body and response                             |
|-------------------|------------------------------|----------|-----------|-----------------------------------------------|
| Register customer | `/api/user/customer/register` | `POST`   | no        |           |
| Login customer    | `/api/user/customer/login`    | `POST`   | no        |              |
| Get customer info | `/api/user/customer/customer`          | `GET`    | yes       |  |

### User Driver

| Name            | Endpoint                   | Method   | JWT token | Body and response                         |
|-----------------|----------------------------|----------|-----------|-------------------------------------------|
| Register driver | `api/user/driver/register` | `POST`   | no        |         |
| Login driver    | `api/user/driver/login`    | `POST`   | no        |            |
| Get driver info | `api/user/driver`          | `GET`    | yes       |  |

---

---
## Table Role
| Name Role             | Keterangan                     |
|-------------------|------------------------------|
| 1 | Customer |
| 2 | Driver |

---

## User Customer Examples
### User Customer - Register

```
POST /api/user/customer/register
```

Body

```json
{
    "username":"handoyo",
    "password":"12345",
    "email":"mukti@gmail.com",
    "address":"jl samarinda",
    "no_handphone":"0852233001",
    "firstname": "hans",
    "lastname" : "jas"
}
```

Response

```json
{
    "status": true,
    "message": "Success",
    "data": true
}
```

### User Customer - Login

```
POST /api/user/customer/login
```

Body

```json
{
    "username" : "handoyo",
    "password" : "12345"
}
```

Response

```json
{
    "status": true,
    "message": "success",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlZDI5YjMxZi0xYWJiLTQwYjAtOWJmMi0wMzlkY2NiZjcwOTciLCJhdXRoIjpbImhhbmRveW8iXSwiZXhwIjoxNjU2NzcxMDUxfQ.huCGF65-upMp6cbc1Qe_WdvWs4M7xLNnCUifsePl6_g"
    }
}
```

### User Customer - Get customer info

```
GET /api/user/customer
HEADER Authorization eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlZDI5YjMxZi0xYWJiLTQwYjAtOWJmMi0wMzlkY2NiZjcwOTciLCJhdXRoIjpbImhhbmRveW8iXSwiZXhwIjoxNjU2NzcxMDUxfQ.huCGF65-upMp6cbc1Qe_WdvWs4M7xLNnCUifsePl6_g
```

Response

```json
{
    "status": true,
    "message": "success",
    "data": {
        "id": "ed29b31f-1abb-40b0-9bf2-039dccbf7097",
        "username": "handoyo",
        "password": 12345,
        "email": "mukti@gmail.com",
        "address": "jl samarinda",
        "no_handphone": "0852233001",
        "firstname": "hans",
        "lastname": "jas",
        "role": 1
    }
}
```

---

## User Driver Examples
### User Driver - Register

```
POST api/user/driver/register
```

Body

```json
{
    "username":"2",
    "password":"1234",
    "email":"han@mail.com",
    "address":"jl ssss",
    "phone_number":"0899990",
    "first_name": "han",
    "last_name" : "handy"
}
```

Response

```json
{
    "status": true,
    "message": "Success",
    "data": true
}
```

### User Driver - Login

```
POST /api/user/driver/login
```

Body

```json
{
    "username" : "2",
    "password" : "1234"
}
```

Response

```json
{
    "status": true,
    "message": "success",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhNDcyNzhhZi1lMWRiLTRjZWEtYjY2MS00NGUzNzM2ZDBhOGEiLCJhdXRoIjpbIjIiXSwiZXhwIjoxNjU2NzcwMjE1fQ.1BCI_X3RM6FV1PMHHRmv7ZwckYWzse1rqxNMU8FiHO0"
    }
}
```

### User Driver - Get Driver info

```
GET /api/user/driver
HEADER Authorization eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhNDcyNzhhZi1lMWRiLTRjZWEtYjY2MS00NGUzNzM2ZDBhOGEiLCJhdXRoIjpbIjIiXSwiZXhwIjoxNjU2NzcwMjE1fQ.1BCI_X3RM6FV1PMHHRmv7ZwckYWzse1rqxNMU8FiHO0
```

Response

```json
{
    "status": true,
    "message": "success",
    "data": {
        "id": "a47278af-e1db-4cea-b661-44e3736d0a8a",
        "username": "2",
        "password": null,
        "email": "han@gmail.com",
        "address": "jl ssss",
        "no_handphone": "han",
        "firstname": "handy",
        "lastname": "0899990",
        "role": 2
    }
}
```
