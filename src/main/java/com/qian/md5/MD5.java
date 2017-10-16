package com.qian.md5;

/**
 * Created by User on 2017/6/7.
 */
public class MD5 {
    private static  int A0 = 0X01234567;
    private static  int B0 = 0X89abcdef;
    private static  int C0 = 0Xfedcba98;
    private static  int D0 = 0X76543210;
    /*private static  int A0 = 0x67452301;
    private static  int B0 = 0xefcdab89;
    private static  int C0 = 0x98badcfe;
    private static  int D0 = 0x10325476;*/
    private static int Atemp = 0X01234567;
    private static int Btemp = 0X89abcdef;
    private static int Ctemp = 0Xfedcba98;
    private static int Dtemp = 0X76543210;
    private static byte[] originMessage;
    private static byte[] finalAppend;
    private static int[] groupMessage;
    /*
    *常量ti
    *公式:floor(abs(sin(i+1))×(2pow32)
    */
    private static  final int T[] = {
            0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
            0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501, 0x698098d8,
            0x8b44f7af, 0xffff5bb1, 0x895cd7be, 0x6b901122, 0xfd987193,
            0xa679438e, 0x49b40821, 0xf61e2562, 0xc040b340, 0x265e5a51,
            0xe9b6c7aa, 0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
            0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed, 0xa9e3e905,
            0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a, 0xfffa3942, 0x8771f681,
            0x6d9d6122, 0xfde5380c, 0xa4beea44, 0x4bdecfa9, 0xf6bb4b60,
            0xbebfbc70, 0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
            0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665, 0xf4292244,
            0x432aff97, 0xab9423a7, 0xfc93a039, 0x655b59c3, 0x8f0ccc92,
            0xffeff47d, 0x85845dd1, 0x6fa87e4f, 0xfe2ce6e0, 0xa3014314,
            0x4e0811a1, 0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391};
    /*
   *向左位移数,计算方法未知
   */
    private static  final int offset[] = {7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7,
            12, 17, 22, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
            4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 6, 10,
            15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21};

