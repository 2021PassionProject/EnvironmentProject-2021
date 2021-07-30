create sequence seq_member increment by 1 start with 1;

create table member
(
    id       number(11) not null primary key,
    email    varchar2(30) not null unique,
    pw       varchar2(30) not null,
    name     varchar2(30) not null,
    birth    number(8) not null,
    phone    varchar2(13) not null,
    address  varchar2(50) not null,
    address2 varchar2(50)
);

INSERT INTO member
VALUES (seq_member.nextval, 'root@induk.ac.kr', 'cometrue', '관리자', '12345678', '010-1234-5678', 'Korea', '');
INSERT INTO member
VALUES (seq_member.nextval, 'hanjs@induk.ac.kr', 'cometrue', '한정수', '19971027', '010-8924-7899', 'Korea', 'DobongGu');

select *
from member;

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
where id = 2;

drop sequence seq_member;

drop table member;