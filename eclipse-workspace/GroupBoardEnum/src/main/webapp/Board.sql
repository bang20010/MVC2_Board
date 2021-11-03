
create table board_g(
num number(4) primary key,
id varchar2(14)CONSTRAINT userid REFERENCES signup_g(id),
subject varchar2(50) not null,
passwd varchar2(12) not null,
reg_date date not null,
readcount number(4) DEFAULT 0,
content varchar2(500) not null
);

create table signup_g( 
    id varchar2(14) PRIMARY key,
    passwd varchar2(30),
    phone number(11),
    name varchar2(30),
    email varchar2(30),
    joindate DATE DEFAULT sysdate
);

create table reply_g( 
    id varchar2(14) CONSTRAINT replyid REFERENCES signup_g(id),
    num number(4) CONSTRAINT replynum REFERENCES board_g(num),
    coment VARCHAR2(200),
    replydate DATE DEFAULT sysdate
);

alter table reply_g add id_reply number(10) Primary key;

create sequence seq_board_g
increment by 1
start with 1
nocycle
nocache;

create SEQUENCE seq_reply_g
increment by 1
start with 1
nocycle
nocache;