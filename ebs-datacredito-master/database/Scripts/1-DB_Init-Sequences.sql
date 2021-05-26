SET search_path TO ebs;

drop sequence if exists ebs."param_sequence";
create sequence ebs."param_sequence" start 1 increment 1;

drop sequence if exists ebs."user_sequence";
create sequence ebs."user_sequence" start 1 increment 1;

drop sequence if exists ebs."log_sequence";
create sequence ebs."log_sequence" start 1 increment 1;