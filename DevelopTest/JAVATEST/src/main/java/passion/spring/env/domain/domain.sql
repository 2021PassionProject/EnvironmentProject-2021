create sequence seq_member increment by 1 start with 1;
create sequence seq_board increment by 1 start with 1;
create sequence seq_comment increment by 1 start with 1;
create sequence seq_news increment by 1 start with 1;

create table member
(
    id       number(11) not null primary key,
    email    varchar2(30) not null unique,
    pw       varchar2(30) not null,
    name     varchar2(30) not null,
    birth    number(8) not null,
    phone    varchar2(13) not null,
    address  varchar2(100) not null,
    address2 varchar2(100)
);

create table board
(
    board_id number(11) not null primary key,
    title    varchar(100),
    content  varchar(2000)
);

create table reply_comment
(
    comment_id  number(11) not null primary key,
    board_id    number(11),
    member_name varchar(20),
    content     varchar2(500),
    write_time  varchar(20)
);

create table news
(
    newsId number(11) not null primary key,
    newsDate date default SYSTEM_TIME,
    reporter varchar(20),
    filepath varchar2(200),
    content varchar2(4000)
);

SELECT board_id, REGEXP_REPLACE(title, '<[^>]*>|\&([^;])*;', ''), REGEXP_REPLACE(content, '<[^>]*>|\&([^;])*;', '') FROM board;


INSERT INTO member
VALUES (seq_member.nextval, 'root@induk.ac.kr', 'cometrue', '관리자', '12345678', '010-1234-5678', 'Korea', '');
INSERT INTO member
VALUES (seq_member.nextval, 'hanjs@induk.ac.kr', 'cometrue', '한정수', '19971027', '010-8924-7899', 'Korea', 'DobongGu');


select *
from member;
select *
from board;
select *
from reply_comment;

select pw
from member
where email = 'hanjs@induk.ac.kr';

update member
set name='bruceHan',
    phone='01089247899',
    address='Seoul',
    address2='SSangMun'
where email = 'hanjs@induk.ac.kr'
  and pw = 'cometrue';

delete
from member
where id between 5 and 300;

drop sequence seq_member;
drop sequence seq_board;
drop sequence seq_comment;

drop table member;
drop table board;
drop table reply_comment;

commit;