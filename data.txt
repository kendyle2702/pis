/register
{
    "email": "lequoccu2003@gmail.com",
    "password": "12345",
    "firstName": "Cư",
    "lastName": "Quốc"
}

{
    "email": "trungtien1910@gmail.com",
    "password": "12345",
    "firstName": "Tiến",
    "lastName": "Trung"
}


{
    "email": "tuyetngan02112003@gmail.com",
    "password": "12345",
    "firstName": "Ngân",
    "lastName": "Tuyết"
}

{
    "email": "nguyenngocphiyen2003m@gmail.com",
    "password": "12345",
    "firstName": "Yến",
    "lastName": "Phi"
}


{
    "email": "trungkhoi@gmail.com",
    "password": "12345",
    "firstName": "Khởi",
    "lastName": "Trung"
}

{
    "email": "thienan@gmail.com",
    "password": "12345",
    "firstName": "Ân",
    "lastName": "Thiên"
}

{
    "email": "honghai@gmail.com",
    "password": "12345",
    "firstName": "Hải",
    "lastName": "Hồng"
}

{
    "email": "huubang@gmail.com",
    "password": "12345",
    "firstName": "Bằng",
    "lastName": "Hữu"
}

{
    "email": "vietnguyen@gmail.com",
    "password": "12345",
    "firstName": "Nguyên",
    "lastName": "Việt"
}

{
    "email": "trungtuong@gmail.com",
    "password": "12345",
    "firstName": "Tường",
    "lastName": "Trung"
}








INSERT INTO friendship (user_id, friend_id, friend_type, is_friend, is_block) VALUES
(1, 2, N'FOLLOW', 0, 0),
(1, 2, N'FRIEND', 1, 0),
(1, 3, N'FRIEND', 1, 0),
(1, 4, N'FOLLOW', 0, 0),
(1, 5, N'FRIEND', 1, 0),
(1, 6, N'FOLLOW', 0, 0),
(1, 7, N'FRIEND', 1, 0),
(1, 8, N'FOLLOW', 0, 0),
(1, 9, N'FRIEND', 1, 0),

(2, 1, N'FRIEND', 1, 0),
(2, 3, N'FRIEND', 1, 0),
(2, 4, N'FOLLOW', 0, 0),
(2, 6, N'FOLLOW', 0, 0),
(2, 7, N'FRIEND', 1, 0),
(2, 8, N'FOLLOW', 0, 0),

(3, 1, N'FRIEND', 1, 0),
(3, 2, N'FRIEND', 1, 0),

(5, 1, N'FRIEND', 1, 0),

(7, 1, N'FRIEND', 1, 0),
(7, 2, N'FRIEND', 1, 0),

(9, 1, N'FRIEND', 1, 0);


INSERT INTO post (user_id, type, content, mode, create_at, pinned) VALUES
(1, N'Image', N'Hôm nay là một ngày tuyệt vời!', N'Public', '2025-01-28 02:02:37', 0),
(2, N'Image', N'Chia sẻ một bức ảnh đẹp từ chuyến du lịch.', N'Public', '2025-02-06 01:00:21', 0),
(3, N'Voice', N'Cùng nghe voice này nhé!', N'Public', '2025-02-06 18:39:23', 0),
(4, N'Image', N'Một ngày đầy cảm hứng với công việc.', N'Public', '2025-01-22 04:25:23', 0)
(5, N'Image', N'Chú cún này quá dễ thương', N'Public', '2025-02-12 23:25:23', 0),
(6, N'Image', N'Bữa ăn thật là ngon', N'Public', '2025-02-12 22:25:23', 0)
(7, N'Image', N'Nay ra đường toàn gặp chuyện xui hà', N'Public', '2025-02-09 10:25:23', 0)
;

INSERT INTO image_post (post_id, url) VALUES
(1, N'https://pisnewcontainer.blob.core.windows.net/image/3be0d91ae67796cfc37a6b17f13944e0.jpg'),
(1, N'https://pisnewcontainer.blob.core.windows.net/image/3be0d91ae67796cfc37a6b17f13944e0.jpg'),
(1, N'https://pisnewcontainer.blob.core.windows.net/image/hinh-nen-bau-troi-thump.jpg'),

(2, N'https://pisnewcontainer.blob.core.windows.net/image/hinh-anh-cac-loai-hinh-du-lich-3-large.jpg'),
(2, N'https://pisnewcontainer.blob.core.windows.net/image/hinh1.jpg'),

