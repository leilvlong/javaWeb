package com.tomcat.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.*;

/*
工厂对象:
    单例存在 协助创建对象 达到解耦作用 解决硬编码等

空指针异常原因:
    在xml获取ContactJdbc对象时 连接数据库也需要花费很多资源
    所以在xml中将ContactJdbc放在第一行就解决问题了
    (这个连接过程大概需要1.5秒左右 你们通过jdbc连接数据库时可以感受一下是不是卡顿一下程序才开始执行 )
在不涉及到数据库连接时:
    程序书写上下文无论怎么改动
    也不会出空指针异常更验证我的猜想
 */
public class GetCoantactProperty implements IGetBean{
    private static GetCoantactProperty gp = null;

    private GetCoantactProperty() {
    }

    private static Map<String,Object> contactProperty = new HashMap<>();
    static {
        InputStream is = GetCoantactProperty.class.getClassLoader().getResourceAsStream("bean.xml");
        SAXReader reader = new SAXReader();
        try {
            Document read = reader.read(is);
            List<Element> list =  read.selectNodes("//bean");
            for (Element element : list) {

                System.out.println("正在加载" + element.attributeValue("id"));

                String id = element.attributeValue("id");
                String className = element.attributeValue("class");
                Object obj = Class.forName(className).newInstance();
                contactProperty.put(id,obj);
            }
        } catch (Exception e) {
            throw new RuntimeException("获取对象异常");
        }
    }

    public Object getBean(String key){
        System.out.println("获取对象: "+ key + ": " + contactProperty.get(key));
        return contactProperty.get(key);
    }

    public static synchronized GetCoantactProperty getGCP(){
        if(gp == null ){
            gp = new GetCoantactProperty();
        }
        return gp;
    }

       /* // 第一种方式
    public Object getBean(String className){
        Object obj = null;
        try{
            obj = Class.forName(className).newInstance();
        }catch (Exception e){
            throw new RuntimeException("获取对象异常");
        }
        return obj;
    }*/

    /*public Object getBean(String key){
        ResourceBundle rb = ResourceBundle.getBundle("bean");
        String className = rb.getString(key);
        Object obj = null;
        try{
            obj = Class.forName(className).newInstance();
        }catch (Exception e){
            throw new RuntimeException("获取对象异常");
        }
        return obj;
    }*/

    /*public Object getBean(String key){
        InputStream is = getClass().getClassLoader().getResourceAsStream("bean.xml");
        SAXReader reader = new SAXReader();
        try {
            Document read = reader.read(is);
            Element clazz =(Element) read.selectSingleNode("//bean[@id='" + key + "']");
            String className = clazz.attributeValue("class");
            Object obj = Class.forName(className).newInstance();
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("获取对象异常");
        }
    }*/

    public static void main(String[] args) {
        GetCoantactProperty gcp = GetCoantactProperty.getGCP();
        gcp.getBean("contactJdbc");
        gcp.getBean("contactService");
    }
}
