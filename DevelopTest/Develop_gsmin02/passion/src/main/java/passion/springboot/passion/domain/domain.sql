create sequence seq_member increment by 1 start with 1;
create sequence seq_board increment by 1 start with 1;
create sequence seq_comment increment by 1 start with 1;

create table member
(
    id       number(11)    not null primary key,
    email    varchar2(30)  not null unique,
    pw       varchar2(30)  not null,
    name     varchar2(30)  not null,
    birth    number(8)     not null,
    phone    varchar2(13)  not null,
    postcode varchar2(5),
    address  varchar2(100) not null,
    address2 varchar2(100)
);

alter table member add postcode varchar2(5);

create table board
(
    board_id number(11) not null primary key,
    writer varchar(20),
    writer_email varchar2(30),
    title    varchar(100),
    write_time varchar(20),
    views number(11),
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

INSERT INTO member
VALUES (seq_member.nextval, 'root@induk.ac.kr', 'cometrue', '관리자', '12345678', '010-1234-5678','12345', 'Korea', '');
INSERT INTO member
VALUES (seq_member.nextval, 'hanjs@induk.ac.kr', 'cometrue', '한정수', '19971027', '010-8924-7899', '44567','Korea', 'DobongGu');

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

delete
from board
where board_id between 3 and 300;


drop table member;
drop table board;
drop table reply_comment;

drop sequence seq_member;
drop sequence seq_board;
drop sequence seq_comment;


commit;