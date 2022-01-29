import { ReleaseRRF } from "./releaserrf";
import { AutomationStatus } from "./automationstatus";
import { ActivityLog } from "./activitylog";

export class Automation {
    public guid!:string;
    public rrf!:ReleaseRRF;
    public status!:AutomationStatus;
    public dt_executed!:Date;
    public log!:Array<ActivityLog>;
}