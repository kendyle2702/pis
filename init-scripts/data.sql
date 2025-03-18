\c pis;

INSERT INTO "user" (username, first_name, last_name, phone_number, email, avatar, qr_code, birthday, hash_password, is_active, is_login, login_attempts, otp) VALUES
                                                                                                                                                                  ('lequoccu2003', 'Cư', 'Lê Quốc', NULL, 'lequoccu2003@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/0c157c72-30bc-40f9-83a9-97ea3c536984-2b723c838ccf32916bde.jpg', NULL, NULL, '$2a$10$QCgk0Md2fpjOnCqTskcvIONmtaOFT5yxJOxlDUqBUiXTq7TNfiQoO', TRUE, NULL, 0, NULL),
                                                                                                                                                                  ('trungtien1910', 'Tiến', 'Bạch Trung', NULL, 'trungtien1910@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/5779e01c-dd11-42da-b100-7ee000d81858-2de1f0504c1cf242ab0d.jpg', NULL, NULL, '$2a$10$ElrI9IgPCcUS0KM0YIzOdeM7hAldJztZRmoj2gnorzNgE6nsBy3z6', TRUE, NULL, 0, NULL),
                                                                                                                                                                  ('tuyetngan02112003', 'Ngân', 'Tuyết', NULL, 'tuyetngan02112003@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/58a6bacf-9f33-4023-a9c1-2785d498724b-3eb0fb1e4f52f10ca843.jpg', NULL, NULL, '$2a$10$GcuWg9q5A4NIr3RrExGJReuqcnGjMIO9Y.EGtgSyVDQU4gJu6v.3K', TRUE, NULL, 0, NULL),
                                                                                                                                                                  ('nguyenngocphiyen2003m', 'Yến', 'Phi', NULL, 'nguyenngocphiyen2003m@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/db01bc94-599f-4325-bc83-dc82348bdf6c-050863c7d28b6cd5359a.jpg', NULL, NULL, '$2a$10$haNBvMxqEnYQEUVAFOGXGOUZWHEuhQZ/n8bUExndAm6osvzkZjlK.', TRUE, NULL, 0, NULL),
                                                                                                                                                                  ('trungkhoi', 'Khởi', 'Trung', NULL, 'trungkhoi@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/db803f75-5cdf-4d80-bffb-1cc02f827f97-3033327985353b6b6224.jpg', NULL, NULL, '$2a$10$wtgWRL7AO0Fv42nqudczWOl6bPsTKivOkQKT6UF5qm6mT37cZntG.', TRUE, NULL, 0, NULL),
                                                                                                                                                                  ('thienan', 'Ân', 'Thiên', NULL, 'thienan@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/6bd28413-e56f-47dd-b40a-58c14f1a6737-19ec2e519c1d22437b0c.jpg', NULL, NULL, '$2a$10$AeCd33fvpGI.M6d6WvxeT.cbIsp54QCmmEihnt6hOVbHM3y17rglS', TRUE, NULL, 0, NULL),
                                                                                                                                                                  ('honghai', 'Hải', 'Hồng', NULL, 'honghai@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/fe3e7dc0-0ce2-46b9-89fa-98e58758f0df-6c67b427246b9a35c37a.jpg', NULL, NULL, '$2a$10$1Kas1DdYAD6S6iM5dAhQmus3GsVvIhFTJep9.t0zB.5WuxVolw4gm', TRUE, NULL, 0, NULL),
                                                                                                                                                                  ('huubang', 'Bằng', 'Hữu', NULL, 'huubang@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/2a3a1ca0-ba7d-4180-aa89-cc5369fe7d7a-6758ac0a3b468518dc57.jpg', NULL, NULL, '$2a$10$Nm9IYb26muYBQZ6xNC2oU.Dr0HyfraLjbrOO1ZmuycNZs7wF.TOwi', TRUE, NULL, 0, NULL),
                                                                                                                                                                  ('vietnguyen', 'Nguyên', 'Việt', NULL, 'vietnguyen@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/278a4fe1-ecce-415a-8580-00c1a7c8060a-f430b9550c19b247eb08.jpg', NULL, NULL, '$2a$10$ADQhXF1NyqlFMwKWe4wncOo5ECaY3fBuiYcpQLP2sHsLv/yUNk1Wa', TRUE, NULL, 0, NULL),
                                                                                                                                                                  ('trungtuong', 'Tường', 'Trung', NULL, 'trungtuong@gmail.com', 'https://pisnewcontainer.blob.core.windows.net/image/3a51ccc2-1a74-442d-85c9-de3e0cdba438-014f926a0426ba78e337.jpg', NULL, NULL, '$2a$10$oinIF2vf7cT7a2kxhAe14OOBwUEkRRLcFSu3ptyMa1m9YI8YiAf2y', TRUE, NULL, 0, NULL);




