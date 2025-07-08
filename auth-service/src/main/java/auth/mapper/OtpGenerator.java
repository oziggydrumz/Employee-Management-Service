package auth.mapper;

import org.springframework.stereotype.Component;

@Component
public class OtpGenerator {
    private int otpNumber=4;


    public String generate(){
        int[]otpGenerator=new int[otpNumber];
        for (int i=0;i<otpGenerator.length;i++){
            otpGenerator[i]=(int)(Math.random()*9);


        }

        String myOtp=""+otpGenerator[0]+otpGenerator[1]+otpGenerator[2]+otpGenerator[3];
        return myOtp;
    }
}
