-- ============================================
-- BDD BANK API 
-- ============================================

create table SBATPERSON (
   PERSON_CODE			CHAR(36)             not null,
   NAME                 VARCHAR(100)         not null,
   GENDER	            VARCHAR(15)	         not null,
   AGE					INTEGER			 	 not null,
   IDENTIFICATION		VARCHAR(13)          not null 	unique,   
   PHONE_NUMBER         VARCHAR(15)          not null,
   ADDRESS              VARCHAR(100)         null,
   STATUS               BOOLEAN                 not null,
   CREATED_DATE         TIMESTAMP            not null,
   CREATED_BY_USER      VARCHAR(50)          not null,
   LAST_MODIFIED_DATE   TIMESTAMP            null,
   LAST_MODIFIED_BY_USER VARCHAR(50)          null,
   CREATED_FROM_IP      VARCHAR(50)          not null,
   UPDATED_FROM_IP      VARCHAR(50)          null,
   constraint PKSBATPERSON primary key (PERSON_CODE)
);

CREATE TABLE SBATCLIENT (
	CLIENT_CODE			CHAR(36)             not null,
	PERSON_CODE			CHAR(36)             not null,
	PASSWORD            VARCHAR(250)         not null,
	CLIENT_STATUS       BOOLEAN                 not null,
	STATUS               BOOLEAN                 not null,
	CREATED_DATE         TIMESTAMP            not null,
	CREATED_BY_USER      VARCHAR(50)          not null,
	LAST_MODIFIED_DATE   TIMESTAMP            null,
	LAST_MODIFIED_BY_USER VARCHAR(50)          null,
	CREATED_FROM_IP      VARCHAR(50)          not null,
	UPDATED_FROM_IP      VARCHAR(50)          null,
	constraint PKSBATCLIENT primary key (CLIENT_CODE),
	constraint FKSBACLIENTPERSON foreign key (PERSON_CODE) references SBATPERSON (PERSON_CODE)
);

CREATE TABLE SBATACCOUNT (
    ACCOUNT_CODE     	CHAR(36)             not null,
	CLIENT_CODE			CHAR(36)             not null,
	ACCOUNT_NUMBER 		VARCHAR(20) 			not null 	unique,
    ACCOUNT_TYPE       	VARCHAR(20) 			not null,
    INITIAL_BALANCE     NUMERIC(10,2) 			not null,
	CURRENT_BALANCE     NUMERIC(10,2) 			not null,
    ACCOUNT_STATUS       BOOLEAN 				not null,
    STATUS               BOOLEAN                 not null,
	CREATED_DATE         TIMESTAMP            not null,
	CREATED_BY_USER      VARCHAR(50)          not null,
	LAST_MODIFIED_DATE   TIMESTAMP            null,
	LAST_MODIFIED_BY_USER VARCHAR(50)          null,
	CREATED_FROM_IP      VARCHAR(50)          not null,
	UPDATED_FROM_IP      VARCHAR(50)          null,
    constraint PKSBATACCOUNT primary key (ACCOUNT_CODE),
    constraint FKSBAACCOUNTCLIENT foreign key (CLIENT_CODE) references SBATCLIENT(CLIENT_CODE)
);

CREATE TABLE SBATTRANSACTION (
    TRANSACTION_CODE	CHAR(36)             not null,
	ACCOUNT_CODE     	CHAR(36)             not null,
	TRANSACTION_DATE    TIMESTAMP            not null,
	TRANSACTION_TYPE	VARCHAR(20)			 not null,
	AMOUNT			    NUMERIC(10,2) 			not null,
	BALANCE     		NUMERIC(10,2) 			not null,
    STATUS               BOOLEAN                 not null,
	CREATED_DATE         TIMESTAMP            not null,
	CREATED_BY_USER      VARCHAR(50)          not null,
	LAST_MODIFIED_DATE   TIMESTAMP            null,
	LAST_MODIFIED_BY_USER VARCHAR(50)          null,
	CREATED_FROM_IP      VARCHAR(50)          not null,
	UPDATED_FROM_IP      VARCHAR(50)          null,
	constraint PKSBATTRANSACTION primary key (TRANSACTION_CODE),
    constraint FKSBATRANSACTIONACCOUNT foreign key (ACCOUNT_CODE) references SBATACCOUNT(ACCOUNT_CODE)
);
