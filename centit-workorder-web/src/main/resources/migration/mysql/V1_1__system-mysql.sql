drop table if exists F_HELP_DOC_VERSION;
drop table if exists F_HELP_DOC_COMMENT;
drop table if exists F_HELP_DOC_SCORE;
drop table if exists F_QUESTION_ROUND;
drop table if exists F_QUESTION_INFO;
drop table if exists F_HELP_DOC;
drop table if exists F_QUESTION_CATALOG;

create table F_QUESTION_CATALOG
(
   CATALOG_ID           varchar(32) not null,
   OS_ID                varchar(20) not null,
   CATALOG_NAME         varchar(100) not null,
   creator              varchar(32),
   create_time          datetime,
    DEFAULT_OPERATOR     VARCHAR(32),
	TIME_LIMIT           INT,
   primary key (CATALOG_ID)
);

create table F_HELP_DOC
(
   DOC_ID               varchar(32) not null,
   CATALOG_ID           varchar(32),
   DOC_TITLE            varchar(500) not null,
   DOC_LEVEL            int not null,
   DOC_PATH             varchar(500) not null,
   DOC_FILE             text,
   OS_ID                varchar(20),
   Opt_ID               varchar(64) not null comment '模块，或者表',
   OPT_Method           varchar(64) comment '方法，或者字段',
   update_user          varchar(32),
   last_update_time     datetime,
   primary key (DOC_ID)
);

create table F_HELP_DOC_VERSION
(
   DOC_ID               varchar(32) not null,
   DOC_VERSION          int not null,
   DOC_FILE             longtext,
   DOC_TITLE            varchar(500) not null,
   update_user          varchar(32),
   last_update_time     datetime,
   primary key (DOC_ID, DOC_VERSION)
);

create table F_HELP_DOC_COMMENT
(
   COMMENT_ID           varchar(32) not null,
   DOC_ID               varchar(32),
   DOC_COMMENT          text,
   User_Code            varchar(32),
   USER_NAME            varchar(64),
   create_time          datetime,
   primary key (COMMENT_ID)
);

create table F_HELP_DOC_SCORE
(
   SCORE_ID             varchar(32) not null,
   DOC_ID               varchar(32),
   DOC_SCORE            int not null,
   User_Code            varchar(32),
   USER_NAME            varchar(64),
   create_time          datetime,
   primary key (SCORE_ID)
);

create table F_QUESTION_INFO
(
   QUESTION_ID          varchar(32) not null,
   CATALOG_ID           varchar(32),
   OS_ID                varchar(20) not null,
   User_Code            varchar(32),
   USER_NAME            varchar(64),
   QUESTION_TITLE       varchar(500) not null,
   QUESTION_CONTENT     text not null,
   QUESTION_STATE       char comment 'N : 正常  C: 关闭',
   create_time          datetime,
   edit_state           char comment 'N: 未编辑  U: 已被编辑',
   last_update_time     datetime,
   accept_time          datetime,
   complete_time        datetime,
   closed_time          datetime,
   evaluate_score       int,
   evaluate_time        datetime,
   CURRENT_OPERATOR   VARCHAR(32),
   primary key (QUESTION_ID)
);

create table F_QUESTION_ROUND
(
   ROUND_ID             varchar(32) not null,
   QUESTION_ID          varchar(32),
   ROUND_CONTENT        text not null,
   edit_state           char comment 'N: 未编辑  U: 已被编辑',
   ROUND_STATE          char comment 'N: 未回复  C : 已回复',
   Q_OR_A               char comment '追问还是解答',
   create_time          datetime,
   last_update_time     datetime,
   User_Code            varchar(32),
   USER_NAME            varchar(64),
   primary key (ROUND_ID)
);
