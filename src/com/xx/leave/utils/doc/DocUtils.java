package  com.xx.leave.utils.doc;
import java.io.*;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import sun.misc.BASE64Encoder;


public class DocUtils {
    public Configuration configure=null;

    public DocUtils(){
        configure=new Configuration();
        configure.setDefaultEncoding("utf-8");
    }

    public void createDoc(Map<String,Object> dataMap, String downloadType, String savePath){
        try {
       
            Template template=null;

            configure.setClassForTemplateLoading(this.getClass(), "/");

            configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);

            template=configure.getTemplate(downloadType+".xml");
            File outFile=new File(savePath);
            Writer out=null;
            out=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
            template.process(dataMap, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public String getImageStr(String imgFile){
        InputStream in=null;
        byte[] data=null;
        try {
            in=new FileInputStream(imgFile);
            data=new byte[in.available()];
            in.read(data);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder=new BASE64Encoder();
        return encoder.encode(data);
    }

}
