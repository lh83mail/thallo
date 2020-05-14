##

## 授权模式
### 授权码 Authorization Code

发起请求
```
GET http://{{host}}/oauth/authorize
    ?response_type=code&client_id={{clientid}}&client_secret={{clientsecret}}
    &redirect_uri={{redirect_uri}}
```

换取令牌
```
POST http://{{host}}/oauth/token
Content-Type: application/x-www-form-urlencoded
Authorization: Basic acme acmesecret

grant_type=authorization_code&redirect_uri={{redirect_uri}}&code=v8j8vT
```

### 隐式许可 Implicit
发起请求
```
GET http://{{host}}/oauth/authorize
    ?response_type=token&client_id={{clientid}}&client_secret={{clientsecret}}
    &redirect_uri={{redirect_uri}}
```


### 资源所有者密码凭证 Resource Owner Password Credentials
Not support


### 客户端凭证 Client Credentials
请求客户端令牌
```
POST http://{{host}}/oauth/token
Authorization: Basic acme acmesecret
Content-Type: application/x-www-form-urlencoded

grant_type=client_credentials
```