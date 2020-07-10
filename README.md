
<!-- TABLE OF CONTENTS -->
## Table of Contents

* [Create and Manage User](#create-and-manage-user)
  * POST user/register
  * POST user/login
  * POST user/update
  * POST user/updatePassword
* [Create and Manage Item](#create-and-manage-item)
  


## Create and Manage User

### POST user/register
take parameters of
* name : String value of user name.
* email: String value of user email, example user@email.com.
* password: String value of user password.
```sh
example: POST /user/register?name=vlue&email=value&password=value
```
will return profile of the user
```sh
response body:
{
    "id": "4c32934d7d1aa0a6d1eb8456a3b16c5fd*****",
    "name": "name of user",
    "address": null,
    "profileImg": null
}
```

### POST user/login
take parameters of
* email: String value of user email, example user@email.com.
* password: String value of user password.
```sh
example: POST /user/login?email=value&password=value
```
will return authentication token for user
```sh
response body:
cd8c1eb8fd296ef41eb3c49b958c32acbd****
```

### PUT user/update
Make update the name, address, profile image of the user.
take parameters of
* tokenId : String value of user authentication token.
* name : String value of user name.
* address : String value of user address.
* profileImg: reference link of user profile image.
```sh
example: PUT /user/update?tokenId=value&name=value&address=value&profileImg=value
```
will return profile of the user
```sh
response body:
{
    "id": "4c32934d7d1aa0a6d1eb8456a3b16c5fd*****",
    "name": "name of user",
    "address": "address xxx",
    "profileImg": "http://www.link-of-image.com"
}
```

### PUT user/updatePassword
take parameters of
* tokenId : String value of user authentication token.
* oldPw : String value of user current password.
* newPw : String value of user new password.

```sh
example: PUT /user/updatePassword?tokenId=value&oldPw=value&newPw=value
```
will return string value of "success" or "failed"


## Create and Manage User

### POST item/insert
take parameters of
* tokenId : String value of user authentication token.
* name : String value of item name.
* desc : String value of item description.
* category : String value of item category.
* price : double value of item price.
* itemImg : reference link of item image.
```sh
example: POST /item/insert?tokenId=value&name=value&desc=value&category=value&price=value&itemImg=value
```
Will return information about item
```sh
response body:
{
    "id": "d94d5b3dd26588a79e75fefb6afe897bf7****",
    "name": "name of the item",
    "description": "item description",
    "category": "Storage",
    "price": 2001.99,
    "itemImg": "http://www.item-image.com",
    "owner": {
        "id": "4c32934d7d1aa0a6d1eb8456a3b16c5f****",
        "name": "user or owner name",
        "address": "owner address",
        "profileImg": "owner profile image"
    }
}
```
