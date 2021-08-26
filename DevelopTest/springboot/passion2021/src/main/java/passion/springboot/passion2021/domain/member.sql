create sequence seq_member increment by 1 start with 1;

create table member (
                         id number(11) not null primary key,
                         email varchar2(30) not null unique,
                         pw varchar2(20) not null,
                         name varchar2(30) not null,
                         birth number(8) not null,
                         phone varchar2(13)not null,
                         address varchar2(50)not null,
                         address2 varchar2(50)
);

INSERT INTO member
VALUES(seq_member.nextval,'root@induk.ac.kr', 'cometrue','관리자','19400221','01012341234','서울','');
INSERT INTO member
VALUES(seq_member.nextval,'jms@induk.ac.kr', 'cometrue','전민서', '19940104', '01054394939', '경기도','102호');

select * from member;

select pw from member where email = 'jms@induk.ac.kr';

update member set name='jeonminseo', birth='19940104' ,phone='01054394939', address='nowon, seoul' , address2='104호' where email='jms@induk.ac.kr' and pw='cometrue';

delete from member where id = 2;

drop sequence seq_member;

drop table member;

