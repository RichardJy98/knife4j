drop table if exists log_record;
create table log_record
(
    id          BIGINT NOT NULL identity PRIMARY KEY,
    time              DATETIME NOT NULL,
    message           TEXT NOT NULL,
    level_string      VARCHAR(254) NOT NULL,
    logger_name       VARCHAR(254) NOT NULL,
    thread_name       VARCHAR(254),
    caller_filename   VARCHAR(254) NOT NULL,
    caller_class      VARCHAR(254) NOT NULL,
    caller_method     VARCHAR(254) NOT NULL,
    caller_line       CHAR(4) NOT NULL
);

drop table if exists log_record_test;
create table log_record_test
(
    id          BIGINT NOT NULL identity PRIMARY KEY,
    time              DATETIME NOT NULL,
    message           TEXT NOT NULL,
    level_string      VARCHAR(254) NOT NULL,
    logger_name       VARCHAR(254) NOT NULL,
    thread_name       VARCHAR(254),
    caller_filename   VARCHAR(254) NOT NULL,
    caller_class      VARCHAR(254) NOT NULL,
    caller_method     VARCHAR(254) NOT NULL,
    caller_line       CHAR(4) NOT NULL
);

drop table if exists log_record_test;
create table log_record_test
(
    id              BIGINT       NOT NULL identity PRIMARY KEY,
    time            DATETIME     NOT NULL,
    message         varchar(MAX) NOT NULL,
    level_string    VARCHAR(254) NOT NULL,
    logger_name     VARCHAR(254) NOT NULL,
    thread_name     VARCHAR(254),
    request_id      VARCHAR(254) NOT NULL,
    artcile         varchar(MAX) not null,
    successful      VARCHAR(254) NOT NULL,
    caller_filename VARCHAR(254) NOT NULL,
    caller_class    VARCHAR(254) NOT NULL,
    caller_method   VARCHAR(254) NOT NULL,
    caller_line     CHAR(4)      NOT NULL
);