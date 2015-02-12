# 2008-insight-framework-try
目的 (071102)

1. 將JNDI-based改為embedded，以增加可攜性 (OK)
2. 開發scenario-based application launch mechanism
3. 加入Scenario Manager功能
4. 加入管理功能
5. Control server id動態切換 (OK)
6. 寫TestCase
7. 重構application架構
8. MessageSender
9. HmbTopic->Enum->PlatformTopic
10. TopicContentFactory
11. JSON Message
12. 生命週期的管理 Node Directory
13. start node
14. nodeadm

TODO:

寫Agent Container
heart beat
jmx
0. Taroko
1. Message Format Validation
2. Callable Agent
2. Persistence
3. Test

Message Format:
CONTEXT
{"subject":"home","burglar":"true"}
{"subject":"user", "location":"LIVINGROOM" } 

 
RAW_DATA
{"type":"taroko", "id":"0", "humidity":"50", "temperature":"30.2" }
COMMAND
{"value":"STUDYROOM-LIGHT_ON"}
{"value":"BEEPER_1_ON"}

COMMAND.DISPLAY
{"id":"DISPLAY1", "value":"PLAY" }

ADMIN
{"type":"NODE_MANAGER","id":"s2h.application.aircon.bl313","value":"CLOSE"}
{"type":"NODE_MANAGER","id":"s2h.application.aircon.bl313","value":"STOP"}
{"type":"NODE_DIR_MANAGER", "function":"REGISTER","name":"SERVICE_NAME","state":"ACTIVE"}
{"type":"NODE_DIR_MANAGER","function":"QUERY","name":"SERVICE_NAME"} -> {"state":"ACTIVE"}
{"type":"NODE_DIR_MANAGER","function":"QUERY_ALL"} -> [{name:SERVICE_NAME_1,state:ACTIVE},{name:SERVICE_NAME_2,state:ACTIVE},...]


WinShark Filter
udp.srcport == 1900 || udp.dstport == 1900

