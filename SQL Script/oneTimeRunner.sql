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

create table Card
(
    card_id        int identity
        primary key,
    title          nvarchar(128) not null,
    description    nvarchar(255),
    cover          varchar(128),
    start_date     datetime,
    due_date       datetime,
    is_completed   bit default 0,
    short_name     varchar(63)
        unique,
    slug_url       varchar(63)
        unique,
    is_enabled     bit default 1,
    list_id        int
        references List,
    ordinal_number int
);

create table [Collaborator]
(
    collaborator_id int identity primary key,
    role            nvarchar(63) not null,
    username        varchar(63),
    email           varchar(63),
    fullname        nvarchar(63),
    avatar          varchar(127),
    user_id         int foreign key references [User] (user_id),
    workspace_id    int foreign key references [Workspace] (workspace_id)
);

create table [Member]
(
    member_id  int identity primary key,
    role       nvarchar(63) not null,
    username   varchar(63),
    email      varchar(63),
    avatar     varchar(127),
    fullname   nvarchar(63),
    is_enabled bit default 1,
    user_id    int foreign key references [User] (user_id),
    board_id   int foreign key references [Board] (board_id)
);

create table [Card_Conductor]
(
    id            int identity primary key,
    assigned_time datetime,
    card_id       int foreign key references [Card] (card_id),
    member_id     int foreign key references [Member] (member_id)
);
go
----- /CREATE TABLE PHASE -----

----- CREATE OTHER DBO -----
go
create trigger afterDisableCard
    on Card
    after update
    as
begin
    UPDATE c
    SET c.ordinal_number = -1
    FROM Card c
             INNER JOIN inserted i ON c.card_id = i.card_id
             INNER JOIN deleted d ON c.card_id = d.card_id
    WHERE d.is_enabled = 1
      AND i.is_enabled = 0;
end
go
create or alter trigger afterCreateCollaborator
    on Collaborator
    after insert
    as
begin
    insert into Member (role, username, email, fullname, avatar, user_id, board_id)
    select i.role, i.username, i.email, i.fullname, i.avatar, i.user_id, b.board_id
    from inserted i
             join Board b on i.workspace_id = b.workspace_id
end
go
create or alter trigger afterCreateBoard
    on Board
    after insert
    as
begin
    insert into Member (role, username, email, fullname, avatar, user_id, board_id)
    select 'admin', u.username, u.email, u.fullname, u.avatar, u.user_id, i.board_id
    from inserted i
             join Workspace w on i.workspace_id = w.workspace_id
             join [User] u on u.user_id = w.user_id
end
go
CREATE OR ALTER PROCEDURE InsertAccountAndUser @username NVARCHAR(63),
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

-- INSERT INTO DATA
use Planny;
-- insert into Account (username, email, password) values

-- Insert records into Account table
INSERT INTO [Account] (username, email, sub, password)
VALUES
('user1', 'user1@example.com', 'sub1', 'password1'),
('user2', 'user2@example.com', 'sub2', 'password2'),
('user3', 'user3@example.com', 'sub3', 'password3'),
('user4', 'user4@example.com', 'sub4', 'password4'),
('user5', 'user5@example.com', 'sub5', 'password5'),
('khoadz', 'admin@gmail.com',null, '$2a$10$7NJakCydtyXhhKWgoJp38e3BCgF3jmK7rP5sk2NxK/lsuwBkBKVAG');
GO

-- Insert records into User table
INSERT INTO [User] (user_id, username, email, fullname, avatar, dob, gender, is_private)
VALUES
(1, 'user1', 'user1@example.com', 'User One', 'avatar1.png', '1990-01-01', 1, 0),
(2, 'user2', 'user2@example.com', 'User Two', 'avatar2.png', '1991-02-02', 0, 0),
(3, 'user3', 'user3@example.com', 'User Three', 'avatar3.png', '1992-03-03', 1, 1),
(4, 'user4', 'user4@example.com', 'User Four', 'avatar4.png', '1993-04-04', 0, 0),
(5, 'user5', 'user5@example.com', 'User Five', 'avatar5.png', '1994-05-05', 1, 1),
(6, 'khoadz', 'admin@gmail.com', 'Khoa Vu', null, '2004-05-05', 1, 0);
GO

