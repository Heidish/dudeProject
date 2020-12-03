package com.dude.dudeproject.System;


import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Random;


public class SmsClass {

    private int certNumLength = 6;

    public void smsText(String phoneNumber) {

        String api_key = "NCSDFJQCQZBN8HGD";
        String api_secret = "LXGWX1ODRGONQXLYUWYB1EBJ8E6AWC4D";
        Message coolsms = new Message(api_key, api_secret);

        // 6자리 인증번호 생성
        String certNum = "";
        for (int i = 0; i < 6; i++) {
            Double d = Math.random() * 9 + 1;
            certNum += Integer.toString(d.intValue());
        }

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber); // 수신전화번호
        params.put("from", "01094548994"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "DUDE Service 회원가입 휴대폰인증 메시지 : DUDE 서비스에 가입하시려면 다음 인증번호를 입력 해 주세요.");
        params.put("certNum", certNum); // 인증번호 (랜덤 6자리수)
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        }
    }

