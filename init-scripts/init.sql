CREATE DATABASE pis;
GO

USE pis;
GO

CREATE TABLE [user] (
    user_id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50) NOT NULL UNIQUE,
    first_name NVARCHAR(50) NOT NULL,
    last_name NVARCHAR(50) NOT NULL,
    phone_number VARCHAR(20),
    email NVARCHAR(255),
    avatar NVARCHAR(255),
    qr_code NVARCHAR(255),
    birthday DATE,
    hash_password NVARCHAR(255) NOT NULL,
    is_active BIT DEFAULT 1,
    is_login BIT DEFAULT 0,
    login_attempts INT DEFAULT 0,
    otp VARCHAR(6)
);

CREATE TABLE friendship (
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    friend_type NVARCHAR(50) NOT NULL,
    is_friend BIT DEFAULT 0,
    is_block BIT DEFAULT 0,
    PRIMARY KEY (user_id, friend_id, friend_type),
    FOREIGN KEY (user_id) REFERENCES [user](user_id),
    FOREIGN KEY (friend_id) REFERENCES [user](user_id)
);

CREATE TABLE conversation (
    conversation_id INT PRIMARY KEY IDENTITY(1,1),
    user1_id INT NOT NULL,
    user2_id INT NOT NULL,
    last_time DATETIME,
    FOREIGN KEY (user1_id) REFERENCES [user](user_id),
    FOREIGN KEY (user2_id) REFERENCES [user](user_id)
);


CREATE TABLE message (
    message_id INT PRIMARY KEY IDENTITY(1,1),
    conversation_id INT NOT NULL,
    sender_id INT NOT NULL,
    type NVARCHAR(50) NOT NULL,
    url NVARCHAR(255),
    content NVARCHAR(MAX) NOT NULL,
    created_at DATETIME DEFAULT GETDATE(),
    status NVARCHAR(50),
    FOREIGN KEY (conversation_id) REFERENCES conversation(conversation_id),
    FOREIGN KEY (sender_id) REFERENCES [user](user_id)
);


CREATE TABLE post (
    post_id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT NOT NULL,
    type NVARCHAR(50) NOT NULL,
    content NVARCHAR(MAX),
    mode NVARCHAR(50) NOT NULL,
    create_at DATETIME DEFAULT GETDATE(),
    pinned BIT,
    FOREIGN KEY (user_id) REFERENCES [user](user_id)
);

CREATE TABLE image_post (
		image_post_id INT PRIMARY KEY IDENTITY(1,1),
    post_id INT,
    url NVARCHAR(255),
    FOREIGN KEY (post_id) REFERENCES [post](post_id)
);


CREATE TABLE comment (
    comment_id INT PRIMARY KEY IDENTITY(1,1),
    post_id INT NOT NULL,
    parent_comment_id INT,
    user_id INT NOT NULL,
    type NVARCHAR(50) NOT NULL,
    url NVARCHAR(255),
    content NVARCHAR(MAX),
    create_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (post_id) REFERENCES post(post_id),
    FOREIGN KEY (parent_comment_id) REFERENCES comment(comment_id),
    FOREIGN KEY (user_id) REFERENCES [user](user_id)
);

CREATE TABLE image_comment (
		image_comment_id INT PRIMARY KEY IDENTITY(1,1),
    comment_id INT,
    url NVARCHAR(255),
    FOREIGN KEY (comment_id) REFERENCES [comment](comment_id)
);

CREATE TABLE reaction (
    reaction_id INT PRIMARY KEY IDENTITY(1,1),
    user_id INT NOT NULL,
    post_id INT,
    comment_id INT,
    created_at DATETIME NOT NULL DEFAULT GETDATE(),
    FOREIGN KEY (user_id) REFERENCES [user](user_id),
    FOREIGN KEY (post_id) REFERENCES post(post_id),
    FOREIGN KEY (comment_id) REFERENCES comment(comment_id)
);

CREATE TABLE invalid_access_token	 (
    id INT IDENTITY(1,1) PRIMARY KEY,       
    token_uuid NVARCHAR(200) NOT NULL,           
    expiry_time DATE
);

