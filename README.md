# 사용법

#### 실행순서 : 

1line_socket_final.py(PC) ->appserver_real.py(리눅스) -> Steering(어플)

#### 매번 바꿔야하는 사항 : 

1line_socket_final.py(PC) 와 move_final.py(리눅스) 의 HOST와 PORT를 PC의 IP,PORT로 바꾸어야한다.

# 소켓 관계도
![socket_realation](https://github.com/bubblydummy/Steering/assets/126440604/c49ccb41-63ea-4476-b51d-4d6d083c56b3)

# 기계 관련 주의점

sleep(1), speed(100) 이하일시, 작동 하지 않을 확률 올라감