INSERT INTO friendship (user_id, friend_id, friend_type, is_friend, is_block) VALUES
                                                                                  (1, 2, 'FOLLOW', FALSE, FALSE),
                                                                                  (1, 2, 'FRIEND', TRUE, FALSE),
                                                                                  (1, 3, 'FRIEND', TRUE, FALSE),
                                                                                  (1, 4, 'FOLLOW', FALSE, FALSE),
                                                                                  (1, 5, 'FRIEND', TRUE, FALSE),
                                                                                  (1, 6, 'FOLLOW', FALSE, FALSE),
                                                                                  (1, 7, 'FRIEND', TRUE, FALSE),
                                                                                  (1, 8, 'FOLLOW', FALSE, FALSE),
                                                                                  (1, 9, 'FRIEND', TRUE, FALSE),

                                                                                  (2, 1, 'FRIEND', TRUE, FALSE),
                                                                                  (2, 3, 'FRIEND', TRUE, FALSE),
                                                                                  (2, 4, 'FOLLOW', FALSE, FALSE),
                                                                                  (2, 6, 'FOLLOW', FALSE, FALSE),
                                                                                  (2, 7, 'FRIEND', TRUE, FALSE),
                                                                                  (2, 8, 'FOLLOW', FALSE, FALSE),

                                                                                  (3, 1, 'FRIEND', TRUE, FALSE),
                                                                                  (3, 2, 'FRIEND', TRUE, FALSE),

                                                                                  (5, 1, 'FRIEND', TRUE, FALSE),

                                                                                  (7, 1, 'FRIEND', TRUE, FALSE),
                                                                                  (7, 2, 'FRIEND', TRUE, FALSE),

                                                                                  (9, 1, 'FRIEND', TRUE, FALSE);




INSERT INTO post (user_id, type, content, mode, create_at, pinned) VALUES
                                                                       (1, 'Image', 'Hôm nay là một ngày tuyệt vời!', 'Public', '2025-01-28 02:02:37', FALSE),
                                                                       (2, 'Image', 'Chia sẻ một bức ảnh đẹp từ chuyến du lịch.', 'Public', '2025-02-06 01:00:21', FALSE),
                                                                       (3, 'Voice', 'Cùng nghe voice này nhé!', 'Public', '2025-02-06 18:39:23', FALSE),
                                                                       (4, 'Image', 'Một ngày đầy cảm hứng với công việc.', 'Public', '2025-01-22 04:25:23', FALSE),
                                                                       (5, 'Image', 'Chú cún này quá dễ thương', 'Public', '2025-02-12 23:25:23', FALSE),
                                                                       (6, 'Image', 'Bữa ăn thật là ngon', 'Public', '2025-02-12 22:25:23', FALSE),
                                                                       (7, 'Image', 'Nay ra đường toàn gặp chuyện xui hà', 'Public', '2025-02-09 10:25:23', FALSE);


INSERT INTO image_post (post_id, url) VALUES
                                          (1, 'https://pisnewcontainer.blob.core.windows.net/image/3be0d91ae67796cfc37a6b17f13944e0.jpg'),
                                          (1, 'https://pisnewcontainer.blob.core.windows.net/image/3be0d91ae67796cfc37a6b17f13944e0.jpg'),
                                          (1, 'https://pisnewcontainer.blob.core.windows.net/image/hinh-nen-bau-troi-thump.jpg'),

                                          (2, 'https://pisnewcontainer.blob.core.windows.net/image/hinh-anh-cac-loai-hinh-du-lich-3-large.jpg'),
                                          (2, 'https://pisnewcontainer.blob.core.windows.net/image/hinh1.jpg'),

                                          (3, 'https://pisnewcontainer.blob.core.windows.net/image/Voice 026.m4a'),

                                          (4, 'https://pisnewcontainer.blob.core.windows.net/image/hinh-anh-met-moi-kiet-suc-12.jpg'),

                                          (5, 'https://m.yodycdn.com/blog/anh-cho-meme-yody-vn53.jpg'),
                                          (5, 'https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/05/anh-cho-hai-1.jpg'),
                                          (5, 'https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/05/anh-cho-hai-74.jpg'),

                                          (6, 'https://photo.znews.vn/w660/Uploaded/ryksdreyxq/2019_08_06/borecole.jpg'),
                                          (6, 'https://media.nghean24h.vn/thumb_x500x/2022/5/25/27/bua-com-cua-sinh-vien-2-1653440427.jpg'),

                                          (7, 'https://s3-sgn10.fptcloud.com/teky-prod/teky-edu-vn/media/document_file/2023/9/12/crC5nRMvNDVclzPW_202391214297.jpg');






