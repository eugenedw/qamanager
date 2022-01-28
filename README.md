# qamanager
A Quality Assurance Management tool for tracking of key tasks, resources, and schedules
Spring Boot and Angular 12
## System Schema (Data Models)

**Table: Activity Log (activity_log)**

| Column     | Data Type     |
| -------    | ------------- |
| guid       | string (20)   |
| message    | string (1000) | 
| dt_created | timestamp     |
| author_id  | string (20)   |
| type       | int           | 
| context    | int           |


**Table: Application (application)**

| Column     | Data Type     |
| -------    | ------------- |
| guid       | string (20)   |
| name       | string (1000) | 
| program_id | string (20)   |
| app_id     | string (20)   |
| artifact   | string (50)   | 
| type       | string (20)   | 
| automated  | int           | 
| govt_owner | string (50)   | 
| proj_mgr   | string (50)   | 
| comments   | string (1000) | 


**Table: Automation (automation)**

| Column      | Data Type     |
| -------     | ------------- |
| guid        | string (20)   |
| rrf_id      | string (20)   | 
| status      | int           |
| dt_executed | timestamp     |


**Table: Person (person)**

| Column     | Data Type     |
| -------    | ------------- |
| guid       | string (20)   |
| firstname  | string (1000) | 
| lastname   | string (20)   |
| email      | string (20)   |
| phone      | string (50)   | 
| dt_created | timestamp     |
| comments   | string (1000) | 


**Table: Person to Application (psn_app_xrf)**

| Column         | Data Type     |
| -------        | ------------- |
| guid           | string (20)   |
| person_id      | string (20)   | 
| application_id | string (20)   |
| role           | int           |


**Table: Person to Role (psn_rol_xrf)**

| Column         | Data Type     |
| -------        | ------------- |
| guid           | string (20)   |
| person_id      | string (20)   | 
| role           | int           |


**Table: Program Area (program_area)**

| Column         | Data Type     |
| -------        | ------------- |
| guid           | string (20)   |
| name           | string (20)   |


**Table: Program Area to Application (pgm_app_xrf)**

| Column         | Data Type     |
| -------        | ------------- |
| guid           | string (20)   |
| program_id     | string (20)   | 
| app_id         | string (20)   |

**Table: RRF (release_rrf)**

| Column                  | Data Type     |
| ----------------------  | ------------- |
| guid                    | string (20)   |
| dt_created              | Date          |
| program_id              | string (20)   |
| app_id                  | string (20)   |
| status                  | int           |
| project_name            | string        |
| requester_name          | string        |
| requester_email         | string        |
| requester_id            | string        |
| app_version             | string        |
| release_type            | string        |
| release_type_other      | string        |
| ms_fspec_req            | int           |
| ms_fspec_dt             | Date          |
| ms_dvint_req            | int           |
| ms_dvint_dt             | Date          |
| ms_uat_req              | int           |
| ms_uat_dt               | Date          |
| ms_beta_req             | int           |
| ms_beta_dt              | Date          |
| ms_disr_req             | int           |
| ms_disr_dt              | Date          |
| ms_modl_dt              | Date          |
| ms_qacmplt_dt           | Date          |
| ms_prod_dt              | Date          |
| chg_under_test_new      | int           |
| chg_under_test_enh      | int           |
| chg_under_test_fix      | int           |
| chg_under_test_ltf      | int           |
| project_dates_flex      | int           |
| project_dates_flex_desc | string (500)  |
| dep_req_url             | string (255)  |
| dep_spec_url            | string (255)  |
| dep_rtm_url             | string (255)  |
| dep_other_app_test      | int           |
| dep_other_app_desc      | string (255)  |
| dep_risk_level          | int           |
| dep_risk_level_desc     | string (50)   |
| dep_priority_level      | int           |
| dep_priority_level_desc | string (500)  |
| jira_list               | string (5000) |
| comments                | string (1000) |
| qa_comments             | string (1000) |
| loe_automation          | int           |
| loe_automation_unit     | string (10)   |
| loe_manual              | int           |
| loe_manual_unit         | string (10)   |
| at_risk                 | int           |
| code_rev_cmplt          | int           |
| code_cvrg               | int           |


**Table: Person to RRF (psn_rrf_xrf)**

| Column         | Data Type     |
| ----------     | ------------- |
| guid           | string (20)   |
| rrf_id         | string (20)   |
| person_id      | string (20)   |

**Table: Release Requirement (release_req)**

| Column         | Data Type     |
| ---------      | ------------- |
| guid           | string (20)   |
| req_name       | string (20)   |
| rrf_id         | string (20)   |
| tc_cmplt       | int           |
| tc_count       | int           |
| dt_tc_cmplt    | Date          |
| ut_cmplt       | int           |
| ut_count       | int           |
| dt_ut_cmplt    | Date          |
