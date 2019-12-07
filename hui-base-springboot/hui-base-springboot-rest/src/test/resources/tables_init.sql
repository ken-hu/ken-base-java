DROP TABLE IF EXISTS training_teachers;
CREATE TABLE training_teachers  (
  teacher_id int(11) NOT NULL,
  teacher_name varchar(50)  NULL DEFAULT NULL,
  major varchar(50) NULL DEFAULT NULL,
  birth datetime NULL DEFAULT NULL,
  created_time datetime NULL DEFAULT NULL,
  modifed_time datetime NULL DEFAULT NULL,
  modifed_by varchar(50) NULL DEFAULT NULL
);


