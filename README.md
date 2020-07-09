
<!-- TABLE OF CONTENTS -->
## Table of Contents

* [Create and Manage User](#create-and-manage-user)
  * POST user/register
* [Create and Manage Item](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)


<!-- GETTING STARTED -->
## Create and Manage User

### POST user/register

```sh
example: POST /user/register?name=value&email=value&password=value
```

### POST user/login

```sh
example: POST /user/login?email=value&password=value
```

```sh
response body:
{
    "id": String,
    "email": String,
    "password": String,
    "name": String,
    "address": String,
    "profileImg": String
}
```

### POST user/update

```sh
example: POST /user/update?id=value&name=value&address=value&profileImg=value
```

### POST user/updatePassword

```sh
example: POST /user/updatePassword?id=value&pwd=value
```
