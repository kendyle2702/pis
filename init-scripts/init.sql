-- Tạo database 'pis' (nếu chạy từ bên ngoài, bỏ qua nếu chạy trong container)
CREATE DATABASE pis;

-- Chuyển vào database 'pis'
\c pis

-- Tạo bảng 'user' (dùng dấu nháy kép để tránh lỗi vì "user" là từ khóa của PostgreSQL)
CREATE TABLE "user" (
                        user_id SERIAL PRIMARY KEY,
                        username VARCHAR(50) UNIQUE NOT NULL,
                        first_name VARCHAR(50) NOT NULL,
                        last_name VARCHAR(50) NOT NULL,
                        phone_number VARCHAR(20),
                        email VARCHAR(255),
                        avatar VARCHAR(255),
                        qr_code VARCHAR(255),
                        birthday DATE,
                        hash_password VARCHAR(255) NOT NULL,
                        is_active BOOLEAN DEFAULT TRUE,
                        is_login BOOLEAN DEFAULT FALSE,
                        login_attempts INT DEFAULT 0,
                        otp VARCHAR(6)
);

-- Tạo bảng 'friendship'
CREATE TABLE friendship (
                            user_id INT NOT NULL,
                            friend_id INT NOT NULL,
                            friend_type VARCHAR(50) NOT NULL,
                            is_friend BOOLEAN DEFAULT FALSE,
                            is_block BOOLEAN DEFAULT FALSE,
                            PRIMARY KEY (user_id, friend_id, friend_type),
                            FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE,
                            FOREIGN KEY (friend_id) REFERENCES "user"(user_id) ON DELETE CASCADE
);

-- Tạo bảng 'conversation'
CREATE TABLE conversation (
                              conversation_id SERIAL PRIMARY KEY,
                              user1_id INT NOT NULL,
                              user2_id INT NOT NULL,
                              last_time TIMESTAMP,
                              FOREIGN KEY (user1_id) REFERENCES "user"(user_id) ON DELETE CASCADE,
                              FOREIGN KEY (user2_id) REFERENCES "user"(user_id) ON DELETE CASCADE
);

-- Tạo bảng 'message'
CREATE TABLE message (
                         message_id SERIAL PRIMARY KEY,
                         conversation_id INT NOT NULL,
                         sender_id INT NOT NULL,
                         type VARCHAR(50) NOT NULL,
                         url VARCHAR(255),
                         content TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         status VARCHAR(50),
                         FOREIGN KEY (conversation_id) REFERENCES conversation(conversation_id) ON DELETE CASCADE,
                         FOREIGN KEY (sender_id) REFERENCES "user"(user_id) ON DELETE CASCADE
);

-- Tạo bảng 'post'
CREATE TABLE post (
                      post_id SERIAL PRIMARY KEY,
                      user_id INT NOT NULL,
                      type VARCHAR(50) NOT NULL,
                      content TEXT,
                      mode VARCHAR(50) NOT NULL,
                      create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      pinned BOOLEAN,
                      FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE
);

-- Tạo bảng 'image_post'
CREATE TABLE image_post (
                            image_post_id SERIAL PRIMARY KEY,
                            post_id INT,
                            url VARCHAR(255),
                            FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE
);

-- Tạo bảng 'comment'
CREATE TABLE comment (
                         comment_id SERIAL PRIMARY KEY,
                         post_id INT NOT NULL,
                         parent_comment_id INT,
                         user_id INT NOT NULL,
                         type VARCHAR(50) NOT NULL,
                         url VARCHAR(255),
                         content TEXT,
                         create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE,
                         FOREIGN KEY (parent_comment_id) REFERENCES comment(comment_id) ON DELETE CASCADE,
                         FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE
);

-- Tạo bảng 'image_comment'
CREATE TABLE image_comment (
                               image_comment_id SERIAL PRIMARY KEY,
                               comment_id INT,
                               url VARCHAR(255),
                               FOREIGN KEY (comment_id) REFERENCES comment(comment_id) ON DELETE CASCADE
);

-- Tạo bảng 'reaction'
CREATE TABLE reaction (
                          reaction_id SERIAL PRIMARY KEY,
                          user_id INT NOT NULL,
                          post_id INT,
                          comment_id INT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES "user"(user_id) ON DELETE CASCADE,
                          FOREIGN KEY (post_id) REFERENCES post(post_id) ON DELETE CASCADE,
                          FOREIGN KEY (comment_id) REFERENCES comment(comment_id) ON DELETE CASCADE
);

-- Tạo bảng 'invalid_access_token'
CREATE TABLE invalid_access_token (
                                      id SERIAL PRIMARY KEY,
                                      token_uuid VARCHAR(200) NOT NULL,
                                      expiry_time DATE
);
