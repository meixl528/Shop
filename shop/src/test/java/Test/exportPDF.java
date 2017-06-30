package Test;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

public class exportPDF {
    
    public static void main(String[] args) throws Exception, DocumentException {
        
        List<String> ponum=new ArrayList<String>();
        add(ponum, 9);
        List<String> line=new ArrayList<String>();
        add(line, 9);
        List<String> part=new ArrayList<String>();
        add(part, 9);
        List<String> description=new ArrayList<String>();
        add(description, 9);
        List<String> origin=new ArrayList<String>();
        add(origin, 9);
        
        //Create Document Instance
        Document document=new Document();
        
        //add Chinese font
        BaseFont bfChinese=BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        
        //Font headfont=new Font(bfChinese,10,Font.BOLD);
        Font keyfont=new Font(bfChinese,11,Font.NORMAL);
        Font textfont=new Font(bfChinese,8,Font.NORMAL);
        
        //Create Writer associated with document
        PdfWriter.getInstance(document, new FileOutputStream(new File("C:\\Users\\meixl\\Downloads\\123.pdf")));
        
        document.open();
        
        //Seperate Page controller
        int perPage=10;
        int totalPage=ponum.size()%perPage>1? ponum.size()/perPage+1 :ponum.size()/perPage;
        
        for(int j=0;j<totalPage;j++){
            document.newPage();
            
            //create page number
/*            String pageNo=leftPad("页码: "+(j+1)+" / "+totalPage,615);
            Paragraph pageNumber=new Paragraph(pageNo, keyfont) ;
            document.add(pageNumber);*/
             
            //create title image
            Image jpeg=Image.getInstance("C:\\Users\\meixl\\Downloads\\w.png");
            jpeg.setAlignment(Image.ALIGN_RIGHT);
            jpeg.scaleAbsolute(150, 37);
            
            //头   公司名   和  logo
            Table thead = new Table(2);
            float[] width={2.5f,2f};
            thead.setWidths(width);
            thead.setWidth(100);
            thead.setBorder(0);
            thead.setAlignment(1);
            thead.setOffset(10);
            thead.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            Paragraph p1 = new Paragraph("上海王的实业",new Font(bfChinese, 18, Font.NORMAL, Color.black));
            Cell cell=new Cell(p1);
            //设置居中
            cell.setHorizontalAlignment(2);
            thead.addCell(cell);
            cell=new Cell(jpeg);
            thead.addCell(cell);
            
            document.add(thead);
            
            Table tbody = new Table(2);
            tbody.setWidths(width);
            tbody.setWidth(100);
            tbody.setBorder(0);
            tbody.setAlignment(1);
            tbody.setOffset(10);
            tbody.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            cell=new Cell(new Paragraph("订单号:   "+"R201701051001",keyfont));
            cell.setHorizontalAlignment(3);
            tbody.addCell(cell);
            
            Paragraph p4 = new Paragraph("发件日期:  "+"2017-01-05 13:00:00",keyfont);
            p4.setIndentationRight(28);
            cell=new Cell(new Paragraph(p4));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("客户采购订单号:  "+"201701051001",keyfont));
            cell.setHorizontalAlignment(3);
            tbody.addCell(cell);
            
            Paragraph p6 = new Paragraph("客户名称:  "+"梅小龙",new Font(bfChinese, 12, Font.NORMAL, Color.red));
            p6.setIndentationRight(75);
            cell=new Cell(p6);
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            document.add(tbody);
            
            tbody = new Table(5);
            float[] width2={1.7f,3f,0.5f,1.7f,3f};
            tbody.setWidths(width2);
            tbody.setWidth(100);
            tbody.setOffset(8);

            tbody.setBorderWidthTop(1);
            tbody.setBorderColorTop(Color.black);
            tbody.setBorderWidthBottom(1);
            tbody.setBorderColorBottom(Color.black);
            tbody.setBorderWidthLeft(1);
            tbody.setBorderColorLeft(Color.LIGHT_GRAY);
            tbody.setBorderWidthRight(1);
            tbody.setBorderColorRight(Color.LIGHT_GRAY);
            
            tbody.getDefaultCell().setBorder(1);
            tbody.getDefaultCell().setBorderColor(Color.LIGHT_GRAY);
            tbody.getDefaultCell().setBorderWidthRight(1);
            tbody.getDefaultCell().setBorderColorRight(Color.LIGHT_GRAY);
            tbody.setSpaceInsideCell(4);
            //tbody.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            cell=new Cell(new Paragraph("Supplier(卖方):",textfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("抚顺天泽包装制品有限公司",textfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("",textfont));
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("Address(联系地址):",textfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("抚顺市望花区鞍山路西段56号",textfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            //第二行
            cell=new Cell(new Paragraph("Contact(联系人):",textfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("梅小龙",textfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);

            cell=new Cell(new Paragraph("",textfont));
            tbody.addCell(cell);

            cell=new Cell(new Paragraph("Tel(电话):",textfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("15012121221",textfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            //第三行
            cell=new Cell(new Paragraph("DeliveryTo(交货地点):",textfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("上海马戏城",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("OrderDate(订购日期):",textfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("2016.11.08",textfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            document.add(tbody);
            
            //第四行
            tbody = new Table(5);
            tbody.setWidths(width2);
            tbody.setWidth(100);
            //tbody.setBorder(0);
            tbody.setBorderWidthTop(0);
            tbody.setBorderColorTop(Color.black);
            tbody.setBorderWidthBottom(0);
            tbody.setBorderColorBottom(Color.black);
            tbody.setBorderWidthLeft(1);
            tbody.setBorderColorLeft(Color.LIGHT_GRAY);
            tbody.setBorderWidthRight(1);
            tbody.setBorderColorRight(Color.LIGHT_GRAY);
            tbody.setOffset(0);
            tbody.setSpaceInsideCell(4);
            tbody.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            
            cell=new Cell(new Paragraph("制单人:",keyfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("齐旭阳",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("销     售:",keyfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("史光全",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            //第五行
            cell=new Cell(new Paragraph("运营中心审核:",keyfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("无",keyfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("电     话:",keyfont));
            cell.setHorizontalAlignment(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("1350000000",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            document.add(tbody);
            
            //table4
            tbody=new Table(9);
            float[] widths3={1f,5f,3f,3f,1f,4f,3f,3f,1f};
            tbody.setWidths(widths3);
            tbody.setWidth(100);
            tbody.setOffset(10);
            
            //tbody.setBorder(1);
            tbody.setBorderWidthTop(1);
            tbody.setBorderColorTop(Color.black);
            tbody.setBorderWidthBottom(1);
            tbody.setBorderColorBottom(Color.black);
            tbody.setBorderWidthLeft(1);
            tbody.setBorderColorLeft(Color.LIGHT_GRAY);
            tbody.setBorderWidthRight(1);
            tbody.setBorderColorRight(Color.LIGHT_GRAY);
            
            tbody.getDefaultCell().setBorder(1);
            tbody.getDefaultCell().setBorderColor(Color.LIGHT_GRAY);
            tbody.getDefaultCell().setBorderWidthRight(1);
            tbody.getDefaultCell().setBorderColorRight(Color.LIGHT_GRAY);
            tbody.setOffset(0);
            tbody.setSpaceInsideCell(6);
            
            cell=new Cell(new Paragraph("序号",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("品名",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("规格",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("数量",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("单位",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("配材",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("单价(含税)",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("到货时间",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("备注",keyfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            //数据循环
            for(int i=0;i<3;i++){
            	cell=new Cell(new Paragraph((i+1)+"",textfont));
                cell.setHorizontalAlignment(1);
                tbody.addCell(cell);
                
                cell=new Cell(new Paragraph("品名"+(i+1),textfont));
                cell.setHorizontalAlignment(1);
                tbody.addCell(cell);
                
                cell=new Cell(new Paragraph("规格"+(i+1),textfont));
                cell.setHorizontalAlignment(1);
                tbody.addCell(cell);
                
                cell=new Cell(new Paragraph("数量"+(i+1),textfont));
                cell.setHorizontalAlignment(1);
                tbody.addCell(cell);
                
                cell=new Cell(new Paragraph("单位"+(i+1),textfont));
                cell.setHorizontalAlignment(1);
                tbody.addCell(cell);
                
                cell=new Cell(new Paragraph("配材"+(i+1),textfont));
                cell.setHorizontalAlignment(1);
                tbody.addCell(cell);
                
                cell=new Cell(new Paragraph("单价(含税)"+(i+1),textfont));
                cell.setHorizontalAlignment(1);
                tbody.addCell(cell);
                
                cell=new Cell(new Paragraph("到货时间"+(i+1),textfont));
                cell.setHorizontalAlignment(1);
                tbody.addCell(cell);
                
                cell=new Cell(new Paragraph("备注"+(i+1),textfont));
                cell.setHorizontalAlignment(1);
                tbody.addCell(cell);
            }
            
            cell=new Cell(new Paragraph("说明",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(7);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("收货地址",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(7);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("收货联系人",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("联系电话",textfont));
            cell.setHorizontalAlignment(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(3);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("备注",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(7);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("供应商确认",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("确认日期",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(1);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            cell=new Cell(new Paragraph("请于1个工作日内回传此订单，否则视为默认.",textfont));
            cell.setHorizontalAlignment(1);
            cell.setColspan(2);
            tbody.addCell(cell);
            
            document.add(tbody);
            
            //段落(Paragraph)  短语(Phrase)  文本块(Chunk)
            //Paragraph pLeft = new Paragraph();
            //Phrase phrase = new Phrase();
            //Chunk chunk = new Chunk();
            
            //header informations
            
            if(j+1==totalPage){
                Paragraph foot11 = new Paragraph("文件只作  Foster 收貨用"+printBlank(55)+"____________________",keyfont);
                document.add(foot11);
                Paragraph foot12 = new Paragraph("Printed from Foster supplier portal"+printBlank(38)+"上海王的实业有限公司"+printBlank(40)+"版本: 1.0",keyfont);
                document.add(foot12);
                HeaderFooter footer11=new HeaderFooter(foot11, true);
                footer11.setAlignment(HeaderFooter.ALIGN_BOTTOM);
                HeaderFooter footer12=new HeaderFooter(foot12, true);
                footer12.setAlignment(HeaderFooter.ALIGN_BOTTOM);
            }
        }
        document.close();
    }
    
    public static String leftPad(String str, int i) {
        int addSpaceNo = i-str.length();
        String space = ""; 
        for (int k=0; k<addSpaceNo; k++){
                space= " "+space;
        };
        String result =space + str ;
        return result;
     }
    
    public static void add(List<String> list,int num){
        for(int i=0;i<num;i++){
            list.add("test"+i);
        }
    }
    
    public static String printBlank(int tmp){
          String space="";
          for(int m=0;m<tmp;m++){
              space=space+" ";
          }
          return space;
    }

}