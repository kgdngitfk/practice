import com.qian.Utils.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by User on 2017/6/8.
 */
public class TestMD5 {
    @Test
    public void test() {
        byte[] jklmns = MD5.bytes("jklmn");
        System.out.println(DatatypeConverter.printHexBinary(jklmns));
    }

    @Test
    public void testAppend() {
        byte[] jklmns = MD5.bytes("jklmn");
        byte[] append = MD5.append(jklmns);
        System.out.println(DatatypeConverter.printHexBinary(append));
        System.out.println(DatatypeConverter.printHexBinary(append).length() * 4);
    }

    @Test
    public void testAppendLength() {
        byte[] jklmns = MD5.bytes("jklmn");
        byte[] append = MD5.append(jklmns);
        System.out.println(DatatypeConverter.printHexBinary(append));
        byte[] bytes = MD5.appendLength(append);
        int[] group = MD5.group(bytes);
        for (int i = 0; i < group.length; i++) {
            System.out.print(Integer.toHexString(group[i])+"\t");
        }
        System.out.println(DatatypeConverter.printHexBinary(bytes));
        System.out.println(DatatypeConverter.printHexBinary(append).length() * 4);
        MD5.md5();

    }

    @Test
    public void testGe() {
        long cnt = 1;
        double start = 1;
        /*while (cnt++ <= 4294967296L) {
            start = Math.abs(Math.sin(start));
        }*/
        System.out.println(((int) start));
    }

    @Test
    public void test4() {
        int i = Integer.parseInt("6A6B6C6D", 16);
        i = 1785425005;
        System.out.println(Integer.toHexString(i));
        System.out.println(i);
        HashMap<Object, Object> objectObjectHashMap = new HashMap();
        objectObjectHashMap.put(null,null);
    }
   // @Test
    public void  test5() throws IOException {
        Document document = Jsoup.parse("http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201703/t20170310_1471429.html");
        System.out.println(document);
        System.out.println(document.select(".MsoNormal"));
        Elements select = document.select(".MsoNormal");
        for (Element e:select
             ) {
            System.out.println(e.toString());
        }
        System.out.println(document.toString());
    }
   // @Test
    public void test6() throws  Exception{
        //Jsoup.parse()似乎获取不了数据
        Document document = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201703/t20170310_1471429.html").post();
        //在浏览器在分析其DOM结构得到如下解析步骤
        Elements select = document.select(".MsoNormal");
        List<Area> areas = new ArrayList<Area>();
        int size =0;
        Area area =null;
        Integer parentCode =null;
        for (Element e:select) {
            Elements span = e.select("span");
             size = span.size();
                area = new Area();
                if(size==3){//是省级或直辖市
                    ////比较坑,String s="110000    ";拿到的文本内容如s,直接用String.trim()出来的字符串还是无法解析成数字所以自己写了一个
                    //parentCode=Integer.parseInt(StringUtil.trim(span.get(0).text().trim()));
                    System.out.println(span.get(0).text().trim()+"|");
                    area.setCode(parentCode);
                    /**拿到的name里面有全角的空格没处理*/
                    area.setName(span.get(2).text().trim());
                    System.out.println("|"+span.get(2).text().trim()+"|");
                }
                else if(size==4){//是省级下面
                    area.setParentCode(parentCode);
                    //area.setCode(Integer.parseInt(StringUtil.trim(span.get(1).text().trim())));
                    System.out.println(span.get(1).text().trim()+"|");
                    area.setName(span.get(3).text().trim());
                    System.out.println("|"+span.get(3).text().trim()+"|");
                }
                areas.add(area);
                //System.out.println(span.toString());
        }
        System.out.println("总共解析到"+areas.size()+"个地区数据");
        /**
         * 入库,建表和数据库相关操作不再缀述
         */
        /*java.sql.Connection connection = MyTest.getConnection();
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into area values(?,?,?)");
        for (Area a:areas
             ) {
            preparedStatement.setInt(1,a.getCode());
            preparedStatement.setString(2,a.getName());
            preparedStatement.setObject(3,a.getParentCode());
            preparedStatement.execute();
        }
        connection.commit();
        connection.close();*/

        //System.out.println(document);
    }
    @Test
    public void test7(){
        String s="110000    ";
        char[] chars = s.toCharArray();
        byte[] bytes = s.getBytes();
        for (byte b:bytes
             ) {
            System.out.println(b);
        }
        System.out.println(s.trim()+"|");
        String trim = StringUtil.trim(s+"|");
        System.out.println(trim);
    }

    @Test
    public void test8(){
        Long aLong = new Long("2");
        Integer integer = new Integer("2");
        System.out.println(integer.equals(aLong));
    }
    @Test
    public void  test9(){
        String s= "　　托里县";
        System.out.println(s.length());
        byte[] bytes = s.getBytes();
        System.out.println(bytes.length);
        for (int i = 0; i <bytes.length ; i++) {
            System.out.println(bytes[i]);
        }
    }
    @Test
    public void  test10(){
        String ss= "　　托里县";
        byte[] bytes = ss.getBytes();
        System.out.println(bytes.length);
        for (int i = 0; i <bytes.length ; i++) {
            System.out.println(bytes[i]);
        }
        String s= "　　托里县";
        char[] chars = s.toCharArray();
        System.out.println(chars.length);
        for (int i = 0; i <chars.length ; i++) {
            System.out.println(chars[i]);
        }
        byte [] b={-27,-114,-65};
        byte [] b1={-65};
        System.out.println("=====");
        System.out.println(new String(b));
    }

    @Test
    public void test34(){
        Class<? extends TestMD5> aClass = this.getClass();
        Method method = aClass.getDeclaredMethods()[0];
        System.out.println(method.toString());
        String s = method.getClass().toString() + method.getName();
        System.out.println(s);
    }


}