    /*下面的5步计算信息的报文摘要。
            （1） 补位
    MD5算法是对输入的数据进行补位，使得如果数据位长度LEN对512求余的结果是448。即数
    据扩展至K*512+448位。即K*64+56个字节，K为整数。补位操作始终要执行，即使数据长度LEN
    对512求余的结果已是448。
    具体补位操作：补一个1，然后补0至满足上述要求。总共最少要补一位，最多补512位。
            （2） 补数据长度
    用一个64位的数字表示数据的原始长度b，把b用两个32位数表示。那么只取B的低64位。
    当遇到b大于2^64这种极少遇到的情况时，这时，数据就被填补成长度为512位的倍数。也就是说，
    此时的数据长度是16个字（32位）的整数倍数。用M[0 ... N-1]表示此时的数据，其中的N是16
    的倍数。
            （3） 初始化MD缓冲器
    用一个四个字的缓冲器（A，B，C，D）来计算报文摘要，A,B,C,D分别是32位的寄存器，初
            始化使用的是十六进制表示的数字
    A=0X01234567
    B=0X89abcdef
    C=0Xfedcba98
    D=0X76543210
            （4） 处理位操作函数
    首先定义4个辅助函数，每个函数的输入是三个32位的字，输出是一个32位的字。
    X，Y，Z为32位整数。
    F(X,Y,Z) = XY v not(X) Z
    G(X,Y,Z) = XZ v Y not(Z)
    H(X,Y,Z) = X xor Y xor Z
    I(X,Y,Z) = Y xor (X v not(Z))
    这一步中 使用一个64元素的常数组T[1 ... 64]，它由sine函数构成，T[i]表示数组中的第i个元
    素，它的值等于经过4294967296次abs(sin(i))后的值的整数部分（其中i是弧度 ）。T[i]为32位
    整数用16进制表示，数组元素在附录中给出。
    具体过程如下：
    *//* 处理数据原文 *//*
    For i = 0 to N/16-1 do
    *//*每一次，把数据原文存放在16个元素的数组X中. *//*
    For j = 0 to 15 do
    Set X[j] to M[i*16+j].
    end  /结束对J的循环
*//* Save A as AA, B as BB, C as CC, and D as DD. *//*
            AA = A
    BB = B
            CC = C
    DD = D
*//* 第1轮*//*
*//* 以 [abcd k s i]表示如下操作
a = b + ((a + F(b,c,d) + X[k] + T[i]) <<< s). *//*
*//* Do the following 16 operations. *//*
[ABCD 0 7 1] [DABC 1 12 2] [CDAB 2 17 3] [BCDA 3 22 4]
            [ABCD 4 7 5] [DABC 5 12 6] [CDAB 6 17 7] [BCDA 7 22 8]
            [ABCD 8 7 9] [DABC 9 12 10] [CDAB 10 17 11] [BCDA 11 22 12]
            [ABCD 12 7 13] [DABC 13 12 14] [CDAB 14 17 15] [BCDA 15 22 16]
*//* 第2轮* *//*
*//* 以 [abcd k s i]表示如下操作
a = b + ((a + G(b,c,d) + X[k] + T[i]) <<< s). *//*
*//* Do the following 16 operations. *//*
            [ABCD 1 5 17] [DABC 6 9 18] [CDAB 11 14 19] [BCDA 0 20 20]
            [ABCD 5 5 21] [DABC 10 9 22] [CDAB 15 14 23] [BCDA 4 20 24]
            [ABCD 9 5 25] [DABC 14 9 26] [CDAB 3 14 27] [BCDA 8 20 28]
            [ABCD 13 5 29] [DABC 2 9 30] [CDAB 7 14 31] [BCDA 12 20 32]
*//* 第3轮*//*
*//* 以 [abcd k s i]表示如下操作
a = b + ((a + H(b,c,d) + X[k] + T[i]) <<< s). *//*
*//* Do the following 16 operations. *//*
            [ABCD 5 4 33] [DABC 8 11 34] [CDAB 11 16 35] [BCDA 14 23 36]
            [ABCD 1 4 37] [DABC 4 11 38] [CDAB 7 16 39] [BCDA 10 23 40]
            [ABCD 13 4 41] [DABC 0 11 42] [CDAB 3 16 43] [BCDA 6 23 44]
            [ABCD 9 4 45] [DABC 12 11 46] [CDAB 15 16 47] [BCDA 2 23 48]
*//* 第4轮*//*
*//* 以 [abcd k s i]表示如下操作
a = b + ((a + I(b,c,d) + X[k] + T[i]) <<< s). *//*
*//* Do the following 16 operations. *//*
            [ABCD 0 6 49] [DABC 7 10 50] [CDAB 14 15 51] [BCDA 5 21 52]
            [ABCD 12 6 53] [DABC 3 10 54] [CDAB 10 15 55] [BCDA 1 21 56]
            [ABCD 8 6 57] [DABC 15 10 58] [CDAB 6 15 59] [BCDA 13 21 60]
            [ABCD 4 6 61] [DABC 11 10 62] [CDAB 2 15 63] [BCDA 9 21 64]
*//* 然后进行如下操作 *//*
    A = A + AA
            B = B + BB
    C = C + CC
            D = D + DD
    end *//* 结束对I的循环*//*
（5） 输出结果*/
    public static byte[] bytes(String message) {
        return message.getBytes();
    }

    private static int calK(int length) {
        int k = 0;
        while (k * 64 + 56 < length) {
            k++;
        }
        return k;
    }

