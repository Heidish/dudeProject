package com.dude.dudeproject.System;

import java.util.ArrayList;
import java.util.Random;

public class RandomClass {

    public ArrayList<StringBuffer> numrandom() {

        ArrayList<StringBuffer> random = new ArrayList<>();
        Random r = new Random();

        StringBuffer buf = new StringBuffer();

        for (int j = 0; j < 5; j++) {

            for (int i = 0; i < 5; i++) {
                // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한
                // 숫자를 StringBuffer 에 append 한다.
                if (r.nextBoolean()) {
                    buf.append((char) ((int) (r.nextInt(26)) + 97));
                } else {
                    buf.append((r.nextInt(10)));
                }

            }
            random.add(buf);

        }

        return random;

    }


}


