
pipeline {
            agent any
            tools 
            {
                maven 'maven_3_5_0'
            }
            stages 
            {
                stage ('Initialize') 
                {
                    steps 
                    {
                        git "https://github.com/nurperiutlu/Patient-Manager.git"
                    }
                }

                stage ('Build') 
                {
                    steps 
                    {
                        bat 'mvn verify' 
                    }
                    post 
                    {
                        success 
                        {
                            junit 'target/surefire-reports/**/*.xml' 
                        }
                    }
                }
            }
}
