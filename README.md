# TwitterLakBot

###### Dev Plan : 2020-5-31 ~ 2020-6-28

###### made by LakWon

#### Twitter Auth
###### Create file > twitter4j.properties > consumerKey, consumerSecret, accessToken, accessTokenSecret

#### How to run
###### start : sudo nohup mvn spring-boot:run &
###### stop : 1) ps -ef  2) kill -9 [PID]  (/usr/lib/jvm/java-1.8.0-openjdk ..)

#### EC2 Instance
###### ec2 > security-group > inbound-rule > tcp/port/source
###### ssh > firewall-cmd --zone=public --permanent --add-port=[Port Number]/tcp

#### API
###### 1. Update Status : POST http://[EC2 IP]:[Port Number]/body/UpdateStatus, Body : {start:true,message:message,one_time:true}
###### 2. Search : POST http://[EC2 IP]:[Port Number]/body/Search, Body : {start:true,count:1,query:query,save_file:true,one_time:true}
###### 3. Delete Status : POST http://[EC2 IP]:[Port Number]/body/DeleteStatus, Body : {start:true,all:true,count:0,one_time:true}
###### 4. Config : POST http://[EC2 IP]:[Port Number]/body/Config, Body : {period:600}
###### 5. IU Analysis : POST http://[EC2 IP]:[Port Number]/body/IUAnalysis, Body : {start:true,interval:60,one_time:true}

### Maintenance : 2020-6-29 ~

#### Update Commit 
###### 2021.03.07 : IU Analysis 생성 (IU 시간별 트윗 수 구하는 기능 추가)
