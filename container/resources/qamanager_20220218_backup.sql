-- -------------------------------------------------------------
-- TablePlus 4.5.2(402)
--
-- https://tableplus.com/
--
-- Database: qamanager
-- Generation Time: 2022-02-18 07:58:34.8650
-- -------------------------------------------------------------


DROP TABLE IF EXISTS "public"."activity_log";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."activity_log" (
    "guid" uuid NOT NULL,
    "message" text,
    "dt_created" timestamp,
    "author_id" varchar,
    "type" int4,
    "context" int4,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."application";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."application" (
    "guid" uuid NOT NULL,
    "name" varchar,
    "program_id" varchar,
    "app_id" varchar,
    "artifact" varchar,
    "type" varchar,
    "automated" int2,
    "govt_owner" varchar,
    "proj_mgr" varchar,
    "comments" text,
    "dt_created" timestamp,
    "product_owner" varchar(255),
    "project_mgr" varchar(255),
    "app_type" int4,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."business_program";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."business_program" (
    "dt_created" timestamp,
    "name" varchar(255),
    "name_short" varchar(255),
    "guid" uuid NOT NULL
);

DROP TABLE IF EXISTS "public"."person";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."person" (
    "guid" uuid NOT NULL,
    "firstname" varchar,
    "lastname" varchar,
    "email" varchar,
    "phone" varchar,
    "dt_created" timestamp,
    "comments" text,
    "ext_id" varchar,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."person_role";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."person_role" (
    "guid" uuid NOT NULL,
    "role_code" varchar(255),
    "role_name" varchar(255),
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."pgm_app_xref";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."pgm_app_xref" (
    "guid" uuid NOT NULL,
    "program_id" varchar,
    "app_id" varchar,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."program_area";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."program_area" (
    "guid" uuid NOT NULL,
    "name" varchar,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."psn_app_xref";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."psn_app_xref" (
    "guid" uuid NOT NULL,
    "app_id" uuid NOT NULL,
    "person_id" uuid NOT NULL,
    PRIMARY KEY ("guid","person_id")
);

DROP TABLE IF EXISTS "public"."psn_rrf_xrf";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."psn_rrf_xrf" (
    "guid" uuid NOT NULL,
    "rrf_id" varchar,
    "person_id" varchar,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."release_req";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."release_req" (
    "guid" uuid NOT NULL,
    "req_name" varchar,
    "rrf_id" varchar,
    "tc_cmplt" int2,
    "tc_count" int2,
    "dt_tc_cmplt" date,
    "ut_cmplt" int2,
    "ut_count" int2,
    "dt_ut_cmplt" date,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."release_rrf";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."release_rrf" (
    "guid" uuid NOT NULL,
    "dt_created" timestamp,
    "program_id" varchar,
    "app_id" varchar,
    "status" int2,
    "project_name" varchar,
    "requester_name" varchar,
    "requester_email" varchar,
    "requester_id" varchar,
    "app_version" varchar,
    "release_type" varchar,
    "release_type_other" varchar,
    "ms_fspec_req" varchar,
    "ms_fspec_dt" varchar,
    "ms_dvint_req" int2,
    "ms_dvint_dt" date,
    "ms_uat_req" int2,
    "ms_uat_dt" date,
    "ms_beta_req" int4,
    "ms_beta_dt" date,
    "ms_disr_req" int4,
    "ms_disr_dt" date,
    "ms_modl_dt" date,
    "ms_qacmplt_dt" date,
    "ms_prod_dt" date,
    "chg_under_test_new" int2,
    "chg_under_test_enh" int2,
    "chg_under_test_fix" int2,
    "chg_under_test_ltf" int2,
    "project_dates_flex" int2,
    "project_dates_flex_desc" varchar,
    "dep_req_url" varchar,
    "dep_spec_url" varchar,
    "dep_rtm_url" varchar,
    "dep_other_app_test" int2,
    "dep_other_app_desc" varchar,
    "dep_risk_level" int2,
    "dep_risk_level_desc" varchar,
    "dep_priority_level" int2,
    "dep_priority_level_desc" varchar,
    "jira_list" varchar,
    "comments" text,
    "qa_comments" text,
    "loe_automation" int2,
    "loe_automation_unit" varchar,
    "loe_manual" int2,
    "loe_manual_unit" varchar,
    "at_risk" int2,
    "code_rev_cmplt" int2,
    "code_cvrg" int2,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."rtm";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."rtm" (
    "guid" uuid NOT NULL,
    "app_id" varchar,
    "dt_created" timestamp,
    "dt_updated" timestamp,
    "created_by" varchar,
    "description" text,
    "parent_id" varchar,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."test_case";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS tc_id_seq;

-- Table Definition
CREATE TABLE "public"."test_case" (
    "guid" uuid NOT NULL,
    "app_id" varchar(255),
    "is_automated" int4 DEFAULT 0,
    "br_id" varchar(255),
    "created_by" varchar(255),
    "dt_created" timestamp,
    "exp_rslt" varchar(255),
    "tc_group_id" varchar(255),
    "tc_name" varchar(255),
    "preconditions" varchar(255),
    "steps" varchar(255),
    "tc_id" int4 NOT NULL DEFAULT nextval('tc_id_seq'::regclass),
    "priority" int4,
    "tc_type" varchar(255),
    "ttc" varchar(255),
    "deleted" int4 DEFAULT 0,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."test_case_execution";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."test_case_execution" (
    "guid" uuid NOT NULL,
    "app_id" varchar(255),
    "dt_created" timestamp,
    "dt_executed" timestamp,
    "trig_by" varchar(255),
    "name" varchar(255),
    "test_pln_id" varchar(255),
    "deleted" int4 DEFAULT 0,
    "milestone_id" varchar(255),
    "description" varchar(255),
    "tc_select" int4,
    "exec_meth" int4,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."test_case_execution_xref";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."test_case_execution_xref" (
    "guid" uuid NOT NULL,
    "exec_id" uuid NOT NULL,
    "tc_id" uuid NOT NULL,
    PRIMARY KEY ("tc_id","exec_id")
);

DROP TABLE IF EXISTS "public"."test_case_group";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."test_case_group" (
    "guid" uuid NOT NULL,
    "app_id" varchar(255),
    "name" varchar(255),
    "parent_id" uuid,
    "sorder" int4,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."test_case_project";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."test_case_project" (
    "guid" uuid NOT NULL,
    "app_id" varchar(255),
    "created_by_id" varchar(255),
    "dt_created" timestamp,
    "deleted" int4 DEFAULT 0,
    "name" varchar(255),
    "dt_last_run" timestamp,
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."test_case_type";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."test_case_type" (
    "guid" uuid NOT NULL,
    "name" varchar(255),
    "typ_code" varchar(255),
    PRIMARY KEY ("guid")
);

DROP TABLE IF EXISTS "public"."test_plan";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."test_plan" (
    "guid" uuid NOT NULL,
    "app_id" varchar(255),
    "created_by" varchar(255),
    "dt_created" timestamp,
    "dt_processed" timestamp,
    "deleted" int4 DEFAULT 0,
    "description" varchar(2000),
    "metrics" varchar(10000),
    "milestone_id" varchar(255),
    "name" varchar(255),
    PRIMARY KEY ("guid")
);

INSERT INTO "public"."application" ("guid", "name", "program_id", "app_id", "artifact", "type", "automated", "govt_owner", "proj_mgr", "comments", "dt_created", "product_owner", "project_mgr", "app_type") VALUES
('d8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'Notifications Web Applications (NTFYM)', '7f661656-3827-4421-9dac-cd3b79d2cd0f', '455', NULL, NULL, 1, NULL, NULL, NULL, '2022-01-29 09:30:24.622', 'Brian Stroud', 'Victor Van Leer', NULL),
('de666b04-6fb9-424d-b602-808cb7366093', 'DS Access Station', '54926b4d-a2de-49df-b7e6-75d1e7e60ff1', '123', NULL, NULL, 1, NULL, NULL, NULL, '2022-01-29 09:28:59.154', 'Steve Turner', 'Rafael Vidauri', NULL);

INSERT INTO "public"."business_program" ("dt_created", "name", "name_short", "guid") VALUES
('2022-01-29 10:09:53.063', 'Entitlements and Benefits', 'EnB', '7f661656-3827-4421-9dac-cd3b79d2cd0f'),
('2022-01-29 10:10:27.63', 'Credentialing', 'CRED', '54926b4d-a2de-49df-b7e6-75d1e7e60ff1');

INSERT INTO "public"."test_case" ("guid", "app_id", "is_automated", "br_id", "created_by", "dt_created", "exp_rslt", "tc_group_id", "tc_name", "preconditions", "steps", "tc_id", "priority", "tc_type", "ttc", "deleted") VALUES
('0a23e7ca-3e8e-485d-afed-11f6de3ede68', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 15:07:12.608', NULL, '', '1 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('2f0329e6-5772-4269-bdf9-d537c20ed7af', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 15:06:41.352', NULL, '', '2 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('5abf6358-29b3-4330-a121-0c6e1ee39229', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 15:03:03.688', NULL, '', '3 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('5bdf5ed1-4ec1-499d-ad8a-8f6bb933800c', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 16:29:50.968', NULL, '274b594c-3b9c-4aa3-9155-02981c61afcf', '4 asdfasdasdfs', NULL, NULL, 12, 0, '', NULL, 0),
('66aa0e44-4f13-4dda-baf2-9e943b09a924', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-17 14:19:47.951', NULL, '', 'Something to test', NULL, NULL, 15, 0, '', NULL, 0),
('6b3bdcd4-5ee5-4e09-aa29-80e3fd67673a', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-14 02:31:48.114', NULL, '0e8fe8d3-75df-4cf4-83f5-78830a7714f8', 'Bring in the feature', NULL, NULL, 1, 0, '', NULL, 0),
('6c925f86-43c5-495d-926f-8454d3074066', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 15:18:19.28', NULL, '', '5 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('75523b9d-e98e-455a-98b0-cc857c6d918a', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 16:48:56.711', NULL, '0e8fe8d3-75df-4cf4-83f5-78830a7714f8', 'The test case', NULL, NULL, 13, 0, '', NULL, 0),
('7d25e66d-570f-47dd-9491-43c4bb6358b9', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 15:33:34.74', NULL, '', '6 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('adb01ff4-be7d-4fd2-8165-83dfbfdbca25', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, 'BR 3.12', NULL, '2022-02-14 02:30:57.762', NULL, 'def9d193-6e58-4080-a11d-43b6f664ef18', 'verify functionality', NULL, NULL, 2, 0, '', '120m', 0),
('b2bd53a4-d93d-4491-987d-e617d2d96761', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 15:29:11.633', NULL, '', '7 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('c50c0fa0-24e8-40a4-a184-840ffdc28144', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 15:04:42.869', NULL, '', '8 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('d25bf155-ebf5-4e72-92db-f7f8e318e6ea', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 15:14:17.432', NULL, '', '9 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('d2703df7-f251-449f-a099-0ff7fff5672c', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-17 14:17:59.037', NULL, '', 'This is a test', NULL, NULL, 14, 0, '', NULL, 0),
('fb9da598-1328-4293-aacf-4b85c6f82ab3', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 14:41:21.915', NULL, '', '10 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('fe6c734f-30d1-4491-95af-9301c2aa6713', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 23:15:47.391937', NULL, NULL, '11 asdfasdfwefwefwaef', NULL, NULL, 11, 0, NULL, NULL, 0),
('ff6c734f-30d1-4491-95af-9301c2aa6712', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 14:41:38.276', NULL, '', '12 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1),
('ffb5f5db-5876-4bfb-bb77-00468e189c86', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 0, NULL, NULL, '2022-02-16 15:16:32.865', NULL, '', '13 asdfasdasdfs', NULL, NULL, 0, 0, '', NULL, 1);

INSERT INTO "public"."test_case_execution" ("guid", "app_id", "dt_created", "dt_executed", "trig_by", "name", "test_pln_id", "deleted", "milestone_id", "description", "tc_select", "exec_meth") VALUES
('00277680-78dc-4215-80e6-72f38423c6ea', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', '2022-02-17 22:53:08.775', NULL, NULL, 'Test Case Execution 1', NULL, 0, '529e1065-4618-4ca9-9ce3-7a98175fecf4', 'test exec', 1, NULL),
('22c5a078-31cf-47bd-94b4-6b74da325a44', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', '2022-02-17 22:58:38.406', NULL, NULL, 'Testing Test Run', NULL, 0, '529e1065-4618-4ca9-9ce3-7a98175fecf4', 'aoijsdoifjapsodifoi ja opisjdfoi ajposd', 1, NULL),
('4f91cbe6-be22-4a7b-93b6-078b4a62d747', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', '2022-02-17 22:54:49.708', NULL, NULL, 'Test Run HEre', NULL, 0, '529e1065-4618-4ca9-9ce3-7a98175fecf4', 'lajspoidjapsiod fpasdf', 1, NULL),
('7d64716f-13f6-4df4-a3e6-cc3bac2ca7c9', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', '2022-02-17 22:57:20.665', NULL, NULL, 'alksj dpkj pfoaosodf', NULL, 0, '529e1065-4618-4ca9-9ce3-7a98175fecf4', 'alsjdfiajspidf', 0, NULL);

INSERT INTO "public"."test_case_execution_xref" ("guid", "exec_id", "tc_id") VALUES
('f2cd8590-3a20-4513-b480-297542cd174c', '22c5a078-31cf-47bd-94b4-6b74da325a44', '5bdf5ed1-4ec1-499d-ad8a-8f6bb933800c');

INSERT INTO "public"."test_case_group" ("guid", "app_id", "name", "parent_id", "sorder") VALUES
('03ccff62-fa02-47fa-acee-b61645f39978', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'Module 1 UI Regression', 'def9d193-6e58-4080-a11d-43b6f664ef18', NULL),
('0e8fe8d3-75df-4cf4-83f5-78830a7714f8', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'Primary', NULL, 1),
('274b594c-3b9c-4aa3-9155-02981c61afcf', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'General', NULL, 0),
('5467faaa-0122-4930-af47-f61da4b25c20', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'Feature Set 3', NULL, 9),
('6d064521-d390-45a2-834f-f10ba4d913e0', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'Regression', NULL, 2),
('900235f3-3931-4151-9cbf-be5aec859812', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'Feature Set 1', NULL, 7),
('98f0b93c-cd09-402a-b766-b00a589004d7', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'Functional Regression', '6d064521-d390-45a2-834f-f10ba4d913e0', NULL),
('a7e8f733-3fec-482b-880e-92168d2747dd', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'Secondary', NULL, NULL),
('af118639-5b73-45f1-87cf-7e79b5139b3c', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'Feature Set 2', NULL, 8),
('def9d193-6e58-4080-a11d-43b6f664ef18', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', 'UI Regression', '6d064521-d390-45a2-834f-f10ba4d913e0', NULL);

INSERT INTO "public"."test_case_project" ("guid", "app_id", "created_by_id", "dt_created", "deleted", "name", "dt_last_run") VALUES
('529e1065-4618-4ca9-9ce3-7a98175fecf4', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', NULL, '2022-02-07 04:31:00.132', 0, 'Full Regression', NULL),
('f7bf15f7-642d-408e-adf5-924d8743aae6', 'd8fc5b06-2e7c-4231-a60a-f7ce80595e15', NULL, '2022-02-07 12:50:33.49', 0, 'Release 1.0 (FR)', NULL);

ALTER TABLE "public"."psn_app_xref" ADD FOREIGN KEY ("app_id") REFERENCES "public"."application"("guid");
ALTER TABLE "public"."psn_app_xref" ADD FOREIGN KEY ("guid") REFERENCES "public"."person"("guid");
ALTER TABLE "public"."psn_app_xref" ADD FOREIGN KEY ("person_id") REFERENCES "public"."person"("guid");
ALTER TABLE "public"."test_case_execution_xref" ADD FOREIGN KEY ("exec_id") REFERENCES "public"."test_case_execution"("guid");
ALTER TABLE "public"."test_case_execution_xref" ADD FOREIGN KEY ("tc_id") REFERENCES "public"."test_case"("guid");
