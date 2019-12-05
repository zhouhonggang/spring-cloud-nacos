1.http://localhost:8030/oauth/authorize?client_id=client&response_type=code
    1.1: 跳转至登陆页面 输入数据库账号密码
    1.2: 选择Approve确认授权
    1.3: 跳转至数据库 redirect_uri 地址, 并携带code参数
    1.4: 复制 code 参数
    
2. http://client:secret@localhost:8030/oauth/token
    2.1: postman
        Body x-www-form-urlencoded
    2.2: 参数 
        grant_type = authorization_code
        code = NPMc9w(1.4生成的)
    2.3:
        {
            "access_token": "431fd462-b98d-4fbc-b69f-f66d0be96c75",
            "token_type": "bearer",
            "expires_in": 43199,
            "scope": "app"
        }
