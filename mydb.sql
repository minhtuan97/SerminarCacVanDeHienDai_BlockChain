/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     12/20/2019 4:57:00 PM                        */
/*==============================================================*/

/*
drop table if exists OFFCHAIN;

drop table if exists ONCHAIN;
*/
/*==============================================================*/
/* Table: OFFCHAIN                                              */
/*==============================================================*/
create table OFFCHAIN
(
   HASHFILE             char(64) not null  comment '',
   FILE                 blob not null  comment '',
   primary key (HASHFILE)
);

/*==============================================================*/
/* Table: ONCHAIN                                               */
/*==============================================================*/
create table ONCHAIN
(
   HASH                 char(64) not null  comment '',
   PREVHASH             char(64) not null  comment '',
   TIMESTAMP            bigint not null  comment '',
   DATA                 text not null  comment '',
   primary key (HASH)
);

