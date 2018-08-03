# @MaxKlyukin/fms_branch_registry

### Set up:

1. Install [Docker](https://docs.docker.com/install/#supported-platforms) (with Docker Compose).
2. Run application:
    `docker-compose up`

### UI:

You can find ui here: http://localhost:8080/.

### Endpoints:

#### 1. Fetch fms branch info
`curl -XGET http://localhost:8080/branches/580-015`

`200 OK`
--------
```javascript
{
    "code": "580-015",
    "name": "ТП №1 МО УФМС РОССИИ ПО ПЕНЗЕНСКОЙ ОБЛ. В Г. Н-ЛОМОВЕ (С МЕСТОМ ДИСЛОКАЦИИ В С. ВАДИНСК)"
}
```

`404 Not Found`
---------------
```javascript
{}
```

#### 2. Initiate db update
`curl -XPOST http://localhost:8080/updates`

`200 OK`
---------------
```javascript
{}
```

`500 Internal Server Error`
---------------
```javascript
{
    error: "Registry can not fetched, it is unavailable or invalid"
}
```