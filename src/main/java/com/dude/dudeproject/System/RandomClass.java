package com.dude.dudeproject.System;

/**
 * random generate class
 */
public class RandomClass {

    /**
     * random generate method
     * @param size
     * @return
     */
    public static String numrandom(int size) {
        if (size > 0) {
            char[] tmp = new char[size];
            for (int i = 0; i < tmp.length; i++) {
                int div = (int) Math.floor(Math.random() * 2);

                if (div == 0) { //0이면 숫자로
                    tmp[i] = (char) (Math.random() * 10 + '0');
                } else { //1이면 알파벳
                    tmp[i] = (char) (Math.random() * 26 + 'A');
                }
            }

            // random 중복확인. db에서 값 확인하고 그리고 나서
           return new String(tmp);
        }
        return "Error : size is required.";
    }
}



