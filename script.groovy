def precheck() {
    echo 'checking bundle files in the Git...'
    echo 'Validate manifest file...'
    echo 'checking images in the artifactory...'
    echo 'checking image size...'
    echo 'Is tenant available...'
    echo 'Is user part of tenant...'
    echo 'checking resource requirements in the tenant...'
} 

def buildBundle(){
    echo 'git login'
    echo 'git checkout'
    echo 'git compare'
    echo 'tar file'
}

def bundleOnboard() {
    echo 'Login to tenant'

    int start = 1
    int end = "${params.total_bundles}".toInteger()
    while(start<end) {
        echo 'Adding multiple bundles to robin'
        println(start);
        start++;
    }
} 

def deleteApp() {
    echo "deleting version ${params.app_name}"
} 
def deleteBundle() {
    echo "deleting bundle ${params.bundle_name}"
} 
def deployApp() {
    echo 'deploy the application...'
} 

def jobStatus(){
    echo 'checking application create job status...'
}
def postcheck() {
    echo 'validating the application...'
} 

def successNotify(appname, message, receivers){
    echo 'Success'
}
def failureNotify(appname, message, receivers){
    echo 'Failed'
}
def notify(appname, message, receivers) {
    echo 'Notify'
}   


return this
