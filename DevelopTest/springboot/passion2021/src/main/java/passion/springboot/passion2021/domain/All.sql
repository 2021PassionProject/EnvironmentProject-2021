create sequence seq_member increment by 1 start with 1;
create sequence seq_board increment by 1 start with 1;
create sequence seq_comment increment by 1 start with 1;


create table member
(
    id       number(11) not null primary key,
    email    varchar2(30) not null unique,
    pw       varchar2(30) not null,
    name     varchar2(30) not null,
    birth    varchar2(8) not null,
    phone    varchar2(13) not null,
    postcode varchar2(5) not null,
    address  varchar2(100) not null,
    address2 varchar2(100)
);


create table board (
                       board_id number(11) not null  primary key,
                       writer varchar(20),
                       writer_email varchar2(30),
                       title varchar(100),
                       write_time varchar(20),
                       views number(11),
                       content varchar(2000)
);

create table reply_comment (
                               comment_id number(11) not null primary key,
                               board_id number(11),
                               member_name varchar(20),
                               content varchar2(500),
                               write_time varchar(20)
);

INSERT INTO member VALUES(seq_member.nextval,'root@induk.ac.kr', 'cometrue','관리자','19400221','01012341234','13334','서울','');
INSERT INTO member VALUES(seq_member.nextval,'jms@induk.ac.kr', 'cometrue','전민서', '19940104', '01054394939','33333', '경기도','102호');

SELECT board_id, REGEXP_REPLACE(title, '<[^>]*>|\&([^;])*;', ''), REGEXP_REPLACE(content, '<[^>]*>|\&([^;])*;', '') FROM board;
select * from member;
select * from board;
select * from reply_comment;

select pw from member where email = 'jms@induk.ac.kr';

update member set name='jeonminseo', birth='19940104' ,phone='01054394939', postcode='17778', address='nowon, seoul' , address2='104호' where email='jms@induk.ac.kr' and pw='cometrue';

delete from member where id = 2;

drop sequence seq_member;
drop sequence seq_board;

drop sequence seq_comment;
drop table member;
drop table board;
drop table reply_comment;

commit;