create table sys_dept
(
    id         int auto_increment
        primary key,
    pid        int          null,
    name       varchar(255) null,
    open       int          null,
    remark     varchar(255) null,
    address    varchar(255) null,
    available  int          null comment '状态【0不可用1可用】',
    ordernum   int          null comment '排序码【为了调试显示顺序】',
    createtime datetime     null
)
    charset = utf8;

INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (1, 0, '总院', 1, '总院', '深圳', 1, 1, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (2, 1, '门诊部', 0, '门诊部', '武汉', 1, 2, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (3, 1, '药房', 0, '无', '武汉', 1, 3, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (4, 1, '化验室', 0, '无', '武汉', 1, 4, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (5, 2, '内科', 0, '内科', '武汉', 1, 5, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (6, 2, '外科', 0, '外科', '武汉', 1, 6, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (7, 2, '妇科', 0, '妇科', '武汉', 1, 7, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (8, 2, '眼科', 0, '眼科', '11', 1, 8, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (9, 2, '耳鼻喉科', 0, '耳鼻喉科', '222', 1, 9, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (10, 2, '口腔科', 0, '口腔科', '33', 1, 10, '2019-04-10 14:06:32');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (19, 2, '中医科', 0, '中医科', '武汉', 1, 12, '2019-04-13 09:49:38');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (20, 3, '门诊药房', 0, '门诊药房', '武汉', 1, 13, '2019-04-13 09:49:38');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (21, 3, '急诊药房', 0, '急诊药房', '武汉', 1, 14, '2019-04-13 09:49:38');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (22, 3, '住院药房', 0, '住院药房', '武汉', 1, 15, '2019-04-13 09:49:38');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (23, 4, '生化室', 0, '生化室', '武汉', 1, 16, '2019-04-13 09:49:38');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (24, 4, '病理室', 0, '病理室', '武汉', 1, 17, '2019-04-13 09:49:38');
INSERT INTO medmis.sys_dept (id, pid, name, open, remark, address, available, ordernum, createtime) VALUES (25, 4, '检验室', 0, '检验室', '武汉', 1, 18, '2019-04-13 09:49:38');
create table sys_loginfo
(
    id        int auto_increment
        primary key,
    loginname varchar(255) null,
    loginip   varchar(255) null,
    logintime datetime     null
)
    charset = utf8;

INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (14, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-25 14:45:25');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (15, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-25 15:21:52');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (18, '落亦--luoyi', '127.0.0.1', '2019-11-25 15:53:00');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (19, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 08:32:41');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (20, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 09:18:06');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (21, '超级管理员-system', '0:0:0:0:0:0:0:1', '2019-11-26 09:59:19');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (22, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 10:48:05');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (23, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 15:15:03');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (24, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 15:42:02');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (25, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 16:56:54');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (26, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 18:07:44');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (27, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 18:08:08');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (28, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 19:23:12');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (29, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 20:46:57');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (30, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 21:17:48');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (31, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-26 21:21:16');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (32, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-27 20:13:56');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (33, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-27 20:29:17');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (34, '落亦--luoyi', '0:0:0:0:0:0:0:1', '2019-11-27 20:30:38');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (35, '管理员-system', '0:0:0:0:0:0:0:1', '2020-06-02 14:04:41');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (36, '管理员-system', '0:0:0:0:0:0:0:1', '2020-06-02 14:25:07');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (37, '管理员-system', '0:0:0:0:0:0:0:1', '2020-06-02 14:26:50');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (38, '管理员-system', '0:0:0:0:0:0:0:1', '2020-06-02 14:27:28');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (39, '管理员-system', '0:0:0:0:0:0:0:1', '2020-06-02 14:28:43');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (40, '管理员-system', '0:0:0:0:0:0:0:1', '2020-06-02 14:37:27');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (41, '管理员-system', '0:0:0:0:0:0:0:1', '2020-06-02 14:58:05');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (42, '管理员-system', '0:0:0:0:0:0:0:1', '2020-06-02 14:59:20');
INSERT INTO medmis.sys_loginfo (id, loginname, loginip, logintime) VALUES (43, '管理员-system', '0:0:0:0:0:0:0:1', '2020-06-02 16:16:21');
create table sys_notice
(
    id         int auto_increment
        primary key,
    title      varchar(255) null,
    content    text         null,
    createtime datetime     null,
    opername   varchar(255) null
)
    charset = utf8;

INSERT INTO medmis.sys_notice (id, title, content, createtime, opername) VALUES (54, 'afds', 'dasf', '2020-03-08 03:48:47', '落亦-');
INSERT INTO medmis.sys_notice (id, title, content, createtime, opername) VALUES (55, '测试', '测试', '2020-03-08 03:53:03', '落亦-');
INSERT INTO medmis.sys_notice (id, title, content, createtime, opername) VALUES (56, 'sadf', 'asdf', '2020-03-08 04:17:44', '落亦-');
INSERT INTO medmis.sys_notice (id, title, content, createtime, opername) VALUES (57, '1', '123', '2020-05-26 10:40:14', '超级管理员');
create table sys_permission
(
    id        int auto_increment
        primary key,
    pid       int          null,
    type      varchar(255) null comment '权限类型[menu/permission]',
    title     varchar(255) null,
    percode   varchar(255) null comment '权限编码[只有type= permission才有  user:view]',
    icon      varchar(255) null,
    href      varchar(255) null,
    target    varchar(255) null,
    open      int          null,
    ordernum  int          null,
    available int          null comment '状态【0不可用1可用】'
)
    charset = utf8;

INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (1, 0, 'menu', '仓库管理系统', null, '&#xe68e;', '', '', 1, 1, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (2, 1, 'menu', '基础管理', null, '&#xe857;', '', '', 0, 2, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (3, 1, 'menu', '进货管理', null, '&#xe645;', '', null, 0, 3, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (4, 1, 'menu', '销售管理', null, '&#xe611;', '', '', 0, 4, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (5, 1, 'menu', '系统管理', null, '&#xe614;', '', '', 1, 5, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (6, 1, 'menu', '其它管理', null, '&#xe628;', '', '', 0, 6, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (7, 2, 'menu', '客户管理', null, '&#xe651;', '/bus/toCustomerManager', '', 0, 7, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (8, 2, 'menu', '供应商管理', null, '&#xe658;', '/bus/toProviderManager', '', 0, 8, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (9, 2, 'menu', '商品管理', null, '&#xe657;', '/bus/toGoodsManager', '', 0, 9, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (10, 3, 'menu', '商品进货', null, '&#xe756;', '/bus/toInportManager', '', 0, 10, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (11, 3, 'menu', '商品退货查询', null, '&#xe65a;', '/bus/toOutportManager', '', 0, 11, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (12, 4, 'menu', '商品销售', null, '&#xe65b;', '/bus/toSalesManager', '', 0, 12, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (13, 4, 'menu', '销售退货查询', null, '&#xe770;', '/bus/toSalesbackManager', '', 0, 13, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (14, 5, 'menu', '部门管理', null, '&#xe770;', '/sys/toDeptManager', '', 0, 14, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (15, 5, 'menu', '菜单管理', null, '&#xe663;', '/sys/toMenuManager', '', 0, 15, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (16, 5, 'menu', '权限管理', '', '&#xe857;', '/sys/toPermissionManager', '', 0, 16, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (17, 5, 'menu', '角色管理', '', '&#xe650;', '/sys/toRoleManager', '', 0, 17, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (18, 5, 'menu', '用户管理', '', '&#xe612;', '/sys/toUserManager', '', 0, 18, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (21, 6, 'menu', '登陆日志', null, '&#xe675;', '/sys/toLoginfoManager', '', 0, 21, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (22, 6, 'menu', '系统公告', null, '&#xe756;', '/sys/toNoticeManager', null, 0, 22, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (23, 6, 'menu', '图标管理', null, '&#xe670;', '../resources/page/icon.html', null, 0, 23, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (30, 14, 'permission', '添加部门', 'dept:create', '', null, null, 0, 24, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (31, 14, 'permission', '修改部门', 'dept:update', '', null, null, 0, 26, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (32, 14, 'permission', '删除部门', 'dept:delete', '', null, null, 0, 27, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (34, 15, 'permission', '添加菜单', 'menu:create', '', '', '', 0, 29, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (35, 15, 'permission', '修改菜单', 'menu:update', '', null, null, 0, 30, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (36, 15, 'permission', '删除菜单', 'menu:delete', '', null, null, 0, 31, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (38, 16, 'permission', '添加权限', 'permission:create', '', null, null, 0, 33, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (39, 16, 'permission', '修改权限', 'permission:update', '', null, null, 0, 34, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (40, 16, 'permission', '删除权限', 'permission:delete', '', null, null, 0, 35, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (42, 17, 'permission', '添加角色', 'role:create', '', null, null, 0, 37, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (43, 17, 'permission', '修改角色', 'role:update', '', null, null, 0, 38, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (44, 17, 'permission', '删除角色', 'role:delete', '', null, null, 0, 39, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (46, 17, 'permission', '分配权限', 'role:selectPermission', '', null, null, 0, 41, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (47, 18, 'permission', '添加用户', 'user:create', '', null, null, 0, 42, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (48, 18, 'permission', '修改用户', 'user:update', '', null, null, 0, 43, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (49, 18, 'permission', '删除用户', 'user:delete', '', null, null, 0, 44, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (51, 18, 'permission', '用户分配角色', 'user:selectRole', '', null, null, 0, 46, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (52, 18, 'permission', '重置密码', 'user:resetPwd', null, null, null, 0, 47, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (53, 14, 'permission', '部门查询', 'dept:view', null, null, null, 0, 48, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (54, 15, 'permission', '菜单查询', 'menu:view', null, null, null, 0, 49, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (55, 16, 'permission', '权限查询', 'permission:view', null, null, null, 0, 50, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (56, 17, 'permission', '角色查询', 'role:view', null, null, null, 0, 51, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (57, 18, 'permission', '用户查询', 'user:view', null, null, null, 0, 52, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (68, 7, 'permission', '客户查询', 'customer:view', null, null, null, null, 60, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (69, 7, 'permission', '客户添加', 'customer:create', null, null, null, null, 61, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (70, 7, 'permission', '客户修改', 'customer:update', null, null, null, null, 62, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (71, 7, 'permission', '客户删除', 'customer:delete', null, null, null, null, 63, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (73, 21, 'permission', '日志查询', 'info:view', null, null, null, null, 65, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (74, 21, 'permission', '日志删除', 'info:delete', null, null, null, null, 66, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (75, 21, 'permission', '日志批量删除', 'info:batchdelete', null, null, null, null, 67, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (76, 22, 'permission', '公告查询', 'notice:view', null, null, null, null, 68, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (77, 22, 'permission', '公告添加', 'notice:create', null, null, null, null, 69, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (78, 22, 'permission', '公告修改', 'notice:update', null, null, null, null, 70, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (79, 22, 'permission', '公告删除', 'notice:delete', null, null, null, null, 71, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (81, 8, 'permission', '供应商查询', 'provider:view', null, null, null, null, 73, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (82, 8, 'permission', '供应商添加', 'provider:create', null, null, null, null, 74, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (83, 8, 'permission', '供应商修改', 'provider:update', null, null, null, null, 75, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (84, 8, 'permission', '供应商删除', 'provider:delete', null, null, null, null, 76, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (86, 22, 'permission', '公告查看', 'notice:viewnotice', null, null, null, null, 78, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (91, 9, 'permission', '商品查询', 'goods:view', null, null, null, 0, 79, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (92, 9, 'permission', '商品添加', 'goods:create', null, null, null, 0, 80, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (116, 9, 'permission', '商品删除', 'goods:delete', null, null, null, 0, 84, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (117, 9, 'permission', '商品修改', 'goods:update', null, null, null, 0, 85, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (118, 9, 'permission', '商品查询', 'goods:view', null, null, null, 0, 86, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (119, 22, 'permission', '公告批量删除', 'notice:batchdelete', null, null, null, 0, 87, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (122, 6, 'menu', '缓存管理', null, '&#xe630', '/sys/toCacheManager', '_black', 1, 88, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (123, 122, 'permission', '同步缓存', 'cache:syncCache', null, null, null, 0, 89, 1);
INSERT INTO medmis.sys_permission (id, pid, type, title, percode, icon, href, target, open, ordernum, available) VALUES (124, 122, 'permission', '清空缓存', 'cache:removeAllCache', null, null, null, 0, 90, 1);
create table sys_user
(
    id        int auto_increment,
    name      char(40)      not null comment '真实姓名',
    loginname varchar(255)  not null,
    password  varchar(255)  not null,
    gender    int default 0 not null comment '性别.1为男，0为女',
    id_num    char(100)     not null comment '身份证号码',
    med_num   char(100)     null comment '医保卡号',
    addr      char(100)     not null comment '地址',
    dept_id   char(10)      null comment '医生的部门id',
    role      int           not null comment '角色。0为管理员，1为门诊医生，2为药剂医生，3为检验医师，4为护士，5为病人',
    phone     char(100)     null comment '电话号码',
    info      varchar(255)          null comment '医生个人信息',
    birthday  date          not null,
    job       char(100)     null comment '病人工作',
    available int default 1 not null comment '1为可用，0为不可用',
    job_title char(100)     null comment '医生职称',
    constraint sys_user_id_uindex
        unique (id),
    constraint sys_user_loginname_uindex
        unique (loginname)
)  charset = utf8;

alter table sys_user
    add primary key (id);

INSERT INTO medmis.sys_user (id, name, loginname, password, gender, id_num, med_num, addr, dept_id, role, phone, info, birthday, job, available, job_title) VALUES (1, '管理员', 'system', 'e10adc3949ba59abbe56e057f20f883e', 1, '1', '1', '木叶村', '1', 0, '1311', null, '1999-03-07', null, 1, null);
INSERT INTO medmis.sys_user (id, name, loginname, password, gender, id_num, med_num, addr, dept_id, role, phone, info, birthday, job, available, job_title) VALUES (2, '普通病人', 'patient', 'e10adc3949ba59abbe56e057f20f883e', 0, '1', '1', '贝岗', '1', 5, '2422', null, '1999-04-28', null, 1, null);