import { RRFStatus } from "./rrfstatus";
import { ActivityLog } from "./activitylog";

export class ReleaseRRF {

    constructor(){}

    guid !: string;
    dt_created !: Date;
    dt_reviewed !: Date;
    program_id !: string;
    app_id !: string;
    status : RRFStatus = RRFStatus.CREATE;
    project_name !: string;
    requester_name !: string;
    requester_email !: string;
    requester_id !: string;
    app_version !: string;
    release_type !: string;
    release_type_other !: string;
    ms_fspec_req : number = 1;
    ms_fspec_dt !: Date;
    ms_dvint_req : number = 1;
    ms_dvint_dt !: Date;
    ms_uat_req : number = 1;
    ms_uat_dt !: Date;
    ms_beta_req : number = 1;
    ms_beta_dt !: Date;
    ms_disr_req : number = 1;
    ms_disr_dt !: Date;
    ms_modl_dt !: Date;
    ms_qacmplt_dt !: Date;
    ms_prod_dt !: Date;
    chg_under_test_new !: number;
    chg_under_test_enh !: number;
    chg_under_test_fix !: number;
    chg_under_test_ltf !: number;
    project_dates_flex : number = -1;
    project_dates_flex_desc !: string;
    dep_req_url !: string;
    dep_spec_url !: string;
    dep_rtm_url !: string;
    dep_other_app_test !: number;
    dep_other_app_desc !: string;
    dep_other_app_dependencies !: number;
    dep_risk_level !: number;
    dep_risk_level_desc !: string;
    dep_priority_level !: number;
    dep_priority_level_desc !: string;
    jira_list : Array<string> = [""];
    team_list : Array<string> = [""];
    comments !: string;
    qa_comments !: string;
    loe_automation !: number;
    loe_automation_unit !: string;
    loe_manual !: number;
    loe_manual_unit !: string;
    at_risk !: boolean;
    activity_log : Array<ActivityLog> = [];

    private required() {
        return {
            "requester_name"     : "Requester's Name Required",
            "requester_email"    : "Requester's Email Address Required",
            "app_version"        : "Application Version Required",
            "release_type"       : "Release Type is Required",
            "ms_modl_dt"         : "The Model Date is required",
            "ms_qacmplt_dt"      : "The QA Completion Date is Required",
            "ms_prod_dt"         : "The Date to Production is Required",
            "project_dates_flex" : "Indicate whether dates are flexible",
            "dep_req_url"        : "The link to requirements is required",
            "dep_spec_url"       : "The specification link is required",
            "dep_rtm_url"        : "The RTM link is required",
            "dep_risk_level"     : "The risk level is required",
            "dep_priority_level" : "The priority level is required"
        }
    }

    public validate(){
        let valid = true;
        for( let k in this.required() ){
            if( (this as any)[k] === undefined || (this as any)[k] == null ){
                valid = false;
                alert((this.required() as any)[k]);
                break;
            }
        }
    }

}