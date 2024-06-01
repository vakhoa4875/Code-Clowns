use master
go
drop database if exists [Planny]
go
create database [Planny]
go
use [Planny]
go

----- CREATE TABLE PHASE -----
create table [Account]
(
    account_id int identity primary key,
    username   varchar(63),
    email      varchar(63),
    sub        varchar(63),
    password   varchar(127) not null,
    is_enabled bit default 1,
    constraint unique_account_email_sub unique (email, sub),
    constraint unique_account_username unique (username)
);

create table [User]
(
    user_id    int primary key,
    username   varchar(63) unique,
    email      varchar(63) unique,
    fullname   nvarchar(63),
    avatar     varchar(127),
    dob        date,
    gender     bit default 1,
    is_private bit default 0,
    constraint unique_user_username unique (username),
    constraint unique_user_email unique (email),
    constraint fk_user_account foreign key (user_id) references [Account] (account_id)
);

create table [Workspace]
(
    workspace_id   int identity primary key,
    workspace_name nvarchar(63) not null,
    short_name     varchar(63) unique,
    website        varchar(128),
    description    varchar(255),
    is_enabled     bit default 1,
    user_id        int foreign key references [User] (user_id)
);

create table [Board]
(
    board_id     int identity primary key,
    board_name   nvarchar(63) not null,
    short_name   varchar(63) unique,
    slug_url     varchar(63) unique,
    visibility   nvarchar(63) not null,
    is_enabled   bit default 1,
    workspace_id int foreign key references [Workspace] (workspace_id)
);

create table [List]
(
    list_id         int identity primary key,
    title           nvarchar(63) not null,
    ordinal_numeral int          not null,
    is_enabled      bit default 1,
    board_id        int foreign key references [Board] (board_id)
);

create table [Card]
(
    card_id      int identity primary key,
    title        nvarchar(128) not null,
    description  nvarchar(255),
    cover        varchar(128),
    start_date   datetime,
    due_date     datetime,
    is_completed bit default 0,
    short_name   varchar(63) unique,
    slug_url     varchar(63) unique,
    is_enabled   bit default 1,
    list_id      int foreign key references [List] (list_id)
);

create table [Collaborator]
(
    collaborator_id int identity primary key,
    role            nvarchar(63) not null,
    username        varchar(63) unique,
    email           varchar(63) unique,
    fullname        nvarchar(63),
    avatar          varchar(127),
    user_id         int foreign key references [User] (user_id),
    workspace_id    int foreign key references [Workspace] (workspace_id)
);

create table [Member]
(
    member_id  int identity primary key,
    role       nvarchar(63) not null,
    username   varchar(63) unique,
    email      varchar(63) unique,
    avatar     varchar(127),
    fullname   nvarchar(63),
    is_enabled bit default 1,
    user_id    int foreign key references [User] (user_id),
    board_id   int foreign key references [Board] (board_id)
);

create table [CardConductor]
(
    id            int identity primary key,
    assigned_time datetime,
    card_id       int foreign key references [Card] (card_id),
    member_id     int foreign key references [Member] (member_id)
);
go
----- /CREATE TABLE PHASE -----

----- CREATE OTHER DBO -----
create or alter trigger newAccountAsNewUser
    on [Account]
    after insert
    as
begin
    insert into [User] (user_id, username, email)
    select i.account_id, i.username, i.email
    from inserted i
end
go
drop trigger if exists newAccountAsNewUser;
CREATE OR ALTER PROCEDURE InsertAccountAndUser
    @username NVARCHAR(63),
    @password NVARCHAR(127),
    @email NVARCHAR(63),
    @sub NVARCHAR(63),
    @isEnabled BIT,
    @fullName NVARCHAR(63)
AS
BEGIN
    -- thêm dlieu vao account
    INSERT INTO [Account] (username, password, email, sub, is_enabled)
    VALUES (@username, @password, @email, @sub, @isEnabled);
    -- gan id vua them vao @accountId
    DECLARE @accountId INT;
    SET @accountId = SCOPE_IDENTITY()
    -- Chèn dữ liệu vào bảng User với accountId vừa thêm
    INSERT INTO [User] (user_id, username, email, fullname)
    VALUES (@accountId, @username, @email, @fullName);
END;
GO
----- /CREATE OTHER DBO -----

----- ALTER LOGIN CREDENTIAL -----
ALTER LOGIN sa WITH PASSWORD = 'root';
