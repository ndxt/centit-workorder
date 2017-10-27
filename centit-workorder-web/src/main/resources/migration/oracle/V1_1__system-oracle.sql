
create sequence DOC_VERSION maxvalue 99999999999999999 start with 1 increment by 1;
create sequence S_ADDRESSID maxvalue 99999999999999999 start with 1 increment by 1;
create sequence S_MSGCODE maxvalue 99999999999999999 start with 1 increment by 1;
create sequence S_OPTDEFCODE maxvalue 99999999999999999 start with 1 increment by 1;
create sequence S_RECIPIENT maxvalue 99999999999999999 start with 1 increment by 1;
create sequence S_SYS_LOG maxvalue 99999999999999999 start with 1 increment by 1;
create sequence S_UNITCODE maxvalue 99999999999999999 start with 1 increment by 1;
create sequence S_USERCODE maxvalue 99999999999999999 start with 1 increment by 1;
create sequence S_USER_UNIT_ID maxvalue 99999999999999999 start with 1 increment by 1;
CREATE TABLE f_assist_operator (
  QUESTION_ID varchar2(32) NOT NULL,
  operator_code varchar2(32) NOT NULL,
  create_date date DEFAULT NULL,
  PRIMARY KEY (QUESTION_ID,operator_code)
) ;

CREATE TABLE f_help_doc (
  DOC_ID varchar2(32) NOT NULL,
  CATALOG_ID varchar2(32) DEFAULT NULL,
  DOC_TITLE varchar2(500) NOT NULL,
  DOC_LEVEL int DEFAULT NULL,
  DOC_PATH varchar2(500) NOT NULL,
  DOC_FILE clob,
  OS_ID varchar2(20) NOT NULL,
  Opt_ID varchar2(64) DEFAULT NULL ,
  OPT_Method varchar2(64) DEFAULT NULL ,
  update_user varchar2(32) DEFAULT NULL,
  last_update_time date DEFAULT NULL,
  PRIMARY KEY (DOC_ID)
) ;

CREATE TABLE f_help_doc_comment (
  COMMENT_ID varchar2(32) NOT NULL,
  DOC_ID varchar2(32) DEFAULT NULL,
  DOC_COMMENT clob,
  User_Code varchar2(32) DEFAULT NULL,
  USER_NAME varchar2(64) DEFAULT NULL,
  create_time date DEFAULT NULL,
  PRIMARY KEY (COMMENT_ID)
);

CREATE TABLE f_help_doc_score (
  SCORE_ID varchar2(32) NOT NULL,
  DOC_ID varchar2(32) DEFAULT NULL,
  DOC_SCORE int NOT NULL,
  User_Code varchar2(32) DEFAULT NULL,
  USER_NAME varchar2(64) DEFAULT NULL,
  create_time date DEFAULT NULL,
  PRIMARY KEY (SCORE_ID)
) ;

CREATE TABLE f_help_doc_version (
  DOC_ID varchar2(32) NOT NULL,
  DOC_VERSION int NOT NULL,
  DOC_FILE clob,
  DOC_TITLE varchar2(500) NOT NULL,
  update_user varchar2(32) DEFAULT NULL,
  last_update_time date DEFAULT NULL,
  PRIMARY KEY (DOC_ID,DOC_VERSION)
) ;

CREATE TABLE f_question_catalog (
  CATALOG_ID varchar2(32) NOT NULL,
  OS_ID varchar2(20) NOT NULL,
  CATALOG_NAME varchar2(100) NOT NULL,
  creator varchar2(32) DEFAULT NULL,
  create_time date DEFAULT NULL,
  DEFAULT_OPERATOR varchar2(32) DEFAULT NULL,
  CURRENT_OPERATOR   VARCHAR(32),
  TIME_LIMIT int DEFAULT '-1',
  catalog_key_words varchar2(200) DEFAULT NULL,
  PARENT_ID varchar2(32) DEFAULT NULL,
  SORT int DEFAULT NULL,
  ICON varchar2(100) DEFAULT NULL,
  CATALOG_DESCRIBE varchar2(200) DEFAULT NULL,
  PRIMARY KEY (CATALOG_ID)
) ;

CREATE TABLE f_question_info (
  QUESTION_ID varchar2(32) NOT NULL,
  CATALOG_ID varchar2(32) DEFAULT NULL,
  OS_ID varchar2(20) NOT NULL,
  User_Code varchar2(32) DEFAULT NULL,
  USER_NAME varchar2(64) DEFAULT NULL,
  QUESTION_TITLE varchar2(500) NOT NULL,
  QUESTION_CONTENT clob NOT NULL,
  QUESTION_STATE char(1) DEFAULT NULL ,
  create_time date DEFAULT NULL,
  edit_state char(1) DEFAULT NULL ,
  last_update_time date DEFAULT NULL,
  accept_time date DEFAULT NULL,
  complete_time date DEFAULT NULL,
  closed_time date DEFAULT NULL,
  evaluate_score int DEFAULT '-1',
  evaluate_time date DEFAULT NULL,
  CURRENT_OPERATOR varchar2(32) DEFAULT NULL,
  PRIMARY KEY (QUESTION_ID)
) ;

CREATE TABLE f_question_round (
  ROUND_ID varchar2(32) NOT NULL,
  QUESTION_ID varchar2(32) DEFAULT NULL,
  ROUND_CONTENT clob NOT NULL,
  edit_state char(1) DEFAULT NULL,
  ROUND_STATE char(1) DEFAULT NULL ,
  Q_OR_A char(1) DEFAULT NULL ,
  create_time date DEFAULT NULL,
  last_update_time date DEFAULT NULL,
  User_Code varchar2(32) DEFAULT NULL,
  USER_NAME varchar2(64) DEFAULT NULL,
  SHOW_USER char(1) DEFAULT NULL,
  PRIMARY KEY (ROUND_ID)
) ;
