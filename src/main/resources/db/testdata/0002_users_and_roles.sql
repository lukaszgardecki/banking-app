insert into
    users (first_name, last_name, email, password, token, code, verified, verified_at, created_at, updated_at)
values
--     login: k.dobrabula@gmail.com, pass: katarzynapass
    ('Katarzyna', 'Dobrabuła', 'k.dobrabula@gmail.com', '{bcrypt}$2a$10$nf/7JQfaBCJEwvkc98qD3uKpgcMzUdIBeKZff03imcE/LDeXU8Lp2', NULL, NULL, '1', '2023-03-30 13:33:55', '2023-03-30 13:33:32', '2023-03-30 13:33:55'),
--     login: r.wolodyjowski@gmail.com, pass: robertpass
    ('Robert', 'Wołodyjowski', 'r.wolodyjowski@gmail.com', '{bcrypt}$2a$10$1HBiS9LAQWc38MALqLnpEeqRzAB/pI8GHOY2/7kZKX0YoQqveDGFe', NULL, NULL, '1', '2023-03-30 13:41:50', '2023-03-30 13:41:44', '2023-03-30 13:41:50'),
--     login: z.kaminiski@gmail.com, pass: zbigniewpass
    ('Zbigniew', 'Kamiński', 'z.kaminiski@gmail.com', '{bcrypt}$2a$10$VTwtrRs4pMy0Fo/UWQor1u/MrX/Wg/2tO7Pjjbr25ZIrmQys6IpPm', NULL, NULL, '1', '2023-03-30 13:42:37', '2023-03-30 13:42:28', '2023-03-30 13:42:37'),
--     login: j.tron@gmail.com, pass: jacekpass
    ('Jacek', 'Tron', 'j.tron@gmail.com', '{bcrypt}$2a$10$LtClItkaBeSjsboJAA00y.LUA0SyXGQP97R/7njHP2aBieGzn2S4C', NULL, NULL, '1', '2023-03-30 13:43:30', '2023-03-30 13:43:09', '2023-03-30 13:43:30'),
--     login: sz.klawisz@gmail.com, pass: szymonpass
    ('Szymon', 'Klawisz', 'sz.klawisz@gmail.com', '{bcrypt}$2a$10$ekzzW0s2754/H0OqsAhF/uTc6YvYHYf8w.cN6HvOO1N8pRM0EPaYa', NULL, NULL, '1', '2023-03-30 13:44:34', '2023-03-30 13:44:19', '2023-03-30 13:44:34'),
--     login: z.martyniuk@gmail.com, pass: zenekpass
    ('Zenek', 'Martyniuk', 'z.martyniuk@gmail.com', '{bcrypt}$2a$10$dCsXXkYkWqlIQveUvYtdO.J03rcume215ZZJw4Qlbm3GyrxLNTG4q', NULL, NULL, '1', '2023-03-30 13:45:14', '2023-03-30 13:45:08', '2023-03-30 13:45:14');



    insert into
    user_roles (user_id, role_id)
values
    (1, 1),
    (2, 2),
    (3, 2),
    (4, 2),
    (5, 2),
    (6, 2);