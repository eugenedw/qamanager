// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
const DEFAULT_APP_TITLE = "QA Manager App.";
const DEFAULT_APP_DESCRIPTION = "This is a tool to facilitate management of critical software validation tasks.";

export const environment = {
  production: false,
  ANGULAR_APP_TITLE : DEFAULT_APP_TITLE, /* this should be set as an environment variable in the container */
  ANGULAR_APP_DESCRIPTION : DEFAULT_APP_DESCRIPTION /* this should be set as an environment variable in the container */
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
