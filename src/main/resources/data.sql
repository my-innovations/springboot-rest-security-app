insert into `role` (`id`,`role_name`) values
(1,'admin'),
(2,'user');

insert into `user`(`id`,`username`,`password`,`role_id`) values
(1,'punya','$2a$10$Tpy2a.DQlJ4hMWZ3.hHcc.VZRPEUUMKrGeE0B7AwvjOZ.r.BC1m.S',1),
(2,'pankaj','$2a$10$52wL8ErG8McLp0s4IiZYf.Rce9vMCczI.XF0Rpl.ZIUakdROqGtle',2);
