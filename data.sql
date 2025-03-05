INSERT INTO [user] (username, first_name, last_name, phone_number, email, avatar, qr_code, birthday, hash_password, is_active, is_login, login_attempts, otp) VALUES
(N'lequoccu2003', N'Cư', N'Lê Quốc', null, N'lequoccu2003@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/0c157c72-30bc-40f9-83a9-97ea3c536984-2b723c838ccf32916bde.jpg', null, null, N'$2a$10$QCgk0Md2fpjOnCqTskcvIONmtaOFT5yxJOxlDUqBUiXTq7TNfiQoO', 1, null, 0, null),
(N'trungtien1910', N'Tiến', N'Bạch Trung', null, N'trungtien1910@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/5779e01c-dd11-42da-b100-7ee000d81858-2de1f0504c1cf242ab0d.jpg', null, null, N'$2a$10$ElrI9IgPCcUS0KM0YIzOdeM7hAldJztZRmoj2gnorzNgE6nsBy3z6', 1, null, 0, null),
(N'tuyetngan02112003', N'Ngân', N'Tuyết', null, N'tuyetngan02112003@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/58a6bacf-9f33-4023-a9c1-2785d498724b-3eb0fb1e4f52f10ca843.jpg', null, null, N'$2a$10$GcuWg9q5A4NIr3RrExGJReuqcnGjMIO9Y.EGtgSyVDQU4gJu6v.3K', 1, null, 0, null),
(N'nguyenngocphiyen2003m', N'Yến', N'Phi', null, N'nguyenngocphiyen2003m@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/db01bc94-599f-4325-bc83-dc82348bdf6c-050863c7d28b6cd5359a.jpg', null, null, N'$2a$10$haNBvMxqEnYQEUVAFOGXGOUZWHEuhQZ/n8bUExndAm6osvzkZjlK.', 1, null, 0, null),
(N'trungkhoi', N'Khởi', N'Trung', null, N'trungkhoi@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/db803f75-5cdf-4d80-bffb-1cc02f827f97-3033327985353b6b6224.jpg', null, null, N'$2a$10$wtgWRL7AO0Fv42nqudczWOl6bPsTKivOkQKT6UF5qm6mT37cZntG.', 1, null, 0, null),
(N'thienan', N'Ân', N'Thiên', null, N'thienan@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/6bd28413-e56f-47dd-b40a-58c14f1a6737-19ec2e519c1d22437b0c.jpg', null, null, N'$2a$10$AeCd33fvpGI.M6d6WvxeT.cbIsp54QCmmEihnt6hOVbHM3y17rglS', 1, null, 0, null),
(N'honghai', N'Hải', N'Hồng', null, N'honghai@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/fe3e7dc0-0ce2-46b9-89fa-98e58758f0df-6c67b427246b9a35c37a.jpg', null, null, N'$2a$10$1Kas1DdYAD6S6iM5dAhQmus3GsVvIhFTJep9.t0zB.5WuxVolw4gm', 1, null, 0, null),
(N'huubang', N'Bằng', N'Hữu', null, N'huubang@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/2a3a1ca0-ba7d-4180-aa89-cc5369fe7d7a-6758ac0a3b468518dc57.jpg', null, null, N'$2a$10$Nm9IYb26muYBQZ6xNC2oU.Dr0HyfraLjbrOO1ZmuycNZs7wF.TOwi', 1, null, 0, null),
(N'vietnguyen', N'Nguyên', N'Việt', null, N'vietnguyen@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/278a4fe1-ecce-415a-8580-00c1a7c8060a-f430b9550c19b247eb08.jpg', null, null, N'$2a$10$ADQhXF1NyqlFMwKWe4wncOo5ECaY3fBuiYcpQLP2sHsLv/yUNk1Wa', 1, null, 0, null),
(N'trungtuong', N'Tường', N'Trung', null, N'trungtuong@gmail.com', N'https://pisnewcontainer.blob.core.windows.net/image/3a51ccc2-1a74-442d-85c9-de3e0cdba438-014f926a0426ba78e337.jpg', null, null, N'$2a$10$oinIF2vf7cT7a2kxhAe14OOBwUEkRRLcFSu3ptyMa1m9YI8YiAf2y', 1, null, 0, null);



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
(1, N'Image', 'Hôm nay là một ngày tuyệt vời!', N'Public', '2025-01-28 02:02:37', 0),
(2, N'Image', N'Chia sẻ một bức ảnh đẹp từ chuyến du lịch.', N'Public', '2025-02-06 01:00:21', 0),
(3, N'Voice', N'Cùng nghe voice này nhé!', N'Public', '2025-02-06 18:39:23', 0),
(4, N'Image', N'Một ngày đầy cảm hứng với công việc.', N'Public', '2025-01-22 04:25:23', 0),
(5, N'Image', N'Chú cún này quá dễ thương', N'Public', '2025-02-12 23:25:23', 0),
(6, N'Image', N'Bữa ăn thật là ngon', N'Public', '2025-02-12 22:25:23', 0),
(7, N'Image', N'Nay ra đường toàn gặp chuyện xui hà', N'Public', '2025-02-09 10:25:23', 0);

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

(7, N'https://s3-sgn10.fptcloud.com/teky-prod/teky-edu-vn/media/document_file/2023/9/12/crC5nRMvNDVclzPW_202391214297.jpg');





INSERT INTO comment (post_id, parent_comment_id, user_id, type, url, content, create_at) VALUES
(1, null, 2, N'Text',null,N'Đẹp quá đi!', '2025-01-28 03:03:37'),
(1, null, 3, N'Image','https://pisnewcontainer.blob.core.windows.net/image/2024_2_28_638447132490658657_anh-bia-bau-troi.jpg',N'Chỗ tui cũng oke nè', '2025-01-29 03:03:37'),


(2, null, 4, N'Text',null,N'Muốn đi chơi quá đi', '2025-02-06 01:15:21'),
(2, null, 7, N'Text',null,N'Đẹp quá đi!', '2025-01-28 03:03:37'),

(3, null, 9, N'Text',null,N'Giọng hay quá!', '2025-02-06 19:39:23');

INSERT INTO comment (post_id, parent_comment_id, user_id, type, url, content, create_at) VALUES
(1, 2, 1, N'Text', null,N'Đẹp dạ ta', '2025-01-29 03:05:37');



INSERT INTO reaction (user_id, post_id, comment_id, created_at) VALUES
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