    /*（1） 补位
    MD5算法是对输入的数据进行补位，使得如果数据位长度LEN对512求余的结果是448。即数
    据扩展至K*512+448位。即K*64+56个字节，K为整数。补位操作始终要执行，即使数据长度LEN
    对512求余的结果已是448。
    具体补位操作：补一个1，然后补0至满足上述要求。总共最少要补一位，最多补512位。*/
    public static byte[] append(byte[] origin) {
        originMessage = origin;
        int k = calK(origin.length);
        byte[] appended = new byte[k * 64 + 56 + 8];
        System.arraycopy(originMessage, 0, appended, 0, originMessage.length);
        byte first = -128;
        byte next = 0x0;
        if (origin.length % 64 != 56) {
            appended[origin.length] = first;
        }
        for (int i = origin.length + 1; i < appended.length; i++) {
            appended[i] = next;
        }
        return appended;
    }

    /* （2） 补数据长度
 用一个64位的数字表示数据的原始长度b，把b用两个32位数表示。那么只取B的低64位。当遇到b大于2^64这种极少遇到的情况时，这时，数据就被填补成长度为512位的倍数。也就是说，
 此时的数据长度是16个字（32位）的整数倍数。用M[0 ... N-1]表示此时的数据，其中的N是16
 的倍数。*/
    public static byte[] appendLength(byte[] append) {
        long length = originMessage.length * 8;
        long mask = 0xFFL;
        int cnt = 0;
        byte by = 0;
        while (cnt < 8) {
            by = (byte) ((length & mask) >>> (cnt * 8));
            append[append.length - (8 - cnt)] = by;
            cnt++;
        }
        finalAppend = append;
        return append;
    }

    /**
     * 分组
     *
     * @param finalAppend
     * @return
     */
    public static int[] group(byte[] finalAppend) {
        int[] group = new int[16];
        for (int i = 0; i < finalAppend.length / 4; i++) {
            for (int j = 0; j < 4; j++) {
                int b = finalAppend[i * 4 + j];
                if (b < 0) {
                    b = b & 0x000000FF;//负数高三字节掩去
                }
                int i1 = group[i] << 8;
                group[i] = b | (i1);
            }
        }
        groupMessage = group;
        return group;

    }
    /*
    *移动一定位数
    */
    private   static int    shift(int a,int s){
        return(a<<s)|(a>>>(32-s));//右移的时候，高位一定要补零，而不是补充符号位
    }

    public static void mainLoop() {
        int F,g;
        int A=A0;
        int B=B0;
        int C=C0;
        int D=D0;
        for (int i = 0; i <64 ; i++) {
            if(i<=15){
                F=(B&C)|((~B)&D);
                g=i;
            }
            else if(i<=31){
                F=(D&B)|((~D)&C);
                g=(5*i+1)%16;
            }
            else if(i<=47){
                F=B^C^D;
                g=(3*i+5)%16;
            }
            else {
                F=C^(B|(~D));
                g=(7*i)%16;
            }
            int tempD=D;
            D=C;
            C=B;
            B=B+shift(A+F+groupMessage[g]+T[i],offset[i]);
           // b=b+((a+F+groupMessage[g]+T[i])<<offset[i]);
            A=tempD;
        }
        Atemp=A0+A;
        Btemp=B0+B;
        Ctemp=C0+C;
        Dtemp=D0+D;
    }
    /*
    *整数变成16进制字符串
    */
    private static  String changeHex(int a){
        String str="";
        for(int i=0;i<4;i++){
            str+=String.format("%2s", Integer.toHexString(((a>>i*8)%(1<<8))&0xff)).replace(' ', '0');

        }
        return str;
    }
    public static void md5(){
        mainLoop();
        System.out.println(changeHex(A0));
        System.out.println(changeHex(B0));
        System.out.println(changeHex(C0));
        System.out.println(changeHex(D0));
        System.out.println("======");
        System.out.println(Integer.toHexString(A0));
        System.out.println(Integer.toHexString(B0));
        System.out.println(Integer.toHexString(C0));
        System.out.println(Integer.toHexString(D0));
        System.out.println("==========>");
        System.out.println(Integer.toHexString(Atemp));
        System.out.println(Integer.toHexString(Btemp));
        System.out.println(Integer.toHexString(Ctemp));
        System.out.println(Integer.toHexString(Dtemp));

    }
}