-- Insert records into Workspace table
INSERT INTO [Workspace] (workspace_name, short_name, website, description, is_enabled, user_id)
VALUES
('Workspace One', 'ws1', 'http://workspace1.com', 'Description for Workspace One', 1, 1),
('Workspace Two', 'ws2', 'http://workspace2.com', 'Description for Workspace Two', 1, 2),
('Workspace Three', 'ws3', 'http://workspace3.com', 'Description for Workspace Three', 1, 3),
('Workspace Four', 'ws4', 'http://workspace4.com', 'Description for Workspace Four', 1, 4),
('Workspace Five', 'ws5', 'http://workspace5.com', 'Description for Workspace Five', 1, 5),
('Code Clowns', 'code-clowns', null, 'Those 5 code clowns', 1, 6);
GO

-- Insert records into Board table
INSERT INTO [Board] (board_name, short_name, slug_url, visibility, is_enabled, workspace_id)
VALUES
('Board One', 'b1', 'board-one', 'Public', 1, 1),
('Board Two', 'b2', 'board-two', 'Private', 1, 2),
('Board Three', 'b3', 'board-three', 'Public', 1, 3),
('Board Four', 'b4', 'board-four', 'Private', 1, 4),
('Board Five', 'b5', 'board-five', 'Public', 1, 5),
('Planny', 'planny', 'planny', 'Public', 1, 6);
GO

-- Insert records into List table
INSERT INTO [List] (title, ordinal_numeral, is_enabled, board_id)
VALUES
('List One', 1, 1, 1),
('List Two', 2, 1, 2),
('List Three', 3, 1, 3),
('List Four', 4, 1, 4),
('List Five', 5, 1, 5),
('To Do', 1, 1, 6),
('Doing', 2, 1, 6),
('Done', 3, 1, 6);
GO

-- Insert records into Card table
INSERT INTO [Card] (title, description, cover, start_date, due_date, is_completed, short_name, slug_url, is_enabled, list_id)
VALUES
('Card One', 'Description for Card One', 'cover1.png', '2023-01-01', '2023-01-31', 0, 'card-one', 'card-one', 1, 1),
('Card Two', 'Description for Card Two', 'cover2.png', '2023-02-01', '2023-02-28', 0, 'card-two', 'card-two', 1, 2),
('Card Three', 'Description for Card Three', 'cover3.png', '2023-03-01', '2023-03-31', 0, 'card-three', 'card-three', 1, 3),
('Card Four', 'Description for Card Four', 'cover4.png', '2023-04-01', '2023-04-30', 0, 'card-four', 'card-four', 1, 4),
('Card Five', 'Description for Card Five', 'cover5.png', '2023-05-01', '2023-05-31', 0, 'card-five', 'card-five', 1, 5);
GO

-- Insert records into Collaborator table
INSERT INTO [Collaborator] (role, username, email, fullname, avatar, user_id, workspace_id)
VALUES
('Role One', 'collab1', 'collab1@example.com', 'Collaborator One', 'avatar1.png', 2, 1),
('Role Two', 'collab2', 'collab2@example.com', 'Collaborator Two', 'avatar2.png', 1, 2),
('Role Three', 'collab3', 'collab3@example.com', 'Collaborator Three', 'avatar3.png', 5, 6),
('Role Four', 'collab4', 'collab4@example.com', 'Collaborator Four', 'avatar4.png', 3, 6),
('Role Five', 'collab5', 'collab5@example.com', 'Collaborator Five', 'avatar5.png', 4, 6);
GO

-- Insert records into Member table
-- INSERT INTO [Member] (role, username, email, avatar, fullname, is_enabled, user_id, board_id)
-- VALUES
-- ('Member Role One', 'member1', 'member1@example.com', 'avatar1.png', 'Member One', 1, 1, 1),
-- ('Member Role Two', 'member2', 'member2@example.com', 'avatar2.png', 'Member Two', 1, 2, 2),
-- ('Member Role Three', 'member3', 'member3@example.com', 'avatar3.png', 'Member Three', 1, 3, 3),
-- ('Member Role Four', 'member4', 'member4@example.com', 'avatar4.png', 'Member Four', 1, 4, 4),
-- ('Member Role Five', 'member5', 'member5@example.com', 'avatar5.png', 'Member Five', 1, 5, 5);
-- GO

-- Insert records into Card_Conductor table
INSERT INTO [Card_Conductor] (assigned_time, card_id, member_id)
VALUES
(GETDATE(), 1, 1),
(GETDATE(), 2, 2),
(GETDATE(), 3, 3),
(GETDATE(), 4, 4),
(GETDATE(), 5, 5);
GO
select * from Workspace