package com.poolsawat.dynamic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.dynamicreports.jasper.constant.ImageType;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

/**
 *
 * @author poola410
 */
public class GenarateReport {

    public GenarateReport() {
        build();
    }

    private void build() {
        try {
            report()//create new report design
                    //.setDefaultFont(stl.font().setFontName("TH SarabunPSK").setFontSize(16))                    
                    .columns(
                            col.column("PostId", "id", type.integerType())
                                    .setStyle(getTableStyle())
                                    .setTitleStyle(getTableStyle()),
                            col.column("PostTitle", "title", type.stringType())
                                    .setStyle(getTableStyle())
                                    .setTitleStyle(getTableStyle()),
                            col.column("PostAuthor", "author", type.stringType())
                                    .setStyle(getTableStyle())
                                    .setTitleStyle(getTableStyle()),
                            col.column("PostPublicDate", "public_date", type.dateType())
                                    .setStyle(getTableStyle())
                                    .setTitleStyle(getTableStyle()))
                    .title(cmp.text("PoolsawatBlogs"))//shows report title
                    .pageFooter(cmp.pageXofY())//shows number of page at page footer
                    .setDataSource(createDataSource())//set datasource
                    //.show();//create and show report
                    .toPdf(new FileOutputStream(new File("./src/main/resources/output/poolsawat.pdf")))
                    .toXls(new FileOutputStream(new File("./src/main/resources/output/poolsawat.xls")))
                    .toCsv(new FileOutputStream(new File("./src/main/resources/output/poolsawat.csv")))
                    .toXlsx(new FileOutputStream(new File("./src/main/resources/output/poolsawat.xlsx")))
                    .toHtml(new FileOutputStream(new File("./src/main/resources/output/poolsawat.html")))
                    .toPptx(new FileOutputStream(new File("./src/main/resources/output/poolsawat.pptx")))
                    .toText(new FileOutputStream(new File("./src/main/resources/output/poolsawat.text")))
                    .toImage(new FileOutputStream(new File("./src/main/resources/output/poolsawat.jpg")), ImageType.JPG)
                    .toXml(new FileOutputStream(new File("./src/main/resources/output/poolsawat.xml")));
                    
        } catch (DRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenarateReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private StyleBuilder getTableStyle(){
        return stl.style().setBorder(stl.border(stl.pen1Point()))
                .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("id", "title","author","public_date");
        dataSource.add(1215,"Quartz Scheduler มันคืออะไร การใช้งานเบื้องต้น", "admin", new Date());
        dataSource.add(1150,"Gson Open Source Library สำหรับจัดการ JSON Formatter", "admin", new Date());
        dataSource.add(1132,"สร้าง Spring MVC 4 ร่วมกับ Apache Tiles 3", "admin", new Date());
        dataSource.add(1099,"[ES6] Promise คืออะไร", "admin", new Date());
        dataSource.add(1072,"ประสบการณ์ทำเว็บไซต์ให้ปลอดภัยด้วย HTTPS ไม่ยากอย่างที่คิด", "admin", new Date());
        dataSource.add(1025,"เล่าประสบการณ์แข่งขัน Hackathon ครั้งแรกให้ชีวิต", "admin", new Date());
        dataSource.add(1002,"สรุปสิ่งที่ได้รับจากงาน Cloud Functions for Firebase and Next Generation of Web", "admin", new Date());
        return dataSource;
    }

    public static void main(String[] args) {
        new GenarateReport();
    }
}