INSERT INTO comment (post_id, parent_comment_id, user_id, type, url, content, create_at) VALUES
                                                                                             (1, NULL, 2, 'Text', NULL, 'Đẹp quá đi!', '2025-01-28 03:03:37'),
                                                                                             (1, NULL, 3, 'Image', 'https://pisnewcontainer.blob.core.windows.net/image/2024_2_28_638447132490658657_anh-bia-bau-troi.jpg', 'Chỗ tui cũng oke nè', '2025-01-29 03:03:37'),

                                                                                             (2, NULL, 4, 'Text', NULL, 'Muốn đi chơi quá đi', '2025-02-06 01:15:21'),
                                                                                             (2, NULL, 7, 'Text', NULL, 'Đẹp quá đi!', '2025-01-28 03:03:37'),

                                                                                             (3, NULL, 9, 'Text', NULL, 'Giọng hay quá!', '2025-02-06 19:39:23');


INSERT INTO comment (post_id, parent_comment_id, user_id, type, url, content, create_at) VALUES
    (1, 2, 1, 'Text', NULL, 'Đẹp dạ ta', '2025-01-29 03:05:37');




INSERT INTO reaction (user_id, post_id, comment_id, created_at) VALUES
                                                                    (1, 1, NULL, '2025-01-28 02:03:37'),
                                                                    (2, 1, NULL, '2025-01-28 02:05:37'),
                                                                    (3, 1, NULL, '2025-01-28 03:02:37'),
                                                                    (1, NULL, 2, '2025-01-29 03:05:37'),

                                                                    (7, 2, NULL, '2025-02-06 01:05:21'),
                                                                    (2, 2, NULL, '2025-02-06 01:15:21'),

                                                                    (8, 3, NULL, '2025-02-06 18:45:23'),

                                                                    (10, 4, NULL, '2025-01-22 04:30:23');




INSERT INTO conversation (user1_id, user2_id, last_time) VALUES
                                                             (1, 2, '2025-02-12 10:09:23'),
                                                             (1, 3, '2025-02-11 09:02:23'),
                                                             (1, 5, '2025-02-05 07:20:02'),
                                                             (2, 3, '2025-02-12 08:35:23');




INSERT INTO message (conversation_id, sender_id, type, url, content, created_at, status) VALUES
                                                                                             (1, 1, 'Text', NULL, 'Cậu làm xong báo cáo chưa?', '2025-02-12 10:00:23', 'SEEN'),
                                                                                             (1, 2, 'Text', NULL, 'Dạ sếp, em làm gần xong rồi ạ', '2025-02-12 10:01:23', 'SEEN'),
                                                                                             (1, 1, 'Text', NULL, 'Sáng nay tôi nhớ bảo xong trước 10h mà?', '2025-02-12 10:05:23', 'SEEN'),
                                                                                             (1, 2, 'Text', NULL, 'Dạ... 10h nào ạ? Hôm nay hay tháng sau?', '2025-02-12 10:07:23', 'SEEN'),
                                                                                             (1, 1, 'Text', NULL, 'Nhanh lên nha!!!', '2025-02-12 10:08:23', 'SEEN'),
                                                                                             (1, 2, 'Text', NULL, 'Dạaaaaa', '2025-02-12 10:09:23', 'NOT SEEN'),

                                                                                             (2, 1, 'Image', 'https://pisnewcontainer.blob.core.windows.net/image/hinh-nen-bau-troi-thump.jpg', '', '2025-02-11 09:00:23', 'SEEN'),
                                                                                             (2, 1, 'Text', NULL, 'Em thấy ảnh này đẹp hông?', '2025-02-11 09:01:23', 'SEEN'),
                                                                                             (2, 3, 'Text', NULL, 'Quá đẹp luôn!', '2025-02-11 09:02:23', 'SEEN'),

                                                                                             (3, 1, 'Voice', 'https://pisnewcontainer.blob.core.windows.net/image/Voice 026.m4a', '', '2025-02-05 07:15:23', 'SEEN'),
                                                                                             (3, 5, 'Text', NULL, 'Đồ bê đê!!!', '2025-02-05 07:20:23', 'SEEN'),

                                                                                             (4, 2, 'Text', NULL, 'Chỗ cũ nha ní', '2025-02-12 08:32:23', 'SEEN'),
                                                                                             (4, 3, 'Text', NULL, 'oke bạn luôn', '2025-02-12 08:35:23', 'SEEN');

