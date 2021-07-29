create sequence seq_member increment by 1 start with 1;

create table member(
    id number(11) not null primary key,
    email varchar2(30) not null unique,
    pw varchar2(30) not null,
    name varchar2(30) not null,
    birth number(8) not null,
    phone varchar2(13) not null,
    address varchar2(50) not null,
    address2 varchar2(50)
);

INSERT INTO member VALUES(seq_member.nextval, 'root@induk.ac.kr', 'cometrue', '관리자', '1712777', 'Korea', '');
INSERT INTO member VALUES(seq_member.nextval, 'hanjs@induk.ac.kr', 'cometrue', '한정수', '1712046', 'Korea', 'DobongGu');

select * from member;

select pw from member where email = 'hanjs@induk.ac.kr';

update member set name='bruceHan', phone='01089247899', address='SSangMun, Seoul' where email='passion@induk.ac.kr' and pw='cometrue';

delete from member where id = 2;

drop sequence seq_member;

drop table member;