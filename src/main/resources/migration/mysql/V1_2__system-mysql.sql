use workorder;

alter table workorder.F_QUESTION_CATALOG 
add 
   DEFAULT_OPERATOR     VARCHAR(32);
   
alter table workorder.F_QUESTION_CATALOG 
 add TIME_LIMIT           INT;
 
 
alter table workorder.F_QUESTION_INFO
add 
   CURRENT_OPERATOR   VARCHAR(32);