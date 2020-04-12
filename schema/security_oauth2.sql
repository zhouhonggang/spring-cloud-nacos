/******************************************/
/*   数据库全名 = spring_cloud_oauth2   */
/*   表名称 = oauth_client_details   */
/******************************************/
create table oauth_client_details
(
    client_id               VARCHAR(128) PRIMARY KEY,
    resource_ids            VARCHAR(128),
    client_secret           VARCHAR(128),
    scope                   VARCHAR(128),
    authorized_grant_types  VARCHAR(128),
    web_server_redirect_uri VARCHAR(128),
    authorities             VARCHAR(128),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(128)
);

/******************************************/
/*   数据库全名 = spring_cloud_oauth2   */
/*   表名称 = oauth_client_token   */
/******************************************/
create table oauth_client_token
(
    token_id          VARCHAR(128),
    token             BLOB,
    authentication_id VARCHAR(128) PRIMARY KEY,
    user_name         VARCHAR(128),
    client_id         VARCHAR(128)
);

/******************************************/
/*   数据库全名 = spring_cloud_oauth2   */
/*   表名称 = oauth_access_token   */
/******************************************/
create table oauth_access_token
(
    token_id          VARCHAR(128),
    token             BLOB,
    authentication_id VARCHAR(128) PRIMARY KEY,
    user_name         VARCHAR(128),
    client_id         VARCHAR(128),
    authentication    BLOB,
    refresh_token     VARCHAR(128)
);

/******************************************/
/*   数据库全名 = spring_cloud_oauth2   */
/*   表名称 = oauth_refresh_token   */
/******************************************/
create table oauth_refresh_token
(
    token_id VARCHAR(128),
    token    BLOB,
    authentication BLOB
);

/******************************************/
/*   数据库全名 = spring_cloud_oauth2   */
/*   表名称 = oauth_code   */
/******************************************/
create table oauth_code
(
    code           VARCHAR(128),
    authentication BLOB
);

/******************************************/
/*   数据库全名 = spring_cloud_oauth2   */
/*   表名称 = oauth_approvals   */
/******************************************/
create table oauth_approvals
(
    userId         VARCHAR(128),
    clientId       VARCHAR(128),
    scope          VARCHAR(128),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

/******************************************/
/*   数据库全名 = spring_cloud_oauth2   */
/*   表名称 = ClientDetails   */
/******************************************/
create table ClientDetails
(
    appId                  VARCHAR(128) PRIMARY KEY,
    resourceIds            VARCHAR(128),
    appSecret              VARCHAR(128),
    scope                  VARCHAR(128),
    grantTypes             VARCHAR(128),
    redirectUrl            VARCHAR(128),
    authorities            VARCHAR(128),
    access_token_validity  INTEGER,
    refresh_token_validity INTEGER,
    additionalInformation  VARCHAR(4096),
    autoApproveScopes      VARCHAR(128)
);