(3, N'https://pisnewcontainer.blob.core.windows.net/image/Voice 026.m4a'),

(4, N'https://pisnewcontainer.blob.core.windows.net/image/hinh-anh-met-moi-kiet-suc-12.jpg'),

(5, N'https://m.yodycdn.com/blog/anh-cho-meme-yody-vn53.jpg'),
(5, N'https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/05/anh-cho-hai-1.jpg'),
(5, N'https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/05/anh-cho-hai-74.jpg'),


(6, N'https://photo.znews.vn/w660/Uploaded/ryksdreyxq/2019_08_06/borecole.jpg'),
(6, N'https://media.nghean24h.vn/thumb_x500x/2022/5/25/27/bua-com-cua-sinh-vien-2-1653440427.jpg'),

(7, N'https://s3-sgn10.fptcloud.com/teky-prod/teky-edu-vn/media/document_file/2023/9/12/crC5nRMvNDVclzPW_202391214297.jpg'),;




INSERT INTO comment (post_id, parent_comment_id, user_id, type, url, content, create_at) VALUES
(1, null, 2, N'Text',null,'Đẹp quá đi!', '2025-01-28 03:03:37'),
(1, null, 3, N'Image','https://pisnewcontainer.blob.core.windows.net/image/2024_2_28_638447132490658657_anh-bia-bau-troi.jpg','Chỗ tui cũng oke nè', '2025-01-29 03:03:37'),


(2, null, 4, N'Text',null,'Muốn đi chơi quá đi', '2025-02-06 01:15:21'),
(2, null, 7, N'Text',null,'Đẹp quá đi!', '2025-01-28 03:03:37'),

(3, null, 9, N'Text',null,'Giọng hay quá!', '2025-02-06 19:39:23');

INSERT INTO comment (post_id, parent_comment_id, user_id, type, url, content, create_at) VALUES
(1, 2, 1, N'Text', null,'Đẹp dạ ta', '2025-01-29 03:05:37');



INSERT INTO reaction (user_id, post_id, comment_id, create_at) VALUES
(1, 1, null, null),
(2, 1, null, null),
(3, 1, null, null),
(1, null, 2, null),

(7, 2, null, null),
(2, 2, null, null),


(8, 3, null, null),	


(10, 4, null, null);



INSERT INTO conversation (user1_id, user2_id, last_time) VALUES
(1,2,'2025-02-12 10:9:23'),
(1,3,'2025-02-11 9:02:23'),
(1,5,'2025-02-5 7:20:2'),
(2,3,'2025-02-12 8:35:23');

INSERT INTO message (conversation_id, sender_id, type, url, content, created_at, status) VALUES
(1,1,N'Text',null,N'Cậu làm xong báo cáo chưa?','2025-02-12 10:00:23','SEEN'),
(1,2,N'Text',null,N'Dạ sếp, em làm gần xong rồi ạ','2025-02-12 10:01:23','SEEN'),
(1,1,N'Text',null,N'Sáng nay tôi nhớ bảo xong trước 10h mà?','2025-02-12 10:05:23','SEEN'),
(1,2,N'Text',null,N'Dạ... 10h nào ạ? Hôm nay hay tháng sau?','2025-02-12 10:07:23','SEEN'),
(1,1,N'Text',null,N'Nhanh lên nha!!!','2025-02-12 10:08:23','SEEN'),
(1,2,N'Text',null,N'Dạaaaaa','2025-02-12 10:9:23','NOT SEEN'),

(2,1,N'Image',N'https://pisnewcontainer.blob.core.windows.net/image/hinh-nen-bau-troi-thump.jpg',N'','2025-02-11 9:00:23','SEEN'),
(2,1,N'Text',null,N'Em thấy ảnh này đẹp hông?','2025-02-11 9:01:23','SEEN'),
(2,3,N'Text',null,N'Quá đẹp luôn!','2025-02-11 9:02:23','SEEN'),

(3,1,N'Voice',N'https://pisnewcontainer.blob.core.windows.net/image/Voice 026.m4a',N'','2025-02-5 7:15:23','SEEN'),
(3,5,N'Text',null,N'Đồ bê đê!!!','2025-02-5 7:20:23','SEEN'),


(4,2,N'Text',null,N'Chỗ cũ nha ní','2025-02-12 8:32:23','SEEN'),
(4,3,N'Text',null,N'oke bạn luôn','2025-02-12 8:35:23','SEEN');












