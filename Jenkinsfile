def gv

pipeline {
    agent any
    parameters {
        
        choice(name: 'cluster', choices: ['sandbox', 'staging', 'prod'], description: 'Please select the target environment')
        string(name: 'VIP', defaultValue: '', description: 'please input the VIP')
        choice(name: 'operation', choices: ['install', 'upgrade', 'delete', 'recreate'], description: 'Please select the operation')
        string(name: 'tenant_name', defaultValue: '', description: 'please enter your tenant name')
        string(name: 'tenant_user', defaultValue: '', description: 'please enter your tenant user_name')
        password(name: 'tenant_password', defaultValue: '***', description: 'tenant password')
        booleanParam(name: 'multiple_bundle_deploy', defaultValue: false, description: 'Do we need to deploy parent bundle?')
        string(name: 'total_bundles', defaultValue: '1', description: 'Number of budles to be deploy')
        string(name: 'bundle_name_1', defaultValue: '', description: 'please enter the bundle name')
        string(name: 'app_name_1', defaultValue: '', description: 'please input the appname')
        choice(name: 'registry', choices: ['dev', 'stag', 'prod'], description: 'please choose the docker repo')      
         
    }
    stages {
        stage("init") { 
            steps {
                echo 'Init ** Loading script file...' 
                script {
                   gv = load "script.groovy"                  
                }
            }
        }
        stage("build") {
            steps {
                script {
                    gv.precheck()
                    gv.buildBundle()
                }
            }
        }
        stage("bundle onboard") {
            steps {
                script {
                    gv.bundleOnboard()
                }
            }
        }
      
        stage("delete app") {
            when {
                expression {
                    params.operation == 'delete' || params.operation == 'recreate'
                }
            }
            steps {
                script {
                    gv.deleteApp()
                }
            }
        }
        stage("deploy") {
            when {
                expression {
                    params.operation == 'install' || params.operation == 'recreate'
                }
            }
            steps {
                script {
                    gv.deployApp()                    
                }
            }
        }
        stage("Verification") {
            steps {
                script {
                    gv.jobStatus()
                }
            }
        }
        stage("post validation") {
            steps {
                script {
                    gv.postcheck()
                }
            }
        }
        post {

            always{}
            success{}
            failure{}
        }                           

    }   
}
