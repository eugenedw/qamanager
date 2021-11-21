import os
import base64
import uuid
import json
from csv import DictReader

appdata = []
programs = []
programdata = {}
with open('application_list.csv') as file:
    csv_dict = DictReader(file)
    print(csv_dict.fieldnames)
    for row in csv_dict:
        program = row['program_area']
        message_bytes = program.encode('ascii')
        base64_bytes = base64.b64encode(message_bytes)
        b64p = base64_bytes.decode('ascii')
        if b64p not in programdata:
            programdata[b64p] = {
                "guid" : str(uuid.uuid1()),
                "name" : program
            }
            programs.append(programdata[b64p])
        app = {
            "guid" : str(uuid.uuid1()),
            "name" : row["application_name"],
            "app_id" : row["app_id"],
            "program" : programdata[b64p]["guid"],
            "artifact" : row['artifact_name'],
            "type" : row['app_type'].replace(" ","_").lower(),
            "automated" : row['automation_status']
        }
        appdata.append(app)

with open('applications.json','w') as file:
    file.write(json.dumps(appdata,indent=4))
with open('programs.json','w') as file:
    file.write(json.dumps(programs,indent=4))