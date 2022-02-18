declare var process: Process;

interface Process {
    env: Env
}

interface Env {
    APP_TITLE : string
}

interface GlobalEnvironment {
    process : Process
}